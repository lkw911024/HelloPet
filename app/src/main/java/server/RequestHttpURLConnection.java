package server;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import VO.MemberVO;

/**
 * Created by ael on 2017. 11. 11..
 */

public class RequestHttpURLConnection {
    private String serverUrl;
    private HttpURLConnection conn;

    public RequestHttpURLConnection(){
        this.serverUrl = "http://10.0.2.2:8089/HelloPet/";
    }

    public RequestHttpURLConnection(String mappingUrl) {
        this();
        this.serverUrl = this.serverUrl + mappingUrl;

        Log.i("url","" + serverUrl.toString());

        try {
            URL url = new URL(serverUrl);
            this.conn = (HttpURLConnection)url.openConnection();

            Log.i("여기오니",conn.toString());

            // conn 설정.
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
            conn.setRequestProperty("Accept", "application/json"); // Accept-Charset 설정.
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);  // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.
            conn.setDoInput(true); // InputStream으로 서버로 부터 응답을 받겠다는 옵션.

        }
        catch (MalformedURLException e) { // for URL.
            e.printStackTrace();
        } catch (IOException e) { // for openConnection().
            e.printStackTrace();
        }
    }

    public HttpURLConnection getConn() {
        return conn;
    }
}
