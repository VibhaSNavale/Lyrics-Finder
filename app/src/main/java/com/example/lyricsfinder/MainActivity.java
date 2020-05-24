package com.example.lyricsfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText edtArtistName, edtSongName;
    private Button btnGetLyrics;
    private TextView txtLyrics;

    //  https://api.lyrics.ovh/v1/Rihanna/Diamonds#

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtArtistName = findViewById(R.id.edtArtistName);
        edtSongName = findViewById(R.id.edtSongName);
        txtLyrics = findViewById(R.id.txtLyrics);
        btnGetLyrics = findViewById(R.id.btnGetLyrics);

        btnGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "Button is tapped", Toast.LENGTH_SHORT).show();

                String  url = "https://api.lyrics.ovh/v1/" + edtArtistName.getText().toString() + "/" + edtSongName.getText().toString();
                url.replace(" ", "20%");
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext()); //required if we want to start volley library in order to request something from the internet

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {  //this method is called when we get response from internet successfully i.e., lyrics(data)

                        //txtLyrics.setText(response.toString()); //gets the actual JSON object
                        try {
                            txtLyrics.setText(response.getString("lyrics"));
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(jsonObjectRequest);

            }
        }); //onClickListener end


    }
}
