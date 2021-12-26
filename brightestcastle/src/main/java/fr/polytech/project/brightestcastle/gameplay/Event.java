package fr.polytech.project.brightestcastle.gameplay;

import fr.polytech.project.brightestcastle.entity.Entity;
import java.lang.Math;

public class Event {
	
	private String description;

	public Event (Entity target) {
		double rand = Math.random();
		double rand2 = Math.random();
		int value = (int)Math.ceil(10*rand);
		switch (value) {
			case 10: this.description = "During your adventure, you find the corpses of some other travelers, surprisingly, their belongings still lie near them. Even more surprising, a weapon is of very good quality, it would improve someone's strength greatly.";
			target.setSTRbase((byte)(target.getSTRbase()+1));
			case 9: this.description = "You meet a travelling smith with a carriage stuck in some quick clays. After helping him out, he offers to repair and reinforce an armor, free of charge.";
			target.setDEFbase(target.getDEFbase()+1);
			case 8: this.description = "Inspecting a pretty noticeable landmark, you notice something sticking out of a nearby bush. It seems to be some poorly hidden chest containing a shiny trinket, that seems to help its wearer to recovering their strength faster";
			target.setVIGbase((byte)(target.getVIGbase()+1));
			case 7: this.description = "During one of your lunch, one of the party member actually ate their greens! They grew to be a very healthy individual.";
			target.setCONbase((byte)(target.getCONbase()+1));
			case 6: this.description = "You find a suspicious weapon on an altar, when approaching, you feel strangely uneasy...";
			if (rand2>0.5) {
				this.description +="The weapon is vibrating with powerful magic, no foe will stand in your path now!";
				target.setSTRbase((byte)(target.getSTRbase()+2));
			}
			else {
				this.description +="The weapon was cursed! You feel your strength abandoning you...  No way to get rid of it now...";
				target.setSTRbase((byte)(target.getSTRbase()-1));
				
			}
			case 5: this.description ="You found what's looks like a magic source. Legends tell that you can enhance something by dipping it into the source.";
			if (rand2>0.5) {
				this.description +="You put your armor into the source, and it came back a strong as ever!";
				target.setDEFbase(target.getDEFbase()+2);
			}
			else {
				this.description +="You put your armor into the source, it came back completely rusted... Guess the legends were not so true after all...";
				target.setDEFbase(target.getDEFbase()-1);
			}
			case 4: this.description ="You encounter some shady witch who offers you a special potion. Perhaps a bit too trusting, you accept.";
			if (rand2>0.5) {
				this.description +="The witch was in fact more of an apothecary, her medicine helped you regain your strength faster!";
				target.setVIGbase((byte)(target.getVIGbase()+2));
			}
			else {
				this.description +="It was poison, you feel weak and very sick. This is why you don't trust strangers with weird mixtures...";
				target.setVIGbase((byte)(target.getVIGbase()-1));
			}
			case 3: this.description ="During your adventure, you came across a very weird, shiny apple. You are drawn to take a bite.";
			if (rand2>0.5) {
				this.description +="You feel a lot healthier now! Guess it must have been an enchanted apple.";
				target.setCONbase((byte)(target.getCONbase()+2));
			}
			else {
				this.description +="Your head began to throb soon after. Eating this wasn't a good idea, you don't feel so good...";
				target.setCONbase((byte)(target.getCONbase()-1));
			}
			case 2: this.description ="You encounter some lost traveler that seem pretty capable, they offer to join your team, as their previous collaborator meet an unfortunate end...";
			//TODO: generating a teammate
			case 1: this.description ="You fell into an ambush! Some monster attacked you when your guard was down, injuring you before you could slay them...";
			target.takeTrueDamage(5);
		}		
	}
	
	public String getDescription() {
		return description;
	}
}