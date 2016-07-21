package br.com.pelisoli.android_firebase.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.adapter.ActiveListAdapter;
import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.presenter.ShoppingListPresenter;
import br.com.pelisoli.android_firebase.utils.Constants;
import br.com.pelisoli.android_firebase.view.activity.ActiveListDetailsActivity;
import br.com.pelisoli.android_firebase.view.contract.IList;
import br.com.pelisoli.android_firebase.view.contract.ShoppingListFragmentContract;
import br.com.pelisoli.android_firebase.view.dialog.AddListDialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pelisoli on 31/05/16.
 */
public class ShoppingListFragment extends Fragment implements ShoppingListFragmentContract.View, IList{

    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    ShoppingListPresenter mShoppingListPresenter;

    List<ShoppingList> mShoppingList;

    DatabaseReference mDatabaseReference;

    ActiveListAdapter mActiveListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabaseReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_LOCATION_ACTIVE_LIST);

        mShoppingListPresenter = new ShoppingListPresenter(mShoppingList, mDatabaseReference, this);
        mShoppingListPresenter.startListeningFirebase();

        mActiveListAdapter = new ActiveListAdapter(ShoppingList.class, R.layout.item_holder, ActiveListAdapter.ViewHolder.class, mDatabaseReference);
        mActiveListAdapter.setIList(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mActiveListAdapter);
    }

    @Override
    public void showDetailFragment(String pushId) {
        Intent intent = new Intent(getContext(), ActiveListDetailsActivity.class);
        intent.putExtra("id", pushId);
        startActivity(intent);
    }

    @Override
    public void showAddDialog() {
        DialogFragment dialog = AddListDialogFragment.newInstance();
        dialog.show(getFragmentManager(), "AddListDialogFragment");
    }

    @Override
    public void onListClicked(int position) {
        String pushId = mActiveListAdapter.getRef(position).getKey();
        mShoppingListPresenter.openDetailFragment(pushId);
    }

    @OnClick(R.id.fab)
    void openAddDialog(){
        showAddDialog();
    }
}
