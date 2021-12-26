package fr.polytech.project.brightestcastle.entity;

import java.util.Arrays;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.EdgyKnightAttacks;

public class EdgyKnight extends Monster {
	private boolean health_trigger = false;
	public EdgyKnight() {
		super((byte) 4, (byte) 4);
	}

	@Override
	public int getATK() {
		if (getHP() > (getHPmax() / 2)) {
			return super.getATK();
		} else {
			return super.getATK() + 2;
		}
	}

	@Override
	public int getDEF() {
		if (getHP() > (getHPmax() / 2)) {
			return super.getDEF() + 2;
		} else {
			return super.getDEF();
		}
	}
	
	@Override
	public int takeTrueDamage(int damage) {
		damage = Math.max(0, damage);
		if (getHP() > getHPmax()/2 && getHP() - damage <= getHPmax()/2) // reached 50% HP
			health_trigger = true;
		if (getHP() > getHPmax()/4 && getHP() - damage <= getHPmax()/4) // reaches 25% HP
			health_trigger = true;
		
		return super.takeTrueDamage(damage);
	}

	@Override
	public MonsterType getType() {
		return MonsterType.DARK_WARRIOR;
	}

	public List<Attack<Monster>> getAttacks() {
		return Arrays.asList(new EdgyKnightAttacks.SLASH(this), new EdgyKnightAttacks.CUT(this),
				new EdgyKnightAttacks.CURSE(this), new EdgyKnightAttacks.CONDEMNATION(this));
	}

	@Override
	public Attack<Monster> selectAttack(int turn) {
		if (health_trigger) {
			health_trigger = false;
			return getAttacks().get(2); // CURSE if health reaches 50% or 25%
		} else
			return turn % 5 == 0 ? getAttacks().get(3) : (turn % 3 == 0 ? getAttacks().get(1) : getAttacks().get(0)); // CONDEMNETION every 5 turns, else CUT every 3 turns, SLASH otherwise
	}
}