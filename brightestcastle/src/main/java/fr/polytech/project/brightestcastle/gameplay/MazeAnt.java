package fr.polytech.project.brightestcastle.gameplay;

public class MazeAnt extends Coords {
	private Direction dir;
	public int w, h, life;
	
	public MazeAnt() {}
	public MazeAnt(int x, int y, int w, int h, Direction dir, int life) {
		super(x, y);
		this.w = w;
		this.h = h;
		this.life = life;
		dir(dir);
	}
	
	public boolean isDead() {
		return this.life <= 0;
	}
	
	public void dir(Direction dir) {
		this.dir = dir;
	}
	
	public Direction dir() {
		return this.dir;
	}
	
	public void move(boolean finished) {
		switch(dir()) {
		case UP:
			if (y() > 0)
				y(y() - 1);
			break;
		case DOWN:
			if (y() < h-1)
				y(y() + 1);
			break;
		case LEFT:
			if (x() > 0)
				x(x() - 1);
			break;
		case RIGHT:
			if (x() < (finished ? w-2 : w-1))
				x(x() + 1);
			break;
		}
		this.life--;
	}
}
