package br.com.pelisoli.android_firebase.view.contract;

/**
 * Created by pelisoli on 09/05/16.
 */
public interface ShoppingDialogContract {

    interface View{
        void showEntry(String name);
    }

    interface Presenter{
        void createEntry(String name);
    }


}
