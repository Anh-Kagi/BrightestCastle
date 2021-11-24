package fr.polytech.project.brightestcastle.character;


public class Bat extends Monster implements Attack {
	
	MonsterType type;

	public Bat() {
		super("Bat",(byte) 1,(byte) 3, MonsterType.BAT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int takeDamage(int damage) {
		if ( Math.random()*100 <=15) {
			System.out.println("The bat Dodged !");
			return 0;
		}
		else {
			
			int damageInflicted=damage-getDEF();
			setHP(getHP()-damageInflicted);
			return damageInflicted;
		}
	}

	@Override
	public void attack1(Character[] targets) {
		setHP(getHP()+(int)(0.5*targets[0].takeDamageBlinded(getATK())));
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack2(Character[] targets) {
		for (int i=0;i<4;i++) {
			targets[i].takeDamage((int)(0.75*getATK()));
		}
		// TODO Auto-generated method stub
		
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
