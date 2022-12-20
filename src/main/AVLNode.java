package main;

public class AVLNode<T> {

    private T value;
    private AVLNode<T> left, right;
    private int height = 0;
    private AVLNode<T> root;

    public AVLNode(T value) {
        setValue(value);
    }

    public void updateHeight() {
        if(hasLeft())
            getLeft().updateHeight();

        if(hasRight())
            getRight().updateHeight();

        if(!hasLeft() && !hasRight())
            setHeight(0);

        else if(hasLeft() && hasRight()) {
            if(getLeft().getHeight() >= getRight().getHeight())
                setHeight(getLeft().getHeight() + 1);

            else setHeight(getRight().getHeight() + 1);
        }

        else if(hasLeft())
            setHeight(getLeft().getHeight() + 1);

        else setHeight(getRight().getHeight() + 1);
    }

    public int getBF() {
        if(!hasLeft() && !hasRight())
            return 0;

        if(hasRight() && hasLeft()) {
            return getRight().getHeight() - getLeft().getHeight();
        }

        else if(hasRight()) {
            return getRight().getHeight() + 1;
        }

        else {
            return -getLeft().getHeight() - 1;
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
        if(left != null)
            left.setRoot(this);
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
        if(right != null)
            right.setRoot(this);
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

    public AVLNode<T> getRoot() {
        return root;
    }

    public void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    public boolean hasRoot() {
        return getRoot() != null;
    }

    @Override
    public String toString() {
        return getValue().toString() + '(' + getBF() + ')' /*+ '(' + getHeight() + ')'*/;
    }
}
