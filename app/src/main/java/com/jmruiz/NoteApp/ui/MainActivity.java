package com.jmruiz.NoteApp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.jmruiz.NoteApp.R;
import com.jmruiz.NoteApp.db.entity.UserEntity;
import com.jmruiz.NoteApp.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnCreateAccount;
    EditText editTextEmail;
    EditText editTextPassword;

    UserViewModel userViewModel;
    String email, password;

    UserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnLogin = findViewById(R.id.buttonLogin);

        //email = editTextEmail.getText().toString();
        //password = editTextPassword.getText().toString();

        // definir el evento click sobre el boton de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1 userViewModel = new ViewModelProvider(MainActivity.this)
                        //2 .get(UserViewModel.class);
                //final UserEntity userEntity = new UserEntity(email,password);

                //userEntity = new LiveData<UserEntity>;

                //3userEntity = userViewModel.getUserMailAndPass(email, password);

                // logica login
                 /*   if(editTextEmail.getText().toString().matches("") ||
                            editTextPassword.getText().toString().matches("")) {

                        Toast.makeText(getApplicationContext(),
                                "empty values, please " +
                                        "try again", Toast.LENGTH_SHORT).show();

                    }*/

                if(validate()){
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(i);
                }
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(i);
            }
        });
    }
    public boolean validate(){
        boolean valid = true;

        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        userViewModel = new ViewModelProvider(MainActivity.this)
                .get(UserViewModel.class);

        //userEntity = userViewModel.getUserMailAndPass(email, password);

        // Check for a valid email address.
        if (email.toString().isEmpty()) {
            editTextEmail.setError("email is empty");
            valid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()) {
            editTextEmail.setError("enter a valid email address");
            valid = false;

        }  else {
            valid = true;
            editTextEmail.setError(null);
        }

        // Check for a valid password.
        if (password.toString().isEmpty()) {
            editTextPassword.setError("password is empty");
            valid = false;
        }else{
            valid = true;
            editTextPassword.setError(null);
        }

      /*  if(userViewModel.getUserMailAndPass(email, password).getEmail().equals(email) && userViewModel.getUserMailAndPass(email, password).getPassword().equals(password)){
            valid = false;
        }else{
            valid = true;
        }*/

        return valid;
    }
}