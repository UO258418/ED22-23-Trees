/*package test;


import main.AVLTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BFTest {

	@Test
	public void test() {
		AVLTree<String> avl=new AVLTree<String>();
		
		avl.add("C");
		// 0 children
		assertEquals(avl.getRoot().getBF(), 0);

		avl.add("A");

		assertEquals(avl.getRoot().getBF(), -1);
		
		avl.add("B");

		assertEquals(avl.getRoot().getBF(), -2);
		
		avl.add("E");
		
		assertEquals(avl.getRoot().getBF(), -1);
		
		avl.add("D");

		assertEquals(avl.getRoot().getBF(), 0);
		

		avl.add("F");

		assertEquals(avl.getRoot().getBF(), 0);
		

		avl.add("G");

		assertEquals(avl.getRoot().getBF(), 1);
		
		avl.add("H");

		assertEquals(avl.getRoot().getBF(), 2);
		

		avl.add("I");

		assertEquals(avl.getRoot().getBF(), 3);
				
		
	}
}*/