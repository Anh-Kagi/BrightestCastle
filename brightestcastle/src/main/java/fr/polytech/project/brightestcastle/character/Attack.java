package fr.polytech.project.brightestcastle.character;

public interface Attack {

	public void attack1(Character target);
	public void attack2(Character target);
	public void attack3(Character target);
	public void attack4(Character target);
	
	public String getAtk1Name();
	public String getAtk2Name();
	public String getAtk3Name();
	public String getAtk4Name();
}
