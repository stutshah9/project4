import student.TestCase;

/**
 * Test class for the block class to ensure the getter and setter methods work
 * properly
 * 
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/21/2024
 *
 */
public class BlockTest extends TestCase {

    private Block block;

    /**
     * Initialize a block object to use in testing
     */
    public void setUp() {
        block = new Block(1, 3);
    }


    /**
     * Tests the accessor methods of the block class
     */
    public void testGetters() {
        assertEquals(block.getSize(), 3);
        assertEquals(block.getStart(), 1);
    }

    /**
     * Tests the mutator methods of the block class
     */
    public void testSetters() {
        block.setSize(6);
        block.setStart(5);
        assertEquals(block.getSize(), 6);
        assertEquals(block.getStart(), 5);
    }
}
