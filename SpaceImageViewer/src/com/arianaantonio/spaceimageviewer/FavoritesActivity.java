/*
 * project SpaceImageViewer
 * 
 * packager com.arianaantonio.spaceimageviewer
 * 
 * @author Ariana Antonio
 * 
 * date Jul 29, 2014
 * 
 */
package com.arianaantonio.spaceimageviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class FavoritesActivity extends Activity {

	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorites_view);
		
		TextView favoritesView = (TextView) findViewById(R.id.favoritesTextView);
		//favoritesView.setText("Testing"); 
		
		try {
			//reading saved file of favorites and setting to textView
		    BufferedReader reader = new BufferedReader(new InputStreamReader(
		
		            openFileInput("FavoritesFile.txt")));
		    String string;
		    StringBuffer stringBuffer = new StringBuffer();                
		    while ((string = reader.readLine()) != null) {
		    	stringBuffer.append(string + "\n");
		    }
		    favoritesView.setText(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
