JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	BFS.java \
	DFS.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class