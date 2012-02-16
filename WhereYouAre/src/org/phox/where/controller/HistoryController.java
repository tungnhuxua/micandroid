package org.phox.where.controller;

import java.util.Collection;
import java.util.LinkedList;

import org.phox.where.controller.LocationController.BestLocationListener;
import org.phox.where.model.HistoryItem;

import android.content.Context;
import android.location.Location;
import android.util.Log;

public class HistoryController implements BestLocationListener {

	private static final int STATIONARY_RADIUS = 100;
	private static final int STATIONARY_TIME = 10 * 1000;
	
	private static HistoryController instance = null;
	
	private Context context = null;
	
	private Location stationaryLocationStart = null;
	private Location stationaryLocationEnd = null;
	private long stationaryLocationStartTime = -1;
	
	private Storage<HistoryItem> historyStorage = null;
	
	private Collection<HistoryListener> listeners = null;
	
	private HistoryController(Context context)
	{
		this.context = context;
		
		historyStorage = Storage.getStorage(PreferencesHistoryItemStorage.class, context);
		
		if (historyStorage == null)
			throw new RuntimeException("HistoryStorage wasn't created in the history controller!");
		
		this.listeners = new LinkedList<HistoryListener>();
		
		Log.d("HistoryController", "Created controller");
	}
	
	public void startRecording()
	{
		LocationController cont = LocationController.getInstance(this.context);
        cont.startListening(90000, STATIONARY_RADIUS);
        cont.addBestLocationListener(this);
	}
	
	public void stopRecording()
	{
		LocationController cont = LocationController.getInstance(this.context);
        cont.stopListening();
        cont.removeBestLocationListener(this);
	}
	
	public Storage<HistoryItem> getStorage()
	{
		return historyStorage;
	}
	
	public synchronized void addHistoryListener(HistoryListener listener)
	{
		listeners.add(listener);
	}
	
	public synchronized void removeHistoryListener(HistoryListener listener)
	{
		listeners.remove(listener);
	}
	
	public static HistoryController getInstance(Context context)
	{
		if (instance == null)
			instance = new HistoryController(context);
		
		return instance;
	}
	
	@Override
	public void bestLocationChanged(Location oldLocation, Location newLocation) {
		long currentTime = System.currentTimeMillis();
		
		LocationController cont = LocationController.getInstance(this.context);
		
		Log.d("HistoryController", "Best location updated! (hasGPSLock: " + cont.hasGPSLock() + ")");
		
		if (cont.hasGPSLock())
		{
			if (stationaryLocationStart == null)
			{
				stationaryLocationStart = newLocation;
				stationaryLocationEnd = newLocation;
				stationaryLocationStartTime = currentTime;
			}
			else
			{
				Log.d("HistoryController", "Distance from stationary point: " + stationaryLocationStart.distanceTo(newLocation));
				
				if (stationaryLocationStart.distanceTo(newLocation) < STATIONARY_RADIUS)
				{
					Log.d("HistoryController", "New location was within radius");
					
					stationaryLocationEnd = newLocation;
				}
				else
				{
					Log.d("HistoryController", "New location out of radius (time delta: " + (currentTime - stationaryLocationStartTime) + ")");
					
					if (currentTime - stationaryLocationStartTime > STATIONARY_TIME)
					{
						Log.d("HistoryController", "Stayed in previous location long enough, recording to history");
						
						HistoryItem item = new HistoryItem();
						item.date = stationaryLocationStartTime;
						item.elapsed_time = currentTime - stationaryLocationStartTime;
						item.latitude = stationaryLocationStart.getLatitude();
						item.longitude = stationaryLocationStart.getLongitude();
						
						historyStorage.put(item);
						
						//
						// Notify listeners
						//
						
						synchronized (this) {
							for (HistoryListener l : this.listeners)
								l.onNewHistoryItem(item);
						}
					}
					
					stationaryLocationStart = newLocation;
					stationaryLocationEnd = newLocation;
					stationaryLocationStartTime = currentTime;
				}
			}
		}
	}
	
	public interface HistoryListener
	{
		public void onNewHistoryItem(HistoryItem item);
	}
	
}
