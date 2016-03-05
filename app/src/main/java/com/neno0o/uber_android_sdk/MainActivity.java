package com.neno0o.uber_android_sdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.neno0o.ubersdk.Activites.Authentication;
import com.neno0o.ubersdk.Endpoints.Models.Prices.Prices;
import com.neno0o.ubersdk.Endpoints.Models.Times.Times;
import com.neno0o.ubersdk.Endpoints.Models.UserProfile.User;
import com.neno0o.ubersdk.Uber;
import com.neno0o.ubersdk.Widgets.UberButton;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    public static final int UBER_AUTHENTICATION = 1;
    public static final String TAG = "Uber_Android_SDK";
    UberButton uberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uberButton = (UberButton) findViewById(R.id.uberBtn);
        uberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Authentication.class);
                startActivityForResult(intent, UBER_AUTHENTICATION);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_price_estimation:
                estimatePrice();
                break;
            case R.id.action_time_estimation:
                estimateTime();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void estimateTime() {
        double start_latitude = 30.532752;
        double start_longitude = 104.052896;

        double end_latitude = 30.584763;
        double end_longitude = 103.965134;

        Uber.getInstance().getUberAPIService().getTimeEstimates(
                start_latitude,
                start_longitude,
                new Callback<Times>() {
                    @Override
                    public void success(Times prices, Response response) {
                        Log.d(TAG, "getPriceEstimates | prices: " + prices + ", response: " + response);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.w(TAG, "getPriceEstimates | failure, error: " + error);
                    }
                });
    }

    private void estimatePrice() {
        double start_latitude = 30.532752;
        double start_longitude = 104.052896;

        double end_latitude = 30.584763;
        double end_longitude = 103.965134;

        Uber.getInstance().getUberAPIService().getPriceEstimates(start_latitude,
                start_longitude,
                end_latitude,
                end_longitude,
                new Callback<Prices>() {
                    @Override
                    public void success(Prices prices, Response response) {
                        Log.d(TAG, "getPriceEstimates | prices: " + prices + ", response: " + response);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.w(TAG, "getPriceEstimates | failure, error: " + error);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult | requestCode: " + requestCode + ", resultCode: " + resultCode);
        if (requestCode == UBER_AUTHENTICATION && resultCode == RESULT_OK) {
            Log.d(TAG, "token: " + Uber.getInstance().getAccessToken().getAccessTokenValue());
            Uber.getInstance().getUberAPIService().getMe(new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    Log.d("user", user.getFirstName());
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
    }
}
