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
    private int number;

    /**
     * constructor for the hash table
     * 
     * @param sizein
     *            The size of the hash table to begin with
     */
    public HashTable(int sizein) {
        number = 0;
        tombstone = new Record(null, -1);
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
        Record[] temp = hashtable;
        hashtable = new Record[size * 2];
        for (int i = 0; i < size; i++) {
            Record tempRecord = temp[i];
            if (tempRecord != tombstone) {
                insert(tempRecord);
            }
        }
        size = size * 2;
    }


// check for duplicates in the command processor not here (use the search
// method) - output message if it already exists
    /**
     * the insert method for the hash table
     * 
     * @param record
     *            The record that needs to be added into the hash table
     */
    public String insert(Record record) {
        if (size / 2 == number) {
            expand();
        }
        int key = record.getKey();
        int home; // Home position for e
        int pos = home = h1(key); // Init probe sequence
        int c = h2(key); // second hash function
        while (hashtable[pos] != null && hashtable[pos] != tombstone) {
            pos = (pos + c) % size; // probe
        }
        hashtable[pos] = record;
        number++;
        return hashtable[pos].getHandle().toString();
    }


// check if the record exists in the command processor not here (use the search
// method) - output message if it does not exist
    /**
     * the remove method for the hashtable
     * 
     * @param record
     *            The record that needs to be removed from the hash table
     */
    public void delete(int id) {
        int home; // Home position for e
        int pos = home = h1(id); // Init probe sequence
        int c = h2(id); // second hash function
        while (hashtable[pos] != null) {
            if (hashtable[pos].getKey() == id) {
                hashtable[pos] = tombstone;
                number--;
            }
            pos = (pos + c) % size; // probe

        }

    }


    /**
     * the search method for the hashtable
     * 
     * @param record
     *            The record that needs to be searched from the hash table
     * @return wether or not the record being searched for was foundf
     */
    public String search(int id) {
        int home;
        int pos = home = h1(id);
        int c = h2(id);
        while (hashtable[pos] != null) {
            if (id == (hashtable[pos]).getKey()) { // Found it
                Record temp = hashtable[pos];
                return temp.getHandle().toString();
            }
            pos = (pos + c) % size; // probe
        }
        return null;
    }


    /**
     * the print method for the hashtable
     * 
     * @return
     */
    public String printHashtable() {
        int entries = 0;
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (hashtable[i] != null) {
                if (hashtable[i] == tombstone) {
                    String s = i + ": TOMBSTONE";
                    stringbuilder.append(s).append("\n");
                }
                else {
                    String s = i + ": " + hashtable[i].getKey();
                    stringbuilder.append(s).append("\n");
                    entries++;
                }
            }
        }
        return stringbuilder.toString();
    }

}