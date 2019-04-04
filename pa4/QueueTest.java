// --------------------------------
// Duy Nguyen
// duminguy (W1475318)
// CS 12B pa4
// 11/14/16
// Queue.java
// Queue ADT
// --------------------------------

public class QueueTest{
    public static void main(String[] args){
        Queue A = new Queue();

	System.out.println(A.isEmpty());
   	A.enqueue((int)1);
    	A.enqueue((int)2);
    	A.enqueue((int)3);
    	A.enqueue((int)4);
    	A.enqueue((int)5);

	A.toString();
    	System.out.println(A.isEmpty());
    	System.out.println("Length of A is: "+A.length());
    	System.out.println("Head of A is: "+A.peek());

    	A.dequeue();

	System.out.println(A.isEmpty());
	A.toString();
    	System.out.println("Length of A is now: "+A.length());
	System.out.println("Head of A is now: "+A.peek());

    	A.dequeueAll();
    	System.out.println(A.isEmpty());
   	System.out.println("Length of A is now "+A.length());
    	try{
	    System.out.println("Head of A is: "+A.peek());
   	}catch(QueueEmptyException e){
	    System.out.println("Caught Exception "+e);
	    System.out.println("Continuing without interruption");
    	}
    }
}
