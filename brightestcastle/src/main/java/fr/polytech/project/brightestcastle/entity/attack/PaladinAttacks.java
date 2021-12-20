package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;

public abstract class PaladinAttacks {
	public static class SLASH extends Attack {
		public SLASH(Entity sender) {
			super(sender, "SLASH", "Slice the target with a sword for 100% damage");
		}
		
		public void attack(Entity[] targets) {
			int threat = targets[0].takeDamageBlinded(getSender().getATK());
			getSender().addThreat(threat);
		}
	}
	
	public static class RALLY extends Attack {
		public RALLY(Entity sender) {
			super(sender, "RALLY", "Inspire the allies in order to boost their defense by 25% for 2 turns");
		}
		
		public void attack(Entity[] targets) {}
	}
	
	public static class PRAYER extends Attack {
		public PRAYER(Entity sender) {
			super(sender, "PRAYER", "The paladin heal himself using is constitution instead of ATK, cost 3 stamina");
		}
		
		public void attack(Entity[] targets) {
			if (getSender().getSTA() >= 3) {
				getSender().setHP(getSender().getHP() + getSender().getCON());
				getSender().setSTA(getSender().getSTA() - 3);
				getSender().addThreat(getSender().getCON());
			} else System.out.println("Not enough Stamina !");
		}
	}
	
	public static class PURGE extends Attack {
		public PURGE(Entity sender) {
			super(sender, "PURGE", "Cleanse the world from evil with light dealing 300% damages to all foes, cost 10 stamina");
		}
		
		public void attack(Entity[] targets) {
			if (getSender().getSTA() >= 10) {
				int threat=0;
				for (int i=0; i<4; i++) {
					threat += targets[i].takeDamage(3*getSender().getATK());
				}
				getSender().addThreat(threat);
				getSender().setSTA(getSender().getSTA() - 10);
			} else System.out.println("Not enough Stamina !");
		}
	}
}
