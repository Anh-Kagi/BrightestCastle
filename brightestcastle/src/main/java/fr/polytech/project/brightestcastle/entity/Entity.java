package fr.polytech.project.brightestcastle.entity;

import java.util.List;

public abstract class Entity {
	private String name;

	private byte CON; // give more HP
	private byte STR; // give more ATK
	private byte VIG; // give more STA regen each turn

	private int HPmax;
	private int HP;
	private int ATK;
	private int DEF;
	private int STA; // same for everyone, regen each turn

	private List <Status> status;
	
	public Entity(String name, byte STR, byte CON, byte VIG) {
		setName(name);

		setHP(25 + CON*5);
		setHPmax(getHP());
		setSTA(20);
		setATKbase(STR + 3);
		
		setSTRbase(STR);
		setCONbase(CON);
		setVIGbase(VIG);
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHPmax(int HPmax) {
		this.HPmax = HPmax;
	}
	
	public int getHPmax() {
		return HPmax;
	}
	
	public void setHP(int HP) {
		this.HP = HP > getHPmax() ? getHPmax() : HP;
	}
	
	public int getHP() {
		return HP;
	}
	
	public void setATKbase(int ATK) {
		this.ATK = ATK;
	}
	
	public int getATKbase() {
		return ATK;
	}
	
	public int getATKmod() {
		int mod = 0;
		
		if (isAffected(StatusEnum.ATKUP))
			mod += 2;
		
		if (getATKbase() >= 2+1 && isAffected(StatusEnum.ATKDOWN))
			mod -= 2;
		
		return mod;
	}
	
	public int getATK() {
		return getATKbase() + getATKmod();
	}
	
	public void setSTA(int STA) {
		this.STA = STA;
	}
	
	public int getSTA() {
		return STA;
	}
	
	public void generateSTA (int vigor) {
		setSTA(getSTA()+getVIG());
	}
	
	public void setDEFbase(int DEF) {
		this.DEF = DEF;
	}
	
	public int getDEFbase() {
		return DEF;
	}
	
	public int getDEF() {
		// TODO: return DEF + modifiers (by overriding or with status)
		return getDEFbase();
	}
	
	public void setSTRbase(byte STR) {
		this.STR = STR;
	}
	
	public byte getSTRbase() {
		return STR;
	}
	
	public byte getSTR() {
		// TODO: return STR + modifiers (by overriding or with status)
		return getSTRbase();
	}
	
	public void setCONbase(byte CON) {
		this.CON = CON;
	}
	
	public byte getCONbase() {
		return CON;
	}
	
	public byte getCON() {
		// TODO: return CON + modifiers (by overriding or with status)
		return getCONbase();
	}
	
	public void setVIGbase(byte VIG) {
		this.VIG = VIG;
	}
	
	public byte getVIGbase() {
		return VIG;
	}
	
	public byte getVIGmod() {
		byte mod = 0;
		
		if (isAffected(StatusEnum.VIGORUP))
			mod += 3;
		
		if (getVIGbase() >= 3+1 && isAffected(StatusEnum.VIGORDOWN))
			mod -= 3;
	
		return mod;
	}
	
	public byte getVIG() {
		return (byte) (getVIGbase() + getVIGmod());
	}
	
	public int takeDamage(int damage) {
		int damageInflicted=damage-getDEF();
		setHP(getHP()-damageInflicted);
		
		return damageInflicted;
	}
	
	public int takeDamageBlinded(int damage) {
		if (Math.random()*100 <= 50 && isAffected(StatusEnum.POISONNED)) {
			System.out.println("You Missed your attack!");
			
			return 0;
		} else {
			int damageInflicted = damage - getDEF();
			setHP(getHP() - damageInflicted);
			
			return damageInflicted;
		}
	}
	
	public int takeTrueDamage(int damage) {
		setHP(getHP() - damage);
		return damage;
	}

	public List <Status> getListStatus() {
		return status;
	}

	public void setListStatus(List<Status> status) {
		this.status = status;
	}
	
	public boolean isAffected(StatusEnum status) {
		return getAffected(status) != -1;
	}

	public int getAffected(StatusEnum status) {
		for (int i=0; i<this.status.size(); i++)
			if(this.status.get(i).getName() == status)
				return i;
		return -1;
	}
	
	public void addStatus(StatusEnum status, int duration) {
		int pos = getAffected(status);
		
		if (pos == -1)
			this.status.add(new Status(status, duration));
		else
			this.status.get(pos).addDuration(duration);
	}

	public void statusUpdate() {
		for(int i=this.status.size()-1; i>=0; i--) {
			this.status.get(i).countDown();
			
			if(this.status.get(i).getName() == StatusEnum.POISONNED)
				takeTrueDamage(5);
			
			if (this.status.get(i).getDuration() == 0)
				this.status.remove(i);
		}
	}
}
