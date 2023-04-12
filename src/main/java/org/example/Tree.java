package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Math.max;

public class Tree {
    private Node root = null;

    public Tree(List<Integer> values) { // build tree using values array
        if (values.size() == 0 || values.get(0) == null) throw new IllegalArgumentException("Wrong input array!");

        this.root = new Node(values.get(0), null, null);
        int idx = 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(this.root);
        Node current = null;

        while (queue.peek() != null) {
            if (values.get(idx) == null) {
                idx++;
                continue;
            }

            current = queue.poll();

            // left child
            if (2 * idx + 1 < values.size() && values.get(2 * idx + 1) != null) {
                current.setLeftChild(new Node(values.get(2 * idx + 1), null, null));
                queue.add(current.getLeftChild());
            }

            // right child
            if (2 * idx + 2 < values.size() && values.get(2 * idx + 2) != null) {
                current.setRightChild(new Node(values.get(2 * idx + 2), null, null));
                queue.add(current.getRightChild());
            }

            idx++;
        }
    }

    public Tree(Node root) { // just pass root node
        if (root == null) throw new IllegalArgumentException("Root can't be null!");

        this.root = root;
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNumberOfNodesWithoutChild() {
        if (this.root == null) return -1;
        return numberOfNodesWithoutChild(this.root);
    }

    public int getMaxDistance() {
        if (this.root == null) return -1;
        return maxDistance(this.root);
    }

    public boolean equal(Tree tree) {
        if (this.root == null || tree.getRoot() == null) return false;
        return checkIfEqual(this.root, tree.getRoot());
    }

    private int numberOfNodesWithoutChild(Node node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) return 1;

        int ret = 0;
        if (node.getRightChild() != null) ret += numberOfNodesWithoutChild(node.getRightChild());
        if (node.getLeftChild() != null) ret += numberOfNodesWithoutChild(node.getLeftChild());

        return ret;
    }

    private int maxDistance(Node node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) return 0;

        int l = 0, r = 0;
        if (node.getRightChild() != null) l = maxDistance(node.getRightChild());
        if (node.getLeftChild() != null) r = maxDistance(node.getLeftChild());

        return 1 + max(l, r);
    }

    private boolean checkIfEqual(Node n1, Node n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null || n2 == null) return false;
        if (n1.getNodeValue() != n2.getNodeValue()) return false;


        boolean ret = checkIfEqual(n1.getLeftChild(), n2.getLeftChild());
        ret = ret && checkIfEqual(n1.getRightChild(), n2.getRightChild());

        return ret;
    }

}
