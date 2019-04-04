public class Dictionary implements DictionaryInterface{
    // private inner Node class
    private class Node{
	String key;
	String value;
	Node next;

	Node(String x, String y){
	    key = x;
	    value = y;
	    next = null;
	}
    }
    // fields for the Dictionary class
    private Node head; 
    private int numItems;
    
    // constructor for the Dictionary class
    public Dictionary(){
	head = null;
	numItems = 0;
    }

    // private helper function
    // returns a reference to the Node at position key
    private Node findKey(String key){
	Node N = head;
	while(N != null){
	    if(N.key.equals(key)){
		return N;
	    }
	    N = N.next;
	}
	return null;
    }

    // returns true of the Dictionary is empty
    public boolean isEmpty(){
	return (numItems == 0);
    }

    // returns the number of elements in the Dictionary
    public int size(){
	return numItems;
    }

    // looks up the value at a certain key in the Dictionary
    public String lookup(String key){
	Node N = findKey(key);
	if(N != null){
		return N.value;
	    }else{
		return null;
	    }
    }

    // adds a certain value at a key
    public void insert(String key, String value) throws DuplicateKeyException{
	if(lookup(key) != null){
	    throw new DuplicateKeyException("cannot insert duplicate keys");
	}else{
  	    if(head == null){ //(numItems == 0);
		Node N = new Node(key, value);
		N.next = head;
	        head = N;
	    }else{
	      	Node N = new Node(key, value);
		N.next = head;
		head = N;
	    }
	    numItems++;
        }
    }

    // deletes a key
    public void delete(String key) throws KeyNotFoundException{
	if(lookup(key) == null){
	    throw new KeyNotFoundException("cannot delete non-existent key");
	}else{
  	    Node N = head;
 	    Node M = findKey(key);
	    if(numItems == 1){
	        head = head.next;
	        N.next = null;
	    }else if (N.key == M.key){
                head = N.next;
            }else{
                while(N.next.key != M.key){
		N = N.next;
	        }
	        Node T = N.next.next;
	        N.next = T;
	    }
	    numItems--;
        }
    }

    // makes empty
    public void makeEmpty(){
	head = null;
	numItems = 0;
    }

    // prints current state to stdout
    public String toString(){
	String s = "";
	Node N = head;

	while(N != null){
	    s = N.key + " " + N.value + "\n" + s;
	    N = N.next;
	}
	return s;
    }
}
