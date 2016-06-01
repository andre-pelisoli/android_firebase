package br.com.pelisoli.android_firebase.view.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.presenter.ActiveListDetailsPresenter;
import br.com.pelisoli.android_firebase.utils.Constants;
import br.com.pelisoli.android_firebase.view.contract.ActiveListDetaisContract;
import br.com.pelisoli.android_firebase.view.dialog.EditListNameDialogFragment;

/**
 * Created by pelisoli on 31/05/16.
 */
public class ActiveListDetailsActivity extends AppCompatActivity implements ActiveListDetaisContract.View {
    private static final String LOG_TAG = ActiveListDetailsActivity.class.getSimpleName();

    private Firebase mActiveListRef;

    private ShoppingList mShoppingList;

    private ActiveListDetailsPresenter mActiveListDetailsPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_list_details_activity);

        mActiveListRef = new Firebase(Constants.FIREBASE_URL_ACTIVE_LIST);
        mActiveListDetailsPresenter = new ActiveListDetailsPresenter(this);

        initializeToolbar();

        mActiveListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                ShoppingList shoppingList = snapshot.getValue(ShoppingList.class);

                if (shoppingList == null) {
                    finish();
                    return;
                }
                mShoppingList = shoppingList;

                invalidateOptionsMenu();

                setTitle(shoppingList.getListName());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG,
                        getString(R.string.log_error_the_read_failed) +
                                firebaseError.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_details, menu);

        MenuItem remove = menu.findItem(R.id.action_remove_list);
        MenuItem edit = menu.findItem(R.id.action_edit_list_name);
        MenuItem share = menu.findItem(R.id.action_share_list);
        MenuItem archive = menu.findItem(R.id.action_archive);

        remove.setVisible(true);
        edit.setVisible(true);
        share.setVisible(false);
        archive.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        if (id == R.id.action_edit_list_name) {
            if (mActiveListDetailsPresenter != null) {
                mActiveListDetailsPresenter.openEditDialog(mShoppingList);
            }
            return true;
        }

        if (id == R.id.action_remove_list) {
            return true;
        }


        if (id == R.id.action_share_list) {
            return true;
        }

        if (id == R.id.action_archive) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
    }

    @Override
    public void showEditDialog(String nameList) {
        DialogFragment dialog = EditListNameDialogFragment.newInstance(nameList);
        dialog.show(this.getSupportFragmentManager(), "EditListNameDialogFragment");
    }
}
