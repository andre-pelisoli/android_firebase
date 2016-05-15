package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.view.contract.ShoppingListFragmentContract;

/**
 * Created by pelisoli on 10/05/16.
 */
public class ShoppingListPresenter implements ShoppingListFragmentContract.Presenter {

    Firebase mFirebase;

    ShoppingListFragmentContract.View mView;

    public ShoppingListPresenter(Firebase firebase, ShoppingListFragmentContract.View view) {
        mFirebase = firebase;
        mView = view;
    }

    @Override
    public void startListeningFirebase() {
        Firebase firebaseRef;

        if (mFirebase != null) {
            firebaseRef = mFirebase.child("activeList");

            if (firebaseRef != null) {
                firebaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (mView != null) {
                            mView.showEntry( dataSnapshot.getValue(ShoppingList.class));
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        }
    }
}
