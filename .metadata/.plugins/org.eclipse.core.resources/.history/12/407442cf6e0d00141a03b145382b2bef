/*
 * project SpaceImageViewer
 * 
 * packager com.arianaantonio.spaceimageviewer
 * 
 * @author Ariana Antonio
 * 
 * date Jul 14, 2014
 * 
 */
package com.arianaantonio.spaceimageviewer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	RatingBar ratingBar;
	float rating;
	Button hdButton;
	TextView title;
	TextView user;
	TextView camera;
	Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_activity);
		
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		ratingBar.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				rating = ratingBar.getRating();
				//listener.setRating(rating);
				String ratingValue = String.valueOf(rating);
				Log.i("Favorite", ratingValue);
				
				return false;
			}
		});
		hdButton = (Button) findViewById(R.id.button1);
		hdButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri website = Uri.parse("http://www.google.com");
				Intent websiteIntent = new Intent(Intent.ACTION_VIEW, website);
				startActivity(websiteIntent);
				
			}
		});
		
	
	}
	
	@Override
	public void finish() {
		Log.i("Detail Activity", "Activity Finished");
		
		title = (TextView) findViewById(R.id.title);
		String titleString = title.getText().toString();
		
		Intent dataPassing = new Intent();
		dataPassing.putExtra("title", titleString);
		dataPassing.putExtra("rating", rating);
		
		setResult(RESULT_OK, dataPassing);
		super.finish();
	}
}
