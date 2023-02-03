package Assignment5;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortTest {

	@Test
	void SortTest1() {
		Integer[] array = { 44, 75, 23, 12, 64, 77, 33 };
		Sort.sort(array);
		assertArrayEquals(array,new Integer[] {12, 23, 33, 44, 64, 75, 77});
	}
	
	@Test
	void SortTest2() {
		Integer[] array = { 5,2,4,6,7};
		Sort.sort(array);
		assertArrayEquals(array,new Integer[] {2,4,5,6,7});
	}
	
	@Test
	void SortTest3() {
		Integer[] array = {100,5, 24,17,-3, 120, 200,225, 350};
		Sort.sort(array);
		assertArrayEquals(array,new Integer[] {-3,5,17,24,100, 120, 200,225,350});
	}


}
