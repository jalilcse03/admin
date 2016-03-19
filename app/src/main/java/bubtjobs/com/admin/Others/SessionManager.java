package bubtjobs.com.admin.Others;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import bubtjobs.com.admin.Activity.Login;

public class SessionManager {
	//shared preference

	private SharedPreferences perf;
	//Editor for sharedPreference
	private Editor editor;
	//Context
	private Context _context;
	//shared pref mode
	private int PRIVATE_MODE=0;
	//sharedpref file name
	private static final String PREF_NAME="bitm_login";
	//all shared preference key
	private static final String IS_LOGIN="IsLoggedIn";
	// User pass (make variable public to access from outside)
	public static final String KEY_ID="id";

	// exam button
	public static final String BUTTON_TEXT="exambutton";

	// result publish
	public static final String RESULT_PUBLISH="result_publish";

	// exam duration
	public static final String EXAM_DURATION="exam_duration";

	//constructor
	public SessionManager(Context _context) {
		this._context = _context;
		perf=_context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor=perf.edit();
	}
	
	
	// create login session
	public void createLoginSession(String id){
		
		//store login value is true
		editor.putBoolean(IS_LOGIN, true);
		//store pass value in pref
		editor.putString(KEY_ID,id);
		//commit changes
		editor.commit();
		
	}
	
	//get store session date
	public String getUserId(){

		return perf.getString(KEY_ID, null);
	}

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.remove("KEY_ID");
        editor.commit();
         
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         
        // Staring Login Activity
        _context.startActivity(i);
    }

	// button
	public String getbuttonText(){
		return perf.getString(BUTTON_TEXT," ");
	}
	public void buttonTextChage(String s){
		editor.putString(BUTTON_TEXT,s);
		editor.commit();
	}
    
	// result
	public String getResult(){
		return perf.getString(RESULT_PUBLISH," ");
	}

	public void setResult(String s){
		editor.putString(RESULT_PUBLISH,s);
		editor.commit();
	}

	// exam duration

	public double getExamDuration(){
		return  Double.parseDouble(perf.getString(EXAM_DURATION,"1"))*60000;

	}
	public void setExamDuration(String time){
		editor.putString(EXAM_DURATION,time);
		editor.commit();
	}

}
