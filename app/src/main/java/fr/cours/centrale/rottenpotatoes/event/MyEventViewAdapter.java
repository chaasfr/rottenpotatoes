package fr.cours.centrale.rottenpotatoes.event;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.cours.centrale.rottenpotatoes.R;


public class MyEventViewAdapter extends RecyclerView.Adapter<MyEventViewAdapter.ViewHolder> {

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
        holder.mIdView.setText(mValues.get(position).getTitre_wrapped());
        holder.mContentView.setText(mValues.get(position).getSoustitre());

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

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Event mEvent;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
