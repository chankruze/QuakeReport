package in.geekofia.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuakeAdapter extends ArrayAdapter<Quake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public QuakeAdapter(Activity context, ArrayList<Quake> quakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, quakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Quake currentQuake = getItem(position);

        // Magnitude
        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(currentQuake.getMagnitude());
        magnitude.setText(formattedMagnitude);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        // Location
        String CurrentLocation = currentQuake.getLocation();
        String LocationPrimary;
        String LocationOffset;

        // Handle location
        if (CurrentLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = CurrentLocation.split(LOCATION_SEPARATOR);
            LocationOffset = parts[0] + LOCATION_SEPARATOR;
            LocationPrimary = parts[1];
        } else {
            LocationOffset = getContext().getString(R.string.near_the);
            LocationPrimary = CurrentLocation;
        }

        TextView PrimaryLocation = (TextView) listItemView.findViewById(R.id.locationPrimary);
        PrimaryLocation.setText(LocationPrimary);

        TextView OffsetLocation = (TextView) listItemView.findViewById(R.id.locationOffset);
        OffsetLocation.setText(LocationOffset);


        // Handle the date & time
        Date dateObject = new Date(currentQuake.getDate());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(formatDate(dateObject));

        // Time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(formatTime(dateObject));

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double mag) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)mag;
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
