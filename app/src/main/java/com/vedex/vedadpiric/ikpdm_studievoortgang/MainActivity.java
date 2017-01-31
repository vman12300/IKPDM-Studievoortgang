package com.vedex.vedadpiric.ikpdm_studievoortgang;



import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.vedex.vedadpiric.ikpdm_studievoortgang.Database.DBManeger;
import com.vedex.vedadpiric.ikpdm_studievoortgang.Database.DatabaseHelper;



public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textViewPunten;
    private DBManeger dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    String st;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.MODULENAAM, DatabaseHelper.CIJFER, DatabaseHelper.ECS  };

    final int[] to = new int[] { R.id.id, R.id.module, R.id.cijfer,R.id.ecs  };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.prgsBar);
        textViewPunten = (TextView) findViewById(R.id.aantalPunten);

        Intent intent = getIntent();
        st = intent.getStringExtra("st");


        dbManager = new DBManeger(this);
        dbManager.open();

        Cursor schoolVaken = dbManager.fetch(st);
        refrashStatus(st);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, schoolVaken, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView moduleTextView = (TextView) view.findViewById(R.id.module);
                TextView cijferTextView = (TextView) view.findViewById(R.id.cijfer);
                TextView ecsTextView = (TextView) view.findViewById(R.id.ecs);

                String id = idTextView.getText().toString();
                String module = moduleTextView.getText().toString();
                String cijfer = cijferTextView.getText().toString();
                String ecs = ecsTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), WijzigenVak.class);
                modify_intent.putExtra("module", module);
                modify_intent.putExtra("cijfer", cijfer);
                modify_intent.putExtra("ecs", ecs);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("st" ,st);
                startActivity(modify_intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, ToevoegenVak.class);

            add_mem.putExtra("st", st);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

    public void refrashStatus(String st){

        progressBar.setMax(60);
        progressBar.setProgress(dbManager.sumEcs(st));
        textViewPunten.setText(dbManager.sumEcs(st)+"/"+progressBar.getMax());
    }
}