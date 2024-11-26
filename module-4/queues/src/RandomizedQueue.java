/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private class ArrayIterator implements Iterator<Item> {

        private Item[] arr;
        private int current;

        public ArrayIterator() {
            this.arr = (Item[]) new Object[size()];

            // create a copy of the queue (not including null indexes)
            for (int i = 0; i < this.arr.length; i++) {
                arr[i] = queue[i];
            }

            // shuffle the array
            StdRandom.shuffle(this.arr);

            this.current = 0;
        }

        public boolean hasNext() {
            return current < arr.length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no more elements to return.");
            }

            return arr[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException("The invoked operation is unsupported.");
        }

    }

    private Item[] queue;
    private int tail;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queue = (Item[]) new Object[16];
        this.tail = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.tail == 0;
    }

    // return number of items present in the randomized queue
    public int size() {
        return this.tail;
    }

    // add the item
    public void enqueue(Item item) {
        this.validateArgument(item);

        this.queue[this.tail++] = item; // insert item at tail index, then increment tail

        // resize accordingly
        if (this.tail == this.queue.length) {
            this.resize(this.queue.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        this.validateNotEmpty();

        int i = getRandIndex();
        this.swapWithEnd(i);

        Item item = this.queue[--this.tail]; // decrement tail, then obtain item at tail index
        this.queue[this.tail] = null; // remove item at tail index

        // resize accordingly
        if (this.tail > 0 && (double) this.tail == this.queue.length * 0.25) {
            this.resize(this.queue.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        this.validateNotEmpty();
        return this.queue[getRandIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
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
            throw new NoSuchElementException("Queue is empty.");
        }
    }

    // return a random index between 0 and tail (exclusive)
    private int getRandIndex() {
        return StdRandom.uniformInt(this.tail);
    }

    // swap item at supplied index with item at last non-null index
    private void swapWithEnd(int i) {
        Item item = this.queue[i];
        this.queue[i] = this.queue[this.tail - 1];
        this.queue[this.tail - 1] = item;
    }

    // resize array to length of supplied capacity
    private void resize(int capacity) {
        Item[] arr = (Item[]) new Object[capacity];

        for (int i = 0; i < this.tail; i++) {
            arr[i] = this.queue[i];
        }

        this.queue = arr;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
