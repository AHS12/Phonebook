package com.mdazizulhakim.phonebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity {

    ActionBar actionBar;
    TextView txt_name, txt_phone, txt_email;
    Button btn_edit, btn_delete;
    ImageButton btn_call, btn_msg, btn_mail;
    MySQLite mySQLite;
    String ID, Name, Phone, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Contact Details");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ED9F23")));

        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_phone = (TextView) findViewById(R.id.txt_phone);
        txt_email = (TextView) findViewById(R.id.txt_email);

        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_call = (ImageButton) findViewById(R.id.btn_call);
        btn_msg = (ImageButton) findViewById(R.id.btn_msg);
        btn_mail = (ImageButton) findViewById(R.id.btn_mail);

        mySQLite = new MySQLite(this);

        ID = getIntent().getExtras().getString("ID");
        Name = getIntent().getExtras().getString("Name");
        Phone = getIntent().getExtras().getString("Phone");
        Email = getIntent().getExtras().getString("Email");

        txt_name.setText("Name: " + Name);
        txt_phone.setText("Phone: " + Phone);
        txt_email.setText("Email: " + Email);


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDetails.this, EditContact.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("ID", ID);
                intent.putExtra("Name", Name);
                intent.putExtra("Phone", Phone);
                intent.putExtra("Email", Email);
                startActivity(intent);
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //confirmMsg();
                new android.app.AlertDialog.Builder(ContactDetails.this)
                        .setTitle("Delete Confirmation")
                        .setMessage("Are you sure you want to Delete this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean chk = mySQLite.deleteData(ID);
                                if (chk == true) {
                                    Toast.makeText(ContactDetails.this, "Contact deleted!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ContactDetails.this, MainActivity.class);
                                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(ContactDetails.this, "Error!Contact deletion failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Phone));
                // intent.setData(Uri.parse("tel :" + Phone));
                startActivity(intent);
            }
        });

        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + Phone));
                startActivity(intent);

            }
        });

        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND, Uri.parse("email"));
                String s[] = {Email};
                intent.putExtra(Intent.EXTRA_EMAIL, s);
                intent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(intent, "Launch email");
                startActivity(chooser);

            }
        });


    }

    public void confirmMsg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this contact?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                boolean chk = mySQLite.deleteData(ID);
                if (chk == true) {
                    Toast.makeText(ContactDetails.this, "Contact deleting successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ContactDetails.this, MainActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ContactDetails.this, "Error!Contact deletion failed", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setTitle("Delete Confirmation");
        builder.show();
    }
}
