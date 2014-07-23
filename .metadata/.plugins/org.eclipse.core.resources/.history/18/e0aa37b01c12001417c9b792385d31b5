package com.arianaantonio.spaceimageviewer;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

public class DetailFragment extends Fragment {
	RatingBar ratingBar;
	float rating;
	Button hdButton;
	TextView titleView;
	TextView userView;
	TextView cameraView;
	String hdString;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.details_activity, container);
		
		titleView = (TextView) view.findViewById(R.id.title);
		userView = (TextView) view.findViewById(R.id.userName);
		cameraView = (TextView) view.findViewById(R.id.cameraType);
		
		ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
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
		
		hdButton = (Button) view.findViewById(R.id.button1);
		hdButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri website = Uri.parse(hdString);
				Intent websiteIntent = new Intent(Intent.ACTION_VIEW, website);
				startActivity(websiteIntent);
				
			}
		});*/
		return view;
}

	
	
public void displayDetails(String username, String title, String url, String camera, String hdUrl) {
	//final SmartImageView imageView = (SmartImageView) view.findViewById(R.id.my_image);
	//imageView.setImageUrl(url);
	Log.i("Detail Activity", "Brought over: " +username+ " " +url+ " " +title+ " " +camera);
	titleView.setText(title);
	userView.setText(username);
	cameraView.setText(camera);
}

}
