package com.lss.example.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lss.example.data.DataBaseHelper;
import com.lss.example.server.Singer;

public class DataBaseDao {

	private DataBaseHelper helper = null;
	private SQLiteDatabase db = null;

	public DataBaseDao(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DataBaseHelper(context);
		db = helper.getWritableDatabase();
	}

	public void insert(Singer singer) {
		
		String sql = "insert into singer values(null, ?, ?)";

		  db.execSQL(sql, new Object[]{singer.getName(), singer.getSong()});  
	}

	public void delete(Singer singer) {
		
		String name = singer.getName();
		String song = singer.getSong();
		
		String whereClause = "song=?";
		String whereArgs[] = {song};
		
		db.delete("singer", whereClause, whereArgs);

	}

	public void update(Singer singer) {

		String name = singer.getName();
		String song = singer.getSong();
		
		ContentValues values = new ContentValues();
		
		values.put("song", name);
		
		String whereClause = "song=?";
		String []whereArgs = {song};
		
		db.update("singer", values, whereClause, whereArgs);
		
		
	}

	public List<Singer> query(String name, String song) {

		List<Singer> singers = new ArrayList<Singer>();
		
		String sql = "select * from singer";
		Cursor cursor = db.rawQuery(sql, null);
		
		while(cursor.moveToNext()){
			
			Singer singer = new Singer();
			singer.setName(cursor.getString(cursor.getColumnIndex("name")));
			singer.setSong(cursor.getString(cursor.getColumnIndex("song")));
			singers.add(singer);
		}
		
		cursor.close();
		return singers;
	}
	
	public void closeDataBase(){
		db.close();
	}

}
