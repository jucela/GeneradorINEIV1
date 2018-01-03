package pe.com.ricindigus.generadorinei.componentes.componente_radio;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by dmorales on 28/12/2017.
 */

public class RadioPullParser {
    public static final String RADIO_ID = "ID";
    public static final String RADIO_MODULO = "MODULO";
    public static final String RADIO_NUMERO = "NUMERO";
    public static final String RADIO_PREGUNTA = "PREGUNTA";

    private POJORadio currentRadio = null;
    private String currentTag = null;
    ArrayList<POJORadio> POJORadios = new ArrayList<POJORadio>();

    public ArrayList<POJORadio> parseXML(Context context){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            try {
                InputStream stream = context.getAssets().open("preguntas_radio.xml");
                xpp.setInput(stream,null);

                int eventType = xpp.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        handleStarTag(xpp.getName());
                    }else if(eventType == XmlPullParser.END_TAG){
                        currentTag = null;
                    }else if(eventType == XmlPullParser.TEXT){
                        handleText(xpp.getText());
                    }
                    eventType = xpp.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return POJORadios;
    }

    private void handleText(String text) {
        String xmlText = text;
        if(currentRadio!= null && currentTag != null){
            switch (currentTag){
                case RADIO_ID:currentRadio.setID(xmlText);break;
                case RADIO_MODULO:currentRadio.setMODULO(xmlText);break;
                case RADIO_NUMERO:currentRadio.setNUMERO(xmlText);break;
                case RADIO_PREGUNTA:currentRadio.setPREGUNTA(xmlText);break;
            }
        }
    }

    private void handleStarTag(String name) {
        if(name.equals("RADIO")){
            currentRadio = new POJORadio();
            POJORadios.add(currentRadio);
        }else{
            currentTag = name;
        }
    }
}