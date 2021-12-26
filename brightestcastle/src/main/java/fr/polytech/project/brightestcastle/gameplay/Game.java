package fr.polytech.project.brightestcastle.gameplay;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.Character;
import fr.polytech.project.brightestcastle.gameplay.map.Coords;
import fr.polytech.project.brightestcastle.gameplay.map.Direction;
import fr.polytech.project.brightestcastle.gameplay.map.Map;
import fr.polytech.project.brightestcastle.gameplay.map.Square;

public class Game {
	private Map map;
	private Coords pos;
	
	private final List<Character> group = new ArrayList<Character>();
	
	/**
	 * Generates a {@link Map} with the given dimensions.
	 * 
	 * @param w {@link Map} width
	 * @param h {@link Map} height
	 * @see Map
	 */
	public Game(int w, int h) {
		map = Map.generate(w, h);
		pos = map.getStart().clone();
	}
	
	/**
	 * Returns generated {@link Map}.
	 * 
	 * @return {@link Map}
	 * @see Map
	 */
	public Map getMap() {
		return this.map;
	}
	
	/**
	 * Current player's position.
	 * @return player's position
	 */
	public Coords getPos() {
		return this.pos;
	}
	
	/**
	 * Shortcut for {@code getMap().getSquare(getPos())}.
	 * 
	 * @return current square
	 */
	public Square getSquare() {
		return getMap().getSquare(getPos());
	}
	
	/**
	 * Move the player in the map, checks if the player can move in the provided
	 * direction (if the target square exists).
	 * 
	 * @param dir direction of the movement
	 * @return return if the square was already visited
	 */
	public boolean move(Direction dir) {
		switch(dir) {
		case UP:
			if (getMap().getSquare(getPos().x(), getPos().y()-1) != null)
				getPos().y(getPos().y() - 1);
			break;
		case DOWN:
			if (getMap().getSquare(getPos().x(), getPos().y()+1) != null)
				getPos().y(getPos().y() + 1);
			break;
		case LEFT:
			if (getMap().getSquare(getPos().x()-1, getPos().y()) != null)
				getPos().x(getPos().x() - 1);
			break;
		case RIGHT:
			if (getMap().getSquare(getPos().x()+1, getPos().y()) != null)
				getPos().x(getPos().x() + 1);
			break;
		}
		return map.getSquare(getPos()).getVisited();
	}
	
	/**
	 * @return a {@link List} containing the player's {@link Character}s
	 */
	public List<Character> getGroup() {
		return group;
	}
}
