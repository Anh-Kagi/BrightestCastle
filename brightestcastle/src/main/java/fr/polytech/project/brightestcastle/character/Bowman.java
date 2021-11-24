package fr.polytech.project.brightestcastle.character;

import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

public class Bowman extends Character implements Attack{
	// passive: Swift: 15% chance to dodge all damage

	public Bowman(String name, CharacterClass job, byte constitution, byte strength, byte vigor) {
		super(name, job, constitution, strength, (byte) (vigor+2));
		// TODO Auto-generated constructor stub
	}

	@Override
	public int takeDamage(int damage) {
		if ( Math.random()*100 <=15) {
			System.out.println("Dodged !");
			return 0;
		}
		else {
			
			int damageInflicted=damage-getDEFTemp();
			setHP(getHP()-damageInflicted);
			return damageInflicted;
		}
	}

	@Override
	public void attack1(Character[] target) {
		int threat=target[0].takeDamageBlinded(getATKTemp());
		addThreat(threat);
	}

	@Override
	public void attack2(Character[] target) {
		int threat=target[0].takeDamageBlinded((int) (getATKTemp()*0.5));
		generateSTA(getVigorTemp());
		addThreat(threat);
	}

	@Override
	public void attack3(Character target[]) {
		if (getSTA()>=3) {
			int threat=0;
			int nbArrows=(int) Math.floor(getSTA()/3);
			for(int i=0;i<nbArrows;i++) {
				int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
				threat+=target[randomNum].takeTrueDamage(getATKTemp());	
			}
			addThreat(threat);
			setSTA(getSTA()-nbArrows*3);
		} else System.out.println("Not enough Stamina !");
		
	}

	@Override
	public void attack4(Character[] target) {
		if (getSTA()>=5) {
			int threat =target[0].takeDamageBlinded(getATKTemp()*2);
			//TODO add debuf
			setSTA(getSTA()-5);
			addThreat(threat);
		} else System.out.println("Not enough Stamina!");
		
	}

	@Override
	public String getAtk1Name() {
		// TODO Auto-generated method stub
		return "SHOT";
	}

	@Override
	public String getAtk2Name() {
		// TODO Auto-generated method stub
		return "STAB";
	}

	@Override
	public String getAtk3Name() {
		// TODO Auto-generated method stub
		return "MAGIC ARROW";
	}

	@Override
	public String getAtk4Name() {
		// TODO Auto-generated method stub
		return "PIERCE";
	}

	@Override
	public String getAtk1Desc() {
		// TODO Auto-generated method stub
		return "Regular ranged attack that deal 100% to a selected rear row";
	}

	@Override
	public String getAtk2Desc() {
		// TODO Auto-generated method stub
		return "Melee attack, deals only 50% damages to the front row, but double stamina regeneration for this turn";
	}

	@Override
	public String getAtk3Desc() {
		// TODO Auto-generated method stub
		return "Send guided arrows at targets at random for 100% send one more arrow every 3 stamina points. Ignore Armor and dodge";
	}

	@Override
	public String getAtk4Desc() {
		// TODO Auto-generated method stub
		return "Completely pierce through any foe for 200% damage, also reduces it's defense by 25% for 3 turns, cost 5 stamina.";
	}
}