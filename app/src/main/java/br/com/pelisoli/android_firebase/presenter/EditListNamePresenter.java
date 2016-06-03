package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;

import br.com.pelisoli.android_firebase.utils.Constants;
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

        if(oldName != null && newName != null){
            if(!oldName.contentEquals(newName)){
                if (mFirebaseRef != null) {
                    HashMap<String, Object> updateObject = new HashMap<>();
                    updateObject.put(Constants.FIREBASE_PROPERTY_LIST_NAME, newName);

                    HashMap<String, Object> changedTimestampMap = new HashMap<>();
                    changedTimestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

                    updateObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, changedTimestampMap);

                    mFirebaseRef.updateChildren(updateObject);
                }
            }
        }

    }
}
