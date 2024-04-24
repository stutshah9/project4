/**
 * The purpose of this class is to handle what is being outputed depending on
 * the command that is being parsed by main
 * 
 * @author Stuti Shah, Lauren Spehlmann
 * 
 * @version 4/18/2024
 */
public class Database {
    private MemoryManager mm;
    private HashTable ht;

    /**
     * the database constructor
     * 
     * @param memSize
     *            The size of the memory manager
     * @param hashSize
     *            The size of the hash table
     */
    public Database(int memSize, int hashSize) {
        mm = new MemoryManager(memSize);
        ht = new HashTable(hashSize);
    }


    /**
     * deletes a record with a specific id if it exists
     * 
     * @param id
     */
    public void delete(int id) {
        Handle deletion = ht.delete(id);
        if (deletion == null) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + id);
        }
        else {
            mm.remove(deletion);
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
        if (ht.search(seminar.getId()) != null) {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + seminar
                    .getId());
        }
        else {
            Handle handle;
            try {
                handle = mm.insert(seminar.serialize(), seminar.serialize().length);
                ht.insert(new Record(seminar.getId(), handle));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }

            System.out.println("Successfully inserted record with ID " + seminar
                .getId());
            System.out.println(seminar.toString());
            try {
                System.out.println("Size: " + seminar.serialize().length);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * search for a record with a specific id if it exists
     * 
     * @param id
     */
    public void search(int id) {
        Handle searchHandle = ht.search(id);
        if (searchHandle == null) {
            System.out.println("Search FAILED -- There is no record with ID "
                + id);
        }
        else {
            System.out.println("Found record with ID " + id + ":");
            byte[] handleContents = new byte[searchHandle.getSize()];
            mm.get(handleContents, searchHandle, searchHandle.getSize());
            try {
                System.out.println(Seminar.deserialize(handleContents)
                    .toString());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * print the hash table array
     */
    public void printhashtable() {
        ht.dump();

    }


    public void printblocks() {
        mm.dump();

    }

}
