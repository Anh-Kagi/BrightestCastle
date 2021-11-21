package fr.polytech.project.brightestcastle.gameplay;

public class Map {
	private Square grid[][];
	
	public Map() {
		grid = new Square[][] {
			{ new Square('a'), new Square('b'), new Square('c') },
			{ new Square('d'), new Square('e'), new Square('f') },
			{ new Square('g'), new Square('h'), new Square('i') }
		};
	}
	
	public Square[][] getGrid() {
		return this.grid;
	}
	
	public int getWidth() {
		return grid.length;
	}
	
	public int getHeight() {
		return grid[0].length;
	}
}
