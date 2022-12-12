
//Title: Queue Implementation Class
//Author: Ahmet Berat Akdoðan, Baþar Aslan
//Section: 1
//Assignment: 5
//Description: This class defines a queue implementation to contribute the trie implementation in Trie class.
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Queue<Item> implements Iterable<Item> {
	
	//Variable Declarations: first and last variables type Node and elementNum type int to keep number of elements in the Queue.
    private Node<Item> first;    
    private Node<Item> last;     
    private int elementNum;             

    //Inner class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

   
  
    public boolean isEmpty() {
    	
    	// Precondition: Checks the queue if it is empty or not.
    	// Postcondition: Returns true if and only if queue is empty.
        return first == null;
    }

   
    public int size() {
    	// Precondition: Finds the size of queue.
    	// Postcondition: Returns number of items in the queue. 
        return elementNum;
    }

   
    public Item peek() {
    	// Precondition: Checks the top of the queue.
    	// Postcondition: Returns the element at the front of the queue.
        return first.item;
    }

    
    public void enqueue(Item item) {
    	
    	// Precondition: Saves a link to last node and create a new node for the end.
    	// Postcondition: Insert an item to the end of the list.
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        elementNum++;
    }

    
    public Item dequeue() {
    	// Precondition: Saves item to return and deletes first node.
    	// Postcondition: Remove item from the beginning of the list.
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        elementNum--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }


    public Iterator<Item> iterator()  {
    	//Precondition: Initializes a new LinkedIterator.
    	// Postcondition: Returns LinkedIterator for the first element
        return new LinkedIterator(first);  
    }
    
    public String toString() {
    	
    	//Precondition: Checks a string representation of this queue.
    	// Postcondition: Returns the sequence of items in FIFO order, separated by spaces.
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    //An Iterator
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}

