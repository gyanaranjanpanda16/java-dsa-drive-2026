package dsa.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** An UNBALANCED integer set. Equal keys are ignored, never stored twice. */
public final class BinarySearchTree {
    private static final class Node {
        int key;
        Node left;
        Node right;
        Node(int key) { this.key = key; }
    }

    private Node root;
    private int size;

    public int size() { return size; }

    /** O(h) time, O(1) working space; h may be n-1 in a skewed tree. */
    public boolean insert(int key) {
        if (root == null) {
            root = new Node(key);
            size++;
            return true;
        }
        Node node = root;
        while (true) {
            if (key == node.key) return false;
            if (key < node.key) {
                if (node.left == null) { node.left = new Node(key); break; }
                node = node.left;
            } else {
                if (node.right == null) { node.right = new Node(key); break; }
                node = node.right;
            }
        }
        size++;
        return true;
    }

    /** O(h) time, O(1) space. The ordering invariant excludes one entire subtree. */
    public boolean contains(int key) {
        Node node = root;
        while (node != null) {
            if (key == node.key) return true;
            node = key < node.key ? node.left : node.right;
        }
        return false;
    }

    /**
     * O(h) time, O(1) extra space. For two children, replace the key with its
     * inorder successor, then unlink that successor (which has no left child).
     */
    public boolean delete(int key) {
        Node parent = null;
        Node current = root;
        while (current != null && current.key != key) {
            parent = current;
            current = key < current.key ? current.left : current.right;
        }
        if (current == null) return false;
        if (current.left != null && current.right != null) {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            current.key = successor.key;
            parent = successorParent;
            current = successor;
        }
        Node child = current.left != null ? current.left : current.right;
        if (parent == null) root = child;
        else if (parent.left == current) parent.left = child;
        else parent.right = child;
        size--;
        return true;
    }

    /** O(n) time, O(h) working space plus O(n) output; returns keys in order. */
    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.key);
            current = current.right;
        }
        return result;
    }

    /** Height in edges: empty = -1, one node = 0. O(n) time, O(max width) space. */
    public int height() {
        if (root == null) return -1;
        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);
        int height = -1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.removeFirst();
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
            }
            height++;
        }
        return height;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int key : new int[]{8, 3, 10, 1, 6, 14, 4, 7, 13}) tree.insert(key);
        System.out.println("Sorted keys: " + tree.inorder());
        tree.delete(8);
        System.out.println("After deleting root: " + tree.inorder());
        BinarySearchTree skewed = new BinarySearchTree();
        for (int key = 1; key <= 5; key++) skewed.insert(key);
        System.out.println("Five sorted inserts produce height: " + skewed.height());
    }
}
