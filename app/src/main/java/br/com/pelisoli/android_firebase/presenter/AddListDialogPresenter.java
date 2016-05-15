package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.Firebase;

import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.view.contract.ShoppingDialogContract;

/**
 * Created by pelisoli on 09/05/16.
 */
public class AddListDialogPresenter implements ShoppingDialogContract.Presenter {
    Firebase mFirebase;

    ShoppingDialogContract.View mView;

    public AddListDialogPresenter(Firebase firebase, ShoppingDialogContract.View view) {
        mFirebase = firebase;
        mView = view;
    }

    @Override
    public void createEntry(ShoppingList shoppingList) {
        mFirebase.child("activeList").setValue(shoppingList);

        if(mView != null){
            mView.showEntry(shoppingList);
        }
    }
}
