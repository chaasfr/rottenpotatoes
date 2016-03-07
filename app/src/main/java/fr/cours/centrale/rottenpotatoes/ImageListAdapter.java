package fr.cours.centrale.rottenpotatoes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;

    private List<String> imageUrls;

    public ImageListAdapter(Context context, List<String> imageUrls) {
        super(context, R.layout.film_image, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.film_image, parent, false);
        }

        Glide
                .with(context)
                .load(imageUrls.get(position))
                .into((ImageView) convertView);
        Log.d("Bleh bleh bleh",imageUrls.get(0));

        return convertView;
    }
}