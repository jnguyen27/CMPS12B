// --------------------------------------
// Duy Nguyen
// W1475318
// CS 12B pa3
// DictionaryTest.java
// Test client for Dictionary class
// --------------------------------------

public class DictionaryTest{

    public static void main(String[] args){
	Dictionary A = new Dictionary();
	
	System.out.println(A.isEmpty());
	System.out.println("The size is: " + A.size());
	A.delete("1");
	A.makeEmpty();
	System.out.println(A.toString());

	A.insert("1", "a");
	System.out.println(A.isEmpty());
	System.out.println("The size is: " + A.size());
	System.out.println(A.lookup("2"));
	System.out.println(A.lookup("1"));
	System.out.println(A.toString());
	A.delete("3");
	A.delete("1");
	A.makeEmpty();
	System.out.println(A.isEmpty());
	System.out.println("The size is: " + A.size());
	
	A.insert("1", "a");
	A.insert("2", "b");
	A.insert("3", "c");
	A.insert("4", "d");
	A.insert("5", "e");
	A.insert("5", "f");
	System.out.println(A.isEmpty());
	System.out.println("The size is: " + A.size());
	System.out.println(A.lookup("3"));
	System.out.println(A.toString());
	A.delete("3");
	System.out.println("The size is: " + A.size());
	System.out.println(A.lookup("3"));
	System.out.println(A.isEmpty());
	A.makeEmpty();
	System.out.println("The size is: " + A.size());
	System.out.println(A.isEmpty());
	
    }

}
