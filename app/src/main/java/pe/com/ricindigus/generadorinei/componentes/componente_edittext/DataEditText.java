package pe.com.ricindigus.generadorinei.componentes.componente_edittext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pe.com.ricindigus.generadorinei.modelo.DataSourceComponentes.DBHelperComponente;

/**
 * Created by RICARDO on 1/01/2018.
 */

public class DataEditText {
    Context contexto;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public DataEditText(Context contexto) {
        this.contexto = contexto;
        sqLiteOpenHelper = new DBHelperComponente(contexto);
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void open(){
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        sqLiteOpenHelper.close();
    }

    public long getNumeroItemsPOJOEditText(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase, SQLEditText.tablaEditText);
    }

    public long getNumeroItemsSPEditText(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase, SQLEditText.tablaSPEditText);
    }

    public void insertarPOJOEditText(POJOEditText POJOEditText){
        ContentValues contentValues = POJOEditText.toValues();
        sqLiteDatabase.insert(SQLEditText.tablaEditText,null,contentValues);
    }
    public void insertarPOJOEditTexts(ArrayList<POJOEditText> editTexts){
        long items = getNumeroItemsPOJOEditText();
        if(items == 0){
            for (POJOEditText POJOEditText : editTexts) {
                try {
                    insertarPOJOEditText(POJOEditText);
                }catch (SQLiteException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public POJOEditText getPOJOEditText(String idPOJOEditText){
        POJOEditText POJOEditText = new POJOEditText();
        String[] whereArgs = new String[]{idPOJOEditText};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLEditText.tablaEditText,
                    SQLEditText.ALL_COLUMNS_EDITTEXT, SQLEditText.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                POJOEditText.setID(cursor.getString(cursor.getColumnIndex(SQLEditText.EDITTEXT_ID)));
                POJOEditText.setMODULO(cursor.getString(cursor.getColumnIndex(SQLEditText.EDITTEXT_MODULO)));
                POJOEditText.setNUMERO(cursor.getString(cursor.getColumnIndex(SQLEditText.EDITTEXT_NUMERO)));
                POJOEditText.setPREGUNTA(cursor.getString(cursor.getColumnIndex(SQLEditText.EDITTEXT_PREGUNTA)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return POJOEditText;
    }

    public void insertarSPEditText(SPEditText spEditText){
        ContentValues contentValues = spEditText.toValues();
        sqLiteDatabase.insert(SQLEditText.tablaSPEditText,null,contentValues);
    }
    public void insertarSPEditTexts(ArrayList<SPEditText> spEditTexts){
        long items = getNumeroItemsSPEditText();
        if(items == 0){
            for (SPEditText spEditText : spEditTexts) {
                try {
                    insertarSPEditText(spEditText);
                }catch (SQLiteException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<SPEditText> getSPEditTexts(String idPregunta) {
        ArrayList<SPEditText> spEditTexts = new ArrayList<SPEditText>();
        String[] whereArgs = new String[]{idPregunta};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLEditText.tablaSPEditText,
                    SQLEditText.ALL_COLUMNS_SPEDITTEXT, SQLEditText.WHERE_CLAUSE_ID_PREGUNTA, whereArgs, null, null, null);
            while(cursor.moveToNext()){
                SPEditText spEditText = new SPEditText();
                spEditText.setID(cursor.getString(cursor.getColumnIndex(SQLEditText.SPEDITTEXT_ID)));
                spEditText.setID_PREGUNTA(cursor.getString(cursor.getColumnIndex(SQLEditText.SPEDITTEXT_ID_PREGUNTA)));
                spEditText.setSUBPREGUNTA(cursor.getString(cursor.getColumnIndex(SQLEditText.SPEDITTEXT_SUBPREGUNTA)));
                spEditText.setVARIABLE(cursor.getString(cursor.getColumnIndex(SQLEditText.SPEDITTEXT_VARIABLE)));
                spEditText.setTIPO(cursor.getString(cursor.getColumnIndex(SQLEditText.SPEDITTEXT_TIPO)));
                spEditText.setLONGITUD(cursor.getString(cursor.getColumnIndex(SQLEditText.SPEDITTEXT_LONGITUD)));
                spEditTexts.add(spEditText);
            }
        }finally {
            if(cursor != null) cursor.close();
        }
        return spEditTexts;
    }
}