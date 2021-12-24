package fr.polytech.project.brightestcastle.entity;

public abstract class Monster extends Entity {
	public Monster(String name, byte STR, byte CON) {
		super(name, STR, CON, (byte) 0);
	}
	
	public abstract MonsterType getType();
}