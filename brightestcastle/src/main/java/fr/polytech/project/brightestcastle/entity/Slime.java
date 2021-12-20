package fr.polytech.project.brightestcastle.entity;

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
	public Attack[] getAttacks() {
		return new Attack[] {
			new SlimeAttacks.FIRST(this),
			new SlimeAttacks.SECOND(this)
		};
	}
}