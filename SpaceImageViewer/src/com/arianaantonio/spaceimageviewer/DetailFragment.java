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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
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
	private RatingBar ratingBar;
	float rating;
	private Button hdButton;
	private TextView titleView;
	private TextView userView;
	private TextView cameraView;
	private String hdUrl;

	HashMap<String, String> data;
	private SmartImageView imageView;
	View view;   
	

	private Context context; 
	private ParentListener listener;
	public interface ParentListener {
		 //void passRatingInfo(Intent dataPassing);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (ParentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + "class does not implement fragment interface");
		}
	}
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
		
		
		if (savedInstanceState != null) {
			Log.i("Main Activity", "working in first saved");
			if (data != null) {
				Log.i("Main Activity", "working after my data not null"); 
				Bundle data2 = getActivity().getIntent().getExtras();
				Log.i("Detail Activity", "Create bundle: " +data2);
				  if(data != null){
				   Log.i("Detail Activity", "working5");
				  
				   //displayDetails(userString, titleString, urlString, cameraString,hdString);
				   displayDetails(data2);
				 }
			} else {
				Log.i("Main Activity", "Did not save");
			}
		} else {
			Log.i("Main Activity", "No saved instance");
		}
		
	} 
	   
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("Detail Activity", "working5");
		view = inflater.inflate(R.layout.details_activity, container);
	
		titleView = (TextView) view.findViewById(R.id.title);
		//titleView.setText("Test title");
		userView = (TextView) view.findViewById(R.id.userName);
		cameraView = (TextView) view.findViewById(R.id.cameraType);
		hdButton = (Button) view.findViewById(R.id.button1);
		imageView = (SmartImageView) view.findViewById(R.id.my_image);
		ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
		//ratingBar.setRating(0);  
		
		ratingBar.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				rating = ratingBar.getRating();
				String ratingValue = String.valueOf(rating);
				
				String title = titleView.getText().toString();
				Log.i("Favorite", ratingValue + " " +title);
				if (ratingValue == "0.0") {
					
				} else {
				BufferedWriter writer = null;
				 //saving title to file if favorite is selected
				try {
					String filePath = context.getFilesDir().getPath().toString() + "/FavoritesFile.txt";
					File file = new File(filePath);
					System.out.println(file.getCanonicalPath());
					//file.delete();
					writer = new BufferedWriter(new FileWriter(file, true));
					String lineTitle = title+ "\n";
		            writer.write(lineTitle);
		            
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

	            
				}
				
				return false;
			}
		});
		//passRating();
		 
		Bundle data2 = getActivity().getIntent().getExtras();
		Log.i("Detail Activity", "Create bundle: " +data2);
		  if(data2 != null){
		   Log.i("Detail Activity", "working5");
		  
		   //displayDetails(userString, titleString, urlString, cameraString,hdString);
		   displayDetails(data2);
		 }
		return view;
}

	//data is passed from detail activity and displayed to the detail fragment
	public void displayDetails(Bundle bundle) {
		 Log.i("Detail Fragment", "Bundle: " + bundle);
		
		  @SuppressWarnings("unchecked")
		 HashMap<String, String> data = (HashMap<String, String>)bundle.getSerializable("details");
		  Log.i("Detail Fragment", "data " +data);
		
		  if (titleView == null) {
			  Log.i("Detail Fragment", "titleView is null");
		  } else {//if data is null, phone is in  landscape and data is in a differently named bundle
			  if (data == null) {
				  @SuppressWarnings("unchecked")
				  HashMap<String, String> data1 = (HashMap<String, String>)bundle.getSerializable("clicked data");
				  Log.i("Detail Fragment", "data is null");
				  titleView.setText(data1.get("title")); 
				  userView.setText(data1.get("user"));
				  cameraView.setText(data1.get("imaging_cameras")); 
				  imageView.setImageUrl(data1.get("url")); 
				  hdUrl = data1.get("hdImage");
				  hdButton.setOnClickListener(new OnClickListener() {
						
						 @Override
						 public void onClick(View v) {
									Uri website = Uri.parse(hdUrl);
									Intent websiteIntent = new Intent(Intent.ACTION_VIEW, website);
									startActivity(websiteIntent);
									
								}
							});
				  } else {
				  titleView.setText(data.get("title")); 
				  userView.setText(data.get("user"));
				  cameraView.setText(data.get("imaging_cameras")); 
				  imageView.setImageUrl(data.get("url")); 
				  hdUrl = data.get("hdImage");
				  //hdUrl.setText(data.get("hdUrl"));
				  hdButton.setOnClickListener(new OnClickListener() {
						
				 @Override
				 public void onClick(View v) {
							Uri website = Uri.parse(hdUrl);
							Intent websiteIntent = new Intent(Intent.ACTION_VIEW, website);
							startActivity(websiteIntent);
							
						}
					});
				 }
		  }
		  Log.i("Detail Fragment", "working 7");
		
	}  
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		if (data != null && !data.isEmpty()) {
			outState.putSerializable("saved", (Serializable) data);
			Log.i("Main Activity", "Saving instance state");
		}
	}
}
