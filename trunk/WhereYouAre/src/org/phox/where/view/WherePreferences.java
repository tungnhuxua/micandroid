package org.phox.where.view;

import org.phox.where.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class WherePreferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}