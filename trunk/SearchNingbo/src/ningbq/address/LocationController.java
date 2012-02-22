package ningbq.address;

import java.util.Collection;
import java.util.LinkedList;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;


/**
 * Description:定位控制类.
 *
 * @author Devon.Ning
 * @2012-2-9
 *
 */
public class LocationController implements LocationListener {

	private static LocationController instance = null;

	private LocationManager locationManager;
	private Location lastLocation = null;
	private boolean isListening = false;

	//private Location nextLocationCandidate = null;
	//private Location lastRecordedLocation = null;

	private Collection<BestLocationListener> listeners;

	private LocationController(Context c) {
		locationManager = (LocationManager) c
				.getSystemService(Context.LOCATION_SERVICE);
		listeners = new LinkedList<BestLocationListener>();
	}

	public void startListening(long minTime, float minDistance) {
		if (!isListening) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, minTime, minDistance, this);
			isListening = true;
			Log.d("LocationController", "Started listening");
		}
	}

	public void stopListening() {
		if (isListening) {
			locationManager.removeUpdates(this);
			isListening = false;
		}
	}

	public Location getLastLocation() {
		return lastLocation;
	}

	public boolean hasGPSLock() {
		return (lastLocation != null && isSameProvider(
				lastLocation.getProvider(), LocationManager.GPS_PROVIDER));
		//return (lastLocation != null) ;
	}

	public synchronized void addBestLocationListener(
			BestLocationListener listener) {
		this.listeners.add(listener);
	}

	public synchronized void removeBestLocationListener(
			BestLocationListener listener) {
		this.listeners.remove(listener);
	}

	@Override
	public void onLocationChanged(Location newLoc) {
		Log.d("LocationController", "Got new location");

		if (isBetterLocation(newLoc, lastLocation)) {
			Log.d("LocationController",
					"Got new best location, notifying listeners");

			// Notify listeners
			Location lastLoc = lastLocation;
			lastLocation = newLoc;

			synchronized (this) {
				for (BestLocationListener l : this.listeners)
					l.bestLocationChanged(lastLoc, newLoc);
			}
		}
	}

	private static final int TWO_MINUTES = 1000 * 60 * 2;

	/**
	 * Determines whether one Location reading is better than the current
	 * Location fix
	 * 
	 * @param location
	 *            The new Location that you want to evaluate
	 * @param currentBestLocation
	 *            The current Location fix, to which you want to compare the new
	 *            one
	 */
	protected boolean isBetterLocation(Location location,
			Location currentBestLocation) {
		if (currentBestLocation == null) {
			// A new location is always better than no location
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use
		// the new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be
			// worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation
				.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(),
				currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and
		// accuracy
		if (isMoreAccurate) {
			return true;
		} else if (isNewer && !isLessAccurate) {
			return true;
		} else if (isNewer && !isSignificantlyLessAccurate
				&& isFromSameProvider) {
			return true;
		}
		return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}

	public static LocationController getInstance(Context c) {
		if (instance == null)
			instance = new LocationController(c);

		return instance;
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	public interface BestLocationListener {
		public void bestLocationChanged(Location oldLocation,
				Location newLocation);
	}

}