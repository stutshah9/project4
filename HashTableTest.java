import student.TestCase;

/**
 * The hashtable test class
 *
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/17/2024
 */
public class HashTableTest extends TestCase {

    private HashTable hashtable;
    private Seminar handle1;
    private Seminar handle2;
    private Seminar handle3;
    private Seminar handle4;
    private Record record1;
    private Record record2;
    private Record record3;
    private Record record4;

    public void setUp() {
        hashtable = new HashTable(8);
        handle1 = new Seminar(1, "Overview of HCI Research at VT", "0610051600",
            90, (short)10, (short)10, 45, new String[] { "HCI",
                "Computer_Science", "VT", "Virginia_Tech" },
            "This seminar will present an overview of HCI research at VT");

        handle2 = new Seminar(2,
            "Computational Biology and Bioinformatics in CS at Virginia Tech",
            "0610071600", 60, (short)20, (short)10, 30, new String[] {
                "Bioinformatics", "computation_biology", "Biology",
                "Computer_Science", "VT", "Virginia_Tech" },
            "Introduction to bioinformatics and computation biology");

        handle3 = new Seminar(3, "Computing Systems Research at VT",
            "0701250830", 30, (short)30, (short)10, 17, new String[] {
                "high_performance_computing", "grids", "VT",
                "computer_science" },
            "Seminar about the Computing systems research at VT");

        handle4 = new Seminar(10, "Overview of HPC and CSE Research at VT",
            "0703301125", 35, (short)0, (short)0, 25, new String[] { "HPC",
                "CSE", "computer_science" },
            "Learn what kind of research is done on HPC and CSE at VT");
        record1 = new Record(handle1, 1);
        record2 = new Record(handle2, 2);
        record3 = new Record(handle3, 3);
        record4 = new Record(handle4, 10);

        hashtable.insert(record1);
        hashtable.insert(record2);
        hashtable.insert(record3);
        hashtable.insert(record4);
    }


    public void testInsert() {

        System.out.print(hashtable.printHashtable());
        assertEquals(systemOut().getHistory(), "1: 1\r\n" + "2: 2\r\n"
            + "3: 3\r\n" + "5: 10\n");
        systemOut().clearHistory();

    }


    public void testRemove() {
        hashtable.remove(2);
        System.out.print(hashtable.printHashtable());
        assertEquals(systemOut().getHistory(), "1: 1\r\n" + "2: TOMBSTONE\r\n"
            + "3: 3\r\n" + "5: 10\n");
        systemOut().clearHistory();
    }


    public void testSearch() {
        System.out.print(hashtable.search(3));
        assertEquals(systemOut().getHistory(),
            "ID: 3, Title: Computing Systems Research at VT\r\n"
                + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\r\n"
                + "Description: Seminar about the Computing systems research at VT\r\n"
                + "Keywords: high_performance_computing, grids, VT, computer_science");
        systemOut().clearHistory();

    }
}
