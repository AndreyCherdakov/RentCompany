package server.config;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import dto.State;

@SuppressWarnings("serial")
public class Randoms implements Serializable {

	private static Random gen = new Random();
	
public static String nextRegNumber() {
		
		String str = "";
		int alfabetWordsLength = Config.ALPHABET_WORDS.length();
		int alfabetDigitsLength = Config.ALPHABET_DIGITS.length();
		
		str = str + Config.ALPHABET_WORDS.charAt(gen.nextInt(alfabetWordsLength));
		for (int i = 0; i < 3; i++) {
			str = str + Config.ALPHABET_DIGITS.charAt(gen.nextInt(alfabetDigitsLength));
		}
		for (int i = 0; i < 2; i++) {
			str = str + Config.ALPHABET_WORDS.charAt(gen.nextInt(alfabetWordsLength));
		}
		for (int i = 6; i < Config.REGNUMBER_LENGTH; i++) {
			str = str + Config.ALPHABET_DIGITS.charAt(gen.nextInt(alfabetDigitsLength));
		}
		
		return str;
	}
	
	public static String nextStringFromSet(String[] set) {
		return set[gen.nextInt(set.length)];
	}
	
	public static int nextIntegerFromSet(int[] set) {
		return set[gen.nextInt(set.length)];
	}
	
	public static State nextState() {
		return State.values()[gen.nextInt(State.values().length)];
	}

	public static boolean nextBoolean() {
		return gen.nextDouble() < Config.TRUE_PROBABILITY;
	}
	
	public static int nextIntegerRange(int from, int to) {
		return from + gen.nextInt(to - from + 1);
	}
	
	public static long nextLongNumberOfDigits(int numberOfDigits) {
		
		String strTemp = "1";
		for (int i = 1; i < numberOfDigits; i++) {
			strTemp = strTemp + "0";
		}
		long longMin = Long.parseLong(strTemp);
		long longMax = Long.parseLong(strTemp + "0");
		
		return longMin + ((long) (gen.nextDouble() * (longMax - longMin)));
	}
	
	public static String randomPhone() {
		String strPhone = "053-";
		for (int i = 0; i < Config.PHONE_NUMBER_OF_DIGITS; i++) {
			strPhone = strPhone + Config.ALPHABET_DIGITS.charAt(gen.nextInt(Config.ALPHABET_DIGITS.length()));
		}
		return strPhone;
	}
	
	public static LocalDate randomDate(LocalDate from, LocalDate to){
		long lFrom = from.toEpochDay();
		long lTo = to.toEpochDay();
		return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(lTo - lFrom + 1) + lFrom);
	}
	
}
