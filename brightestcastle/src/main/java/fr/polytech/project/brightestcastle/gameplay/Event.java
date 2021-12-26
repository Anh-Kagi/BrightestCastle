package fr.polytech.project.brightestcastle.gameplay;

import fr.polytech.project.brightestcastle.entity.Character;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Event {
	public static List<String> applyEvent(List<Character> group) {
		ArrayList<String> ret = new ArrayList<String>();
		switch ((int) Math.ceil(10 * Math.random())) {
		case 10:
			ret.add("During your adventure, you find the corpses of some other travelers, surprisingly, their belongings still lie near them. Even more surprising, a weapon is of very good quality, it would improve someone's strength greatly.");
			for (Character target: group)
				target.setSTRbase((byte) (target.getSTRbase() + 1));
			break;
		case 9:
			ret.add("You meet a travelling smith with a carriage stuck in some quick clays. After helping him out, he offers to repair and reinforce an armor, free of charge.");
			for (Character target: group)
				target.setDEFbase(target.getDEFbase() + 1);
			break;
		case 8:
			ret.add("Inspecting a pretty noticeable landmark, you notice something sticking out of a nearby bush. It seems to be some poorly hidden chest containing a shiny trinket, that seems to help its wearer to recovering their strength faster");
			for (Character target: group)
				target.setVIGbase((byte) (target.getVIGbase() + 1));
			break;
		case 7:
			ret.add("During one of your lunch, one of the party member actually ate their greens! They grew to be a very healthy individual.");
			for (Character target: group)
				target.setCONbase((byte) (target.getCONbase() + 1));
			break;
		case 6:
			ret.add("You find a suspicious weapon on an altar, when approaching, you feel strangely uneasy...");
			if (Math.random() < 0.5) {
				ret.add("The weapon is vibrating with powerful magic, no foe will stand in your path now!");
				for (Character target: group)
					target.setSTRbase((byte) (target.getSTRbase() + 2));
			} else {
				ret.add("The weapon was cursed! You feel your strength abandoning you...� No way to get rid of it now...");
				for (Character target: group)
					target.setSTRbase((byte) (target.getSTRbase() - 1));
			}
			break;
		case 5:
			ret.add("You found what's looks like a magic source. Legends tell that you can enhance something by dipping it into the source.");
			if (Math.random() < 0.5) {
				ret.add("You put your armor into the source, and it came back a strong as ever!");
				for (Character target: group)
					target.setDEFbase(target.getDEFbase() + 2);
			} else {
				ret.add("You put your armor into the source, it came back completely rusted... Guess the legends were not so true after all...");
				for (Character target: group)
					target.setDEFbase(target.getDEFbase() - 1);
			}
			break;
		case 4:
			ret.add("You encounter some shady witch who offers you a special potion. Perhaps a bit too trusting, you accept.");
			if (Math.random() < 0.5) {
				ret.add("The witch was in fact more of an apothecary, her medicine helped you regain your strength faster!");
				for (Character target: group)
					target.setVIGbase((byte) (target.getVIGbase() + 2));
			} else {
				ret.add("It was poison, you feel weak and very sick. This is why you don't trust strangers with weird mixtures...");
				for (Character target: group)
					target.setVIGbase((byte) (target.getVIGbase() - 1));
			}
			break;
		case 3:
			ret.add("During your adventure, you came across a very weird, shiny apple. You are drawn to take a bite.");
			if (Math.random() < 0.5) {
				ret.add("You feel a lot healthier now! Guess it must have been an enchanted apple.");
				for (Character target: group)
					target.setCONbase((byte) (target.getCONbase() + 2));
			} else {
				ret.add("Your head began to throb soon after. Eating this wasn't a good idea, you don't feel so good...");
				for (Character target: group)
					target.setCONbase((byte) (target.getCONbase() - 1));
			}
			break;
		case 2:
			ret.add("You encounter some lost traveler that seem pretty capable.");
			if (group.size() >= 4)
				ret.add("You offer him to join your company, but he refuses, mocking you while walking away.");
			else {
				ret.add("He chooses to join your group, since his former company had an unfortunate end...")
				group.add(Character.generate());
			}
			break;
		case 1:
			ret.add("You fell into an ambush! Some monster attacked you when your guard was down, injuring you before you could slay them...");
			for (Character target: group)
				target.takeTrueDamage(5);
			break;
		}
		return ret;
	}
}