package main;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Static utility class for championship statistics.
 */
public final class ChampionshipStatistics {
    private ChampionshipStatistics() {
    }

    public static double calculateAveragePointsPerDriver(List<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) {
            return 0.0;
        }

        int totalPoints = 0;
        for (Driver driver : drivers) {
            totalPoints += driver.getTotalPoints();
        }
        return (double) totalPoints / drivers.size();
    }

    public static String findMostSuccessfulCountry(List<Driver> drivers) {
        if (drivers == null || drivers.isEmpty()) {
            return "No drivers registered";
        }

        Map<String, Integer> countryPoints = new HashMap<>();
        for (Driver driver : drivers) {
            String country = driver.getCountry();
            int updatedPoints = countryPoints.getOrDefault(country, 0) + driver.getTotalPoints();
            countryPoints.put(country, updatedPoints);
        }

        String bestCountry = null;
        int maxPoints = -1;

        for (Map.Entry<String, Integer> entry : countryPoints.entrySet()) {
            String country = entry.getKey();
            int points = entry.getValue();

            if (points > maxPoints ||
                (points == maxPoints && (bestCountry == null || country.compareTo(bestCountry) < 0))) {
                maxPoints = points;
                bestCountry = country;
            }
        }

        return bestCountry;
    }

    public static int countTotalRacesHeld() {
        return RallyRaceResult.getTotalRacesHeld();
    }
}
