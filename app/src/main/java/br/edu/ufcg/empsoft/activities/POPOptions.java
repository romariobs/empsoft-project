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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.OrdemDeColheita;

/**
 * Created by JoseLucas on 25/03/2017.
 */

public class POPOptions extends Activity{

    TextView insumo;
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

        insumo = (TextView) findViewById(R.id.popup_title);
        insumo.setText(nomeInsumo);


        btnAgendarColheita = (Button)findViewById(R.id.btn_agendar_colheita);
        this.btnAgendarColheita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(POPOptions.this, AgendarVisita.class);
                intent.putExtra("Title", "Colheita");
                startActivity(intent);
                Toast.makeText(POPOptions.this,
                        "Agende sua colheita",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnColhaParaMim = (Button)findViewById(R.id.btn_colha_para_mim);
        this.btnColhaParaMim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.append(Database.Table.ORDENS_DE_COLHEITA, new OrdemDeColheita(nomeInsumo));
                Toast.makeText(POPOptions.this,
                        "Colheremos o insumo para você, não se esqueça de vir buscá-lo!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
