package br.edu.ufcg.empsoft.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stenio on 17/03/17.
 */

public class Database {
    private FirebaseDatabase database;
    private Map<Table, DatabaseReference> references;
    private static Database instance;

    public static enum Table {
        FAZENDAS("fazendas"), INSUMOS("insumos");

        Table(String insumos) {}
    }

    private Database() {
        this.database = FirebaseDatabase.getInstance();
        this.references = this.initialize_refers();
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    private Map<Table, DatabaseReference> initialize_refers() {
        Map<Table, DatabaseReference> refers = new HashMap<>();

        refers.put(Table.FAZENDAS, this.database.getReference(String.valueOf(Table.FAZENDAS)));
        refers.put(Table.INSUMOS, this.database.getReference(String.valueOf(Table.INSUMOS)));

        return refers;
    }

    public void addListener(Table table, ValueEventListener listener) {
        if (listener != null)
            this.references.get(table).addValueEventListener(listener);
    }

    public void append(Table table, Fazenda fazenda) {
        String key = this.references.get(table).push().getKey();
        fazenda.setId(key);
        this.references.get(table).child(key).setValue(fazenda);
    }

//    public void append(Table table, Insumo insumo) {
//        String key = this.references.get(table).push().getKey();
//        this.references.get(table).child(key).setValue(insumo);
//        insumo.setId(key);
//    }
}
