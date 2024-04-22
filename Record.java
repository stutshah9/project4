/**
 * The record class the creates records which are composed of a handle and a
 * key. The handle is derived from the memory manager and the key is the ID of a
 * seminar
 *
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/17/2024
 */
public class Record {
    private int key;
    private Seminar handle;

    /**
     * Constructor method for the handle class
     * 
     * @param handlein
     *            The handle from the memory manager
     * @param keyin
     *            The ID of the seminar
     */
    public Record(int keyin, Seminar handlein) {
        handle = handlein;
        key = keyin;
    }


    /**
     * getter method for the handle of the record
     * 
     * @return the whole seminar
     */
    public Seminar getHandle() {
        return handle;
    }


    /**
     * getter method for the key of the record
     * 
     * @return the key of a specific record
     */
    public int getKey() {
        return key;
    }


    /**
     * setter method for the key of the record
     * 
     * @param keyID
     *            The key of the record
     */
    public void setKey(int keyID) {
        key = keyID;
    }

}