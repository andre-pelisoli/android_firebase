package br.com.pelisoli.android_firebase.presenter;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.view.contract.ShoppingListFragmentContract;

/**
 * Created by pelisoli on 10/05/16.
 */
public class ShoppingListPresenter implements ShoppingListFragmentContract.Presenter {

    List<ShoppingList> mShoppingList;

    DatabaseReference mDatabaseReference;

    ShoppingListFragmentContract.View mView;

    public ShoppingListPresenter(List<ShoppingList> shoppingList, DatabaseReference databaseRef, ShoppingListFragmentContract.View view) {
        mShoppingList = shoppingList;
        mDatabaseReference = databaseRef;
        mView = view;
    }

    @Override
    public void startListeningFirebase() {
        if (mDatabaseReference != null) {
            if (mDatabaseReference != null) {
                mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<ShoppingList> shoppingLists = new ArrayList<>();

                        for (DataSnapshot child: dataSnapshot.getChildren()) {
                            ShoppingList shoppingList = child.getValue(ShoppingList.class);
                            shoppingLists.add(shoppingList);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    @Override
    public void openDetailFragment(String pushId) {
        mView.showDetailFragment(pushId);
    }
}
