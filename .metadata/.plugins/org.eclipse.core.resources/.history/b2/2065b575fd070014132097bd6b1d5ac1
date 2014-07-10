package com.arianaantonio.spaceimageviewer;

//Ariana Antonio
//Java 1, Week 1, Full Sail University, MDVBS
//06/10/2014



//import imageType.ImageAdapter;
import imageType.ImageType;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;


import JSON.buildJSON;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.arianaantonio.networkconnection.NetworkConnect;
import com.loopj.android.image.SmartImageView;
//import com.arianaantonio.networkjar.networkConnection;


public class MainActivity extends Activity {
	
	//global variables
	
	Context mContext;
	ArrayList<String> arrayForSpinner = new ArrayList<String>();
	public static ArrayList<String> arrayForGridView = new ArrayList<String>();
	ArrayList<String> arrayForTypeId = new ArrayList<String>();
	buildJSON imageJSON = new buildJSON();
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = this;
		//final buildJSON imageJSON = new buildJSON();
		setContentView(R.layout.activity_main);
		
		//checking network connection from JAR
		NetworkConnect networkConnection = new NetworkConnect();
		Boolean networkConn = networkConnection.connectionStatus(mContext);
		if (networkConn) {
			Toast.makeText(mContext, "You are connected", Toast.LENGTH_LONG).show(); 
		} else {
			Toast.makeText(mContext, "You are NOT connected", Toast.LENGTH_LONG).show();
		}
		
		//getting JSON titles
		for (ImageType types : ImageType.values()) {
			arrayForSpinner.add(types.getTypeTitle());
			arrayForTypeId.add(types.getTypeID());
		}
		
		//creating spinner
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, arrayForSpinner);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner spinnerView = (Spinner) findViewById(R.id.spinner1);
		spinnerView.setAdapter(spinnerAdapter);
		
		
		//on click for spinner
		spinnerView.setOnItemSelectedListener(new OnItemSelectedListener() {

			
				
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//get position of selected item
				final String typeSelected = arrayForTypeId.get(position);
				Log.v(typeSelected, "What you clicked: " + typeSelected);
				
				//empty listview so it doesn't append data
				arrayForGridView.removeAll(arrayForGridView);
			
		
				
				try {
					//setup network connection from JAR
					NetworkConnect networkConnection = new NetworkConnect();
					Boolean networkConn = networkConnection.connectionStatus(mContext);
					
					//creating listview
					ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, arrayForGridView);
					ListView listView = (ListView) findViewById(R.id.listView1);
					listView.setAdapter(listAdapter);
					
					//check for network when trying to pull data
					if (networkConn) {
						//grab data from type selected and set to listview array
						
						JSONArray imageTypeArray = imageJSON.buildsJSONImages(mContext, typeSelected);
						final ArrayList<String> arrayForImageURL = new ArrayList<String>();
						//Log.v(typeSelected, "Image array:" + imageTypeArray.length());
						for (int i = 0; i < imageTypeArray.length(); i++) {
							
							//Log.v(typeSelected, "Image array test:" + imageTypeArray.getJSONObject(i).getString("url_thumb"));
							String urlString = imageTypeArray.getJSONObject(i).getString("url_regular");
							String titleString = imageTypeArray.getJSONObject(i).getString("title");
							arrayForImageURL.add(urlString);
							arrayForGridView.add(titleString);
							
						}
							//onclick for listview
							listView.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {
									
									//grab item selected
									String imageSelected = arrayForGridView.get(position);
									
									//pull corresponding URl
									String pullImage = arrayForImageURL.get(position);
									Log.v(pullImage, "Image pulled: " + pullImage);
									
									//set smartimageview to URL
									final SmartImageView imageView = (SmartImageView) findViewById(R.id.my_image);
									imageView.setImageUrl(pullImage);
									Log.v(imageSelected, "Image Selected:" + imageSelected);
								
									
								}

							});
					
					} else {
						//if no network connection
						arrayForGridView.removeAll(arrayForGridView);
						listAdapter.notifyDataSetChanged();
						Toast.makeText(mContext, "You are NOT connected", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				
				}
		
			} 
			 

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			 
		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
