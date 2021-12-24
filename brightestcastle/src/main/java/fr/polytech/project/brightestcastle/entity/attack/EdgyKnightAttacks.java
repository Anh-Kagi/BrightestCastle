package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;
import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.entity.StatusEnum;
import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Played;

public abstract class EdgyKnightAttacks {
	public static class SLASH extends Attack<Monster> {
		public SLASH(Monster sender) {
			super(sender, "SLASH", "Deals 100% damage to the target");
		}

		public void attack(Battle battle, Entity target) {
			target.takeDamageBlinded(getSender().getATK());
		}

		@Override
		public boolean[] getTargetables() {
			return new boolean[] { true, true, false, false };
		}
	}

	public static class CUT extends Attack<Monster> {
		public CUT(Monster sender) {
			super(sender, "DEEP CUT", "Deals 100% damage to the target, inflict bleed.");
		}

		public void attack(Battle battle, Entity target) {
			target.takeDamageBlinded(getSender().getATK());
		}

		@Override
		public boolean[] getTargetables() {
			return new boolean[] { true, true, false, false };
		}
	}

	public static class CURSE extends Attack<Monster> {
		public CURSE(Monster sender) {
			super(sender, "CURSE", "Reduce all the foes attack and defense.");
		}

		public void attack(Battle battle, Entity target) {
			for (Played<Character> c : battle.getCharacters()) {
				c.entity().addStatus(StatusEnum.ATKDOWN, 2);
				c.entity().addStatus(StatusEnum.DEFDOWN, 2);
			}
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}

	public static class CONDEMNATION extends Attack<Monster> {
		public CONDEMNATION(Monster sender) {
			super(sender, "CONDEMNATION", "Deals 300% damage to the person in the front row, inflict bleed.");
		}

		public void attack(Battle battle, Entity target) {
			if (battle.getCharacters().size() >= 1)
				battle.getCharacters().get(0).entity().takeDamage(3 * getSender().getATK());
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
}
