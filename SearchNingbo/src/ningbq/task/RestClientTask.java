package ningbq.task;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;

public abstract class RestClientTask extends
		AsyncTask<Context, Void, JSONObject> {
	
	public RestClient restClient ;
	private OnPostExecuteDelegate	onPostExecuteDelegate;
	private OnPreExecuteDelegate	onPreExecuteDelegate;
	
	public RestClientTask(){
		restClient = new RestClient() ;
	}

	@Override
	protected JSONObject doInBackground(Context... params) {
		doExecute() ;
		return getResult();
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		if(null != onPostExecuteDelegate){
			onPostExecuteDelegate.action(result) ;
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if(null != onPreExecuteDelegate){
			onPreExecuteDelegate.action() ;
		}
	}
	
	public void setOnPostExecuteDelegate(OnPostExecuteDelegate delegate) {
		onPostExecuteDelegate = delegate;
	}
	
	public void setOnPreExecuteDelegate(OnPreExecuteDelegate delegate) {
		onPreExecuteDelegate = delegate;
	}
	
	public JSONObject getResult() {
		if (null == restClient) { return null; }
		
		JSONObject data = null;
		try {
			data = new JSONObject(restClient.getResponse());
			return data;
		} catch (JSONException e) {
			return null;
		}
	}
	
	public abstract void doExecute();
	
	public interface OnPostExecuteDelegate {
		public void action(JSONObject result);
	}
	
	public interface OnPreExecuteDelegate {
		public void action();
	}

}
