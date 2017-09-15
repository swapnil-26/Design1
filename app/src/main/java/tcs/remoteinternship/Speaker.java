package tcs.remoteinternship;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Contacts;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Dell-1 on 7/25/2017.
 */

/**
 * Created by Dell-1 on 7/22/2017.
 */

public class Speaker extends Service implements TextToSpeech.OnInitListener {
    public static TextToSpeech mtts;
    public String s;
    public String s1;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("SpeakerService", "Service created successfully!");
        mtts = new TextToSpeech(getApplicationContext(), this);
        mtts.setLanguage(Locale.US);

    }

    @Override
    public void onStart(Intent intent, int startid) {
        s= intent.getStringExtra("sms_str");
        s1=intent.getStringExtra("sms_str1");
        Log.d("SpeakerService", "Service started successfully!");
        Log.d("SpeakerService", "Service started successfully!");
        // Log.d("SpeakerService", "tspker.mtts = " +
        // TextSpeaker.mtts.toString());
        mtts = new TextToSpeech(getApplicationContext(), this);
        mtts.setLanguage(Locale.US);


        Toast.makeText(getApplicationContext(),
                "The service has been done!", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onDestroy() {
        if (mtts != null) {
            mtts.stop();
            Toast.makeText(getApplicationContext(),
                    "The service has been destroyed!", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    @Override
    public void onInit(int arg0) {
        Uri uri;
        String[] projection;
        Uri mBaseUri = Contacts.Phones.CONTENT_FILTER_URL;
        projection = new String[] { android.provider.Contacts.People.NAME };
        try {
            Class<?> c = Class.forName("android.provider.ContactsContract$PhoneLookup");
            mBaseUri = (Uri) c.getField("CONTENT_FILTER_URI").get(mBaseUri);
            projection = new String[] { "display_name" };
        }
        catch (Exception e) {
        }


        uri = Uri.withAppendedPath(mBaseUri, Uri.encode(s1));
        Cursor cursor = Speaker.this.getContentResolver().query(uri, projection, null, null, null);

        String contactName = "";

        if (cursor.moveToFirst())
        {
            contactName = cursor.getString(0);
        }

        cursor.close();
        cursor = null;
        if(contactName=="") {
            SharedPreferences sp=getSharedPreferences("myXML", Context.MODE_PRIVATE);
            float pitch=sp.getFloat("k",0.0f);
            SharedPreferences sp1=getSharedPreferences("myXML1", Context.MODE_PRIVATE);
            float speechrate=sp1.getFloat("k1",0.0f);
            mtts.setPitch(pitch);
            mtts.setSpeechRate(speechrate);
            mtts.speak("Sent from"+s1+s,
                    TextToSpeech.QUEUE_FLUSH, null);}
        else
        {
            SharedPreferences sp=getSharedPreferences("myXML", Context.MODE_PRIVATE);
            float pitch=sp.getFloat("k",0.0f);
            SharedPreferences sp1=getSharedPreferences("myXML1", Context.MODE_PRIVATE);
            float speechrate=sp1.getFloat("k1",0.0f);
            mtts.setPitch(pitch);
            mtts.setSpeechRate(speechrate);
            mtts.speak("Sent from"+contactName+s,
                    TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}

