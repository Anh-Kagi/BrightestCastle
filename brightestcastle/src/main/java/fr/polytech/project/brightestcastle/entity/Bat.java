package fr.polytech.project.brightestcastle.entity;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.BatAttacks;

public class Bat extends Monster {
	public Bat() {
		super("Bat", (byte) 3, (byte) 1);
	}
	
	@Override
	public int takeDamage(int damage) {
		if ( Math.random()*100 <=15) {
			System.out.println("The bat Dodged !");
			return 0;
		} else {
			return super.takeDamage(damage);
		}
	}
	
	@Override
	public MonsterType getType() {
		return MonsterType.BAT;
	}
	
	public Attack[] getAttacks() {
		return new Attack[] {
				new BatAttacks.FIRST(this),
				new BatAttacks.SECOND(this)
		};
	}
}
