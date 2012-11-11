package com.terroir.caisse.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.terroir.caisse.R;

public class Category {


	public static final Map<String, Integer> CATEGORIES = new HashMap<String, Integer>(); 
	static {
		CATEGORIES.put("MIEL", R.drawable.confit); 
		CATEGORIES.put("LAITIERS", R.drawable.lait); 
		CATEGORIES.put("OLIVES", R.drawable.olive); 
		CATEGORIES.put("BOULANGERIE", R.drawable.boulang); 
		CATEGORIES.put("FRUITS", R.drawable.fruit_et_legume); 
		CATEGORIES.put("VIANDE", R.drawable.viande); 
		CATEGORIES.put("AROME", R.drawable.parfum); 
		CATEGORIES.put("GOURMANDISES", R.drawable.bonbons); 
		CATEGORIES.put("VOLAILLES", R.drawable.poussin); 
		CATEGORIES.put("PLANTES", R.drawable.vegetal); 
		CATEGORIES.put("SEL", R.drawable.sel); 
		CATEGORIES.put("SPIRITUEUX", R.drawable.bonbons); 
		CATEGORIES.put("JUS", R.drawable.jus_de_fruit);	
	}
	
	public static Set<String> get() {
		return CATEGORIES.keySet();
	}
	public static Integer get(String key) {
		return CATEGORIES.get(key);
	}
}
