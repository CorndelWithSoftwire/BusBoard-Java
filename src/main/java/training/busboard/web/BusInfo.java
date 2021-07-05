package training.busboard.web;

import training.busboard.BusStop;

public class BusInfo {
    private final String postcode;
    private BusStop stop1;
    private BusStop stop2;

    public BusInfo(String postcode) {
        this.postcode = postcode;
    }

    public void setStop1(BusStop stop1) {
        this.stop1 = stop1;
    }

    public void setStop2(BusStop stop2) {
        this.stop2 = stop2;
    }

    public BusStop getStop1() {
        return stop1;
    }

    public BusStop getStop2() {
        return stop2;
    }

    public String getPostcode() {
        return postcode;
    }

}
