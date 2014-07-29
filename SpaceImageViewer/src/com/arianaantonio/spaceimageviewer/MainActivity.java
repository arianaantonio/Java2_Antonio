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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import json.FileManager;
import json.ServiceClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.arianaantonio.networkconnection.NetworkConnect;



public class MainActivity extends Activity implements MainFragment.ParentListener, DetailFragment.ParentListener {
	
	//global variables
	Context mContext;
	FileManager mFile;
	String mFileName = "ImageFile.txt";
	public enum DialogType {SEARCH, PREFERENCES, FAVORITES};
	//public Spinner spinner;

	private static FileManager fileManager = FileManager.getInstance();
	final MyHandler handler = new MyHandler(this);
	ArrayList<HashMap<String, String>> myData = new ArrayList<HashMap<String, String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		mContext = this;
		mFile = FileManager.getInstance();
		//Spinner spinner;  
		/*
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, R.array.background_colors);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);*/
		
		
		
		//checking network connection from JAR
		NetworkConnect networkConnection = new NetworkConnect();
		Boolean networkConn = networkConnection.connectionStatus(mContext);
		if (networkConn) {
			Toast.makeText(mContext, "You are connected", Toast.LENGTH_LONG).show(); 
		} else {
			Toast.makeText(mContext, "You are NOT connected", Toast.LENGTH_LONG).show();
		}
			
		final MyHandler handler = new MyHandler(this);
		
