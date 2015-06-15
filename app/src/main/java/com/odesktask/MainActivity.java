package com.odesktask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private RelativeLayout Origin_city_layout;
    private ListView Origin_city_listview;
    private TextView Origin_city_label;
    private EditText Origin_city_edit;
    private TextView Origin_city_label_first;

    private RelativeLayout Destination_city_layout;
    private ListView Destination_city_listview;
    private TextView Destination_city_label;
    private EditText Destination_city_edit;
    private TextView Destination_city_label_first;

    private List<String> citylist;
    private List<String> search_citylist;
    private AITextAdapter ai_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Origin_city_layout = (RelativeLayout)findViewById(R.id.origin_city_list_layout);
        Origin_city_layout.setVisibility(View.GONE);

        Origin_city_listview = (ListView)findViewById(R.id.listview_origin);
        Origin_city_listview.setVisibility(View.GONE);

        Origin_city_label = (TextView)findViewById(R.id.origin_city_label);
        Origin_city_edit = (EditText)findViewById(R.id.origin_city_edit);
        Origin_city_label_first = (TextView)findViewById(R.id.origin_city_label_first);

        Destination_city_layout = (RelativeLayout)findViewById(R.id.destination_city_list_layout);
        Destination_city_layout.setVisibility(View.GONE);

        Destination_city_listview = (ListView)findViewById(R.id.listview_destination);
        Destination_city_listview.setVisibility(View.GONE);

        Destination_city_label = (TextView)findViewById(R.id.destination_city_label);
        Destination_city_edit = (EditText)findViewById(R.id.destination_city_edit);
        Destination_city_label_first = (TextView)findViewById(R.id.destination_city_label_first);

        citylist = new ArrayList<String>();

        citylist.add("New York");
        citylist.add("New Hapshire");
        citylist.add("New Haven");
        citylist.add("New Deli");
        citylist.add("Boston");
        citylist.add("New Haven");
        citylist.add("aNew Deli");
        citylist.add("Boston");
        citylist.add("New Haven");
        citylist.add("New Deli");
        citylist.add("Boston");

        search_citylist = new ArrayList<String>();
        search_citylist = citylist;
        ai_adapter = new AITextAdapter(this, citylist);

        Origin_city_edit.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

                search_citylist = new ArrayList<String>();
                for(int i=0; i<citylist.size(); i++){
                    if(citylist.get(i).toLowerCase().indexOf(String.valueOf(s).toLowerCase())>-1){
                        search_citylist.add(citylist.get(i));
                    }
                }

                ai_adapter = new AITextAdapter(getBaseContext(), search_citylist);
                Origin_city_listview.setAdapter(ai_adapter);
            }

        });

        Destination_city_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search_citylist = new ArrayList<String>();
                for(int i=0; i<citylist.size(); i++){
                    if(citylist.get(i).toLowerCase().indexOf(String.valueOf(s).toLowerCase())>-1){
                        search_citylist.add(citylist.get(i));
                    }
                }

                ai_adapter = new AITextAdapter(getBaseContext(), search_citylist);
                Destination_city_listview.setAdapter(ai_adapter);
            }
        });
    }

    public void onLabel(View v){
        Origin_city_label.setVisibility(View.VISIBLE);
        Origin_city_edit.setVisibility(View.VISIBLE);
        Origin_city_layout.setVisibility(View.VISIBLE);
        Origin_city_listview.setVisibility(View.VISIBLE);
        Origin_city_label_first.setVisibility(View.GONE);
        Origin_city_edit.setText("");
        Origin_city_edit.requestFocus();
    }

    public void onCloseLable(View v){
        Origin_city_label.setVisibility(View.GONE);
        Origin_city_edit.setVisibility(View.GONE);
        Origin_city_layout.setVisibility(View.GONE);
        Origin_city_listview.setVisibility(View.GONE);
        Origin_city_label_first.setVisibility(View.VISIBLE);
    }

    public void onDestinationLabel(View v){
        Destination_city_label.setVisibility(View.VISIBLE);
        Destination_city_edit.setVisibility(View.VISIBLE);
        Destination_city_layout.setVisibility(View.VISIBLE);
        Destination_city_listview.setVisibility(View.VISIBLE);
        Destination_city_label_first.setVisibility(View.GONE);
        Destination_city_edit.setText("");
        Destination_city_edit.requestFocus();
    }

    public void onDestinationCloseLabel(View v){
        Destination_city_label.setVisibility(View.GONE);
        Destination_city_edit.setVisibility(View.GONE);
        Destination_city_layout.setVisibility(View.GONE);
        Destination_city_listview.setVisibility(View.GONE);
        Destination_city_label_first.setVisibility(View.VISIBLE);
    }

    public void onCalanderShow(View v){
        Intent intent = new Intent(MainActivity.this, MyCalendarActivity.class);
        startActivity(intent);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
