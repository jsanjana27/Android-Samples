package com.example.networkingsample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    String urlString = "https://api.github.com/search/repositories?q=rmkrishna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MyAsyncTask().execute(urlString);

    }


    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            InputStream stream = null;
            HttpsURLConnection connection = null;
            String result = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpsURLConnection) url.openConnection();
                // Timeout for reading InputStream arbitrarily set to 3000ms.
                connection.setReadTimeout(30000);
                // Timeout for connection.connect() arbitrarily set to 3000ms.
                connection.setConnectTimeout(30000);
                // For this use case, set HTTP method to GET.
                connection.setRequestMethod("GET");
                // Already true by default but setting just in case; needs to be true since this request
                // is carrying an input (response) body.
                connection.setDoInput(true);
                // Open communications link (network traffic occurs here).
                connection.connect();
//                publishProgressress(DownloadCallback.Progress.CONNECT_SUCCESS);
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code: " + responseCode);
                }
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
//                publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
                if (stream != null) {
                    // Converts Stream to String with max length of 500.
                    result = convertStreamToString(stream, false);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Close Stream and disconnect HTTPS connection.
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        /**
         * Converts the contents of an InputStream to a String.
         */
        private String convertStreamToString(InputStream is, boolean isGzipEnabled) throws IOException {

            // Just return empty if we InputStream is null.
            if (is == null) {
                return "";
            }

            InputStream cleanedIs = is;
            if (isGzipEnabled) {
                cleanedIs = new GZIPInputStream(is);
            }

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(cleanedIs, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (String line; (line = reader.readLine()) != null; ) {
                    sb.append(line);
                    sb.append("\n");
                }

                return sb.toString();
            } finally {
                if (reader != null) {
                    reader.close();
                }

                cleanedIs.close();

                if (isGzipEnabled) {
                    is.close();
                }
            }
        }


        @Override
        protected void onPostExecute(String s) {
            Log.d("MM", "onPostExecute() called with: s = [" + s + "]");
            try {
                JSONObject rootObj = new JSONObject(s);
                JSONArray array = rootObj.getJSONArray("items");

                ArrayList<Items> items = new ArrayList();
                for (int i = 0; i < array.length(); i++) {
                    Items items1 = new Items();
                    JSONObject obj = array.getJSONObject(i);
                    long id = obj.getLong("id");
                    String name = obj.getString("name");

                    items1.setName(name);
                    items1.setId(id);


                    JSONObject ownerObj = obj.getJSONObject("owner");
                    long ownerId = ownerObj.getLong("id");
                    String url = ownerObj.getString("url");

                    Owner owner = new Owner();

                    owner.setId(ownerId);
                    owner.setUrl(url);

                    Log.d("ff", "onPostExecute: " + ownerId);
                    Log.d("ff", "onPostExecute: " + url);

                    items1.setOwner(owner);
                    items.add(items1);

                }

                for (int i = 0; i < items.size(); i++) {
                    Items items2 = items.get(i);

                    Log.d("MM", "items2 name: " + items2.getName() + " id " + items2.getId());
                }

                Log.d("ArrayList", "onPostExecute: " + items);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
