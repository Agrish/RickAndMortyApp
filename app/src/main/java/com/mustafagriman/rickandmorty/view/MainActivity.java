package com.mustafagriman.rickandmorty.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mustafagriman.rickandmorty.R;
import com.mustafagriman.rickandmorty.model.CharacterModel;
import com.mustafagriman.rickandmorty.model.CharacterPageResponseModel;
import com.mustafagriman.rickandmorty.model.PageInformation;
import com.mustafagriman.rickandmorty.service.RickAndMortyAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
{
    public static final int MAX_SWIPE_VALUE = 120;
    CardView cv_cardView;
    TextView tv_characterName, tv_characterStatus, tv_lastKnownLocation;
    ImageView iv_characterImage;

    ArrayList<CharacterModel> characterModels;
    PageInformation pageInfo;
    private String BASE_URL = "https://rickandmortyapi.com/api/";
    Retrofit retrofit;

    int activePageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //System.out.println("onCreate is called!");

        cv_cardView = findViewById(R.id.card_view);
        tv_characterName = findViewById(R.id.characterName);
        tv_characterStatus = findViewById(R.id.characterStatus);
        tv_lastKnownLocation = findViewById(R.id.lastKnownLocation);
        iv_characterImage = findViewById(R.id.characterImage);

        getDataFromAPI(activePageIndex);  // load the data for first page

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float cardWidth = 260; //cv_cardView.getWidth();
        float cardStart = (displayMetrics.widthPixels / 2.0f);

        cv_cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
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
                        characterModels.remove(0);
                        if(characterModels.size() == 0 && pageInfo.next != null)
                        {
                            activePageIndex++;
                            getDataFromAPI(activePageIndex);
                        }
                        else if(characterModels.size() == 0 && pageInfo.next == null)
                        {
                            Toast.makeText(MainActivity.this, "You reached the last character!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            tv_characterName.setText(characterModels.get(0).name);
                            tv_characterStatus.setText(characterModels.get(0).status);
                            tv_lastKnownLocation.setText(characterModels.get(0).lastKnownLocation.locationName);
                            Picasso.get().load(characterModels.get(0).imageUrl).into(iv_characterImage);
                        }

                    }

                    cv_cardView.setX(cardStart - (cardWidth/2.0f)); // put card to center of layout
                    return true;
                }
                else if (action == MotionEvent.ACTION_MOVE)
                {
                    //System.out.println("ACTION_MOVE");
                    float newX = motionEvent.getRawX();
                    //System.out.println("newX:" + newX);
                    if (Math.abs(newX - cardStart) > MAX_SWIPE_VALUE) {
                        // do nothing - dont drag the card
                        System.out.println("Card is swiped enough to load new character!");
                    }
                    else {
                        // drag the card
                        cv_cardView.setX( newX - (cardWidth/2.0f) );
                    }
                    return true;
                }
                return false;
            }
        });

    }

    public void getDataFromAPI(int pageIndex)
    {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RickAndMortyAPI rickAndMortyAPI = retrofit.create(RickAndMortyAPI.class);
        Call<CharacterPageResponseModel> call = rickAndMortyAPI.getCharacterPageResponse(pageIndex);

        call.enqueue(new Callback<CharacterPageResponseModel>()
        {
            @Override
            public void onResponse(Call<CharacterPageResponseModel> call, Response<CharacterPageResponseModel> response)
            {
                if(response.isSuccessful())
                {
                    Log.i("MainActivity", response.toString());
                    pageInfo = response.body().info;
                    characterModels = new ArrayList<>(response.body().characterModels);

                    tv_characterName.setText(characterModels.get(0).name);
                    tv_characterStatus.setText(characterModels.get(0).status);
                    tv_lastKnownLocation.setText(characterModels.get(0).lastKnownLocation.locationName);
                    Picasso.get().load(characterModels.get(0).imageUrl).into(iv_characterImage);
                }
                else if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Unsuccessful network call!", Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<CharacterPageResponseModel> call, Throwable t)
            {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Please check the Internet connection!", Toast.LENGTH_LONG ).show();
            }
        });
    }
}