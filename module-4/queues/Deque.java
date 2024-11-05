/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {

        private Item item;
        private Node previous;
        private Node next;

        public Node(Item item) {
            this.item = item;
            this.previous = null;
            this.next = null;
        }

    }

    private class ListIterator implements Iterator<Item> {

        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("There are no more elements to return.");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("The invoked operation is unsupported.");
        }

    }

    private Node head;
    private Node tail;
    private int size;

    // construct an empty deque
    public Deque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        this.validateArgument(item);

        // create new node
        Node newNode = new Node(item);

        // modify deque as required
        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            newNode.next = this.head;
            this.head.previous = newNode;
            this.head = newNode;
        }

        this.size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        this.validateArgument(item);

        // create new node
        Node newNode = new Node(item);

        // modify deque as required
        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            newNode.previous = this.tail;
            this.tail.next = newNode;
            this.tail = newNode;
        }

        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        this.validateNotEmpty();

        // obtain item
        Item item = this.head.item;

        // modify deque as required
        if (this.size() == 1) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.head = this.head.next;
            this.head.previous = null;
        }

        this.size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        this.validateNotEmpty();

        // obtain item
        Item item = this.tail.item;

        // modify deque as required
        if (this.size() == 1) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.tail = this.tail.previous;
            this.tail.next = null;
        }

        this.size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // validate that the supplied argument is not null
    private void validateArgument(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Function argument cannot be null.");
        }
    }

    // validate that the list is not empty
    private void validateNotEmpty() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
