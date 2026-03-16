package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


 // Singleton class that manages the championship.
 
public class ChampionshipManager {
    private static ChampionshipManager instance;
    private List<Driver> drivers;
    private List<RaceResult> raceResults;

    private ChampionshipManager() {
        drivers = new ArrayList<>();
        raceResults = new ArrayList<>();
    }

    public static ChampionshipManager getInstance() {
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    public void registerDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }
        drivers.add(driver);
    }

    public void addRaceResult(RaceResult result) {
        if (result == null) {
            throw new IllegalArgumentException("Race result cannot be null.");
        }
        raceResults.add(result);
    }

    public List<Driver> getDrivers() {
        return Collections.unmodifiableList(drivers);
    }

    public List<RaceResult> getRaceResults() {
        return Collections.unmodifiableList(raceResults);
    }

    public static List<Driver> getChampionshipStandings() {
        List<Driver> standings = new ArrayList<>(getInstance().drivers);
        standings.sort(Comparator.comparingInt(Driver::getTotalPoints)
                .reversed()
                .thenComparing(Driver::getName));
        return standings;
    }

    public static Driver getLeadingDriver() {
        List<Driver> standings = getChampionshipStandings();
        if (standings.isEmpty()) {
            return null;
        }
        return standings.get(0);
    }

    public static int getTotalChampionshipPoints() {
        int total = 0;
        for (Driver driver : getInstance().drivers) {
            total += driver.getTotalPoints();
        }
        return total;
    }
}
