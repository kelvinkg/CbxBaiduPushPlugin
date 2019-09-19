package com.cbx.baidu.push;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushSettings;



public class CbxBaiduPushPlugin extends CordovaPlugin {
    private static final String TAG = CbxBaiduPushPlugin.class.getSimpleName() + ".";


	/** JS callback interface */
    public static CallbackContext pushCallbackContext = null;
    public static CallbackContext onMessageCallbackContext = null;
    public static CallbackContext onNotificationClickedCallbackContext = null;
    public static CallbackContext onNotificationArrivedCallbackContext = null;


    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    	LOG.d(TAG, "CbxBaiduPushPlugin#initialize");

        super.initialize(cordova, webView);
    }


    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        LOG.d(TAG, "CbxBaiduPushPlugin#execute() is called for action: " + action);

        boolean result = false;
        CbxBaiduPushPlugin.pushCallbackContext = null;


        if (action.equalsIgnoreCase(PluginConstant.FCT_START_WORK)) {
            result = this.startWork(args, callbackContext);
        } else if (action.equalsIgnoreCase(PluginConstant.FCT_STOP_WORK)) {
            result = this.stopWork(args, callbackContext);
        } else if (action.equalsIgnoreCase(PluginConstant.FCT_RESUME_WORK)) {
            result = this.resumeWork(args, callbackContext);
        } else if (action.equalsIgnoreCase(PluginConstant.FCT_IS_PUSH_ENABLED)) {
            result = this.isPushEnabled(args, callbackContext);
        } else if (action.equalsIgnoreCase(PluginConstant.FCT_SET_TAGS)) {
            result = this.setTags(args, callbackContext);
        } else if (action.equalsIgnoreCase(PluginConstant.FCT_DEL_TAGS)) {
            result = this.delTags(args, callbackContext);
        } else if (action.equalsIgnoreCase(PluginConstant.FCT_LIST_TAGS)) {
            result = this.listTags(args, callbackContext);
        } else {
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            result = false;
        }

        /*
        // Send a positive result to the callbackContext
        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);
        */
        return result;
    }

    /*
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
    */


    private boolean startWork(JSONArray args, final CallbackContext callbackContext) throws JSONException{
        CbxBaiduPushPlugin.pushCallbackContext = callbackContext;

        JSONObject options = args.getJSONObject(0);
        final String apiKey = options.getString("apiKey");
        LOG.d(TAG, "startWork(): apiKey = " + apiKey);

        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                LOG.d(TAG, "PushManager#startWork");

                PushManager.startWork(cordova.getActivity().getApplicationContext(),
                    PushConstants.LOGIN_TYPE_API_KEY, apiKey);

                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);
            }
        });

        return true;

        /*
        try {
            JSONObject options = args.getJSONObject(0);
            String apiKey = options.getString("apiKey");
            System.out.println(CbxBaiduPushPlugin.TAG + "startWork() is called with apiKey = " + apiKey);

            Context context = this.getAppContext();

            PushManager.startWork(context, PushConstants.LOGIN_TYPE_API_KEY, apiKey);

            result = true;
        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
        }

        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);
        return true;
        */
    }


    private boolean stopWork(JSONArray args, final CallbackContext callbackContext) {
        System.out.println(CbxBaiduPushPlugin.TAG + "stopWork() is called");
        boolean result = false;

        PushManager.stopWork(cordova.getActivity().getApplicationContext());
        result = true;

        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);

        return result;
    }


    private boolean resumeWork(JSONArray args, final CallbackContext callbackContext) {
        System.out.println(CbxBaiduPushPlugin.TAG + "resumeWork() is called");
        boolean result = false;

        PushManager.resumeWork(cordova.getActivity().getApplicationContext());
        result = true;

        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);

        return result;

    }


    private boolean isPushEnabled(JSONArray args, final CallbackContext callbackContext) {
        System.out.println(CbxBaiduPushPlugin.TAG + "isPushEnabled() is called");
        boolean result = false;

        result = PushManager.isPushEnabled(cordova.getActivity().getApplicationContext());

        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);

        return result;
    }


    private boolean setTags(JSONArray args, final CallbackContext callbackContext) throws JSONException {
        System.out.println(CbxBaiduPushPlugin.TAG + "setTags() is called");
        boolean result = false;

        try {
             JSONObject options = args.getJSONObject(0);
            List tags = JsonUtil.getListFromJsonArray(options.getJSONArray("tags"));

            PushManager.setTags(cordova.getActivity().getApplicationContext(), tags);
            result = true;
        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
        }
        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);

        return result;
    }


    private boolean delTags(JSONArray args, final CallbackContext callbackContext) throws JSONException {
        System.out.println(CbxBaiduPushPlugin.TAG + "delTags() is called");
        boolean result = false;

        try {
            JSONObject options = args.getJSONObject(0);
            List tags = JsonUtil.getListFromJsonArray(options.getJSONArray("tags"));

            PushManager.delTags(cordova.getActivity().getApplicationContext(), tags);

            result = true;

        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
        }

        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);

        return result;
    }


    private boolean listTags(JSONArray args, final CallbackContext callbackContext) throws JSONException {
        System.out.println(CbxBaiduPushPlugin.TAG + "listTags() is called");
        boolean result = false;
        List tags = null;

        try {
            JSONObject options = args.getJSONObject(0);
            PushManager.listTags(cordova.getActivity().getApplicationContext());

            result = true;

        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
        }

        PluginResult pluginResult = new PluginResult((result)? PluginResult.Status.OK: PluginResult.Status.ERROR);
        callbackContext.sendPluginResult(pluginResult);

        return result;
    }
}