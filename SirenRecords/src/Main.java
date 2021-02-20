import java.util.Scanner;

public class Main {
    /*
     *
     * This is a temporary Command line tool used to interact with the system until there is a GUI
     * See below for usage
     *
     */

    enum actions{
        SIGNUP,
        LOGIN,
        PLAYLIST,
        MOD,
        SORT,
    }

    public static String[] simpleParse(actions command, String input){
        String[] parsed;

        parsed = input.split("\\|");

        if ((command.equals("-a") || command.equals("-d")) && parsed.length != 4) {
            System.out.println("Invalid number of arguments:" + parsed.length);
            return null;
        } else if ((command == actions.LOGIN || command == actions.SIGNUP) && parsed.length != 2){
            System.out.println("Invalid number of arguments:" + parsed.length);
            return null;
        } else if (command == actions.PLAYLIST && parsed.length != 1){
            System.out.println("Invalid number of arguments:" + parsed.length);
            return null;
        } else if (command == actions.SORT && parsed.length != 1){
            System.out.println("Invalid number of arguments:" + parsed.length);
            return null;
        }

        return parsed;
    }


    /*
     * A simple command line tool for creating an account and adding, deleting, and printing songs in a playlist
     *
     * Usage: type -?  to see usage
     */
    public static void main(String[] _args) {

        Song song;
        Scanner scan = new Scanner(System.in);
        String input;
        String[] args;

        //login state
        SirenRecords state = new SirenRecords();

        while(true) {

            input = scan.nextLine();

            if (input.startsWith("--signup")){

                if (state.signedIn()){
                    System.out.println("Already signed in.");
                } else {
                    args = input.length() > 9 ? simpleParse(actions.SIGNUP, input.substring(9)) : null;
                    if (args != null) {
                        if (!state.signup(args[0], args[1])) {
                            System.out.println("Signup error. Try again.");
                        } else {
                            System.out.println("Signup successful.");
                        }
                    }
                }

            } else if (input.startsWith("--login")) {

                if (state.signedIn()){
                    System.out.println("Already signed in.");
                } else {
                    args = input.length() > 8 ? simpleParse(actions.LOGIN, input.substring(8)) : null;
                    if (args != null) {
                        if (!state.login(args[0], args[1])) {
                            System.out.println("Login error. Try again.");
                        } else {
                            System.out.println("Logged in.");
                        }
                    }
                }

            } else if (input.startsWith("--logout")) {

                if (!state.signedIn()){
                    System.out.println("Not signed in.");
                } else {
                    state.logout();
                }

            } else if (input.startsWith("--playlist")) {

                if (!state.signedIn()){
                    System.out.println("Not signed in.");
                } else {
                    args = input.length() > 11 ? simpleParse(actions.PLAYLIST, input.substring(11)) : null;
                    if (args != null){
                        if(!state.selectPlayList(args[0])) {
                            System.out.println("Playlist not found.");
                        }
                    }
                }

            } else if (input.startsWith("--new")) {

                if (!state.signedIn()){
                    System.out.println("Not signed in.");
                } else {
                    args = input.length() > 6 ? simpleParse(actions.PLAYLIST, input.substring(6)) : null;
                    if (args != null){
                        state.addPlaylist(args[0]);
                        System.out.println("Playlist added.");
                    }
                }

            } else if (input.startsWith("--add")) {

                if (!state.signedIn()){
                    System.out.println("Not signed in.");
                } else {
                    args = input.length() > 6 ? simpleParse(actions.MOD, input.substring(6)) : null;

                    if (args != null) {
                        if(!state.addToPlayList(new Song(args[0], args[1], Integer.parseInt(args[2]), Long.parseLong(args[3])))) {
                            System.out.println("Playlist not selected");
                        } else {
                            System.out.println("Song added.");
                        }
                    }
                }

            } else if (input.startsWith("--delete")) {

                if (!state.signedIn()){
                    System.out.println("Not signed in.");
                } else {
                    args = input.length() > 9 ? simpleParse(actions.MOD,input.substring(9)) : null;

                    if (args != null){
                        state.removeFromPlayList(new Song(args[0], args[1], Integer.parseInt(args[2]), Long.parseLong(args[3])));
                        System.out.println("Song deleted");
                    }
                }

            } else if (input.startsWith("--print")) {

                if (!state.signedIn()){
                    System.out.println("Not signed in.");
                } else {
                    System.out.println(state.printPlayList());
                }

            } else if(input.startsWith("--sort")) {
                args = input.length() > 7 ? simpleParse(actions.SORT, input.substring(7)) : null;
                if(args != null) {
                    if (args[0].equals("SortByYearOldtoNew")) {
                        state.currentPlayList().sortByYearOldtoNew();
                    } else if (args[0].equals("MostRecent")) {
                        state.currentPlayList().sortByYearNewtoOld();
                    } else if (args[0].equals("SortByYearArtist")) {
                        state.currentPlayList().sortByArtist();
                    } else if (args[0].equals("SortAlphabetically")) {
                        state.currentPlayList().sortByName();
                    } else if (args[0].equals("SortShortestToLongest")) {
                        state.currentPlayList().sortByLength();
                    } else {
                        System.out.println("Sorting playlist unssuccesful, please enter valid argument");
                    }
                }
            } else if (input.startsWith("--show")) {

                if (!state.signedIn()){
                    System.out.println("Not signed in.");
                } else {
                    System.out.println(state.printPlayLists());
                }

            } else if (input.startsWith("--quit")) {
                if (state.signedIn()){
                    state.logout();
                }
                break;
            } else if (input.startsWith("--help")) {
                System.out.println( "--signup username|password ---------signup with given credentials\n"+
                        "--login username|password ----------login with given credentials\n"+
                        "--logout ---------------------------logout\n"+
                        "--playlist name --------------------select the playlist with the given name\n"+
                        "--new name -------------------------add a playlist with the given name\n"+
                        "--add name|artist|year|length ------add a song to the currently selected playlist\n"+
                        "--delete name|artist|year|length ---delete a song from the currently selected playlist\n"+
                        "--print ----------------------------print the currently selected playlist\n"+
                        "--sort criteria --------------------sort by the given criteria\n" +
                        "--show -----------------------------print all the playlists for the current user\n"+
                        "--quit -----------------------------quit the shell\n"+
                        "--help -----------------------------show this help message");
            }else {
                System.out.println("Invalid command. See --help");
            }
        }
    }
}



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            	
