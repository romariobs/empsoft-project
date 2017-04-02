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
        this.addListener(new CallBack<List<Fazenda>>() {
            @Override
            public void onDataChange(List<Fazenda> result) {
                fazendas = result;
            }
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

    public void addListener(final CallBack<List<Fazenda>> callback) {
        if (callback != null) {
            callback.setListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<HashMap<String, Fazenda>> t =
                            new GenericTypeIndicator<HashMap<String, Fazenda>>(){};
                    HashMap<String, Fazenda> mapeamentoFazendas = dataSnapshot.getValue(t);

                    List<Fazenda> fazendasRemote = new ArrayList<Fazenda>();
                    if (mapeamentoFazendas != null) {
                        fazendasRemote = new ArrayList<>(mapeamentoFazendas.values());
                    }

                    callback.onDataChange(fazendasRemote);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
            this.addListener(Table.FAZENDAS, callback.getListener());
        }
    }

    public void addListener(Fazenda fazenda, final CallBack<Fazenda> callback) {
        if (callback != null) {
            callback.setListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Fazenda fazendaResult = dataSnapshot.getValue(Fazenda.class);
                    callback.onDataChange(fazendaResult);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
            this.references.get(Table.FAZENDAS).child(fazenda.getId())
                    .addValueEventListener(callback.getListener());
        }
    }

    public void addListener(OrdemDeColheita ordem, final CallBack<OrdemDeColheita> callback) {
        if (callback != null) {
            callback.setListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    OrdemDeColheita ordem = dataSnapshot.getValue(OrdemDeColheita.class);
                    callback.onDataChange(ordem);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
            this.references.get(Table.ORDENS_DE_COLHEITA)
                    .child(ordem.getId())
                    .addValueEventListener(callback.getListener());
        }
    }

    public void addListener(Agendamento agendamento, final CallBack<Agendamento> callback) {
        if (callback != null) {
            callback.setListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Agendamento agendamentoResult = dataSnapshot.getValue(Agendamento.class);
                    callback.onDataChange(agendamentoResult);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
            this.references.get(Table.AGENDAMENTOS)
                    .child(agendamento.getId())
                    .addValueEventListener(callback.getListener());
        }
    }

    public void removeListener(Table table, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).removeEventListener(listener);
    }

    public void removeListener(Fazenda fazenda, ValueEventListener listener) {
        if (listener != null)
            this.references.get(Table.FAZENDAS)
                    .child(fazenda.getId()).removeEventListener(listener);
    }

    public void removeListener(OrdemDeColheita ordem, ValueEventListener listener) {
        if (listener != null)
            this.references.get(Table.ORDENS_DE_COLHEITA)
                    .child(ordem.getId()).removeEventListener(listener);
    }

    public void removeListener(Agendamento agendamento, ValueEventListener listener) {
        if (listener != null)
            this.references.get(Table.AGENDAMENTOS)
                    .child(agendamento.getId()).removeEventListener(listener);
    }

    public void append(Fazenda fazenda) {
        String key = this.references.get(Table.FAZENDAS).push().getKey();
        fazenda.setId(key);
        this.references.get(Table.FAZENDAS).child(key).setValue(fazenda);
    }

    public void append(OrdemDeColheita ordem){
        String key = this.references.get(Table.ORDENS_DE_COLHEITA).push().getKey();
        ordem.setId(key);
        this.references.get(Table.ORDENS_DE_COLHEITA).child(key).setValue(ordem);
    }

    public void append(Agendamento agendamento) {
        String key = this.references.get(Table.AGENDAMENTOS).push().getKey();
        agendamento.setId(key);
        this.references.get(Table.AGENDAMENTOS).child(key).setValue(agendamento);
    }

    public Fazenda getFazenda(String fazendaId) {
        for (Fazenda fazenda: fazendas) {
            if (fazenda.getId().equals(fazendaId))
                return fazenda;
        }
        return null;
    }

    public void update(Fazenda fazenda) {
        this.references.get(Table.FAZENDAS).child(fazenda.getId()).setValue(fazenda);
    }
}