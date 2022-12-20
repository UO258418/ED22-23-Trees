package test;

import main.BSTree;

public class IncompleteRatioTest {

    public static void main(String... args) {
        testIncompleteRatio();
    }

    public static void testIncompleteRatio() {
        BSTree<Integer> tree = new BSTree<>();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(4);

        System.out.println(tree);
        System.out.println(tree.incompleteRatio());

        BSTree<Character> tree2 = new BSTree<>();
        tree2.add('d');
        tree2.add('b');
        tree2.add('a');
        tree2.add('c');
        tree2.add('e');

        System.out.println(tree2);
        System.out.println(tree2.incompleteRatio());
    }

}
