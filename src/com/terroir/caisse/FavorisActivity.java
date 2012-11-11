package com.terroir.caisse;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.terroir.*;
import com.terroir.caisse.adapter.HomeAdapter;
import com.terroir.caisse.data.Producer;

public class FavorisActivity extends Activity {
	
	HomeAdapter adapter;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        setContentView(R.layout.activity_favoris);
        
        ListView favorisList = (ListView) findViewById(R.id.favorisList);
        
        List<Producer> producers = new ArrayList<Producer>();
        
        Producer pro1 = new Producer();
       // pro1.mail;
       // pro1.telephone;
        //pro1.distance;
        pro1.raison_social = "Bugey Côté Ferme";
        pro1.mail = "cote.ferme@gmail.com";
        pro1.telephone = "0199828867";
        pro1.distance = 19458.82;
        

        producers.add(pro1);
        pro1 = new Producer();
        pro1.raison_social = "Les Fermiers de la Dombes";
        pro1.mail = "fermiers@gmail.com";
        pro1.telephone = "0199828867";
        pro1.distance = 928.22;
        producers.add(pro1);
        pro1 = new Producer();
        pro1.raison_social = "La Marande";
        pro1.mail = "marande@gmail.com";
        pro1.telephone = "0199828867";
        pro1.distance =528.98;
        producers.add(pro1);
        pro1 = new Producer();
        pro1.raison_social = "Bio Evasion";
        pro1.mail = "evasion@gmail.com";
        pro1.telephone = "0199828867";
        pro1.distance = 128.51;
        producers.add(pro1);
        pro1 = new Producer();
        pro1.raison_social = "Une boutique de produits bio";
        pro1.mail = "boutique@gmail.com";
        pro1.telephone = "0199828867";
        pro1.distance = 98528.87;
        producers.add(pro1);
        pro1 = new Producer();
        pro1.raison_social = "Le Fournil des Ballets";
        pro1.mail = "fournil@gmail.com";
        pro1.telephone = "0199828867";
        pro1.distance = 1512.20;
        producers.add(pro1);
        pro1 = new Producer();
        pro1.raison_social = "La Panetière menulphienne";
        pro1.mail = "panetière@gmail.com";
        pro1.telephone = "0199828867";
        pro1.distance = 4533.98;
        producers.add(pro1);
        pro1 = new Producer();
        pro1.raison_social = "Le Chemin du pain";
        pro1.mail = "chemin@gmail.com";
        pro1.telephone = "0199828867";
        pro1.distance =9822.87;
        
        producers.add(pro1);
        pro1 = new Producer();
        adapter = new HomeAdapter(this, producers);
        favorisList.setAdapter(adapter);
        
        favorisList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView l, View v, int position, long id) {
				Producer producer = (Producer) adapter.getItem(position);    			   
				Intent wake = new Intent(FavorisActivity.this, CardActivity.class);				
			    wake.putExtra("raison_social", producer.raison_social);
			    wake.putExtra("address", producer.address);
			    wake.putExtra("code_postal", producer.code_postal);
			    wake.putExtra("distance", String.valueOf(producer.distance));
			    wake.putExtra("latitude", producer.latitude);
			    wake.putExtra("longitude", producer.longitude);
			    wake.putExtra("mail", producer.mail);
			    wake.putExtra("sous_type", producer.sous_type);
			    wake.putExtra("telephone", producer.telephone);
			    wake.putExtra("ville", producer.ville);
			    startActivity(wake);			    			   
			}
		});
        
    }
}