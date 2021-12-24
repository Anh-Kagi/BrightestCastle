package fr.polytech.project.brightestcastle.entity;

import java.util.Arrays;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.WizardAttacks;

public class Wizard extends Monster {
	public Wizard() {
		super("Wizard", (byte) 7, (byte) 5);
	}

	@Override
	public MonsterType getType() {
		return MonsterType.WIZARD;
	}

	@Override
	public List<Attack<Monster>> getAttacks() {
		return Arrays.asList(new WizardAttacks.ZAP(this), new WizardAttacks.HEAL(this),
				new WizardAttacks.FIREBALL(this), new WizardAttacks.SMACK(this));
	}
}