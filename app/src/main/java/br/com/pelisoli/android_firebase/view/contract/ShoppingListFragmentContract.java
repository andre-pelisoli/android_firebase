package br.com.pelisoli.android_firebase.view.contract;

import br.com.pelisoli.android_firebase.model.ShoppingList;

/**
 * Created by pelisoli on 10/05/16.
 */
public interface ShoppingListFragmentContract {

    interface View{
        void showEntry(ShoppingList entry);

        void showDetailFragment();

        void showAddDialog();
    }

    interface Presenter{
        void startListeningFirebase();

        void openDetailFragment();
    }

}
