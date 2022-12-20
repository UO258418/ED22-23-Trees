package main;

public class BSTree<T extends Comparable<T>> {

    private BSTNode<T> root;

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    private int numberOfNodes = 0;

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
            numberOfNodes += 1;
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
                numberOfNodes += 1;
            }
            else recursiveAdd(currentRoot.getLeft(), value);
        } else {
            if(currentRoot.getRight() == null) {
                BSTNode<T> node = new BSTNode<>(value);
                node.setRoot(currentRoot);
                currentRoot.setRight(node);
                node.updateHeight();
                numberOfNodes += 1;
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

    private BSTNode<T> getLowestChild(BSTNode<T> currentRoot) throws NullPointerException {
        if(currentRoot == null)
            throw new NullPointerException("The node cannot be null");

        if(!currentRoot.hasLeft() && !currentRoot.hasRight())
            return null;

        if(currentRoot.hasLeft())
            return currentRoot.getLeft();

        return currentRoot.getRight();
    }

    public void remove(T element) {
        if(getRoot() == null)
            return;

        recursiveRemove(getRoot(), element);
    }

    private void recursiveRemove(BSTNode<T> currentRoot, T element) {
        if(currentRoot.getValue().equals(element)) {
            BSTNode<T> parent = currentRoot.getRoot();
            BSTNode<T> newChild = getLowestChild(currentRoot);

            if(currentRoot.equals(parent.getLeft())) {
                parent.setLeft(newChild);
            }

            else {
                parent.setRight(newChild);
            }

            if(newChild != null) {
                newChild.setRoot(parent);
                if(newChild.equals(currentRoot.getLeft())) {
                    newChild.setRight(currentRoot.getRight());
                    if(currentRoot.hasRight())
                        currentRoot.getRight().setRoot(newChild);
                }
                newChild.updateHeight();
            }

            numberOfNodes -= 1;
        }

        else if(element.compareTo(currentRoot.getValue()) < 0 && currentRoot.getLeft() != null) {
            recursiveRemove(currentRoot.getLeft(), element);
        }

        else if(element.compareTo(currentRoot.getValue()) > 0 && currentRoot.getRight() != null){
            recursiveRemove(currentRoot.getRight(), element);
        }
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

    public double incompleteRatio() {
        if(root == null)
            throw new IllegalStateException("Tree cannot be empty");

        return recursiveIncompleteRatio(getRoot()) / (double)numberOfNodes;
    }

    private int recursiveIncompleteRatio(BSTNode<T> currentRoot) {
        if(!currentRoot.hasLeft() && !currentRoot.hasRight()) {
            return 1;
        }

        if(currentRoot.hasLeft() && currentRoot.hasRight()) {
            return recursiveIncompleteRatio(currentRoot.getLeft()) + recursiveIncompleteRatio(currentRoot.getRight());
        }

        if(currentRoot.hasLeft()) {
            return recursiveIncompleteRatio(currentRoot.getLeft()) + 1;
        }

        else {
            return recursiveIncompleteRatio(currentRoot.getRight()) + 1;
        }
    }

}
