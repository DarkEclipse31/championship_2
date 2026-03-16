package main;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Concrete implementation of a race result.
 */
public class RallyRaceResult implements RaceResult {
    private String raceName;
    private String location;
    private String surface;
    private Map<Driver, Integer> results;
    private Map<Integer, Driver> positions;
    private static int totalRacesHeld = 0;

    public RallyRaceResult(String raceName, String location, String surface) {
        this.raceName = raceName;
        this.location = location;
        this.surface = surface;
        this.results = new LinkedHashMap<>();
        this.positions = new TreeMap<>();
        totalRacesHeld++;
    }

    @Override
    public void recordResult(Driver driver, int position, int points) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }
        if (position <= 0) {
            throw new IllegalArgumentException("Position must be positive.");
        }
        if (positions.containsKey(position)) {
            throw new IllegalArgumentException("Position " + position + " is already used.");
        }
        if (results.containsKey(driver)) {
            throw new IllegalArgumentException("Driver already has a result in this race.");
        }

        positions.put(position, driver);
        results.put(driver, points);
        driver.addPoints(points);
    }

    @Override
    public Map<Driver, Integer> getResults() {
        return Collections.unmodifiableMap(results);
    }

    @Override
    public Map<Integer, Driver> getPositions() {
        return Collections.unmodifiableMap(positions);
    }

    @Override
    public String getRaceName() {
        return raceName;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getSurface() {
        return surface;
    }

    public static int getTotalRacesHeld() {
        return totalRacesHeld;
    }
}
