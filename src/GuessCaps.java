import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class GuessCaps {
	public List<GuessCaps> caps = new ArrayList<GuessCaps>();
	public Set<GuessCaps> tenSet = new HashSet<GuessCaps>();
	private String state;
	private String cap;
	static Scanner keyboard = new Scanner(System.in);

	public GuessCaps() {

	}

	public GuessCaps(String state, String cap) {
		this.state = state;
		this.cap = cap;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public static String readFile(String classlist) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(new File(classlist)));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			return sb.toString();
		} catch (IOException io) {
			throw new RuntimeException(io);
		} finally {
			try {
				br.close();
			} catch (IOException io) {

			}
		}
	}

	public List<GuessCaps> listOfFile() {
		String list = readFile("stateCaps.txt");
		String eachline = null;
		Scanner inputScanner = new Scanner(list);
		String[] strArray = null;
		while (inputScanner.hasNext()) {
			eachline = inputScanner.nextLine();
			strArray = eachline.split("\t");
			String state = strArray[0];
			String cap = strArray[1];
			GuessCaps gc = new GuessCaps(state, cap);
			caps.add(gc);
		}
		return caps;
	}

	public void randomlyChoose(List<GuessCaps> caps) {
		int correctCounter = 0;
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(caps);
			tenSet.add(caps.get(0));
		}
		for (GuessCaps s : caps.subList(0, 9)) {
			out.println("What is the capital of " + s.getState() + "?");
			String guess = keyboard.nextLine();
			if (s.getCap().equalsIgnoreCase(guess)) {
				out.println("You're right!");
				correctCounter++;
			} else {
				out.println("Sorry, that's not right!");
			}
		}
		out.println(correctCounter + " correct!");
		switch (correctCounter)

		{
		case 10:
			out.println("Congratulations, you got them all right!");
			break;
		case 9:
			out.println("You only missed one!");
			break;
		case 8:
			out.println("You're pretty good but you could improve!");
			break;
		case 7:
			out.println("You're pretty good but you could improve!");
			break;
		case 6:
			out.println("You're pretty good but you could improve!");
			break;
		case 5:
			out.println("You need to memorize your state capitals!");
			break;
		case 4:
			out.println("You need to memorize your state capitals!");
			break;
		case 3:
			out.println("You need to memorize your state capitals!");
			break;
		case 2:
			out.println("You need to memorize your state capitals!");
			break;
		case 1:
			out.println("You need to memorize your state capitals!");
			break;
		case 0:
			out.println("You need to memorize your state capitals! You missed them all!");
			break;
		}
	}

	public void game() {
		out.println("Hello, in this game, you will be quizzed on your state capitals! Let's see how many you know!");
		caps = listOfFile();
		randomlyChoose(caps);
	}

	public static void main(String[] args) {
		GuessCaps g = new GuessCaps();
		g.game();
	}

}
