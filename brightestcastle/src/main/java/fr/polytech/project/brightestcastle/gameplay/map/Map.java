package fr.polytech.project.brightestcastle.gameplay.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * A {@link Map} contains every {@link Square} that compose a dungeon.<br>
 * 
 * A {@link Map} is created using {@link Map#generate(int, int)} which will generate a Maze.
 * 
 * @see Square
 */
public class Map {
	private Square grid[][];
	private Coords start;
	private Coords end;
	
	protected Map(int w, int h) {
		grid = new Square[h][w];
	}
	
	/**
	 * Gives access to the square grid.<br>
	 * 
	 * {@code null} means that no room exists at the given coordinates.
	 * 
	 * @return the {@link Square} grid
	 * @see Square
	 */
	protected Square[][] getGrid() {
		return this.grid;
	}
	
	/**
	 * Returns the square at the given coordinates.<br>
	 * 
	 * If the coordinates are off-grid, {@code null} is returned.
	 * 
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return the {@link Square} instance
	 * @see Square
	 */
	public Square getSquare(int x, int y) {
		if (y < 0 || y >= grid.length)
			return null;
		if (x < 0 || x >= grid[y].length)
			return null;
		return grid[y][x];
	}
	
	/**
	 * Shortcut for {@link Map#getSquare(int, int)}.
	 * 
	 * @param c the {@link Coords} of the {@link Square}
	 * @return the {@link Square} instance
	 * @see Map#getSquare(int, int)
	 */
	public Square getSquare(Coords c) {
		return getSquare(c.x(), c.y());
	}
	
	/**
	 * @return {@link Map}'s height 
	 */
	public int getHeight() {
		return grid.length;
	}
	
	/**
	 * @return {@link Map}'s width
	 */
	public int getWidth() {
		return grid[0].length;
	}
	
	/**
	 * Sets the starting square of the map.<br>
	 * It should be the coordinates of a non-null {@link Square}, no verification is made.<br>
	 * Sets the {@link Square} in which the player will spawn.
	 * 
	 * @param start the {@link Coords} of the start
	 * @see Square
	 * @see Coords
	 */
	private void setStart(Coords start) {
		this.start = start;
	}
	
	/**
	 * Returns the {@link Coords} of the starting Square.
	 * 
	 * @return the starting {@link Coords}
	 * @see Coords
	 */
	public Coords getStart() {
		return this.start;
	}
	
	/**
	 * Sets the {@link Coords} of the end of the dungeon.<br>
	 * It doesn't modify the dungeon, neither does it swap the {@link SquareType#BOSS} Square.<br>
	 * It is set after the dungeon is generated.<br><br>
	 * 
	 * The {@link Square}s' {@link Square#getDistance()} and {@link Square#getBossProximity()} will be affected.
	 * 
	 * @param end the {@link Coords} of the end of the dungeon
	 * @see Coords
	 * @see Square
	 */
	private void setEnd(Coords end) {
		this.end = end;
		
		// clears squares' distances
		for (Square[] row : getGrid())
			for (Square s : row)
				if (s != null)
					s.setDistance(-1);
		
		// recompute squares' distances
		computeDistances();
	}

	/**
	 * Returns the {@link Coords} of the ending {@link Square}.<br>
	 * {@link Square#getDistance()} and {@link Square#getBossProximity()} are computed in comparison to it.
	 *  
	 * @return the ending {@link Coords}
	 * @see Coords
	 */
	public Coords getEnd() {
		return this.end;
	}

	/**
	 * Computes the {@link Square}s' distances to the end of the dungeon.
	 * 
	 * @see Map#getEnd()
	 * @see Map#setEnd(Coords)
	 * @see Square#getDistance()
	 * @see Square#getBossProximity()
	 */
	private void computeDistances() {
		List<Coords> cs = new ArrayList<Coords>();
		
		cs.add(getEnd());
		int distance = 0;
		
		while(!cs.isEmpty()) {
			for (int i=cs.size()-1; i>=0; i--) {
				Coords c = cs.get(i);
				Square s = getSquare(c);
				if (s != null && s.getDistance() == -1)
					s.setDistance(distance);
				
				Coords neighbors[] = {
					new Coords(c.x()-1, c.y()),
					new Coords(c.x()+1, c.y()),
					new Coords(c.x(), c.y()-1),
					new Coords(c.x(), c.y()+1)
				};
				for (Coords cn : neighbors) {
					Square sn = getSquare(cn);
					if (sn != null && sn.getDistance() == -1)
						cs.add(new Coords(cn.x(), cn.y()));
				}
				cs.remove(i);
			}
			distance++;
		}
	}
	
	/**
	 * Generate a {@link Square} at the given {@link Coords}.<br>
	 * The coordinates must be in-grid, no verification is made.<br><br>
	 * 
	 * If the {@link Square} at the given coordinates already exists,
	 * no modification is made and the method returns {@code true}.<br>
	 * If the {@link Square} is {@code null},
	 * a new {@link Square} is generated with the given {@link SquareType}
	 * and with a {@code visited} flag to {@code false}.
	 * 
	 * @param c the {@link Square}'s coordinates
	 * @param type the {@link SquareType} to affect
	 * @return if the given {@link Square} has already been generated
	 * @see Square
	 * @see Coords
	 * @see SquareType
	 */
	private boolean generateSquare(Coords c, SquareType type) {
		if (grid[c.y()][c.x()] == null) {
			grid[c.y()][c.x()] = new Square(type, false);
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the amount of {@link Square} around the one at the given {@link Coords}
	 * that are non-{@code null}.<br>
	 * 
	 * The checked {@link Square}s are the ones in each @{link Direction}s.
	 *  
	 * @param c the reference {@link Square}
	 * @return the amount of non-{@code null} neighbors
	 * @see Square
	 * @see Coords
	 */
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
	
	/**
	 * Generates a @{link Map} from the given dimensions.<br><br>
	 * 
	 * The generation works in the following way:
	 * a starting {@link Square} is chosen in the first column of the grid.<br><br>
	 * 
	 * Then, hallways are built using {@link MazeAnt}s:
	 * The first {@link MazeAnt} is created at the starting Square and heads to the {@link Direction#RIGHT}.<br>
	 * Each {@link MazeAnt} walks and generate {@link Square}s until it dies.<br><br>
	 * 
	 * A {@link MazeAnt} can die of old age (number of generated {@link Square}) or
	 * by walking on an already generated {@link Square}.<br><br>
	 * 
	 * Each generated {@link Square} can have the following {@link SquareType}:
	 * {@link SquareType#FIGHT} or {@link SquareType#LOOT}.
	 * Except if the {@link MazeAnt} died of old age,
	 * in which case a {@link SquareType#CAMP} is generated.<br>
	 * Starting {@link Square} is of type {@link SquareType#EMPTY}, and ending {@link Square} is of type {@link SquareType#BOSS}.
	 * 
	 * @param w the {@link Map}'s width
	 * @param h the {@link Map}'s height
	 * @return the generated {@link Map}
	 * @see Square
	 * @see MazeAnt
	 * @see SquareType
	 */
	public static Map generate(int w, int h) {
		Map map = new Map(w, h);
		Random rand = new Random();
		
		List<MazeAnt> ants = new ArrayList<MazeAnt>();
		ants.add(new MazeAnt(0, rand.nextInt(h), map.getWidth(), map.getHeight(), Direction.RIGHT, rand.nextInt(1)+1));
		
		map.generateSquare(ants.get(0), SquareType.EMPTY);
		map.getSquare(ants.get(0)).setVisited(true);
		map.setStart(ants.get(0).clone());
		
		Coords end = null;
		
		boolean finished = false;
		while (ants.size() > 0) {
			for (int i=ants.size()-1; i>=0; i--) {
				MazeAnt a = ants.get(i);
				if (a.move(finished))
					if (map.generateSquare(a, a.isDead() ? SquareType.CAMP : (rand.nextFloat() < 0.25 ? SquareType.LOOT : SquareType.FIGHT)))
						ants.remove(a);
				if (a.x() == map.getWidth()-1) {
					finished = true;
					end = ((Coords) a).clone(); // parenthesis are optional, but for better understanding
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
		
		map.setEnd(end);
		map.getSquare(map.getEnd()).setType(SquareType.BOSS);
		return map;	
	}
}
