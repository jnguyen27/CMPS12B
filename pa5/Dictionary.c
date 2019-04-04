// ------------------------------------------------------
// Duy Nguyen
// duminguy
// CS 12B pa5
// Dictionary.c
// Implementing the Dictionary ADT with hashing
// ------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Dictionary.h"

const int tableSize = 101;

// NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v){
    Node N = malloc(sizeof(NodeObj));
    assert(N!=NULL);
    N->key = k;
    N->value = v;
    N->next = NULL;
    return(N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pN){
    if(pN!=NULL && *pN!=NULL){
        free(*pN);
        *pN = NULL;
    }
}

// DictionaryObj
typedef struct DictionaryObj{
    Node* table;
    int numItems;
} DictionaryObj;

// newDictionary()
// constructor for the Dictionary Type
Dictionary newDictionary(){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D!=NULL);
    D->table = calloc(tableSize, sizeof(NodeObj));
    assert(D->table!=NULL);
    D->numItems = 0;
    return D;
}

// freeDictionary()
void freeDictionary(Dictionary* pD){
    if(pD!=NULL && *pD!=NULL){
        makeEmpty(*pD);
        free((*pD)->table);
        free(*pD);
        *pD = NULL;
    }
}

// deleteAll()
// deletes all Nodes
void deleteAll(Node N){
    if(N!=NULL){
        deleteAll(N->next);
        freeNode(&N);
        //N = NULL;
    }
}

// rotate_left()
// rotate the bits in an an unsigned int
unsigned int rotate_left(unsigned int value, int shift){
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if(shift == 0){
        return value;
    }
    return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input){
    unsigned int result = 0xBAE86554;
    while(*input){
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

// findKey()
// returns the Node containing key k in the dictionary, or
// returns NULL if no such Node exists
Node findKey(Dictionary D, char* k){
    Node N;
    N = D->table[hash(k)];
    while(N!=NULL){
        if(strcmp(N->key, k)==0){
            break;
        }
        N = N->next;
    }
    return N;
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
    if(D==NULL){
        fprintf(stderr,
                "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return (D->numItems==0);
}

// size()
// returns the numbers of (key, value) pairs in D
// pre: none
int size(Dictionary D){
    if(D==NULL){
        fprintf(stderr,
                "Dictionary Error: calling size() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return(D->numItems);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists
// pre: none
char* lookup(Dictionary D, char* k){
    Node N;
    if(D==NULL){
        fprintf(stderr,
                "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    N = findKey(D, k);
    return (N==NULL ? NULL : N->value);
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    Node N;
    if(D==NULL){
        fprintf(stderr,
                "Dictionary Error: calling insert() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if(findKey(D, k)!=NULL){
        fprintf(stderr,
                "Dictionary Error: cannot insert() duplicate key: \"%s\"\n", k);
        exit(EXIT_FAILURE);
    }
    if(D->table[hash(k)]==NULL){
        D->table[hash(k)] = newNode(k, v);
    }else{
        N = newNode(k, v);
        N->next = D->table[hash(k)];
    }
    D->numItems++;
}

// delete()
// deletes a pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
    Node N, M;
    if(D==NULL){
        fprintf(stderr,
                "Dictionary Error: calling delete() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    N = findKey(D, k);
    if(N==NULL){
        fprintf(stderr,
                "Dictionary Error: cannot delete() non-existent key: \"%s\"\n", k);
        exit(EXIT_FAILURE);
    }
    if(N==D->table[hash(k)] && N->next==NULL){
        freeNode(&N);
        N = NULL;
        //freeNode(&N);
    }else if(N==D->table[hash(k)]){
        D->table[hash(k)] = N->next;
        freeNode(&N);
        N = NULL;
        //freeNode(&N);
    }else{
        M = D->table[hash(k)];
        while(M->next!=N){
            M = M->next;
        }
        freeNode(&N);
        M->next = N->next;
        //freeNode(&N);
    }
    D->numItems--;
}

// makeEmpty()
// re-sets D to the empty state
// pre: none
void makeEmpty(Dictionary D){
    Node N;
    int i;
    for(i=0; i<tableSize; i++){
        N = D->table[i];
        deleteAll(N);
    }
    D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file point to by out
void printDictionary(FILE* out, Dictionary D){
    Node N;
    int i;
    if(D==NULL){
        fprintf(stderr,
                "Dictionary Error: calling printDictionary() on NULL"
                " Dictionary reference\n");
        exit(EXIT_FAILURE);
    }else{
        for(i=0; i<tableSize; i++){
            N = D-> table[i];
            for( ; N!=NULL; N = N->next){
                fprintf(out, "%s %s\n", N->key, N->value);
            }
        }
        
    }
}
