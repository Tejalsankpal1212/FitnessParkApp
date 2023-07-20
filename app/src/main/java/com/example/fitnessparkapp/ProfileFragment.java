package com.example.fitnessparkapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends Fragment {

    private static final String SHARED_PREFS="sharedprefs";
    private static final String MEMBER_ID="memberid";
    private String memberId="";
    private TextView memberName;
    private TextView clientId;
    private TextView memberStatus;

    private TextView memberEmailId;

    private TextView memberDateOfBirth;

    private TextView memberMobileNo;

    public ProfileFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences  sharedPreferences= this.getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        memberId= sharedPreferences.getString("MEMBER_ID","");
        clientId = view.findViewById(R.id.memberId);
        memberName = view.findViewById(R.id.memberName);
        memberStatus = view.findViewById(R.id.memberStatus);
        memberEmailId = view.findViewById(R.id.memberEmailId);
        memberDateOfBirth = view.findViewById(R.id.memberDateOfBirth);
        memberMobileNo = view.findViewById(R.id.memberMobileNo);
        loadMemberProfile(getContext());
        return view;
    }

    private void loadMemberProfile(Context context) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://10.0.2.2:8001/member/getmemberbyid/"+memberId;

        // Request a string response from the provided URL.
        StringRequest sr = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject payload = new JSONObject(response);
                            JSONArray jsonArray = payload.getJSONArray("payload");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            memberName.setText(jsonObject.getString("fname")+ " "+jsonObject.getString("mname")+ " "+jsonObject.getString("lname"));
                            clientId.setText("Member Id : "+memberId);
                            memberStatus.setText(jsonObject.getString("status"));
                            memberEmailId.setText(jsonObject.getString("emailid"));
                            memberDateOfBirth.setText(jsonObject.getString("dob"));
                            memberMobileNo.setText(jsonObject.getString("mobileno"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        Log.e("HttpClient", "success! response: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HttpClient", "error: " + error.toString());
                    }
                });
        queue.add(sr);
    }
}