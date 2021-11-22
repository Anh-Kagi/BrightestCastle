package fr.polytech.project.brightestcastle.character;
public class Monster extends Character {
	
	private MonsterType type;

	public Monster(String name, byte constitution, byte strength, MonsterType type) {
		super(name, CharacterClass.MONSTER, constitution, strength, (byte) 0);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

}

