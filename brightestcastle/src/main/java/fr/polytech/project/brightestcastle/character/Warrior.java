package fr.polytech.project.brightestcastle.character;


public class Warrior extends Character implements Attack {
	// passive= Rage -> attk+ when hp low

	public Warrior(String name, CharacterClass job, byte constitution, byte strength, byte vigor) {
		
		super(name, job, constitution, (byte) (strength+2), vigor);
		
	}
	
	@Override
	public int getATK(){
		int ATK;

		ATK= ((getHPMax() - getHP()) / getHPMax()) * super.getATK() + super.getATK() ;

		return ATK;
		
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
