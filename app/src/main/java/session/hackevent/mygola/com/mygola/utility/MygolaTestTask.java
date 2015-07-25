package session.hackevent.mygola.com.mygola.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import session.hackevent.mygola.com.mygola.model.GameItem;
import session.hackevent.mygola.com.mygola.model.ResponseDetails;
import session.hackevent.mygola.com.mygola.model.TaskResult;


/**
 * Created by nitinraj.arvind on 7/6/2015.
 */
public class MygolaTestTask extends AsyncTask<Void,Object,TaskResult> {


    OperationCallback operationCallback = null;
    RestRelatedWork jobs = null;
    Context context = null;

    private static final String STAGEUPDATE = "stage_update:";

    private int indexOfInterest = 10;

    TaskResult taskResult = null;
    private int progressCount = 0;

    public MygolaTestTask(Context mContext, OperationCallback mOperationCallback) {
        this.context = mContext;
        this.operationCallback = mOperationCallback;
        this.taskResult = new TaskResult();
        this.jobs = RestRelatedWork.getInstance(mContext);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        operationCallback.onProgressStarted();
        taskResult.setStartTime(new Date());
        SharedPrefHandler.getInstance(context).upApiHitCount();
    }

    @Override
    protected TaskResult doInBackground(Void... params) {

        try {
            ResponseDetails responseDetails = jobs.getWebPage(context);
            String responseString = responseDetails.getReponseString();

            if(responseString!=null) {
//                Gson gson = new Gson();
//                Type listType = new TypeToken<List<GameItem>>(){}.getType();
//                List<GameItem> myModelList = gson.fromJson(responseString, listType);
//                taskResult.setGameItemList(myModelList);

                return taskResult;
            }
        }catch (Exception e){
            publishProgress(e);
        }
        return null;
    }


    @Override
    protected void onPostExecute(TaskResult result) {
        super.onPostExecute(result);
        operationCallback.onProgressEnded();
        if(result!=null) {
            result.setEndTime(new Date());
            operationCallback.storeTaskResult(result);
          }
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        if(operationCallback!=null){
            if(values[0] instanceof Exception) {
                Exception e = (Exception) values[0];
                operationCallback.processException(e);
            }else if(values[0] instanceof Integer) {
                Integer progressCount = (Integer) values[0];
                operationCallback.onProgressUpdated(progressCount);
            }else if(values[0] instanceof String) {
                String data = (String) values[0];
            }
       }

    }

}
