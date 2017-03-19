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

public class FazendaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final RecyclerView recyclerView;
    private List<Fazenda> fazendasList;
    private int typeOfView;

    public FazendaAdapter(List<Fazenda> fazendasList, RecyclerView recyclerView, int typeOfView) {
        this.fazendasList = fazendasList;
        this.recyclerView = recyclerView;
        this.typeOfView = typeOfView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (typeOfView) {
            case 1:
                itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.fazenda_viewholder, parent, false);
                return new ListViewHolder(itemView);
            case 0:
                itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.fazenda_cardview, parent, false);
                return new CardViewHolder(itemView);
            default:
                itemView = LayoutInflater.
                        from(parent.getContext()).
                        inflate(R.layout.fazenda_cardview, parent, false);
                return new CardViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Fazenda fazenda = fazendasList.get(position);
        switch (holder.getItemViewType()) {
            case 1:
                ListViewHolder listViewHolder = (ListViewHolder)holder;
                listViewHolder.setName(fazenda.getName());
                listViewHolder.setDescription(fazenda.getDescription());
//                listViewHolder.setThumb(fazenda.getThumb());
                break;

            case 0:
                CardViewHolder cardViewHolder = (CardViewHolder)holder;
                cardViewHolder.setName(fazenda.getName());
                cardViewHolder.setDescription(fazenda.getDescription());
//                cardViewHolder.setThumb(fazenda.getThumb());
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

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mDescription;
        private ImageView mThumb;
        public ListViewHolder(View itemView) {
            super(itemView);
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

        public void setThumb(int thumb) {
            mThumb.setImageResource(thumb);
        }
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;
        private ImageView mThumb;

        public CardViewHolder(View itemView) {
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

        public void setThumb(int thumb) {
            mThumb.setImageResource(thumb);
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
