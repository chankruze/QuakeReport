package in.geekofia.quakereport;

public class Quake {
    private double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mURL;

    public Quake(double magnitude, String location, long date, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mURL = url;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public long getDate(){
        return mDate;
    }

    public String getURL(){
        return mURL;
    }
}
