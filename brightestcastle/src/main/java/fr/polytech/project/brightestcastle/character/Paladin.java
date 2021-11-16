package fr.polytech.project.brightestcastle.character;

public class Paladin extends Character {
	//passive : Taunt => greater threat, threat generation improved by 25%

	
	public Paladin(String name, CharacterClass job, byte constitution, byte strength, byte vigor) {
		super(name, job, (byte) (constitution+2), strength, vigor);
		setThreat(25);
	}

	@Override
	public void setThreat(int threat) {
		super.setThreat((int) (threat+ threat*0.25));
	}
}
