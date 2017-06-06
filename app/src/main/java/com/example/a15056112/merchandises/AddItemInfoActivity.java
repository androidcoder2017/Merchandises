package com.example.a15056112.merchandises;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AddItemInfoActivity extends Activity {

	private EditText etItemNameAdd, etPriceAdd, etQuantityAdd;
	private Button btnAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item_info);

		etItemNameAdd = (EditText)findViewById(R.id.editTextItemNameAdd);
		etQuantityAdd = (EditText)findViewById(R.id.editTextItemQuantityAdd);
		etPriceAdd = (EditText)findViewById(R.id.editTextItemPriceAdd);
		btnAdd = (Button) findViewById(R.id.buttonAdd);
	}

	public void addNewRecordButtonClicked(View view){
		etItemNameAdd = (EditText)findViewById(R.id.editTextItemNameAdd);
		etQuantityAdd = (EditText)findViewById(R.id.editTextItemQuantityAdd);
		etPriceAdd = (EditText)findViewById(R.id.editTextItemPriceAdd);

		String url = "http://10.0.2.2/C302_P07/addItem.php";

		HttpRequest request = new HttpRequest(url);
		request.setMethod("POST");

		request.addData("item_name", etItemNameAdd.getText().toString());
		request.addData("quantity", etQuantityAdd.getText().toString());
		request.addData("price", etPriceAdd.getText().toString());
		request.execute();
		try{
			finish();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
