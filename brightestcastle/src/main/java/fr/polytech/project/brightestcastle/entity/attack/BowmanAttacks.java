package fr.polytech.project.brightestcastle.entity.attack;

import java.util.concurrent.ThreadLocalRandom;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.Entity;
import fr.polytech.project.brightestcastle.entity.StatusEnum;
import fr.polytech.project.brightestcastle.gameplay.Battle;

public abstract class BowmanAttacks {
	public static class SHOT extends Attack<Character> {
		public SHOT(Character sender) {
			super(sender, "SHOT", "Regular ranged attack that deal 100% to a selected rear row");
		}

		@Override
		public void attack(Battle battle, Entity target) {
			int threat = target.takeDamageBlinded(getSender().getATK());
			getSender().addThreat(threat);
		}

		@Override
		public boolean[] getTargetables() {
			return new boolean[] { false, true, true, true };
		}
	}
	
	public static class STAB extends Attack<Character> {
		public STAB(Character sender) {
			super(sender, "STAB", "Melee attack, deals only 75% damages to the front row, but double stamina regeneration for this turn");
		}
		
		@Override
		public void attack(Battle battle, Entity target) {
			if (battle.getMonsters().size() >= 1) {
				int threat = battle.getMonsters().get(0).entity().takeDamageBlinded((int) (getSender().getATK()*0.75));
				getSender().generateSTA();
				getSender().addThreat(threat);
			}
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
	
	public static class ARROW extends Attack<Character> {
		public ARROW(Character sender) {
			super(sender, "MAGIC ARROW", "Send guided arrows at targets at random for 150% send one more arrow every 3 stamina points. Ignore Armor and dodge");
		}
		
		@Override
		public void attack(Battle battle, Entity target) {
			if (getSender().getSTA() >= 3) {
				int threat = 0;
				int nbArrows = (int) Math.floor(getSender().getSTA()/3);
				for(int i=0; i<nbArrows; i++) {
					int randomNum = ThreadLocalRandom.current().nextInt(0, battle.getMonsters().size());
					threat += battle.getMonsters().get(randomNum).entity().takeTrueDamage((int) (getSender().getATK()*1.5));	
				}
				getSender().addThreat(threat);
				getSender().setSTA(getSender().getSTA() - nbArrows*3);
			} else System.out.println("Not enough Stamina !");
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
	
	public static class PIERCE extends Attack<Character> {
		public PIERCE(Character sender) {
			super(sender, "PIERCE", "Completely pierce through any foe for 250% damage, also reduces it's defense by 25% for 3 turns, cost 5 stamina.");
		}
		
		@Override
		public void attack(Battle battle, Entity target) {
			if (getSender().getSTA() >= 5) {
				int threat = target.takeDamageBlinded((int)(getSender().getATK() * 2.5));
				target.addStatus(StatusEnum.DEFDOWN, 3);
				getSender().setSTA(getSender().getSTA() - 5);
				getSender().addThreat(threat);
			} else System.out.println("Not enough Stamina!");
		}

		@Override
		public boolean[] getTargetables() {
			return new boolean[] { true, true, true, true };
		}
	}
}
