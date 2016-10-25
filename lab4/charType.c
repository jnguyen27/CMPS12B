/*
 * Duy Nguyen
 * w1475318
 * CS 12M lab4
 * 10/24/16
 * charType.c
 * Takes two command line arguments giving the input and output
 * files names respectively, then classifies the characters on
 * each line of the input file into different categories
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

/* function prototype */
void extract_chars(char* s, char* a, char* d, char* p, char* w);

/* function main which takes command line arguments */
int main(int argc, char* argv[]){
    FILE* in; /* file handle for input */
    FILE* out; /* file handle for output */
    char* line;
    char* letters;
    char* numbers;
    char* punctuation;
    char* whitespace;
    int lineNum = 1;

    /* check number of arguments on the command line */
    if(argc!=3){
	printf("Usage: %s input-file output-file\n", argv[0]);
	exit(EXIT_FAILURE);
    }

    /* open input file for reading */
    if((in = fopen(argv[1], "r"))==NULL){
	printf("Unable to read from file %s\n", argv[1]);
	exit(EXIT_FAILURE);
    }

    /* open output file for writing */
    if((out = fopen(argv[2], "w"))==NULL){
	printf("Unable to write to file %s\n", argv[2]);
	exit(EXIT_FAILURE);
    }

    /* allocate the char* variables on the heap */
    line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    letters = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    numbers = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    punctuation = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    whitespace = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    assert((line!=NULL) && (letters!=NULL) & (numbers!=NULL) && (punctuation!=NULL) && (whitespace!=NULL));

    /* read each line in input file, extract alpha-numeric characters */
    while(fgets(line, MAX_STRING_LENGTH, in)!=NULL){
	extract_chars(line, letters, numbers, punctuation, whitespace);
	fprintf(out, "line %d contains: \n", lineNum);
	/* letters */
	if(strlen(letters)>1){
	    fprintf(out, "%d alphabetic characters: %s \n", (int)strlen(letters), letters);
	}else{
	    fprintf(out, "%d alphabetic character: %s \n", (int)strlen(letters), letters);
	}
	/* numbers */
	if(strlen(numbers)>1){
	    fprintf(out, "%d numeric characters: %s \n", (int)strlen(numbers), numbers);
	}else{
	    fprintf(out, "%d number character: %s \n", (int)strlen(numbers), numbers);
	}
	/* punctuation */
	if(strlen(punctuation)>1){
	    fprintf(out, "%d punctuation characters: %s \n", (int)strlen(punctuation), punctuation);
	}else{
	    fprintf(out, "%d punctuation character: %s \n", (int)strlen(punctuation), punctuation);
	}
	/* whitespace */
	if(strlen(whitespace)>1){
	    fprintf(out, "%d whitespace characters: %s \n", (int)strlen(whitespace), whitespace);
	}else{
	    fprintf(out, "%d whitespace character: %s \n", (int)strlen(whitespace), whitespace);
	}
	lineNum++;
    }

    /* free heap memory */
    free(line);
    free(letters);
    free(numbers);
    free(punctuation);
    free(whitespace);

    /* close input and output files */
    fclose(in);
    fclose(out);

    return(EXIT_SUCCESS);
}

/* function definition */
void extract_chars(char* s, char* a, char* d, char* p, char* w){
    int i=0, j=0, k=0, l=0, m=0;
    while(s[i]!='\0' && i<MAX_STRING_LENGTH){
	if(isalpha((int)s[i])){
	    a[j] = s[i];
	    j++;
	} else if(isdigit((int)s[i])){
	    d[k] = s[i];
	    k++;
	}else if(ispunct((int)s[i])){
	    p[l] = s[i];
	    l++;
	}else{
	    w[m] = s[i];
	    m++;
	}
	i++;
    }
    a[j] = '\0';
    d[k] = '\0';
    p[l] = '\0';
    w[m] = '\0';
}
