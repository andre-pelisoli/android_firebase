package br.com.pelisoli.android_firebase.view.contract;

/**
 * Created by pelisoli on 22/06/16.
 */
public interface RemoveListDialogContract {

    interface View {
        void closeDialog();
    }

    interface Presenter{
        void removeList(String listId);
    }
}
