package br.com.pelisoli.android_firebase.presenter;

import com.google.firebase.database.DatabaseReference;

import br.com.pelisoli.android_firebase.view.contract.RemoveListDialogContract;

public class RemoveListDialogPresenter implements RemoveListDialogContract.Presenter {
    DatabaseReference mDatabaseRef;

    RemoveListDialogContract.View mView;

    public RemoveListDialogPresenter(DatabaseReference databaseRef, RemoveListDialogContract.View view) {
        mDatabaseRef = databaseRef;
        mView = view;
    }

    @Override
    public void removeList(String listId) {
        if (mDatabaseRef != null) {
            mDatabaseRef.child(listId).removeValue();
        }

        if (mView != null) {
            mView.closeDialog();
        }
    }
}
