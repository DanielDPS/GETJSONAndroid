package com.example.dany.gamaslt;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.kosalgeek.asynctask.EachExceptionsHandler;
import com.kosalgeek.asynctask.ExceptionHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Dany on 26/04/2016.
 */
public class JsonAsyncTask   extends AsyncTask<String, Void, String> {

    private String LOG = "PostResponseAsyncTask";
    private ProgressDialog progressDialog;
    private RespuestaJson asyncResponse;
    private   Context context;
    private HashMap<String, String> postData = new HashMap();
    private String loadingMessage = "Cargando...";
    private boolean showLoadingMessage = true;
    private ExceptionHandler exceptionHandler;
    private EachExceptionsHandler eachExceptionsHandler;
    private Exception exception = new Exception();






    public JsonAsyncTask(Context context, RespuestaJson asyncResponse) {
        this.asyncResponse = asyncResponse;
        this.context = context;
    }

    public JsonAsyncTask(Context context, boolean showLoadingMessage, RespuestaJson asyncResponse) {
        this.asyncResponse = asyncResponse;
        this.context = context;
        this.showLoadingMessage = showLoadingMessage;
    }

    public JsonAsyncTask(Context context, HashMap<String, String> postData,RespuestaJson asyncResponse) {
        this.context = context;
        this.postData = postData;
        this.asyncResponse = asyncResponse;
    }

    public JsonAsyncTask(Context context, HashMap<String, String> postData, boolean showLoadingMessage, RespuestaJson asyncResponse) {
        this.context = context;
        this.postData = postData;
        this.asyncResponse = asyncResponse;
        this.showLoadingMessage = showLoadingMessage;
    }

    public JsonAsyncTask(Context context, String loadingMessage, RespuestaJson asyncResponse) {
        this.context = context;
        this.loadingMessage = loadingMessage;
        this.asyncResponse = asyncResponse;
    }

    public JsonAsyncTask(Context context, HashMap<String, String> postData, String loadingMessage, RespuestaJson asyncResponse) {
        this.context = context;
        this.postData = postData;
        this.loadingMessage = loadingMessage;
        this.asyncResponse = asyncResponse;
    }

    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }

    public HashMap<String, String> getPostData() {
        return this.postData;
    }

    public void setPostData(HashMap<String, String> postData) {
        this.postData = postData;
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public void setEachExceptionsHandler(EachExceptionsHandler eachExceptionsHandler) {
        this.eachExceptionsHandler = eachExceptionsHandler;
    }

    public String getLoadingMessage() {
        return this.loadingMessage;
    }

    public   Context getContext() {
        return this.context;
    }



    public RespuestaJson getAsyncResponse() {
        return this.asyncResponse;
    }

    protected void onPreExecute() {
        Toast.makeText(context,loadingMessage.toString(),Toast.LENGTH_SHORT).show();
/*
try {

    this.progressDialog = new ProgressDialog(this.context);
    this.progressDialog.setMessage(this.loadingMessage);
    this.progressDialog.show();

}catch (Exception e){
    e.printStackTrace();
}

     */


    }

    protected String doInBackground(String... urls) {
        String result = "";

        for(int i = 0; i <= 0; ++i) {
            result = this.invokePost(urls[i], this.postData);
        }

        return result;
    }

    private String invokePost(String requestURL, HashMap<String, String> postDataParams) {
        String response = "";

        try {
            URL url = new URL(requestURL);
            HttpURLConnection e = (HttpURLConnection)url.openConnection();
            e.setReadTimeout(7000);/*15000*/
            e.setConnectTimeout(7000);/*15000*/
            e.setRequestMethod("POST");
            e.setDoInput(true);
            e.setDoOutput(true);
            OutputStream os = e.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(this.getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = e.getResponseCode();
            String line;
            if(responseCode == 200) {
                for(BufferedReader br = new BufferedReader(new InputStreamReader(e.getInputStream())); (line = br.readLine()) != null; response = response + line) {
                    ;
                }
            } else {
                response = "";
                Log.d("PostResponseAsyncTask", responseCode + "");
            }
        } catch (MalformedURLException var11) {
            Log.d("PostResponseAsyncTask", "MalformedURLException Error: " + var11.toString());
            this.exception = var11;
        } catch (ProtocolException var12) {
            Log.d("PostResponseAsyncTask", "ProtocolException Error: " + var12.toString());
            this.exception = var12;
        } catch (UnsupportedEncodingException var13) {
            Log.d("PostResponseAsyncTask", "UnsupportedEncodingException Error: " + var13.toString());
            this.exception = var13;
        } catch (IOException var14) {
            Log.d("PostResponseAsyncTask", "IOException Error: " + var14.toString());
            this.exception = var14;
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator var4 = params.entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry entry = (Map.Entry)var4.next();
            if(first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode((String)entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String)entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    protected void onPostExecute(String result) {
      /*  if(this.showLoadingMessage && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
*/
        result = result.trim();
        if(this.asyncResponse != null) {
            this.asyncResponse.ProcesoFinalizado(result);
        }

        if(this.exception != null) {
            if(this.exceptionHandler != null) {
                this.exceptionHandler.handleException(this.exception);
            }

            if(this.eachExceptionsHandler != null) {
                Log.d(this.LOG, "" + this.exception.getClass().getSimpleName());
                if(this.exception instanceof MalformedURLException) {
                    this.eachExceptionsHandler.handleMalformedURLException((MalformedURLException)this.exception);
                } else if(this.exception instanceof ProtocolException) {
                    this.eachExceptionsHandler.handleProtocolException((ProtocolException)this.exception);
                } else if(this.exception instanceof UnsupportedEncodingException) {
                    this.eachExceptionsHandler.handleUnsupportedEncodingException((UnsupportedEncodingException)this.exception);
                } else if(this.exception instanceof IOException) {
                    this.eachExceptionsHandler.handleIOException((IOException)this.exception);
                }
            }
        }

    }

    /** @deprecated */
    @Deprecated
    public JsonAsyncTask(RespuestaJson asyncResponse) {
        this.asyncResponse = asyncResponse;
        this.context = (Context)asyncResponse;
    }

    /** @deprecated */
    @Deprecated
    public JsonAsyncTask(RespuestaJson asyncResponse, HashMap<String, String> postData) {
        this.asyncResponse = asyncResponse;
        this.context = (Context)asyncResponse;
        this.postData = postData;
    }

    /** @deprecated */
    @Deprecated
    public JsonAsyncTask(RespuestaJson asyncResponse, String loadingMessage) {
        this.asyncResponse = asyncResponse;
        this.context = (Context)asyncResponse;
        this.loadingMessage = loadingMessage;
    }

    /** @deprecated */
    @Deprecated
    public JsonAsyncTask(RespuestaJson asyncResponse, HashMap<String, String> postData, String loadingMessage) {
        this.asyncResponse = asyncResponse;
        this.context = (Context)asyncResponse;
        this.postData = postData;
        this.loadingMessage = loadingMessage;
    }
}
