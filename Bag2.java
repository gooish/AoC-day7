import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


/* this took way too long
*  only managed to get the recursive function working properly
*  by reading someone else's solution
*  i was on the right track, just couldn't figure out that one line
*  also trying to comment my code more with these now
*/

public class Bag2 {
    public static void main(String[] args) {

        // learned to use hashmaps with this 
        // i kinda like the parsing, it's more elegant than mine usually is
        LinkedList<String> rivit = loadFile("data.txt");
        HashMap<String, String[]> pairs = new HashMap<>();
        for (String rivi : rivit) {
            String bag = rivi.split(" contain ")[0].replace("bags", "").trim();
            String children[] = rivi.split("contain ")[1].split(", ");
            for (int i = 0; i < children.length; i++) {
                children[i] = children[i].replace("bags", "").replace("bag", "").trim();
            }
            pairs.put(bag, children);
        }

        System.out.print("Day 7 answer: ");
        System.out.println(bagContent(pairs, "shiny gold"));

    }

    // recursive function to walk the graph
    public static int bagContent (HashMap<String, String[]> data, String toSearchString) {
        int bags = 0;        
        for (String s : data.get(toSearchString)) {
            if (!s.contains("other")) {
                int numero = Integer.parseInt(s.substring(0, 1));

                /* that one line i couldn't figure out
                *
                *  the original that made me give up was something like 
                *  bags += bags * bagcontent(data, s.substring(2)); */
                
                bags += numero + numero * bagContent(data, s.substring(2));
            }
            else {
                break;
            }
        }
        return bags;
        
    }


    /* people somehow always have shorter file read methods than this
    *  oh well
    */

    public static LinkedList<String> loadFile(String fName) {
        try {
            Scanner input = new Scanner(new File(fName));
            LinkedList<String> rivit = new LinkedList<>();
            while (input.hasNextLine()) {
                rivit.add(input.nextLine().replace(".", ""));
                
            }
            return rivit;
        } catch (FileNotFoundException e) {
            System.out.println("file not found"
            );
            System.exit(1);
        }
        return null;
    }
}