package br.com.pelisoli.android_firebase.view.contract;

/**
 * Created by pelisoli on 10/05/16.
 */
public interface ShoppingListFragmentContract {

    interface View{
        void showEntry(String entry);
    }

    interface Presenter{
        void startListeningFirebase();
    }

}
