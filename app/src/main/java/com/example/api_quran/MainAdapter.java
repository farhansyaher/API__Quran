package com.example.api_quran;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api_quran.models.ChapterModel.ChaptersItem;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<ChaptersItem> results;

    public MainAdapter(List<ChaptersItem> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChaptersItem result = results.get(position);

        holder.textViewLatihSurah.setText(result.getNameSimple());
        holder.textViewArtiSurah.setText(result.getTranslatedName().getName());
        holder.textViewArabSurah.setText(result.getNameArabic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);

                intent.putExtra("id", result.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLatihSurah , textViewArtiSurah, textViewArabSurah;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLatihSurah = itemView.findViewById(R.id.tvLatinSurah);
            textViewArtiSurah = itemView.findViewById(R.id.tvArtiSurah);
            textViewArabSurah = itemView.findViewById(R.id.tvArabSurah);
        }
    }

    public void setData(List<ChaptersItem> data){
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }
}
