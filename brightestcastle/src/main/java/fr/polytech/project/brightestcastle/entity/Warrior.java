package fr.polytech.project.brightestcastle.entity;

import java.util.Arrays;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.WarriorAttacks;

public class Warrior extends Character {
	// passive = Rage -> attk + when hp low

	public Warrior(String name, byte STR, byte CON, byte VIG) {
		super(name, (byte) (STR + 2), CON, VIG);
	}

	@Override
	public int getATK() {
		int ATK;
		ATK = ((getHPmax() - getHP()) / getHPmax()) * super.getATK() + super.getATK();
		return ATK;
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