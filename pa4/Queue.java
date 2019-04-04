// --------------------------------
// Duy Nguyen
// duminguy (W1475318)
// CS 12B pa4
// 11/14/16
// Queue.java
// Queue ADT
// --------------------------------

public class Queue implements QueueInterface{

    // private inner Node class
    private class Node{
	Object item;
	Node next;

	Node(Object x){
	    item = x;
	    next = null;
	}
    }

    // Fields for the Queue
    private Node head;    // reference to first Node in Queue
    private int numItems; // number of items in this Queue

    // Queue()
    // constructor for Queue class
    public Queue(){
	head = null;
	numItems = 0;
    }

    // isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
    public boolean isEmpty(){
	return(numItems == 0);
    } 

    // length()
    // pre: none
    // post: returns the length of the Queue
    public int length(){
	return numItems;
    }

    // enqueue()
    // adds x to back of this Queue
    // post: !isEmpty()
    public void enqueue(Object newItem){
	if(numItems == 0){
	    head = new Node(newItem);
	}else{
	    Node N = head;
	    while(N.next!=null){
		N = N.next;
	    }	
	    N.next = new Node(newItem);
	}
	numItems++;
    }

    // dequeue()
    // deltes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    public Object dequeue() throws QueueEmptyException{
	if(numItems == 0){
	    throw new QueueEmptyException("cannot dequeue() empty queue");
	}else{
	    Node N = head;
	    head = N.next;
	    numItems--;
	    return N.item;
	}
    }

    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    public Object peek() throws QueueEmptyException{
	if(numItems == 0){
	    throw new QueueEmptyException("cannot peek() empty queue");
	}else{
	    return head.item;
	}
    }

    // dequeueAll()
    // sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    public void dequeueAll() throws QueueEmptyException{
	if(numItems == 0){
	    throw new QueueEmptyException("cannot dequeueAll() empty queue");
	}else{
	    numItems = 0;
	    head = null;
	}
    }

    // toString
    // overrides Object's toString() method
    public String toString(){
	Node N;
	String s = "";
	for(N=head; N!=null; N=N.next){
	    s = s + N.item + " ";
	}
	return s;
    }
}
