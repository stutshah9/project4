/**
 * The handle class which contains getter and setter methods for the size of the
 * block and start location of the block
 *
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/17/2024
 */
public class Handle {
    private int start;
    private int size;

    /**
     * Constructor method for the handle class
     * 
     * @param startin
     *            Start location of the record in the hash table
     * @param sizein
     *            The size of the record in hash table
     */
    public Handle(int startin, int sizein) {
        start = startin;
        size = sizein;
    }


    /**
     * getter method for the start location of the record
     * 
     * @return the start location of the record
     */
    public int getStart() {
        return start;
    }


    /**
     * getter method for the size of the record
     * 
     * @return the size of the record
     */
    public int getSize() {
        return size;
    }


    /**
     * setter method for the start location of the record
     * 
     * @param startOfRecord
     *            the start location of the record
     */
    public void setStart(int startOfRecord) {
        start = startOfRecord;
    }


    /**
     * setter method for the size of the record
     * 
     * @param sizeOfRecord
     *            the size of the record
     */
    public void setSize(int sizeOfRecord) {
        size = sizeOfRecord;
    }
}