package pe.com.ricindigus.generadorinei.componentes.componente_edittext;

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

public class EditTextPullParser {
    public static final String EDITTEXT_ID = "ID";
    public static final String EDITTEXT_MODULO = "MODULO";
    public static final String EDITTEXT_NUMERO = "NUMERO";
    public static final String EDITTEXT_PREGUNTA = "PREGUNTA";


    private POJOEditText currentEditText = null;
    private String currentTag = null;
    ArrayList<POJOEditText> POJOEditTexts = new ArrayList<POJOEditText>();

    public ArrayList<POJOEditText> parseXML(Context context){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            try {
                InputStream stream = context.getAssets().open("preguntas_edittext.xml");
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
        return POJOEditTexts;
    }

    private void handleText(String text) {
        String xmlText = text;
        if(currentEditText!= null && currentTag != null){
            switch (currentTag){
                case EDITTEXT_ID:currentEditText.setID(xmlText);break;
                case EDITTEXT_MODULO:currentEditText.setMODULO(xmlText);break;
                case EDITTEXT_NUMERO:currentEditText.setNUMERO(xmlText);break;
                case EDITTEXT_PREGUNTA:currentEditText.setPREGUNTA(xmlText);break;
            }
        }
    }

    private void handleStarTag(String name) {
        if(name.equals("EDITTEXT")){
            currentEditText = new POJOEditText();
            POJOEditTexts.add(currentEditText);
        }else{
            currentTag = name;
        }
    }
}