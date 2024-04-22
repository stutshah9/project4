import student.TestCase;

/**
 * Test class for the record class to ensure the getter and setter methods work
 * properly
 * 
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/21/2024
 *
 */
public class RecordTest extends TestCase {

    private Record record;
    private Seminar handle;

    /**
     * Initialize a record object to use in testing
     */
    public void setUp() {
        handle = new Seminar(1, "Overview of HCI Research at VT", "0610051600",
            90, (short)10, (short)10, 45, new String[] { "HCI",
                "Computer_Science", "VT", "Virginia_Tech" },
            "This seminar will present an overview of HCI research at VT");
        record = new Record(1, handle);
    }


    /**
     * Tests the accessor methods of the record class
     */
    public void testGetters() {
        assertEquals(record.getKey(), 1);
        assertEquals(record.getHandle(), handle);
    }

    /**
     * Tests the mutator method of the record class
     */
    public void testSetter() {
        record.setKey(9);
        assertEquals(record.getKey(), 9);
    }
}
