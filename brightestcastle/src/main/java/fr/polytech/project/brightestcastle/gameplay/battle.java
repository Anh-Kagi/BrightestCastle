package fr.polytech.project.brightestcastle.battle;

import java.util.ArrayList;
import java.util.List;
import fr.polytech.project.brightestcastle.character.Monster;
import fr.polytech.project.brightestcastle.character.Wizard;
import fr.polytech.project.brightestcastle.character.Slime;
import fr.polytech.project.brightestcastle.character.Bat;
import fr.polytech.project.brightestcastle.character.EdgyKnight;
import java.lang.Math;

public class battle {
	
	public List<Monster> battleGenerate (long dist){
		List<Monster> ret = new ArrayList<Monster> ();
		int danger = (int)Math.ceil(dist);
		double rand = Math.random();
		switch (danger) {
			case 10:Wizard wizard = new Wizard();
				ret.add(wizard);
				return ret;
			case 9:if (rand<0.2) {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						EdgyKnight knight2 = new EdgyKnight();
						ret.add(knight2);
						EdgyKnight knight3 = new EdgyKnight();
						ret.add(knight3);
						return ret;
					}
					else if (rand<0.6) {
						Bat bat1 = new Bat();
						ret.add(bat1);
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						EdgyKnight knight2 = new EdgyKnight();
						ret.add(knight2);
						EdgyKnight knight3 = new EdgyKnight();
						ret.add(knight3);
						return ret;
					}
					else {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						EdgyKnight knight2 = new EdgyKnight();
						ret.add(knight2);
						EdgyKnight knight3 = new EdgyKnight();
						ret.add(knight3);
						Slime slime1 = new Slime();
						ret.add(slime1);
						return ret;
					}	
			case 8:if (rand<0.4) {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						Slime slime1 = new Slime();
						ret.add(slime1);
						EdgyKnight knight2 = new EdgyKnight();
						ret.add(knight2);
						Bat bat1 = new Bat();
						ret.add(bat1);
						return ret;
					}
					else if (rand<0.8) {
						Bat bat1 = new Bat();
						ret.add(bat1);
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						EdgyKnight knight2 = new EdgyKnight();
						ret.add(knight2);
						Bat bat2 = new Bat();
						ret.add(bat2);
						return ret;
					}
					else {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						EdgyKnight knight2 = new EdgyKnight();
						ret.add(knight2);
						return ret;
					}	
			case 7:if (rand<0.4) {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						Slime slime1 = new Slime();
						ret.add(slime1);
						EdgyKnight knight2 = new EdgyKnight();
						ret.add(knight2);
						return ret;
					}
					else if (rand<0.8) {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						EdgyKnight knight2 = new EdgyKnight();
						ret.add(knight2);
						Bat bat1 = new Bat();
						ret.add(bat1);
						return ret;
					}
					else {
						Bat bat1 = new Bat();
						ret.add(bat1);
						Bat bat2 = new Bat();
						ret.add(bat2);
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						return ret;
					}				
			case 6:if (rand<0.4) {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						return ret;
					}
					else if (rand<0.8) {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						Bat bat1 = new Bat();
						ret.add(bat1);
						Slime slime1 = new Slime();
						ret.add(slime1);
						return ret;
					}
					else {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						Bat bat1 = new Bat();
						ret.add(bat1);
						Bat bat2 = new Bat();
						ret.add(bat2);
						return ret;
					}
			case 5:if (rand<0.4) {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						Slime slime1 = new Slime();
						ret.add(slime1);
						return ret;
					}
					else if (rand<0.8) {
						EdgyKnight knight1 = new EdgyKnight();
						ret.add(knight1);
						Bat bat1 = new Bat();
						ret.add(bat1);
						return ret;
					}
					else {
						Bat bat1 = new Bat();
						ret.add(bat1);
						Bat bat2 = new Bat();
						ret.add(bat2);
						Bat bat3 = new Bat();
						ret.add(bat3);
						Bat bat4 = new Bat();
						ret.add(bat4);
						return ret;
					}
			case 4:if (rand<0.2) {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						Slime slime3 = new Slime();
						ret.add(slime3);
						Slime slime4 = new Slime();
						ret.add(slime4);
						return ret;
					}
					else if (rand<0.6) {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						Bat bat1 = new Bat();
						ret.add(bat1);
						Bat bat2 = new Bat();
						ret.add(bat2);
						return ret;
					}
					else {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						Slime slime3 = new Slime();
						ret.add(slime3);
						Bat bat1 = new Bat();
						ret.add(bat1);
						return ret;
					}
			case 3:if (rand<0.4) {
						Bat bat1 = new Bat();
						ret.add(bat1);
						Bat bat2 = new Bat();
						ret.add(bat2);
						Bat bat3 = new Bat();
						ret.add(bat3);
						return ret;
					}
					else if (rand<0.8) {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Bat bat1 = new Bat();
						ret.add(bat1);
						Bat bat2 = new Bat();
						ret.add(bat2);
						return ret;
					}
					else {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						Slime slime3 = new Slime();
						ret.add(slime3);
						Slime slime4 = new Slime();
						ret.add(slime4);
						return ret;
					}
			case 2: if (rand<0.2) {
						Bat bat1 = new Bat();
						ret.add(bat1);
						Bat bat2 = new Bat();
						ret.add(bat2);
						return ret;
					}
					else if (rand<0.6) {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						Slime slime3 = new Slime();
						ret.add(slime3);
						return ret;
					}
					else {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						Bat bat1 = new Bat();
						ret.add(bat1);
						return ret;
					}
			case 1:if (rand<0.4) {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Slime slime2 = new Slime();
						ret.add(slime2);
						return ret;
					}
					else if (rand<0.8) {
						Bat bat1 = new Bat();
						ret.add(bat1);
						Bat bat2 = new Bat();
						ret.add(bat2);
						return ret;
					}
					else {
						Slime slime1 = new Slime();
						ret.add(slime1);
						Bat bat1 = new Bat();
						ret.add(bat1);
						return ret;
					}
			default:Slime slime = new Slime();
					ret.add(slime);
					return ret;
					
		}
	}

}
