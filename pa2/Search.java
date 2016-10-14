// -----------------------------------------------------------------------
// Duy Nguyen
// W1475318
// CS 12B pa2
// Search.java
// Implementing Binary Search and Merge Sort algorithms for String arrays
// -----------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class Search{
    // main() takes in a file of strings and a target(s) and calls the methods to sort 
    // then search for the target and print whether the target is found or not and
    // where it was found 
    public static void main(String[] args) throws IOException{
        if(args.length<2){
	    System.err.println("Usage: Search file target1 [target2..]");
	    System.exit(1);
	}

	Scanner in = new Scanner(new File(args[0]));
	int lineCount = 0;

	while(in.hasNextLine()){
	    in.nextLine();
	    lineCount++;
	}

	String[] words = new String[lineCount];
	int[] lineNumber = new int[lineCount];

	in = new Scanner(new File(args[0]));
	
	String line = null;	

	for(int i=1; i<=lineNumber.length; i++){
		lineNumber[i-1] = i;
	}

	for(int i=0; in.hasNextLine(); i++){
      	    line = in.nextLine();
	    words[i] = line;
	}

        mergeSort(words, lineNumber, 0, words.length-1);
	
        for(int i=1; i<args.length; i++){
	    System.out.println(binarySearch(words, lineNumber, 0, words.length-1, args[i]));
	}

	in.close();

    }
    // mergeSort(): continuously splits array in half and calls merge to put it back
    // together in order
    static void mergeSort(String[] word, int[] lineNumber, int p, int r){
	int q;
	if(p<r){
	    q = (p+r)/2;
	    mergeSort(word, lineNumber, p, q);
	    mergeSort(word, lineNumber, q+1, r);
	    merge(word, lineNumber, p, q, r);
	}
    }
    // merge(): merges the array of strings back together in order
    static void merge(String[] word, int[] lineNumber, int p, int q, int r){
	int n1 = q-p+1;
	int n2 = r-q;
	String[] left = new String[n1];
	String[] right = new String[n2];
	int[] leftNum = new int[n1];
	int[] rightNum = new int[n2];
	int i, j, k;
	
	for(i=0; i<n1; i++){
	    left[i] = word[p+i];
	    leftNum[i] = lineNumber[p+i];
	}
	for(j=0; j<n2; j++){
	    right[j] = word[q+j+1];
	    rightNum[j] = lineNumber[q+j+1];
	}
	
	i=0; j=0;

	for(k=p; k<=r; k++){
	    if(i<n1 && j<n2){
		if(left[i].compareTo(right[j])<0){
	  	    word[k] = left[i];
		    lineNumber[k] = leftNum[i];
		    i++;
	        }else{
		    word[k] = right[j];
		    lineNumber[k] = rightNum[j];
		    j++;
	 	}
	    }else if(i<n1){
		word[k] = left[i];
		lineNumber[k] = leftNum[i];
		i++;
	    }else{
		word[k] = right[j];
		lineNumber[k] = rightNum[j];
		j++;
	    }
	}
    }	
    // binarySearch() splits the array in half and searcesh both sides and returns a message with/without
    // target and where it was found if it was found
    public static String binarySearch(String[] word, int[] lineNumber, int p, int r, String target){   
	int q = 0;
	if(p>r){
	    return target+" not found";
	}else{
	    q = (p+r)/2;
	    if(word[q].compareTo(target)==0){
		return target+" found on line "+lineNumber[q];
	    }else if(word[q].compareTo(target)>0){
		return binarySearch(word, lineNumber, p, q-1, target);
	    }else{
		return binarySearch(word, lineNumber, q+1, r, target);
	    }
	}
    }
}
