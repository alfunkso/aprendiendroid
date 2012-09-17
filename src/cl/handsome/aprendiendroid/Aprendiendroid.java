package cl.handsome.aprendiendroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Aprendiendroid extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprendiendroid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_aprendiendroid, menu);
        return true;
    }
}
