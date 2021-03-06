package br.edu.ufcg.empsoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.empsoft.adapters.FazendaAdapter;
import br.edu.ufcg.empsoft.models.CallBack;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.Fazenda;

public class MainActivity extends AppCompatActivity {

    private FazendaAdapter adapter;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setBackground(new ColorDrawable(Color.parseColor("#2ecc71")));
        setSupportActionBar(toolbar);

        initAdapter(0); //Default mode card view
    }

    private void initAdapter(int typeOfView) {
        RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        adapter = new FazendaAdapter(new ArrayList<Fazenda>(), recList, this, typeOfView);
        database.addListener(new CallBack<List<Fazenda>>() {
            @Override
            public void onDataChange(List<Fazenda> result) {
                adapter.setFazendasList(result);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 25) {
            if(resultCode == Activity.RESULT_OK){
                Fazenda result= (Fazenda) data.getSerializableExtra("novaFazenda");
                database.append(result);
                adapter.addFazenda(result);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }
}
