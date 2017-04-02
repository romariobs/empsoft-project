package br.edu.ufcg.empsoft.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import br.edu.ufcg.empsoft.MainActivity;
import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.CallBack;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.Fazenda;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 500;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                database.addListener(new CallBack<List<Fazenda>>() {
                    public void onDataChange(List<Fazenda> result) {
                        Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();
                        database.removeListener(Database.Table.FAZENDAS, this.getListener());
                    }
                });
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
