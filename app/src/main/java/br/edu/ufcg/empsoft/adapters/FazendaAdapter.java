package br.edu.ufcg.empsoft.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Fazenda;

/**
 * Created by romario on 3/12/2017.
 */

public class FazendaAdapter extends RecyclerView.Adapter<FazendaAdapter.ModelViewHolder> {
    private final RecyclerView recyclerView;
    private List<Fazenda> fazendasList;

    public FazendaAdapter(List<Fazenda> fazendasList, RecyclerView recyclerView) {
        this.fazendasList = fazendasList;
        this.recyclerView = recyclerView;
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.fazenda_cardview, parent, false);
        return new ModelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        System.out.println(fazendasList.get(0));
        Fazenda fazenda = fazendasList.get(position);
        holder.setName(fazenda.getName());
        holder.setDescription(fazenda.getDescription());

    }

    @Override
    public int getItemCount() {
        return fazendasList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mDescription;
        private ImageView mThumb;
        public ModelViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.textViewFarmName);
            mDescription = (TextView) itemView.findViewById(R.id.textViewFarmDesc);
            mThumb = (ImageView) itemView.findViewById(R.id.imageViewThumb);

        }

        public void setName(String name) {
            mName.setText(name);
        }

        public void setDescription(String message) {
            mDescription.setText(message);
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
