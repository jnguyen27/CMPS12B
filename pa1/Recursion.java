// Duy Nguyen and Mrinal Chitithoti
// W1475318
// CS 12B pa1
// 09-28-16
// Recursion.java
// Program that recursively prints out an arra in 3 different ways and finds the max and min element in the array
class Recursion{
    // places leftmost n elements of X[] into rightmost n positions in Y[] in
    // reverse order
    static void reverseArray1(int[] X, int n, int[] Y){
	if(n==0){
	    return;
	}else if (n>0){
	    Y[Y.length-n] = X[n-1];
	    reverseArray1(X, n-1, Y);
	}
    }
    // places rightmost n elements of X[] into leftmost n positions in Y[] in
    // reverse order
    static void reverseArray2(int[] X, int n, int[] Y){
	if(n==0){
	    return;
	}else if (n>0){
	    Y[n-1] = X[X.length-n];
	    reverseArray2(X, n-1, Y);
	}
    }
    // reverse the subarray at index i and j (X[i...j])
    static void reverseArray3(int[]X, int i, int j){
	if(i>=j){
	    return;
	}else{
	    int temp = X[i];
	    X[i] = X[j];
	    X[j] = temp;
	    reverseArray3(X, i+1, j-1);
	}
    }
    // returns the index that contains the largest value in the array
    static int maxArrayIndex(int[] X, int p, int r){
	int max = 0;
	if(p<r){
	    max = (p+r)/2;
	    int left = maxArrayIndex(X, p, max);
	    int right = maxArrayIndex(X, max+1, r);
	    if(X[left]>X[right]){
		max = left;
	    }else if (X[left]<X[right]){
		max = right;
	    }
	}
	return max;
    }
    // returns the index that contains the smallest value in the array
    static int minArrayIndex(int[] X, int p, int r) {
        int min = 0;
	if(p<r){
	    min = (p+r)/2;
	    int left = minArrayIndex(X, p, min);
	    int right = minArrayIndex(X, min+1, r);
	    if(X[left]>X[right]){
		min = right;
            }else if(X[left]<X[right]){
		min = left;
	    }
        }
	return min;
    }
    public static void main (String[] args) {
    
        int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
	int[] B = new int[A.length];
	int[] C = new int[A.length];
	int minIndex = minArrayIndex(A, 0, A.length-1);
	int maxIndex = maxArrayIndex(A, 0, A.length-1);

	for(int x: A) System.out.print(x+" ");
	System.out.println();

	System.out.println("minIndex = " + minIndex);
	System.out.println("maxIndex = " + maxIndex);

	reverseArray1(A, A.length, B);
	for(int x: B) System.out.print(x+" ");
	System.out.println();

	reverseArray2(A, A.length, C);
	for(int x: C) System.out.print(x+" ");
	System.out.println();

	reverseArray3(A,0, A.length-1);
	for(int x: A) System.out.print(x+" ");
	System.out.println();

    }
}
