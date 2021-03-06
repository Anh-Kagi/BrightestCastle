package fr.polytech.project.brightestcastle.entity;

import java.util.Arrays;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.WarriorAttacks;

public class Warrior extends Character {
	// passive = Rage -> attk + when hp low

	public Warrior(String name, byte STR, byte CON, byte VIG) {
		super(name, (byte) (STR + 4), (byte)(CON+1), (byte)(VIG+1));
	}

	@Override
	public int getATK() {
		return ((getHPmax() - getHP()) / getHPmax()) * super.getATK() + super.getATK();
	}

	@Override
	public CharacterJob getJob() {
		return CharacterJob.WARRIOR;
	}

	@Override
	public List<Attack<Character>> getAttacks() {
		return Arrays.asList(new WarriorAttacks.BASH(this), new WarriorAttacks.SWING(this),
				new WarriorAttacks.INTIMIDATE(this), new WarriorAttacks.OBLITERATE(this));
	}
}