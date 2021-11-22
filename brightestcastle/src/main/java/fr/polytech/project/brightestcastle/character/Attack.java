package fr.polytech.project.brightestcastle.character;

public interface Attack {

	public void attack1(Character[] targets);
	public void attack2(Character[] targets);
	public void attack3(Character[] targets);
	public void attack4(Character[] targets);
	
	public String getAtk1Name();
	public String getAtk2Name();
	public String getAtk3Name();
	public String getAtk4Name();
	
	public String getAtk1Desc();
	public String getAtk2Desc();
	public String getAtk3Desc();
	public String getAtk4Desc();
}
