package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginTab extends AppCompatActivity {
    private Button login_button;
    private EditText edtEmail;
    private EditText edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tab);

        initUILogin();
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginLayout();
            }
        });
    }
    public void openLoginLayout() {
        String enterMail = edtEmail.getText().toString().trim();
        String enterPass = edtPass.getText().toString().trim();
        if (true){
//        if (enterMail.equals("dangdung2301@gmail.com") && enterPass.equals("dung123")) {
            Intent intent = new Intent(LoginTab.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            edtEmail.setText("");
            edtPass.setText("");
            Toast.makeText(this, "Your email or password is wrong!", Toast.LENGTH_LONG).show();
        }
    }

    public void initUILogin() {
        login_button = findViewById(R.id.login_button);
        edtEmail = findViewById(R.id.email);
        edtPass = findViewById(R.id.password);
    }
}