package com.aerifiu.jokesource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeDispenser {

	private static List<String> jokeVault;

	static {
		jokeVault = new ArrayList<>();
		jokeVault.add("Question: How many mathematicians does it take to screw in a light bulb?\n" +
				"Answer: It's left to the reader as an exercise.");
		jokeVault.add("Horse walks into a bar, bartender says: why the long face?");
	}

	public static String getJoke() {
		Random random = new Random();
		int randomNumber = random.nextInt(jokeVault.size());
		return jokeVault.get(randomNumber);
	}
}
