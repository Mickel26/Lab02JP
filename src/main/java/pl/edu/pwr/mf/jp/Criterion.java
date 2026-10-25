package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;

import java.util.List;

public class Criterion {
    private final double efficiencyWeight;
    private final double deviationWeight;
    private final double costWeight;

    private final double maxCost;

    public Criterion(double efficiencyWeight, double deviationWeight, double costWeight, List<Fan> fans){
        this.efficiencyWeight = efficiencyWeight;
        this.deviationWeight = deviationWeight;
        this.costWeight = costWeight;

        int max = 0;

        for (Fan fan : fans) {
            if (fan.cost() > max) {
                max = fan.cost();
            }
        }

        this.maxCost = max;
    }

    public boolean fanFits(Fan fan, Room room) {
        return fan.size() <= room.size();
    }

    public double pairPenalty(Fan fan, Room room) {
        double efficiency = -(double) fan.efficiency() / room.volume();
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
