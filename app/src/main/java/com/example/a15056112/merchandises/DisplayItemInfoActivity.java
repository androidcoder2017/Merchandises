package com.example.a15056112.merchandises;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;


@SuppressLint("NewApi")
public class DisplayItemInfoActivity extends Activity {

	private String userId;
	private EditText etItemName, etQuantity, etPrice;
	private Button btnEdit, btnDelete;


	protected void onStart(){
		super.onStart();
		etItemName = (EditText)findViewById(R.id.editTextItemName);
		etQuantity = (EditText)findViewById(R.id.editTextItemQuantity);
		etPrice = (EditText)findViewById(R.id.editTextItemPrice);
		btnDelete = (Button)findViewById(R.id.buttonDelete);
		btnEdit = (Button) findViewById(R.id.buttonEdit);

		Intent intent = getIntent();
		userId = intent.getStringExtra("com.example.MAIN_MESSAGE");
		HttpRequest request= new HttpRequest("http://10.0.2.2/C302_P07/getItemById.php?Id=" + userId);
		request.setMethod("GET");
		request.execute();
		try{
			String jsonString = request.getResponse();
			JSONObject jsonObj = new JSONObject(jsonString);

			etItemName.setText(jsonObj.get("item_name").toString());
			etQuantity.setText(jsonObj.get("quantity").toString());
			etPrice.setText(jsonObj.get("price").toString());

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDetailsButtonClicked(View view){
		
		etItemName = (EditText)findViewById(R.id.editTextItemName);
		etQuantity = (EditText)findViewById(R.id.editTextItemQuantity);
		etPrice = (EditText)findViewById(R.id.editTextItemPrice);

		
		//TODO 03: Send the HttpRequest to updateContact.php
		Toast.makeText(DisplayItemInfoActivity.this, "Perform TODO task 3", Toast.LENGTH_SHORT).show();

		String url = "http://10.0.2.2/C302_P07/updateItemById.php";

		HttpRequest request = new HttpRequest(url);
		request.setMethod("POST");

		request.addData("id", userId);
		request.addData("firstname", etItemName.getText().toString());
		request.addData("lastname", etQuantity.getText().toString());
		request.addData("home", etPrice.getText().toString());

		request.execute();

		try{
			finish();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteRecordButtonClicked(View view){

		String url = "http://10.0.2.2/C302_P07/deleteItem.php";

		HttpRequest request = new HttpRequest(url);
		request.setMethod("POST");

		request.addData("id", userId);

		request.execute();

		try{

			finish();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}