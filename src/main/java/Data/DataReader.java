package Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    private static List<Fan> fans = null;
    private static List<Room> rooms = null;

    public static List<Fan> getFans() {
        return fans;
    }

    public static List<Room> getRooms() {
        return rooms;
    }

    public static void readFans(String path) throws IOException {
        List<String> contents = Files.readAllLines(Paths.get(path));
        fans = new ArrayList<>();

        for (String content : contents) {
            String[] t = content.split(",");
            fans.add(new Fan(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]), Integer.parseInt(t[3])));
        }
    }

    public static void readRooms(String path) throws IOException {
        List<String> contents = Files.readAllLines(Paths.get(path));
        rooms = new ArrayList<>();

        for (String content : contents) {
            String[] t = content.split(",");
            rooms.add(new Room(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2])));
        }
    }
}
