package tcs.remoteinternship;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animation animBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        final Animation animBlink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);

        final ImageButton img1= (ImageButton) findViewById(R.id.imageButton);
        assert img1 != null;
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img1.startAnimation(animBlink);
            }
        });
        final ImageButton img2= (ImageButton) findViewById(R.id.imageButton2);
        assert img2 != null;
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img2.startAnimation(animBlink);
            }
        });
        final ImageButton img3= (ImageButton) findViewById(R.id.imageButton3);
        assert img3 != null;
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img3.startAnimation(animBlink);
            }
        });






        final TextView myTextView1 = (TextView) findViewById(R.id.textView1);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/lob.ttf");
        myTextView1.setTypeface(typeface1);
        myTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView1.startAnimation(animBounce);
                Intent i = new Intent(MainActivity.this, SwipeSmsActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

        final TextView myTextView2 = (TextView) findViewById(R.id.textView2);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/lob.ttf");
        myTextView2.setTypeface(typeface2);
        myTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView2.startAnimation(animBounce);
                Intent intent2=new Intent(MainActivity.this,SwipeTTS.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });
        final TextView myTextView3 = (TextView) findViewById(R.id.textView3);
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/lob.ttf");
        myTextView3.setTypeface(typeface3);
        myTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView3.startAnimation(animBounce);
                Intent intent=new Intent(MainActivity.this,SwipeSpeechToText.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

            }
        });
        final TextView myTextView4 = (TextView) findViewById(R.id.textView4);
        Typeface typeface4 = Typeface.createFromAsset(getAssets(), "fonts/lob.ttf");
        myTextView4.setTypeface(typeface4);
        myTextView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView4.startAnimation(animBounce);
                MyDialog md=new MyDialog(MainActivity.this);
                md.show(getFragmentManager(),"My Alert");
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
                moveTaskToBack(true);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}

