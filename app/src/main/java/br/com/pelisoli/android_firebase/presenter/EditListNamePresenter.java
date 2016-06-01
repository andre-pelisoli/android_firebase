package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.Firebase;

import br.com.pelisoli.android_firebase.view.contract.EditListNameContract;

/**
 * Created by pelisoli on 31/05/16.
 */
public class EditListNamePresenter implements EditListNameContract.Presenter {
    Firebase mFirebaseRef;

    public EditListNamePresenter(Firebase firebaseRef) {
        mFirebaseRef = firebaseRef;
    }

    @Override
    public void editListName(String oldName, String newName) {

    }
}
