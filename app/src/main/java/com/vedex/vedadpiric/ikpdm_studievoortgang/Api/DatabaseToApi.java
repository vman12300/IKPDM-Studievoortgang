package com.vedex.vedadpiric.ikpdm_studievoortgang.Api;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.vedex.vedadpiric.ikpdm_studievoortgang.LoginActivity;
import com.vedex.vedadpiric.ikpdm_studievoortgang.SignupActivity;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by vedadpiric on 31-01-17.
 */

public class DatabaseToApi {

    private void insertVak(final String moduleNaam,final int ecs, final double cijfer,
                              final String student_nummer) {
        String tag_string_req = "req_register";



        StringRequest strReq = new StringRequest(Request.Method.POST,
                Appconfig.URL_INSERT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());

            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_nummer", student_nummer);
                params.put("modulenaam", moduleNaam);
                params.put("ecs", String.valueOf(ecs));
                params.put("cijfer", String.valueOf(cijfer));

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
    private void update(final String moduleNaam,final int ecs, final double cijfer,
                           final String student_nummer) {
        String tag_string_req = "req_register";



        StringRequest strReq = new StringRequest(Request.Method.POST,
                Appconfig.URL_INSERT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());

            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_nummer", student_nummer);
                params.put("modulenaam", moduleNaam);
                params.put("ecs", String.valueOf(ecs));
                params.put("cijfer", String.valueOf(cijfer));

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
