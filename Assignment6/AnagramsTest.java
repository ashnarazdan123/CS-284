package Assignment6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class AnagramsTest {
	@Test
	void testAddWord() {
		Anagrams a = new Anagrams();
		a.addWord("j");

		assertFalse(a.anagramTable.containsKey((long) 8));
		assertTrue(a.anagramTable.containsKey((long) 29));
		a.addWord("peach");
		assertTrue(a.anagramTable.containsKey((long) 110770));
		a.addWord("cheap");
		assertTrue(a.anagramTable.containsKey((long) 110770));

	}

	@Test
	void testMaxEntries() {
		Anagrams ana = new Anagrams();
		ana.addWord("chape");
		ana.addWord("job");
		ana.addWord("boj");

		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = ana.getMaxEntries();
		String listString = maxEntries.stream().map(Object::toString).collect(Collectors.joining(", "));
		assertEquals(listString, "110770=[peach, cheap, chape]");
	}

	@Test
	void testMaxEntries2() {
		Anagrams ana = new Anagrams();
		ana.addWord("race");
		ana.addWord("care");
		ana.addWord("caer");
		ana.addWord("raec");
		ana.addWord("acer");

		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = ana.getMaxEntries();
		String listString = maxEntries.stream().map(Object::toString).collect(Collectors.joining(", "));
		assertEquals(listString,"6710=[race, care, caer, raec, acer]");
	}
}
