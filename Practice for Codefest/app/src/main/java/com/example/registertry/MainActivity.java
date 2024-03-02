package com.example.registertry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClicked (View view) {
        TextView txtfn = findViewById(R.id.txtfn);
        TextView txtsn = findViewById(R.id.txtsn);
        TextView txtemail = findViewById(R.id.txtemail);

        EditText edtfirstname = findViewById(R.id.edtfirstname);
        EditText edtsn = findViewById(R.id.edtsurname);
        EditText edtemail = findViewById(R.id.edtemail);

        txtfn.setText("FirstName" + edtfirstname.getText().toString());
        txtsn.setText("Surname: " + edtsn.getText().toString());
        txtemail.setText("Email: " + edtemail.getText().toString());

    }
}