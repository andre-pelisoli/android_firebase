package br.com.pelisoli.android_firebase.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.presenter.ShoppingListPresenter;
import br.com.pelisoli.android_firebase.adapter.ShoppingListAdapter;
import br.com.pelisoli.android_firebase.utils.Constants;
import br.com.pelisoli.android_firebase.view.contract.ShoppingListFragmentContract;
import br.com.pelisoli.android_firebase.view.dialog.AddListDialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pelisoli on 27/04/16.
 */
public class ShoppingListFragment extends Fragment implements ShoppingListFragmentContract.View {

    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    ShoppingListPresenter mShoppingListPresenter;

    ShoppingListAdapter mShoppingListAdapter;

    List<ShoppingList> itemsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mShoppingListAdapter = new ShoppingListAdapter(itemsList, getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mShoppingListAdapter);

        mShoppingListPresenter = new ShoppingListPresenter(itemsList,
                new Firebase(Constants.FIREBASE_ROOT_URL),
                this);

        mShoppingListPresenter.startListeningFirebase();
    }

    @OnClick(R.id.fab)
    void floatingButtonClick(){
        AddListDialogFragment.newInstance().show(getFragmentManager(), "addListDialogFragment");
    }

    @Override
    public void showEntry(ShoppingList entry) {
        mShoppingListAdapter.addNewItem(entry);
    }
}
