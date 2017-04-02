package br.edu.ufcg.empsoft.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ufcg.empsoft.MainActivity;
import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.utils.FazendaList;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */


                Database.getInstance().addListener(Database.Table.FAZENDAS, new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<HashMap<String, Fazenda>> t =
                                new GenericTypeIndicator<HashMap<String, Fazenda>>(){};
                        HashMap<String, Fazenda> mapeamentoFazendas = dataSnapshot.getValue(t);

                        List<Fazenda> fazendas = new ArrayList<Fazenda>();
                        if (mapeamentoFazendas != null) {
                            fazendas = new ArrayList<>(mapeamentoFazendas.values());
                        }
                        FazendaList.fazendasList = fazendas;

                        Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
