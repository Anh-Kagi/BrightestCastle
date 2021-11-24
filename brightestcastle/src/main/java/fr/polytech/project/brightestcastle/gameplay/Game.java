package fr.polytech.project.brightestcastle.gameplay;

import fr.polytech.project.brightestcastle.gameplay.map.Coords;
import fr.polytech.project.brightestcastle.gameplay.map.Direction;
import fr.polytech.project.brightestcastle.gameplay.map.Map;

public class Game {
	private Map map;
	private Coords pos;
	
	public Game(int w, int h) {
		map = Map.generate(w, h);
		pos = map.getStart().cloneCoords();
	}
	
	public Map getMap() {
		return this.map;
	}
	
	public Coords getPos() {
		return this.pos;
	}
	
	public void move(Direction dir) {
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
	}
}
