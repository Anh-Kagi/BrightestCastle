package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;
import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Played;

public abstract class BatAttacks {
	public static class BITE extends Attack<Monster> {
		public BITE(Monster sender) {
			super(sender, "BITE", "Deals 100% damage to the target, steal 50% of the damage as a heal.");
		}
		
		public void attack(Battle battle, Entity target) {
			getSender().setHP(getSender().getHP()+(int)(0.5*target.takeDamageBlinded(getSender().getATK())));
		}

		@Override
		public boolean[] getTargetables() {
			return new boolean[] { true, true, false, false };
		}
	}
	
	public static class BLAST extends Attack<Monster> {
		public BLAST(Monster sender) {
			super(sender, "WIND BLAST", "Deals 75% damages to all the foes.");
		}
		
		public void attack(Battle battle, Entity target) {
			for (Played<Character> c : battle.getCharacters())
				c.entity().takeDamage((int)(0.75 * getSender().getATK()));
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
}
