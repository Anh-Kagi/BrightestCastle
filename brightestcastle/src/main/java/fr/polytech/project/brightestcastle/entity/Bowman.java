package fr.polytech.project.brightestcastle.entity;

import java.lang.Math;
import java.util.Arrays;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.BowmanAttacks;

public class Bowman extends Character {
	// passive: Swift: 15% chance to dodge all damage

	public Bowman(String name, byte STR, byte CON, byte VIG) {
		super(name, (byte)(STR+1), (byte)(CON+1), (byte) (VIG + 4));
	}

	@Override
	public int takeDamage(int damage) {
		if (Math.random() * 100 <= 15) {
			System.out.println("Dodged !");
			return 0;
		} else {
			int damageInflicted = damage - getDEF();
			setHP(getHP() - damageInflicted);
			return damageInflicted;
		}
	}

	@Override
	public CharacterJob getJob() {
		return CharacterJob.BOWMAN;
	}

	@Override
	public List<Attack<Character>> getAttacks() {
		return Arrays.asList(new BowmanAttacks.SHOT(this), new BowmanAttacks.STAB(this), new BowmanAttacks.ARROW(this),
				new BowmanAttacks.PIERCE(this));
	}
}