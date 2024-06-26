import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * {Project Description Here}
 */

/**
 * The class containing the main method.
 *
 * @author Stuti Shah & Lauren Spehlmann
 * @version 04/15/2024
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class SemManager {
    /**
     * @param args
     *            Command line parameters: initial memory size, initial hash
     *            size,
     *            command file. Initial memory size and initial hash size are
     *            both
     *            powers of 2.
     * 
     */
    public static void main(String[] args) {

        // the file containing the commands
        File file = null;

        // Attempts to open the file and scan through it
        try {

            // takes the third command line argument and opens that file
            file = new File(args[2]);

            // creates a scanner object
            Scanner scanner = new Scanner(file);

            // creates a command processor object
            int memSize = Integer.parseInt(args[0]);
            int hashSize = Integer.parseInt(args[1]);
            CommandProcessor cmdProc = new CommandProcessor(memSize, hashSize);

            // reads the entire file and processes the commands
            // as blank lines are encountered (insert is a multi
            // line command)
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s{1,}");
                // if there's an insert command, we need to read the next 4
                // lines too
                if (parts.length > 0 && parts[0].equals("insert")) {
                    builder.append(line.trim());
                    for (int i = 0; i < 4; i++) {
                        line = scanner.nextLine();
                        // add the \n to distinguish between multiple lines
                        builder.append("\n" + line.trim());
                    }
                    cmdProc.processor(builder.toString());
                    // clear the string to hold the next command
                    builder = new StringBuilder();
                }
                // in the case that it is not an insert command, just process
                // the single line
                else {
                    // don't process if there is a blank line
                    if (!line.trim().isEmpty()) {
                        cmdProc.processor(line);
                    }
                }
            }
            // closes the scanner
            scanner.close();
        }
        // catches the exception if the file cannot be found
        // and outputs the correct information to the console
        catch (FileNotFoundException e) {
            System.out.println("Invalid file");
            e.printStackTrace();
        }

    }
}
