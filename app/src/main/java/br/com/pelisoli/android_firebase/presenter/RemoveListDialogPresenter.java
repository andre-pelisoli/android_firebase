package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.Firebase;

import br.com.pelisoli.android_firebase.view.contract.RemoveListDialogContract;

public class RemoveListDialogPresenter implements RemoveListDialogContract.Presenter {
    Firebase mFirebase;

    RemoveListDialogContract.View mView;

    public RemoveListDialogPresenter(Firebase firebase, RemoveListDialogContract.View view) {
        mFirebase = firebase;
        mView = view;
    }

    @Override
    public void removeList(String listId) {
        if (mFirebase != null) {
            mFirebase.child(listId).removeValue();
        }

        if (mView != null) {
            mView.closeDialog();
        }
    }
}
