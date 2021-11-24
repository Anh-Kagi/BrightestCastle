package fr.polytech.project.brightestcastle.character;


public class Slime extends Monster implements Attack {
	
	MonsterType type;

	public Slime() {
		super("Slime",(byte) 2,(byte) 2, MonsterType.SLIME);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void attack1(Character target[]) {
		target[0].takeDamageBlinded(getATK());
		
	}

	@Override
	public void attack2(Character target[]) {
		// TODO Add stun effect	
		target[0].takeDamage((int) (getATK()));
		target[1].takeDamage((int) (getATK()));
	}


	@Override
	public void attack3(Character[] targets) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void attack4(Character[] targets) {
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


	@Override
	public String getAtk1Desc() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getAtk2Desc() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getAtk3Desc() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getAtk4Desc() {
		// TODO Auto-generated method stub
		return null;
	}
}