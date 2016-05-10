package br.com.pelisoli.android_firebase.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.pelisoli.android_firebase.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pelisoli on 27/04/16.
 */
public class ShoppingListFragment extends Fragment {

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddListDialogFragment.newInstance().show(getFragmentManager(), "addListDialogFragment");
            }
        });
    }
}
