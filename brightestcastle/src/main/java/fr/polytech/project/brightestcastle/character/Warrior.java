package fr.polytech.project.brightestcastle.character;

public class Warrior extends Character implements Attack {
	// passive= Rage -> attk+ when hp low

	public Warrior(String name, CharacterClass job, byte constitution, byte strength, byte vigor) {
		
		super(name, job, constitution, (byte) (strength+2), vigor);
		
	}
	
	@Override
	public int getATKTemp(){
		int ATK;

		ATK= ((getHPMax() - getHP()) / getHPMax()) * super.getATKTemp() + super.getATKTemp() ;

		return ATK;
		
	}

	@Override
	public void attack1(Character target[]) {
		int threat = target[0].takeDamageBlinded(getATKTemp());
		addThreat(threat);
	}

	@Override
	public void attack2(Character target[]) {
		
		int threat=0;
		if (getSTA()>=3) {
			if(Math.random()*100 <=25) {
				target[0].addStatus(StatusEnum.STUNNED, 1);
				target[1].addStatus(StatusEnum.STUNNED, 1);
			}
			threat+=target[0].takeDamageBlinded((int) (1.5*getATKTemp()));
			threat+=target[1].takeDamageBlinded((int) (1.5*getATKTemp()));
			addThreat(threat);
			setSTA(getSTA()-3);
		}else System.out.println("Not enough stamina!");
		
	}

	@Override
	public void attack3(Character target[]) {
		for(int i=0;i<target.length;i++) {
			target[i].addStatus(StatusEnum.ATKDOWN, 2);
		}
	}

	@Override
	public void attack4(Character target[]) {
		if(getSTA()==20) {
			int threat=target[0].takeDamageBlinded(getATKTemp()*5);
			setSTA(0);
			addThreat(threat);
			target[0].addStatus(StatusEnum.STUNNED, 1);
		
		}
		else System.out.println("Not enough Stamina!");
		
	}

	@Override
	public String getAtk1Name() {
		// TODO Auto-generated method stub
		return "BASH";
	}

	@Override
	public String getAtk2Name() {
		// TODO Auto-generated method stub
		return "SWING";
	}

	@Override
	public String getAtk3Name() {
		// TODO Auto-generated method stub
		return "INTIMIDATE";
	}

	@Override
	public String getAtk4Name() {
		// TODO Auto-generated method stub
		return "OBLITERATE";
	}

	@Override
	public String getAtk1Desc() {
		// TODO Auto-generated method stub
		return "Deals 100% to the target in the front row";
	}

	@Override
	public String getAtk2Desc() {
		// TODO Auto-generated method stub
		return "A circular attack, target the two front rows for 150% of damages, \nHave a 25% chance to apply stun, cost 3 stamina";
	}

	@Override
	public String getAtk3Desc() {
		// TODO Auto-generated method stub
		return "Shout a war cry that affects every foes, diminishes their ATK by 25% for 2 turns";
	}

	@Override
	public String getAtk4Desc() {
		// TODO Auto-generated method stub
		return "Completely crush the foe on the front row for 500% damage with a 100% chance stun. Only at max stamina, deleat it completely afterward.";
	}

}
