package com.example.umer.hotelapp;

import android.app.DownloadManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.umer.hotelapp.R.drawable.logo;

public class HomeActivity extends AppCompatActivity {

    List<Currency> list=new ArrayList<Currency>();
    ListView listView;
    ImageView imageView;
    /*private WebView wv1;*/
    TextView dateplace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      /*  wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());*/

        imageView=(ImageView) findViewById(R.id.logo);
        imageView.setImageResource(R.drawable.logo);

        listView=(ListView) findViewById(R.id.list);
        getExchangeRate();
        Date c = Calendar.getInstance().getTime();


        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        dateplace=(TextView) findViewById(R.id.dateplace);
        dateplace.setText(formattedDate);
    }

    void getExchangeRate()
    {

        String url="http://belockchain.com/getlist.php";

        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url,null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    for (int i = 0; i < response.getJSONArray("object").length(); i++) {
                        Currency currency = new Currency();
                        currency.setName(response.getJSONArray("object").getJSONObject(i).getString("name"));
                        currency.setRate(response.getJSONArray("object").getJSONObject(i).getString("rate"));
                        currency.setFlag(response.getJSONArray("object").getJSONObject(i).getString("flag"));
                        list.add(currency);
                       /* wv1.getSettings().setLoadsImagesAutomatically(true);
                        wv1.getSettings().setJavaScriptEnabled(true);
                        wv1.loadUrl("http://belockchain.com/sliding.php");*/
                    }

                    CurrencyAdaptor adaptor=new CurrencyAdaptor(getApplicationContext(),R.layout.list_item,list);
                    listView.setAdapter(adaptor);
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("tag",error);
            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);


    }
}
class MyBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}