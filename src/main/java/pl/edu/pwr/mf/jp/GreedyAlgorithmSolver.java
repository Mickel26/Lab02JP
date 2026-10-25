package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;

import java.util.ArrayList;
import java.util.List;

public class GreedyAlgorithmSolver extends Solver{

    public GreedyAlgorithmSolver(List<Room> rooms, List<Fan> fans, Criterion criterion) {
        super(rooms, fans, criterion);
    }

    @Override
    public Solution solve() {
        boolean[] used = new boolean[fans.size()];
        List<Fan> matched = new ArrayList<>();

        long start = System.currentTimeMillis();

        for (Room room : rooms) {
            int bestId = -1;
            double bestPenalty = Double.MAX_VALUE;

            for (int i = 0; i < fans.size(); i++) {
                if (used[i] || !criterion.fanFits(fans.get(i), room)) {
                    continue;
                }

                if (criterion.pairPenalty(fans.get(i), room) < bestPenalty) {
                    bestId = i;
                    bestPenalty = criterion.pairPenalty(fans.get(i), room);
                }
            }

            if (bestId == -1) {
                throw new RuntimeException("Nie udalo sie znalezc rozwiazania");
            }

            used[bestId] = true;
            matched.add(fans.get(bestId));
        }

        long time = System.currentTimeMillis() - start;
        double penalty = criterion.planPenalty(rooms, matched);

        return new Solution(rooms, matched, penalty, time);
    }
}
