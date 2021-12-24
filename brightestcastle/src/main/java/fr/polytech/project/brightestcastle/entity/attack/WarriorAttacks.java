package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.Entity;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.entity.StatusEnum;
import fr.polytech.project.brightestcastle.gameplay.Battle;
import fr.polytech.project.brightestcastle.gameplay.Played;

public abstract class WarriorAttacks {
	public static class BASH extends Attack<Character> {
		public BASH(Character sender) {
			super(sender, "BASH", "Deals 100% to the target in the front row");
		}
		
		@Override
		public void attack(Battle battle, Entity targets) {
			if (battle.getMonsters().size() >= 1 ) {
				int threat = battle.getMonsters().get(0).entity().takeDamageBlinded(getSender().getATK());
				getSender().addThreat(threat);
			}
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
	
	public static class SWING extends Attack<Character> {
		public SWING(Character sender) {
			super(sender, "SWING", "A circular attack, target the two front rows for 150% of damages, \nHave a 25% chance to apply stun, cost 3 stamina");
		}
		
		@Override
		public void attack(Battle battle, Entity target) {
			if (getSender().getSTA() >= 3) {
				boolean stun = Math.random() < 0.25;
				for (int i=0; i<Math.min(2, battle.getMonsters().size()); i++) {
					if (stun)
						battle.getMonsters().get(i).entity().addStatus(StatusEnum.STUNNED, 1);
					int threat = battle.getMonsters().get(i).entity().takeDamageBlinded((int) (1.5*getSender().getATK()));
					getSender().addThreat(threat);
				}
				getSender().setSTA(getSender().getSTA()-3);
			} else
				System.out.println("Not enough stamina!");
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
	
	public static class INTIMIDATE extends Attack<Character> {
		public INTIMIDATE(Character sender) {
			super(sender, "INTIMIDATE", "Shout a war cry that affects every foes, diminishes their ATK by 25% for 2 turns");
		}
		
		public void attack(Battle battle, Entity target) {
			for (Played<Monster> m : battle.getMonsters())
				m.entity().addStatus(StatusEnum.STUNNED, 2);
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
	
	public static class OBLITERATE extends Attack<Character> {
		public OBLITERATE(Character sender) {
			super(sender, "OBLITERATE", "Completely crush the foe on the front row for 500% damage with a 100% chance stun. Only at max stamina, deleat it completely afterward.");
		}
		
		public void attack(Battle battle, Entity target) {
			if(getSender().getSTA() == 20) {
				if (battle.getMonsters().size() >= 1) {
					Monster opponent = battle.getMonsters().get(0).entity();
					int threat = opponent.takeDamageBlinded(getSender().getATK()*5);
					getSender().setSTA(0);
					getSender().addThreat(threat);
					opponent.addStatus(StatusEnum.STUNNED, 1);
				}
			} else System.out.println("Not enough Stamina!");
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
}
