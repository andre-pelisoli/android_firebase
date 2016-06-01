package br.com.pelisoli.android_firebase.view.contract;

/**
 * Created by pelisoli on 31/05/16.
 */
public interface EditListNameContract {

    interface Presenter {
        void editListName(String oldName, String newName);
    }

}
