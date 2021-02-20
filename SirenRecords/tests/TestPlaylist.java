import static org.junit.Assert.*;				
import org.junit.Test;

public class TestPlaylist {
	/*
	 * Testers for testing each methods of our playlist object
	 */
	@Test
	public void testToString() {
		Playlist list = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		list.add(song);
		StringBuilder playlistAsString = new StringBuilder("Playlist:\n");
		playlistAsString.append(song.toString());
		playlistAsString.append("\n");
		assertEquals(playlistAsString.toString(), list.toString());
	}

	@Test
	public void testLinkedList() {
		Playlist playlist = new Playlist();
		assertEquals(true, playlist.playlist.isEmpty());
	}

	@Test
	public void testAdd() {
		Playlist list = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		list.add(song);
		assertEquals(song, list.getRecentSong());
	}

	@Test
	public void testDelete() {
		Playlist list = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		Song song2 = new Song("Test2", "TestArtist2", 2020, 570);
		list.add(song);
		list.add(song2);
		list.delete(song2);
		assertEquals(song.toString(), list.getRecentSong().toString());
	}

	@Test
	public void testGetPlaylistLength() {
		Playlist list = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		Song song2 = new Song("ATest2", "TestArtist2", 2020, 570);
		list.add(song);
		list.add(song2);
		assertEquals(false, list.getPlaylistLength());
	}

	@Test
	public void testSortByName() {
		Playlist list = new Playlist();
		Playlist sortedList = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		Song song2 = new Song("ATest2", "TestArtist2", 2020, 570);
		list.add(song);
		list.add(song2);
		sortedList.add(song2);
		sortedList.add(song);
		list.sortByName();
		assertEquals(sortedList.toString(), list.toString());
	}

	@Test
	public void testSortByArtist() {
		Playlist list = new Playlist();
		Playlist sortedList = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		Song song2 = new Song("ATest2", "ATestArtist2", 2020, 570);
		list.add(song);
		list.add(song2);
		sortedList.add(song2);
		sortedList.add(song);
		list.sortByArtist();
		assertEquals(sortedList.toString(), list.toString());
	}

	@Test
	public void testSortByYearOldtoNew() {
		Playlist list = new Playlist();
		Playlist sortedList = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		Song song2 = new Song("ATest2", "ATestArtist2", 2018, 570);
		list.add(song);
		list.add(song2);
		sortedList.add(song2);
		sortedList.add(song);
		list.sortByYearOldtoNew();
		assertEquals(sortedList.toString(), list.toString());
	}

	@Test
	public void testSortByYearNewtoOld() {
		Playlist list = new Playlist();
		Playlist sortedList = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		Song song2 = new Song("ATest2", "ATestArtist2", 2018, 570);
		list.add(song);
		list.add(song2);
		sortedList.add(song);
		sortedList.add(song2);
		list.sortByYearNewtoOld();
		assertEquals(sortedList.toString(), list.toString());
	}

	@Test
	public void testSortByLength() {
		Playlist list = new Playlist();
		Playlist sortedList = new Playlist();
		Song song = new Song("Test", "TestArtist", 2019, 560);
		Song song2 = new Song("ATest2", "ATestArtist2", 2018, 570);
		list.add(song2);
		list.add(song);
		sortedList.add(song);
		sortedList.add(song2);
		list.sortByLength();
		assertEquals(sortedList.toString(), list.toString());
	}

	@Test
	public void testPlaylistIsValidByLength() {
		Playlist list = new Playlist();
		Song LongSong = new Song("A", "B", 2019, 4000);
		list.add(LongSong);
		assertEquals(true, list.getPlaylistLength());
	}
}
