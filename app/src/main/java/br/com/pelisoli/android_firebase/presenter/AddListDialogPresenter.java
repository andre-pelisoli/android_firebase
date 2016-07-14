package br.com.pelisoli.android_firebase.presenter;

import com.google.firebase.database.DatabaseReference;

import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.view.contract.AddShoppingDialogContract;

/**
 * Created by pelisoli on 09/05/16.
 */
public class AddListDialogPresenter implements AddShoppingDialogContract.Presenter {
    DatabaseReference mDatabaseReference;

    AddShoppingDialogContract.View mView;

    public AddListDialogPresenter(DatabaseReference databaseReference, AddShoppingDialogContract.View view) {
        mDatabaseReference = databaseReference;
        mView = view;
    }

    @Override
    public void createEntry(ShoppingList shoppingList) {
        if (shoppingList != null) {
            DatabaseReference newDatabaseRef = mDatabaseReference.push();

            /* Save listsRef.push() to maintain same random Id */
            final String listId = newDatabaseRef.getKey();

            newDatabaseRef.setValue(shoppingList);

            if(mView != null){
                mView.closeDialog(shoppingList);
            }
        }

    }
}
