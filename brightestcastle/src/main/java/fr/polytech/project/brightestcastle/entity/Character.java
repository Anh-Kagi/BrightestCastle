package fr.polytech.project.brightestcastle.entity;

import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;

public abstract class Character extends Entity {
	private int threat;
	
	public Character(String name, byte STR, byte CON, byte VIG) {
		super(name, STR, CON, VIG);
		setThreat(0);
	}
	
	public abstract CharacterJob getJob();

	public int getThreat() {
		return threat;
	}
	
	public void setThreat(int threat) {
		this.threat = threat;
	}

	public void addThreat(int threat) {
		setThreat(getThreat() + threat);
	}

	public abstract List<Attack<Character>> getAttacks();
}
