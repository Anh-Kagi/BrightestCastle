package fr.polytech.project.brightestcastle.character;

import java.lang.Math;

public class Bowman extends Character implements Attack{
	// passive: Swift: 15% chance to dodge all damage

	public Bowman(String name, CharacterClass job, byte constitution, byte strength, byte vigor) {
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

	@Override
	public void attack1(Character target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack2(Character target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack3(Character target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack4(Character target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAtk1Name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAtk2Name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAtk3Name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAtk4Name() {
		// TODO Auto-generated method stub
		return null;
	}
}
