package br.edu.ufcg.empsoft.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.activities.POPOptions;
import br.edu.ufcg.empsoft.models.Insumo;

/**
 * Created by JoseLucas on 19/03/2017.
 */

public class CultivoAdapter extends RecyclerView.Adapter {
    private Insumo[] insumos;
    private Context context;
    private PopupWindow opcoesInsumo;

    public CultivoAdapter(Insumo[] insumos, Context context) {
        this.insumos = insumos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_insumo, parent, false);

        CultivoViewHolder holder = new CultivoViewHolder(view, this.context);
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

    public class CultivoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nome;
        private TextView cultivoMedio;
        private Context context;

        public CultivoViewHolder(View itemView, Context context) {
            super(itemView);
            itemView.setOnClickListener(this);
            nome = (TextView) itemView.findViewById(R.id.cultivo_name);
            cultivoMedio = (TextView) itemView.findViewById(R.id.cultivo_medio);
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Insumo insumo = insumos[position];
            System.out.println("ENTROU");
            System.out.println(insumo.getNome());

            Intent intent = new Intent(this.context, POPOptions.class);
            intent.putExtra("nome", insumo.getNome());
            context.startActivity(intent);
        }
    }
}