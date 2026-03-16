package main;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ChampionshipManager manager = ChampionshipManager.getInstance();

        GravelCar ogierGravelCar = new GravelCar("Toyota", "GR Yaris Rally1", 380, 9.8);
        GravelCar tanakGravelCar = new GravelCar("Hyundai", "i20 N Rally1", 375, 10.5);
        GravelCar kalleGravelCar = new GravelCar("Toyota", "GR Yaris Rally1", 378, 9.5);
        GravelCar neuvilleGravelCar = new GravelCar("Hyundai", "i20 N Rally1", 374, 10.2);

        Driver ogier = new Driver("Sébastien Ogier", "France", ogierGravelCar);
        Driver kalle = new Driver("Kalle Rovanperä", "Finland", kalleGravelCar);
        Driver tanak = new Driver("Ott Tänak", "Estonia", tanakGravelCar);
        Driver neuville = new Driver("Thierry Neuville", "Belgium", neuvilleGravelCar);

        manager.registerDriver(ogier);
        manager.registerDriver(kalle);
        manager.registerDriver(tanak);
        manager.registerDriver(neuville);

        RallyRaceResult rallyFinland = new RallyRaceResult("Rally Finland", "Jyväskylä", "Gravel");
        rallyFinland.recordResult(ogier, 1, 25);
        rallyFinland.recordResult(tanak, 2, 18);
        rallyFinland.recordResult(kalle, 3, 15);
        rallyFinland.recordResult(neuville, 4, 12);
        manager.addRaceResult(rallyFinland);

        ogier.setCar(new AsphaltCar("Toyota", "GR Yaris Rally1", 400, 16.0));
        kalle.setCar(new AsphaltCar("Toyota", "GR Yaris Rally1", 402, 15.5));
        tanak.setCar(new AsphaltCar("Hyundai", "i20 N Rally1", 398, 15.0));
        neuville.setCar(new AsphaltCar("Hyundai", "i20 N Rally1", 399, 15.8));

        RallyRaceResult monteCarlo = new RallyRaceResult("Monte Carlo Rally", "Monaco", "Asphalt");
        monteCarlo.recordResult(kalle, 1, 25);
        monteCarlo.recordResult(neuville, 2, 18);
        monteCarlo.recordResult(ogier, 3, 15);
        monteCarlo.recordResult(tanak, 4, 12);
        manager.addRaceResult(monteCarlo);

        printStandings();
        printLeader();
        printStatistics(manager.getDrivers());
        printRaceResults(manager.getRaceResults());
        printPerformanceExamples();
    }

    private static void printStandings() {
        List<Driver> standings = ChampionshipManager.getChampionshipStandings();
        for (int i = 0; i < standings.size(); i++) {
            Driver driver = standings.get(i);
            System.out.println((i + 1) + ". " + driver.getName() + " (" + driver.getCountry() + "): "
                    + driver.getTotalPoints() + " points");
        }
    }

    private static void printLeader() {
        Driver leader = ChampionshipManager.getLeadingDriver();
        System.out.println("===== CHAMPIONSHIP LEADER =====");
        if (leader != null) {
            System.out.println(leader.getName() + " with " + leader.getTotalPoints() + " points");
        }
    }

    private static void printStatistics(List<Driver> drivers) {
        System.out.println("===== CHAMPIONSHIP STATISTICS =====");
        System.out.println("Total Drivers: " + Driver.getTotalDrivers());
        System.out.println("Total Races: " + ChampionshipStatistics.countTotalRacesHeld());
        System.out.printf("Average Points Per Driver: %.2f%n",
                ChampionshipStatistics.calculateAveragePointsPerDriver(drivers));
        System.out.println("Most Successful Country: "
                + ChampionshipStatistics.findMostSuccessfulCountry(drivers));
        System.out.println("Total Championship Points: " + ChampionshipManager.getTotalChampionshipPoints());
    }

    private static void printRaceResults(List<RaceResult> raceResults) {
        System.out.println("===== RACE RESULTS =====");
        for (RaceResult raceResult : raceResults) {
            System.out.println("Race: " + raceResult.getRaceName() + " (" + raceResult.getLocation() + ") - "
                    + raceResult.getSurface());
            for (Integer position : raceResult.getPositions().keySet()) {
                Driver driver = raceResult.getPositions().get(position);
                int points = raceResult.getResults().get(driver);
                System.out.println(" Position " + position + ": " + driver.getName() + " - " + points + " points");
            }
        }
    }

    private static void printPerformanceExamples() {
        GravelCar gravelCar = new GravelCar("Toyota", "GR Yaris Rally1", 380, 9.8);
        AsphaltCar asphaltCar = new AsphaltCar("Toyota", "GR Yaris Rally1", 400, 16.0);

        System.out.println("===== CAR PERFORMANCE RATINGS =====");
        System.out.printf("Gravel Car Performance: %.1f%n", gravelCar.calculatePerformance());
        System.out.printf("Asphalt Car Performance: %.1f%n", asphaltCar.calculatePerformance());
    }
}
