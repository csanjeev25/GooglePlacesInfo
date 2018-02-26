package com.insomniac.googlemapsinfo;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServicesAvail())
            init();
    }

    private void init(){
            Button mapButton = (Button) findViewById(R.id.btnMap);
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,MapsActivity.class));;
                }
            });
    }



    private boolean isServicesAvail(){

        int avail = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(avail == ConnectionResult.SUCCESS)
            return true;

        else{
            if(GoogleApiAvailability.getInstance().isUserResolvableError(avail)){
                Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,avail,ERROR_DIALOG_REQUEST);
                dialog.show();
            }else {
                Toast.makeText(this,"You cant make map requests",Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }
}
