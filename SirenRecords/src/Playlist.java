import java.util.*;
//import static org.junit.Assert.*;
//import org.junit.Test;

public class Playlist implements java.io.Serializable {
	
	public static final long OneHOUR = (long) 3600;
	public static final long ThreeHOUR = (long) 10800;
	
    LinkedList<Song> playlist;
    Long playlistLength;
    
    public Playlist(){
        playlist = new LinkedList<Song>();
        playlistLength = (long)0;
 
    }

    public boolean add(Song song){
    	this.playlistLength += song.getLength();
        return this.playlist.add(song);     
    }
    
    /*
     * Gets the most recent(last) song added to the playlist
     */
  public Song getRecentSong() {
	  return this.playlist.getLast();
  }
    
    /*
     * Sort playlist based on Song's name
     */
    public void sortByName() {
    	Collections.sort(this.playlist, Song.SongNameComparator);
    }
    
    /*
     * Sort playlist based on Artists name
     */
    public void sortByArtist() {
    	Collections.sort(this.playlist, Song.SongArtistComparator);
    }
    
    /*
     * Sort playlist from oldest to newest songs based on year of production
     */
    public void sortByYearOldtoNew() {
    	Collections.sort(this.playlist, Song.SongYearComparatorOtoN);
    }
    
    /*
     * Sort playlist from newest to oldest songs based on year of production
     */
    public void sortByYearNewtoOld() {
    	Collections.sort(this.playlist, Song.SongYearComparatorNtoO);
    }
    
    /*
     * Sort playlist based on songs length
     */
    public void sortByLength() {
    	Collections.sort(this.playlist, Song.SongLengthComparator);
    }
    
    /*
     * Remove song from current playlist
     */
    public boolean delete(Song song) { 
    	return this.playlist.remove(song);
    	}
    public boolean searchPlaylist(Playlist playlist) {
    	if(playlist != null)
    		return true;
    	else
    		return false;
    }
    
    /*
     * Checks to see if length of playlist is between 1 to 3 hours
     * @return return false if not return true if successful
     */
    public boolean getPlaylistLength() {
    	return (this.playlistLength >= OneHOUR && this.playlistLength <= ThreeHOUR) ? true : false;
    }
    
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("Playlist:\n");
        for(int i=0; i<this.playlist.size(); i++){
            string.append(this.playlist.get(i).toString());
            string.append("\n");
        }
        return string.toString();
    }
 
}