package br.com.pelisoli.android_firebase.view.activity;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.presenter.ActiveListDetailsPresenter;
import br.com.pelisoli.android_firebase.view.contract.ActiveListDetaisContract;
import br.com.pelisoli.android_firebase.view.dialog.EditListNameDialogFragment;
import br.com.pelisoli.android_firebase.view.dialog.RemoveListDialogFragment;

/**
 * Created by pelisoli on 31/05/16.
 */
public class ActiveListDetailsActivity extends AppCompatActivity implements ActiveListDetaisContract.View {
    private static final String LOG_TAG = ActiveListDetailsActivity.class.getSimpleName();

    private DatabaseReference mDatabaseReference;

    private String listId;

    private String mShoppingListTitle;

    private ActiveListDetailsPresenter mActiveListDetailsPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_list_details_activity);

        mActiveListDetailsPresenter = new ActiveListDetailsPresenter(this);

        initializeToolbar();

        listId = getIntent().getStringExtra("id");

        if(listId != null && !listId.isEmpty()){
            mDatabaseReference = FirebaseDatabase.getInstance().getReference();
            mDatabaseReference.child(listId);
            mActiveListDetailsPresenter.startListeningFirebase(mDatabaseReference);
        }else {
            closeActivity();
        }
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
                mActiveListDetailsPresenter.openEditDialog(mShoppingListTitle, listId);
            }
            return true;
        }

        if (id == R.id.action_remove_list) {
            mActiveListDetailsPresenter.openRemoveDialog(listId);
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
    public void showEditDialog(String title, String listId) {
        DialogFragment dialog = EditListNameDialogFragment.newInstance(title, listId);
        dialog.show(this.getSupportFragmentManager(), "EditListNameDialogFragment");
    }

    @Override
    public void showRemoveDialog(String listId) {
        DialogFragment removeListDialogFragment = RemoveListDialogFragment.newInstance(listId);
        removeListDialogFragment.show(this.getSupportFragmentManager(), "RemoveListDialogFragment");
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void showError() {

    }

    @Override
    public void updateToolbarTitle(String titleName) {
        invalidateOptionsMenu();

        mShoppingListTitle = titleName;
        setTitle(mShoppingListTitle);
    }
}
