package fr.polytech.project.brightestcastle.gameplay.map;

public class Coords {
	private int x;
	private int y;
	
	public Coords() {}
	public Coords(int x, int y) {
		x(x);
		y(y);
	}
	
	public void x(int x) {
		this.x = x;
	}
	
	public int x() {
		return this.x;
	}
	
	public void y(int y) {
		this.y = y;
	}
	
	public int y() {
		return this.y;
	}
	
	public Coords cloneCoords() {
		Coords coords = new Coords();
		coords.x(x());
		coords.y(y());
		return coords;
	}
}
