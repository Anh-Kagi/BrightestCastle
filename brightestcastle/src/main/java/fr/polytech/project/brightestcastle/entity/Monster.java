package fr.polytech.project.brightestcastle.entity;

import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;

public abstract class Monster extends Entity {
	public Monster(String name, byte STR, byte CON) {
		super(name, STR, CON, (byte) 0);
	}
	
	public abstract MonsterType getType();
	
	public abstract List<Attack<Monster>> getAttacks();
}