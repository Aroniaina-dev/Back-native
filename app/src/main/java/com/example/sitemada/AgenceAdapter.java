package com.example.sitemada;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AgenceAdapter extends RecyclerView.Adapter<AgenceAdapter.AgenceViewHolder> {

    private List<AgenceModel> agences;
    private AgenceListFragment.OnItemClickListener onItemClickListener;

    public AgenceAdapter(List<AgenceModel> agences, AgenceListFragment.OnItemClickListener listener) {
        this.agences = agences;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public AgenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agence, parent, false);
        return new AgenceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AgenceViewHolder holder, int position) {
        AgenceModel agence = agences.get(position);
        holder.bind(agence);
    }

    @Override
    public int getItemCount() {
        return agences.size();
    }

    public class AgenceViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDesignation;
        private TextView textViewEmail;
        private TextView textViewContact;

        public AgenceViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDesignation = itemView.findViewById(R.id.textDesignation);
            textViewEmail = itemView.findViewById(R.id.textEmail);
            textViewContact = itemView.findViewById(R.id.textContact);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(agences.get(position));
                    }
                }
            });
        }

        public void bind(AgenceModel agence) {
            textViewDesignation.setText(agence.getDesignation());
            textViewEmail.setText(agence.getEmail());
            textViewContact.setText(agence.getContact());
        }
    }
}