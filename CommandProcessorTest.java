import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * Tests the communication between the command processor and database primarily
 * 
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/19/2024
 *
 */
public class CommandProcessorTest extends TestCase {
    private CommandProcessor cmp;
    private ByteArrayOutputStream out;

    /**
     * Sets up the objects for the command processor tests
     */
    public void setUp() {
        cmp = new CommandProcessor(2048, 4096);
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

    }


    /**
     * Tests the insert command for valid and invalid (duplicate) records
     */
    public void testInsertCommand() {

        cmp.processor("insert 1\r\n" + "Overview of HCI Research at VT\r\n"
            + "0610051600 90 10 10 45\r\n"
            + "HCI Computer_Science VT Virginia_Tech\r\n"
            + "This seminar will present an overview of HCI research at VT");
        assertFuzzyEquals("Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will present an overview of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\r\n"
            + "Size: 173", out.toString());
        out.reset();
        cmp.processor("insert 1\r\n" + "Overview of HCI Research at VT\r\n"
            + "0610051600 90 10 10 45\r\n"
            + "HCI Computer_Science VT Virginia_Tech\r\n"
            + "This seminar will present an overview of HCI research at VT");
        assertFuzzyEquals("Insert FAILED - There is already a record with ID 1",
            out.toString());
        out.reset();

    }


    /**
     * Test to ensure that delete is called when "delete" is passed through the
     * command processor
     */
    public void testRemoveCommand() {

        cmp.processor("delete 2");
        assertFuzzyEquals("Delete FAILED -- There is no record with ID 2\n", out
            .toString());
        // insert a record with the ID 1
        cmp.processor("insert 1\r\n" + "Overview of HCI Research at VT\r\n"
            + "0610051600 90 10 10 45\r\n"
            + "HCI Computer_Science VT Virginia_Tech\r\n"
            + "This seminar will present an overview of HCI research at VT");
        out.reset();
        cmp.processor("delete 1");
        assertFuzzyEquals(
            "Record with ID 1 successfully deleted from the database\n", out
                .toString());
        out.reset();
    }


    public void testSearch() {
        cmp.processor("search 5");
        assertFuzzyEquals("Search FAILED -- There is no record with ID 5\n", out
            .toString());
        cmp.processor("insert 1\r\n" + "Overview of HCI Research at VT\r\n"
            + "0610051600 90 10 10 45\r\n"
            + "HCI Computer_Science VT Virginia_Tech\r\n"
            + "This seminar will present an overview of HCI research at VT");
        out.reset();
        cmp.processor("search 1");
        assertFuzzyEquals("Found record with ID 1\r\n"
            + "ID: 1, Title: Overview of HCI Research at VT\r\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\r\n"
            + "Description: This seminar will present an overview of HCI research at VT\r\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech", out
                .toString());
    }


    public void testPrint() {
        cmp.processor("print hashtable");
        assertFuzzyEquals("Hashtable:\ntotal records: 0", out.toString());
        out.reset();
        cmp.processor("print blocks");
        assertFuzzyEquals("Freeblock List:\n2048 0\n", out.toString());
    }


    public void testUnrecognized() {
        cmp.processor("idk");
        assertFuzzyEquals("Unrecognized command.", out.toString());
        out.reset();
    }

}
