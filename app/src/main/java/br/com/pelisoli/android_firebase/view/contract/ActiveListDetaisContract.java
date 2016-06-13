package br.com.pelisoli.android_firebase.view.contract;

import com.firebase.client.Firebase;

/**
 * Created by pelisoli on 31/05/16.
 */
public interface ActiveListDetaisContract {

    interface View{
        void showEditDialog(String title, String childId);

        void closeActivity();

        void showError();

        void updateToolbarTitle(String titleName);
    }

    interface Presenter{
        void openEditDialog(String title, String childId);

        void startListeningFirebase(Firebase rFirebase);
    }

}
