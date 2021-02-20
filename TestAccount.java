import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/*
 * Tester to test methods and functionality of Account.java
 */
public class TestAccount {
	private Account tester;
	private Playlist test1;
	private Playlist test2;
	private Playlist test3;
	private Song inTest1;
	private Song inTest2;
	private Song inTest3;
@Before
public void setUp() throws IOException {
	tester = new Account("Test");
	test1 = new Playlist();
	test2 = new Playlist();
	test3 = new Playlist();
	inTest3 = new Song("ATest", "ATest", 0, 0);
}
@Test
public void testAddPlaylist() {
	tester.addPlaylist("Testtest1");
	tester.addPlaylist("Testtest2");
	tester.addPlaylist("Testtest3");
	assertEquals(true, tester.selectPlayList("Testtest1"));
	assertEquals(false, tester.selectPlayList("TestInvalid"));
}
@Test
public void testAddToSong() {
	tester.addToPlayList(inTest3);
	assertEquals(inTest3.toString(), test3.getRecentSong().toString());
}
@Test
public void testRemoveSongFromPlaylist() {
	tester.removeFromPlayList(inTest3);
	assertEquals(null,test3.getRecentSong());
}
@Test
public void testGetAccountCurrentPlaylistName() {
	assertEquals("test3", tester.getPlayListName("Testtest3"));
}
}
