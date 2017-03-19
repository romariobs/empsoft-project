package br.edu.ufcg.empsoft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ufcg.empsoft.activities.AgendarVisita;

public class FazendaDetalhes extends AppCompatActivity {
    ImageView imageFazenda;
    TextView textName, textDescription, textLocalization;
    Button btnVisita, btnCultivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fazenda_detalhes_layout);

        this.imageFazenda = (ImageView)findViewById(R.id.image_fazenda);
        // TODO

        this.textName = (TextView) findViewById(R.id.name_fazenda);
        this.textName.setText("Fazenda " + getIntent().getStringExtra("name"));

        this.textDescription = (TextView)findViewById(R.id.description_fazenda);
        this.textDescription.setText(getIntent().getStringExtra("description"));

        textLocalization = (TextView)findViewById(R.id.localization_fazenda);
        textLocalization.setText("Endereço: " + getIntent().getStringExtra("localization"));

        // BOTÕES
        this.btnVisita = (Button) findViewById(R.id.btn_visita);
        this.btnVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FazendaDetalhes.this, AgendarVisita.class);
                startActivity(intent);

            }
        });

        this.btnCultivo = (Button) findViewById(R.id.btn_cultivos);


    }
}
