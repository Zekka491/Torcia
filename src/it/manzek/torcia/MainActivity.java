package it.manzek.torcia;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Camera camera;
    Toast t;
    ImageView lampadina;
    boolean FlashOn = false;
    Camera.Parameters p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testiamo la presenza del flash
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
        	t = new Toast(this);
        	t.makeText(this, "FLASH SUPPORTATO", Toast.LENGTH_LONG).show();
        	camera = Camera.open();
            lampadina = (ImageView)findViewById(R.id.lampadina);
            lampadina.setOnClickListener(new View.OnClickListener(){
            	@Override
            	public void onClick(View v){
            		p = camera.getParameters();
            		if(!FlashOn){
            			p.setFlashMode(Parameters.FLASH_MODE_TORCH);
            			camera.setParameters(p);
            			FlashOn = true;
            		}
            		else{
            			p.setFlashMode(Parameters.FLASH_MODE_OFF);
            			camera.setParameters(p);
            			FlashOn = false;
            		}
            	}
            });
        }
        else{
        	t = new Toast(this);
        	t.makeText(this, "FLASH NON SUPPORTATO", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
