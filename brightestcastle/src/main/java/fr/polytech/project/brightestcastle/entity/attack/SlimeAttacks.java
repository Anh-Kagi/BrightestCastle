package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.entity.StatusEnum;
import fr.polytech.project.brightestcastle.gameplay.Battle;

public abstract class SlimeAttacks {
	public static class BASH extends Attack<Monster> {
		public BASH(Monster sender) {
			super(sender, "BASH", "Deals 100% damage to the target in the front row.");
		}
		
		public void attack(Battle battle, Entity target) {
			if (battle.getMonsters().size() >= 1)
				battle.getMonsters().get(0).entity().takeDamageBlinded(getSender().getATK());
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
	
	public static class SLAM extends Attack<Monster> {
		public SLAM(Monster sender) {
			super(sender, "SLAM	", "Deals 100% damage to the two front row, 25% chance to cause stun.");
		}
		
		public void attack(Battle battle, Entity target) {
			if (battle.getMonsters().size() >= 2) {
				battle.getMonsters().get(1).entity().takeDamage((int) getSender().getATK());
				if(Math.random()*100<=25) battle.getMonsters().get(1).entity().addStatus(StatusEnum.STUNNED, 1);
			}
			if (battle.getMonsters().size() >= 1) {
				battle.getMonsters().get(0).entity().takeDamage((int) getSender().getATK());
				if(Math.random()*100<=25) battle.getMonsters().get(1).entity().addStatus(StatusEnum.STUNNED, 1);
			}
		}

		@Override
		public boolean[] getTargetables() {
			return null;
		}
	}
}
