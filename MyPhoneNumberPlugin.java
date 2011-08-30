/**
 * 
 */
package com.TravelingTechGuy.ImAlive;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
 * @author Guy Vider
 *
 */
public class MyPhoneNumberPlugin extends Plugin {

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

	private JSONObject getMyPhoneNumber() throws JSONException {
		Log.d("MyPhoneNumberPlugin", "at getMyPhoneNumber");
		JSONObject result = new JSONObject();
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String number = tm.getLine1Number();
        number = "";
        if(number.equals("") || number == null) {
        	Log.d("MyPhoneNumberPlugin", "We're on a non-phone device. Returning a hash of the UDID");
        	number = md5(tm.getDeviceId()).substring(0, 10);
        }
        Log.d("MyPhoneNumberPlugin", "Phone number=" + number);
        result.put("phoneNumber", number);
		return result;
	}
	
	private String md5(String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();
	        
	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i=0; i<messageDigest.length; i++)
	            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	        return hexString.toString();
	        
	    } catch (NoSuchAlgorithmException e) {
	    	Log.d("MyPhoneNumberPlugin", e.getMessage());
	    }
	    return "";
	}
}
