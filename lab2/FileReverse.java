// ----------------------------------------------------------------------
// Duy Nguyen
// W1475318
// CS 12M lab2
// 10/04/16
// FileReverse.java
// Takes two command line arguments, reading lines of input and printing
// each token backwards to the output file on a line by itself
// -----------------------------------------------------------------------
import java.io.*;
import java.util.Scanner;

class FileReverse{
    public static void main(String[] args) throws IOException{

	int lineNumber = 0;

	// check number of command line arguments is at least 2
        if(args.length < 2){
	    System.out.println("Usage: FileCopy <input file> <output file>");
	    System.exit(1);
        }

	// open files
	Scanner in = new Scanner(new File(args[0]));
	PrintWriter out = new PrintWriter(new FileWriter(args[1]));

	// read lines from in, extract and pring tokens from each line
	while(in.hasNextLine()){
	    lineNumber++;

	    // trim leading and trailing spaces, then add one trailing space so
	    // split works on blank lines
	    String line = in.nextLine().trim() + " ";

	    // split line around white space
	    String[] token = line.split("\\s+");

	    // print out tokens
	    int n = token.length;
	    for(int i = 0; i < n; i++){
	        out.println(stringReverse(token[i], token[i].length()));
	    }
	
        }

        // close files
        in.close();
	out.close();

    }
    public static String stringReverse(String s, int n){
        if(s.length() == 1){
	    return s;
        } else{
	    return stringReverse(s.substring(1), n-1) + s.charAt(0);
	}
    }
}
