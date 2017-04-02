package br.edu.ufcg.empsoft.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.edu.ufcg.empsoft.R;
import br.edu.ufcg.empsoft.activities.POPOptions;
import br.edu.ufcg.empsoft.models.Agendamento;
import br.edu.ufcg.empsoft.models.CallBack;
import br.edu.ufcg.empsoft.models.Database;
import br.edu.ufcg.empsoft.models.Fazenda;
import br.edu.ufcg.empsoft.models.Insumo;

/**
 * Created by JoseLucas on 19/03/2017.
 */

public class CultivoAdapter extends RecyclerView.Adapter {
    private List<Insumo> insumos;
    private Fazenda fazenda;
    private Context context;
    private PopupWindow opcoesInsumo;
    private Database database = Database.getInstance();
    private RecyclerView recyclerView;

    public CultivoAdapter(final List<Insumo> insumos, Fazenda fazenda, RecyclerView rv, Context context) {
        this.insumos = insumos;
        this.fazenda = fazenda;
        this.context = context;
        this.recyclerView = rv;

        database.addListener(fazenda, new CallBack<Fazenda>() {
            @Override
            public void onDataChange(Fazenda fazendaResult) {
                List<Insumo> insumosRemote = fazendaResult.getInsumos();
                setInsumos(insumosRemote);
            }
        });
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
        recyclerView.setAdapter(this);
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

        Insumo insumo = this.insumos.get(position);

        viewHolder.setImage(insumo.getImage());

        viewHolder.nome.setText(insumo.getNome());

        viewHolder.cultivoMedio.setText("Em média "
                + Integer.toString(insumo.getCultivoMedio()) + " dias");
        viewHolder.diasCultivados.setText("Há " + insumo.getDiasCultivados() + " dias");

        if (insumo.isColhaParaMim()) {
            viewHolder.colheitaAgendada.setText("Nós colheremos");
        } else if (insumo.getProximaColheita() != null) {
            Agendamento agen = insumo.getProximaColheita();
            viewHolder.colheitaAgendada.setText(
              "Colheita Agendada: " + agen.getDay() + "/" + agen.getMonth());
        } else {
            viewHolder.colheitaAgendada.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return this.insumos.size();
    }

    public class CultivoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nome, colheitaAgendada;
        private TextView cultivoMedio, diasCultivados;
        private ImageView imagem;
        private Context context;

        public CultivoViewHolder(View itemView, Context context) {
            super(itemView);
            itemView.setOnClickListener(this);
            nome = (TextView) itemView.findViewById(R.id.cultivo_name);
            cultivoMedio = (TextView) itemView.findViewById(R.id.cultivo_medio);
            colheitaAgendada = (TextView)itemView.findViewById(R.id.colheita_agendada);
            diasCultivados = (TextView)itemView.findViewById(R.id.dias_cultivados);
            imagem = (ImageView) itemView.findViewById(R.id.cultivo_icon);
            this.context = context;
        }

        public void setImage(String url) {
            System.out.println(url);
            Picasso.with(context).load(url).into(imagem);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Insumo insumo = insumos.get(position);
            Log.wtf("CultivoAdapter", insumo.getProximaColheita() + "");

            Intent intent = new Intent(this.context, POPOptions.class);
            intent.putExtra("nome", insumo.getNome());
            intent.putExtra("fazendaId", fazenda.getId());
            context.startActivity(intent);
        }
    }
}