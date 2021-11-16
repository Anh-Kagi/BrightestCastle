package classSystem;

import java.lang.Math;

public class BOWMAN extends Character {
	// passive: Swift: 15% chance to dodge all damage

	public BOWMAN(String name, CharacterClass job, byte constitution, byte strength, byte vigor) {
		super(name, job, constitution, strength, (byte) (vigor+2));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeDamage(int damage) {
		if ( Math.random()*100 <=15) {
			System.out.println("Dodged !");
		}
		else {
			setHP(getHP()-(damage-getDEF()));
		}
	}
}
