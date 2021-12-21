package fr.polytech.project.brightestcastle.gameplay.map;

/**
 * Class holding x and y coordinates.
 */
public class Coords {
	private int x;
	private int y;
	
	public Coords() {}
	public Coords(int x, int y) {
		x(x);
		y(y);
	}
	
	/**
	 * Setter
	 * @param x x-coordinate
	 */
	public void x(int x) {
		this.x = x;
	}
	
	/**
	 * Getter
	 * @return x-coordinate
	 */
	public int x() {
		return this.x;
	}
	
	/**
	 * Setter
	 * @param y y-coordinates
	 */
	public void y(int y) {
		this.y = y;
	}
	
	/**
	 * Getter
	 * @return y-coordinates
	 */
	public int y() {
		return this.y;
	}
	
	/**
	 * Return a new {@link Coords} instance with the same xy-coordinates.
	 * @return a cloned instance of {@link Coords}
	 */
	@Override
	public Coords clone() {
		Coords coords = new Coords();
		coords.x(x());
		coords.y(y());
		return coords;
	}
	
	/**
	 * Gets the {@link Coords} of the neighbors for each {@link Direction}.
	 * 
	 * @param x x-coordinate of the target
	 * @param y y-coordinate of the target
	 * @return the {@link Coords} for each neighbor following each {@link Direction}
	 */
	public static Coords[] getNeighbors(int x, int y) {
		return new Coords[] {
				new Coords(x+1, y),
				new Coords(x, y+1),
				new Coords(x-1, y),
				new Coords(x, y-1),
		};
	}
	
	/**
	 * Shortcut for {@link Coords#getNeighbors(int, int)}.
	 * 
	 * @param c {@link Coords} of the target
	 * @return the {@link Coords} for each neighbor followin each {@link Direction}
	 */
	public static Coords[] getNeighbors(Coords c) {
		return getNeighbors(c.x(), c.y());
	}
}
