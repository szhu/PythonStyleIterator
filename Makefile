%: %.java
	javac $<

clean:
	rm -rf -- *.class
