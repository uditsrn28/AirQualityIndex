package android.airpolind.airpollutionindex.task;

import android.airpolind.airpollutionindex.entity.AirIndex;
import android.airpolind.airpollutionindex.interfaces.ICallback;
import android.airpolind.airpollutionindex.model.AirIndexData;
import android.airpolind.airpollutionindex.utils.Utils;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


public class AirIndexAsycTask extends AsyncTask<Integer, Void, String> {

	private Context mContext;
	private ICallback<String> mCallback;

	public AirIndexAsycTask(Context mContext, ICallback<String> mCallback) {
		super();
		this.mContext = mContext;
		this.mCallback = mCallback;
	}

	@Override
	protected String doInBackground(Integer... params) {
		if (Utils.checkInternetConnection(mContext)) {
			try {
				AirIndexData airIndexData = new AirIndexData();
				return airIndexData.getPostData(params[0], mContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Log.d("Nt:-1.1.2", "Internet is not working");
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			this.mCallback.getResponse(result);
		} catch (Exception e) {
			Log.d("Nt:-1.1.2", "SomeThing gone Wrong wih the Result");
		}
	}

}