		getData(handler);
		setContentView(R.layout.fragment_main);
	} 
	
	private static class MyHandler extends Handler {
		
		private final WeakReference<MainActivity> myActivity;
		
		public MyHandler(MainActivity activity) {
			myActivity = new WeakReference<MainActivity>(activity);
		}
		@Override
		public void handleMessage(Message message) {
			MainActivity activity = myActivity.get();
			if (activity !=null) {
				
				Object objectReturned = message.obj;
				String filename = objectReturned.toString();
				Log.i("Filename", filename);
				
				if (message.arg1 == RESULT_OK && objectReturned !=null) {
					
					Log.i("Main Activity", "Message handler");
					String fileContent = fileManager.readStringFile(activity, filename);
					Log.i("Main Activity", "File content: " +fileContent);
					
					
					Log.i("Main Activity", "working");
					try {
						//Log.i("Main Activity", "Handler working here");
						JSONObject json = new JSONObject(fileContent);
						Log.i("Main Activity", "Handler working here");
						JSONArray imagesArray = json.getJSONArray("objects");
						activity.displayData(imagesArray);
					} catch (JSONException e) {
						Log.e("JSON Parser", "Error parsing data [" + e.getMessage()+"] "+fileContent);
						e.printStackTrace();
					}
					
				}
			}
		}
	}
	//start intent service to get the API data, passing in the hander object
	public void getData(Handler handler) {
		Messenger messenger = new Messenger(handler);
		
		Intent getIntent = new Intent(this, ServiceClass.class);
		getIntent.putExtra("messenger", messenger);
		startService(getIntent);
	}
	/**grab data from txt file, add each object value to the display Hashmap
	* set the values to the Simple Adapter to display on the listview
	*/
	public void displayData(JSONArray jsonArray) {
		
		Log.i("Main Activity", "working1");
		for (int i = 0; i < jsonArray.length(); i++) {
			//HashMap<String, String> displayText = new HashMap<String, String>();
			Log.i("Main Activity", "working2");
			try {
				String url = jsonArray.getJSONObject(i).getString("url_regular");
				String title = jsonArray.getJSONObject(i).getString("title");
				String user = jsonArray.getJSONObject(i).getString("user");
				String camera = jsonArray.getJSONObject(i).getString("imaging_cameras");
				camera = camera.replace("[", "");
				camera = camera.replace("]", "");
				String hd = jsonArray.getJSONObject(i).getString("url_hd");
				
				Log.i("Returned objects", title+ " " +user+" " +camera+ " " +url);
				
				HashMap<String, String> displayText = new HashMap<String, String>();
				//ArrayList<HashMap<String, String>> myData = new ArrayList<HashMap<String, String>>();
				Log.i("Main Activity", "working 2.5");
				displayText.put("title", title);
				displayText.put("user", user);
				displayText.put("imaging_cameras", camera);
				displayText.put("url", url);
				displayText.put("hdImage", hd);
				Log.i("Main Activity", "working3");
				myData.add(displayText);
				
			} catch (JSONException e) {
				Log.e("Error displaying data in listview", e.getMessage().toString());
				e.printStackTrace();
			}
				
		}
		FragmentManager manager = getFragmentManager();
		MainFragment fragment = (MainFragment) manager.findFragmentById(R.id.main_fragment);
		if (fragment !=null) {
			fragment.displayData(myData);
		} else {
			fragment = new MainFragment();
			fragment.displayData(myData); 
		}
	}

	//when detail activity finishes and passes back data, receive data and run dialog
	protected void onActivityResult(int requestCode, int resultCode, Intent dataPassing) {
		Log.i("Main Activity", "Pulling passed data");
		
		if (resultCode == RESULT_OK && requestCode == 0) {
			if (dataPassing.hasExtra("rating") && dataPassing.hasExtra("title")) {
				Float rating = dataPassing.getExtras().getFloat("rating");
				String title = dataPassing.getExtras().getString("title");
				Log.i("Activity Result", "Rating: " +rating+ "Title: " +title);
				AlertDialog.Builder ratingDialog = new AlertDialog.Builder(this);
				
				if (rating.toString() == "0.0") {
					ratingDialog.setTitle(title).setMessage("You did not rate this as a favorite")
					.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						
						}
					});
				} else {
					ratingDialog.setTitle(title).setMessage("You rated this as a favorite")
					.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						
						}
					});
				}
				ratingDialog.create();
				ratingDialog.show();
			}
		}
	}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar, menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.search:
			Log.i("Main Activity", "Selected 'Search'");
			launchDialogFragment(DialogType.SEARCH);    
			break;
		case R.id.preferences:
			Log.i("Main Activity", "Selected 'Preferences'");
			//Spinner spinner = (Spinner) new Spinner(mContext);
			//Spinner arrayForSpinner = new ArrayList<String>();
			//arrayForSpinner = R.array.background_colors;
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, R.array.background_colors);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			Spinner spinner = (Spinner) findViewById(R.id.spinner1);
			spinner.setAdapter(adapter);
			
			launchDialogFragment(DialogType.PREFERENCES);
			break;
		case R.id.favorites:
			Log.i("Main Activity", "Selected 'Favorites'");
			
			break;
		}
		return true;
	}
	public void launchDialogFragment(DialogType type) {
		AlertDialogFragment dialogFragment = AlertDialogFragment.newInstance(type);
		dialogFragment.show(getFragmentManager(), "search_dialog");
	}
	//portrait mode: data of selected listview item sent back from the main fragment and passed to detail fragment
	public void passToDetail(HashMap<String, String> item) {
		Log.i("Main Activty passed", "Selected" +item);
		Intent detailActivity = new Intent(getBaseContext(), DetailsActivity.class);
		
		Bundle bundle = new Bundle();
		 bundle.putSerializable("details", item);
		 detailActivity.putExtras(bundle);
		 startActivityForResult(detailActivity, 0);
		 Log.i("passToDetail", "working");
	}
	//landscape mode: data of selected listview item sent back from the main fragment and passed to detail fragment
	@Override
	public void passBackClickedItem(HashMap<String, String> item) {
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("clicked data", item);
		FragmentManager manager = getFragmentManager();
		DetailFragment fragment = (DetailFragment) manager.findFragmentById(R.id.detailfragment);
		if (fragment != null && fragment.isInLayout()) {
			fragment.displayDetails(bundle);
			Log.i("Main Activity", "Inside fragment isn't null");
			
		} else {
			passToDetail(item);
			Log.i("Main Activity", "pass to Detail");
		}
		
	}
	public static class AlertDialogFragment extends DialogFragment {
		public static DialogType type;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			LayoutInflater inflater = getActivity().getLayoutInflater();
			switch(type) {
			case PREFERENCES: 
				builder.setView(inflater.inflate(R.layout.preferences_dialog, null))
					.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							
						}
					})
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							AlertDialogFragment.this.getDialog().cancel();
							
						}
					});
				break;
			case SEARCH:
				builder.setView(inflater.inflate(R.layout.search_dialog, null))
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						AlertDialogFragment.this.getDialog().cancel();
						
					}
				});
				
				break;
	
			}
			return builder.create();
		}
		public static AlertDialogFragment newInstance(DialogType dialogType) {
			type = dialogType;
			return new AlertDialogFragment();
		}
		
	}

	/*
	@Override
	public void passRatingInfo(Intent dataPassing) {
		Log.i("Main Activity", "inside passRatingInfo");
		
		int requestCode = 0;
		int resultCode = RESULT_OK;
		Log.i("Main Activity", "inside passRatingInfo");
		if (dataPassing != null) {
			onActivityResult(requestCode, resultCode, dataPassing); 
		}
		
	}*/
}
