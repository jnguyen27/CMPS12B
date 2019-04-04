// ----------------------------------------
// Duy Nguyen
// W1475318
// CS 12M
// CommanLineArguments.java
// ----------------------------------------
class CommandLineArguments{
    public static void main(String[] args) {
        int n = args.length;
	System.out.println("args.length = " + n);
	for (int i = 0; i < n; i++) System.out.println(args[i]);
    }
}
