// ----------------------------------------------------------------
// Duy Nguyen
// duminguy
// CS 12M lab7
// Dictionary.java
// Binary Search Tree implementation of Dictionary ADT
// -----------------------------------------------------------------

public class Dictionary implements DictionaryInterface{

    // private inner Node class
    private class Node{
        String key;
        String value;
        Node left;
        Node right;

        Node(String key, String value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    // Fields for Dictionary class
    private Node root;     // reference to the root Node in the BST
    private int numItems;  // number of items in this BST

    // Dictionary()
    // constructor for the Dictionary class
    public Dictionary(){
        root = null;
        numItems = 0;
    }

    // private types and functions -----------------------------------------

    //findKey()
    // returns the Node containing key k in the subtree rooted at R
    // or returns null if no such Node exists
    private Node findKey(Node R, String key){
        if(R==null || key.compareTo(R.key)==0){
            return R;
        }else if(key.compareTo(R.key)<0){
            return findKey(R.left, key);
        }else{ // (key.compareTo(R.key)>0)
            return findKey(R.right, key);
        }
    }

    // findParent()
    // returns the parent of N in the subtree rooted at R, or returns
    // null if N is equal to R. (pre: R != null)  
    private Node findParent(Node N, Node R){
        Node P = null;
        if(N!=R){
            P = R;
            while(P.left!=N && P.right!=N){
                if(N.key.compareTo(P.key)<0){
                    P = P.left;
                }else{
                    P = P.right;
                }
            }
        }
        return P;
    }

    // findLeftmost()
    // returns the leftmost Node in the subtree rooted at R, or null if
    // R is null
    private Node findLeftmost(Node R){
        Node L = R;
        if(L!=null){
            for( ; L.left!=null; L=L.left){
                return L;
            }
        }
        return L;
    }

    // printInOrder()
    // prints the (key, value) pairs belonging to the subtree rooted at R
    // in order of increasing keys to file pointed to by out
    private void printInOrder(Node R){
        if(R!=null){
            printInOrder(R.left);
            System.out.println(R.key + " " + R.value);
            printInOrder(R.right);
        }
    }

    // deleteAll()
    // deletes all Nodes in the subtree rooted at N.
    private void deleteAll(Node N){
        if(N!=null){
            deleteAll(N.left);
            deleteAll(N.right);
        }
    }


    // ADT Operations ---------------------------------------------------

    // isEmpty()
    // returns true if the tree is empty, false otherwise
    // pre: none
    
    public boolean isEmpty(){
        return (numItems ==0);
    }

    // size()
    // returns the number of (key, value) pairs
    // pre: none
   
    public int size(){
        return numItems;
    }

    // lookup()
    // returns the value such that (key, value) is in the dictionary,
    // or returns null if no such value exists
 
    public String lookup(String key){
        Node N = findKey(root, key);
        if(N!=null){
            return N.value;
        }else{
            return null;
        }
    }

    // insert()
    // inserts new (key, value) pair into the dictionary
    // pre: lookup(key)== null
  
    public void insert(String key, String value) throws DuplicateKeyException{
        Node N, A, B;
        if(lookup(key) != null){
            throw new DuplicateKeyException("cannot insert duplicate key");
        }else{
            N = new Node(key, value);
            B = null;
            A = root;
            while(A!=null){
                B = A;
                if(key.compareTo(A.key)<0){
                    A = A.left;
                }else{
                    A = A.right;
                }
            }                                                                                                   
            if(B==null){
                root = N;
            }else if(key.compareTo(B.key)<0){
                B.left = N;
            }else{
                B.right = N;
            }
        }
        numItems++;
    }

    // delete()
    // deletes a pair with the key k
    // pre: lookup(key) != null
    public void delete(String key) throws KeyNotFoundException{
        Node N, P, S;
        if(lookup(key) == null){
            throw new KeyNotFoundException("cannot delete a non-existnt key");
        }else{
            N = findKey(root, key);
            if(N.left==null && N.right==null){ // case 1 (no children)
                if(N==root){
                    root = null;
                }else{
                    P = findParent(N, root);
                    if(P.right==N){
                        P.right = null;
                    }else{
                        P.left = null;
                    }
                }
            }else if(N.right==null){ // case 2 (left but on right child)
                if(N==root){
                    root = N.left;
                }else{
                    P = findParent(N, root);
                    if(P.right==N){
                        P.right = N.right;
                    }else{
                        P.left = N.right;
                    }
                }
            }else{  // case 3 (two children: N.left!=null && N.right!=null)
                S = findLeftmost(N.right);
                N.key = S.key;
                N.value = S.value;
                P = findParent(S, N);
                if(P.right==S){
                    P.right = S.right;
                }else{
                    P.left = S.right;
                }
            }
        }
        numItems--;
    }

    // makeEmpty()
    // resets the dictionary to the empty state
    // pre: none
    
    public void makeEmpty(){
        deleteAll(root);
        root = null;
        numItems = 0;
    }

    // private recurseive function that does work
    // and is called by toString()
    private String myString(Node N){
        String s = "";
        if(N==null){
            return "";
        }else{
            s = s + myString(N.left);
            s = s + N.key + " " + N.value + "\n";
            s = s + myString(N.right);
        }
        return s;
    }
    
    // toString()
    // overrides object's toString method
    public String toString(){
        return myString(root);
    }
    
}

