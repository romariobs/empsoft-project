package br.edu.ufcg.empsoft.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.adapters.CultivoAdapter;
import br.edu.ufcg.empsoft.models.Insumo;

public class DuracaoDeCultivo extends AppCompatActivity {
    Insumo[] insumos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duracao_de_cultivo);

        insumos = (Insumo[]) getIntent().getExtras().get("insumos");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setAdapter(new CultivoAdapter(insumos, this));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }
}
