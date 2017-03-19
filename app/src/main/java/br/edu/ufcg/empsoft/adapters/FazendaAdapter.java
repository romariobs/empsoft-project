package br.edu.ufcg.empsoft.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.empsoft.activities.FazendaDetalhes;
import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.models.Insumo;

/**
 * Created by romario on 3/12/2017.
 */

public class FazendaAdapter extends RecyclerView.Adapter<FazendaAdapter.ModelViewHolder> {
    private final RecyclerView recyclerView;
    private List<Fazenda> fazendasList;
    private Context context;

    public FazendaAdapter(List<Fazenda> fazendasList, RecyclerView recyclerView, Context context) {
        this.fazendasList = fazendasList;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.fazenda_viewholder, parent, false);
        return new ModelViewHolder(itemView, this.context ,this.fazendasList);
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        Fazenda fazenda = fazendasList.get(position);
        holder.setName(fazenda.getName());
        holder.setDescription(fazenda.getDescription());

    }

    @Override
    public int getItemCount() {
        return fazendasList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mName;
        private TextView mDescription;
        private Context context;
        private List<Fazenda> fazendas;

        public ModelViewHolder(View itemView, Context context, List<Fazenda> fazendas) {
            super(itemView);
            this.context = context;
            this.fazendas = fazendas;
            itemView.setOnClickListener(this);
            mName = (TextView) itemView.findViewById(R.id.tvName);
            mDescription = (TextView) itemView.findViewById(R.id.tvDescription);

        }

        public void setName(String name) {
            mName.setText(name);
        }

        public void setDescription(String message) {
            mDescription.setText(message);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Fazenda fazenda = this.fazendas.get(position);
            ArrayList<Insumo> insumos = fazenda.getInsumos();

            Intent intent = new Intent(this.context, FazendaDetalhes.class);
            // TODO: FIND A WAY TO STORE AND RETRIEVE FARM IMAGES
            intent.putExtra("name", fazenda.getName());
            intent.putExtra("description", fazenda.getDescription());
            intent.putExtra("localization", fazenda.getLocalization());
            intent.putExtra("insumos", insumos.toArray(new Insumo[insumos.size()]));
            this.context.startActivity(intent);
        }
    }

    public void addFazenda(Fazenda fazenda) {
        fazendasList.add(fazenda);
        recyclerView.setAdapter(this);
    }

    public void addFazends(List<Fazenda> fazendas) {
        fazendasList = fazendas;
        recyclerView.setAdapter(this);
    }

    public void setFazendasList(List<Fazenda> fazendas) {
        fazendasList = fazendas;
        recyclerView.setAdapter(this);
    }

}
