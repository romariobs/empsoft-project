package br.edu.ufcg.empsoft.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.adapters.CultivoAdapter;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.models.Insumo;

public class DuracaoDeCultivo extends AppCompatActivity {
    private List<Insumo> insumos;
    private Database database = Database.getInstance();
    private Fazenda fazenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duracao_de_cultivo);

        fazenda = database.getFazenda(getIntent().getStringExtra("fazendaId"));
        insumos = fazenda.getInsumos();

        if (insumos.size() > 0) {
            TextView semInsumo = (TextView) findViewById(R.id.semInsumo);
            semInsumo.setVisibility(View.INVISIBLE);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setAdapter(new CultivoAdapter(insumos, fazenda, recyclerView, this));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

    }
}
