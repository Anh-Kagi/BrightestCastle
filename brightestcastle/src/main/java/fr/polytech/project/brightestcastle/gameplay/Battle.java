package fr.polytech.project.brightestcastle.gameplay;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.Bat;
import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.EdgyKnight;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.entity.Slime;
import fr.polytech.project.brightestcastle.entity.Wizard;

public class Battle {
	/**
	 * Max number of enemies/allies
	 */
	public static final byte MAX_TEAM_MEMBERS = 4;
	
	private List<Played<Character>> characters = new ArrayList<Played<Character>>();
	private List<Played<Monster>> monsters = new ArrayList<Played<Monster>>();
	
	/**
	 * Adds a list of {@link Character}s to the {@code characters} list.<br>
	 * Fails if there would be more than {@link Battle#MAX_TEAM_MEMBERS} in {@code characters}.
	 * 
	 * @param characters a {@link List} of {@link Character}s
	 * @return if the list of {@link Character}s was added successfully
	 */
	public boolean addCharacters(List<Character> characters) {
		if (characters.size() + this.characters.size() >= Battle.MAX_TEAM_MEMBERS)
			return false;
		for (Character c : characters)
			this.characters.add(new Played<Character>(c));
		return true;
	}
	
	/**
	 * @return the number of {@link Character} on the battlefield
	 */
	public int nbCharacters() {
		return characters.size();
	}
	
	/**
	 * @return a list containing the {@link Character}s still on the battlefield
	 * @see Played
	 */
	public List<Played<Character>> getCharacters() {
		return characters;
	}

	/**
	 * Adds a list of {@link Monster}s to the {@code monsters} list.<br>
	 * Fails if there would be more than {@link Battle#MAX_TEAM_MEMBERS} in {@code monsters}.
	 * 
	 * @param monsters a {@link List} of {@link Monster}s
	 * @return if the list of {@link Monster}s was added successfully
	 */
	public boolean addMonsters(List<Monster> monsters) {
		if (monsters.size() + this.monsters.size() > Battle.MAX_TEAM_MEMBERS)
			return false;
		for (Monster m : monsters)
			this.monsters.add(new Played<Monster>(m));
		return true;
	}
	
	public int nbMonsters() {
		return monsters.size();
	}
	
	public List<Played<Monster>> getMonsters() {
		return monsters;
	}
	
	public boolean attack(int sender, int target, int attack) {
		// check that the sender exists
		// check that the target exists
		// check that the attack exists
		// check if player can attack
		// attack
		return true; // if could attack
	}
	
	public boolean endTurn() {
		// foreach enemy, do attack (if possible)
		// do states
		// reset player attcked
		return finished();
	}
	
	public boolean finished() {
		return monsters.size() == 0 || characters.size() == 0;
	}
	
	public boolean won() {
		return characters.size() > 0 && monsters.size() == 0;
	}
	
	/**
	 * Generates a group of {@link Monster} for a Battle.
	 * 
	 * @param dist the distance to the 
	 * @return the group of {@link Monster} (should be &lt;4)
	 */
	public static Battle generate (long dist){
		List<Monster> monsters = new ArrayList<Monster>();
		int danger = (int)Math.ceil(dist);
		double rand = Math.random();
		switch (danger) {
			case 10:
				monsters.add(new Wizard());
			case 9:
				if (rand<0.2) {
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
				} else if (rand<0.6) {
					monsters.add(new Bat());
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
				} else {
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
					monsters.add(new Slime());
				}	
			case 8:
				if (rand<0.4) {
					monsters.add(new EdgyKnight());
					monsters.add(new Slime());
					monsters.add(new EdgyKnight());
					monsters.add(new Bat());
				} else if (rand<0.8) {
					monsters.add(new Bat());
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
					monsters.add(new Bat());
				} else {
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
				}	
			case 7:
				if (rand<0.4) {
					monsters.add(new EdgyKnight());
					monsters.add(new Slime());
					monsters.add(new EdgyKnight());
				} else if (rand<0.8) {
					monsters.add(new EdgyKnight());
					monsters.add(new EdgyKnight());
					monsters.add(new Bat());
				} else {
					monsters.add(new Bat());
					monsters.add(new Bat());
					monsters.add(new EdgyKnight());
				}				
			case 6:
				if (rand<0.4) {
					monsters.add(new EdgyKnight());
					monsters.add(new Slime());
					monsters.add(new Slime());
				} else if (rand<0.8) {
					monsters.add(new EdgyKnight());
					monsters.add(new Bat());
					monsters.add(new Slime());
				} else {
					monsters.add(new EdgyKnight());
					monsters.add(new Bat());
					monsters.add(new Bat());
				}
			case 5:
				if (rand<0.4) {
					monsters.add(new EdgyKnight());
					monsters.add(new Slime());
				} else if (rand<0.8) {
					monsters.add(new EdgyKnight());
					monsters.add(new Bat());
				} else {
					monsters.add(new Bat());
					monsters.add(new Bat());
					monsters.add(new Bat());
					monsters.add(new Bat());
				}
			case 4:
				if (rand<0.2) {
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Slime());
				} else if (rand<0.6) {
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Bat());
					monsters.add(new Bat());
				} else {
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Bat());
				}
			case 3:
				if (rand<0.4) {
					monsters.add(new Bat());
					monsters.add(new Bat());
					monsters.add(new Bat());
				} else if (rand<0.8) {
					monsters.add(new Slime());
					monsters.add(new Bat());
					monsters.add(new Bat());
				} else {
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Slime());
				}
			case 2:
				if (rand<0.2) {
					monsters.add(new Bat());
					monsters.add(new Bat());
				} else if (rand<0.6) {
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Slime());
				} else {
					monsters.add(new Slime());
					monsters.add(new Slime());
					monsters.add(new Bat());
				}
			case 1:
				if (rand<0.4) {
					monsters.add(new Slime());
					monsters.add(new Slime());
				} else if (rand<0.8) {
					monsters.add(new Bat());
					monsters.add(new Bat());
				} else {
					monsters.add(new Slime());
					monsters.add(new Bat());
				}
			default:
				monsters.add(new Slime());
		}
		
		Battle battle = new Battle();
		for (Monster m : monsters) {
			System.out.println(m.getType().name());
		}
		if (!battle.addMonsters(monsters))
			System.err.println("Couldn't add monsters");
		return battle;
	}
}