package com.terroir.caisse.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAdapter {

	DatabaseHelper	DBHelper;
	Context			context;
	SQLiteDatabase	db;
	
	public DBAdapter(Context context){
		this.context = context;
		DBHelper = new DatabaseHelper(context);
	}	
	
	public DBAdapter open(){
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		db.close();
	}
	
	public void drop() {
		db.execSQL("DROP TABLE IF EXISTS producers");
	}
	
	public void Truncate(){
		db.execSQL("DELETE FROM producers");
	}
	
	public long insert(Producer producer){
		ContentValues values = new ContentValues();
		values.put("raison_social", producer.raison_social);
		values.put("sous_type", producer.sous_type);
		values.put("address", producer.address);
		values.put("code_postal", producer.code_postal);
		values.put("ville", producer.ville);
		values.put("mail", producer.mail);
		values.put("telephone", producer.telephone);
		values.put("latitude", String.valueOf(producer.latitude));
		values.put("longitude", String.valueOf(producer.longitude));
		
		return db.insert("producers", null, values);		
	}
	
	
	public boolean supprimerProduit(long id){
		return db.delete("producers", "_id="+id, null)>0;
	}
	
	public Cursor query(){
		return db.query("producers", new String[]{
				"_id",
				"raison_social",
				"sous_type",
				"address",
				"code_postal",
				"ville",
				"mail",
				"telephone",
				"latitude",
				"longitude"}, null, null, null, null, null);
	}

	public Cursor categories() {
		return db.query("producers", new String[]{"sous_type"}, null, null, "sous_type", null, null);
	}
	public Cursor query(String category) {
		return db.query("producers", new String[]{"raison_social"}, "sous_type = " + category, null, "raison_social", null, null);
	}
	public int count(String key, String value) {
		String SQL_STATEMENT = "SELECT COUNT(*) FROM producers WHERE "+key+"=?";
		Cursor cursor = db.rawQuery(SQL_STATEMENT, new String[] { value });
		cursor.moveToFirst();
		int count = cursor.getInt(0);
		cursor.close();      
		return count;
	}
}
