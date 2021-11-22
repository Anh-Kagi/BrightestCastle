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
		target.takeDamage(getATK());
		
	}

	@Override
	public void attack2(Character target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack3(Character target) {
		if (getSTA()>=3) {
			setHP(getConstitution());
		}
	}

	@Override
	public void attack4(Character target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAtk1Name() {
		// TODO Auto-generated method stub
		return "SLASH";
	}

	@Override
	public String getAtk2Name() {
		// TODO Auto-generated method stub
		return "RALLY";
	}

	@Override
	public String getAtk3Name() {
		// TODO Auto-generated method stub
		return "PRAYER";
	}

	@Override
	public String getAtk4Name() {
		// TODO Auto-generated method stub
		return "PURGE";
	}

	@Override
	public String getAtk1Desc() {
		// TODO Auto-generated method stub
		return "Slice the target with a sword for 100% damage";
	}

	@Override
	public String getAtk2Desc() {
		// TODO Auto-generated method stub
		return "Inspire the allies in order to boost their defense by 25% for 2 turns";
	}

	@Override
	public String getAtk3Desc() {
		// TODO Auto-generated method stub
		return "The paladin heal himself using is constitution instead of ATK, cost 3 stamina";
	}

	@Override
	public String getAtk4Desc() {
		// TODO Auto-generated method stub
		return "Cleanse the world from evil with light dealing 300% damages to all foes, cost 10 stamina";
	}
}
