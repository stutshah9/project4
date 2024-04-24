import student.TestCase;

/**
 * The hashtable test class
 *
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/17/2024
 */
public class HashTableTest extends TestCase {

    private HashTable hashtable;
    private MemoryManager memManager;
    private Handle handle1;
    private Handle handle2;
    private Handle handle3;
    private Handle handle4;
    private Handle handle5;
    private Handle handle6;
    private Record record1;
    private Record record2;
    private Record record3;
    private Record record4;
    private Record record5;
    private Record record6;

    /**
     * the setUp method
     */
    public void setUp() {
        hashtable = new HashTable(4);
        memManager = new MemoryManager(2048);
        Seminar seminar1 = new Seminar(1, "Overview of HCI Research at VT",
            "0610051600", 90, (short)10, (short)10, 45, new String[] { "HCI",
                "Computer_Science", "VT", "Virginia_Tech" },
            "This seminar will present an overview of HCI research at VT");

        Seminar seminar2 = new Seminar(2,
            "Computational Biology and Bioinformatics in CS at Virginia Tech",
            "0610071600", 60, (short)20, (short)10, 30, new String[] {
                "Bioinformatics", "computation_biology", "Biology",
                "Computer_Science", "VT", "Virginia_Tech" },
            "Introduction to bioinformatics and computation biology");

        Seminar seminar3 = new Seminar(3, "Computing Systems Research at VT",
            "0701250830", 30, (short)30, (short)10, 17, new String[] {
                "high_performance_computing", "grids", "VT",
                "computer_science" },
            "Seminar about the Computing systems research at VT");

        Seminar seminar4 = new Seminar(10,
            "Overview of HPC and CSE Research at VT", "0703301125", 35,
            (short)0, (short)0, 25, new String[] { "HPC", "CSE",
                "computer_science" },
            "Learn what kind of research is done on HPC and CSE at VT");

        Seminar seminar5 = new Seminar(2,
            "Much More Computational Biology and Bioinformatics in CS at\r\n"
                + "Virginia Tech", "0610071600", 60, (short)20, (short)10, 30,
            new String[] { "Bioinformatics", "computation_biology", "Biology",
                "Computer_Science", "VT", "Virginia_Tech" },
            "Introduction to bioinformatics and lots of computation\r\n"
                + "biology");

        Seminar seminar6 = new Seminar(0,
            "Much More Computational Biology and Bioinformatics in CS at\r\n"
                + "Virginia Tech", "0610071600", 60, (short)20, (short)10, 30,
            new String[] { "Bioinformatics", "computation_biology", "Biology",
                "Computer_Science", "VT", "Virginia_Tech" },
            "Introduction to bioinformatics and lots of computation\r\n"
                + "biology");

        try {
            handle1 = memManager.insert(seminar1.serialize(), seminar1
                .serialize().length);
            handle2 = memManager.insert(seminar2.serialize(), seminar2
                .serialize().length);
            handle3 = memManager.insert(seminar3.serialize(), seminar3
                .serialize().length);
            handle4 = memManager.insert(seminar4.serialize(), seminar4
                .serialize().length);
            handle5 = memManager.insert(seminar5.serialize(), seminar5
                .serialize().length);
            handle6 = memManager.insert(seminar6.serialize(), seminar6
                .serialize().length);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        record1 = new Record(1, handle1);
        record2 = new Record(2, handle2);
        record3 = new Record(3, handle3);
        record4 = new Record(10, handle4);
        record5 = new Record(2, handle5);
        record6 = new Record(0, handle6);
    }


    /**
     * testing to increase mutation testing coverage
     */
    public void testRandom() {
        // hashtable empty
        Handle handle = hashtable.search(3);
        assertNull(handle);
        systemOut().getHistory();

        handle = hashtable.delete(4);
        assertNull(handle);
        systemOut().getHistory();

        // hashtable with 1 record and found
        hashtable.insert(record6);
        handle = hashtable.search(0);
        byte[] array = new byte[handle.getSize()];
        memManager.get(array, handle, handle.getSize());
        try {
            System.out.print(Seminar.deserialize(array));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(systemOut().getHistory(),
            "ID: 0, Title: Much More Computational Biology and Bioinformatics in CS at\r\n"
                + "Virginia Tech\r\n"
                + "Date: 0610071600, Length: 60, X: 20, Y: 10, Cost: 30\r\n"
                + "Description: Introduction to bioinformatics and lots of computation\r\n"
                + "biology\r\n"
                + "Keywords: Bioinformatics, computation_biology, Biology, Computer_Science, VT, Virginia_Tech");
        systemOut().clearHistory();

        // hashtable with 1 record and not found
        handle = hashtable.search(2);
        assertNull(handle);
        systemOut().clearHistory();

        // 
        handle = hashtable.insert(record2);
        array = new byte[handle.getSize()];
        memManager.get(array, handle, handle.getSize());
        try {
            System.out.print(Seminar.deserialize(array));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(systemOut().getHistory(),
            "ID: 2, Title: Computational Biology and Bioinformatics in CS at Virginia Tech\r\n"
                + "Date: 0610071600, Length: 60, X: 20, Y: 10, Cost: 30\r\n"
                + "Description: Introduction to bioinformatics and computation biology\r\n"
                + "Keywords: Bioinformatics, computation_biology, Biology, Computer_Science, VT, Virginia_Tech");
        systemOut().clearHistory();

        handle = hashtable.insert(record2);
        assertNull(handle);
        systemOut().clearHistory();
    }


    /**
     * Tests inserting and deleting multiple times in a row
     */
    public void testInsertDelete() {
        hashtable.insert(record1);
        hashtable.insert(record2);
        hashtable.insert(record3);
        hashtable.insert(record6);
        hashtable.dump();
        assertFuzzyEquals(systemOut().getHistory(),
            "Hash table expanded to 8 records\nHashtable:\r\n" + "0 0\r\n"
                + "1 1\r\n" + "2 2\r\n" + "3 3\r\n" + "total records: 4");
        systemOut().getHistory();

        hashtable.delete(0);
        systemOut().clearHistory();

        hashtable.insert(record4);
        hashtable.dump();
        assertFuzzyEquals(systemOut().getHistory(), "Hashtable:\r\n"
            + "0: TOMBSTONE\r\n" + "1: 1\r\n" + "2: 2\r\n" + "3: 3\r\n"
            + "5: 10\r\n" + "total records: 4");
        hashtable.delete(2);
        systemOut().clearHistory();
        hashtable.dump();
        assertFuzzyEquals(systemOut().getHistory(), "Hashtable:\r\n"
            + "0: TOMBSTONE\r\n" + "1: 1\r\n" + "2: TOMBSTONE\r\n" + "3: 3\r\n"
            + "5: 10\r\n" + "total records: 3");
        hashtable.insert(record5);
        hashtable.delete(10);
        systemOut().clearHistory();
        hashtable.dump();
        assertEquals(systemOut().getHistory(), "Hashtable:\r\n"
            + "0: TOMBSTONE\r\n" + "1: 1\r\n" + "2: 2\r\n" + "3: 3\r\n"
            + "5: TOMBSTONE\r\n" + "total records: 3\n");
        hashtable.delete(3);
        systemOut().clearHistory();
        hashtable.dump();
        assertFuzzyEquals(systemOut().getHistory(), "Hashtable:\r\n"
            + "0: TOMBSTONE\r\n" + "1: 1\r\n" + "2: 2\r\n" + "3: TOMBSTONE\r\n"
            + "5: TOMBSTONE\r\n" + "total records: 2");
    }


    /**
     * the test method for the insert in hashtable
     */
    public void testInsert() {
        hashtable.insert(record1);
        hashtable.insert(record2);
        hashtable.insert(record3);
        Handle handle = hashtable.insert(record4);
        byte[] array = new byte[handle.getSize()];
        memManager.get(array, handle, handle.getSize());
        try {
            System.out.print(Seminar.deserialize(array));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(systemOut().getHistory(),
            "Hash table expanded to 8 records\nID: 10, Title: Overview of HPC and CSE Research at VT\r\n"
                + "Date: 0703301125, Length: 35, X: 0, Y: 0, Cost: 25\r\n"
                + "Description: Learn what kind of research is done on HPC and CSE at VT\r\n"
                + "Keywords: HPC, CSE, computer_science");
        systemOut().clearHistory();

        hashtable.dump();
        assertEquals(systemOut().getHistory(), "Hashtable:\n1: 1\r\n"
            + "2: 2\r\n" + "3: 3\r\n" + "5: 10\ntotal records: 4\n");
        systemOut().clearHistory();

    }


    /**
     * the test method for the delete in hashtable
     */
    public void testDelete() {
        hashtable.insert(record1);
        hashtable.insert(record2);
        hashtable.insert(record3);
        hashtable.insert(record4);
        hashtable.delete(2);
        hashtable.dump();
        assertEquals(systemOut().getHistory(),
            "Hash table expanded to 8 records\nHashtable:\n1: 1\r\n"
                + "2: TOMBSTONE\r\n" + "3: 3\r\n"
                + "5: 10\ntotal records: 3\n");
        systemOut().clearHistory();

    }


    /**
     * the test method for the search in hashtable
     */
    public void testSearch() {
        hashtable.insert(record1);
        hashtable.insert(record2);
        hashtable.insert(record3);
        hashtable.insert(record4);
        // hashtable not empty and exists
        Handle handle = hashtable.search(3);
        byte[] array = new byte[handle.getSize()];
        memManager.get(array, handle, handle.getSize());
        try {
            System.out.print(Seminar.deserialize(array));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(systemOut().getHistory(),
            "Hash table expanded to 8 records\nID: 3, Title: Computing Systems Research at VT\r\n"
                + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\r\n"
                + "Description: Seminar about the Computing systems research at VT\r\n"
                + "Keywords: high_performance_computing, grids, VT, computer_science");
        systemOut().clearHistory();

        // hashtable not empty and does not exists
        handle = hashtable.search(0);
        assertNull(handle);
        systemOut().getHistory();
    }
}
