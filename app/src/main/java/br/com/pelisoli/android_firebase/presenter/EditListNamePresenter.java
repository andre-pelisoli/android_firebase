package br.com.pelisoli.android_firebase.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

import br.com.pelisoli.android_firebase.utils.Constants;
import br.com.pelisoli.android_firebase.view.contract.EditListNameContract;

/**
 * Created by pelisoli on 31/05/16.
 */
public class EditListNamePresenter implements EditListNameContract.Presenter {
    DatabaseReference mDatabaseReference;

    public EditListNamePresenter(DatabaseReference databaseRef) {
        mDatabaseReference = databaseRef;
    }

    @Override
    public void editListName(String oldName, String newName) {

        if(oldName != null && newName != null){
            if(!oldName.contentEquals(newName)){
                if (mDatabaseReference != null) {
                    HashMap<String, Object> updateObject = new HashMap<>();
                    HashMap<String, Object> changedTimestampMap = new HashMap<>();

                    changedTimestampMap.put(Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, ServerValue.TIMESTAMP);
                    updateObject.put(Constants.FIREBASE_PROPERTY_LIST_NAME, newName);

                    updateObject.put(Constants.FIREBASE_PROPERTY_TIMESTAMP_LAST_CHANGED, changedTimestampMap);
                    mDatabaseReference.updateChildren(updateObject);
                }
            }
        }

    }
}
