package fr.polytech.project.brightestcastle.forms;

public class CharaForm {
	private String name;
	
	private Job job;
	
	private byte CON;
	private byte STR;
	private byte VIG;
	
	public CharaForm() {}
	public CharaForm(String name, Job job, byte CON, byte STR, byte VIG) {
		setName(name);
		setJob(job);
		setCON(CON);
		setSTR(STR);
		setVIG(VIG);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public Job getJob() {
		return this.job;
	}
	
	public void setCON(byte CON) {
		this.CON = CON;
	}
	
	public byte getCON() {
		return this.CON;
	}
	
	public void setSTR(byte STR) {
		this.STR = STR;
	}
	
	public byte getSTR() {
		return this.STR;
	}
	
	public void setVIG(byte VIG) {
		this.VIG = VIG;
	}
	
	public byte getVIG() {
		return this.VIG;
	}
	
	public boolean isValid() {
		boolean maxCON = CON >= 0 && CON <= 5;
		boolean maxSTR = STR >= 0 && STR <= 5;
		boolean maxVIG = VIG >= 0 && VIG <= 5;
		
		boolean maxPts = (CON + STR + VIG) == 5;
		
		boolean validJob = (job != null);
		
		boolean validName = (name != null && !name.isEmpty());
		
		return maxCON && maxSTR && maxVIG && maxPts && validJob && validName;
	}
}