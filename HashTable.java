/**
 * The hashtable class which contains the implementation of the insert, delete
 * and search methods with any helper functions
 *
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/17/2024
 */
public class HashTable {
    private Record[] hashtable;
    private int size;
    private Record tombstone;
    private int currentNumRecords;

    /**
     * constructor for the hash table
     * 
     * @param sizein
     *            The size of the hash table to begin with
     */
    public HashTable(int sizein) {
        currentNumRecords = 0;
        tombstone = new Record(-1, null);
        size = sizein;
        hashtable = new Record[size];
    }


    /**
     * first hash function
     * 
     * @param key
     *            The key of the record
     * @return the results of the first hash function
     */
    private int h1(int key) {
        return key % size;
    }


    /**
     * second hash function
     * 
     * @param key
     *            The key of the record
     * @return the results of the second hash function
     */
    private int h2(int key) {
        return (((key / size) % (size / 2)) * 2) + 1;
    }


    /**
     * if the hash table exceeds 50% full, replace the array with
     * another that is twice the size, and rehash all of the records from the
     * old array
     */
    private void expand() {
        Record[] newHashtable = new Record[size * 2];
        for (int i = 0; i < size; i++) {
            Record tempRecord = hashtable[i];
            if (tempRecord != tombstone && tempRecord != null) {
                int key = tempRecord.getKey();
                int pos = h1(key);
                int c = h2(key);
                while (newHashtable[pos] != null && newHashtable[pos] != tombstone) {
                    pos = (pos + c) % size; // probe
                }
                newHashtable[pos] = tempRecord;
            }
        }
        hashtable = newHashtable;
        size = size * 2;
        System.out.println("Hash table expanded to " + size + " records");
    }


    /**
     * the insert method for the hash table
     * 
     * @param record
     *            The record that needs to be added into the hash table
     */
    public Handle insert(Record record) {
        // Return an empty string if there is already a record with the
        // specified ID
        if (search(record.getKey()) != null) {
            return null;
        }
        if (size / 2 <= currentNumRecords) {
            expand();
        }
        int key = record.getKey();
        int home = h1(key); // Home position for e
        int pos = home; // Init probe sequence
        int c = h2(key); // second hash function
        while (hashtable[pos] != null && hashtable[pos] != tombstone) {
            pos = (pos + c) % size; // probe
        }
        hashtable[pos] = record;
        currentNumRecords++;
        return hashtable[pos].getHandle();
    }


// check if the record exists in the command processor not here (use the search
// method) - output message if it does not exist
    /**
     * the remove method for the hashtable
     * 
     * @param record
     *            The record that needs to be removed from the hash table
     * @return The handle of the record removed or null if not found
     */
    public Handle delete(int id) {
        int home = h1(id); // Home position for e
        int pos = home; // Init probe sequence
        int c = h2(id); // second hash function
        while (hashtable[pos] != null) {
            if (hashtable[pos].getKey() == id) {
                Handle handle = hashtable[pos].getHandle();
                hashtable[pos] = tombstone;
                currentNumRecords--;
                return handle;
            }
            pos = (pos + c) % size; // probe

        }
        return null;

    }


    /**
     * the search method for the hashtable
     * 
     * @param record
     *            The record that needs to be searched from the hash table
     * @return whether or not the record being searched for was foundf
     */
    public Handle search(int id) {
        int home = h1(id);
        int pos = home;
        int c = h2(id);
        while (hashtable[pos] != null) {
            if (id == (hashtable[pos]).getKey()) { // Found it
                Record temp = hashtable[pos];              
                return temp.getHandle();
            }
            pos = (pos + c) % size; // probe
            // then we've checked every possibility already
            if (pos == home) {
                break;
            }
        }
        return null;
    }


    /**
     * the print method for the hashtable
     * 
     * @return
     */
    public void dump() {
        int entries = 0;
        StringBuilder stringbuilder = new StringBuilder();
        String s = "Hashtable:";
        stringbuilder.append(s).append("\n");
        for (int i = 0; i < size; i++) {
            if (hashtable[i] != null) {
                if (hashtable[i].equals(tombstone)) {
                    s = i + ": TOMBSTONE";
                    stringbuilder.append(s).append("\n");
                }
                else {
                    s = i + ": " + hashtable[i].getKey();
                    stringbuilder.append(s).append("\n");
                    entries++;
                }
            }
        }
        s = "total records: " + entries;
        stringbuilder.append(s);
        System.out.println(stringbuilder.toString());
    }

}
