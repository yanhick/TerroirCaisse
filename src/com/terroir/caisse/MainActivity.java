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
		TabSpec tabSpecHome = tabHost
			.newTabSpec("Home")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_home_config))
			.setContent(intentHome);

		// Category tab
		Intent intentCategory = new Intent().setClass(this, CategoryActivity.class);
		TabSpec tabSpecCategory = tabHost
			.newTabSpec("Category")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_category_config))
			.setContent(intentCategory);
		
		// Favorite tab
		Intent intentFavoris = new Intent().setClass(this, FavorisActivity.class);
		TabSpec tabSpecFavoris = tabHost
			.newTabSpec("Favoris")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_favoris_config))
			.setContent(intentFavoris);
				
		// add all tabs 
		tabHost.addTab(tabSpecHome);		
		tabHost.addTab(tabSpecCategory);
		tabHost.addTab(tabSpecFavoris);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

}