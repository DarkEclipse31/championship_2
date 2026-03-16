package main;
 
public class Driver {
    private String name;
    private String country;
    private int totalPoints;
    private RallyCar car;
    private static int totalDrivers = 0;

    public Driver(String name, String country, RallyCar car) {
        this.name = name;
        this.country = country;
        this.car = car;
        this.totalPoints = 0;
        totalDrivers++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public RallyCar getCar() {
        return car;
    }

    public void setCar(RallyCar car) {
        this.car = car;
    }

    public void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        totalPoints += points;
    }

    public static int getTotalDrivers() {
        return totalDrivers;
    }

    @Override
    public String toString() {
        return name + " (" + country + "): " + totalPoints + " points";
    }
}
