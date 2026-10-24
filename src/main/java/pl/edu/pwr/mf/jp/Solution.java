package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;

import java.util.List;
import java.util.Locale;

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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            Fan fan = matchedFans[i];

            sb.append("Pomieszczenie ").append(room.id()).append(" -> wentylator ").append(fan.id()).append(" wydajność: ").append(fan.efficiency()).append(", koszt: ").append(fan.cost()).append("\n");
        }

        sb.append("Kryterium: ").append(String.format(Locale.US, "%.2f", score)).append("\n");
        sb.append("Czas: ").append(time).append(" ms");

        return sb.toString();
    }
}
