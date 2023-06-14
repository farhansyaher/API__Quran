package com.example.api_quran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_quran.models.VerseModel.VersesItem;

import java.util.List;

public class AdapterVerses extends RecyclerView.Adapter<AdapterVerses.AyatViewHolder> {
    private List<VersesItem> results;

    public AdapterVerses(List<VersesItem> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.verses, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AyatViewHolder holder, int position) {
        VersesItem result = results.get(position);

        holder.textViewAyat.setText(result.getTextIndopak());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat;
        public AyatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
        }
    }

    public void setData(List<VersesItem> data){
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }


}
