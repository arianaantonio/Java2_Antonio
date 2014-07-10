/*
 * project SpaceImageViewer
 * 
 * packager com.arianaantonio.spaceimageviewer
 * 
 * @author Ariana Antonio
 * 
 * date Jul 10, 2014
 * 
 */
package com.arianaantonio.spaceimageviewer;

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
import android.widget.Toast;

import com.arianaantonio.networkconnection.NetworkConnect;
import com.loopj.android.image.SmartImageView;
//import com.arianaantonio.networkjar.networkConnection;


public class MainActivity extends Activity {
	
	//global variables
	
	Context mContext;
	public static ArrayList<String> arrayForGridView = new ArrayList<String>();
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
		
				//empty listview so it doesn't append data
				arrayForGridView.removeAll(arrayForGridView);
			
		
				
				//try {
					
					//creating listview
					ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, arrayForGridView);
					ListView listView = (ListView) findViewById(R.id.listView1);
					listView.setAdapter(listAdapter);
					
					//check for network when trying to pull data
					if (networkConn) {
						//grab data from type selected and set to listview array
						
					
						final ArrayList<String> arrayForImageURL = new ArrayList<String>();
						/*
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
				*/
				}
				
		
			} 
			 

			
			 
	
		
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
