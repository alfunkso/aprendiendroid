package cl.handsome.aprendiendroid;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class Aprendiendroid extends Activity {
	
	private Juego juego;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprendiendroid);

		List<Integer> numberPool = new ArrayList<Integer>(55);    	
    	TableLayout grid = (TableLayout)findViewById(R.id.grid);
    	
    	LinearLayout layout = null;

    	for ( int i = 0; i < 5; ++i ) {
    		
    		layout = new LinearLayout(getApplicationContext());
    		layout.setLayoutParams( new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT 
    																, LinearLayout.LayoutParams.WRAP_CONTENT ) );
    		for ( int j = 0; j < 10; ++j ) {

    	    	final Button button = new Button(getApplicationContext());
    			int n = (int)(Math.random()*9 + 1);
    			numberPool.add(n);
    			button.setText( "   " + n + "   " );
    			button.setLayoutParams( new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT
    																	, RelativeLayout.LayoutParams.WRAP_CONTENT) );
    			button.setBackgroundResource( R.drawable.pixel_button_layout );
    			
    			button.setOnClickListener( new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						juego.seleccionar( button );
					}
				} );
    			
    			layout.addView(button);
    		}
    		
    		grid.addView(layout);
    	}

    	this.juego = new Juego( numberPool, (TextView)this.findViewById(R.id.textView1)  );
    	this.juego.instruir();
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_aprendiendroid, menu);
        return true;
    }
}
