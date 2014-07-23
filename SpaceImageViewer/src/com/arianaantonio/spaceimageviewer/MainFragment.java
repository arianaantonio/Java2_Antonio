package com.arianaantonio.spaceimageviewer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
//import android.support.v4.app.Fragment;

public class MainFragment extends Fragment implements OnItemClickListener{

	private ArrayList<HashMap<String, String>> myData = new ArrayList<HashMap<String, String>>();
	private ListView listView;
	private Context context; 
	private ParentListener listener;
	public interface ParentListener {
		//void displayDetailedData(Intent intent, int position);
		void startActivityForResult(Intent intent, int position);
		void passBackClickedItem(HashMap<String, String> item);
	}
	
	public MainFragment() {
		//context = getActivity();
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
	}  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, container);
		if (savedInstanceState != null) {
			Log.i("Main Activity", "working in first saved");
			myData = (ArrayList<HashMap<String, String>>) savedInstanceState.getSerializable("saved"); 
			
			if (myData != null) {
				Log.i("Main Activity", "work in mydata not null");
				Log.i("Main Activity", "Saved Instance");
				SimpleAdapter adapter = new SimpleAdapter(context, myData, R.layout.advance_listview,
						new String[] {"title", "user", "imaging_cameras"}, new int[] {R.id.title, R.id.user, R.id.camera});
				listView = (ListView) view.findViewById(R.id.listView1);
				listView.setAdapter(adapter);
				Log.i("Main Activity", "working after my data not null"); 
				
			} else {
				Log.i("Main Activity", "Did not save");
			}
		} else {
			Log.i("Main Activity", "No saved instance");
		}
		listView = (ListView) view.findViewById(R.id.listView1);
		return view;
	 }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		HashMap<String, String> selectedListItem = myData.get(position);
		String usernameString = (String)selectedListItem.get("user");
		String cameraString = (String)selectedListItem.get("imaging_cameras");
		String titleString = (String)selectedListItem.get("title");
		String urlString = (String)selectedListItem.get("url");
		String hdString = (String)selectedListItem.get("hdImage");
		Log.i("Item Selected", "name: " +usernameString);
	} 
	public void displayData(ArrayList<HashMap<String, String>> data) {
		myData = data; 
		Log.i("My Data", myData.toString());   
		Log.i("Main Fragment", "working4"); 
		SimpleAdapter adapter = new SimpleAdapter(context, myData, R.layout.advance_listview, 
				new String[] {"title", "user", "imaging_cameras"}, new int[] {R.id.title, R.id.user, R.id.camera});
		Log.i("Main Fragment", "working5");
	
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, String> selectedListItem = myData.get(position);
				Log.i("Main Fragment Listview", "Selected" +selectedListItem);
				listener.passBackClickedItem(selectedListItem);
			}
			
		});
	}
	//protected void passBackClickedItem(HashMap<String, String> selectedListItem) {
		// TODO Auto-generated method stub
		
	//}
	
	


	/*
	//saving instance state of listView
		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			
			if (myData != null && !myData.isEmpty()) {
				outState.putSerializable("saved", (Serializable) myData);
				Log.i("Main Activity", "Saving instance state");
			}
		}*/
}
