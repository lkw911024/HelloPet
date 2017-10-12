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
public class Protect extends Fragment {


    public Protect() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_protect, container, false);
    }

    public static Protect newInstance(){

        Bundle args = new Bundle();

        Protect fragment = new Protect();
        fragment.setArguments(args);

        return fragment;
    }
}
