package com.jmruiz.NoteApp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jmruiz.NoteApp.R;

public class CreateAccountActivity extends AppCompatActivity {
    Button btnRecord;
    Button btnCreateAccount;
    EditText edTextEmail;
    EditText edTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTextEmail = findViewById(R.id.edTextEmail);
        edTextPassword = findViewById(R.id.edTextPassword);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnRecord = findViewById(R.id.btnRecord);
        // definir el evento click sobre el boton de Login
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // logica login
                    if(edTextEmail.getText().toString().equals("admin") &&
                            edTextPassword.getText().toString().equals("admin")) {

                        Toast.makeText(CreateAccountActivity.this,
                                "Redirecting...",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(CreateAccountActivity.this, "Wrong " +
                                "Credentials",Toast.LENGTH_SHORT).show();

                        btnCreateAccount.setEnabled(false);

                    }
                }

                // end logica login
                Intent i = new Intent(CreateAccountActivity.this, DashboardActivity.class);
                startActivity(i);
            }
        });
    }
}