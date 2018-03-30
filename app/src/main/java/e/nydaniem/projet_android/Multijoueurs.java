package e.nydaniem.projet_android;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

public class Multijoueurs extends AppCompatActivity {
    private MediaPlayer mySound;

    public void playMusic(Boolean loop, String name) {
        try {
            mySound.setDataSource("android.resource//"+getPackageName()+"/"+name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (loop == true){
            mySound.setLooping(true);
        }else{
            mySound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    onStopSound();
                }
            });
        }
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
        setContentView(R.layout.activity_multijoueurs);
        mySound = MediaPlayer.create(this, R.raw.joueurs2intro);
        playMusic(false, "joueurs2intro.wav");
        }

    public void onStopSound(){
        System.out.println("test");
        if (mySound != null) {
            if (!mySound.isPlaying()) {
                mySound.release();
                mySound = MediaPlayer.create(this, R.raw.joueurs2);
                playMusic(true, "joueurs2.wav");
            }
        }
    }
}