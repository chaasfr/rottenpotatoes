package fr.cours.centrale.rottenpotatoes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.SimpleViewHolder>{

    private Context context;

    public ImageListAdapter(Context context){
        this.context = context;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;

        public SimpleViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(this.context).inflate(R.layout.film_image, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        String url = MainActivity.filmSelected.getMedias().get(position).getPath();
        Glide.with(context)
                .load(url)
                .into(holder.imageView);
        Log.d("bleh bleh bleh",url);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return MainActivity.filmSelected.getMedias().size();
    }
}