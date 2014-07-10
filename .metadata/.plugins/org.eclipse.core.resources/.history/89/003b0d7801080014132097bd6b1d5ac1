
package JSON;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class FileManager {

	private static FileManager managerInstance;
	
	private FileManager() {
		
	}
	
	public static FileManager getInstance() {
		if (managerInstance == null) {
			managerInstance = new FileManager();
		}
		return managerInstance;
	}
	
	public Boolean writeStringFile(Context context, String filename, String content) {
		Boolean result = false;
		FileOutputStream fos = null;
		
		try {
			fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(content.getBytes());
			Log.i("Write to file", "working");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return result;
		
	}
}
