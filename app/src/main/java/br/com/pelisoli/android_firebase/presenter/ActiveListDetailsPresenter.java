package br.com.pelisoli.android_firebase.presenter;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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
    public void openEditDialog(String title, String listId) {
        if(!isViewNull(mView)){
            mView.showEditDialog(title, listId);
        }
    }

    @Override
    public void openRemoveDialog(String listId) {
        if(!isViewNull(mView)){
            mView.showRemoveDialog(listId);
        }
    }

    @Override
    public void startListeningFirebase(DatabaseReference refFirebase) {
        if (refFirebase != null) {
            refFirebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ShoppingList shoppingList = dataSnapshot.getValue(ShoppingList.class);

                    if (shoppingList == null) {
                        if (!isViewNull(mView)) {
                            mView.closeActivity();
                        }
                    } else {
                        if (!isViewNull(mView)) {
                            mView.updateToolbarTitle(shoppingList.getListName());
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    if (!isViewNull(mView)) {
                        mView.showError();
                    }
                }
            });
        }
    }


    private boolean isViewNull(ActiveListDetaisContract.View view){
        boolean status = false;

        if(mView == null){
            status = true;
        }

        return status;
    }
}
