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

	System.out.println("The size of A is " + A.size());
	System.out.println("The size of B is " + B.size());
	System.out.println("The size of C is " + C.size());

	System.out.println("The value at index 1 of A is " + A.get(1));
	System.out.println("The value at index 2 of B is " + B.get(2));
	System.out.println("The value at index 1 of C is " + A.get(1));

	System.out.println("A.equals(A) is " + A.equals(A));
	System.out.println("A.equals(B) is " + A.equals(B));
	System.out.println("A.equals(C) is " + A.equals(C));

	A.remove(1);
	B.remove(2);

	System.out.println("The size of A is " + A.size());
	System.out.println("The size of B is " + B.size());
	System.out.println("The value at index 1 of A is " + A.get(1));
	System.out.println("THe value at index 2 of B is " + B.get(2));

	C.removeAll();

	System.out.println("The size of C is " + C.size();
	
    }

}
