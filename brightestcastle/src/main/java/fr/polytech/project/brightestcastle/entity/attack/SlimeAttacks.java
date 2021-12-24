package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;

public abstract class SlimeAttacks {
	public static class FIRST extends Attack {
		public FIRST(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {
			targets[0].takeDamageBlinded(getSender().getATK());
		}
	}
	
	public static class SECOND extends Attack {
		public SECOND(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {
			targets[0].takeDamage((int) (getSender().getATK()));
			targets[1].takeDamage((int) (getSender().getATK()));
		}
	}
}
