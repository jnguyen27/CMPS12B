# ---------------------------------------
#  Duy Nguyen
#  CS 12M lab3
#  10/17/16
# Makefile for ReverseFile.c with macros
# ---------------------------------------

FLAGS	= -std=c99 -Wall
SOURCES = FileReverse.c
OBJECTS = FileReverse.o
EXEBIN	= FileReverse

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)
