package fr.polytech.project.brightestcastle.gameplay;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.Bat;
import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.entity.EdgyKnight;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.entity.Slime;
import fr.polytech.project.brightestcastle.entity.Wizard;
import fr.polytech.project.brightestcastle.entity.attack.Attack;
import fr.polytech.project.brightestcastle.gameplay.map.Square;

public class Battle {
	/**
	 * Max number of enemies/allies
	 */
	public static final byte MAX_TEAM_MEMBERS = 4;
	
	private List<Played<Character>> characters = new ArrayList<Played<Character>>();
	private List<Played<Monster>> monsters = new ArrayList<Played<Monster>>(); // TODO removed Played for monsters
	
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
		for (Character c : characters) {
			c.setSTA(0);
			this.characters.add(new Played<Character>(c));
		}
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
		System.out.println("Attack: " + sender + " " + target + " " + attack);
		if (sender >= 0 && sender < this.characters.size()) { // check that the sender exists
			Played<Character> c = getCharacters().get(sender);
			if (!c.getPlayed()) { // if player didn't played already this turn
				if (attack >= 0 && attack < c.entity().getAttacks().size()) { // check that the attack exists
					Attack<Character> a = c.entity().getAttacks().get(attack);
					Monster m = (a.needTarget() && target >= 0 && target < getMonsters().size()) ? getMonsters().get(target).entity() : null; // check that the target exists or isn't needed
					
					a.attack(this, m);
					c.setPlayed(true);
					return true;
				} else
					System.out.println("invalid attack"); // TODO: tmp
			} else
				System.out.println("player already played"); // TODO: tmp
		} else
			System.out.println("invalid sender"); // TODO: tmp
		return false;
	}
	
	public boolean endTurn() {
		// TODO: foreach enemy, do attack (if possible)

		// update states
		for (Played<Character> c : characters)
			c.entity().statusUpdate();
		for (Played<Monster> m : monsters)
			m.entity().statusUpdate();
		
		// reset played flag
		for (Played<Character> c : characters)
			c.setPlayed(false);
		for (Played<Monster> m : monsters)
			m.setPlayed(false);
		
		// regen STA
		for (Played<Character> c : characters)
			c.entity().generateSTA();
		for (Played<Monster> m : monsters)
			m.entity().generateSTA();
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
	 * @param dist the proximity to the boss's {@link Square}
	 * @return the group of {@link Monster} (should be &lt;4)
	 * @see Square#getBossProximity()
	 */
	public static Battle generate (List<Character> group, float dist){
		List<Monster> monsters = new ArrayList<Monster>();
		int danger = (int)Math.ceil(dist*10);
		double rand = Math.random();
		switch (danger) {
			case 10:
				monsters.add(new Wizard());
				break;
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
				break;
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
				break;
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
				break;		
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
				break;
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
				break;
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
				break;
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
				break;
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
				break;
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
				break;
			default:
				monsters.add(new Slime());
		}
		
		Battle battle = new Battle();
		battle.addCharacters(group);
		if (!battle.addMonsters(monsters))
			System.err.println("Couldn't add monsters");
		return battle;
	}
}