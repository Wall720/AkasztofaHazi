package com.example.wall720.gallowshazi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonA, buttonZ, buttonGuess;
    private TextView textViewCurrentLetter, textViewGuessedWord;
    private ImageView imageGallows;
    private int letter;
    private AlertDialog.Builder wantANewGame;
    private String word;
    private String guessedWord;
    private int Tries = 13;

    //WORDS
    String[] quotes = new String[] {"NIX","WALL","FINAL","FANTASY","NEWT","BARRACUDA","GALLOWS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        NewGame();

    }

    private void NewGame(){
        this.imageGallows.setImageResource(R.drawable.gallows00);
        this.Tries = 13;
        this.word = quotes[(int) (Math.random() * quotes.length)];

        //ASCII CODE
        this.letter = 65;
        this.textViewCurrentLetter.setText(String.valueOf((char)letter));
        int i = 0;
        this.guessedWord = "";
        while (i < word.length()){
            this.guessedWord += "_ ";
            i++;
        }
        this.textViewGuessedWord.setText(guessedWord + "");

    }

    private void Init(){
        this.imageGallows = (ImageView)findViewById(R.id.i_Gallows);

        this.buttonA = (Button)findViewById(R.id.b_A);
        this.buttonZ = (Button)findViewById(R.id.b_Z);
        this.buttonGuess = (Button)findViewById(R.id.b_Guess);

        this.textViewCurrentLetter = (TextView)findViewById(R.id.tV_CurrentLetter);
        this.textViewGuessedWord = (TextView)findViewById(R.id.tV_GuessedWord);

        this.buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnclick(view,-1);
            }
        });

        this.buttonZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOnclick(view, +1);
            }
        });

        this.buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hit()){
                    hitSwitch();
                    Check();
                }
                else{
                    if (Tries > 0){
                        Death(Tries--);
                    }
                    else{
                        wantANewGame.setTitle("You Died!").show();
                    }
                }
            }
        });

        this.wantANewGame= new AlertDialog.Builder(MainActivity.this);
        this.wantANewGame.setMessage("Want a new game?");

        //TELJESEN ÚJ SZÁMOMRA
        this.wantANewGame.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                NewGame();
            }
        });
        this.wantANewGame.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        this.wantANewGame.setCancelable(false);
        this.wantANewGame.create();



    }


    //TELJESEN ÚJ SZÁMOMRA
    public void Check(){
        int i = 0;
        String checking = "";
        char[] guessedArray = guessedWord.toCharArray();
        while (i < word.length()){
            checking += guessedArray[(i * 2)];
            i++;
        }
        if (word.equals(checking)){
            wantANewGame.setTitle("YAY! You've done it!").show();
        }
    }


    private void Death(int i){
        switch (i){
            case 13:imageGallows.setImageResource(R.drawable.gallows01);
                break;
            case 12:imageGallows.setImageResource(R.drawable.gallows02);
                break;
            case 11:imageGallows.setImageResource(R.drawable.gallows03);
                break;
            case 10:imageGallows.setImageResource(R.drawable.gallows04);
                break;
            case 9:imageGallows.setImageResource(R.drawable.gallows05);
                break;
            case 8:imageGallows.setImageResource(R.drawable.gallows06);
                break;
            case 7:imageGallows.setImageResource(R.drawable.gallows07);
                break;
            case 6:imageGallows.setImageResource(R.drawable.gallows08);
                break;
            case 5:imageGallows.setImageResource(R.drawable.gallows09);
                break;
            case 4:imageGallows.setImageResource(R.drawable.gallows10);
                break;
            case 3:imageGallows.setImageResource(R.drawable.gallows11);
                break;
            case 2:imageGallows.setImageResource(R.drawable.gallows12);
                break;
            case 1:imageGallows.setImageResource(R.drawable.gallows13);
                break;
        }
    }



    //TELJESEN ÚJ SZÁMOMRA
    private boolean hit() {
        int i = 0;
        while (i < word.length() && (char)this.letter != word.charAt(i)) {
            i++;
        }
        if (i < word.length()) {
            return true;
        }
        else {
            return false;
        }
    }

    //TELJESEN ÚJ SZÁMOMRA
    public void hitSwitch(){
        int i = 0;
        while (i < word.length()){
            if(word.charAt(i) == (char)letter){
                char[] guessedArray = guessedWord.toCharArray();
                guessedArray[i*2] = (char)letter;
                guessedWord = String.valueOf(guessedArray);
            }
            i++;
        }
        this.textViewGuessedWord.setText(guessedWord + "");
    }



    public void buttonOnclick(View v,int i){
        int possibleValue = letter + i;
        if(possibleValue < 65) {
            letter = 90;
        }
        else if(possibleValue > 90) {
            letter = 65;
        }
        else{
            letter = possibleValue;
        }

        this.textViewCurrentLetter.setText(String.valueOf((char)letter));
    }
}
