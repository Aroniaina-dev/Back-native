package com.example.sitemada;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DestiAdapter extends RecyclerView.Adapter<DestiAdapter.DestinationViewHolder> {

    private List<DestinationModel> destis;
    private DestiList.OnItemClickListener onItemClickListener;

    public DestiAdapter(List<DestinationModel> dest, DestiList.OnItemClickListener listener) {
        this.destis = dest;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_desti, parent, false);
        return new DestinationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position) {
        DestinationModel dest = destis.get(position);
        holder.bind(dest);
    }

    @Override
    public int getItemCount() {
        return destis.size();
    }

    public class DestinationViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitre;
        private TextView textViewLocalisation;
        private TextView textViewDescri;

        public DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitre = itemView.findViewById(R.id.textTitreD);
            textViewLocalisation = itemView.findViewById(R.id.textLocalisationD);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(destis.get(position));
                    }
                }
            });
        }

        public void bind(DestinationModel desti) {
            textViewTitre.setText(desti.getTitre());
            textViewLocalisation.setText(desti.getLocalisation());
        }
    }
}