package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "d-MainActivity";
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequest = (Button) findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);

        responseText = (TextView) findViewById(R.id.response_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_request:
                //sendRequestWithOkHttp();
                sendRequestWithHttpUtil();
                break;
                default:
                    break;
        }
    }

    private void sendRequestWithHttpUtil() {
        // 使用HttpUrlConnection发送网络请求
        /*HttpUtil.sendHttpRequest("http://192.168.1.101:8080/fileservice/get_data.json",
                new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        parseJSONWithGson(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this, "网络访问错误", Toast.LENGTH_SHORT)
                                .show();
                    }
                });*/
        // 使用OkHttp发送网络请求
        HttpUtil.sendOkHttpRequest("http://192.168.1.101:8080/fileservice/get_data.json",
                new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this, "网络错误", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        parseJSONWithGson(response.body().string());
                    }
                });
    }

    /**
     * 使用Okhttp发送网络请求
     */
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            //.url("http://www.baidu.com")
                            .url("http://192.168.1.101:8080/fileservice/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
//                    showResponse(responseData);
//                    parseXMLWithPull(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseJSONWithJSONObject(responseData);
                    parseJSONWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 使用HttpURLConnection对象发送网络请求
     */
    private void sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    // GET方法获取网络数据
                    connection.setRequestMethod("GET");
                    // 设置连接超时
                    connection.setConnectTimeout(8000);
                    // 设置读取超时
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }

    /**
     * 使用Pull解析XML数据
     * @param xmlData xml数据
     */
    private void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            StringBuffer sb = new StringBuffer();
            while (eventType != xmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    // 开始解析某个节点
                    case XmlPullParser.START_TAG:
                        if ("id".equalsIgnoreCase(nodeName)) {
                            id = xmlPullParser.nextText();
                            sb.append("id is " + id);
                        } else if ("name".equalsIgnoreCase(nodeName)) {
                            name = xmlPullParser.nextText();
                            sb.append("name is " + name);
                        } else if ("version".equalsIgnoreCase(nodeName)) {
                            version = xmlPullParser.nextText();
                            sb.append("version is " + version);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equalsIgnoreCase(nodeName)) {
                            System.out.println(TAG + " id is " + id);
                            System.out.println(TAG + " name is " + name);
                            System.out.println(TAG + " version is " + version);
                            Log.d(TAG, "parseXMLWithPull: id is " + id);
                            Log.d(TAG, "parseXMLWithPull: name is " + name);
                            Log.d(TAG, "parseXMLWithPull: version is " + version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
            showResponse(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用SAX解析XML数据
     * @param xmlData xml数据
     */
    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.setContentHandler(handler);
            // 开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
            showResponse(handler.getXMLContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用JSONObject 解析JSON数据
     * @param jsonData json数据
     */
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            StringBuilder sb = new StringBuilder();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                sb.append("id is " + id).append(",\r\n");
                sb.append("name is " + name).append(",\r\n");
                sb.append("version is " + version).append(",\r\n");
            }
            showResponse(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用Gson解析JSON数据
     * @param jsonData json数据
     */
    private void parseJSONWithGson(String jsonData) {
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
        for (App app : appList) {
            sb.append("id is ").append(app.getId()).append("\r\n")
                    .append("name is ").append(app.getName()).append("\r\n")
                    .append("version is ").append(app.getVersion()).append("\r\n");
        }
        showResponse(sb.toString());
    }
}
