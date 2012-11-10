package com.terroir.caisse.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper{

	Context			context;
	
	public DatabaseHelper(Context context) {
		super(context, "producers", null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table producers (_id integer primary key autoincrement, "
				+ "raison_social text not null, sous_type text not null, " 
				+ "address text not null, code_postal text not null,"
				+ "ville text not null, mail text not null,"
				+ "telephone text not null, latitude text not null,"
				+ "longitude text not null"
				+ ");");			
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Toast.makeText(context, "Mise à jour de la Base de données version "+oldVersion+" vers "+newVersion, Toast.LENGTH_SHORT).show();
		db.execSQL("DROP TABLE IF EXISTS producers");
		onCreate(db);
	}
	
}
