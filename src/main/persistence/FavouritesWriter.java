package main.persistence;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FavouritesWriter {

    private final String DESTINATION;
    private final String KEY = "favouritesList";
    private File file;

    // EFFECTS: constructs writer to write to destination file
    public FavouritesWriter(String destination) throws FileNotFoundException {
        this.DESTINATION = destination;
        file = new File(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(JSONObject jsonObj) {
        try (FileWriter file = new FileWriter(DESTINATION)) {
            file.write(String.valueOf(jsonObj));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: return tickers saved in favourites
    public String[] getFavourites() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            JSONArray favourites = json.getJSONArray(KEY);

            String[] tickers = new String[favourites.length()];
            for (int i = 0; i < tickers.length; i++) {
                tickers[i] = favourites.optString(i);
            }
            return tickers;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // REQUIRES: a valid ticker
    // MODIFIES: this
    // EFFECTS: adds a given ticker to list of favourite tickers
    public void addToFavourites(String ticker) throws FileNotFoundException {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            JSONArray favourites = json.getJSONArray(KEY);
            json.put(KEY, favourites.put(ticker));
            saveToFile(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // REQUIRES: ticker exists in favourite already
    // MODIFIES: this
    // EFFECTS: remove a given ticker to list of favourite tickers
    public void removeFromFavourites(String ticker) throws FileNotFoundException {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            JSONArray favourites = json.getJSONArray(KEY);

            for (int i = 0; i < favourites.length(); i++) {
                if (favourites.get(i).equals(ticker)) {
                    favourites.remove(i);
                }
            }
            json.put(KEY, favourites);
            saveToFile(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
