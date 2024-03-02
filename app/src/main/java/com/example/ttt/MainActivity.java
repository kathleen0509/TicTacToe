package com.example.ttt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button[][] buttons = new Button[3][3];
    private boolean player1turn = true;
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
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1turn = !player1turn;
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        String message = "Player 1 Wins!\nScore: Player 1 - " + player1Points + ", Player 2 - " + player2Points;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        String message = "Player 2 Wins!\nScore: Player 1 - " + player1Points + ", Player 2 - " + player2Points;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        String message = "Draw!\nScore: Player 1 - " + player1Points + ", Player 2 - " + player2Points;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void updatePointsText() {
        textviewPlayer1.setText("Player 1: " + player1Points);
        textviewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }
}