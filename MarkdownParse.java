// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
            // the next )
            int currentIndex = 0;
            while(currentIndex < markdown.length()) {
                int nextOpenBracket = markdown.indexOf("[", currentIndex);
                int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
                int openParen = markdown.indexOf("(", nextCloseBracket);
                int closeParen = markdown.indexOf(")", openParen);
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            }
            return toReturn;
        }
    public static void main(String[] args) throws IOException {
        // take in the first command line arg as the file name to be searched for links.
		Path fileName = Path.of(args[0]);
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = getLinks(contents);
        // print out the links that we found
        System.out.println(links);
    }
}