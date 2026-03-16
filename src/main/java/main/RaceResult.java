package main;
import java.util.Map;

/**
 * Abstraction for race result handling.
 * Demonstrates interface segregation and dependency inversion.
 */
public interface RaceResult {
    void recordResult(Driver driver, int position, int points);
    Map<Driver, Integer> getResults();
    Map<Integer, Driver> getPositions();
    String getRaceName();
    String getLocation();
    String getSurface();
}
