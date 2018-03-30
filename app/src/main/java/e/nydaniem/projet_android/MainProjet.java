package e.nydaniem.projet_android;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import java.io.IOException;
import java.util.HashSet;

public class MainProjet extends AppCompatActivity {

    MediaPlayer mySound;

    public void playMusic() {
        mySound.setLooping(true);
        mySound.start();

    }

    @Override
    public void onStop(){
        super.onStop();
        mySound.pause();

    }
    @Override
    public void onRestart() {
        super.onRestart();
        mySound.start();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_projet);
        mySound = MediaPlayer.create(this, R.raw.menu);
        playMusic();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate our menu from the resources by using the menu inflater.
        getMenuInflater().inflate(R.menu.main, menu);

        // Need to use MenuItemCompat methods to call any action item related methods

        return true;
    }

}
