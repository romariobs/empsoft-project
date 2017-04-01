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

public class AgendarVisita extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatePicker datePicker;
    private Button visitaOk;
    private Button visitaCancelar;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_visita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handleDatePicker(this);

        setTitle(getIntent().getExtras().getString("Title"));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
                    database.update(Database.Table.FAZENDAS, fazenda);
                } else {
                    database.append(Database.Table.AGENDAMENTOS, agendamento);
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.agendar_visita, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}