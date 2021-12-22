package fr.polytech.project.brightestcastle.character;

import java.util.List;

public class Character {
	
	private String name;
	private CharacterClass job;
	private byte constitution; // give more HP
	private byte strength; // give more ATT
	private byte vigor; // give more STA regen each turn
	

	private int HP;
	private int HPMax;
	private int ATK;
	private int STA; // same for everyone, regen each turn
	private int DEF;
	
	private int DEFTemp;
	private int ATKTemp;
	private int vigorTemp;

	private int Threat;
	
	private List <Status> listStatus;

	
	public Character(String name, CharacterClass job,byte constitution, byte strength, byte vigor) {
		super();
		this.name = name;
		this.job = job;
		this.constitution = constitution;
		this.strength = strength;
		this.vigor = vigor;
		
		this.HP = 25 +  constitution * 5;
		this.ATK = 3 + strength;
		this.STA = 20;

		this.setATKTemp(this.ATK);
		this.setDEFTemp(this.DEF);
		this.setVigorTemp(this.vigor);
	
		this.Threat=0;

	}

	public byte getConstitution() {
		return constitution;
	}

	public void setConstitution(byte constitution) {
		this.constitution = constitution;
	}

	public byte getStrength() {
		return strength;
	}

	public void setStrength(byte strength) {
		this.strength = strength;
	}

	public byte getVigor() {
		return vigor;
	}

	public void setVigor(byte vigor) {
		this.vigor = vigor;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		if(hP<=HPMax) {
			HP = hP;
		} else HP=HPMax;
	}

	public int getHPMax() {
		return HPMax;
	}

	public void setHPMax(int hPMax) {
		HPMax = hPMax;
	}

	public int getATK() {
		return ATK;
	}

	public void setATK(int aTK) {
		ATK = aTK;
	}

	public int getSTA() {
		return STA;
	}

	public void setSTA(int sTA) {
		STA = sTA;
	}

	public int getDEF() {
		return DEF;
	}

	public void setDEF(int dEF) {
		DEF = dEF;
	}
		
	public int getDEFTemp() {
		return DEFTemp;
	}

	public void setDEFTemp(int dEFTemp) {
		DEFTemp = dEFTemp;
	}

	public int getATKTemp() {
		return ATKTemp;
	}

	public void setATKTemp(int aTKTemp) {
		ATKTemp = aTKTemp;
	}

	public int getVigorTemp() {
		return vigorTemp;
	}

	public void setVigorTemp(int vigorTemp) {
		this.vigorTemp = vigorTemp;
	}

	public int getThreat() {
		return Threat;
	}
	
	public void setThreat(int threat) {
		Threat=threat;
	}

	public void addThreat(int threat) {
		Threat += threat;
	}

	public int takeDamage(int damage) {
		
		int damageInflicted=damage-getDEFTemp();
		setHP(getHP()-damageInflicted);
		
		return damageInflicted;
	}
	
	public int takeDamageBlinded(int damage) {
		if ( Math.random()*100 <=50) {
			System.out.println("You Missed your attack!");
			return 0;
		}
		else {
			int damageInflicted=damage-getDEFTemp();
			setHP(getHP()-damageInflicted);
			return damageInflicted;
		}
	}
	
	public int takeTrueDamage(int damage) {
		setHP(getHP()-damage);
		return damage;
	}
	
	public void generateSTA (int vigor) {
		setSTA(getSTA()+vigorTemp);
	}

	public List <Status> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List <Status> listStatus) {
		this.listStatus = listStatus;
	}

	public int alreadyAffected(StatusEnum status) {
		int pos=-1;
		for (int i=0; i< listStatus.size(); i++) {
			if(listStatus.get(i).getName()==status) pos=i;
		}
		return pos; 
	}
	
	public void addStatus(StatusEnum status, int duration) {
		int pos=alreadyAffected(status);
		if(pos==-1) {
			Status s= new Status(status, duration);
			listStatus.add(s);
			switch(status) {
			
			case POISONNED://poison doesn't deal damage yet, only during the status update
				break;
				
			case STUNNED:
				break;
				
			case BLINDED://?
				break;
			
			case ATKUP://+2 ATK
				setATKTemp(getATKTemp()+2);
				break;
				
			case ATKDOWN://-2 ATK, cannot go under 1
				
				if(getATKTemp()<=2) {
					setATKTemp(1);
				}
				else {
					setATKTemp(getATKTemp()-2);
				}
				break;
				
			case VIGORUP://+3 vigor
				setVigorTemp(getVigor()+3);
				break;
				
			case VIGORDOWN://-3 vigor, cannot go under 1
				if(getVigorTemp()<=3) {
					setVigorTemp(1);
				}
				else {
					setVigorTemp(getVigorTemp()-3);
				}
				break;
			
			default:
				break;

			}
		}
		else {
			listStatus.get(pos).addDuration(duration);
		}
	}
	
	public void returnToNormal(StatusEnum status) {
		switch(status) {
		
		case POISONNED:
			break;
			
		case STUNNED:
			break;
			
		case BLINDED:
			break;
		
		case ATKUP:
			setATKTemp(getATKTemp()-2);
			break;
			
		case ATKDOWN:
			
			if(getATK()==1 && getATKTemp()<=2) {
				setATKTemp(1);
			}
			else if(getATK()==2 && getATKTemp()<=2) {
				setATKTemp(2);
			}
			else {
				setATKTemp(getATKTemp()+2);
			}
			break;
			
		case VIGORUP://+3 vigor
			setVigorTemp(getVigor()-3);
			break;
			
		case VIGORDOWN://-3 vigor, cannot go under 1
			if(getVigor()==1 && getVigorTemp()<=3) {
				setVigorTemp(1);
			}
			else if (getVigor()==2 && getVigorTemp()<=3) {
				setVigorTemp(2);
			}
			else {
				setVigorTemp(getVigorTemp()-3);
			}
			break;
		
		default:
			break;

		}
	}

	public void statusUpdate() {
		for(int i=0; i<listStatus.size();i++) {
			
			listStatus.get(i).countDown();
			if(listStatus.get(i).getName()==StatusEnum.POISONNED){
				takeTrueDamage(5);
			}
			if(listStatus.get(i).getDuration()==0) {
				returnToNormal(listStatus.get(i).getName());
				listStatus.remove(i);
				i-=1;
			}
		}
	}
	
}
