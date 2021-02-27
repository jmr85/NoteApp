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

    LiveData<UserEntity> userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnLogin = findViewById(R.id.buttonLogin);

        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        // definir el evento click sobre el boton de Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userViewModel = new ViewModelProvider(MainActivity.this)
                        .get(UserViewModel.class);
                //final UserEntity userEntity = new UserEntity(email,password);

                //userEntity = new LiveData<UserEntity>;

                userEntity = userViewModel.getUserMailAndPass(email, password);

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

             /*   if(TextUtils.isEmpty(editTextEmail.getText().toString())){
                    editTextEmail.setError("Email cannot be blank");

                }else if(TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Password cannot be blank");
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(i);
                }*/


             /*   if(editTextEmail.getText().length() == 0 || editTextPassword.getText().length() == 0){//como o tamanho é zero é nulla aresposta

                    editTextEmail.setError("Empty field");
                    editTextPassword.setError("Empty field");


                }*/
             /*   else if (meuEditText.getText().length() < 5){

                    meuEditText.setError("Minimo 5 letras");

                }*/

             /*       if(userEntity == null){
                        Toast.makeText(getApplicationContext(), "Wrong " +
                                "Credentials",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Redirecting...",Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(i);
                    }*/

                // end logica login
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

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("enter a valid email address");
            valid = false;
        } else {
            editTextEmail.setError(null);
            valid = true;
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 8) {
            editTextPassword.setError("between 4 and 8 alphanumeric characters");
            valid = false;
        } else {
            editTextPassword.setError(null);
            valid = true;
        }

        return valid;
    }
}