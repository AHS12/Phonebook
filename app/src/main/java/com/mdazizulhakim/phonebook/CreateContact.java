package com.mdazizulhakim.phonebook;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity {

    ActionBar actionBar;
    EditText etxt_name, etxt_phone, etxt_email;
    Button btn_save;
    MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Create Contact");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ED9F23")));
        etxt_name = (EditText) findViewById(R.id.etxt_name);
        etxt_phone = (EditText) findViewById(R.id.etxt_phone);
        etxt_email = (EditText) findViewById(R.id.etxt_email);

        btn_save = (Button) findViewById(R.id.btn_save);
        mySQLite = new MySQLite(this);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etxt_name.getText().toString();
                String phone = etxt_phone.getText().toString();
                String email = etxt_email.getText().toString();

                if (!name.isEmpty()) {
                    if (!phone.isEmpty()) {
                        boolean chk = mySQLite.addToTable(null, name, phone, email);
                        if (chk == true) {
                            Toast.makeText(CreateContact.this, "Contact Saved!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateContact.this, MainActivity.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(CreateContact.this, "Contact Saving Failed!", Toast.LENGTH_SHORT).show();
                    } else {
                        etxt_phone.setError("Please Enter Number");
                        requestFocus(etxt_phone);
                    }
                } else {
                    etxt_name.setError("Please Enter Name");
                    requestFocus(etxt_name);
                }
            }
        });


    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
