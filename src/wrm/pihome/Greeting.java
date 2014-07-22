package wrm.pihome;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
    
    
    private final String url = "/api/plug";
    
    private RestTemplate getRestTemplate(){
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
      return restTemplate;
    }
    
    public void onClick(View view){
      EditText input = (EditText) findViewById(R.id.host);
      String host = input.getText().toString();
      
      Toast.makeText(this, "Switching button1", Toast.LENGTH_LONG).show();
      try{
        String result = getRestTemplate().postForObject(host + url, new SwitchRequest(0), String.class);
        Toast.makeText(this, "Switched button1: " + result, Toast.LENGTH_LONG).show();
      } catch(Exception e){
        Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
      }
      
    }
}
