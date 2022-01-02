package test.persistence;

import main.persistence.FavouritesWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class FavouritesWriterTest {

    @Test
    public void testGetFavourites() throws FileNotFoundException {
        FavouritesWriter favouritesWriter = new FavouritesWriter("data/testFavourites.json");
        Assertions.assertEquals(0, favouritesWriter.getFavourites().length);
    }

    @Test
    public void testaddFavouritesThenRemove() throws FileNotFoundException {
        FavouritesWriter favouritesWriter = new FavouritesWriter("data/testFavourites.json");

        favouritesWriter.addToFavourites("AAPL");
        Assertions.assertArrayEquals(new String[]{"AAPL"}, favouritesWriter.getFavourites());

        favouritesWriter.addToFavourites("GME");
        Assertions.assertArrayEquals(new String[]{"AAPL", "GME"}, favouritesWriter.getFavourites());

        favouritesWriter.removeFromFavourites("AAPL");
        Assertions.assertArrayEquals(new String[]{"GME"}, favouritesWriter.getFavourites());

        favouritesWriter.removeFromFavourites("GME");
        Assertions.assertArrayEquals(new String[]{}, favouritesWriter.getFavourites());
    }
}
