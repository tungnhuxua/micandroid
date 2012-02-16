package org.phox.where.model;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcel;
import android.os.Parcelable;

public class HistoryItem implements Parcelable {

	public double latitude = -1;
	public double longitude = -1;
	public long date = 0;
	public long elapsed_time = 0;
	
	public HistoryItem()
	{
	}
	
	public HistoryItem(Parcel in)
	{
		latitude = in.readDouble();
		longitude = in.readDouble();
		date = in.readLong();
		elapsed_time = in.readLong();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(latitude);
		dest.writeDouble(longitude);
		dest.writeLong(date);
		dest.writeLong(elapsed_time);
	}
	
    public static final Parcelable.Creator<HistoryItem> CREATOR = new Parcelable.Creator<HistoryItem>() {
        public HistoryItem createFromParcel(Parcel in) {
            return new HistoryItem(in);
        }

        public HistoryItem[] newArray(int size) {
            return new HistoryItem[size];
        }
    };
    
    public String getAddressString(Context c)
	{
		Geocoder geocoder = new Geocoder(c);
		List<Address> addresses = null;
		
		try {
			addresses = geocoder.getFromLocation(this.latitude, this.longitude, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String addressString = "";
		if (addresses == null || addresses.size() < 1)
			addressString = String.format("%f, %f", this.latitude, this.longitude);
		else
		{
			Address a = addresses.get(0);
			
			if (a.getMaxAddressLineIndex() < 1)
			{
				addressString = a.getLocality() + "\n" + a.getAdminArea();
			}
			else
			{	
				for (int i = 0; i < a.getMaxAddressLineIndex(); i++)
				{
					addressString += a.getAddressLine(i);
					
					if (i == 0 && a.getMaxAddressLineIndex() > 1)
						addressString += "\n";
					else
						addressString += ", ";
				}
				addressString = addressString.substring(0, addressString.length() - 2);
			}
		}
		
		return addressString;
	}
	
	
}
