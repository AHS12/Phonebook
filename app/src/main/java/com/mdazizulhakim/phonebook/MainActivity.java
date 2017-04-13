package com.mdazizulhakim.phonebook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {
    ActionBar actionBar;
    android.widget.SearchView contact_search;
    ListView contact_list;
    ArrayAdapter adapter;
    MySQLite mySQLite;

    String IDlist[], Namelist[], Phonelist[], Emaillist[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ED9F23")));//#ED9F23
       // actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        contact_list = (ListView) findViewById(R.id.contact_list);
        contact_search = (android.widget.SearchView) findViewById(R.id.contact_search);

        // listAdapter = new ArrayAdapter<String>(this,);
        mySQLite = new MySQLite(this);

        Cursor cursor = mySQLite.display();
        if (cursor.getCount() > 0) {

            IDlist = new String[cursor.getCount()];
            Namelist = new String[cursor.getCount()];
            Phonelist = new String[cursor.getCount()];
            Emaillist = new String[cursor.getCount()];

            cursor.moveToFirst();


            int count = 0;

            do {
                IDlist[count] = cursor.getString(0);
                Namelist[count] = cursor.getString(1);
                Phonelist[count] = cursor.getString(2);
                Emaillist[count] = cursor.getString(3);

                count++;

            } while (cursor.moveToNext());

            adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Namelist);
            contact_list.setAdapter(adapter);
            contact_search.setOnQueryTextListener(this);

        } else {
        }


       // contact_list.setOnItemClickListener();

       contact_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ContactDetails.class);
                intent.putExtra("ID", IDlist[position]);
                intent.putExtra("Name", Namelist[position]);
                intent.putExtra("Phone", Phonelist[position]);
                intent.putExtra("Email", Emaillist[position]);
                startActivity(intent);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */

                Intent intent = new Intent(MainActivity.this, CreateContact.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutDev.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_exit) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit Confirmation")
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }

        if (id == R.id.action_Dial){
            Intent intent = new Intent(MainActivity.this,Dialar.class);
            startActivity(intent);

            return  true;
        }

        if (id == R.id.action_Msg){

            Intent intent = new Intent(MainActivity.this,Massenger.class);
            startActivity(intent);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit Confirmation")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.getFilter().filter(newText);
        return false;
    }
}
