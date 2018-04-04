package e.nydaniem.projet_android;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Multijoueurs extends AppCompatActivity {
    private LinearLayout container;
    private Button envoyer;
    private TextView lettre_mem;
    private TextView new_lettre;
    private ImageView image;
    private TextView mot_a_trouver;
    private String mot;
    private String mot2;

    private int compt;
    private int error;
    private ArrayList<Character> listMemLettre = new ArrayList<>();
    private boolean gagne;
    private ArrayList<String> wordlist = new ArrayList<>();
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

    public void init() {
        mot = randomword();
        mot2 = randomword();
        gagne = false;
        error = 0;
        compt = 0;
        lettre_mem.setText((""));
        image.setBackgroundResource(R.drawable.first);
        listMemLettre = new ArrayList<>();
        container.removeAllViews();

        for (int i = 0; i < mot.length(); i++) {
            TextView unelettre = (TextView) getLayoutInflater().inflate(R.layout.textlettre, null);
            container.addView(unelettre);
        }
    }



    public void onClickLettre(View v) {
        Button b = (Button)v;
        String bGet = (b.getText().toString());
        if (bGet.equals("DEL")){
            //new_lettre.setText("");
        }
        else{
            //new_lettre.setText(b.getText().toString());
        }
    }

    public void onClickLettre2(View v) {
        Button b = (Button)v;
        String bGet = (b.getText().toString());
        if (bGet.equals("DEL")){
            //new_lettre.setText("");
        }
        else{
            //new_lettre.setText(b.getText().toString());
        }
    }

    @Override
    public void onClick(View v) {
        String lettre_in = new_lettre.getText().toString().toUpperCase();
        new_lettre.setText("");

        if (lettre_in.length() > 0) {
            if(!letter_present(lettre_in.charAt(0), listMemLettre)){
                listMemLettre.add(lettre_in.charAt(0));
                checkchar(lettre_in, mot);
            }
            if (compt == mot.length()){
                gagne= true;
                createDialogue(gagne);
            }
            if(!mot.contains(lettre_in)){
                error++;
            }
            setimage(error);
            if (error == 6){
                gagne=false;
                createDialogue(gagne);
            }
            viewlettremem(listMemLettre);
        }
    }

    @Override
    public void onClick2(View v) {
        String lettre_in2 = new_lettre2.getText().toString().toUpperCase();
        new_lettre.setText("");

        if (lettre_in2.length() > 0) {
            if(!letter_present(lettre_in2.charAt(0), listMemLettre2)){
                listMemLettre.add(lettre_in2.charAt(0));
                checkchar(lettre_in2, mot2);
            }
            if (compt2 == mot2.length()){
                gagne2= true;
                createDialogue(gagne2);
            }
            viewlettremem(listMemLettre2);
        }
    }

    public boolean letter_present(char a, ArrayList<Character> listMemLettre) {
        for (int i = 0; i < listMemLettre.size(); i++) {
            if (listMemLettre.get(i) == a) {
                Toast.makeText(getApplicationContext(), "Lettre déjà utilisée", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    public void checkchar(String lettreCheck, String mot){
        for(int i=0; i<mot.length();i++){
            if(lettreCheck.equals(String.valueOf(mot.charAt(i)))){
                TextView tv = (TextView) container.getChildAt(i);
                tv.setText((String.valueOf(mot.charAt(i))));
                compt++;
            }
        }
    }

    public void viewlettremem(ArrayList<Character> listMemLettre){
        String chaine = "";

        for (int i=0; i < listMemLettre.size(); i++){
            chaine += listMemLettre.get(i)+" ";
        }
        if(!chaine.equals((""))){
            lettre_mem.setText(chaine);
        }
    }


    public void createDialogue(Boolean gagne){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vous avez gagné !");
        if(!gagne) {
            builder.setTitle("Vous avez perdu !");
            builder.setMessage("le mot à trouver était :"+mot);
        }
        builder.setPositiveButton("Rejouer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });
        builder.create().show();
        init();
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
    public String randomword(){
        wordlist = getlistemot();
        int random = new Random().nextInt(wordlist.size() - 71) + 71;
        String mot = wordlist.get(random).trim();
        return mot;
    }
}