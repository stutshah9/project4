import java.util.ArrayList;

/**
 * Memory manager class that stores a list of free blocks and a memory pool of
 * byte arrays.
 * 
 * @author Stuti Shah and Lauren Spehlmann
 * @verison 4/19/2024
 *
 */
public class MemoryManager {

    private byte[] memoryPool;
    private ArrayList<Block> freeList;

    /**
     * Constructor for the memory manager, creates a memory pool of the
     * specified size
     * 
     * @param insize
     *            The initial size of the memory pool, must be a power of 2
     */
    public MemoryManager(int insize) {
        freeList = new ArrayList<Block>();
        memoryPool = new byte[insize];
    }


    /**
     * Insert a record of a given size
     * 
     * @param space
     *            The byte array containing the record
     * @param size
     *            The size of the byte array
     * @return The position handle of the inserted record
     */
    public Handle insert(byte[] space, int size) {
        return new Handle(0, 0);
    }


    /**
     * Calculates the size of a record
     * 
     * @param theHandle
     *            An object of the handle class
     * @return the length of the record associated with theHandle
     */
    public int length(Handle theHandle) {
        return 0;
    }


    /**
     * Free a block at the position specified by theHandle and merge the
     * adjacent free blocks
     * 
     * @param theHandle
     *            The handle that identifies the block to remove
     */
    public void remove(Handle theHandle) {

    }


    /**
     * Return the record with handle posHandle, up to size bytes into space
     * 
     * @param space
     *            The byte array to copy the contents of the record into
     * @param theHandle
     *            The handle of the record to copy
     * @param size
     *            Number of bytes of the record to return
     * @return the number of bytes actually copied into space
     */
    public int get(byte[] space, Handle theHandle, int size) {
        return 0;
    }


    /**
     * Dump a printout of the freeblock list
     */
    public void dump() {

    }
}
