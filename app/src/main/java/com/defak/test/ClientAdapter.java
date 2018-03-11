package com.defak.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DEFAK on 3/4/2018.
 */

public class ClientAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Client> clientList;

    public ClientAdapter(Context context, int layout, List<Client> clientList) {
        this.context = context;
        this.layout = layout;
        this.clientList = clientList;
    }

    @Override
    public int getCount() {
        return clientList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        //map component to view
        ImageView img = view.findViewById(R.id.imgview);
        TextView txtName = view.findViewById(R.id.txtview_name);
        TextView txtAddr = view.findViewById(R.id.txtview_addr);
        TextView txtState = view.findViewById(R.id.txtview_state);

        Client client = clientList.get(pos);
        img.setImageResource(R.drawable.pic);
        txtName.setText(client.getName());
        txtAddr.setText(client.getAddr());
        txtState.setText(client.getState());
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
