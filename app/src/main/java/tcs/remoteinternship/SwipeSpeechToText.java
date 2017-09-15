package tcs.remoteinternship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class SwipeSpeechToText extends AppCompatActivity {
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_speech_to_text);
        iv1=(ImageView) findViewById(R.id.imageview1);
        iv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent in=new Intent(SwipeSpeechToText.this,SpeechToText.class);
                startActivity(in);
                return true;
            }

        });
    }
}
