package com.asus.zenbodialogsample;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.asus.ctc.tool.DSAPI_Result;
import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.asus.robotframework.API.RobotFace;
import com.asus.robotframework.API.SpeakConfig;
import com.robot.asus.robotactivity.RobotActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ZenboDialogSample extends RobotActivity {
    public final static String TAG = "ZenboDialogSample";
    public final static String DOMAIN = "9EF85697FF064D54B32FF06D21222BA2";

    private static TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zenbo_dialog_sample);

        mTextView = (TextView) findViewById(R.id.textview_info);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // close faical
        robotAPI.robot.setExpression(RobotFace.HIDEFACE);

        // jump dialog domain
        robotAPI.robot.jumpToPlan(DOMAIN, "lanuchHelloWolrd_Plan");

        // listen user utterance
        robotAPI.robot.speakAndListen("Which city do you like?", new SpeakConfig().timeout(20));

        // show hint
        mTextView.setText(getResources().getString(R.string.dialog_example));

    }


    @Override
    protected void onPause() {
        super.onPause();

        //stop listen user utterance
        robotAPI.robot.stopSpeakAndListen();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static RobotCallback robotCallback = new RobotCallback() {
        @Override
        public void onResult(int cmd, int serial, RobotErrorCode err_code, Bundle result) {
            super.onResult(cmd, serial, err_code, result);
        }

        @Override
        public void onStateChange(int cmd, int serial, RobotErrorCode err_code, RobotCmdState state) {
            super.onStateChange(cmd, serial, err_code, state);
        }
    };


    public static RobotCallback.Listen robotListenCallback = new RobotCallback.Listen() {
        @Override
        public void onFinishRegister() {

        }

        @Override
        public void onVoiceDetect(JSONObject jsonObject) {

        }

        @Override
        public void onSpeakComplete(String s, String s1) {

        }

        @Override
        public void onEventUserUtterance(JSONObject jsonObject) {
            String text;
            text = "onEventUserUtterance: " + jsonObject.toString();
            Log.d(TAG, text);
        }

        @Override
        public void onResult(JSONObject jsonObject) {
            String text;
            text = "onResult: " + jsonObject.toString();
            Log.d(TAG, text);

            String sSluResultCity = QuerySLUJson(jsonObject, "myCity1");

            if(sSluResultCity!= null) {
                mTextView.setText("You are now at " + sSluResultCity);
            }

        }

        @Override
        public void onRetry(JSONObject jsonObject) {

        }
    };


    public ZenboDialogSample() {
        super(robotCallback, robotListenCallback);
    }




    public static String QuerySLUJson(JSONObject Result, String QueryString) {
        DSAPI_Result mDSAPI_Result = new DSAPI_Result(Result.toString());
        JSONObject app_semantic = mDSAPI_Result.event_slu_query.app_semantic();

        if (app_semantic == null)
            return null;

		/*
        // use IntentionId to verify what user said
        if(app_semantic != null) {
            try {
                if (app_semantic.getString("IntentionId").equals("helloWorld")) {
                    Log.d(TAG, "IntentionId is helloWorld");
                    //Do things if you get helloWorld intentionId
                }
            }catch(JSONException e) {
                e.getStackTrace();
            }
        }
		*/

        try {
            Log.d(TAG, "QuerySLUJson app_semantic = " + app_semantic);

            if (app_semantic != null) {
                if (app_semantic.isNull(QueryString)) {
                    return null;
                } else {
                    return (app_semantic.optString(QueryString, null));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

