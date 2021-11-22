package fr.polytech.project.brightestcastle.gameplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Map {
	private Square grid[][];
	
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
	
	public int getHeight() {
		return grid.length;
	}
	
	public int getWidth() {
		return grid[0].length;
	}
	
	protected boolean generateSquare(Coords c) {
		if (grid[c.y()][c.x()] == null) {
			grid[c.y()][c.x()] = new Square();
			return false;
		}
		return true;
	}
	
	public static Map generate(int w, int h) {
		Map map = new Map(w, h);
		Random rand = new Random();
		
		List<MazeAnt> ants = new ArrayList<MazeAnt>();
		ants.add(new MazeAnt(0, rand.nextInt(h), map.getWidth(), map.getHeight(), Direction.RIGHT, rand.nextInt(2)+2));
		
		map.generateSquare(ants.get(0));
		
		boolean finished = false;
		while (ants.size() > 0) {
			for (int i=0; i<ants.size(); i++) {
				MazeAnt a = ants.get(i);
				a.move(finished);
				if (map.generateSquare(a))
					ants.remove(a);
				if (a.x() == map.getWidth()-1) {
					finished = true;
					ants.remove(a);
					continue;
				}
				
				if (a.isDead()) {
					// bifurcation
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
