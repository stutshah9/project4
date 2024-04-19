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
}
