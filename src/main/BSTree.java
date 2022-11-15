package main;

public class BSTree<T extends Comparable<T>> {

    private BSTNode<T> root;

    public BSTree() {
        setRoot(null);
    }

    public void add(T value) throws NullPointerException {
        if(value == null)
            throw new NullPointerException("The element to add cannot be null");

        if(getRoot() == null){
            BSTNode<T> node = new BSTNode<>(value);
            setRoot(node);
            node.updateHeight();
        } else {
            recursiveAdd(getRoot(), value);
        }
    }

    private void recursiveAdd(BSTNode<T> currentRoot, T value) throws IllegalArgumentException {
        if(currentRoot.getValue().equals(value))
           throw new IllegalArgumentException("Element is already present");

        if(value.compareTo(currentRoot.getValue()) < 0) {
            if(currentRoot.getLeft() == null) {
                BSTNode<T> node = new BSTNode<>(value);
                node.setRoot(currentRoot);
                currentRoot.setLeft(node);
                node.updateHeight();
            }
            else recursiveAdd(currentRoot.getLeft(), value);
        } else {
            if(currentRoot.getRight() == null) {
                BSTNode<T> node = new BSTNode<>(value);
                node.setRoot(currentRoot);
                currentRoot.setRight(node);
                node.updateHeight();
            }
            else recursiveAdd(currentRoot.getRight(), value);
        }
    }

    public boolean search(T value) {
        if(getRoot() == null)
            return false;

        return recursiveSearch(getRoot(), value);
    }

    private boolean recursiveSearch(BSTNode<T> currentRoot, T value) {
        if(currentRoot.getValue().equals(value))
            return true;

        if(value.compareTo(currentRoot.getValue()) < 0) {
            return currentRoot.getLeft() == null ? false : recursiveSearch(currentRoot.getLeft(), value);
        } else {
            return currentRoot.getRight() == null ? false : recursiveSearch(currentRoot.getRight(), value);
        }
    }

    public BSTNode<T> getNode(T value) {
        if(getRoot() == null)
            return null;

        return recursiveGetNode(getRoot(), value);
    }

    private BSTNode<T> recursiveGetNode(BSTNode<T> currentRoot, T value) {
        if(currentRoot.getValue().equals(value))
            return currentRoot;

        if(value.compareTo(currentRoot.getValue()) < 0) {
            return currentRoot.getLeft() == null ? null : recursiveGetNode(currentRoot.getLeft(), value);
        } else {
            return currentRoot.getRight() == null ? null : recursiveGetNode(currentRoot.getRight(), value);
        }
    }

    public T getMax(BSTNode<T> currentRoot) throws NullPointerException {
        if(currentRoot == null)
            throw new NullPointerException("The node cannot be null");

        if(!currentRoot.hasRight())
            return currentRoot.getValue();

       return getMax(currentRoot.getRight());
    }

    public BSTNode<T> remove(T element) {

        return null;
    }

    @Override
    public String toString() {
        if(getRoot() == null)
            return null;

        return recursiveToString(getRoot());
    }

    private String recursiveToString(BSTNode<T> currentRoot) {
        String result = "";
        result += currentRoot.getValue().toString() + '(' + currentRoot.getHeight() + ')';
        result += currentRoot.getLeft() == null ? '-' : recursiveToString(currentRoot.getLeft());
        result += currentRoot.getRight() == null ? '-' : recursiveToString(currentRoot.getRight());
        return result;
    }

    public BSTNode<T> getRoot() {
        return root;
    }

    public void setRoot(BSTNode<T> root) {
        this.root = root;
    }

}
