package JSON;

//Ariana Antonio
//Java 1, Week 1, Full Sail University, MDVBS
//06/10/2014


import imageType.ImageType;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;



public class buildJSON {

	static String TAG = "JSON DATA - JSON BUILDER";
	public String responseStr;
	public static String _apiURL;
	//= "http://www.astrobin.com/api/v1/image/?title__icontains=andromeda&api_key=e5ca219df4572fd4f187f3e6c4192e24af7e78f8&api_secret=5d4bf7b7097eed09a878af19a475fb879a36b916&format=json";
	
	public JSONArray buildsJSONImages(Context context, String typeID) {
		//initial object
		//JSONObject imagesObject = new JSONObject();
		JSONArray imagesArray = new JSONArray();
		
		//for exceptions
		try {
		//api URL with typeID placed based on what user selected
		_apiURL = "http://www.astrobin.com/api/v1/image/?title__icontains=" + typeID + "&api_key=e5ca219df4572fd4f187f3e6c4192e24af7e78f8&api_secret=5d4bf7b7097eed09a878af19a475fb879a36b916&format=json";
		
		//call getData method with api URL
		getData imageData = new getData();
		responseStr = imageData.execute(_apiURL).get();
		Log.i(TAG, responseStr);
		//set array in object
		//imagesObject.put("images", imagesArray);
		JSONObject json = new JSONObject(responseStr);
		//JSONArray images = new JSONArray();
		imagesArray = json.getJSONArray("objects");
		//imagesArray = new JSONArray(responseStr);
		
		} catch (JSONException e) {
			Log.e(TAG, "JSON Error", e);
			e.printStackTrace();
		} catch(ExecutionException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		return imagesArray;
		//return images;
	}
	
	public static JSONObject buildsJSONTypes() {
		//initial object
		JSONObject typesObject = new JSONObject();
		//exceptions
		try {
			//array to hold objects
			JSONArray typesArray = new JSONArray();
			
			for (ImageType type : ImageType.values()) {
				JSONObject typeObject = new JSONObject();
				//set enum values
				typeObject.put("Type", type.getTypeTitle());
				typeObject.put("ID", type.getTypeID());
				
				typesArray.put(typeObject);
			}
			//add values to object
			typesObject.put("types", typesArray);
			
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return typesObject;
	}
	public static String getResponse(URL url) {
		String response = "";
		try {
			
			//open connection to URL
			URLConnection connect = url.openConnection();
			BufferedInputStream bufferStream = new BufferedInputStream(connect.getInputStream());
			byte[] byteContext = new byte[1024];
			int readByte = 0;
			StringBuffer responseBuffer = new StringBuffer();
			while ((readByte = bufferStream.read(byteContext)) != -1) {
				response = new String(byteContext, 0 ,readByte);
				responseBuffer.append(response);
			}
			response = responseBuffer.toString();
			Log.i(TAG, response);
		} catch (IOException e) {
			response = "There was an error getting the data";
			Log.e(TAG, "Unable to get data", e);
		}
		
		return response;
	}
	public class getData extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			responseStr = "";
			try {
				//pass api URL into getResponse function
				URL url = new URL(_apiURL);
				responseStr = getResponse(url);
			} catch (MalformedURLException e) {
				responseStr = "There was an error getting data in getData";
				Log.e(TAG, "Error in getData", e);
			}
			Log.v(responseStr, "Response");
			return responseStr;
			
		}
		@Override
		protected void onPostExecute(String result) {
			//execute and return result
			super.onPostExecute(result);
		}
		
		
	}
}
