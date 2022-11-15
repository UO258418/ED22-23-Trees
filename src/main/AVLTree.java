package main;

public class AVLTree<T extends Comparable<T>> {

    private static final int BF_LIMIT = 2;
    private AVLNode<T> root;

    public AVLTree() {
        setRoot(null);
    }

    public void add(T value) throws NullPointerException {
        if(value == null)
            throw new NullPointerException("The element to add cannot be null");

        if(getRoot() == null){
            AVLNode<T> node = new AVLNode<>(value);
            setRoot(node);
            updateBF(getRoot());
        } else {
            recursiveAdd(getRoot(), value);
        }
    }

    private void recursiveAdd(AVLNode<T> currentRoot, T value) throws IllegalArgumentException {
        if(currentRoot.getValue().equals(value))
           throw new IllegalArgumentException("Element is already present");

        if(value.compareTo(currentRoot.getValue()) < 0) {
            if(currentRoot.getLeft() == null) {
                AVLNode<T> node = new AVLNode<>(value);
                node.setRoot(currentRoot);
                currentRoot.setLeft(node);
                updateBF(getRoot());
            }
            else recursiveAdd(currentRoot.getLeft(), value);
        } else {
            if(currentRoot.getRight() == null) {
                AVLNode<T> node = new AVLNode<>(value);
                node.setRoot(currentRoot);
                currentRoot.setRight(node);
                updateBF(getRoot());
            }
            else recursiveAdd(currentRoot.getRight(), value);
        }
    }

    public boolean search(T value) {
        if(getRoot() == null)
            return false;

        return recursiveSearch(getRoot(), value);
    }

    private boolean recursiveSearch(AVLNode<T> currentRoot, T value) {
        if(currentRoot.getValue().equals(value))
            return true;

        if(value.compareTo(currentRoot.getValue()) < 0) {
            return currentRoot.getLeft() == null ? false : recursiveSearch(currentRoot.getLeft(), value);
        } else {
            return currentRoot.getRight() == null ? false : recursiveSearch(currentRoot.getRight(), value);
        }
    }

    public AVLNode<T> getNode(T value) {
        if(getRoot() == null)
            return null;

        return recursiveGetNode(getRoot(), value);
    }

    private AVLNode<T> recursiveGetNode(AVLNode<T> currentRoot, T value) {
        if(currentRoot.getValue().equals(value))
            return currentRoot;

        if(value.compareTo(currentRoot.getValue()) < 0) {
            return currentRoot.getLeft() == null ? null : recursiveGetNode(currentRoot.getLeft(), value);
        } else {
            return currentRoot.getRight() == null ? null : recursiveGetNode(currentRoot.getRight(), value);
        }
    }

    public T getMax(AVLNode<T> currentRoot) throws NullPointerException {
        if(currentRoot == null)
            throw new NullPointerException("The node cannot be null");

        if(!currentRoot.hasRight())
            return currentRoot.getValue();

       return getMax(currentRoot.getRight());
    }

    public AVLNode<T> remove(T element) {

        return null;
    }

    private AVLNode<T> updateBF(AVLNode<T> currentRoot) {
        if(currentRoot.getBF() <= -BF_LIMIT)
            if(currentRoot.getLeft().getBF() <= 0)
                currentRoot = singleLeftRotation(currentRoot);
            else
                currentRoot = doubleLeftRotation(currentRoot);
        else if(currentRoot.getBF() >= BF_LIMIT)
            if(currentRoot.getRight().getBF() >= 0)
                currentRoot = singleRightRotation(currentRoot);
            else
                currentRoot = doubleRightRotation(currentRoot);

        currentRoot.updateHeight();
        return currentRoot;
    }

    private AVLNode<T> singleLeftRotation(AVLNode<T> currentRoot) {
        AVLNode<T> newRoot = currentRoot.getLeft();
        currentRoot.setLeft(newRoot.getRight());
        newRoot.setRight(currentRoot);
        currentRoot.updateHeight();
        return newRoot;
    }

    private AVLNode<T> doubleLeftRotation(AVLNode<T> currentRoot) {
        AVLNode<T> currentLeft = currentRoot.getLeft();
        AVLNode<T> newRoot = currentLeft.getRight();
        currentRoot.setLeft(newRoot.getRight());
        newRoot.setRight(currentRoot);
        currentLeft.setRight(newRoot.getLeft());
        newRoot.setLeft(currentLeft);

        currentLeft.updateHeight();
        return newRoot;
    }

    private AVLNode<T> singleRightRotation(AVLNode<T> currentRoot) {
        AVLNode<T> newRoot = currentRoot.getRight();
        currentRoot.setRight(newRoot.getLeft());
        newRoot.setLeft(currentRoot);
        currentRoot.updateHeight();
        return newRoot;
    }

    private AVLNode<T> doubleRightRotation(AVLNode<T> currentRoot) {
        AVLNode<T> currentRight = currentRoot.getRight();
        AVLNode<T> newRoot = currentRight.getLeft();
        currentRoot.setRight(newRoot.getLeft());
        newRoot.setLeft(currentRoot);
        currentRight.setLeft(newRoot.getRight());
        newRoot.setRight(currentRight);

        currentRight.updateHeight();
        return newRoot;
    }

    @Override
    public String toString() {
        if(getRoot() == null)
            return null;

        return recursiveToString(getRoot());
    }

    private String recursiveToString(AVLNode<T> currentRoot) {
        String result = "";
        result += currentRoot.getValue().toString() + '(' + currentRoot.getBF() + ')';
        result += currentRoot.getLeft() == null ? '-' : recursiveToString(currentRoot.getLeft());
        result += currentRoot.getRight() == null ? '-' : recursiveToString(currentRoot.getRight());
        return result;
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    public int getHeight() {
        return recursiveGetHeight(getRoot());
    }

    private int recursiveGetHeight(AVLNode<T> currentRoot) {
        if(!currentRoot.hasLeft() && !currentRoot.hasRight())
            return 0;

        if(currentRoot.hasLeft() && currentRoot.hasRight())
            return maxHeight(recursiveGetHeight(currentRoot.getLeft()), recursiveGetHeight(currentRoot.getRight())) + 1;

        if(currentRoot.hasLeft())
            return recursiveGetHeight(currentRoot.getLeft());

        else
            return recursiveGetHeight(currentRoot.getRight());
    }

    private int maxHeight(int heightLeft, int heightRight) {
        if(heightLeft >= heightRight)
            return heightLeft;
        else
            return heightRight;
    }

}
