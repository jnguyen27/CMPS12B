// -------------------------------------------------------------
// Duy Nguyen
// duminguy (W1475318)
// CS 12M lab6
// 11/13/16
// ListTest.java
// Test file for List.java
// -------------------------------------------------------------

public class ListTest{

    public static void main(String[] args){
	List<String> A = new List<String>();
	List<String> B = new List<String>();
	List<List<String>> C = new List<List<String>>;

	A.add(1, "a");
	A.add(2, "b");
	B.add(1, "c");
	B.add(2, "d");
	C.add(1, A);
	C.add(2, B);

	System.out.println("A: "+A);
	System.out.println("B: "+B);
	System.out.println("C: "+C);

	System.out.println("A.size() is " + A.size());
	System.out.println("B.size() is " + B.size());
	System.out.println("C.size() is " + C.size());

	System.out.println("A.get(1) is " + A.get(1));
	System.out.println("B.get(1) is " + B.get(2));
	System.out.println("C.get(1) is " + C.get(1));

	System.out.println("A.equals(A) is " + A.equals(A));
	System.out.println("A.equals(B) is " + A.equals(B));
	System.out.println("A.equals(C) is " + A.equals(C));

	A.remove(1);
	B.remove(2);

	System.out.println("A.size() is " + A.size());
	System.out.println("B.size() is " + B.size());
	System.out.println("A.get(1) is " + A.get(1));
	System.out.println("B.get(1) is " + B.get(1));

	try{
	    System.out.println(A.get(200));
	}catch(ListIndexOutOfBoundsException e){
	    System.out.println("Caught Exception: ");
	    System.out.println(e);
	    System.out.println("Continuing without interruption");
	}

	C.removeAll();

	System.out.println("The size of C is " + C.size();
	
    }

}
