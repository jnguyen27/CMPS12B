// -------------------------------------------
// Duy Nguyen
// duminguy
// CS 12B pa5
// DictionaryTest.c
// Test client for Dictionary class
// -------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include "Dictionary.h"

#define MAX_LEN 180

int main(void){
    Dictionary A = newDictionary();
    
    insert(A, 1, 10);
    insert(A, 2, 20);
    insert(A, 3, 30);
    insert(A, 4, 40);
    insert(A, 5, 50);
    
    printDictionary(stdout, A);
    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", size(A));
    char* v = lookup(A, 3);
    printf("key=\"%s\n", (v==NULL?"not found ":"value="), v);
    
    delete(A, 2);
    delete(A, 4);
    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", size(A));
    char* v = lookup(A, 3);
    printf("key=\"%s\n", (v==NULL?"not found ":"value="), v);
    
    makeEmpty(A);
    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", size(A));
    char* v = lookup(A, 3);
    printf("key=\"%s\n", (v==NULL?"not found ":"value="), v);

    freeDictionary(&A);
    
}
