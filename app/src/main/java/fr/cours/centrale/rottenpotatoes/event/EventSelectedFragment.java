package fr.cours.centrale.rottenpotatoes.event;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.cours.centrale.rottenpotatoes.MainActivity;
import fr.cours.centrale.rottenpotatoes.R;

public class EventSelectedFragment extends Fragment {
    private final static String TAG= "FRAGMENT PARAMETERS";
    private OnEventSelectedFragmentInteractionListener mListener;

    public TextView mTitreView;
    public TextView mDateView;
    public TextView mRealisateurView;
    public TextView mParticipantsView;
    public TextView mDescriptionView;
    public EventSelectedFragment() {
        // Required empty public constructor
    }

    public static EventSelectedFragment newInstance(String param1, String param2) {
        EventSelectedFragment fragment = new EventSelectedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_event_selected, container, false);

        mTitreView = (TextView) view.findViewById(R.id.ESTitle);
        mDateView = (TextView) view.findViewById(R.id.ESDate);
        mRealisateurView = (TextView) view.findViewById(R.id.ESRealisateur);
        mParticipantsView = (TextView) view.findViewById(R.id.ESActeurs);
        mDescriptionView = (TextView) view.findViewById(R.id.ESDescription);

        mTitreView.setText(MainActivity.eventSelected.getTitre());
        mDateView.setText("Du " + MainActivity.eventSelected.getDate_deb() + " au "+ MainActivity.eventSelected.getDate_fin() + "\n");
        mRealisateurView.setText("Contact : " + MainActivity.eventSelected.getContact() + "\n");
        mParticipantsView.setText("Heure : " + MainActivity.eventSelected.getHeure() + "\n");
        mDescriptionView.setText("Synopsis:\n" + MainActivity.eventSelected.getDescription() + "\n");


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.OnEventSelectedFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEventSelectedFragmentInteractionListener) {
            mListener = (OnEventSelectedFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnEventSelectedFragmentInteractionListener {
        void OnEventSelectedFragmentInteraction(Uri uri);
    }
}
