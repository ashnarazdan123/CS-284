package Assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void addTest() {
		Treap<Integer> testTree = new Treap<Integer>();

		assertTrue(testTree.add(4, 19));
		//System.out.println(testTree);
	}

	@Test
	void deleteTest() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		//System.out.println(testTree);
		assertTrue(testTree.delete(4));
		assertFalse(testTree.delete(9));
		//System.out.println("delete" +testTree);
	}
	
	@Test
	void findTest() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		//System.out.println(testTree);
		assertTrue(testTree.find(4));
		//System.out.println("delete" +testTree);
	}
}
