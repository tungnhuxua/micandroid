package org.phox.where.view;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.phox.where.R;
import org.phox.where.controller.HistoryController;
import org.phox.where.controller.HistoryController.HistoryListener;
import org.phox.where.controller.Storage;
import org.phox.where.model.HistoryItem;
import org.phox.where.util.DateTimeUtil;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryListView extends ListActivity
							 implements HistoryListener {
	
	private HistoryAdapter historyItems = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		HistoryController historyController = HistoryController.getInstance(getApplicationContext());
		Storage<HistoryItem> historyStorage = historyController.getStorage();
		
		historyItems = new HistoryAdapter(this, R.layout.list_item);
		setListAdapter(historyItems);

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		
		final List<HistoryItem> items = historyStorage.getAll();
		Collections.sort(items, new Comparator<HistoryItem>() {
			@Override
			public int compare(HistoryItem object1, HistoryItem object2) {
				return (int) (object2.date - object1.date);
			}
		});
		
		for (HistoryItem hi: items)
			addNewHistoryItem(hi);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View child, int pos, long id) {
				Intent mapIntent = new Intent(getBaseContext(), HistoryMapView.class);
				mapIntent.putExtra("history_item", items.get(pos));
	    		startActivityForResult(mapIntent, 0);
			}
		});
	}

	public void addNewHistoryItem(HistoryItem item)
	{
		historyItems.add(item);
		historyItems.notifyDataSetChanged();
	}
	
	@Override
	public void onNewHistoryItem(HistoryItem item) {
		addNewHistoryItem(item);
	}

	public class HistoryAdapter extends ArrayAdapter<HistoryItem>
	{
		public HistoryAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null)
				view = getLayoutInflater().inflate(R.layout.list_item, null);
			
			HistoryItem item = getItem(position);
			String dateString = DateTimeUtil.getFuzzyTimeDelta(item.date, System.currentTimeMillis(), 1000*60*60*24*3);
			String timeString = new SimpleDateFormat("h:mm a").format(new Date(item.date)).toLowerCase();
			String addressString = item.getAddressString(HistoryListView.this);
			String elapsedString = "For " + DateTimeUtil.getFuzzyTime(item.elapsed_time);
			
			setTextForResource(view, R.id.dateTextView, dateString);
			setTextForResource(view, R.id.timeTextView, timeString);
			setTextForResource(view, R.id.addressTextView, addressString);
			setTextForResource(view, R.id.elapsedTextView, elapsedString);
			
			return view;
		}
		
		private void setTextForResource(View parent, int resId, String text)
		{
			TextView tv = (TextView)(parent.findViewById(resId));
			tv.setText(text);
		}
		
	}
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflator = getMenuInflater();
    	inflator.inflate(R.menu.history_menu, menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId())
    	{
    	case R.id.clearHistoryMenuItem:
    		HistoryController historyController = HistoryController.getInstance(getApplicationContext());
    		Storage<HistoryItem> historyStorage = historyController.getStorage();
    		historyStorage.clear();
    		
    		historyItems.clear();
    		historyItems.notifyDataSetChanged();
    		return true;
    	case R.id.toggleSaveHistoryMenuItem:
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
}
