package pe.com.ricindigus.generadorinei.componentes.componente_radio.pojos;

import android.content.ContentValues;

import pe.com.ricindigus.generadorinei.componentes.componente_radio.modelo.SQLRadio;


/**
 * Created by dmorales on 21/12/2017.
 */

public class PRadio {
    private String ID;
    private String MODULO;
    private String NUMERO;
    private String PREGUNTA;


    public PRadio() {
        this.ID = "";
        this.MODULO = "";
        this.NUMERO = "";
        this.PREGUNTA = "";
    }

    public PRadio(String ID, String MODULO, String NUMERO) {
        this();
        this.ID = ID;
        this.MODULO = MODULO;
        this.NUMERO = NUMERO;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMODULO() {
        return MODULO;
    }

    public void setMODULO(String MODULO) {
        this.MODULO = MODULO;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getPREGUNTA() {
        return PREGUNTA;
    }

    public void setPREGUNTA(String PREGUNTA) {
        this.PREGUNTA = PREGUNTA;
    }


    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues(5);
        contentValues.put(SQLRadio.RADIO_ID,ID);
        contentValues.put(SQLRadio.RADIO_MODULO,MODULO);
        contentValues.put(SQLRadio.RADIO_NUMERO,NUMERO);
        contentValues.put(SQLRadio.RADIO_PREGUNTA,PREGUNTA);
        return contentValues;
    }
}
