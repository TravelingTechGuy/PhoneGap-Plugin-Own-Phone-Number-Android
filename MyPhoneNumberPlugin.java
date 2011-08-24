package com.travelingtechguy.myphonenumberplugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;

/**
 * @author Guy Vider, © 2011 Traveling Tech Guy LLC
 *
 */
public class MyPhoneNumberPlugin extends Plugin {

	/***
	 * @param action - name of action to execute (if more than 1 available)
	 * @param data - JSON data input
	 * @param callbackId - function to call on completion
	 * @return PluginResult containing result data
	 */
	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId) {
		Log.d("MyPhoneNumberPlugin", "Plugin called");
		PluginResult result = null;
		try {
			JSONObject number = getMyPhoneNumber();
			Log.d("MyPhoneNumberPlugin", "Returning "+ number.toString());
			result = new PluginResult(Status.OK, number);
		}
		catch (Exception ex) {
			Log.d("MyPhoneNumberPlugin", "Got Exception "+ ex.getMessage());
			result = new PluginResult(Status.ERROR);
		}
		return result;
	}
	
	/***
	 * 
	 * @return JSONObject containing phone number
	 * @throws JSONException
	 */
	private JSONObject getMyPhoneNumber() throws JSONException {
		Log.d("MyPhoneNumberPlugin", "at getMyPhoneNumber");
		JSONObject result = new JSONObject();
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getLine1Number();
        Log.d("MyPhoneNumberPlugin", "Phone number=" + number);
        result.put("phoneNumber", number);
		return result;
	}
}

