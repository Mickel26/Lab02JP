package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;

import java.util.List;

public class Criterion {
    private final double efficiencyWeight;
    private final double deviationWeight;
    private final double costWeight;

    private final double maxCost;
    private final double maxVolume;

    public Criterion(double efficiencyWeight, double deviationWeight, double costWeight, List<Fan> fans, List<Room> rooms){
        this.efficiencyWeight = efficiencyWeight;
        this.deviationWeight = deviationWeight;
        this.costWeight = costWeight;


        int maxC = 0;

        for (Fan fan : fans) {
            if (fan.cost() > maxC) {
                maxC = fan.cost();
            }
        }

        this.maxCost = maxC;

        int maxV = 0;
        for (Room room : rooms) {
            if (room.volume() > maxV) {
                maxV = room.volume();
            }
        }

        this.maxVolume = maxV;
    }

    public boolean fanFits(Fan fan, Room room) {
        return fan.size() <= room.size();
    }

    public double pairPenalty(Fan fan, Room room) {
        double efficiency = -(double) Math.min(fan.efficiency(), room.volume()) / maxVolume;
        double deviation = Math.abs(fan.efficiency() - room.volume()) / (double) room.volume();
        double cost = fan.cost() / maxCost;

        return efficiencyWeight * efficiency + deviationWeight * deviation + costWeight * cost;
    }

    public double planPenalty(List<Room> rooms, List<Fan> fans) {
        double sum = 0.0;

        for (int i = 0; i < rooms.size(); i++) {
            if (!fanFits(fans.get(i), rooms.get(i))) {
                return Double.MAX_VALUE;
            }
            sum += pairPenalty(fans.get(i), rooms.get(i));
        }

        return sum;
    }
}
