package org.vkedco.regexp;

import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
Enter your regex: a
Enter input string to search: bac
Found match "a" starting at index 1 and ending at index 2.

Enter your regex: /\d/
Enter input string to search: bc12ab
No match found.

Enter your regex: \d
Enter input string to search: bc12ab
Found match "1" starting at index 2 and ending at index 3.
Found match "2" starting at index 3 and ending at index 4.

Enter your regex: \d*
Enter input string to search: ab 1243 cd
Found match "" starting at index 0 and ending at index 0.
Found match "" starting at index 1 and ending at index 1.
Found match "" starting at index 2 and ending at index 2.
Found match "1243" starting at index 3 and ending at index 7.
Found match "" starting at index 7 and ending at index 7.
Found match "" starting at index 8 and ending at index 8.
Found match "" starting at index 9 and ending at index 9.
Found match "" starting at index 10 and ending at index 10.
 */

public class RegexTestHarness {

    public static void main(String[] args){
        Console console = System.console();

        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        while (true) {

            Pattern pattern = 
            Pattern.compile(console.readLine("%nEnter your regex: "));

            Matcher matcher = 
            pattern.matcher(console.readLine("Enter input string to search: "));

            boolean found = false;
            // iterate through groups and print matches and their positions 
            while (matcher.find()) {
                console.format("I found match \"%s\" starting at " +
                   "index %d and ending at index %d.%n",
                    matcher.group(), matcher.start(), matcher.end());
                found = true;
            }
            if(!found){
                console.format("No match found.%n");
            }
        }
    }
}

