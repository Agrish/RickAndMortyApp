package com.mustafagriman.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    public static final int MAX_SWIPE_VALUE = 120;
    CardView cv_cardView;
    TextView tv_characterName, tv_characterStatus, tv_lastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate is called!");

        cv_cardView = findViewById(R.id.card_view);
        tv_characterName = findViewById(R.id.characterName);
        tv_characterStatus = findViewById(R.id.characterStatus);
        tv_lastKnownLocation = findViewById(R.id.lastKnownLocation);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float cardWidth = 260; //cv_cardView.getWidth();
        float cardStart = (displayMetrics.widthPixels / 2);
        //System.out.println("cardWidth: " + cardWidth);
        //System.out.println("Cardstart: " + cardStart);

        cv_cardView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                int action = motionEvent.getAction();

                if (action == MotionEvent.ACTION_DOWN)
                {
                    //System.out.println("ACTION_DOWN");
                    return true;
                }
                if (action == MotionEvent.ACTION_UP)
                {
                    //System.out.println("ACTION_UP");
                    float newX = motionEvent.getRawX();
                    if (Math.abs(newX - cardStart) > MAX_SWIPE_VALUE)
                    {
                        //load new character
                    }

                    cv_cardView.setX(cardStart - (cardWidth/2.0f)); // put card to center of layout

                    return true;
                }
                else if (action == MotionEvent.ACTION_MOVE)
                {
                    System.out.println("ACTION_MOVE");
                    float newX = motionEvent.getRawX();
                    System.out.println("newX:" + newX);

                    if (Math.abs(newX - cardStart) > MAX_SWIPE_VALUE)
                    {
                        System.out.println("Card is swiped enough to load new character!");
                    }
                    else
                    {
                        cv_cardView.setX( newX - (cardWidth/2.0f) );
                    }

                    return true;
                }
                return false;
            }
        });

    }
}