package fr.cours.centrale.rottenpotatoes.event;

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

public class MyEventViewAdapter extends RecyclerView.Adapter<MyEventViewAdapter.ViewHolder> {

    private static String TAG = MyEventViewAdapter.class.getSimpleName();
    private final List<Event> mValues;
    private final EventFragment.OnEventSelectedListener mListener;

    public MyEventViewAdapter(List<Event> events, EventFragment.OnEventSelectedListener listener) {
        mValues = events;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mEvent = mValues.get(position);
        setEventDescription(holder, mValues.get(position));
        loadImageFromUrl(holder,mValues.get(position).getAffiche());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onEventSelected(holder.mEvent);
                }
            }
        });
    }

    public void setEventDescription(final ViewHolder holder, Event event){
        holder.mTitreView.setText(event.getTitre());
        holder.mTitreView.setSingleLine();
        holder.mTitreView.setEllipsize(TextUtils.TruncateAt.END);

        holder.mTitreWrappedView.setText(event.getTitre_wrapped());

        String date = event.getDate_deb().substring(0, 10); //on enlève l'heure cette affichage
        if (event.getDate_fin() != null) date = date + " - " + event.getDate_fin().substring(0,10);
        holder.mDateView.setText(date);
    }

    public void loadImageFromUrl(final ViewHolder holder,final String url){
        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(final Bitmap bitmap) {
                        new Thread(new Runnable() {
                            public void run() {
                                holder.mImageView.post(new Runnable() {
                                    public void run() {
                                        holder.mImageView.setImageBitmap(bitmap);
                                    }
                                });
                            }
                        }).start();

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
        int ItemCount = 0;
        if (mValues != null) {
            ItemCount = mValues.size();
        }
        return ItemCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitreView;
        public final TextView mTitreWrappedView;
        public final TextView mDateView;
        public final ImageView mImageView;
        public Event mEvent;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitreView = (TextView) view.findViewById(R.id.titreEvent);
            mTitreWrappedView = (TextView) view.findViewById(R.id.titreWrappedEvent);
            mDateView = (TextView) view.findViewById(R.id.dateEvent);
            mImageView= (ImageView) view.findViewById(R.id.afficheEvent);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
