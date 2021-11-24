package fr.polytech.project.brightestcastle.gameplay.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Map {
	private Square grid[][];
	private Coords start;
	private Coords end;
	
	protected Map(int w, int h) {
		grid = new Square[h][w];
	}
	
	public Square[][] getGrid() {
		return this.grid;
	}
	
	public Square getSquare(int x, int y) {
		if (y < 0 || y >= grid.length)
			return null;
		if (x < 0 || x >= grid[y].length)
			return null;
		return grid[y][x];
	}
	
	public Square getSquare(Coords c) {
		return getSquare(c.x(), c.y());
	}
	
	public int getHeight() {
		return grid.length;
	}
	
	public int getWidth() {
		return grid[0].length;
	}
	
	private void setStart(Coords start) {
		this.start = start;
	}
	
	public Coords getStart() {
		return this.start;
	}
	
	private void setEnd(Coords end) {
		this.end = end;
	}
	
	public Coords getEnd() {
		return this.end;
	}
	
	protected boolean generateSquare(Coords c) {
		if (grid[c.y()][c.x()] == null) {
			grid[c.y()][c.x()] = new Square();
			return false;
		}
		return true;
	}
	
	public int nbNeighborSquares(Coords c) {
		int nbNeighbors = 0;
		if (getSquare(c.x(), c.y()-1) != null)
			nbNeighbors++;
		if (getSquare(c.x(), c.y()+1) != null)
			nbNeighbors++;
		if (getSquare(c.x()-1, c.y()) != null)
			nbNeighbors++;
		if (getSquare(c.x()+1, c.y()) != null)
			nbNeighbors++;
		return nbNeighbors;
	}
	
	public static Map generate(int w, int h) {
		Map map = new Map(w, h);
		Random rand = new Random();
		
		List<MazeAnt> ants = new ArrayList<MazeAnt>();
		ants.add(new MazeAnt(0, rand.nextInt(h), map.getWidth(), map.getHeight(), Direction.RIGHT, rand.nextInt(1)+1));
		
		map.generateSquare(ants.get(0));
		map.setStart(ants.get(0).cloneCoords());
		
		boolean finished = false;
		while (ants.size() > 0) {
			for (int i=0; i<ants.size(); i++) {
				MazeAnt a = ants.get(i);
				if (a.move(finished))
					if (map.generateSquare(a))
						ants.remove(a);
				if (a.x() == map.getWidth()-1) {
					finished = true;
					map.setEnd(a.cloneCoords());
					ants.remove(a);
					continue;
				}
				
				if (a.isDead()) {
					// bifurcation
					if (map.nbNeighborSquares(a) <= 1) {
						if (a.dir() == Direction.RIGHT) {
							Stack<Direction> dirs = new Stack<Direction>();
							if (a.x() < (finished ? map.getWidth()-2 : map.getWidth()-1))
								dirs.add(Direction.RIGHT);
							if (a.y() > 0)
								dirs.add(Direction.UP);
							if (a.y() < map.getHeight()-1)
								dirs.add(Direction.DOWN);
							
							int nbDirs = rand.nextInt(dirs.size())+1;
							if (nbDirs < dirs.size())
								Collections.shuffle(dirs);
							for (int j=0; j<nbDirs; j++) {
								MazeAnt na = new MazeAnt(a.x(), a.y(), map.getWidth(), map.getHeight(), dirs.pop(), rand.nextInt(2)+2);
								ants.add(na);
							}
						} else {
							MazeAnt na = new MazeAnt(a.x(), a.y(), map.getWidth(), map.getHeight(), Direction.RIGHT, rand.nextInt(2)+2);
							ants.add(na);
						}
					}
					ants.remove(a);
				}
			}
		}
		
		return map;	
	}
	
	@Override
	public String toString() {
		String ret = "";
		for (int y=0; y<getHeight(); y++) {
			for (int x=0; x<getWidth(); x++)
				ret += getSquare(x, y) == null ? " " : "0";
			ret += "\n";
		}
		return ret;
	}
}
