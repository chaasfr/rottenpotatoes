package fr.cours.centrale.rottenpotatoes.film;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.cours.centrale.rottenpotatoes.ImageListAdapter;
import fr.cours.centrale.rottenpotatoes.MainActivity;
import fr.cours.centrale.rottenpotatoes.R;

public class FilmSelectedFragment extends Fragment {
    private final static String TAG= "FRAGMENT PARAMETERS";
    private OnFilmSelectedFragmentInteractionListener mListener;

    public TextView mTitreView;
    public TextView mDateView;
    public TextView mRealisateurView;
    public TextView mParticipantsView;
    public TextView mDescriptionView;
    public List<String> listImages;
    public FilmSelectedFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FilmSelectedFragment newInstance(String param1, String param2) {
        FilmSelectedFragment fragment = new FilmSelectedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MainActivity.filmSelected.getMedias() != null) {
            List<Film.Medias> medias = MainActivity.filmSelected.getMedias();
            listImages = new ArrayList<String>();
            for(int i=0;i<medias.size();i++) {
                listImages.add(medias.get(i).getPath());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_film_selected, container, false);
        HorizontalGridView gridView = (HorizontalGridView) view.findViewById(R.id.grid_view);

        mTitreView = (TextView) view.findViewById(R.id.FSTitle);
        mDateView = (TextView) view.findViewById(R.id.FSDate);
        mRealisateurView = (TextView) view.findViewById(R.id.FSRealisateur);
        mParticipantsView = (TextView) view.findViewById(R.id.FSActeurs);
        mDescriptionView = (TextView) view.findViewById(R.id.FSDescription);

        mTitreView.setText(MainActivity.filmSelected.getTitre());
        mDateView.setText(MainActivity.filmSelected.getDate_sortie());
        mRealisateurView.setText(MainActivity.filmSelected.getRealisateur());
        mParticipantsView.setText(MainActivity.filmSelected.getParticipants());
        mDescriptionView.setText(MainActivity.filmSelected.getSynopsis());

        gridView.setAdapter(new ImageListAdapter(view.getContext()));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.OnFilmSelectedFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFilmSelectedFragmentInteractionListener) {
            mListener = (OnFilmSelectedFragmentInteractionListener) context;
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
    public interface OnFilmSelectedFragmentInteractionListener {
        void OnFilmSelectedFragmentInteraction(Uri uri);
    }
}
