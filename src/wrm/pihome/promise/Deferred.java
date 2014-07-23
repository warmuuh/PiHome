package wrm.pihome.promise;

/**
 * naive promise implementation loosely based on Promise/A.
 * no chaining, no exception handling etc... 
 * 
 * @author pmucha
 *
 * @param <T>
 */
public class Deferred<T> {
	
	public static interface Callback<V>{
		void call(V value);
	}
	
	
	public class Promise {
		
		private Callback<T> cb;
		private Callback<Exception> cbe;
		private boolean cbCalled = false;
		public Promise then(Callback<T> cb){
			this.cb = cb;
			callCb();
			return this;
		}
		
		public Promise otherwise(Callback<Exception> cbe){
			this.cbe = cbe;
			callCb();
			return this;
		}

		protected void callCb() {
			if (isResolved && cb != null && !cbCalled){
				cbCalled = true;
				cb.call(value);
			}
			
			if (isRejected && cbe != null && !cbCalled){
				cbCalled = true;
				cbe.call(exception);
			}
		}
	
	}
	
	
	private T value = null;
	private Exception exception = null;
	private boolean isResolved = false;
	private boolean isRejected = false;
	private Promise promise;
	
	
	public Promise getPromise(){
		if (promise == null)
			promise = new Promise();
		return promise;
	}
	
	public void resolve(T value){
		this.value = value;
		this.isResolved = true;
		if (promise != null)
			promise.callCb();
	}

	public void reject(Exception e) {
		this.exception = e;
		this.isRejected = true;
		if (promise != null)
			promise.callCb();
	}
	
}
