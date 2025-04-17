package com.example.lb39;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TODOAdapter extends RecyclerView.Adapter<TODOAdapter.ViewHolder> implements Filterable {

    private List<TODO> todoList;
    private List<TODO> filteredtodoList;
    private OnItemClickListener listener;
    private Context context;

    public TODOAdapter(Context context, List<TODO> todoItems, OnItemClickListener listener){
        this.context = context;
        this.todoList = todoItems;
        this.listener = listener;
        this.filteredtodoList = todoItems;
        if (this.filteredtodoList.isEmpty()) {
            // Обработка случая пустого адаптера (возможно, отобразите сообщение в представлении)
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if(charSequenceString.isEmpty()){
                    filteredtodoList = todoList;
                }
                else{
                    List<TODO> filteredList = new ArrayList<>();
                    for (TODO todos : todoList){
                        if (todos.getTitle().toLowerCase().contains(charSequenceString.toLowerCase())){
                            filteredList.add(todos);
                        }
                    }
                    filteredtodoList = filteredList;
                }
                FilterResults results = new FilterResults();
                results.values = filteredtodoList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                filteredtodoList = (List<TODO>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }



    @NonNull
    @Override
    public TODOAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TODOAdapter.ViewHolder holder, int position) {
        TODO todo = filteredtodoList.get(position);
        holder.textViewTitle.setText(todo.getTitle());
        holder.textViewText.setText(todo.getText());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));

        holder.itemView.setOnLongClickListener(v -> {
            listener.onItemLongClick(position);
            return  true;
        });
    }

    @Override
    public int getItemCount() {
        return filteredtodoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public TextView textViewText;

        public ViewHolder(View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.titleTextView);
            textViewText = itemView.findViewById(R.id.textTextView);
        }
    }
}