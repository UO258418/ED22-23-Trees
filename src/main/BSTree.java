package main;

public class BSTree<T extends Comparable<T>> {

    private Node<T> root;

    public BSTree() {
        setRoot(null);
    }

    public void add(T value) throws NullPointerException {
        if(value == null)
            throw new NullPointerException("The element to add cannot be null");

        if(getRoot() == null){
            Node<T> node = new Node<>(value);
            setRoot(node);
        } else {
            recursiveAdd(getRoot(), value);
        }
    }

    private void recursiveAdd(Node<T> currentRoot, T value) throws IllegalArgumentException {
        if(currentRoot.getValue().equals(value))
           throw new IllegalArgumentException("Element is already present");

        if(value.compareTo(currentRoot.getValue()) < 0) {
            if(currentRoot.getLeft() == null)
                currentRoot.setLeft(new Node<>(value));
            else recursiveAdd(currentRoot.getLeft(), value);
        } else {
            if(currentRoot.getRight() == null)
                currentRoot.setRight(new Node<>(value));
            else recursiveAdd(currentRoot.getRight(), value);
        }
    }

    public boolean search(T value) {
        if(getRoot() == null)
            return false;

        return recursiveSearch(getRoot(), value);
    }

    private boolean recursiveSearch(Node<T> currentRoot, T value) {
        if(currentRoot.getValue().equals(value))
            return true;

        if(value.compareTo(currentRoot.getValue()) < 0) {
            return currentRoot.getLeft() == null ? false : recursiveSearch(currentRoot.getLeft(), value);
        } else {
            return currentRoot.getRight() == null ? false : recursiveSearch(currentRoot.getRight(), value);
        }
    }

    public T getMax(Node<T> root) {
        return null;
    }

    @Override
    public String toString() {
        if(getRoot() == null)
            return null;

        String result = "";
        return recursiveToString(getRoot());
    }

    private String recursiveToString(Node<T> currentRoot) {
        String result = "";
        result += currentRoot.getValue();
        result += currentRoot.getLeft() == null ? '-' : recursiveToString(currentRoot.getLeft());
        result += currentRoot.getRight() == null ? '-' : recursiveToString(currentRoot.getRight());
        return result;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

}
