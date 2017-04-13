package com.mdazizulhakim.phonebook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Massenger extends AppCompatActivity {

    ActionBar actionBar;
    Button btn_send;
    EditText etxt_Phone, etxt_Msg;

    private static final int Permission_Request_Send_Sms = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massenger);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Messenger");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ED9F23")));

        btn_send = (Button) findViewById(R.id.btn_send);
        etxt_Phone = (EditText) findViewById(R.id.etxt_PhoneMsg);
        etxt_Msg = (EditText) findViewById(R.id.etxt_Message);


    }

    //Method for starting Service
    public void sendSms(View view) {
        int permissionchk = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permissionchk == PackageManager.PERMISSION_GRANTED) {
            //Messege Function
            SendMsg();
        }
    }

    public void SendMsg() {

        String number = etxt_Phone.getText().toString();
        String message = etxt_Msg.getText().toString();

        if (number.isEmpty()) {

            etxt_Phone.setError("Please Enter Number");
            requestFocus(etxt_Phone);

        } else {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number, null, message, null, null);
            Toast.makeText(this, "SMS Sent!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Permission_Request_Send_Sms: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SendMsg();
                } else
                    Toast.makeText(this, "You Do Not Have Permission To Perfrom This Action!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
