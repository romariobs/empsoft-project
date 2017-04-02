package br.edu.ufcg.empsoft.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Agendamento;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.models.Insumo;

public class AgendarVisita extends AppCompatActivity {

    private DatePicker datePicker;
    private Button visitaOk;
    private Button visitaCancelar;
    private Database database = Database.getInstance();
    private Fazenda fazenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_visita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handleDatePicker(this);

        fazenda = database.getFazenda(getIntent().getStringExtra("fazendaId"));

        setTitle(getIntent().getExtras().getString("Title"));

    }

    private void handleDatePicker(final Context context) {
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        visitaOk = (Button) findViewById(R.id.visitaOK);
        visitaCancelar = (Button) findViewById(R.id.visitaCancelar);

        visitaOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleConfirmationDatePicker(datePicker);
            }
        });
        visitaCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgendarVisita.this.finish();
            }
        });
    }

    private void handleConfirmationDatePicker(DatePicker datePicker) {
        final int mYear = datePicker.getYear();
        final int mMonth = datePicker.getMonth() + 1;
        final int mDay = datePicker.getDayOfMonth();
        AlertDialog.Builder builder = new AlertDialog.Builder(AgendarVisita.this);
        builder.setMessage("Sua visita será " + mDay + "/" + mMonth + "/" + mYear)
                .setTitle("CONFIRMAÇÃO");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Agendamento agendamento = new Agendamento(mYear, mMonth, mDay);
                Toast.makeText(AgendarVisita.this,
                        "Agendamento realizado com sucesso.", Toast.LENGTH_SHORT).show();

                String insumoNome = getIntent().getStringExtra("insumoNome");
                if (insumoNome != null) {
                    String fazendaId = getIntent().getStringExtra("fazendaId");
                    Fazenda fazenda = database.getFazenda(fazendaId);
                    Insumo insumo = fazenda.getInsumo(insumoNome);
                    insumo.setProximaColheita(agendamento);
                    insumo.setColhaParaMim(false);
                    database.update(fazenda);
                } else {
                    fazenda.setProximaVistia(agendamento);
                    database.update(fazenda);
                }

                AgendarVisita.this.finish();
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(AgendarVisita.this,
                        "Agendamento não realizado.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }
}
