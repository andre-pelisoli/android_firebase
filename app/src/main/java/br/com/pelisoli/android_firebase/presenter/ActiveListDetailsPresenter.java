package br.com.pelisoli.android_firebase.presenter;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
    public void openEditDialog(String title, String childId) {
        if(!isViewNull(mView)){
            mView.showEditDialog(title, childId);
        }
    }

    @Override
    public void startListeningFirebase(Firebase refFirebase) {
        if (refFirebase != null) {
            refFirebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    ShoppingList shoppingList = snapshot.getValue(ShoppingList.class);

                    if (shoppingList == null) {
                        if(!isViewNull(mView)){
                            mView.closeActivity();
                        }
                    }else{
                        if(!isViewNull(mView)){
                            mView.updateToolbarTitle(shoppingList.getListName());
                        }
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    if(!isViewNull(mView)){
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
