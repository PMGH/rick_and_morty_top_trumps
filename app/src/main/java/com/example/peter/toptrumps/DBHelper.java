package com.example.peter.toptrumps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.content;
import static android.R.attr.id;
import static android.R.attr.priority;

/**
 * Created by Peter on 12/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "top_trumps.db";
    public static final String PLAYERS_TABLE_NAME = "players";
    public static final String PLAYERS_COLUMN_ID = "id";
    public static final String PLAYERS_COLUMN_NAME = "name";
    public static final String PLAYERS_COLUMN_NUMWINS = "numWins";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + PLAYERS_TABLE_NAME + "(id INTEGER primary key autoincrement, name STRING, numWins INTEGER )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + PLAYERS_TABLE_NAME);
        onCreate(db);
    }

    public int save(String name, Integer numWins){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PLAYERS_COLUMN_NAME, name);
        cv.put(PLAYERS_COLUMN_NUMWINS, numWins);
        return (int) db.insert(PLAYERS_TABLE_NAME, null, cv);
    }

    public Playable getPlayerById(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Playable> players = new ArrayList<>();
        if (db == null) {
            return null;
        }
        Cursor rs = db.rawQuery("SELECT * FROM " + PLAYERS_TABLE_NAME + " WHERE id = ?", new String[] { String.valueOf(ID) });
        if (rs.moveToNext()) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            int numWins = rs.getInt(2);
            Player player = new Player(id, name, numWins);
            players.add(player);
        }
        rs.close();
        db.close();
        return players.get(0);
    }

    public ArrayList<Playable> all(){
        ArrayList<Playable> players = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM " + PLAYERS_TABLE_NAME, null);
        rs.moveToFirst();
        while(rs.moveToNext()){
            int id = rs.getInt(rs.getColumnIndex(PLAYERS_COLUMN_ID));
            String name = rs.getString(rs.getColumnIndex(PLAYERS_COLUMN_NAME));
            int numWins = rs.getInt(rs.getColumnIndex(PLAYERS_COLUMN_NUMWINS));
            Playable player = new Player(id, name, numWins);
            players.add(player);
        }
        rs.close();
        return players;
    }

    public boolean deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + PLAYERS_TABLE_NAME);
        return true;
    }

    public boolean delete(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = " id = ? ";
        String[] values = { id.toString() };
        db.delete(PLAYERS_TABLE_NAME, selection, values);
        return true;
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

}
