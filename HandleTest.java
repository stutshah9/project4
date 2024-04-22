import student.TestCase;

/**
 * Test class for the handle class to ensure the getter and setter methods work
 * properly
 * 
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/21/2024
 *
 */
public class HandleTest extends TestCase {

    private Handle handle;

    /**
     * Initialize a handle object to use in testing
     */
    public void setUp() {
        handle = new Handle(1, 3);
    }


    /**
     * Tests the accessor methods of the handle class
     */
    public void testGetters() {
        assertEquals(handle.getSize(), 3);
        assertEquals(handle.getStart(), 1);
    }

    /**
     * Tests the mutator methods of the handle class
     */
    public void testSetters() {
        handle.setSize(6);
        handle.setStart(5);
        assertEquals(handle.getSize(), 6);
        assertEquals(handle.getStart(), 5);
    }
}
