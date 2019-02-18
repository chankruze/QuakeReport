package in.geekofia.quakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a fake list of earthquake locations.
        final ArrayList<Quake> quakes = new ArrayList<Quake>();
        quakes.add(new Quake(7.2,"San Francisco", "Feb 2,2016"));
        quakes.add(new Quake(6.1,"London", "July 20,2015"));
        quakes.add(new Quake(3.9,"Tokyo", "Nov 10,2014"));
        quakes.add(new Quake(5.4,"Mexico City", "May 3,2014"));
        quakes.add(new Quake(2.8,"Moscow", "Jan 31,2013"));
        quakes.add(new Quake(4.9,"Rio de Janeiro", "August 19,2012"));
        quakes.add(new Quake(1.6,"Paris", "Oct 30,2011"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of quakes
        QuakeAdapter adapter = new QuakeAdapter(this, quakes);

        earthquakeListView.setAdapter(adapter);
    }
}
