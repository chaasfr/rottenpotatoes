package fr.cours.centrale.rottenpotatoes.film;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import fr.cours.centrale.rottenpotatoes.AppController;
import fr.cours.centrale.rottenpotatoes.MainActivity;
import fr.cours.centrale.rottenpotatoes.R;

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
    public void onBindViewHolder(final SimpleViewHolder holder, final int position) {
        String url = MainActivity.filmSelected.getMedias().get(position).getPath();
        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(final Bitmap bitmap) {
                        new Thread(new Runnable() {
                            public void run() {
                                holder.imageView.post(new Runnable() {
                                    public void run() {
                                        holder.imageView.setImageBitmap(bitmap);
                                    }
                                });
                            }
                        }).start();

                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        holder.imageView.setImageResource(R.drawable.no_image);
                    }
                });
// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(request);
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