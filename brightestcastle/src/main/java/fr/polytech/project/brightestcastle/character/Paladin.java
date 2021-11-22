package fr.polytech.project.brightestcastle.character;

public class Paladin extends Character implements Attack {
	//passive : Taunt => greater threat, threat generation improved by 25%

	
	public Paladin(String name, CharacterClass job, byte constitution, byte strength, byte vigor) {
		super(name, job, (byte) (constitution+2), strength, vigor);
		super.setThreat(25);
	}

	@Override
	public void setThreat(int threat) {
		super.setThreat((int) (threat+ threat*0.25));
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
