package fr.polytech.project.brightestcastle.entity;

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
	public Attack[] getAttacks() {
		return new Attack[] {
				new WizardAttacks.FIRST(this),
				new WizardAttacks.SECOND(this),
				new WizardAttacks.THIRD(this)
		};
	}
}