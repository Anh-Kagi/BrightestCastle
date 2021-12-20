package fr.polytech.project.brightestcastle.gameplay;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.project.brightestcastle.entity.Bat;
import fr.polytech.project.brightestcastle.entity.EdgyKnight;
import fr.polytech.project.brightestcastle.entity.Monster;
import fr.polytech.project.brightestcastle.entity.Slime;
import fr.polytech.project.brightestcastle.entity.Wizard;

public class Battle {
	/**
	 * Generates a group of {@link Monster} for a Battle.
	 * 
	 * @param dist the distance to the 
	 * @return the group of {@link Monster} (should be <4)
	 */
	public static List<Monster> battleGenerate (long dist){
		List<Monster> ret = new ArrayList<Monster> ();
		int danger = (int)Math.ceil(dist);
		double rand = Math.random();
		switch (danger) {
			case 10:
				ret.add(new Wizard());
			case 9:
				if (rand<0.2) {
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
				} else if (rand<0.6) {
					ret.add(new Bat());
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
				} else {
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
					ret.add(new Slime());
				}	
			case 8:
				if (rand<0.4) {
					ret.add(new EdgyKnight());
					ret.add(new Slime());
					ret.add(new EdgyKnight());
					ret.add(new Bat());
				} else if (rand<0.8) {
					ret.add(new Bat());
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
					ret.add(new Bat());
				} else {
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
				}	
			case 7:
				if (rand<0.4) {
					ret.add(new EdgyKnight());
					ret.add(new Slime());
					ret.add(new EdgyKnight());
				} else if (rand<0.8) {
					ret.add(new EdgyKnight());
					ret.add(new EdgyKnight());
					ret.add(new Bat());
				} else {
					ret.add(new Bat());
					ret.add(new Bat());
					ret.add(new EdgyKnight());
				}				
			case 6:
				if (rand<0.4) {
					ret.add(new EdgyKnight());
					ret.add(new Slime());
					ret.add(new Slime());
				} else if (rand<0.8) {
					ret.add(new EdgyKnight());
					ret.add(new Bat());
					ret.add(new Slime());
				} else {
					ret.add(new EdgyKnight());
					ret.add(new Bat());
					ret.add(new Bat());
				}
			case 5:
				if (rand<0.4) {
					ret.add(new EdgyKnight());
					ret.add(new Slime());
				} else if (rand<0.8) {
					ret.add(new EdgyKnight());
					ret.add(new Bat());
				} else {
					ret.add(new Bat());
					ret.add(new Bat());
					ret.add(new Bat());
					ret.add(new Bat());
				}
			case 4:
				if (rand<0.2) {
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Slime());
				} else if (rand<0.6) {
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Bat());
					ret.add(new Bat());
				} else {
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Bat());
				}
			case 3:
				if (rand<0.4) {
					ret.add(new Bat());
					ret.add(new Bat());
					ret.add(new Bat());
				} else if (rand<0.8) {
					ret.add(new Slime());
					ret.add(new Bat());
					ret.add(new Bat());
				} else {
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Slime());
				}
			case 2:
				if (rand<0.2) {
					ret.add(new Bat());
					ret.add(new Bat());
				} else if (rand<0.6) {
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Slime());
				} else {
					ret.add(new Slime());
					ret.add(new Slime());
					ret.add(new Bat());
				}
			case 1:
				if (rand<0.4) {
					ret.add(new Slime());
					ret.add(new Slime());
				} else if (rand<0.8) {
					ret.add(new Bat());
					ret.add(new Bat());
				} else {
					ret.add(new Slime());
					ret.add(new Bat());
				}
			default:
				ret.add(new Slime());
		}
		return ret;
	}
}