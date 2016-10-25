# -------------------------
#  Duy Nguyen
#  CS 12M lab4
#  10/24/16
#  Makefile for charType.c
#  ------------------------

FLAGS	= -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN  = charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check :
	valgrind --leak-check=full charType infile outfile
