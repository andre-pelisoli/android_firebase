package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.view.contract.ShoppingListFragmentContract;

/**
 * Created by pelisoli on 10/05/16.
 */
public class ShoppingListPresenter implements ShoppingListFragmentContract.Presenter {

    List<ShoppingList> mShoppingList;

    Firebase mFirebase;

    ShoppingListFragmentContract.View mView;

    public ShoppingListPresenter(List<ShoppingList> shoppingList, Firebase firebase, ShoppingListFragmentContract.View view) {
        mShoppingList = shoppingList;
        mFirebase = firebase;
        mView = view;
    }

    @Override
    public void startListeningFirebase() {
        if (mFirebase != null) {
            if (mFirebase != null) {
                mFirebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<ShoppingList> shoppingLists = new ArrayList<>();

                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            ShoppingList shoppingList = child.getValue(ShoppingList.class);
                            shoppingLists.add(shoppingList);
                        }

                        if (mView != null) {
                            mView.showEntry(shoppingLists);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        }
    }

    @Override
    public void openDetailFragment() {
        mView.showDetailFragment();
    }
}
