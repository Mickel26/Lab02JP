import pl.edu.pwr.mf.jp.data.DataReader;
import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;
import pl.edu.pwr.mf.jp.Solver;

void main() throws IOException {
    DataReader.readFans("src/main/java/pl/edu/pwr/mf/jp/data/wentylatory.txt");
    DataReader.readRooms("src/main/java/pl/edu/pwr/mf/jp/data/pomieszczenia.txt");

    List<Fan> fans = DataReader.getFans();
    List<Room> rooms = DataReader.getRooms();

    System.out.println(fans);
    System.out.println(rooms);
}
