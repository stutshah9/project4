/**
 * This class represents a single block to be used by the memory manager in the
 * list of free blocks
 * 
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/19/2024
 */
public class Block {
    
    private int start;
    private int size;
    
    /**
     * Constructor method for the block class
     * 
     * @param startin
     *            Start location of the block in the memory manager
     * @param sizein
     *            The size of the block in memory manager
     */
    public Block(int startin, int sizein) {
        start = startin;
        size = sizein;
    }


    /**
     * getter method for the start location of the block
     * 
     * @return the start location of the clock
     */
    public int getStart() {
        return start;
    }


    /**
     * getter method for the size of the block
     * 
     * @return the size of the block
     */
    public int getSize() {
        return size;
    }


    /**
     * setter method for the start location of the block
     * 
     * @param startOfBlock
     *            the start location of the block
     */
    public void setStart(int startOfBlock) {
        start = startOfBlock;
    }


    /**
     * setter method for the size of the block
     * 
     * @param sizeOfBlock
     *            the size of the block
     */
    public void setSize(int sizeOfBlock) {
        size = sizeOfBlock;
    }
}