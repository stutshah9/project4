import java.util.ArrayList;
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
     * Tests that a simple insert returns the correct handle when a record is
     * inserted
     */
    public void testSimpleInsert() {
        Handle handle = memManager.insert(new byte[] { 0x0, 0x1, 0x2, 0x3 }, 4);
        byte[] array = new byte[4];
        memManager.get(array, handle, 4);
        for (int i = 0; i < 4; i++) {
            assertEquals(i, array[i]);
        }
        ArrayList<Block> freeList = memManager.getFreeList();
        assertEquals(freeList.size(), 1);
        assertEquals(freeList.get(0).getStart(), 4);
        systemOut().clearHistory();
        memManager.dump();
        assertEquals(systemOut().getHistory(), "Freeblock List:\n4: 4\n");
    }


    /**
     * Tests inserting 4 records where an expansion is necessary
     */
    public void testComplicatedInsert() {
        memManager.insert(new byte[] { 0x0, 0x1, 0x2, 0x3 }, 4);
        memManager.insert(new byte[] { 0x4, 0x5, 0x6, 0x7 }, 4);
        // third insert causes the memory pool to expand to 16 bytes
        memManager.insert(new byte[] { 0x8, 0x9, 0xa, 0xb }, 4);
        memManager.insert(new byte[] { 0xc, 0xd, 0xe, 0xf }, 4);
        ArrayList<Block> freeList = memManager.getFreeList();
        assertEquals(freeList.size(), 0);
        systemOut().clearHistory();
        memManager.dump();
        assertEquals(systemOut().getHistory(),
            "Freeblock List:\nThere are no freeblocks in the memory pool\n");
    }


    /**
     * Tests a complicated insert where expansion is necessary because there is
     * no room
     */
    public void testExpand() {
        memManager.insert(new byte[] { 0x0, 0x1, 0x2, 0x3 }, 4);
        memManager.insert(new byte[] { 0x4, 0x5, 0x6, 0x7 }, 4);
        // third insert causes the memory pool to expand to 16 bytes
        memManager.insert(new byte[] { 0x8, 0x9, 0xa, 0xb }, 4);
        ArrayList<Block> freeList = memManager.getFreeList();
        assertEquals(freeList.size(), 1);
        assertEquals(freeList.get(0).getStart(), 12);
        for (int i = 0; i < 12; i++) {
            assertEquals(i, memManager.getMemoryPool()[i]);
        }
    }


    /**
     * Tests a complicated insert where multiple expansions are necessary
     * because there is not room for a record so large
     */
    public void testMultiExpand() {
        memManager.insert(new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7,
            0x8, 0x9, 0xa, 0xb, 0xc, 0xd, 0xe, 0xf, 0x0 }, 17);
        // expands the free list to 32 bytes
        ArrayList<Block> freeList = memManager.getFreeList();
        assertEquals(freeList.size(), 0);
        assertEquals(memManager.getMemoryPool()[15], 15);
    }


    /**
     * Tests when the record fits in a smaller block than the last one in the
     * free list
     */
    public void testDecrementInsertionIndex() {
        memManager.insert(new byte[] { 0x0 }, 1);
        // now the free list has blocks of size 4, 2, and 1
        memManager.insert(new byte[] { 0x1 }, 1);
        ArrayList<Block> freeList = memManager.getFreeList();
        assertEquals(freeList.size(), 2);
        assertEquals(freeList.get(0).getStart(), 2);
        assertEquals(memManager.getMemoryPool()[1], 1);
    }


    /**
     * Tests the remove method for removing the specified handle from the memory
     * manager
     */
    public void testRemove() {
        memManager.insert(new byte[] { 0x0 }, 1);
        // now the free list has blocks of size 4, 2, and 1
        Handle handle = memManager.insert(new byte[] { 0x1 }, 1);
        memManager.remove(handle);
        ArrayList<Block> freeList = memManager.getFreeList();
        assertEquals(freeList.size(), 3);
        assertEquals(freeList.get(0).getStart(), 1);
    }


    public void testComplicatedRemove() {
        memManager.insert(new byte[] { 0x0, 0x1, 0x2, 0x3 }, 4);
        Handle handle = memManager.insert(new byte[] { 0x4, 0x5, 0x6, 0x7 }, 4);
        // third insert causes the memory pool to expand to 16 bytes
        memManager.insert(new byte[] { 0x8, 0x9, 0xa, 0xb }, 4);
        Handle handle2 = memManager.insert(new byte[] { 0xc, 0xd, 0xe, 0xf },
            4);
        // delete the second handle, free block 4: 4
        memManager.remove(handle);
        // memory pool expands to 32 bytes, adds 16: 16
        memManager.insert(new byte[] { 0x4, 0x5, 0x6, 0x7, 0x8 }, 5);
        memManager.remove(handle2);
        // free block at 
        //4: 4, 12, 
        //8: 24
        ArrayList<Block> freeList = memManager.getFreeList();
        assertEquals(freeList.size(), 3);
        assertEquals(freeList.get(1).getStart(), 12);
    }


    /**
     * Tests the dump method when there are 2 free blocks of the same size
     */
    public void testDump() {

    }
}
