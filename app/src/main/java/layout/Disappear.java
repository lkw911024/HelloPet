package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hellopet.sangji.hellopet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Disappear extends Fragment {


    public Disappear() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disappear, container, false);
    }

    public static Disappear newInstance(){

        Bundle args = new Bundle();

        Disappear fragment = new Disappear();
        fragment.setArguments(args);

        return fragment;
    }
}
