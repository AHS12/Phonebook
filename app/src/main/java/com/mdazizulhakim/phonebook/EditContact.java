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

public class EditContact extends AppCompatActivity {
    ActionBar actionBar;
    EditText etxt_name_up, etxt_phone_up, etxt_email_up;
    Button btn_update;
    String ID, Name, Phone, Email;
    MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Update Contact");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ED9F23")));

        etxt_name_up = (EditText) findViewById(R.id.etxt_name_up);
        etxt_phone_up = (EditText) findViewById(R.id.etxt_phone_up);
        etxt_email_up = (EditText) findViewById(R.id.etxt_email_up);

        btn_update = (Button) findViewById(R.id.btn_update);

        mySQLite = new MySQLite(this);

        ID = getIntent().getExtras().getString("ID");
        Name = getIntent().getExtras().getString("Name");
        Phone = getIntent().getExtras().getString("Phone");
        Email = getIntent().getExtras().getString("Email");

        etxt_name_up.setText(Name);
        etxt_phone_up.setText(Phone);
        etxt_email_up.setText(Email);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etxt_name_up.getText().toString();
                String phone = etxt_phone_up.getText().toString();
                String email = etxt_email_up.getText().toString();

                if (!name.isEmpty()) {
                    if (!phone.isEmpty()) {
                        boolean chk = mySQLite.updateData(ID, name, phone, email);
                        if (chk == true) {
                            Toast.makeText(EditContact.this, "Contact Information Updated!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditContact.this, MainActivity.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else
                            Toast.makeText(EditContact.this, "Contact Updating Failed!", Toast.LENGTH_SHORT).show();
                    } else {
                        etxt_phone_up.setError("Please Enter Number");
                        requestFocus(etxt_phone_up);
                    }
                } else {
                    etxt_name_up.setError("Please Enter Name");
                    requestFocus(etxt_name_up);
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
