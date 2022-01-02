package main.persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FavouritesWriter {

    private String destination;
    private PrintWriter writer;

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public static JSONArray getFavourites() {
        File file = new File("data/favourites.json");
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            JSONArray ListOfFavourites = json.getJSONArray("favouritesList");
            return ListOfFavourites;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray addToFavourites(String ticker) {
        return getFavourites().put(ticker);
    }

    public static void main(String[] args) {
        addToFavourites("AAAA");

    }
}
