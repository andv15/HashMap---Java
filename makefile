build:
	javac src/list/*.java src/map/*.java -d .
clean:
	rm -rf list map 
run:
	java test/Main
