package org.phox.where.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.phox.where.model.SavedItem;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

public class PreferencesSavedItemStorage extends Storage<SavedItem>
{

	private static final String ITEM_SAVE_PREFS = "SavedItemsPrefs";
	
	public PreferencesSavedItemStorage(Context c)
	{
		super(c);
	}
	
	@Override
	public void put(SavedItem item) {
		SharedPreferences savedItems = this.context.getSharedPreferences(ITEM_SAVE_PREFS, 0);
    	SharedPreferences.Editor editor = savedItems.edit();
        editor.putString(item.name, String.format("%s %s %d", 
        		Location.convert(item.latitude, Location.FORMAT_SECONDS),
        		Location.convert(item.longitude, Location.FORMAT_SECONDS),
        		item.date));
        
        editor.commit();
	}

	@Override
	public List<SavedItem> getAll() {
		LinkedList<SavedItem> list = new LinkedList<SavedItem>();
		
		SharedPreferences savedItems = this.context.getSharedPreferences(ITEM_SAVE_PREFS, 0);
		for (Map.Entry<String, ?> entry : savedItems.getAll().entrySet())
		{	
			SavedItem i = new SavedItem();
			i.name = entry.getKey();
			
			String locString = (String)entry.getValue();
			if (locString != null)
			{
				System.out.println(locString);
				String[] latLongAndTime = locString.split(" ", 3);
				i.latitude = Location.convert(latLongAndTime[0]);
				i.longitude = Location.convert(latLongAndTime[1]);
				i.date = Long.parseLong(latLongAndTime[2]);
			}
			else
			{
				Log.e("SavedItemStorage", "Item " + i.name + " had null value in preferences!");
			}
			
			list.add(i);
		}
		
		return list;
	}

	@Override
	public void remove(SavedItem item) {
		SharedPreferences savedItems = this.context.getSharedPreferences(ITEM_SAVE_PREFS, 0);
    	SharedPreferences.Editor editor = savedItems.edit();
    	editor.remove(item.name);
    	editor.commit();
	}

	@Override
	public void clear() {
		SharedPreferences savedItems = this.context.getSharedPreferences(ITEM_SAVE_PREFS, 0);
    	SharedPreferences.Editor editor = savedItems.edit();
    	editor.clear();
    	editor.commit();
	}
	
}