package com.arianaantonio.spaceimageviewer;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class MainFragment extends Fragment {

	private ArrayList<HashMap<String, String>> myData = new ArrayList<HashMap<String, String>>();
	private ListView listView;
	private Context context; 
	private ParentListener listener;
	public interface ParentListener {
		
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
		super.onCreate(savedInstanceState);
		context = getActivity();  
	}  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, container);
		listView = (ListView) view.findViewById(R.id.listView1);
		return view;
	 }
	//get api data passed from main activity and display to listview
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
				HashMap<String, String> selectedListItem = myData.get(position);
				Log.i("Main Fragment Listview", "Selected" +selectedListItem);
				listener.passBackClickedItem(selectedListItem);
			}
			
		});
	}
}
