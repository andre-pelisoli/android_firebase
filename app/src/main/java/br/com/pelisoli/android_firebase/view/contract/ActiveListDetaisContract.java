package br.com.pelisoli.android_firebase.view.contract;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by pelisoli on 31/05/16.
 */
public interface ActiveListDetaisContract {

    interface View{
        void showEditDialog(String title, String listId);

        void showRemoveDialog(String listId);

        void closeActivity();

        void showError();

        void updateToolbarTitle(String titleName);
    }

    interface Presenter{
        void openEditDialog(String title, String listId);

        void openRemoveDialog(String listId);

        void startListeningFirebase(DatabaseReference rFirebase);
    }

}
