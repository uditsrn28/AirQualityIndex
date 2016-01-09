package android.airpolind.airpollutionindex.controller;

import android.airpolind.airpollutionindex.entity.AirIndex;
import android.airpolind.airpollutionindex.interfaces.ICallback;
import android.airpolind.airpollutionindex.task.AirIndexAsycTask;
import android.airpolind.airpollutionindex.utils.Constants;
import android.airpolind.airpollutionindex.utils.Utils;
import android.content.Context;
import android.util.Log;


public class AirIndexContoller {
	private Context mContext;

	public AirIndexContoller(Context context) {
		mContext = context;
	}

	public void getCreativeTypes(int cityCode, ICallback<String> callback) {
		if (Utils.checkInternetConnection(mContext)) {
			try {
				AirIndexAsycTask task = new AirIndexAsycTask(mContext, callback);
				task.execute(cityCode);
			} catch (Exception e) {
				if (Constants.LOG_ENABLED) {
					e.printStackTrace();
				}
			}
		} else {
			if (Constants.LOG_ENABLED) {
				Log.d("Nt:-1.1.2", "Internet is not working");
			}
		}

	}

}
