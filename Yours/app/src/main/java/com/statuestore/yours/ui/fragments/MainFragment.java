package com.statuestore.yours.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.statuestore.yours.R;
import com.statuestore.yours.data.model.LoggedInUser;
import com.statuestore.yours.ui.adapters.ProdAdapter;
import com.statuestore.yours.data.control.ProdutoController;
import com.statuestore.yours.data.model.Produto;
import com.statuestore.yours.ui.adapters.RecyclerViewOnClickListenerHack;
import com.statuestore.yours.ui.compra.CompraActivity;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    List<Produto> list = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.linearCards);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                list = new ProdutoController().listaProduto(LoggedInUser.getId());

                final ProdAdapter adapter = new ProdAdapter(getActivity(), list);

                adapter.setRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack() {

                    @Override
                    public void onClickListener(View view, Produto produto) {
                        CompraActivity compra = new CompraActivity();
                        CompraActivity.produto = produto;
                        Intent intent = new Intent(getActivity(), compra.getClass());
                        startActivity(intent);
                    }
                });
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();


        return view;
    }
}



