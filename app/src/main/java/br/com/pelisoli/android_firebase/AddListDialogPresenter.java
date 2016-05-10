package br.com.pelisoli.android_firebase;

import com.firebase.client.Firebase;

import br.com.pelisoli.android_firebase.view.ShoppingDialogContract;

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
    public void createEntry(String name) {
        mFirebase.child("listName").setValue(name);

        if(mView != null){
            mView.showEntry(name);
        }
    }
}
