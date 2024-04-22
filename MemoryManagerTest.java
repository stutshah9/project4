import student.TestCase;

/**
 * Tests the methods of the memory manager class
 * 
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/21/2024
 *
 */
public class MemoryManagerTest extends TestCase {

    private MemoryManager memManager;

    /**
     * Initializes a memory manager object to be used in testing
     */
    public void setUp() {
        memManager = new MemoryManager(8);
    }


    /**
     * Tests that insert returns the correct handle when a record is inserted
     */
    public void testInsert() {
        Handle handle = memManager.insert(null, 0);
        assertEquals(handle.getStart(), new Handle(0, 0).getStart());
    }


    /**
     * Tests the length method for a handle
     */
    public void testLength() {
        assertEquals(0, memManager.length(new Handle(0, 0)));
    }

    /**
     * Tests the remove method for removing the specified handle from the memory
     * manager
     */
    public void testRemove() {
        memManager.remove(new Handle(0, 0));
    }
    
    /**
     * Tests the get method
     */
    public void testGet() {
        memManager.get(null, null, 0);
    }
    
    /**
     * Tests the dump method
     */
    public void testDump() {
        memManager.dump();
    }
}