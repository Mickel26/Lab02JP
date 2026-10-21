import Data.DataReader;
import Data.Fan;
import Data.Room;
import pl.edu.pwr.mf.jp.Solver;

void main() throws IOException {
    DataReader.readFans("src/main/java/Data/wentylatory.txt");
    DataReader.readRooms("src/main/java/Data/pomieszczenia.txt");

    List<Fan> fans = DataReader.getFans();
    List<Room> rooms = DataReader.getRooms();

    System.out.println(fans);
    System.out.println(rooms);

    Solver.solve();
}
