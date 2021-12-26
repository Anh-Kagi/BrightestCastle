package fr.polytech.project.brightestcastle.entity;

import java.util.Arrays;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.BatAttacks;

public class Bat extends Monster {
	public Bat() {
		super((byte) 3, (byte) 1);
	}

	@Override
	public int takeDamage(int damage) {
		if (Math.random() < 0.15) {
			System.out.println("The bat Dodged !");
			return 0;
		} else {
			return super.takeDamage(damage);
		}
	}

	@Override
	public MonsterType getType() {
		return MonsterType.BAT;
	}

	@Override
	public List<Attack<Monster>> getAttacks() {
		return Arrays.asList(new BatAttacks.BITE(this), new BatAttacks.BLAST(this));
	}

	@Override
	public Attack<Monster> selectAttack(int turn) {
		return turn % 3 == 0 ? getAttacks().get(1) : getAttacks().get(0); // every 3 turns, BLAST, BITE otherwise
	}
}
