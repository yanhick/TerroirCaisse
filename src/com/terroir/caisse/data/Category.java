package com.terroir.caisse.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.util.Log;

import com.terroir.caisse.R;

public class Category {
		
	public static final Map<String, Integer> CATEGORIES = new HashMap<String, Integer>(); 
	static {
		CATEGORIES.put("MIEL", R.drawable.confit); 
		CATEGORIES.put("LAIT", R.drawable.lait); 
		CATEGORIES.put("OLIVE", R.drawable.olive); 
		CATEGORIES.put("BOULANGERIE", R.drawable.boulang); 
		CATEGORIES.put("FRUIT", R.drawable.fruit_et_legume); 
		CATEGORIES.put("VIANDE", R.drawable.viande); 
		CATEGORIES.put("AROME", R.drawable.parfum); 
		CATEGORIES.put("GOURMANDISE", R.drawable.bonbons); 
		CATEGORIES.put("VOLAILLE", R.drawable.poussin); 
		CATEGORIES.put("PLANTE", R.drawable.vegetal); 
		CATEGORIES.put("SEL", R.drawable.sel); 
		CATEGORIES.put("SPIRITUEUX", R.drawable.bonbons); 
		CATEGORIES.put("JUS", R.drawable.jus_de_fruit);	
	}
	
	public static Set<String> get() {
		return CATEGORIES.keySet();
	}
	public static Integer get(String key) {
		int drawable = -1;
		if(key != null)
		for(String category: Category.get()) {			
			if(key.toUpperCase().contains(category)) {
				Log.i("Category", key+" == "+category);
				drawable = CATEGORIES.get(category);
			}else {
				Log.i("Category", key+" =! "+category);
			}
		}
		return drawable;
	}
	

	public static final Map<String, Integer> PINS = new HashMap<String, Integer>();
	static {
		PINS.put("MIEL", R.drawable.bonbonpin);
		PINS.put("LAIT", R.drawable.laitpin); 
		PINS.put("OLIVE", R.drawable.huileolivepin); 
		PINS.put("BOULANGERIE", R.drawable.boulangerpin); 
		PINS.put("FRUIT", R.drawable.fruitetlegumepin); 
		PINS.put("VIANDE", R.drawable.viandepin); 
		PINS.put("AROME", R.drawable.parfumpin); 
		PINS.put("GOURMANDISE", R.drawable.bonbonpin); 
		PINS.put("VOLAILLE", R.drawable.poussinpin); 
		PINS.put("PLANTE", R.drawable.vegepin); 
		PINS.put("SEL", R.drawable.selpin); 
		PINS.put("SPIRITUEUX", R.drawable.bonbonpin); 
		PINS.put("JUS", R.drawable.jusdefruitpin);
	}
	
	public static Set<String> pin() {
		return PINS.keySet();
	}
	public static int pin(String key) {
		int drawable = -1;
		if(key != null)
			for(String category: Category.pin()) {
				if(key.toUpperCase().contains(category))
					drawable = PINS.get(category);
			}
		return drawable;
	}
}
