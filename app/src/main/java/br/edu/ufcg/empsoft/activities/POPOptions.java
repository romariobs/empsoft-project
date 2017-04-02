package br.edu.ufcg.empsoft.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.models.Insumo;
import br.edu.ufcg.empsoft.models.OrdemDeColheita;

/**
 * Created by JoseLucas on 25/03/2017.
 */

public class POPOptions extends Activity{

    TextView insumoText;
    private Insumo insumo;
    private Fazenda fazenda;
    Button btnAgendarColheita, btnColhaParaMim;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_insumo_options);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        WindowManager.LayoutParams windowManager = getWindow().getAttributes();
        windowManager.dimAmount = 0.75f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        getWindow().setLayout((int)(width * .8), (int)(height * .4));

        final String nomeInsumo = getIntent().getStringExtra("nome");

        insumoText = (TextView) findViewById(R.id.popup_title);
        insumoText.setText(nomeInsumo);

        String fazendaId = getIntent().getStringExtra("fazendaId");
        fazenda = database.getFazenda(fazendaId);
        insumo = fazenda.getInsumo(getIntent().getStringExtra("nome"));

        btnAgendarColheita = (Button)findViewById(R.id.btn_agendar_colheita);
        this.btnAgendarColheita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(POPOptions.this, AgendarVisita.class);
                intent.putExtra("Title", "Colheita");
                intent.putExtra("insumoNome", insumo.getNome());
                intent.putExtra("fazendaId", getIntent().getStringExtra("fazendaId"));
                startActivity(intent);
                Toast.makeText(POPOptions.this,
                        "Agende sua colheita",
                        Toast.LENGTH_SHORT).show();
                POPOptions.this.finish();
            }
        });

        btnColhaParaMim = (Button)findViewById(R.id.btn_colha_para_mim);
        this.btnColhaParaMim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insumo.setColhaParaMim(true);
                insumo.setProximaColheita(null);

                database.append(new OrdemDeColheita(nomeInsumo));
                database.update(fazenda);

                Toast.makeText(POPOptions.this,
                        "Colheremos o insumoText para você, não se esqueça de vir buscá-lo!",
                        Toast.LENGTH_SHORT).show();
                POPOptions.this.finish();
            }
        });
    }
}
