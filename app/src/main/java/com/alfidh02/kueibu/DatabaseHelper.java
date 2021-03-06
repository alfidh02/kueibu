package com.alfidh02.kueibu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "KueIbu.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "data_kue";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DEADLINE = "kue_deadline";
    private static final String COLUMN_TYPE = "kue_macam";
    private static final String COLUMN_QUANTITY = "kue_banyak";
    private static final String COLUMN_NOTE = "kue_catatan";


    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_DEADLINE + " TEXT, " +
                        COLUMN_TYPE + " INTEGER, " +
                        COLUMN_QUANTITY + " INTEGER, " +
                        COLUMN_NOTE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addCake(String tanggal, int macam, int banyak, String catatan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_DEADLINE,tanggal);
        contentValues.put(COLUMN_TYPE,macam);
        contentValues.put(COLUMN_QUANTITY,banyak);
        contentValues.put(COLUMN_NOTE,catatan);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1) {
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil", Toast.LENGTH_SHORT).show();
        }
    }

    // cursor disini untuk menampung semua query
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

//    update data
    void updateData(String row_id, String tanggal, String macam, String banyak, String catatan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DEADLINE,tanggal);
        cv.put(COLUMN_TYPE,macam);
        cv.put(COLUMN_QUANTITY,banyak);
        cv.put(COLUMN_NOTE,catatan);

        long result = db.update(TABLE_NAME, cv,COLUMN_ID + " = ? ", new String[]{row_id}); // where id = ...
        Log.d("DatabaseHelp", String.valueOf(result));

        if (result == -1){
            Toast.makeText(context, "Gagal update data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil update", Toast.LENGTH_SHORT).show();
        }
    }

//    delete data
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Gagal menghapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil menghapus", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
