package e.nydaniem.projet_android;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout container;
    private Button envoyer;
    private Button lettre_A;
    private Button lettre_B;
    private Button lettre_C;
    private Button lettre_D;
    private Button lettre_E;
    private Button lettre_F;
    private Button lettre_G;
    private Button lettre_H;
    private Button lettre_I;
    private Button lettre_J;
    private Button lettre_K;
    private Button lettre_L;
    private Button lettre_M;
    private Button lettre_N;
    private Button lettre_O;
    private Button lettre_P;
    private Button lettre_Q;
    private Button lettre_R;
    private Button lettre_S;
    private Button lettre_T;
    private Button lettre_U;
    private Button lettre_V;
    private Button lettre_W;
    private Button lettre_X;
    private Button lettre_Y;
    private Button lettre_Z;

    private TextView lettre_mem;
    private EditText new_lettre;
    private ImageView image;
    private TextView mot_a_trouver;
    private String mot;
    private int compt;
    private int error;
    private ArrayList<Character> listMemLettre = new ArrayList<>();
    private boolean gg;
    private ArrayList<String> wordlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.layout_mot_chercher);
        envoyer = findViewById(R.id.button_envoyer);
        image = findViewById(R.id.image_pendu);
        lettre_mem = findViewById(R.id.lettre_mem);
        mot_a_trouver = findViewById(R.id.mot_a_trouver);
        new_lettre = findViewById(R.id.new_lettre_view);
        lettre_A = findViewById(R.id.A);
        lettre_B = findViewById(R.id.B);
        lettre_C = findViewById(R.id.C);
        lettre_D = findViewById(R.id.D);
        lettre_E = findViewById(R.id.E);
        lettre_F = findViewById(R.id.F);
        lettre_I = findViewById(R.id.I);
        lettre_J = findViewById(R.id.J);
        lettre_K = findViewById(R.id.K);
        lettre_L = findViewById(R.id.L);
        lettre_M = findViewById(R.id.M);
        lettre_N = findViewById(R.id.N);
        lettre_O = findViewById(R.id.O);
        lettre_P = findViewById(R.id.P);
        lettre_Q = findViewById(R.id.Q);
        lettre_R = findViewById(R.id.R);
        lettre_S = findViewById(R.id.S);
        lettre_T = findViewById(R.id.T);
        lettre_U = findViewById(R.id.U);
        lettre_V = findViewById(R.id.V);
        lettre_W = findViewById(R.id.W);
        lettre_X = findViewById(R.id.X);
        lettre_Y = findViewById(R.id.Y);
        lettre_Z = findViewById(R.id.Z);

        init();
        envoyer.setOnClickListener(this);
        Log.d("btn", envoyer.getText().toString());
    }

    public void init() {
        mot = randomword();
        gg = false;
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
        String ntm = (b.getText().toString());
        if (ntm.equals("DEL")){
            new_lettre.setText("");
        }
        else{
            new_lettre.setText(b.getText().toString());
        }
    }


    @Override
    public void onClick(View v) {
        System.out.println("click");
        String lettre_in = new_lettre.getText().toString().toUpperCase();
        new_lettre.setText("");
        Log.d("test", lettre_in);

        if (lettre_in.length() > 0) {
            Log.d("test", lettre_mem.toString());
            if(!letter_present(lettre_in.charAt(0), listMemLettre)){
                Log.d("test", lettre_mem.toString());
                listMemLettre.add(lettre_in.charAt(0));
                checkchar(lettre_in, mot);
                Log.d("test", lettre_mem.toString());
            }
            if (compt == mot.length()){
                gg= true;
                createDialogue(gg);
            }
            if(!mot.contains(lettre_in)){
                error++;
            }
            setimage(error);
            if (error == 6){
                gg=false;
                createDialogue(gg);
            }
            viewlettremem(listMemLettre);
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

    public void setimage (int error){
        switch(error){
            case 1 :
                image.setBackgroundResource(R.drawable.second);
                break;
            case 2 :
                image.setBackgroundResource(R.drawable.third);
                break;
            case 3 :
                image.setBackgroundResource(R.drawable.fourth);
                break;
            case 4 :
                image.setBackgroundResource(R.drawable.fifth);
                break;
            case 5 :
                image.setBackgroundResource(R.drawable.sixth);
                break;
            case 6 :
                image.setBackgroundResource(R.drawable.seventh);
                break;
        }
    }

    public void createDialogue(Boolean gg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vous avez gg !");
        if(!gg) {
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
        System.out.println("test1");
        wordlist = getlistemot();
        System.out.println("test2");
        int random = new Random().nextInt(wordlist.size() + 1) + 1;
        System.out.println("test3 "+ random);
        String mot = wordlist.get(random).trim();
        System.out.println("test4");
        return mot;
    }
}