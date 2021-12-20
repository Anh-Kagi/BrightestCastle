package fr.polytech.project.brightestcastle.entity;

public class Status {
	private StatusEnum name;
	private String effect;
	private int duration;
	
	public Status(StatusEnum name, int duration) {
		this.name = name;
		this.duration = duration;
		
		switch(name) {
			case POISONNED:
				break;
			case STUNNED:
				break;
			case BLINDED:
				break;
			case ATKUP:
				break;
			case ATKDOWN:
				break;
			case VIGORUP:
				break;
			case VIGORDOWN:
				break;
			default:
				break;
		}
	}
}