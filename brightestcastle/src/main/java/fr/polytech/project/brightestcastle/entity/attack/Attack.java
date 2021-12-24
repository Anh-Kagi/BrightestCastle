package fr.polytech.project.brightestcastle.entity.attack;

import fr.polytech.project.brightestcastle.entity.Entity;
import fr.polytech.project.brightestcastle.gameplay.Battle;

public abstract class Attack<T extends Entity> {
	private T sender;
	private String name;
	private String desc;
	
	public Attack(T sender, String name, String desc) {
		this.sender = sender;
		this.name = name;
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return desc;
	}
	
	public T getSender() {
		return sender;
	}
	
	public boolean needTargets() {
		return getTargetables() == null;
	}
	
	public abstract boolean[] getTargetables(); 
	
	public abstract void attack(Battle battle, Entity target);
}
