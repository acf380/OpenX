package org.example;

public class Node {
    private Integer value = null;
    private Node left = null;
    private Node right = null;

    public Node(int val, Node l, Node r) {
        this.value = val;
        this.left = l;
        this.right = r;
    }

    public void setLeftChild(Node l) { this.left = l; }

    public void setRightChild(Node r) { this.right = r; }

    public Node getLeftChild() {
        return this.left;
    }

    public Node getRightChild() {
        return this.right;
    }

    public int getNodeValue() {
        return this.value;
    }

}
