package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.johan.navigator.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class formulario2 extends Fragment {



    private View view;

    public formulario2() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_formulario2, container, false);

        return view;
    }




}
