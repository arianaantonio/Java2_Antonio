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

import com.loopj.android.image.SmartImageView;

public class DetailsActivity extends Activity {
	/*
	RatingBar ratingBar;
	float rating;
	Button hdButton;
	TextView titleView;
	TextView userView;
	TextView cameraView;
	String hdString;*/
	//Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_detail);
		/*
		titleView = (TextView) findViewById(R.id.title);
		userView = (TextView) findViewById(R.id.userName);
		cameraView = (TextView) findViewById(R.id.cameraType);
		
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
		});*/
/*
		Bundle data = getIntent().getExtras();
		
		if(data != null){
			Log.i("Detail Activity", "working5");
			String userString = data.getString("user");
			String urlString = data.getString("url");
			String cameraString = data.getString("camera");
			String titleString = data.getString("title");
			hdString = data.getString("hdImage");
			Log.i("Detail Activity", "Inside Data");
			
			displayDetails(userString, titleString, urlString, cameraString, hdString);
		}
		
		hdButton = (Button) findViewById(R.id.button1);
		hdButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri website = Uri.parse(hdString);
				Intent websiteIntent = new Intent(Intent.ACTION_VIEW, website);
				startActivity(websiteIntent);
				
			}
		});*/
}
	/*
	public void displayDetails(String username, String title, String url, String camera, String hdUrl) {
		final SmartImageView imageView = (SmartImageView) findViewById(R.id.my_image);
		imageView.setImageUrl(url);
		Log.i("Detail Activity", "Brought over: " +username+ " " +url+ " " +title+ " " +camera);
		titleView.setText(title);
		userView.setText(username);
		cameraView.setText(camera);
	}*/
	//when detail activity finishes, pass back the image title and rating value
	@Override
	public void finish() {
		Log.i("Detail Activity", "Activity Finished");
		
		TextView title = (TextView) findViewById(R.id.title);
		String titleString = title.getText().toString();
		
		Intent dataPassing = new Intent();
		dataPassing.putExtra("title", titleString);
		//dataPassing.putExtra("rating", rating);
		
		setResult(RESULT_OK, dataPassing);
		super.finish();
	}
}
