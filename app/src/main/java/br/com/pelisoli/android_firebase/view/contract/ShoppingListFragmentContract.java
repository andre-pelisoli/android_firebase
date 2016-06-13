package br.com.pelisoli.android_firebase.view.contract;

/**
 * Created by pelisoli on 10/05/16.
 */
public interface ShoppingListFragmentContract {

    interface View{

        void showDetailFragment(String pushId);

        void showAddDialog();
    }

    interface Presenter{
        void startListeningFirebase();

        void openDetailFragment(String pushId);
    }

}
