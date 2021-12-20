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
}
