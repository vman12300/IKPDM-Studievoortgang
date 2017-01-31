package com.vedex.vedadpiric.ikpdm_studievoortgang;


        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.android.volley.Request.Method;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.vedex.vedadpiric.ikpdm_studievoortgang.Api.AppController;
        import com.vedex.vedadpiric.ikpdm_studievoortgang.Api.Appconfig;
        import java.util.HashMap;
        import java.util.Map;


public class SignupActivity extends Activity {
    private static final String TAG = SignupActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btn_login;
    private EditText student_nummer,naam,email,wachtwoord;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        student_nummer = (EditText) findViewById(R.id.student_nummer);
        naam = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        wachtwoord = (EditText) findViewById(R.id.et_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_loginBACK);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String student_nummer_st = student_nummer.getText().toString().trim();
                String naam_st = naam.getText().toString().trim();
                String email_st = email.getText().toString().trim();
                String password_st = wachtwoord.getText().toString().trim();

                if (!student_nummer_st.isEmpty() && !naam_st.isEmpty() && !email_st.isEmpty() && !password_st.isEmpty()) {
                    registerUser(student_nummer_st,naam_st, email_st, password_st);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Velden zijn leeg", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    private void registerUser(final String student_nummer,final String name, final String email,
                              final String password) {
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                Appconfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                Toast.makeText(getApplicationContext(), "UW account is aangemaakt", Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                         startActivity(intent);
                        finish();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_nummer", student_nummer);
                params.put("naam", name);
                params.put("email", email);
                params.put("wachtwoord", password);

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}