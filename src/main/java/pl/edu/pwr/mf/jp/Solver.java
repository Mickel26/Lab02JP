package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;

import java.util.List;

public abstract class Solver {
    protected List<Room> rooms;
    protected List<Fan> fans;

    public Solver(List<Room> rooms, List<Fan> fans){
        this.rooms = rooms;
        this.fans = fans;
    }

    abstract Solution solve();

}
