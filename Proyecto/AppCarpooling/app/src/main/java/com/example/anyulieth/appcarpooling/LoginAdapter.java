package com.example.anyulieth.appcarpooling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anyulieth on 15/04/2018.
 */

public class LoginAdapter  extends RecyclerView.Adapter<LoginAdapter.ViewHolder>
        implements View.OnClickListener {
    private ArrayList<Login> login;
    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {

        this.listener = listener;
    }

    @Override
    public void onClick(View view) {

        if (listener != null) {
            listener.onClick(view);
        }
    }

    public LoginAdapter() {
        login = new ArrayList<>();
    }

    public void setmDataSet(ArrayList<Login> login) {
        this.login = login;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_login, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtCorreo.setText(login.get(position).getCorreo());
        holder.txtClave.setText(login.get(position).getClave());
    }

    @Override
    public int getItemCount() {
        return login.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public EditText txtCorreo;
        public EditText txtClave;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCorreo = (EditText) itemView.findViewById(R.id.txtCorreo);
            txtClave = (EditText) itemView.findViewById(R.id.txtClave);
        }
    }
}
