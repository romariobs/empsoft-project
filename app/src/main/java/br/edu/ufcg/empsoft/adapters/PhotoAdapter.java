package br.edu.ufcg.empsoft.adapters;

import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by treinamento-20 on 29/03/17.
 */
public interface PhotoAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    View getCardViewAt(int position);

    int getCount();

}
