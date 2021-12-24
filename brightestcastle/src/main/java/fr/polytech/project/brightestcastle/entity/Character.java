package fr.polytech.project.brightestcastle.entity;

public abstract class Character extends Entity {
	public Character(String name, byte STR, byte CON, byte VIG) {
		super(name, STR, CON, VIG);
		setThreat(0);
	}
	
	public abstract CharacterJob getJob();
}
