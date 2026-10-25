package pl.edu.pwr.mf.jp;

import pl.edu.pwr.mf.jp.data.DataReader;
import pl.edu.pwr.mf.jp.data.Fan;
import pl.edu.pwr.mf.jp.data.Room;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private List<Room> rooms;
    private List<Fan> fans;
    private Criterion criterion;

    Menu() {
    }

    public void start() {
        loadData();
        criterion = chooseCriterion();
        runSolvers();
    }

    public void loadData() {
        while (true) {
            System.out.println("Prosze podac nazwe pliku z pomieszczeniami z katalogu data: ");
            String roomsPath = scanner.nextLine().trim();

            System.out.println("\nProsze podac nazwe pliku z wentylatorami z katalogu data: ");
            String fansPath = scanner.nextLine().trim();

            try {
                DataReader.readRooms("src/main/java/pl/edu/pwr/mf/jp/data/" + roomsPath);
                DataReader.readFans("src/main/java/pl/edu/pwr/mf/jp/data/" + fansPath);
            } catch (IOException e) {
                System.out.println("Nie udalo sie wczytac pliku/plikow");
                continue;
            }

            rooms = DataReader.getRooms();
            fans = DataReader.getFans();

            return;
        }
    }

    private Criterion chooseCriterion() {
        System.out.println("\n Jakie kryterium zastosowac?");
        System.out.println("1. Wymienic jak najwiecej powietrza, jak najnizszym kosztem");
        System.out.println("2. Dopasowac wydajnosc do kubatury pomieszczen, jak najnizszym kosztem");
        System.out.println("3. Wlasne wagi");

        int input = Integer.parseInt(scanner.nextLine().trim());

        if (input == 1) {
            return new Criterion(1, 0, 0.5, fans, rooms);
        } else if (input == 2) {
            return new Criterion(0, 1, 0.5, fans, rooms);
        }

        System.out.println("Waga wyminionego powietrza: ");
        double efficiencyWeight = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Waga odchylenia od kubatury: ");
        double deviationWeight = Double.parseDouble(scanner.nextLine().trim());

        System.out.println("Waga kosztu: ");
        double costWeight = Double.parseDouble(scanner.nextLine().trim());

        return new Criterion(efficiencyWeight, deviationWeight, costWeight, fans, rooms);
    }

    private void runSolvers() {
        System.out.println("\n Jaki algorytm?");
        System.out.println("1. Brute force");
        System.out.println("2. Algorytm zachłanny");
        System.out.println("3. Oba");

        int input = Integer.parseInt(scanner.nextLine().trim());


        if (input == 1 || input == 3) {
            System.out.println("\n Brute force:");
            System.out.println(new BruteForceSolver(rooms, fans, criterion).solve());
        }

        if (input == 2 || input == 3) {
            System.out.println("\n Algorytm zachlanny:");
            System.out.println(new GreedyAlgorithmSolver(rooms, fans, criterion).solve());
        }
    }
}
