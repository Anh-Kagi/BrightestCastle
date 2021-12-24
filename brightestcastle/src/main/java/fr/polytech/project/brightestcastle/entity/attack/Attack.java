package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;

public abstract class Attack {
	private String name;
	private String desc;
	private Entity sender;
	
	public Attack(Entity sender, String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return desc;
	}
	
	public Entity getSender() {
		return sender;
	}
	
	public abstract void attack(Entity[] targets);
}
