/*
 * Duy Nguyen
 * W1475318
 * CS 12M lab3
 * 10/17/16
 * FileReverse.c
 * Reads the input file and prints each segment in reverse on a separate line
 * in the output file
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void stringReverse(char* s){
    int i=0, temp, j=strlen(s)-1;
    for(i=0; i<j; i++){
	temp = s[i];
	s[i] = s[j];
	s[j] = temp;
	j--;
    }
}

int main(int argc, char* argv[]){
    FILE* in; /* file handle for input */
    FILE* out; /* file handle for output */
    char word[256]; /* char array to store words from the input file */

    /* check command line for corret number of arguments */
    if(argc != 3){
	printf("Usage: %s <input file> <output file>\n", argv[0]);
	exit(EXIT_FAILURE);
    }

    /* open input file for reading */
    in = fopen(argv[1], "r");
    if(in==NULL){
	printf("Unable to read from file %s\n", argv[1]);
	exit(EXIT_FAILURE);
    }

    /* open output file for writing */
    out = fopen(argv[2], "w");
    if(out==NULL){
	printf("Unable to write to file %s\n", argv[2]);
	exit(EXIT_FAILURE);
    }

    /* read words from input file, print on separate lines to output file */
    while(fscanf(in, " %s", word) != EOF){
	stringReverse(word);
	fprintf(out, "%s\n", word);
    }

    /* close input and output files */
    fclose(in);
    fclose(out);

    return(EXIT_SUCCESS);
}
