/**
 * The purpose of this class is to parse a single line from the command text
 * file (or multiple lines condensed into 1, in the case of insert) according to
 * the format specified in the project specs.
 * 
 * @author Stuti Shah, Lauren Spehlmann, & CS Staff
 * 
 * @version 4/18/2024
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * @param memSize
     *            The size of the memory manager
     * @param hashSize
     *            The size of the hash table
     */
    public CommandProcessor(int memSize, int hashSize) {
        data = new Database(memSize, hashSize);
    }


    /**
     * This method parses keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions.
     * These actions are performed on specified objects and include insert,
     * delete, search, print hashtable, and print blocks. If the command in the
     * file line is not one of these, an appropriate message will be written in
     * the console. This processor method is called for each line in the file.
     * Note that the methods called will themselves write to the console, this
     * method does not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        // split the potentially multiline string into its lines
        String[] lines = line.split("\\n");
        // converts the string of the line into an
        // array of its space (" ") delimited elements
        String[] arr = lines[0].trim().split("\\s{1,}");
        String command = arr[0]; // the command will be the first of these
                                 // elements
        // calls the insert function and passes the correct
        // parameters by converting the string integers into
        // their Integer equivalent, trimming the whitespace
        // first line has the id, second line is the title, third
        // line contains the data/time, length, x, y, and cost,
        // fourth line has the keyword list, and fifth line has
        // the description
        if (command.equals("insert")) {
            // Create a seminar object and call insert
            int id = Integer.parseInt(arr[1]);
            String title = lines[1].trim();
            String[] line2 = lines[2].trim().split("\\s{1,}");
            String date = line2[0];
            int length = Integer.parseInt(line2[1]);
            short x = Short.parseShort(line2[2]);
            short y = Short.parseShort(line2[3]);
            int cost = Integer.parseInt(line2[4]);
            String[] keywords = lines[3].trim().split("\\s{1,}");
            String desc = lines[4].trim();
            Seminar seminar = new Seminar(id, title, date, length, x, y, cost,
                keywords, desc);
            data.insert(seminar);
        }

        else if (command.equals("delete")) {
            // Call the delete method for the specified id of a record in the
            // hash table
            int id = Integer.parseInt(arr[1]);
            data.delete(id);
        }

        else if (command.equals("search")) {
            // Calls the search method for the record with the specified id in
            // the hash table
            int id = Integer.parseInt(arr[1]);
            data.search(id);
        }

        else if (command.equals("print")) {
            // check the second arg to see what to print
            if (arr[1].equals("hashtable")) {
                // Calls the print hashtable method that prints the keys and
                // values
                // of records in the hash table as well
                // as the total number of records
                data.printhashtable();
            }
            else {
                // calls the print blocks method to print the number and sizes
                // of
                // available free blocks in the memory manager
                data.printblocks();
            }

        }
        else {
            // the first white space delimited string in the line is not
            // one of the commands which can manipulate the database,
            // a message will be written to the console
            System.out.println("Unrecognized command.");
        }
    }

}
