package e.nydaniem.projet_android;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;


public class Aventure extends AppCompatActivity {
    private MediaPlayer mySound;

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
        setContentView(R.layout.activity_aventure);
        mySound = MediaPlayer.create(this, R.raw.aventure);
        playMusic();
    }
    public void onClickLettre(View v) {
        System.out.println("lol");
    }
}