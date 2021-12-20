package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;

public abstract class EdgyKnightAttacks {
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
			targets[0].takeDamageBlinded(getSender().getATK());
		}
	}
	
	public static class THIRD extends Attack {
		public THIRD(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {}
	}
	
	public static class FOURTH extends Attack {
		public FOURTH(Entity sender) {
			super(sender, "", "");
		}
		
		public void attack(Entity[] targets) {
			targets[0].takeDamage(3 * getSender().getATK());
		}
	}
}
