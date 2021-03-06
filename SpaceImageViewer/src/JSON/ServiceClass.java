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
package json;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
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
	public static final String MESSENGER_KEY = "messenger";
	Context context = this;
	
	public ServiceClass() {
		
	super("API Service");
		
	}

	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	//handle the service intent to get the data from the API and pass it to MainActivity
	protected void onHandleIntent(Intent intent) {
		
	String filename = "ImageFile.txt";	
	
	_apiURL = "http://www.astrobin.com/api/v1/image/?title__icontains=spiral&api_key=e5ca219df4572fd4f187f3e6c4192e24af7e78f8&api_secret=5d4bf7b7097eed09a878af19a475fb879a36b916&format=json";
	String content = getData(_apiURL);
	//Log.i("Service Class", content);
	
	Bundle extra = intent.getExtras();
	Messenger messenger = (Messenger) extra.get(MESSENGER_KEY);
	Message message = Message.obtain();
	message.arg1 = Activity.RESULT_OK;
	message.obj = filename;
	Log.i("Service Class", filename);
	
	FileManager fileManager = FileManager.getInstance();
	fileManager.writeStringFile(context, filename, content);
	
	
	try {
		messenger.send(message);
	} catch (RemoteException e) {
		Log.e("onHandleIntent error", e.getMessage().toString());
		e.printStackTrace();
	}
	
}
	//grab data from API URL and pass it into getResponse to handle the connection
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
	//connect to API as passed from getData() and grab the data, then return it back to getData()
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
			//Log.i(TAG, response);
		} catch (IOException e) {
			response = "There was an error getting the data";
			Log.e(TAG, "Unable to get data", e);
		}
		
		return response;
	}

}
