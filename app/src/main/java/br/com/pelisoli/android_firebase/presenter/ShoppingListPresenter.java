package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
        Firebase firebaseRef;

        if (mFirebase != null) {
            firebaseRef = mFirebase.child("activeList");

            if (firebaseRef != null) {
                firebaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (mView != null) {
                            ShoppingList shoppingItem = dataSnapshot.getValue(ShoppingList.class);

                            if(!itemExists(shoppingItem)) {
                                mView.showEntry(shoppingItem);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        }
    }

    private boolean itemExists(ShoppingList shoppingItem){
        boolean status = false;

        if (mShoppingList != null) {
            for (ShoppingList item : mShoppingList) {
                if(item.getId().toString().equals(shoppingItem.getId().toString())){
                    status = true;
                    break;
                }
            }
        }

        return status;
    }
}
