package fr.polytech.project.brightestcastle.character;

public class EdgyKnight extends Monster implements Attack{

	public EdgyKnight() {
		super("DARK_WARRIOR", (byte) 4, (byte) 4, MonsterType.DARK_WARRIOR);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getATKTemp(){
		if (getHP()>(getHPMax()/2)) {
			return super.getATKTemp();
		}
		else {
			return super.getATKTemp()+2;
		}
	}
	
	@Override
	public int getDEFTemp(){
		if (getHP()>(getHPMax()/2)) {
			return super.getDEFTemp()+2;
		}
		else {
			return super.getDEFTemp();
		}
	}

	@Override
	public void attack1(Character[] targets) {
		targets[0].takeDamageBlinded(getATKTemp());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack2(Character[] targets) {
		targets[0].takeDamageBlinded(getATKTemp());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack3(Character[] targets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack4(Character[] targets) {
		targets[0].takeDamage(3*getATK());
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
