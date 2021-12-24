package fr.polytech.project.brightestcastle.gameplay;

import fr.polytech.project.brightestcastle.entity.Entity;

public class Played<T extends Entity> {
	private T entity; 
	
	private boolean played;
	
	public Played() {}
	
	public Played(T src) {
		entity = src;
	}
	
	public T entity() {
		return entity;
	}
	
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public void setPlayed(boolean played) {
		this.played = played;
	}
	
	public boolean getPlayed() {
		return played;
	}
}
