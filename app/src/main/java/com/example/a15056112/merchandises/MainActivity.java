package com.example.a15056112.merchandises;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    ArrayList<Item> itemList = new ArrayList<Item>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onResume(){
        super.onResume();
        itemList.clear();
        // Check if there is network access
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            HttpRequest request = new HttpRequest("http://10.0.2.2/C302_P07/getItems.php");
            request.setMethod("GET");
            request.execute();
            try{
                String jsonString = request.getResponse();
                System.out.println(">>" + jsonString);

                JSONArray jsonArray = new JSONArray(jsonString);

                // Populate the arraylist personList
                for(int i=0 ; i < jsonArray.length(); i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);
                    Item item = new Item();
                    item.setId(jObj.getString("id"));
                    item.setItemName(jObj.getString("item_name"));
                    item.setQuantity(jObj.getString("quantity"));
                    item.setPrice(jObj.getString("price"));

                    itemList.add(item);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            ItemAdapter arrayAdapter = new ItemAdapter(this, R.layout.listview, itemList);
            listView = (ListView) findViewById(R.id.lvItem);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {

                    Item item = (Item) parent.getItemAtPosition(arg2);

                    intent = new Intent(getApplicationContext(), DisplayItemInfoActivity.class);
                    intent.putExtra("com.example.MAIN_MESSAGE", item.getId());
                    startActivity(intent);
                }
            });
        } else {
            // AlertBox
            showAlert();
        }
    }

    private void showAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No network connection!")
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = builder.create();

        // show it
        alertDialog.show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.menu_add) {
            intent = new Intent(getApplicationContext(), AddItemInfoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
