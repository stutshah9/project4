/**
 * The purpose of this class is to handle what is being outputed depending on
 * the command that is being parsed by main
 * 
 * @author Stuti Shah, Lauren Spehlmann
 * 
 * @version 4/18/2024
 */
public class Database {
    MemoryManager mm;
    HashTable ht;
    int size;

    /**
     * the database constructor
     * 
     * @param sizein
     */
    public Database(int sizein) {
        size = sizein;
        mm = new MemoryManager(sizein);
        ht = new HashTable(sizein);
    }


    /**
     * deletes a record with a specific id if it exists
     * 
     * @param id
     */
    public void delete(int id) {
        if (!ht.delete(id)) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
        }
        else {
            System.out.println("Record with ID " + id
                + " successfully deleted from the database");
        }

    }


    /**
     * insert a record with a specific seminar
     * 
     * @param seminar
     */
    public void insert(Seminar seminar) {
        // TODO Auto-generated method stub

    }


    /**
     * search for a record with a specific id if it exists
     * 
     * @param id
     */
    public void search(int id) {
        if (ht.search(id) == null) {
            System.out.println("Search FAILED -- There is no record with ID "
                + id);
        }
        else {
            System.out.println("Found record with ID " + id + ":");
            System.out.println(ht.search(id));
        }

    }


    /**
     * print the hash table array
     */
    public void printhashtable() {
        System.out.println(ht.printHashtable());

    }


    public void printblocks() {
        // TODO Auto-generated method stub

    }

}
