package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.Entity;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.entity.StatusEnum;
import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Played;

public abstract class PaladinAttacks {
	public static class SLASH extends Attack<Character> {
		public SLASH(Character sender) {
			super(sender, "SLASH", "Slice the target with a sword for 100% damage");
		}
		
		public void attack(Battle battle, Entity target) {
			int threat = target.takeDamageBlinded(getSender().getATK());
			getSender().addThreat(threat);
		}

		@Override
		public boolean[] getTargetables() {
			return new boolean[] { true, true, false, false };
		}
	}
	
	public static class RALLY extends Attack<Character> {
		public RALLY(Character sender) {
			super(sender, "RALLY", "Inspire the allies in order to boost their defense by 25% for 3 turns");
		}
		
		public void attack(Battle battle, Entity target) {
			for (Played<Character> c : battle.getCharacters())
				c.entity().addStatus(StatusEnum.DEFUP, 3);
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
	
	public static class PRAYER extends Attack<Character> {
		public PRAYER(Character sender) {
			super(sender, "PRAYER", "The paladin heal himself using is constitution instead of ATK, cost 3 stamina");
		}
		
		public void attack(Battle battle, Entity target) {
			if (getSender().getSTA() >= 3) {
				getSender().setHP(getSender().getHP() + getSender().getCON()*2);
				getSender().setSTA(getSender().getSTA() - 3);
				getSender().addThreat(getSender().getCON()*2);
			} else System.out.println("Not enough Stamina !");
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
	
	public static class PURGE extends Attack<Character> {
		public PURGE(Character sender) {
			super(sender, "PURGE", "Cleanse the world from evil with light dealing 300% damages to all foes, cost 10 stamina");
		}
		
		public void attack(Battle battle, Entity target) {
			if (getSender().getSTA() >= 10) {
				int threat=0;
				for (Played<Monster> m : battle.getMonsters())
					threat += m.entity().takeDamage(3 * getSender().getATK());
				getSender().addThreat(threat);
				getSender().setSTA(getSender().getSTA() - 10);
			} else System.out.println("Not enough Stamina !");
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
}
