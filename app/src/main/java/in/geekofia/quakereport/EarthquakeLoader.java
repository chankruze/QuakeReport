package in.geekofia.quakereport;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.ArrayList;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Quake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    public ArrayList<Quake> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl == null) {
            return null;
        }

        ArrayList<Quake> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}