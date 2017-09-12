package com.delitto.locker.Activity;

/**
 * Created by Delitto on 2017/9/12.
 */
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.delitto.locker.R;

public class SplashScreen extends AppCompatActivity{
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        setContentView(R.layout.activity_splashscreen);


        ImageView img  = (ImageView)findViewById(R.id.logo_splashscreen);
        //img.setImageResourse(R.drawable.logo);

        TextView versionNumber = (TextView)findViewById(R.id.versionNumber);
        versionNumber.setText(R.string.versionNumber);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(getApplicationContext(), MainActivity.class));
                SplashScreen.this.finish();
            }
        }, 1500);
    }

}
