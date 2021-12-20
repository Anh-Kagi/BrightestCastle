package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;

public abstract class WizardAttacks {
	public static class FIRST extends Attack {
		public FIRST(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {
			targets[0].takeTrueDamage(getSender().getATK());
		}
	}
	
	public static class SECOND extends Attack {
		public SECOND(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {
			targets[0].takeTrueDamage((int)(2.5 * getSender().getATK()));
		}
	}
	
	public static class THIRD extends Attack {
		public THIRD(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {
			for (int i=0;i<4;i++) {
				targets[i].takeDamage(3 * getSender().getATK());
			}
		}
	}
}
