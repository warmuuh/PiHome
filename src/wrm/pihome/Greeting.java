package wrm.pihome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Greeting extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    public void onClick(View view){
      EditText input = (EditText) findViewById(R.id.host);
      String string = input.getText().toString();
      Toast.makeText(this, "Button1 pressed:" + string, Toast.LENGTH_LONG).show();
    }
}
