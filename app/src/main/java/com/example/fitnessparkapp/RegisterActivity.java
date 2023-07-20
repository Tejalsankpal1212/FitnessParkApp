package com.example.fitnessparkapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.widget.Toast;
public class RegisterActivity extends AppCompatActivity {
    private static final String SHARED_PREFS="sharedprefs";
    private static final String MEMBER_ID="memberid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<String, String>();

                params.put("firstname", ((EditText) findViewById(R.id.memberFirstName)).getText().toString());
                params.put("middlename", ((EditText) findViewById(R.id.memberMiddleName)).getText().toString());
                params.put("lastname", ((EditText) findViewById(R.id.memberLastName)).getText().toString());
                params.put("dateofbirth", ((EditText) findViewById(R.id.memberDateOfBirth)).getText().toString());
                params.put("emailid", ((EditText) findViewById(R.id.memberEmailId)).getText().toString());
                params.put("mobileno", ((EditText) findViewById(R.id.memberMobileNo)).getText().toString());

                //String url = "http://10.0.2.2:8001/member/postmemberfromapp";
                String url = "http://localhost:8001/member/postmemberfromapp";

                // Request a string response from the provided URL.
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    //JSONArray jsonArray = new JSONArray(response);
                                    Log.e("api", response.toString());
//                                    for (int i = 0; i < jsonArray.length(); i++)
//                                    {
                                        //JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        try {
                                            String memberId = response.getString("payload");
                                            String responseMessage=response.getString("message");
                                            SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                                            SharedPreferences.Editor editor=sharedPreferences.edit();
                                            editor.putString("MEMBER_ID",memberId);
                                            editor.apply();
                                            Toast.makeText(getApplicationContext(),responseMessage,Toast.LENGTH_LONG).show();

                                            // After successfully registration redirect to home page
                                            Intent home=new Intent(RegisterActivity.this,MainActivity.class);
                                            startActivity(home);

                                        } catch (Exception e) {
                                            Log.e("",e.getMessage());
                                        }
//                                    }
                                } catch (Exception e) {
                                    Log.e("", e.getMessage());
                                }
                            }
                        }, new Response.ErrorListener(

                ) {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}