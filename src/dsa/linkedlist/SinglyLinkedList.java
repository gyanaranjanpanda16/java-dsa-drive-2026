package dsa.linkedlist;

import java.util.Arrays;

/** An integer list built from nodes; Java's LinkedList is doubly linked instead. */
public final class SinglyLinkedList {
    private static final class Node {
        final int value;
        Node next;
        Node(int value) { this.value = value; }
    }

    private Node head;
    private Node tail;
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    /** O(1) time and extra space: reconnect only the first link. */
    public void addFirst(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        if (tail == null) tail = node;
        size++;
    }

    /** O(1) time because we keep a tail reference; O(1) new space. */
    public void addLast(int value) {
        Node node = new Node(value);
        if (tail == null) head = node;
        else tail.next = node;
        tail = node;
        size++;
    }

    /** Removes only the first matching value. O(n) time, O(1) extra space. */
    public boolean removeFirstOccurrence(int value) {
        Node previous = null;
        Node current = head;
        while (current != null && current.value != value) {
            previous = current;
            current = current.next;
        }
        if (current == null) return false;
        if (previous == null) head = current.next;
        else previous.next = current.next;
        if (current == tail) tail = previous;
        size--;
        return true;
    }

    /** O(n) time: unlike an array, nodes cannot be accessed by index in O(1). */
    public boolean contains(int value) {
        for (Node node = head; node != null; node = node.next) {
            if (node.value == value) return true;
        }
        return false;
    }

    /**
     * Reverses links in O(n) time and O(1) extra space.
     * Invariant: previous heads the reversed prefix; current heads untouched nodes.
     */
    public void reverse() {
        Node previous = null;
        Node current = head;
        tail = head; // The old first node becomes the new last node.
        while (current != null) {
            Node next = current.next; // Save the remaining chain before overwriting.
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    /** O(n) time and O(n) output space. The returned array is independent. */
    public int[] toArray() {
        int[] result = new int[size];
        int index = 0;
        for (Node node = head; node != null; node = node.next) result[index++] = node.value;
        return result;
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addFirst(5);
        System.out.println("Before: " + Arrays.toString(list.toArray()));
        list.reverse();
        System.out.println("Reversed: " + Arrays.toString(list.toArray()));
        list.removeFirstOccurrence(10);
        System.out.println("Remove 10: " + Arrays.toString(list.toArray()));
    }
}
