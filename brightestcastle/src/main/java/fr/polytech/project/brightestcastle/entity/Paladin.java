package fr.polytech.project.brightestcastle.entity;

import java.util.Arrays;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.PaladinAttacks;

public class Paladin extends Character {
	// passive : Taunt => greater threat, threat generation improved by 50%

	public Paladin(String name, byte STR, byte CON, byte VIG) {
		super(name, STR, (byte) (CON + 2), VIG);
		super.setThreat(25);
	}

	@Override
	public void addThreat(int threat) {
		super.addThreat((int) (threat + threat * 0.5));
	}

	@Override
	public CharacterJob getJob() {
		return CharacterJob.BOWMAN;
	}

	public List<Attack<Character>> getAttacks() {
		return Arrays.asList(new PaladinAttacks.SLASH(this), new PaladinAttacks.RALLY(this),
				new PaladinAttacks.PRAYER(this), new PaladinAttacks.PURGE(this));
	}
}