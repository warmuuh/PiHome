package wrm.pihome;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import roboguice.util.SafeAsyncTask;
import wrm.pihome.events.SwitchButtonEvent;
import wrm.pihome.promise.Deferred;
import wrm.pihome.promise.Deferred.Promise;
import android.util.Log;

import com.google.inject.Singleton;

@Singleton
public class PiServeApi {

	String URL = "/api/plug";
	private String host;
	private String user;
	private String password;

	private class RestAsyncTask<RT> extends SafeAsyncTask<RT> {
		protected RestTemplate getRestTemplate() {
			RestTemplate rest = new RestTemplate();
			rest.getMessageConverters().add(
					new MappingJackson2HttpMessageConverter());
			
			return rest;
		}
		
		final String url;
		private Class<? extends RT> resultType;
		private Object param;
		private Deferred<RT> deferred;
		
		
		
		public RestAsyncTask(String url, Class<? extends RT> resultType, Object param) {
			super();
			this.url = url;
			this.resultType = resultType;
			this.param = param;
			this.deferred = new Deferred<RT>();
		}


		public Deferred<RT>.Promise getPromise() {
			return deferred.getPromise();
		}
		
		@Override
		protected void onException(Exception e) throws RuntimeException {
			Log.e("PiServApi", "Failed to call restservice", e);
			deferred.reject(e);
		}


		public RT call() throws Exception {
			RestTemplate template = getRestTemplate();
			HttpAuthentication authHeader = new HttpBasicAuthentication(user, password);
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setAuthorization(authHeader);
			HttpEntity<?> entity = new HttpEntity<Object>(param, requestHeaders);
			return (RT) template.exchange(host + url, HttpMethod.POST, entity, resultType);
		}
		
		@Override
		protected void onSuccess(RT t) throws Exception {
			deferred.resolve(t);
		}
		
		
		
	}
	
	
	

	public Deferred<Integer>.Promise switchPlug(SwitchButtonEvent sbe) {
		RestAsyncTask<Integer> task = new RestAsyncTask<Integer>(URL, Integer.class, sbe);
		task.execute();
		return task.getPromise();
	}




	public void setConfiguration(String host, String user, String password) {
		this.host = host;
		this.user = user;
		this.password = password;
		
	}

}
