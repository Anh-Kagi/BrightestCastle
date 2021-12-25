package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.Entity;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.entity.StatusEnum;
import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Played;

public abstract class WizardAttacks {
	public static class ZAP extends Attack<Monster> {
		public ZAP(Monster sender) {
			super(sender, "ZAP", "Deals 100% damage to the target.");
		}

		public void attack(Battle battle, Entity target) {
			target.takeTrueDamage(getSender().getATK());
		}

		@Override
		public boolean[] getTargetables() {
			return new boolean[] { true, true, true, true };
		}
	}

	public static class HEAL extends Attack<Monster> {
		public HEAL(Monster sender) {
			super(sender, "HEAL", "Heals all allies using the wizard's constitution.");
		}

		public void attack(Battle battle, Entity target) {
			for (Played<Monster> m : battle.getMonsters())
				m.entity().setHP(m.entity().getHP() + m.entity().getCON());
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}

	public static class FIREBALL extends Attack<Monster> {
		public FIREBALL(Monster sender) {
			super(sender, "FIREBALL", "Deals 250% damage to the target as well as bleed.");
		}

		public void attack(Battle battle, Entity target) {
			target.takeDamage((int) 2.5 * getSender().getATK());
			target.addStatus(StatusEnum.POISONNED, 2);
		}

		@Override
		public boolean[] getTargetables() {
			return new boolean[] { true, true, false, false };
		}
	}

	public static class SMACK extends Attack<Monster> {
		public SMACK(Monster sender) {
			super(sender, "NAMZAR'S SMACK", "Deals 300% damage to all foes.");
		}

		public void attack(Battle battle, Entity target) {
			for (Played<Character> c : battle.getCharacters())
				c.entity().takeDamage(3 * getSender().getATK());
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
}
