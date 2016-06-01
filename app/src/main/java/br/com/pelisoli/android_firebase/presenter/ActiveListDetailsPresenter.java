package br.com.pelisoli.android_firebase.presenter;

import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.view.contract.ActiveListDetaisContract;

/**
 * Created by pelisoli on 31/05/16.
 */
public class ActiveListDetailsPresenter implements ActiveListDetaisContract.Presenter {

    ActiveListDetaisContract.View mView;

    public ActiveListDetailsPresenter(ActiveListDetaisContract.View view) {
        mView = view;
    }

    @Override
    public void openEditDialog(ShoppingList shoppingList) {
        String listName = "";

        if (mView != null) {
            if (shoppingList != null) {
                if (shoppingList.getListName() != null) {
                    listName = shoppingList.getListName();
                }
            }

            mView.showEditDialog(listName);
        }
    }
}
