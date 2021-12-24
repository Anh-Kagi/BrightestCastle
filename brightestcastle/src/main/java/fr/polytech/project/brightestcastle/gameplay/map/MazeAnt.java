package fr.polytech.project.brightestcastle.gameplay.map;

/**
 * Ants used in {@link Map} to generate {@link Square}s.
 * 
 * They have a TTL (size of the hallway), a {@link Direction},
 * the ant's starting coordinates, and the {@link Map}'s dimensions.
 * 
 * @see Map
 * @see Direction
 * @see Square
 */
public class MazeAnt extends Coords {
	private Direction dir;
	public int w, h, ttl;
	
	/**
	 * @param x the starting x-coordinates
	 * @param y the starting y-coordinates
	 * @param w the {@link Map}'s width
	 * @param h the {@link Map}'s height
	 * @param dir the ant's {@link Direction}
	 * @param ttl the number of room to generate
	 * @see Map
	 * @see Direction
	 */
	public MazeAnt(int x, int y, int w, int h, Direction dir, int ttl) {
		super(x, y);
		this.w = w;
		this.h = h;
		this.ttl = ttl;
		dir(dir);
	}
	
	/**
	 * @return if the ant used all its TTL.
	 */
	public boolean isDead() {
		return this.ttl <= 0;
	}
	
	/**
	 * @return how much {@link Square} are left to generate.
	 * @see Square
	 */
	public int getTTL() {
		return this.ttl;
	}
	
	/**
	 * @param dir the ant's {@link Direction}
	 * @see Direction 
	 */
	public void dir(Direction dir) {
		this.dir = dir;
	}
	
	/**
	 * @return the ant's {@link Direction}
	 * @see Direction
	 */
	public Direction dir() {
		return this.dir;
	}
	
	/**
	 * Makes the ant move following its {@link Direction}.<br><br>
	 * 
	 * The {@code finished} flag defines if an ending {@link Square} has been generated.
	 * If {@code false}, it allows the ant to move to the last column of the grid.
	 * If {@code true}, the ants won't be able to pass the penultimate column. 
	 * 
	 * @param finished if an ending {@link Square} has already been generated
	 * @return if the ant managed to move successfully (if it didn't hit any wall)
	 * @see Direction
	 * @see Square
	 */
	public boolean move(boolean finished) {
		boolean moved = false;
		switch(dir()) {
		case UP:
			if (y() > 0) {
				y(y() - 1);
				moved = true;
			}
			break;
		case DOWN:
			if (y() < h-1) {
				y(y() + 1);
				moved = true;
			}
			break;
		case LEFT:
			if (x() > 0) {
				x(x() - 1);
				moved = true;
			}
			break;
		case RIGHT:
			if (x() < (finished ? w-2 : w-1)) {
				x(x() + 1);
				moved = true;
			}
			break;
		}
		this.ttl--;
		return moved;
	}
}
