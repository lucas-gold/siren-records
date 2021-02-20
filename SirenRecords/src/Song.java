import java.util.Comparator;
//import static org.junit.Assert.*;
//import org.junit.Test;

public class Song implements java.io.Serializable {
    /*
    * The name of the song
    */
    String name;

    /*
    * the songs year of release
    */
    int year;

    /*
    * the songs artist
    */
    String artist;

    /*
    * the songs length in seconds
    */
    long length;

    public Song(String name, String artist, int year, long length){

        this.name = name;
        this.artist = artist;
        this.year = year;
        this.length = length;

    }
    

    /*
     * Get name of a song
     */
    public String getName() {
    	return this.name;
    }
    
    /*
     * Get artist of a song
     */
    public String getArtist() {
    	return this.artist;
    }
    
    /*
     * Get a song's production year
     */
    public int getYear() {
    	return this.year;
    }
    
    /*
     * Get length of a song
     */
    public long getLength() {
    	return this.length;
    }
    
    /*
     * compare two songs based on their name, used to sort playlists in alphabetical order 
     */
    public static Comparator<Song> SongNameComparator = new Comparator<Song>() {
    	public int compare(Song s1, Song s2) {
    		return (s1.getName().toUpperCase()).compareTo(s2.getName().toUpperCase());
    	}
    };
    
    /*
     * compare two songs based on their artists and sort playlists based on their artists in alphabetical order
     */
    public static Comparator<Song> SongArtistComparator = new Comparator<Song>() {
    	public int compare(Song s1, Song s2) {
    		return (s1.getName().toUpperCase()).compareTo(s2.getName().toUpperCase());
    	}
    };
    
    /*
     * compare songs year of production and sort playlist in ascending order (from oldest to newest songs)
     */
    public static Comparator<Song> SongYearComparatorOtoN = new Comparator<Song>() {
    	public int compare(Song s1, Song s2) {
    		return (s1.getYear() - s2.getYear());
    	}
    };
    
    /*
     * compare songs year of production and sort playlist in descending order (from newest to oldest songs)
     */
    public static Comparator<Song> SongYearComparatorNtoO = new Comparator<Song>() {
    	public int compare(Song s1, Song s2) {
    		return (s2.getYear() - s1.getYear());
    	}
    };
    
    /*
     * Compare songs based on length, sorts playlists starting from shortest songs to longest
     */
    public static Comparator<Song> SongLengthComparator = new Comparator<Song>() {
    	public int compare(Song s1, Song s2) {
    		return s1.getLength() < s2.getLength()?-1:s1.getLength() > s2.getLength()?1:0;
    	}
    };

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("name: ");

        string.append(this.name);
        string.append(", artist: ");
        string.append(this.artist);
        string.append(", year: ");
        string.append(this.year);
        string.append(", length: ");
        string.append(this.length);

        return string.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Song)) {
            return false;
        }

        Song o_song = (Song) o;

        return o_song.name.equals(this.name) &&
                o_song.artist.equals(this.artist) &&
                o_song.year == this.year &&
                o_song.length == this.length;
    }
    
    
    @Override
    public int hashCode() {
        int result = 31;
        result = 17 * result + this.name.hashCode();
        result = 17 * result + this.artist.hashCode();
        result = 17 * result + this.year;
        result = 17 * result + (int)this.length;
        return result;
    }
    
}