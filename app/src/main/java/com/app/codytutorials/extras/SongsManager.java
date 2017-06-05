package com.app.codytutorials.extras;



import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager {
    // SDCard Path
    private final String MEDIA_PATH = new String("/storage/sdcard0/Music");                  ///("/storage/sdcard0/MusicVk/");
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    // Constructor
    public SongsManager(){

    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */
    public ArrayList<HashMap<String, String>> getPlayList(){
        File home = new File(String.valueOf(MEDIA_PATH));

//        AssetManager myAssetManager = getContext().getAssets();
//        try {
//            String[] files = myAssetManager.list("javamedia"); // массив имен файлов
//            for (String title : files){
//                data.add(new DTO(title));
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                HashMap<String, String> song = new HashMap<String, String>();
                song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
                song.put("songPath", file.getPath());

                // Adding each song to SongList
                songsList.add(song);
            }
        }

        return songsList;
    }

    /**
     * Class to filter files which are having .mp3 extension
     * */
    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
}
