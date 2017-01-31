package com.vedex.vedadpiric.ikpdm_studievoortgang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedex.vedadpiric.ikpdm_studievoortgang.Database.DBManeger;
import com.vedex.vedadpiric.ikpdm_studievoortgang.Model.Student;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by vedadpiric on 05-01-17.
 */

public class ToevoegenVak extends Activity implements View.OnClickListener {
    private Button addTodoBtn;
    private EditText ecsEditText;
    private EditText cijferEditText;
    private EditText moduleEditText;
    String st;


    private DBManeger dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        st = intent.getStringExtra("st");

        setTitle("Add Record");

        setContentView(R.layout.activity_toevoegenvak);

        moduleEditText = (EditText) findViewById(R.id.module);
        cijferEditText = (EditText) findViewById(R.id.cijfer);
        ecsEditText = (EditText) findViewById(R.id.ecs);

        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManeger(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }
    //test
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String module = moduleEditText.getText().toString();
                final double cijfer = Double.parseDouble(cijferEditText.getText().toString());
                final int ecs = Integer.parseInt(ecsEditText.getText().toString());
                if (cijfer >=5.5) {

                    dbManager.insert(st,module, cijfer, ecs);
                    Toast.makeText(getApplicationContext(),
                            "Gefeliciteerd  !!", Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getApplicationContext(),
                            "Je cijfer is te laag dus 0 ECS", Toast.LENGTH_LONG).show();
                    dbManager.insert(st,module, cijfer, 0);
                }
                Intent main = new Intent(ToevoegenVak.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                main.putExtra("st", st);
                startActivity(main);
                break;
        }
    }

}
