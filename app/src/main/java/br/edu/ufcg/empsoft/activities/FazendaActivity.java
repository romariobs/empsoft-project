package br.edu.ufcg.empsoft.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.models.Insumo;

public class FazendaActivity extends AppCompatActivity {

    private Button mBtCreate;
    private EditText mEtDescription;
    private EditText mEtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazenda);
        mBtCreate = (Button) findViewById(R.id.btCreate);
        mEtName = (EditText) findViewById(R.id.etmName);
        mEtDescription = (EditText) findViewById(R.id.etmDescription);



        mBtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mEtName.getText().toString().isEmpty() && !mEtDescription.getText().toString().isEmpty()) {
                    Fazenda fazenda = new Fazenda(mEtName.getText().toString(), mEtDescription.getText().toString());

                    // --> CRIANDO INSUMOS PARA TESTES
                         fazenda.addInsumo(new Insumo("Batata", 30, 0));
                         fazenda.addInsumo(new Insumo("Milho", 60, 0));
                        // ERA MASSA COLOCAR IMAGENS PRA OS INSUMOS, UNS ÍCONES JÁ AJUDAVA
                    // <-- TESTES

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("novaFazenda", fazenda);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }

            }
        });
    }
}
