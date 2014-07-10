/*
 * project SpaceImageViewer
 * 
 * packager JSON
 * 
 * @author Ariana Antonio
 * 
 * date Jul 10, 2014
 * 
 */
package JSON;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceClass. This class grabs the API data through an intentService so that it 
 * can be stored.
 */


public class ServiceClass extends IntentService {

	/**
	 * Instantiates a new service class.
	 *
	 * @param name the name
	 */
	static String TAG = "IMAGE INTENT SERVICE";
	public static String responseStr;
	public static String _apiURL;
	
	public ServiceClass() {
		
	super("API Service");
		
	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		
		
	}
	public static String getData(String URL) {
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

}
