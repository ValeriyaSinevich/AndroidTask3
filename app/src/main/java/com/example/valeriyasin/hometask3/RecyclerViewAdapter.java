package com.example.valeriyasin.hometask3;

import android.icu.text.AlphabeticIndex;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by valeriyasin on 11/28/16.
 */

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Observer {
    private List<Post> posts;

    public RecyclerViewAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ViewHolder(v);
    }

    /**
     * Заполнение виджетов View данными из элемента списка с номером i
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Post post = posts.get(i);
        viewHolder.name.setText(post.getName());
        viewHolder.text.setText(post.getText());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView icon;
        private TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.recyclerViewItemName);
            icon = (ImageView) itemView.findViewById(R.id.recyclerViewItemIcon);
        }
    }
}
