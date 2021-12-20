package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;

public abstract class BatAttacks {
	public static class FIRST extends Attack {
		public FIRST(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {
			getSender().setHP(getSender().getHP()+(int)(0.5*targets[0].takeDamageBlinded(getSender().getATK())));
		}
	}
	
	public static class SECOND extends Attack {
		public SECOND(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {
			for (int i=0;i<4;i++) {
				targets[i].takeDamage((int)(0.75 * getSender().getATK()));
			}
		}
	}
}
