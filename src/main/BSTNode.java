package main;

import org.junit.platform.engine.support.hierarchical.Node;

public class BSTNode<T> {

    private T value;
    private BSTNode<T> left, right;
    private int height = 0;
    private BSTNode<T> root;

    public BSTNode(T value) {
        setValue(value);
    }

    public void updateHeight() {
        if(!hasLeft() && !hasRight())
            setHeight(0);

        else if(hasLeft() && hasRight()) {
            if(getLeft().getHeight() == getRight().getHeight() || getLeft().getHeight() > getRight().getHeight())
                setHeight(getLeft().getHeight() + 1);

            else setHeight(getRight().getHeight() + 1);
        }

        else if(hasLeft() && !hasRight())
            setHeight(getLeft().getHeight() + 1);

        else setHeight(getRight().getHeight() + 1);

        if(hasRoot())
            getRoot().updateHeight();

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    public boolean hasRight() {
        return getRight() != null;
    }

    public boolean hasLeft() {
        return getLeft() != null;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BSTNode<T> getRoot() {
        return root;
    }

    public void setRoot(BSTNode<T> root) {
        this.root = root;
    }

    public boolean hasRoot() {
        return getRoot() != null;
    }

}
