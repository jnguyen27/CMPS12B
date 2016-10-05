# -------------------------------------------------
#  Duy Nguyen
#  CS 12M lab2
#  10/04/16
#  Makefile for FileReverse.java
#  ------------------------------------------------
JAVASRC    = FileReverse.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = FileReverse
CLASSES    = FileReverse.class
JARFILE	   = FileReverse

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
