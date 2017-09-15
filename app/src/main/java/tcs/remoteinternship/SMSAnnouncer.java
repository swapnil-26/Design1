package tcs.remoteinternship;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SMSAnnouncer extends Activity implements TextToSpeech.OnInitListener {
    //Create variables
    //public static TextToSpeech ts;
    double pitch = 0.0f, speechRate = 0.0f;
    //declare views/controls
    private TextToSpeech tts1;
    SeekBar sBSpeechRate, sBPitchRate;
    private SeekBar sBVolume = null;
    private AudioManager audioManager = null;
    TextView tv;
    public int value,value1,value2;
    // EditText eTPronounce;
    //Button btnSpeak;
    // Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_smsannouncer);
        tv= (TextView) findViewById(R.id.tvtxt);

        final TextView tv1 = (TextView) findViewById(R.id.text);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/lob.ttf");
        tv1.setTypeface(typeface2);


        initializeControls();
  /*Initialize the Text to speech engine using the default TTS engine.
   *This will also initialize the associated TextToSpeech engine if it isn't already running.
   */
        tts1 = new TextToSpeech(this, this);

       /* bt= (Button) findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });*/

        final TextView tv2 = (TextView) findViewById(R.id.textView1);
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/lob.ttf");
        tv2.setTypeface(typeface3);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("myXML", Context.MODE_PRIVATE);
                //name is of xml file...//returns object of implementing class of shared preference
                SharedPreferences.Editor editor = sp.edit();
                //sp stores the object of the xml file and we need to edit the text and that is of Editor return type...
                // returns shared preference in editable form
                editor.putFloat("k", (float) pitch);
                editor.commit();

                SharedPreferences sp1 = getSharedPreferences("myXML1", Context.MODE_PRIVATE);
                //name is of xml file...//returns object of implementing class of shared preference
                SharedPreferences.Editor editor1 = sp1.edit();
                //sp stores the object of the xml file and we need to edit the text and that is of Editor return type...
                // returns shared preference in editable form
                editor1.putFloat("k1", (float) speechRate);
                editor1.commit();
                Toast.makeText(SMSAnnouncer.this, "Changes have been saved", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initializeControls() {
        try {
            //get reference of the UI Controls
            sBSpeechRate = (SeekBar) findViewById(R.id.sBSpeechRate);
            sBPitchRate = (SeekBar) findViewById(R.id.sBPitchRate);

            sBVolume = (SeekBar) findViewById(R.id.sBVolume);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            sBVolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            sBVolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            value=0;
            SharedPreferences prefs = getSharedPreferences("mySharedPrefsFilename", Context.MODE_PRIVATE);

            value = prefs.getInt("seekBarValue", 0); // 0 is default

            sBSpeechRate.setProgress(value);
            value1=0;
            SharedPreferences prefs1 = getSharedPreferences("mySharedPrefsFilename1", Context.MODE_PRIVATE);

            value1 = prefs1.getInt("seekBarValue", 0); // 0 is default

            sBVolume.setProgress(value1);
            value2=0;
            SharedPreferences prefs2 = getSharedPreferences("mySharedPrefsFilename2", Context.MODE_PRIVATE);

            value2 = prefs2.getInt("seekBarValue", 0); // 0 is default

            sBPitchRate.setProgress(value2);
            sBVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                    SharedPreferences prefs1 = getSharedPreferences("mySharedPrefsFilename1", Context.MODE_PRIVATE);
                    // Don't forget to call commit() when changing preferences.
                    prefs1.edit().putInt("seekBarValue", arg0.getProgress()).commit();
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
            //eTPronounce=(EditText)findViewById(R.id.eTPronounce);
            //btnSpeak=(Button)findViewById(R.id.btnSpeak);
  /*initialize seek bar change listener to listen every change on seekbar
   * either increment or decrement*/
            sBSpeechRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    SharedPreferences prefs = getSharedPreferences("mySharedPrefsFilename", Context.MODE_PRIVATE);
                    // Don't forget to call commit() when changing preferences.
                    prefs.edit().putInt("seekBarValue", seekBar.getProgress()).commit();
                    // int value = 0;

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    //divide progress by 10 to get speech rate in float values like 0.1
                    speechRate = ((double) progress + 1) / 10;
                }
            });

            sBPitchRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    SharedPreferences prefs2 = getSharedPreferences("mySharedPrefsFilename2", Context.MODE_PRIVATE);
                    // Don't forget to call commit() when changing preferences.
                    prefs2.edit().putInt("seekBarValue", seekBar.getProgress()).commit();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    //divide progress by 10 to get pitch in float values like 0.1
                    pitch = ((double) progress + 1) / 10;
                }
            });
            //set default text as Welcome to shaikhhamadali.blogspot.com
            //eTPronounce.setText("Welcome to shaikhhamadali.blogspot.com");
            //set on click listener to button speak call speakOut Method to speak text
            //btnSpeak.setOnClickListener(new OnClickListener() {
            // @Override
           /* public void onClick(View v) {
                speakOut();
            }*/
            //});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onInit ( int status){
        //check the status
        if (status == TextToSpeech.SUCCESS) {
            //set language Locale to US
            int result = tts1.setLanguage(Locale.US);
            //check that is language locale available on device or supported
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            } else {
                //then enable button to listen for listener
                //btnSpeak.setEnabled(true);
                //and speak by calling speakOut
                speakOut();
            }

        } else {
            //show toast if initialization failed
            Toast.makeText(getBaseContext(), "TTS Engine Initilization Failed!", Toast.LENGTH_SHORT).show();
        }

    }

    private void speakOut() {
        //get entered text to speak
        //String text = eTPronounce.getText().toString();
        //set pitch rate adjusted by user
        tts1.setPitch((float) pitch);
        //set speech rate adjusted by user
        tts1.setSpeechRate((float) speechRate);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tts1.setLanguage(Locale.US);
                //tts1.setPitch((float) pitch);
                //tts1.setSpeechRate((float) speechRate);
                //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                // value1, 0);
                tts1.speak("Now I will speak aloud the messages for you",
                        TextToSpeech.QUEUE_FLUSH, null);
            }
        });
  /*pass text to speak using engine and pass Queue mode as QUEUE_FLUSH where all entries in the playback queue
   *(media to be played and text to be synthesized) are dropped and
   *replaced by the new entry. Queues are flushed with respect to
   *a given calling app. Entries in the queue from other callers are not discarded*/
        //tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        //Intent i=new Intent(this,)


    }

    @Override
    public void onDestroy() {
        // Don't forget to stop and shutdown text to speech engine!
        if (tts1 != null) {
            tts1.stop();
            tts1.shutdown();
        }
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        //new AlertDialog.Builder(this)
    }


}


