package fr.polytech.project.brightestcastle.character;

public class Warrior extends Character {
	// passive= Rage -> attk+ when hp low

	public Warrior(String name, CharacterClass job, byte constitution, byte strength, byte vigor) {
		
		super(name, job, constitution, (byte) (strength+2), vigor);
		
	}
	
	@Override
	public int getATK(){
		int ATK;
		ATK= ((getHPMax() - getHP()) / getHPMax()) * getATK() + getATK() ;
		return ATK;
		
	}

}
