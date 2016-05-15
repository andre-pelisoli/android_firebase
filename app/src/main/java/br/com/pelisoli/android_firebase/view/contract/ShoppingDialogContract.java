package br.com.pelisoli.android_firebase.view.contract;

import br.com.pelisoli.android_firebase.model.ShoppingList;

/**
 * Created by pelisoli on 09/05/16.
 */
public interface ShoppingDialogContract {

    interface View{
        void showEntry(ShoppingList shoppingList);
    }

    interface Presenter{
        void createEntry(ShoppingList shoppingList);
    }


}
