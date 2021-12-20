package fr.polytech.project.brightestcastle.gameplay.map;

/**
 * Class representing the squares of a {@link Map}
 */
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
	
	/**
	 * Setter
	 * @param visited if the {@link Square} has been visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * Getter
	 * @return if the {@link Square} has been visited
	 */
	public boolean getVisited() {
		return visited;
	}
	
	/**
	 * Setter
	 * @param type the {@link Square}'s type
	 */
	public void setType(SquareType type) {
		this.type = type;
	}
	
	/**
	 * Getter
	 * @return the {@link Square}'s type
	 */
	public SquareType getType() {
		return type;
	}
	
	/**
	 * Setter
	 * @param distance the {@link Square}'s distance to the {@link Map}'s end
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	/**
	 * Getter
	 * @return the {@link Square}'s distance to the {@link Map}'s end
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Returns the proximity of the current {@link Square} to the {@link Map}'s end.
	 * With 1.0 being the end {@link Square}, and 0 being the infinity.
	 * @return
	 */
	public float getBossProximity() {
		return distance == -1 ? -1.f : 1.f/((float)distance+1.f);
	}
}
