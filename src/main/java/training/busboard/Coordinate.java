package training.busboard;

public class Coordinate{
    private String longitude;
    private String latitude;

    public String getlongitude() {
        return longitude;
    }

    public String getlatitude() {
        return latitude;
    }

    public Coordinate(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}