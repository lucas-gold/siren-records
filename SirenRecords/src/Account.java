import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class Account implements java.io.Serializable {

    private String name;
    private HashMap<String, Playlist> playlists;
    transient private Playlist current_list;
    //private String serial_file;
    private Playlist current_queue; 

    public Account(String name) throws IOException {
        this.name = name;
        playlists = new HashMap<String, Playlist>();
        current_list = null;
        current_queue = null;
    }

    public Playlist getCurrent_list() { return current_list; }

    public String getName(){ return name; }

    public int playlistsSize(){
        return playlists.size();
    }

    /*
    * adds a new, empty playlist to the users hashtable of playlists
    * @param playlist_name is used in the name of the playlist (username+playlist_name)
    */
    public void addPlaylist(String playlist_name){
        String code = this.name +  playlist_name;
        playlists.put(code, new Playlist());
        current_list = playlists.get(code);
    }

    /*
    * add a song to the current playlist
    * @param song the song to add to the playlist
    */
    public boolean addToPlayList(Song song) {

        if (current_list != null){
            this.current_list.add(song);
            return true;
        }
        return false;
    }

    /*
     * select a playlist of a given name as current
     * @param playlist_name the name of the playlist to be accessed
     * @return true if successful, false otherwise
     */
    public boolean selectPlayList(String playlist_name){
        String code = this.name + playlist_name;
        if (playlists.containsKey(code)) {
            current_list = playlists.get(code);
            return true;
        }
        return false;
    }

    /*
    * delete a song from the currently selected playlist
    * @param song the song to delete
    */
    public void removeFromPlayList(Song song) {
        if (current_list != null){
            current_list.delete(song);
        }
    }

    /*
     * get the name of the playlist--note that the username is prepended to the playlist name when
     * added to the HashMap. This must be removed
     * @param l_name the name of the playlist including the prepended username
     */
    public String getPlayListName(String l_name){
        return l_name.substring(this.name.length());
    }

    /*
     * return the contents of the currently selected playlist
     * @return a string representation of the playlist, or "Empty" if the playlist is empty
     */
    public String printPlayList(){
        if (current_list != null){
            return current_list.toString();
        }
        return "Empty.";
    }

    /*
     * return a list of all the playlists the user has
     * @return a single column list of strings of all the playlist names
     */
    public String printPlayLists(){
        StringBuilder lists = new StringBuilder("");
        Iterator it = this.playlists.keySet().iterator();

        for (Map.Entry<String, Playlist> list : playlists.entrySet()){
            lists.append(getPlayListName(list.getKey()));
            lists.append("\n");
        }

        return lists.toString();
    }

    /*
     * Send the current playlist to the song queue
     * @return true if playlist exists and is between 1 and 3 hours, otherwise false
     */
    public boolean playPlayList() {	
    	if (current_list != null && current_list.getPlaylistLength()) {
    		current_queue = current_list;
    		return true;
    	}
    	return false;	
    }
    /*
     * Add song to end of queue 
     * @param song to add
     * @return true if queue is not empty and song is added
     */
    public boolean addToQueue(Song song) {
    	if (current_queue != null){
    		return current_queue.add(song);
    	}
    	return false;
    }
    /*
     * Skip next song in queue
     * @return skipped song
     */
    public Song skipCurrentSong() {
    	if (current_queue != null){
            return current_queue.playlist.removeFirst();
        }
    	return null;
    }


}

