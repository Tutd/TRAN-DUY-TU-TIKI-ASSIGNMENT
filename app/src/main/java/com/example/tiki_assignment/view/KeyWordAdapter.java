package com.example.tiki_assignment.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tiki_assignment.R;

import java.util.ArrayList;

public class KeyWordAdapter extends RecyclerView.Adapter<KeyWordAdapter.CustomViewHolder> {

    private ArrayList<String> mList;
    private int[] arrColor;

    public KeyWordAdapter(ArrayList<String> arrKeyWord, Context context) {
        this.mList = arrKeyWord;
        arrColor = context.getResources().getIntArray(R.array.arrColor);
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_keyword, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int i) {
        viewHolder.cardKeyWord.setCardBackgroundColor(arrColor[i % 10]);
        viewHolder.tvKeyWord.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKeyWord;
        public CardView cardKeyWord;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tvKeyWord = itemView.findViewById(R.id.tvKeyWord);
            cardKeyWord = itemView.findViewById(R.id.cardKeyWord);
        }

    }
}
