package fr.polytech.project.brightestcastle.entity;

import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;

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
	
	private int threat;

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
	
	public int getATK() {
		// TODO: return ATK + modifiers (by overriding or with status)
		return getATKbase();
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
	
	public byte getVIG() {
		// TODO: return VIG + modifiers (by overriding or with status)
		return getVIG();
	}

	public int getThreat() {
		return threat;
	}
	
	public void setThreat(int threat) {
		this.threat = threat;
	}

	public void addThreat(int threat) {
		setThreat(getThreat() + threat);
	}
	
	public int takeDamage(int damage) {
		int damageInflicted=damage-getDEF();
		setHP(getHP()-damageInflicted);
		
		return damageInflicted;
	}
	
	public int takeDamageBlinded(int damage) {
		if (Math.random()*100 <= 50) {
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

	public void addStatus(StatusEnum name, int duration) {
		Status s = new Status(name, duration);
		status.add(s);
	}

	public abstract Attack[] getAttacks();
}
