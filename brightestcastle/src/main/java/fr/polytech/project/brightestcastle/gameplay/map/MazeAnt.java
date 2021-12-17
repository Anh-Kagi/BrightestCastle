package fr.polytech.project.brightestcastle.gameplay.map;

public class MazeAnt extends Coords {
	private Direction dir;
	public int w, h, ttl;
	
	public MazeAnt() {}
	public MazeAnt(int x, int y, int w, int h, Direction dir, int ttl) {
		super(x, y);
		this.w = w;
		this.h = h;
		this.ttl = ttl;
		dir(dir);
	}
	
	public boolean isDead() {
		return this.ttl <= 0;
	}
	
	public int getTTL() {
		return this.ttl;
	}
	
	public void dir(Direction dir) {
		this.dir = dir;
	}
	
	public Direction dir() {
		return this.dir;
	}
	
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
