package com.markzhai.library.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by marktlzhai on 2015/4/2.
 */
public class LocationUtils {
    public static Location getLocation(Context context) {
        LocationManager locMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        return location;
    }
}
