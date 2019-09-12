package com.cbx.baidu.push;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;


public class CbxBaiduPushPlugin extends CordovaPlugin {
    private static final String TAG = CbxBaiduPushPlugin.class.getSimpleName();
    private static final String DURATION_LONG = "long";

    @Override
    public boolean execute(String action, JSONArray args,
        final CallbackContext callbackContext) {

        boolean result = true;

        if (action.equalsIgnoreCase(PluginConstant.FCT_SHOW)) {
            result = this.show(args, callbackContext);
        } else if (action.equalsIgnoreCase(PluginConstant.FCT_START_WORK)) {
            result = this.startWork(args, callbackContext);
        } else {
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            result = false;
        }

        // Send a positive result to the callbackContext
        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);
        return true;
    }


    private boolean show(JSONArray args, final CallbackContext callbackContext) {
        String message;
        String duration;

        try {
            JSONObject options = args.getJSONObject(0);
            message = options.getString("message");
            duration = options.getString("duration");

            // Create the toast
            Toast toast = Toast.makeText(cordova.getActivity(), message,
            DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
            // Display toast
            toast.show();

        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
        }

        return true;
    }


    private Context getAppContext() {
        return this.cordova.getActivity().getApplicationContext();
    }


    private boolean startWork(JSONArray args, final CallbackContext callbackContext) {
        try {
            JSONObject options = args.getJSONObject(0);
            String apiKey = options.getString("apiKey");

            Context context = this.getAppContext();

            PushManager.startWork(context, PushConstants.LOGIN_TYPE_API_KEY, apiKey);

            Log.d(CbxBaiduPushPlugin.TAG, "startWork() is called with apiKey = " + apiKey);

        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
        }
        return true;
    }
}