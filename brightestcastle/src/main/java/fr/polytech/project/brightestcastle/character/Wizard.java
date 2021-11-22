package fr.polytech.project.brightestcastle.character;

public class Wizard extends Monster implements Attack {

	public Wizard() {
		super("Wizard", (byte) 5, (byte) 7, MonsterType.WIZARD);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack1(Character[] targets) {
		targets[0].takeTrueDamage(getATK());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack2(Character[] targets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack3(Character[] targets) {
		targets[0].takeTrueDamage((int)(2.5*getATK()));
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack4(Character[] targets) {
		for (int i=0;i<4;i++) {
			targets[i].takeDamage(3*getATK());
		}
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
