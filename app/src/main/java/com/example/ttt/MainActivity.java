package com.example.ttt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    // 3 x 3 kay ang list of array kay 3 x 3
    /* This line of code is declaring a two-dimensional array of Buttons named buttons.
    Each element in this array can hold a Button object.*/
    private Button[][] buttons = new Button[3][3];

    // para pag start sa game ang player 1(X) ang una mag tira
    /*This line of code declares a variable named player1turn which is of type boolean
    (meaning it can only have two possible values: true or false). */
    private boolean player1turn = true;

    // this will count our rounds of if there are nine rounds (kay 9 fields raman) and no winner we will have a draw
    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textviewPlayer1;
    private TextView textviewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewPlayer1 = findViewById(R.id.player1_score);
        textviewPlayer2 = findViewById(R.id.player2_score);

        //clicks on buttons

        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j ++){
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button btnreset = findViewById(R.id.btn_reset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) { // meaning it will check if this button that was clicked contains an empty string if dili na use na sya before X or O sya
            return;
        }
        if (player1turn) {
            ((Button) v).setText("X");
        }else {
            ((Button) v).setText("O");
        }

        roundCount++;//incriment roundcount so we know that one more round is over

        //gi add after ang return false; sa baba

        if (checkForWin()){
            if (player1turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        }else {
            player1turn = !player1turn;
        }


    }

    private boolean checkForWin() { //check if someone won or not (tru or false)
        String[][] field = new String [3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // for COLUMNS

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) // responsible for comparing the three fields next to each other
                  && field [i][0].equals(field[i][2])
                  && !field [i][0].equals("")){        //  responsible to make sure that its not an empty field
              return true;
            }
        }

        // for ROWS
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) // responsible for comparing the three fields next to each other
                    && field [0][i].equals(field[2][i])
                    && !field [0][i].equals("")){        //  responsible to make sure that its not an empty field
                return true;
            }
        }

        //for DIAGONALS
        if (field[0][0].equals(field[1][1]) // responsible for comparing the three fields next to each other
                && field [0][0].equals(field[2][2])
                && !field [0][0].equals("")){        //  responsible to make sure that its not an empty field
            return true;
        }

        if (field[0][2].equals(field[1][1]) // responsible for comparing the three fields next to each other
                && field [0][2].equals(field[2][0])
                && !field [0][2].equals("")){        //  responsible to make sure that its not an empty field
            return true;
        }
        return false;
    }

    // gi add after ang player1turn = !player1turn;

    private void player1Wins () {
        player1Points++;
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins () {
        player2Points++;
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw () {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText () {
        textviewPlayer1.setText("Player 1: " + player1Points);
        textviewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard () {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1turn = true;
    }

    private void resetGame (){
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

}