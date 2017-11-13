package com.hellopet.sangji.hellopet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        Log.d("search", "**** APP START");

        final EditText eText;
        Button btn;

        eText = (EditText) findViewById(R.id.imageSearch_image_et);
        btn = (Button) findViewById(R.id.imageSearch_search_btn);
        final TextView result = (TextView) findViewById(R.id.imageSearch_result_tv);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final String str = eText.getText().toString();
                Log.d("search", "Searching for :" + str);
                result.setText("Searching for :" + str);


                Thread thread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        try {


                            // looking for
                            String strNoSpaces = str.replace(" ", "+");

                            // Your API key
                            String key="AIzaSyANvzVy-BpfO6FDxiNEvqNxBHKuL4rl5Vs";

                            // Your Search Engine ID
                            String cx = "016097678484420070352:xpagkivok94";

                            String url2 = "https://www.googleapis.com/customsearch/v1?q=" + strNoSpaces + "&key=" + key + "&cx=" + cx + "&alt=json";
                            Log.d("search", "Url = "+  url2);
                            String result2 = httpGet(url2);

                            result.setText(result2);

                        }
                        catch(Exception e) {
                            System.out.println("Error1 " + e.getMessage());
                        }

                    }


                    private String httpGet(String urlStr) throws IOException {

                        URL url = new URL(urlStr);

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        if(conn.getResponseCode() != 200) {
                            throw new IOException(conn.getResponseMessage());
                        }

                        Log.d("search", "Connection status = " + conn.getResponseMessage());

                        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;

                        while((line = rd.readLine()) != null) {

                            Log.d("search", "Line =" + rd.readLine());
                            sb.append(line+"\n");

                        }
                        rd.close();

                        conn.disconnect();
                        return sb.toString();
                    }
                });

                thread.start();

            }
        });
    }
}
