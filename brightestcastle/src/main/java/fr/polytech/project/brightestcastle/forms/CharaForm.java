package fr.polytech.project.brightestcastle.forms;

import fr.polytech.project.brightestcastle.controller.CreationController;
import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.Bowman;
import fr.polytech.project.brightestcastle.entity.CharacterJob;
import fr.polytech.project.brightestcastle.entity.Paladin;
import fr.polytech.project.brightestcastle.entity.Warrior;

/**
 * The class holding the data from the character creation form.
 * @see CreationController
 */
public class CharaForm {
	public static int STAT_POINT = 8;
	
	private String name;
	
	private CharacterJob job;
	
	private byte CON;
	private byte STR;
	private byte VIG;
	
	public CharaForm() {}
	public CharaForm(String name, CharacterJob job, byte CON, byte STR, byte VIG) {
		setName(name);
		setJob(job);
		setCON(CON);
		setSTR(STR);
		setVIG(VIG);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setJob(CharacterJob job) {
		this.job = job;
	}
	
	public CharacterJob getJob() {
		return this.job;
	}
	
	public void setCON(byte CON) {
		this.CON = CON;
	}
	
	public byte getCON() {
		return this.CON;
	}
	
	public void setSTR(byte STR) {
		this.STR = STR;
	}
	
	public byte getSTR() {
		return this.STR;
	}
	
	public void setVIG(byte VIG) {
		this.VIG = VIG;
	}
	
	public byte getVIG() {
		return this.VIG;
	}
	
	/**
	 * @return the validity of the data stored in the {@link CharaForm}
	 */
	public boolean isValid() {
		boolean maxCON = CON >= 0 && CON <= 8;
		boolean maxSTR = STR >= 0 && STR <= 8;
		boolean maxVIG = VIG >= 0 && VIG <= 8;
		
		boolean maxPts = (CON + STR + VIG) == STAT_POINT;
		
		boolean validJob = (job != null);
		
		boolean validName = (name != null && !name.isEmpty());
		
		return maxCON && maxSTR && maxVIG && maxPts && validJob && validName;
	}
	
	public Character toCharacter() {
		Character chara = null;
		switch (getJob()) {
		case BOWMAN:
			chara = new Bowman(getName(), getSTR(), getCON(), getVIG());
			break;
		case PALADIN:
			chara = new Paladin(getName(), getSTR(), getCON(), getVIG());
			break;
		case WARRIOR:
			chara = new Warrior(getName(), getSTR(), getCON(), getVIG());
			break;
		}
		return chara;
	}
}