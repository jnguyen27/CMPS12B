# ----------------------------------
#  Duy Nguyen
#  W1475318
#  CS 12B pa3
#  Makefile for pa3 using wildcards 
#  and pattern substitution
#  ---------------------------------

JAVAC		= javac
MAINCLASS	= DictionaryClient
JAVASRC		= $(wildcard *.java)
SOURCES		= $(JAVASRC) makefile README
CLASSES		= $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES	= $(patsubst %.class, %*.class, $(CLASSES))
JARFILE		= $(MAINCLASS)

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)
