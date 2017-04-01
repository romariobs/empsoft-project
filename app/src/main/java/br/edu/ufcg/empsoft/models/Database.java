package br.edu.ufcg.empsoft.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stenio on 17/03/17.
 */

public class Database {
    private FirebaseDatabase database;
    private Map<Table, DatabaseReference> references;
    private static Database instance;
    private List<Fazenda> fazendas;

    public static enum Table {
        FAZENDAS, AGENDAMENTOS, ORDENS_DE_COLHEITA
    }

    private Database() {
        this.database = FirebaseDatabase.getInstance();
        this.references = this.initialize_refers();
        this.addListener(Table.FAZENDAS, new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, Fazenda>> t =
                        new GenericTypeIndicator<HashMap<String, Fazenda>>(){};
                HashMap<String, Fazenda> mapeamentoFazendas = dataSnapshot.getValue(t);

                List<Fazenda> fazendasRemote = new ArrayList<Fazenda>();
                if (mapeamentoFazendas != null) {
                    fazendasRemote = new ArrayList<>(mapeamentoFazendas.values());
                }

                fazendas = fazendasRemote;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    private Map<Table, DatabaseReference> initialize_refers() {
        Map<Table, DatabaseReference> refers = new HashMap<>();

        refers.put(Table.FAZENDAS,
                this.database.getReference(String.valueOf(Table.FAZENDAS)));
        refers.put(Table.AGENDAMENTOS,
                this.database.getReference(String.valueOf(Table.AGENDAMENTOS)));
        refers.put(Table.ORDENS_DE_COLHEITA,
                this.database.getReference(String.valueOf(Table.ORDENS_DE_COLHEITA)));

        return refers;
    }

    public void addListener(Table table, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).addValueEventListener(listener);
    }

    public void addListener(Table table, Fazenda fazenda, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).child(fazenda.getId()).addValueEventListener(listener);
    }

    public void addListener(Table table, OrdemDeColheita ordem, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).child(ordem.getId()).addValueEventListener(listener);
    }

    public void addListener(Table table, Agendamento agendamento, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).child(agendamento.getId()).addValueEventListener(listener);
    }

    public void removeListener(Table table, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).removeEventListener(listener);
    }

    public void removeListener(Table table, Fazenda fazenda, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).child(fazenda.getId()).removeEventListener(listener);
    }

    public void removeListener(Table table, OrdemDeColheita ordem, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).child(ordem.getId()).removeEventListener(listener);
    }

    public void removeListener(Table table, Agendamento agendamento, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).child(agendamento.getId()).removeEventListener(listener);
    }

    public void append(Table table, Fazenda fazenda) {
        String key = this.references.get(table).push().getKey();
        fazenda.setId(key);
        this.references.get(table).child(key).setValue(fazenda);
    }

    public void append(Table table, OrdemDeColheita ordem){
        String key = this.references.get(table).push().getKey();
        ordem.setId(key);
        this.references.get(table).child(key).setValue(ordem);
    }

    public void append(Table table, Agendamento agendamento) {
        String key = this.references.get(table).push().getKey();
        agendamento.setId(key);
        this.references.get(table).child(key).setValue(agendamento);
    }

    public Fazenda getFazenda(String fazendaId) {
        for (Fazenda fazenda: fazendas) {
            if (fazenda.getId().equals(fazendaId))
                return fazenda;
        }
        return null;
    }

    public void update(Table table, Fazenda fazenda) {
        this.references.get(table).child(fazenda.getId()).setValue(fazenda);
    }
}