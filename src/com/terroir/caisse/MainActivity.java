package com.terroir.caisse;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		// Android tab
		Intent intentHome = new Intent().setClass(this, HomeActivity.class);
		TabSpec tabSpecAndroid = tabHost
			.newTabSpec("Home")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_home_config))
			.setContent(intentHome);

		// Apple tab
		Intent intentFavoris = new Intent().setClass(this, FavorisActivity.class);
		TabSpec tabSpecApple = tabHost
			.newTabSpec("Favoris")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_favoris_config))
			.setContent(intentFavoris);
		
		// Windows tab
		Intent intentWindows = new Intent().setClass(this, WindowsActivity.class);
		TabSpec tabSpecWindows = tabHost
			.newTabSpec("Windows")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_windows_config))
			.setContent(intentWindows);
		
		// Blackberry tab
		Intent intentBerry = new Intent().setClass(this, BlackBerryActivity.class);
		TabSpec tabSpecBerry = tabHost
			.newTabSpec("Berry")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_blackberry_config))
			.setContent(intentBerry);
	
		// add all tabs 
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		tabHost.addTab(tabSpecWindows);
		tabHost.addTab(tabSpecBerry);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

}