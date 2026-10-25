package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSolver extends Solver{
    private boolean[] used;
    private List<Fan> matched;
    private List<Fan> bestFans;
    private double bestPenalty;

    BruteForceSolver(List<Room> rooms, List<Fan> fans, Criterion criterion) {
        super(rooms, fans, criterion);
    }

    private void search() {
        if(matched.size() == rooms.size()) {
            double penalty = criterion.planPenalty(rooms, matched);

            if(penalty < bestPenalty) {
                bestPenalty = penalty;
                bestFans = new ArrayList<>(matched);
            }
            return;
        }

        Room room = rooms.get(matched.size());

        for (int i = 0; i < fans.size(); i++) {
            if (used[i] || !criterion.fanFits(fans.get(i), room)) {
                continue;
            }

            used[i] = true;
            matched.add(fans.get(i));

            search();

            matched.removeLast();
            used[i] = false;
        }
    }

    @Override
    Solution solve() {
        used = new boolean[fans.size()];
        matched = new ArrayList<>();
        bestFans = null;
        bestPenalty = Double.MAX_VALUE;

        long start = System.currentTimeMillis();
        search();
        long time = System.currentTimeMillis() - start;

        if (bestFans == null) {
            throw new RuntimeException("Nie udalo sei znalezc rozwiazania");
        }

        return new Solution(rooms, bestFans, bestPenalty, time);
    }
}
