package com.vedex.vedadpiric.ikpdm_studievoortgang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vedex.vedadpiric.ikpdm_studievoortgang.Database.DBManeger;
import com.vedex.vedadpiric.ikpdm_studievoortgang.Model.Student;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by vedadpiric on 05-01-17.
 */

public class WijzigenVak extends Activity implements View.OnClickListener {

    private EditText moduleEditText;
    private Button updateBtn, deleteBtn;
    private EditText cijferEditText;
    private EditText ecsEditText;

    private long _id;

    private DBManeger dbManager;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_updatevak);

        dbManager = new DBManeger(this);
        dbManager.open();

        moduleEditText = (EditText) findViewById(R.id.module);
        cijferEditText = (EditText) findViewById(R.id.cijfer);
        ecsEditText = (EditText) findViewById(R.id.ecs);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String moduele = intent.getStringExtra("module");
        String cijfer = intent.getStringExtra("cijfer");
        String ecs = intent.getStringExtra("ecs");
         st = intent.getStringExtra("st");
        _id = Long.parseLong(id);

        moduleEditText.setText(moduele);
        cijferEditText.setText(cijfer);
        ecsEditText.setText(ecs);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                final String module = moduleEditText.getText().toString();
                final double cijfer = Double.parseDouble(cijferEditText.getText().toString());
                final int ecs = Integer.parseInt(ecsEditText.getText().toString());

                dbManager.update(st,_id, module, cijfer,ecs);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home_intent.putExtra("st", st);
        startActivity(home_intent);
    }
}