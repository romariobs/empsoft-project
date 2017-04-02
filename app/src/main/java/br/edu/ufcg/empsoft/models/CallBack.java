package br.edu.ufcg.empsoft.models;


import com.google.firebase.database.ValueEventListener;

/**
 * Created by stenio on 4/2/2017.
 */

public abstract class CallBack<T> {
    private ValueEventListener listener;

    public abstract void onDataChange(T result);

    public ValueEventListener getListener() {
        return listener;
    }

    public void setListener(ValueEventListener listener) {
        this.listener = listener;
    }
}
