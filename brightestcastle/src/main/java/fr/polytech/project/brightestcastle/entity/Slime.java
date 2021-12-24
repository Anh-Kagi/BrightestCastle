package fr.polytech.project.brightestcastle.entity;

import java.util.Arrays;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.SlimeAttacks;

public class Slime extends Monster {
	MonsterType type;

	public Slime() {
		super("Slime", (byte) 2, (byte) 2);
	}

	@Override
	public MonsterType getType() {
		return MonsterType.SLIME;
	}
	
	@Override
	public List<Attack<Monster>> getAttacks() {
		return Arrays.asList(new SlimeAttacks.BASH(this),
				new SlimeAttacks.SLAM(this));
	}
}