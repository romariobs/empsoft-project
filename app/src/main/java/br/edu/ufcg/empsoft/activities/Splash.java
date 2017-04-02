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
import br.edu.ufcg.empsoft.models.CallBack;
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
                Database.getInstance().addListener(new CallBack<List<Fazenda>>() {
                    @Override
                    public void onDataChange(List<Fazenda> result) {
                        FazendaList.fazendasList = result;

                        Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();
                    }
                });
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
