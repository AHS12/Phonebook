package com.mdazizulhakim.phonebook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Dialar extends AppCompatActivity {
    ActionBar actionBar;
    EditText edtPhone;
    Button btn_one, btn_two, btn_three, btn_four, btn_five, btn_six, btn_seven, btn_eight, btn_nine, btn_zero, btn_star, btn_hash, btn_del, btn_call, btn_Clr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Dialer");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ED9F23")));

        edtPhone = (EditText) findViewById(R.id.edtPhoneNumber);
//        btn_one = (Button) findViewById(R.id.btnOne);
//        btn_two = (Button) findViewById(R.id.btnTwo);
//        btn_three = (Button) findViewById(R.id.btnThree);
//        btn_four = (Button) findViewById(R.id.btnFour);
//        btn_five = (Button) findViewById(R.id.btnFive);
//        btn_six = (Button) findViewById(R.id.btnSix);
//        btn_seven = (Button) findViewById(R.id.btnSeven);
//        btn_eight = (Button) findViewById(R.id.btnEight);
//        btn_nine = (Button) findViewById(R.id.btnNine);
//        btn_zero = (Button) findViewById(R.id.btnZero);
//        btn_star = (Button) findViewById(R.id.btnStar);
//        btn_hash = (Button) findViewById(R.id.btnHash);
//        btn_del = (Button) findViewById(R.id.btndel);
//        btn_call = (Button) findViewById(R.id.btnCall);
//        btn_Clr = (Button) findViewById(R.id.btnClearAll);


    }

    public void buttonClickEvent(View v) {
        String phoneNo = edtPhone.getText().toString();
        try {

            switch (v.getId()) {
                case R.id.btnStar:
                    phoneNo += "*";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnHash:
                    phoneNo += "#";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnZero:
                    phoneNo += "0";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnOne:
                    phoneNo += "1";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnTwo:
                    phoneNo += "2";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnThree:
                    phoneNo += "3";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnFour:
                    phoneNo += "4";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnFive:
                    phoneNo += "5";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnSix:
                    phoneNo += "6";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnSeven:
                    phoneNo += "7";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnEight:
                    phoneNo += "8";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnNine:
                    phoneNo += "9";
                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btndel:
                    if (phoneNo != null && phoneNo.length() > 0) {
                        phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                    }

                    edtPhone.setText(phoneNo);
                    break;
                case R.id.btnClearAll:
                    edtPhone.setText("");
                    break;
                case R.id.btnCall:
                    if (phoneNo.trim().equals("")) {
                        edtPhone.setError("Please Enter Number");
                        requestFocus(edtPhone);
                    } else {
                        Boolean isHash = false;
                        if (phoneNo.subSequence(phoneNo.length() - 1, phoneNo.length()).equals("#")) {
                            phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                            String callInfo = "tel:" + phoneNo + Uri.encode("#");
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse(callInfo));
                            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(callIntent);
                        } else {
                            String callInfo = "tel:" + phoneNo;
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse(callInfo));
                            startActivity(callIntent);
                        }
                    }
                    break;
            }

        } catch (Exception ex) {

        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
