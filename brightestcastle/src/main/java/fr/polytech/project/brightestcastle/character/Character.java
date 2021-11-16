package fr.polytech.project.brightestcastle.character;

public class Character {
	
	private String name;
	private CharacterClass job;
	private byte constitution; // give more HP
	private byte strength; // give more ATT
	private byte vigor; // give more STA regen each turn
	

	private int HP;
	private int HPMax;
	private int ATK;
	private int STA; // same for everyone, regen each turn
	private int DEF;
	
	public Character(String name, CharacterClass job,byte constitution, byte strength, byte vigor) {
		super();
		this.name = name;
		this.job = job;
		this.constitution = constitution;
		this.strength = strength;
		this.vigor = vigor;
		
		this.HP = 25 +  constitution * 5;
		this.ATK = 3 + strength;
		this.STA = 20;
	}

	public byte getConstitution() {
		return constitution;
	}

	public void setConstitution(byte constitution) {
		this.constitution = constitution;
	}

	public byte getStrength() {
		return strength;
	}

	public void setStrength(byte strength) {
		this.strength = strength;
	}

	public byte getVigor() {
		return vigor;
	}

	public void setVigor(byte vigor) {
		this.vigor = vigor;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getHPMax() {
		return HPMax;
	}

	public void setHPMax(int hPMax) {
		HPMax = hPMax;
	}

	public int getATK() {
		return ATK;
	}

	public void setATK(int aTK) {
		ATK = aTK;
	}

	public int getSTA() {
		return STA;
	}

	public void setSTA(int sTA) {
		STA = sTA;
	}

	public int getDEF() {
		return DEF;
	}

	public void setDEF(int dEF) {
		DEF = dEF;
	}
	
	public void takeDamage(int damage) {
		
		setHP(getHP()-(damage-getDEF()));
	
	}
}
