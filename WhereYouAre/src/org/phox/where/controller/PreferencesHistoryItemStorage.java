package org.phox.where.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.phox.where.model.HistoryItem;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

public class PreferencesHistoryItemStorage extends Storage<HistoryItem>
{

	private static final String HISTORY_SAVE_PREFS = "SavedHistoryPrefs";

	public PreferencesHistoryItemStorage(Context c)
	{
		super(c);
	}
	
	@Override
	public void put(HistoryItem item) {
		SharedPreferences savedItems = this.context.getSharedPreferences(HISTORY_SAVE_PREFS, 0);
		SharedPreferences.Editor editor = savedItems.edit();
		editor.putString("" + item.date, String.format("%s %s %d", 
				Location.convert(item.latitude, Location.FORMAT_SECONDS),
				Location.convert(item.longitude, Location.FORMAT_SECONDS),
				item.elapsed_time));

		editor.commit();
	}

	@Override
	public List<HistoryItem> getAll() {
		LinkedList<HistoryItem> list = new LinkedList<HistoryItem>();

		SharedPreferences savedItems = this.context.getSharedPreferences(HISTORY_SAVE_PREFS, 0);
		for (Map.Entry<String, ?> entry : savedItems.getAll().entrySet())
		{	
			HistoryItem i = new HistoryItem();
			i.date = Long.parseLong(entry.getKey());

			String locString = (String)entry.getValue();
			if (locString != null)
			{
				System.out.println(locString);
				String[] latLongAndTime = locString.split(" ", 3);
				i.latitude = Location.convert(latLongAndTime[0]);
				i.longitude = Location.convert(latLongAndTime[1]);
				i.elapsed_time = Long.parseLong(latLongAndTime[2]);
			}
			else
			{
				Log.e("SavedItemStorage", "Item " + i.date + " had null value in preferences!");
			}

			list.add(i);
		}

		return list;
	}

	@Override
	public void remove(HistoryItem item) {
		SharedPreferences savedItems = this.context.getSharedPreferences(HISTORY_SAVE_PREFS, 0);
		SharedPreferences.Editor editor = savedItems.edit();
		editor.remove("" + item.date);
		editor.commit();
	}

	@Override
	public void clear() {
		SharedPreferences savedItems = this.context.getSharedPreferences(HISTORY_SAVE_PREFS, 0);
		SharedPreferences.Editor editor = savedItems.edit();
		editor.clear();
		editor.commit();
	}

}