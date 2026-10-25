package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.DataReader;
import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        DataReader.readFans("src/main/java/pl/edu/pwr/mf/jp/data/small/wentylatory.txt");
        DataReader.readRooms("src/main/java/pl/edu/pwr/mf/jp/data/small/pomieszczenia.txt");

        List<Fan> fans = DataReader.getFans();
        List<Room> rooms = DataReader.getRooms();

        //System.out.println(fans);
        //System.out.println(rooms);

        Criterion maxVolumeMinCost = new Criterion(1, 0, 0.5, fans, rooms); //najmocniejsze wentylaaotry do najwiekszych pokoi
        Criterion minDeviationMinCost = new Criterion(0, 1, 0.5, fans, rooms); //wydajnosc ma byc jak najblizsza kuabturze

        BruteForceSolver bruteforce = new BruteForceSolver(rooms, fans, maxVolumeMinCost);
        BruteForceSolver bruteforce1 = new BruteForceSolver(rooms, fans, minDeviationMinCost);

        Solution bruteforceSolution = bruteforce.solve();
        Solution bruteforceSolution1 = bruteforce1.solve();

        System.out.println(bruteforceSolution.toString());
        System.out.println(bruteforceSolution1.toString());
    }
}
