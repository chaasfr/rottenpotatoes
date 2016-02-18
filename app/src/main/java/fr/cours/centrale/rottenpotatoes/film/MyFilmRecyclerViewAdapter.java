package fr.cours.centrale.rottenpotatoes.film;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.List;

import fr.cours.centrale.rottenpotatoes.AppController;
import fr.cours.centrale.rottenpotatoes.R;
import fr.cours.centrale.rottenpotatoes.film.FilmFragment.OnFilmSelectedListener;

public class MyFilmRecyclerViewAdapter extends RecyclerView.Adapter<MyFilmRecyclerViewAdapter.ViewHolder> {

    private final List<Film> mValues;
    private final OnFilmSelectedListener mListener;

    public MyFilmRecyclerViewAdapter(List<Film> films, OnFilmSelectedListener listener) {
        mValues = films;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mFilm = mValues.get(position);
        setFilmDescription(holder, mValues.get(position));
        loadImageFromUrl(holder,mValues.get(position).getAffiche());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFilmSelected(holder.mFilm);
                }
            }
        });
    }

    public void setFilmDescription(final ViewHolder holder, Film film){
        holder.mTitreView.setText(film.getTitre());
        holder.mTitreView.setSingleLine();
        holder.mTitreView.setEllipsize(TextUtils.TruncateAt.END);

        holder.mDateView.setText(film.getDate_sortie());

        holder.mGenreView.setText(film.getGenre());
    }

    public void loadImageFromUrl(final ViewHolder holder,final String url){
        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.mImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        holder.mImageView.setImageResource(R.drawable.ic_menu_camera); // TODO: add an error image
                    }
                });
// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(request);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitreView;
        public final TextView mDateView;
        public final TextView mGenreView;
        public final ImageView mImageView;
        public Film mFilm;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitreView = (TextView) view.findViewById(R.id.titreFilm);
            mDateView = (TextView) view.findViewById(R.id.dateFilm);
            mGenreView = (TextView) view.findViewById(R.id.genreFilm);
            mImageView= (ImageView) view.findViewById(R.id.affiche);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
