package com.example.studenttimemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter 
{
		static final String DATABASE_NAME = "studenttime.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		public static final String DATABASE_TABLE = "timetable";
		
		private static final String TOPIC = null;
		private static final String ID = null;
		private static final String REMARKS = null;
		private static final String TIME = null;
		private static final String DATE = null;
		public static final String[] ALL_KEYS = new String[] {ID, DATE, TIME, TOPIC,REMARKS};
		
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"timetable"+
		                             "( " +"ID"+" integer primary key autoincrement,"+ "DATE  text,TIME text, TOPIC text,REMARKS text); ";
		// Variable to hold the database instance
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;
		public  LoginDataBaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String a,String b,String c,String d)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("DATE", a);
			newValues.put("TIME",b);
			newValues.put("TOPIC",c);
			newValues.put("REMARKS",d);
			
			// Insert the row into your table
			db.insert("timetable", null, newValues);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		public int deleteEntry(String x)
		{
			//String id=String.valueOf(ID);
		    String where="TOPIC=?";
		    int numberOFEntriesDeleted= db.delete("timetable", where, new String[]{x}) ;
	       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	
		public String getSinlgeEntry(String topic)
		{
			Cursor cursor=db.query("timetable", null, " TOPIC=?", new String[]{topic}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
			cursor.close();
			return password;				
		}
		public void  updateEntry(int ikan,String a,String b,String c,String d)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("DATE", a);
			updatedValues.put("TIME",b);
			updatedValues.put("TOPIC",c);
			updatedValues.put("REMARKS",d);
			
			String adoi = String.valueOf(ikan);
			
	        String where="ID = ?";
		    db.update("timetable",updatedValues, where, new String[]{adoi});			   
		}
		
		
		// Return all data in the database.
		public String getdata()
		{
			Cursor c=db.query("timetable", ALL_KEYS,null, null, null, null, null);
			
			String result = "";
			int irow = c.getColumnIndex(ID);
			int date = c.getColumnIndex(DATE);
			int time = c.getColumnIndex(TIME);
			int topic = c.getColumnIndex(TOPIC);
			int remarks = c.getColumnIndex(REMARKS);
			
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(irow) + " " + c.getString(date) + " " + c.getString(time) + " " +c.getString(topic) + " " +c.getString(remarks) + "\n";
		}
			return result;
}
		
		
}

