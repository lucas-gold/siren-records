import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestSong {
	private Song tester;
    /*
     * Test cases for testing Song class method
     */
	@Before
	public void setUp() {
	 	tester = new Song("Test", "TestArtist", 2019, 560);
	}
    @Test
    public void testSongName() {
   
    	assertEquals("Test", tester.getName());
    }
    @Test
    public void testSongArtist() {
    	assertEquals("TestArtist", tester.getArtist());
    }
    @Test
    public void testSongYear() {
    	assertEquals(2019, tester.getYear());
    }
    @Test
    public void testSongLength() {
    	assertEquals(560, tester.getLength());
    }
    @Test
    public void testToString() {
    	assertEquals("name: " + tester.getName() + ", artist: " + tester.getArtist() + ", year: " + tester.getYear() + ", length: " + tester.getLength(), 
    			"name: Test, artist: TestArtist, year: 2019, length: 560");
    }

}
