package com.jmruiz.NoteApp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jmruiz.NoteApp.R;
import com.jmruiz.NoteApp.db.entity.UserEntity;
import com.jmruiz.NoteApp.viewmodel.UserViewModel;

public class CreateAccountActivity extends AppCompatActivity {
    Button btnRecord;
    Button btnCreateAccount;
    EditText edTextEmail;
    EditText edTextPassword;

    String email, password;

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        edTextEmail = findViewById(R.id.edTextEmail);
        edTextPassword = findViewById(R.id.edTextPassword);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnRecord = findViewById(R.id.btnRecord);

        email = edTextEmail.getText().toString();
        password = edTextEmail.getText().toString();

        // definir el evento click sobre el boton de Login
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!edTextEmail.getText().toString().equals("") &&
                            !edTextPassword.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Redirecting...",Toast.LENGTH_SHORT).show();


                        userViewModel = new ViewModelProvider(CreateAccountActivity.this)
                                .get(UserViewModel.class);
                        userViewModel.insertUser(new UserEntity(email, password));

                        Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(i);


                    }else{
                        //Toast.makeText(getApplicationContext(), "clickkkk", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Wrong " +
                                "New Account",Toast.LENGTH_SHORT).show();

                        //btnCreateAccount.setEnabled(false);
                    }
                }
        });
    }
}