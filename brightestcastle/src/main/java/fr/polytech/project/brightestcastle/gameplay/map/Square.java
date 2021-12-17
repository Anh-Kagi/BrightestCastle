package fr.polytech.project.brightestcastle.gameplay.map;

public class Square {
	private boolean visited = false;
	private SquareType type;
	private int distance = -1;
	
	public Square(SquareType type) {
		this(type, false);
	}
	
	public Square(SquareType type, boolean visited) {
		this(type, visited, -1);
	}
	
	public Square(SquareType type, boolean visited, int distance) {
		setType(type);
		setVisited(visited);
		setDistance(distance);
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setType(SquareType type) {
		this.type = type;
	}
	
	public SquareType getType() {
		return type;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public float getBossProximity() {
		return distance == -1 ? -1.f : 1.f/((float)distance+1.f);
	}
}
