package fr.cours.centrale.rottenpotatoes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;


//TODO: spinner handler

public class ParametersFragment extends Fragment {

    private final static String TAG= "FRAGMENT PARAMETERS";
    private OnParametersListener mListener;

    public ParametersFragment() {
        // Required empty public constructor
    }

    public static ParametersFragment newInstance() {
        ParametersFragment fragment = new ParametersFragment();
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
        View view= inflater.inflate(R.layout.fragment_parameters, container, false);

        startCheckboxes(view);
        startSpinners(view);

        return view;
    }

    public void onCheckboxClicked(View view) {
        //if (mListener != null) {
        //    mListener.onParametersInteraction(uri);
        //}
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBoxCinema1:
                if (checked)
                    MainActivity.listCinemaSelected.add(1);
                else
                    MainActivity.listCinemaSelected.remove(1);
                break;
            case R.id.checkBoxCinema2:
                if (checked)
                    MainActivity.listCinemaSelected.add(2);
                else
                    MainActivity.listCinemaSelected.remove(2);
                break;
            case R.id.checkBoxCinema3:
                if (checked)
                    MainActivity.listCinemaSelected.add(3);
                else
                    MainActivity.listCinemaSelected.remove(3);
                break;
            case R.id.checkBoxNationality1:
                if (checked)
                    MainActivity.listNationalitySelected.add(1);
                else
                    MainActivity.listNationalitySelected.remove(1);
                break;
            case R.id.checkBoxNationality2:
                if (checked)
                    MainActivity.listNationalitySelected.add(2);
                else
                    MainActivity.listNationalitySelected.remove(2);
                break;
            case R.id.checkBoxNationality3:
                if (checked)
                    MainActivity.listNationalitySelected.add(3);
                else
                    MainActivity.listNationalitySelected.remove(3);
                break;
            case R.id.checkBoxCategorie1:
                if (checked)
                    MainActivity.listCategorieSelected.add(1);
                else
                    MainActivity.listCategorieSelected.remove(1);
                break;
            case R.id.checkBoxCategorie2:
                if (checked)
                    MainActivity.listCategorieSelected.add(2);
                else
                    MainActivity.listCategorieSelected.remove(2);
                break;
            case R.id.checkBoxCategorie3:
                if (checked)
                    MainActivity.listCategorieSelected.add(3);
                else
                    MainActivity.listCategorieSelected.remove(3);
                break;
            case R.id.checkBoxCategorie4:
                if (checked)
                    MainActivity.listCategorieSelected.add(4);
                else
                    MainActivity.listCategorieSelected.remove(4);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnParametersListener) {
            mListener = (OnParametersListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnParametersListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnParametersListener {
        void onParametersInteraction(Uri uri);
    }

    public void startCheckboxes(View view){
        final CheckBox checkBoxCinema1 = (CheckBox) view.findViewById(R.id.checkBoxCinema1);
        final CheckBox checkBoxCinema2 = (CheckBox) view.findViewById(R.id.checkBoxCinema2);
        final CheckBox checkBoxCinema3 = (CheckBox) view.findViewById(R.id.checkBoxCinema3);
        final CheckBox checkBoxLangue1 = (CheckBox) view.findViewById(R.id.checkBoxNationality1);
        final CheckBox checkBoxLangue2 = (CheckBox) view.findViewById(R.id.checkBoxNationality2);
        final CheckBox checkBoxLangue3 = (CheckBox) view.findViewById(R.id.checkBoxNationality3);
        final CheckBox checkBoxCategorie1 = (CheckBox) view.findViewById(R.id.checkBoxCategorie1);
        final CheckBox checkBoxCategorie2 = (CheckBox) view.findViewById(R.id.checkBoxCategorie2);
        final CheckBox checkBoxCategorie3 = (CheckBox) view.findViewById(R.id.checkBoxCategorie3);
        final CheckBox checkBoxCategorie4 = (CheckBox) view.findViewById(R.id.checkBoxCategorie4);

        if (!MainActivity.listCinemaSelected.contains(1))
            checkBoxCinema1.setChecked(false);
        else
            checkBoxCinema1.setChecked(true);
        checkBoxCinema1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listCinemaSelected.contains(2))
            checkBoxCinema2.setChecked(false);
        else
            checkBoxCinema2.setChecked(true);
        checkBoxCinema2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listCinemaSelected.contains(3))
            checkBoxCinema3.setChecked(false);
        else
            checkBoxCinema3.setChecked(true);
        checkBoxCinema3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listNationalitySelected.contains(1))
            checkBoxLangue1.setChecked(false);
        else
            checkBoxLangue1.setChecked(true);
        checkBoxLangue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listNationalitySelected.contains(2))
            checkBoxLangue2.setChecked(false);
        else
            checkBoxLangue2.setChecked(true);
        checkBoxLangue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listNationalitySelected.contains(3))
            checkBoxLangue3.setChecked(false);
        else
            checkBoxLangue3.setChecked(true);
        checkBoxLangue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listCategorieSelected.contains(1))
            checkBoxCategorie1.setChecked(false);
        else
            checkBoxCategorie1.setChecked(true);
        checkBoxCategorie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listCategorieSelected.contains(2))
            checkBoxCategorie2.setChecked(false);
        else
            checkBoxCategorie2.setChecked(true);
        checkBoxCategorie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listCategorieSelected.contains(3))
            checkBoxCategorie3.setChecked(false);
        else
            checkBoxCategorie3.setChecked(true);
        checkBoxCategorie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });

        if (!MainActivity.listCategorieSelected.contains(4))
            checkBoxCategorie4.setChecked(false);
        else
            checkBoxCategorie4.setChecked(true);
        checkBoxCategorie4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckboxClicked(v);
            }
        });
    }

    public void startSpinners(View view){
        Spinner troisDSpinner = (Spinner) view.findViewById(R.id.troisD_spinner);
        Spinner malEntendantSpinner = (Spinner) view.findViewById(R.id.malEntendantSpinner);
        Spinner handicapeSpinner = (Spinner) view.findViewById(R.id.handicapeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.choice_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        troisDSpinner.setAdapter(adapter);
        troisDSpinner.setSelection(MainActivity.user_choice_troisd);
        troisDSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.user_choice_troisd = position;
                Log.d(TAG, String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        malEntendantSpinner.setAdapter(adapter);
        malEntendantSpinner.setSelection(MainActivity.user_choice_malentendant);
        malEntendantSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.user_choice_malentendant = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        handicapeSpinner.setAdapter(adapter);
        handicapeSpinner.setSelection(MainActivity.user_choice_handicape);
        handicapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.user_choice_handicape = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}

