package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr_virwus.ammen.R;
import com.example.mr_virwus.ammen.Tente_detail;

import java.util.List;

import model.Tente;

/**
 * Created by mr_virwus on 8/2/18.
 */

public class RecycleAdapter_tentelist extends  RecyclerView.Adapter<RecycleAdapter_tentelist.MyViewHolder> {

    Context context;
    List<Tente> tenteList;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView address, price, bed, shower, sqft;


        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.image);
            price = (TextView) view.findViewById(R.id.price);
            bed = (TextView) view.findViewById(R.id.bed);
            address = (TextView) view.findViewById(R.id.address);
            shower = (TextView) view.findViewById(R.id.shower);
            sqft = (TextView) view.findViewById(R.id.sqft);


        }

    }


    public RecycleAdapter_tentelist(Context mainActivityContacts,List<Tente> moviesList) {
        this.tenteList = moviesList;
        this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tentelist, parent, false);


        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Tente movie = tenteList.get(position);

        holder.price.setText(movie.getPrice());
        holder.bed.setText(movie.getBed());
        holder.shower.setText(movie.getShower());
        holder.sqft.setText(movie.getSqrft());
        holder.address.setText(movie.getAddress());
        holder.image.setImageResource(movie.getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Tente_detail.class);
                i.putExtra("price",movie.getPrice());
                i.putExtra("address",movie.getAddress());
                i.putExtra("bed",movie.getBed());
                i.putExtra("bath",movie.getShower());
                i.putExtra("sqft",movie.getSqrft());
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return tenteList.size();
    }


}
