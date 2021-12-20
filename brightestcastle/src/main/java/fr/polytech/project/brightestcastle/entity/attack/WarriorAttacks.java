package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;

public abstract class WarriorAttacks {
	public static class BASH extends Attack {
		public BASH(Entity sender) {
			super(sender, "BASH", "Deals 100% to the target in the front row");
		}
		
		public void attack(Entity[] targets) {
			int threat = targets[0].takeDamageBlinded(getSender().getATK());
			getSender().addThreat(threat);
		}
	}
	
	public static class SWING extends Attack {
		public SWING(Entity sender) {
			super(sender, "SWING", "A circular attack, target the two front rows for 150% of damages, \nHave a 25% chance to apply stun, cost 3 stamina");
		}
		
		public void attack(Entity[] targets) {
			// TODO Add stun effect
			int threat=0;
			if (getSender().getSTA() >= 3) {
				threat += targets[0].takeDamageBlinded((int) (1.5*getSender().getATK()));
				threat += targets[1].takeDamageBlinded((int) (1.5*getSender().getATK()));
				getSender().addThreat(threat);
				getSender().setSTA(getSender().getSTA()-3);
			} else System.out.println("Not enough stamina!");
		}
	}
	
	public static class INTIMIDATE extends Attack {
		public INTIMIDATE(Entity sender) {
			super(sender, "INTIMIDATE", "Shout a war cry that affects every foes, diminishes their ATK by 25% for 2 turns");
		}
		
		public void attack(Entity[] targets) {}
	}
	
	public static class OBLITERATE extends Attack {
		public OBLITERATE(Entity sender) {
			super(sender, "OBLITERATE", "Completely crush the foe on the front row for 500% damage with a 100% chance stun. Only at max stamina, deleat it completely afterward.");
		}
		
		public void attack(Entity[] targets) {
			if(getSender().getSTA() == 20) {
				int threat = targets[0].takeDamageBlinded(getSender().getATK()*5);
				getSender().setSTA(0);
				getSender().addThreat(threat);
				//TODO add stun effect
			} else System.out.println("Not enough Stamina!");
		}
	}
}
