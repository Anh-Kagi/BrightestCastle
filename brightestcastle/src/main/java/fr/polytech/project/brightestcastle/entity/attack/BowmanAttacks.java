package fr.polytech.project.brightestcastle.entity.attack;

import java.util.concurrent.ThreadLocalRandom;

import fr.polytech.project.brightestcastle.entity.Entity;

public abstract class BowmanAttacks {
	public static class SHOT extends Attack {
		public SHOT(Entity sender) {
			super(sender, "SHOT", "Regular ranged attack that deal 100% to a selected rear row");
		}

		@Override
		public void attack(Entity[] targets) {
			int threat = targets[0].takeDamageBlinded(getSender().getATK());
			getSender().addThreat(threat);
		}
	}
	
	public static class STAB extends Attack {
		public STAB(Entity sender) {
			super(sender, "STAB", "Melee attack, deals only 50% damages to the front row, but double stamina regeneration for this turn");
		}
		
		@Override
		public void attack(Entity[] targets) {
			int threat = targets[0].takeDamageBlinded((int) (getSender().getATK()*0.5));
			getSender().generateSTA(getSender().getVIG());
			getSender().addThreat(threat);
		}
	}
	
	public static class ARROW extends Attack {
		public ARROW(Entity sender) {
			super(sender, "MAGIC ARROW", "Send guided arrows at targets at random for 100% send one more arrow every 3 stamina points. Ignore Armor and dodge");
		}
		
		@Override
		public void attack(Entity[] targets) {
			if (getSender().getSTA() >= 3) {
				int threat = 0;
				int nbArrows = (int) Math.floor(getSender().getSTA()/3);
				for(int i=0; i<nbArrows; i++) {
					int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
					threat += targets[randomNum].takeTrueDamage(getSender().getATK());	
				}
				getSender().addThreat(threat);
				getSender().setSTA(getSender().getSTA() - nbArrows*3);
			} else System.out.println("Not enough Stamina !");
		}
	}
	
	public static class PIERCE extends Attack {
		public PIERCE(Entity sender) {
			super(sender, "PIERCE", "Completely pierce through any foe for 200% damage, also reduces it's defense by 25% for 3 turns, cost 5 stamina.");
		}
		
		@Override
		public void attack(Entity[] targets) {
			if (getSender().getSTA()>=5) {
				int threat = targets[0].takeDamageBlinded(getSender().getATK()*2);
				//TODO add debuf
				getSender().setSTA(getSender().getSTA()-5);
				getSender().addThreat(threat);
			} else System.out.println("Not enough Stamina!");
		}
	}
}
