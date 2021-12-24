package fr.polytech.project.brightestcastle.entity;

public class Status {
	private StatusEnum name;
	private int duration;
	
	public Status(StatusEnum name, int duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public StatusEnum getName() {
		return name;
	}
	
	public void setName(StatusEnum name) {
		this.name = name;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void addDuration(int duration) {
		this.duration+=duration;
	}
	
	public void countDown() {
		this.duration-=1;
	}
}