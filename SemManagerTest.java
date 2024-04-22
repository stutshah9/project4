import student.TestCase;

/**
 * Tests the main method of the program
 * 
 * @author Stuti Shah & Lauren Spehlmann
 * @version 4/19/2024
 */
public class SemManagerTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testMInitx()
    {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        String[] args = {"2", "2", "P1Sample_inputX.txt"};
        SemManager.main(args);
    }
}
