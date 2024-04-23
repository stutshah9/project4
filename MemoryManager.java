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
        // Initially, there is 1 free block the size of the entire memory pool
        freeList.add(new Block(0, insize));
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
        // the largest block of free memory is at the end of the list
        while (size > freeList.get(freeList.size() - 1).getSize()) {
            expand();
        }
        int insertionIndex = freeList.size() - 1;
        // put the byte array in the smallest block it will fit in
        while (freeList.get(insertionIndex).getSize() / 2 >= size) {
            // if no smaller blocks exist, we need to split the current block
            if (insertionIndex == 0) {
                splitBlock();
            }
            else {
                insertionIndex--;
            }
        }
        // now the freeList has a block that can fit the byte array while
        // minimizing external fragmentation
        Block insertedHere = freeList.remove(insertionIndex);
        // use the block to copy the byte array into the correct position
        System.arraycopy(space, 0, memoryPool, insertedHere.getStart(), size);
        return new Handle(insertedHere.getStart(), insertedHere.getSize());
    }


    private void splitBlock() {
        // split the current smallest block in half
        int currentBlockSize = freeList.get(0).getSize();
        int currentStart = freeList.get(0).getStart();
        // replace the full size block with 1 block that is half the size
        // and 2 blocks that are 1/4 the size
        freeList.get(0).setSize(currentBlockSize / 2);
        freeList.get(0).setStart(currentStart + (currentBlockSize / 2));
        freeList.add(0, new Block(currentStart, currentBlockSize / 4));
        freeList.add(0, new Block(currentStart + (currentBlockSize / 4),
            currentBlockSize / 4));
    }


    private void expand() {
        // Copy each slot of the old memory pool into the new memory pool
        byte[] newMemoryPool = new byte[memoryPool.length * 2];
        for (int i = 0; i < memoryPool.length; i++) {
            newMemoryPool[i] = memoryPool[i];
        }
        memoryPool = newMemoryPool;
        freeList.add(new Block(memoryPool.length / 2, memoryPool.length / 2));
        System.out.println("Memory pool expanded to " + memoryPool.length
            + " bytes");
    }


    /**
     * Free a block at the position specified by theHandle and merge the
     * adjacent free blocks
     * 
     * @param theHandle
     *            The handle that identifies the block to remove
     */
    public void remove(Handle theHandle) {
        // clear the memory in the pool
        for (int i = theHandle.getStart(); i < theHandle.getStart() + theHandle
            .getSize(); i++) {
            memoryPool[i] = 0;
        }
        Block newBlock = new Block(theHandle.getStart(), theHandle.getSize());
        int index = 0;
        while (newBlock.getStart() > freeList.get(index).getStart()) {
            index++;
        }
        freeList.add(index, newBlock);
        while (checkForBuddies()) {
            // Intentionally blank - checkForBuddies() merges blocks to resolve
            // buddies
            // if checkForBuddies() returned true, it is possible that more
            // buddies are still left
        }
    }


    private boolean checkForBuddies() {
        // compare each block to the block after it, since buddies are always
        // next to each other
        for (int i = 0; i < freeList.size() - 1; i++) {
            if ((freeList.get(i).getStart() | freeList.get(i)
                .getSize()) == (freeList.get(i + 1).getStart() | freeList.get(i
                    + 1).getSize())) {
                // merge the buddies and return true
                int newSize = freeList.remove(i + 1).getSize() * 2;
                freeList.get(i).setSize(newSize);
                return true;
            }
        }
        return false;
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
        int numBytesCopied = Math.min(size, theHandle.getSize());
        for (int i = theHandle.getStart(); i < numBytesCopied; i++) {
            space[i] = memoryPool[i];
        }
        return numBytesCopied;
    }


    /**
     * Dump a printout of the freeblock list
     */
    public void dump() {
        System.out.print("Freeblock List:");
        if (freeList.size() == 0) {
            System.out.println();
            System.out.println("There are no freeblocks in the memory pool");
        }
        else {
            int currBlockSize = -1;
            for (int i = 0; i < freeList.size(); i++) {
                if (currBlockSize != freeList.get(i).getSize()) {
                    System.out.println();
                    currBlockSize = freeList.get(i).getSize();
                    System.out.print(currBlockSize + ": " + freeList.get(i)
                        .getStart());
                }
                else {
                    // it's the second block of the same size
                    System.out.println(" " + freeList.get(i).getStart());
                }
            }
            System.out.println();
        }
    }
}
