package jejunu.hackathon.walkingdead.model;

/**
 * Created by replay on 2016. 7. 12..
 */
public class Zombie {

    private int level;
    private int healthPoint;
    private int offensivePower;
    private int defensivePower;
    private double speed;
    private double latitude;
    private double longitude;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public int getOffensivePower() {
        return offensivePower;
    }

    public void setOffensivePower(int offensivePower) {
        this.offensivePower = offensivePower;
    }

    public int getDefensivePower() {
        return defensivePower;
    }

    public void setDefensivePower(int defensivePower) {
        this.defensivePower = defensivePower;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
