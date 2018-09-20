package com.example.shuvojoty.flash;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    Camera camera;
    Camera.Parameters parameters;
    boolean isFlash=false;
    boolean isOn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton=(ImageButton)findViewById(R.id.imagebutton);


        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
            camera=Camera.open();
            parameters=camera.getParameters();
            isFlash=true;

        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isFlash)
                {
                    if(!isOn)
                    {
                        imageButton.setImageResource(R.drawable.bon);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        isOn=true;



                    }
                    else
                    {
                        imageButton.setImageResource(R.drawable.bof);
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        camera.stopPreview();
                        isOn=false;
                    }

                }
                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("ERROOORRRRR.....");
                    builder.setMessage("FLash light is not available");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();









                }
            }
        });





    }

    @Override
    protected void onStop() {
        super.onStop();
        if(camera!=null) {
            camera.release();

            camera = null;
        }
    }
}
