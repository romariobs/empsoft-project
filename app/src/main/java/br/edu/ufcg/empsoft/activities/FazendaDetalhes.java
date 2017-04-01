package br.edu.ufcg.empsoft.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.adapters.PhotoPagerAdapter;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.models.Insumo;

public class FazendaDetalhes extends AppCompatActivity {
    private ImageView imageFazenda;
    private TextView textName, textDescription, textLocalization;
    private Button btnVisita, btnCultivo;
    private ViewPager mViewPager;
    private PhotoPagerAdapter mPhotoAdapter;
    private Fazenda fazenda;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fazenda_detalhes_layout);

        String fazendaId = getIntent().getStringExtra("fazendaId");
        fazenda = database.getFazenda(fazendaId);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mPhotoAdapter = new PhotoPagerAdapter();

        mPhotoAdapter.addPhotos(fazenda.getPhotoItems());

        mViewPager.setAdapter(mPhotoAdapter);
        mViewPager.setOffscreenPageLimit(1);

        this.textName = (TextView) findViewById(R.id.name_fazenda);
        this.textName.setText(getIntent().getStringExtra("name"));

        this.textDescription = (TextView)findViewById(R.id.description_fazenda);
        this.textDescription.setText(getIntent().getStringExtra("description"));

        textLocalization = (TextView)findViewById(R.id.localization_fazenda);
        textLocalization.setText("CAMPINA GRANDE");

        // BOTÕES
        this.btnVisita = (Button) findViewById(R.id.btn_visita);
        this.btnVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FazendaDetalhes.this, AgendarVisita.class);
                intent.putExtra("Title", "Visita");
                startActivity(intent);

            }
        });

        this.btnCultivo = (Button) findViewById(R.id.btn_cultivos);
        this.btnCultivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FazendaDetalhes.this, DuracaoDeCultivo.class);
                intent.putExtra("insumos", (Insumo[])getIntent().getExtras().get("insumos"));
                intent.putExtra("fazendaId", getIntent().getStringExtra("fazendaId"));
                startActivity(intent);

            }
        });
    }
}
