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
				+ "raison_social text, sous_type text, " 
				+ "address text, code_postal text,"
				+ "ville text, mail text,"
				+ "telephone text, latitude text,"
				+ "longitude text"
				+ ");");			
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Toast.makeText(context, "Mise � jour de la Base de donn�es version "+oldVersion+" vers "+newVersion, Toast.LENGTH_SHORT).show();
		db.execSQL("DROP TABLE IF EXISTS producers");
		onCreate(db);
	}
	
}
