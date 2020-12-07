package com.siddheswar.bookhub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class RecyclerHomeAdapter extends RecyclerView.Adapter<RecyclerHomeAdapter.RecyclerViewHolderHome> {
    public String[] title;
    public String[] image;

    public String[] Preview;
    public String[] Info;
    public Context context;
    public  RecyclerHomeAdapter (String[] title,String[] image,String[] Preview,String[] Info,Context context)
    {

      this.context= context;
      this.title=title;
      this.image=image;
      this.Preview=Preview;
      this.Info=Info;
    }

    @NonNull
    @Override
    public RecyclerViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item,parent,false);

        return new RecyclerViewHolderHome(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderHome holder, int position) {
        String name = title[position];

        String Detail = Info[position];
        String Look = Preview[position];
        String Photo = image[position];


        holder.txtPreview.setText(Look);
        holder.txtInfo.setText(Detail);

        holder.txtSample.setText(name);

        Picasso.with(context).load(Photo).into(holder.imgSampleImage);

        final  String look = Look;

        holder.btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(look));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        final String detail = Detail;
        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(detail));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });







    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class RecyclerViewHolderHome extends RecyclerView.ViewHolder{
       TextView txtSample,txtPreview,txtInfo;
       Button btnPreview, btnInfo;
       ImageView imgSampleImage;

        public RecyclerViewHolderHome(@NonNull View itemView) {
            super(itemView);
            txtSample = itemView.findViewById(R.id.textBookName);

            btnInfo = itemView.findViewById(R.id.btnInfo);
            btnPreview = itemView.findViewById(R.id.btnPreview);
            imgSampleImage = itemView.findViewById(R.id.SampleImage);

            txtInfo=itemView.findViewById(R.id.txtInfo);
            txtPreview=itemView.findViewById(R.id.txtPreview);
        }
    }
}
