package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;

import java.util.List;

public class Solution {
    private final Fan[] matchedFans;
    private final double score;
    private final long time;
    private final List<Room> rooms;

    Solution(List<Room> rooms, Fan[] matchedFans, double score, long time){
        this.rooms = rooms;
        this.matchedFans = matchedFans;
        this.score = score;
        this.time = time;
    }

    public Fan[] getMatchedFans() {
        return matchedFans;
    }

    public double getScore() {
        return score;
    }

    public long getTime() {
        return time;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
