import student.TestCase;

/**
 * The hashtable test class
 *
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/17/2024
 */
public class HashTableTest extends TestCase {

    private HashTable hashtable;
    private Handle handle1;
    private Handle handle2;
    private Handle handle3;
    private Handle handle4;
    private Record record1;
    private Record record2;
    private Record record3;
    private Record record4;

    public void setUp() {
        hashtable = new HashTable(8);
        handle1 = new Handle(1, 2);
        handle2 = new Handle(5, 6);
        handle3 = new Handle(2, 3);
        handle4 = new Handle(1, 4);
        record1 = new Record(handle1, 1);
        record2 = new Record(handle2, 2);
        record3 = new Record(handle3, 3);
        record4 = new Record(handle4, 10);
    }


    public void testAll() {
        hashtable.insert(record1);
        hashtable.insert(record2);
        hashtable.insert(record3);
        hashtable.insert(record4);

        System.out.print(hashtable.printHashtable());
        assertEquals(systemOut().getHistory(), "Hashtable:\n" + "1: 1\n"
            + "2: 2\n" + "3: 3\n" + "5: 10\n" + "total records: 4\n");
        systemOut().clearHistory();

        System.out.print(hashtable.search(3));
        assertEquals(systemOut().getHistory(), "Hashtable:\n" + "1: 1\n"
            + "2: 2\n" + "3: 3\n" + "5: 10\n" + "total records: 4\n");
        systemOut().clearHistory();

        hashtable.remove(2);
        System.out.print(hashtable.printHashtable());
        assertEquals(systemOut().getHistory(), "Hashtable:\n" + "1: 1\n"
            + "2: TOMBSTONE\n" + "3: 3\n" + "5: 10\n" + "total records: 3\n");
        systemOut().clearHistory();

    }
}
