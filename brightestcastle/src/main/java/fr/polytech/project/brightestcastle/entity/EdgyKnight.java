package fr.polytech.project.brightestcastle.entity;

import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.entity.attack.EdgyKnightAttacks;

public class EdgyKnight extends Monster {
	public EdgyKnight() {
		super("DARK_WARRIOR", (byte) 4, (byte) 4);
	}
	
	@Override
	public int getATK(){
		if (getHP()>(getHPmax()/2)) {
			return super.getATK();
		} else {
			return super.getATK()+2;
		}
	}
	
	@Override
	public int getDEF(){
		if (getHP()>(getHPmax()/2)) {
			return super.getDEF()+2;
		} else {
			return super.getDEF();
		}
	}
	
	@Override
	public MonsterType getType() {
		return MonsterType.DARK_WARRIOR;
	}
	
	public Attack[] getAttacks() {
		return new Attack[] {
				new EdgyKnightAttacks.FIRST(this),
				new EdgyKnightAttacks.SECOND(this),
				new EdgyKnightAttacks.THIRD(this),
				new EdgyKnightAttacks.FOURTH(this)
		};
	}
}