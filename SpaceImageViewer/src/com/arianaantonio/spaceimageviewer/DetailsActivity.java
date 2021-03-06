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
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;





public class DetailsActivity extends Activity implements DetailFragment.ParentListener {
	
	//Float rating;
	//String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//check if phone is in landscape, finish if it is as Main Activity will handle data passing
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
		
		Bundle data = getIntent().getExtras();
		
		if(data != null){
			//passing data to detail fragment
			FragmentManager manager = getFragmentManager();
			DetailFragment fragment = (DetailFragment) manager.findFragmentById(R.id.detailfragment);
			if (fragment !=null) {
				fragment.displayDetails(data);
				
			} else {
				fragment = new DetailFragment();
				fragment.displayDetails(data); 
				
			}
		}
		
		setContentView(R.layout.fragment_detail);
}
	//when detail activity finishes, pass back the image title and rating value
	@Override
	public void finish() {
		Log.i("Detail Activity", "Activity Finished");
		/*
		//TextView title = (TextView) findViewById(R.id.title);
		//String titleString = title.getText().toString();
		Bundle extras = getIntent().getExtras();
		String title = extras.getString("title");
		Float rating = extras.getFloat("rating");
		Log.i("Inside Finish", "Rating: " +rating);
		Intent dataPassing = new Intent();
		dataPassing.putExtra("title", title);
		dataPassing.putExtra("rating", rating);
		
		setResult(RESULT_OK, dataPassing);*/
		super.finish();
	}
	/*
	@Override
	public void passRatingInfo(Intent dataPassing) {
		Float rating = dataPassing.getExtras().getFloat("rating");
		String title = dataPassing.getExtras().getString("title");
		Log.i("passRatingInfo", "Rating: " +rating+ " Title: " +title);
		Log.i("Detail Activity", "inside passRatingInfo");
		getIntent().putExtra("title", title);
		getIntent().putExtra("rating", rating);
		
	}*/
}
