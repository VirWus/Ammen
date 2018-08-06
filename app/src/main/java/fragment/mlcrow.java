package fragment;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mr_virwus.ammen.JSONParser;
import com.example.mr_virwus.ammen.R;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.internal.zzagz.runOnUiThread;

public class mlcrow extends Fragment {




    private View view;

    public static String responce,state1,state2;
    private TextView crowd ;
    //private CircleProgress circleProgress;
    //private ArcProgress temp,hum,per;
    private Handler handler = new Handler();
    private int tempi,humi,perso;
    private final int f = 3000;
    private final int r = 1000;
    private ToggleButton Door1,Door2;
    private GoogleApiClient client ;
    String temper = null,humid = null,pers = null;
    private String AdressIp = "localhost";
    private Boolean cancel ;
    private ProgressDialog pDialog;
    private Button about;
    String pid;
    JSONParser jsonParser = new JSONParser();

    private Activity activity ;

    // private static final String url_get_data = "http://10.7.0.205/esp8266/get_data.php";
    private static final String url_get_data = "http://10.1.5.15:4567/vision";//?place=jeddah&label=crowd";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_BASE = "esp8266";
    private static final String TAG_ETAT_DOOR = "door";
    private static final String TAG_ETAT_DOOR1 = "door1";
    private static final String TAG_ETAT_TEMP = "temp";
    private static final String TAG_ETAT_HUM = "hum";
    private static final String TAG_ETAT_PERS = "person";
    private static final String TAG_IP_ADRESS = "ip";

    private String led1Status;
    private String serverAdresses;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_mlcrow, container, false);

        client = new GoogleApiClient.Builder(getContext()).addApi(AppIndex.API).build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        activity = getActivity();

        Intent i = getActivity().getIntent();


        GetData();

        crowd = (TextView) view.findViewById(R.id.textView2);


        return view;



    }


    public void notification()
    {
        addNotification();
    }
    private void addNotification()
    {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.drawable.ic_audiotrack_light)
                        .setContentTitle("Person Arrived...")   //this is the title of notification
                        .setColor(101)
                        .setContentText("New Person Arrived to your house.");   //this is the message showed in notification
        Intent intent = new Intent(getContext(),getClass());
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager) activity.getSystemService(getContext().NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    public void GetData() {
        handler.postDelayed(new Runnable() {
            public void run() {

                new GetDetails().execute();
                GetData();


            }
        }, f);

    }


    /**
     * run get all product in background
     * */
    class GetDetails extends AsyncTask<String, String, String> {

        /**
         * if start get activity then run progress dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pDialog = new ProgressDialog(getContext());
            //pDialog.setMessage("Retrieving Data...");
            // pDialog.setIndeterminate(false);
            //  pDialog.setCancelable(true);
            //pDialog.show();
        }

        /**
         * start run get all list and run in background
         * */
        protected String doInBackground(String... params) {

            // ui updates from threads executed
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check tag success
                    int success;
                    // create paramater
                    List<NameValuePair> params = new ArrayList<NameValuePair>();

                    params.add(new BasicNameValuePair("place", "jeddah"));
                    params.add(new BasicNameValuePair("label", "crowd"));

                    // get details from the list using http request

                    JSONObject json = jsonParser.makeHttpRequest(
                            url_get_data, "GET", params);

                    //getJSONArray("crowd"); // JSON

                    // Array

                    // check our logs with json response
                    try {
                       // Log.d("crowd", String.valueOf(json.getDouble("crowd")));
                        crowd.setText(String.valueOf(json.getDouble("crowd")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("Single Product Details", json.toString());

                    // Array

                    // get first list object from json array
                    //JSONObject data = productObj.getJSONObject(0);


                }

            });

            return null;
        }

        /**
         * If the job in the background is complete then stop progress reply
         * running
         * **/
        protected void onPostExecute(String file_url) {
            // stop progress dialog for get
            // pDialog.dismiss();
        }
    }



}
