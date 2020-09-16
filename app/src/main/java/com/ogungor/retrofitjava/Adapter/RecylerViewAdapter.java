package com.ogungor.retrofitjava.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ogungor.retrofitjava.Model.CryptoModel;
import com.ogungor.retrofitjava.R;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.RowHolder> {

    private ArrayList<CryptoModel> cryptoModelArrayList;

    private String[] colors = {"#a3ff00", "#ff00aa", "#b4a7d6", "#a4c2f4", "#8ee5ee"};


    public RecylerViewAdapter(ArrayList<CryptoModel> cryptoModelArrayList) {
        this.cryptoModelArrayList = cryptoModelArrayList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.crypto_view, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

        holder.bind(cryptoModelArrayList.get(position), colors, position);
    }

    @Override
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView currencyText;
        TextView priceText;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(CryptoModel cryptoModel, String[] color, Integer position) {


            itemView.setBackgroundColor(Color.parseColor(color[position % 5]));
            currencyText = itemView.findViewById(R.id.currency_text);
            priceText = itemView.findViewById(R.id.price_text);
            currencyText.setText(cryptoModel.currency);
            priceText.setText(cryptoModel.price);
        }


    }

}

