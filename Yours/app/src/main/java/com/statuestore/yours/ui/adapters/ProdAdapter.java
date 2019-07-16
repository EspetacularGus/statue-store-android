package com.statuestore.yours.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.statuestore.yours.R;
import com.statuestore.yours.data.helpers.FileIO;
import com.statuestore.yours.data.model.Produto;

import java.util.List;

public class ProdAdapter extends RecyclerView.Adapter<ProdAdapter.MyViewHolder> {

    private List<Produto> list;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack;

    public ProdAdapter(Context context, List<Produto> list) {
        this.list = list;
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = layoutInflater.inflate(R.layout.card_layout, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Bitmap modelo = FileIO.getImageFrom(list.get(position).getImgModelo());

        myViewHolder.nome.setText(list.get(position).getNome());
        myViewHolder.subgrupo.setText(list.get(position).getSubgrupo());
        myViewHolder.modelo.setImageBitmap(modelo);
        myViewHolder.produto = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        recyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Produto produto, int position) {
        list.add(produto);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nome;
        public TextView subgrupo;
        public ImageView modelo;
        public Produto produto;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.product_name);
            subgrupo = itemView.findViewById(R.id.product_subgroup);
            modelo = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnClickListenerHack != null) {
                recyclerViewOnClickListenerHack.onClickListener(v, produto);
            }
        }
    }
}
