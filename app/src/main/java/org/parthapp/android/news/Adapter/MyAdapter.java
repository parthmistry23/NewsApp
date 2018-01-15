package org.parthapp.android.news.Adapter;

import android.content.Intent;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import org.parthapp.android.news.UI.DetailNews;
import org.parthapp.android.news.Pojo.DataList;
import com.example.android.news.R;
import org.parthapp.android.news.UI.NewsFragment;

import java.util.List;

/**
 * Created by parth on 12/30/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<DataList> dataList;
    private NewsFragment context;

    public MyAdapter(NewsFragment context, List<DataList> dataListList) {
        this.dataList = dataListList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType==1){
            View view = inflater.inflate(R.layout.first_row, parent, false);
            return new MyViewHolder(view);
        }else{
        View view = inflater.inflate(R.layout.raw_layout, parent, false);
            return new MyViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final DataList dl = dataList.get(position);
        if (dl != null) {
            holder.textView.setText(dl.getTitle());
            if(dl.getUrlToImage()!=null)
            {Glide.with(context).load(dl.getUrlToImage()).into(holder.image);}
            else{holder.image.setImageResource(R.drawable.news);}
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), DetailNews.class);
                    intent.putExtra("URL", dl.getUrl());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
////
    public void addAll(List<DataList> dataListList) {
        this.dataList=dataListList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            Typeface font=Typeface.createFromAsset(context.getContext().getAssets(),"fonts/Bold.otf");
            textView.setTypeface(font);
        }
    }
}
