package fr.polytech.project.brightestcastle.entity;

import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;

public abstract class Character extends Entity {
	private String name;
	private int threat;
	
	public Character(String name, byte STR, byte CON, byte VIG) {
		super(STR, CON, VIG);
		setName(name);
		setThreat(0);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
