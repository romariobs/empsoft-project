package br.edu.ufcg.empsoft.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Insumo;

/**
 * Created by JoseLucas on 19/03/2017.
 */

public class CultivoAdapter extends RecyclerView.Adapter {
    private Insumo[] insumos;
    private Context context;

    public CultivoAdapter(Insumo[] insumos, Context context) {
        this.insumos = insumos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_insumo, parent, false);

        CultivoViewHolder holder = new CultivoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CultivoViewHolder viewHolder = (CultivoViewHolder) holder;

        Insumo insumo = this.insumos[position];

        System.out.println(insumo == null);

        viewHolder.nome.setText(insumo.getNome());

        viewHolder.cultivoMedio.setText("Em média "
                + Integer.toString(insumo.getCultivoMedio()) + " dias");
    }

    @Override
    public int getItemCount() {
        return this.insumos.length;
    }

    public class CultivoViewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private TextView cultivoMedio;

        public CultivoViewHolder(View itemView) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.cultivo_name);
            cultivoMedio = (TextView) itemView.findViewById(R.id.cultivo_medio);
        }
    }
}
