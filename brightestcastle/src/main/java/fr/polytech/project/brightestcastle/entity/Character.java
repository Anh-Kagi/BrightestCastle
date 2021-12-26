package fr.polytech.project.brightestcastle.entity;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import fr.polytech.project.brightestcastle.entity.attack.Attack;

public abstract class Character extends Entity {
	private String name;
	private int threat;

	public Character(String name, byte STR, byte CON, byte VIG) {
		super(STR, CON, VIG);
		setName(name);
		setDEFbase(3);
		setThreat(0);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract CharacterJob getJob();

	public int getThreat() {
		return threat;
	}

	public void setThreat(int threat) {
		this.threat = threat;
	}

	public void addThreat(int threat) {
		setThreat(getThreat() + threat);
	}

	public abstract List<Attack<Character>> getAttacks();

	public static Character generate() {
		String names[] = { "Wigmund", "Edvárd", "Ted", "Raimondo", "Raimundo", "Allyster", "Sigeweard", "Guerino",
				"Allastir", "Edmondo", "Sascha", "Erramun", "Shepherd", "da", "Claramond", "Raymonde", "Amparo",
				"Lexie", "Alexina" };

		String name = names[ThreadLocalRandom.current().nextInt(names.length)];
		
		
		byte STR = (byte) ThreadLocalRandom.current().nextInt(8 - 2);
		byte CON = (byte) ThreadLocalRandom.current().nextInt(8 - 1 - STR);
		STR++;
		CON++;
		byte VIG = (byte) (8 - CON - STR);

		CharacterJob job = CharacterJob.values()[ThreadLocalRandom.current().nextInt(CharacterJob.values().length)];

		switch (job) {
		case BOWMAN:
			return new Bowman(name, STR, CON, VIG);
		case PALADIN:
			return new Paladin(name, STR, CON, VIG);
		case WARRIOR:
			return new Warrior(name, STR, CON, VIG);
		default:
			return null; // should never happen
		}
	}
}
