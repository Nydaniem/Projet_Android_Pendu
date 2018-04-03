package e.nydaniem.projet_android;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class Aventure extends AppCompatActivity {
    private MediaPlayer mySound;
    private ImageView img;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView imagepoint1;
    private ImageView imagepoint2;
    private ImageView imagepoint3;
    private ImageView zombie;
    private ImageView dragon;
    private ImageView dragonfire;
    private ImageView zombiefire;


    private LinearLayout layoutbg;
    private LinearLayout layoutfield;
    private LinearLayout container;
    private LinearLayout framemobs;

    private TextView new_mot;
    private TextView mot_a_ecrire;
    private TextView levelView;
    private ArrayList<String> wordlist = new ArrayList<>();

    private int level;
    private int point;
    private int tempsbg = 60000;
    private int tempsfield = tempsbg/10;

    public boolean builder = false;
    public AlertDialog builderglobal ;


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

    public void mobscall(){
        Handler mobs = new Handler();

            mobs.postDelayed(new Runnable() {
            @Override
            public void run() {

                int randomimg = new Random().nextInt(2);
                ObjectAnimator animation5 = ObjectAnimator.ofFloat(zombie, "translationX", -framemobs.getWidth()/2).setDuration(3000);
                ObjectAnimator animation6 = ObjectAnimator.ofFloat(zombiefire, "translationX", -framemobs.getWidth()/2).setDuration(3000);
                ObjectAnimator animation7 = ObjectAnimator.ofFloat(dragon, "translationX", -framemobs.getWidth()/2).setDuration(3000);
                ObjectAnimator animation8 = ObjectAnimator.ofFloat(dragonfire, "translationX", -framemobs.getWidth()/2).setDuration(3000);

                if (randomimg == 1){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dragon.setVisibility(View.INVISIBLE);
                            dragonfire.setVisibility(View.INVISIBLE);
                            zombiefire.setVisibility(View.VISIBLE);
                            zombie.setVisibility(View.VISIBLE);
                        }
                    },0);

                    animation5.start();
                    animation6.start();

                    animation5.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            zombie.setX(framemobs.getWidth());
                            dragon.setX(framemobs.getWidth());
                            zombiefire.setX(framemobs.getWidth());
                            dragonfire.setX(framemobs.getWidth());
                            run();
                        }

                    });



                }else{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dragon.setVisibility(View.VISIBLE);
                            dragonfire.setVisibility(View.VISIBLE);
                            zombie.setVisibility(View.INVISIBLE);
                            zombie.setVisibility(View.INVISIBLE);
                        }
                    }, 0);
                    animation7.start();
                    animation8.start();

                    animation7.addListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animation) {
                            zombie.setX(framemobs.getWidth());
                            dragon.setX(framemobs.getWidth());
                            zombiefire.setX(framemobs.getWidth());
                            dragonfire.setX(framemobs.getWidth());
                            run();
                        }

                    });


                }
            }
        }, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aventure);

        mySound = MediaPlayer.create(this, R.raw.aventure);
        playMusic();

        img = findViewById(R.id.gifImageView);
        img2 = findViewById(R.id.gifImageView2);
        img3 = findViewById(R.id.field1);
        img4 = findViewById(R.id.field2);
        imagepoint1 = findViewById(R.id.point1);
        imagepoint2 = findViewById(R.id.point2);
        imagepoint3 = findViewById(R.id.point3);
        zombie = findViewById(R.id.zombie);
        dragon = findViewById(R.id.dragon);
        zombiefire = findViewById(R.id.firezombie);
        dragonfire = findViewById(R.id.firedragon);

        framemobs = findViewById(R.id.layoutmobs);

        container = findViewById(R.id.containerMotAEcrire);
        mot_a_ecrire = findViewById(R.id.motAEcrire);
        new_mot = findViewById(R.id.newMot);
        levelView = findViewById(R.id.levelView);

        layoutbg = findViewById(R.id.layoutBg);
        layoutfield = findViewById(R.id.layoutField);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation = ObjectAnimator.ofFloat(img, "translationX", -layoutbg.getWidth()/2).setDuration(tempsbg);
                ObjectAnimator animation2 = ObjectAnimator.ofFloat(img2, "translationX", -layoutbg.getWidth()/2).setDuration(tempsbg);
                animation.start();
                animation2.start();


                animation.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        img.setX(0);
                        img2.setX((layoutbg.getWidth()/2)-20);
                        run();
                    }

                });
            }
        }, 0);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                ObjectAnimator animation3 = ObjectAnimator.ofFloat(img3, "translationX", -layoutfield.getWidth()/2).setDuration(tempsfield);
                ObjectAnimator animation4 = ObjectAnimator.ofFloat(img4, "translationX", -layoutfield.getWidth()/2).setDuration(tempsfield);

                animation3.start();
                animation4.start();

                animation3.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        img3.setX(0);
                        img4.setX(layoutfield.getWidth()/2);
                        run();
                    }

                });

            }
        }, 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                zombie.setX(framemobs.getWidth());
                dragon.setX(framemobs.getWidth());
                zombiefire.setX(framemobs.getWidth());
                dragonfire.setX(framemobs.getWidth());
            }
        }, 0);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }, 0);

        final Handler ha = new Handler();
        ha.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                onGame();
                ha.postDelayed(this, 3000);
            }
        }, 3000);

        mobscall();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void init() {
        mot_a_ecrire.setText(randomword());
        new_mot.setText("");
        level = 1;
        point = 0;
        imagepoint1.setVisibility(View.INVISIBLE);
        imagepoint2.setVisibility(View.INVISIBLE);
        imagepoint3.setVisibility(View.INVISIBLE);
        zombie.setX(framemobs.getWidth());
        dragon.setX(framemobs.getWidth());
        zombiefire.setX(framemobs.getWidth());
        dragonfire.setX(framemobs.getWidth());
        dragonfire.setVisibility(View.INVISIBLE);
        zombiefire.setVisibility(View.INVISIBLE);
        levelView.setText("level 1");

    }



    @SuppressLint("SetTextI18n")
    public void onClickLettre(View v) {
        Button b = (Button)v;
        String bGet = (b.getText().toString());
        if (bGet.equals("DEL")){
            new_mot.setText("");
        }
        else{
            new_mot.setText(new_mot.getText().toString()+b.getText().toString());
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onGame() {
        String motEcrit = new_mot.getText().toString().toUpperCase();

        if (motEcrit.length() > 0 && (mot_a_ecrire.getText().toString().equals(motEcrit))) {
            dragonfire.setVisibility(View.VISIBLE);
            zombiefire.setVisibility(View.VISIBLE);
            point = point + 1;

            if (point == 1){
                imagepoint1.setVisibility(View.VISIBLE);
            }
            else if (point == 2){
                imagepoint2.setVisibility(View.VISIBLE);
            }
            else if (point == 3){
                imagepoint3.setVisibility(View.VISIBLE);
            }
            else if (point == 4){
                imagepoint1.setVisibility(View.INVISIBLE);
                imagepoint2.setVisibility(View.INVISIBLE);
                imagepoint3.setVisibility(View.INVISIBLE);
                point = 0;
                level = level + 1;
                levelView.setText("level "+String.valueOf(level));
            }

            mot_a_ecrire.setText(randomword());
            new_mot.setText("");
        }
        else if (builder == false) {
            builderglobal = createDialogue().create();
            builderglobal.show();
            builder = true;
            init();
        }
        else if (!builderglobal.isShowing()){
            init();
            builder = false;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public AlertDialog.Builder createDialogue(){
        AlertDialog.Builder builderlocal = new AlertDialog.Builder(this);

        builderlocal.setTitle("Vous avez perdu !");
        builderlocal.setMessage("votre score : niveau "+level+" point "+point);

        builderlocal.setPositiveButton("Rejouer", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });
        return builderlocal;
    }

    public ArrayList<String> getlistemot(){
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(getAssets().open("pendu_liste.txt")));
            String line;
            while ((line = buffer.readLine()) != null) {
                wordlist.add(line);
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordlist;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String randomword(){
        wordlist = getlistemot();
        String mot = "";
        if (level == 1){
            int random = new Random().nextInt(26);
            return wordlist.get(random).trim();
        }
        else if(level == 2){
            int random = new Random().nextInt(73 - 27)+27;
            return wordlist.get(random).trim();
        }
        else if(level == 3){
            int random = new Random().nextInt(111 - 73)+73;
            return wordlist.get(random).trim();
        }
        else if(level == 4){
            int random = new Random().nextInt(201 - 111)+111;
            return wordlist.get(random).trim();
        }
        else if(level == 5){
            int random = new Random().nextInt(361 - 201)+201;
            return wordlist.get(random).trim();
        }
        else if(level == 6){
            int random = new Random().nextInt(540 - 361)+361;
            return wordlist.get(random).trim();
        }
        else if(level == 7){
            int random = new Random().nextInt(705 - 540)+540;
            return wordlist.get(random).trim();
        }
        else if(level == 8){
            int random = new Random().nextInt(810 - 705)+705;
            return wordlist.get(random).trim();
        }
        else if(level == 9){
            int random = new Random().nextInt(865 - 810)+810;
            return wordlist.get(random).trim();
        }
        else if(level == 10){
            int random = new Random().nextInt(888 - 865)+865;
            return wordlist.get(random).trim();
        }
        else if(level == 11){
            int random = new Random().nextInt(900 - 888)+888;
            return wordlist.get(random).trim();
        }
        else if(level == 12){
            int random = new Random().nextInt(904 - 900)+900;
            return wordlist.get(random).trim();
        }
        else{
            for(int i=0; i < level; i++){
                int random = new Random().nextInt(26);
                if (mot.equals("")){
                    mot = wordlist.get(random).trim();
                }
                else{
                    mot = mot + wordlist.get(random).trim();
                }
            }
            return mot;
        }
    }
}