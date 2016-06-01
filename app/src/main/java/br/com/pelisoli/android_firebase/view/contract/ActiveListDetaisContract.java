package br.com.pelisoli.android_firebase.view.contract;

import br.com.pelisoli.android_firebase.model.ShoppingList;

/**
 * Created by pelisoli on 31/05/16.
 */
public interface ActiveListDetaisContract {

    interface View{
        void showEditDialog(String nameList);
    }

    interface Presenter{
        void openEditDialog(ShoppingList shoppingList);
    }

}
