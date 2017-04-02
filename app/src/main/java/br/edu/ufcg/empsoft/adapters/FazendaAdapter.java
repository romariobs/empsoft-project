package br.edu.ufcg.empsoft.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufcg.empsoft.activities.FazendaDetalhes;
import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.models.Insumo;

/**
 * Created by romario on 3/12/2017.
 */

public class FazendaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final RecyclerView recyclerView;
    private List<Fazenda> fazendasList;
    private int typeOfView;
    private Context context;

    public FazendaAdapter(List<Fazenda> fazendasList, RecyclerView recyclerView, Context context, int typeOfView) {
        this.fazendasList = fazendasList;
        this.recyclerView = recyclerView;
        this.typeOfView = typeOfView;
        this.context = context;

        for (Fazenda fazenda: fazendasList
             ) {
            for (Insumo insumo: fazenda.getInsumos()
                 ) {
                System.out.println(insumo.getImage());
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (typeOfView) {
            case 1: //Visualization Mode List
                itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.fazenda_viewholder, parent, false);
                return new ListViewHolder(itemView, this.context ,this.fazendasList);
            case 0: //Visualization Mode Card
                itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.fazenda_cardview, parent, false);
                return new CardViewHolder(itemView, this.context ,this.fazendasList);
            default:
                itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.fazenda_cardview, parent, false);
                return new CardViewHolder(itemView, this.context ,this.fazendasList);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Fazenda fazenda = fazendasList.get(position);
        switch (holder.getItemViewType()) {
            case 1: //Visualization Mode List
                ListViewHolder listViewHolder = (ListViewHolder)holder;
                listViewHolder.setName(fazenda.getName());
                listViewHolder.setDescription(fazenda.getDescription());
                listViewHolder.setImage(fazenda.getMainImage().getUrl());
                break;

            case 0: //Visualization Mode Card
                CardViewHolder cardViewHolder = (CardViewHolder)holder;
                cardViewHolder.setName(fazenda.getName().toUpperCase());
//                cardViewHolder.setDescription(fazenda.getDescription());
                //cardViewHolder.setImage(fazenda.getMainImage().getUrl());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return typeOfView;
    }

    @Override
    public int getItemCount() {
        return fazendasList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mName;
        private TextView mDescription;
        private ImageView mThumb;
        private Context context;
        private List<Fazenda> fazendas;

        public ListViewHolder(View itemView, Context context, List<Fazenda> fazendas) {
            super(itemView);
            this.context = context;
            this.fazendas = fazendas;
            itemView.setOnClickListener(this);
            mName = (TextView) itemView.findViewById(R.id.tvName);
            mDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            mThumb = (ImageView) itemView.findViewById(R.id.ivIcon);
        }

        public void setName(String name) {
            mName.setText(name);
        }

        public void setDescription(String message) {
            mDescription.setText(message);
        }

        public void setImage(String image) {
            Picasso.with(context).load(image).into(mThumb);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Fazenda fazenda = this.fazendas.get(position);
            ArrayList<Insumo> insumos = fazenda.getInsumos();

            Intent intent = new Intent(this.context, FazendaDetalhes.class);
            intent.putExtra("name", fazenda.getName());
            intent.putExtra("description", fazenda.getDescription());
            intent.putExtra("localization", fazenda.getLocalization());
            intent.putExtra("insumos", insumos.toArray(new Insumo[insumos.size()]));
            intent.putExtra("fazendaId", fazenda.getId());
            this.context.startActivity(intent);
        }


    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mName;
        private TextView mDescription;
        private ImageView mThumb;
        private Context context;
        private List<Fazenda> fazendas;

        public CardViewHolder(View itemView, Context context, List<Fazenda> fazendas) {
            super(itemView);
            this.context = context;
            this.fazendas = fazendas;
            itemView.setOnClickListener(this);
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

        public void setImage(String url) {
            Picasso.with(context).load(url).into(mThumb);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Fazenda fazenda = this.fazendas.get(position);
            ArrayList<Insumo> insumos = fazenda.getInsumos();

            Intent intent = new Intent(this.context, FazendaDetalhes.class);
            intent.putExtra("name", fazenda.getName());
            intent.putExtra("description", fazenda.getDescription());
            intent.putExtra("localization", fazenda.getLocalization());
            intent.putExtra("fazendaId", fazenda.getId());
            this.context.startActivity(intent);
        }


    }

    public void addFazenda(Fazenda fazenda) {
        fazendasList.add(fazenda);
        recyclerView.setAdapter(this);
    }

    public void setFazendasList(List<Fazenda> fazendas) {
        fazendasList = fazendas;
        recyclerView.setAdapter(this);
    }

}
