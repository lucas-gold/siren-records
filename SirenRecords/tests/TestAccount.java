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
	private Song song1;
	private Song song2;
	private Song song3;
	private Song longSong;
@Before
public void setUp() throws IOException {
	tester = new Account("Test");
	test1 = new Playlist();
	test2 = new Playlist();
	test3 = new Playlist();
	song1 = new Song("First", "Song", 0, 0);
	song2 = new Song("Second", "Song", 0, 0);
	song3 = new Song("Third", "Song", 0, 0);
	longSong = new Song("A", "B", 2019, 4000);
}
@Test
public void testAddPlaylist() {
	tester.addPlaylist("Testtest1");
	assertEquals(true, tester.selectPlayList("Testtest1"));
	assertEquals(false, tester.selectPlayList("TestInvalid"));
}
@Test
public void testAddToSong() {
	tester.addPlaylist("Testtest2");
	tester.selectPlayList("Testtest2");
	tester.addToPlayList(song3);
	assertEquals(song3.toString(), tester.getCurrent_list().getRecentSong().toString());
}
@Test
public void testRemoveSongFromPlaylist() {
	tester.addPlaylist("Testtest3");
	tester.addToPlayList(song2);
	tester.addToPlayList(song3);
	tester.removeFromPlayList(song3);
	assertEquals(song2,tester.getCurrent_list().getRecentSong());
}
@Test
public void testGetAccountCurrentPlaylistName() {
	assertEquals("test3", tester.getPlayListName("Testtest3"));
}
@Test
public void testAddPlaylistToQueue() {
        tester.addPlaylist("QueueTest");
        tester.addToPlayList(longSong);
        assertEquals(true, tester.playPlayList());
}
@Test
public void testAddSongToQueue() {
        tester.addPlaylist("QueueSongAddTest");
        tester.addToPlayList(longSong);
        tester.playPlayList();
        assertEquals(true, tester.addToQueue(song2));
}
@Test
public void testSkipSongInQueue() {
        tester.addPlaylist("QueueSkipTest");
        tester.addToPlayList(longSong);
        tester.addToPlayList(song1);
        tester.addToPlayList(song2);
        tester.playPlayList();
        assertEquals(longSong, tester.skipCurrentSong());
        assertEquals(song1, tester.skipCurrentSong());
}	
}
