package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.Firebase;

import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.view.contract.AddShoppingDialogContract;

/**
 * Created by pelisoli on 09/05/16.
 */
public class AddListDialogPresenter implements AddShoppingDialogContract.Presenter {
    Firebase mFirebase;

    AddShoppingDialogContract.View mView;

    public AddListDialogPresenter(Firebase firebase, AddShoppingDialogContract.View view) {
        mFirebase = firebase;
        mView = view;
    }

    @Override
    public void createEntry(ShoppingList shoppingList) {
        if (shoppingList != null) {
            Firebase newFirebaseRef = mFirebase.push();

            /* Save listsRef.push() to maintain same random Id */
            final String listId = newFirebaseRef.getKey();

            newFirebaseRef.setValue(shoppingList);

            if(mView != null){
                mView.closeDialog(shoppingList);
            }
        }

    }
}
