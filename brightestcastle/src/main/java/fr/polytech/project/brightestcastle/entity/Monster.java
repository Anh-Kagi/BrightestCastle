package fr.polytech.project.brightestcastle.entity;

import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;

public abstract class Monster extends Entity {
	public Monster(byte STR, byte CON) {
		super(STR, CON, (byte) 0);
	}

	public abstract MonsterType getType();

	public abstract List<Attack<Monster>> getAttacks();

	public abstract Attack<Monster> selectAttack(int turn);
}