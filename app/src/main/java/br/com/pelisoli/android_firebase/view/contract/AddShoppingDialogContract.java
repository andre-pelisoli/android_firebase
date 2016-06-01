package br.com.pelisoli.android_firebase.view.contract;

import br.com.pelisoli.android_firebase.model.ShoppingList;

/**
 * Created by pelisoli on 09/05/16.
 */
public interface AddShoppingDialogContract {

    interface View{
        void closeDialog(ShoppingList shoppingList);
    }

    interface Presenter{
        void createEntry(ShoppingList shoppingList);
    }


}
