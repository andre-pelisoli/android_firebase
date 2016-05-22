package br.com.pelisoli.android_firebase.view.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.presenter.AddListDialogPresenter;
import br.com.pelisoli.android_firebase.utils.Constants;
import br.com.pelisoli.android_firebase.view.contract.ShoppingDialogContract;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adds a new shopping list
 */
public class AddListDialogFragment extends DialogFragment implements ShoppingDialogContract.View {
    @BindView(R.id.edit_text_list_name)
    EditText mEditTextListName;

    ShoppingDialogContract.Presenter mPresenter;

    /**
     * Public static constructor that creates fragment and
     * passes a bundle with data into it when adapter is created
     */
    public static AddListDialogFragment newInstance() {
        AddListDialogFragment addListDialogFragment = new AddListDialogFragment();
        Bundle bundle = new Bundle();
        addListDialogFragment.setArguments(bundle);
        return addListDialogFragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new AddListDialogPresenter(new Firebase(Constants.FIREBASE_ROOT_URL), this);
    }

    /**
     * Open the keyboard automatically when the dialog fragment is opened
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_list, null);

        ButterKnife.bind(this, rootView);

        /**
         * Call addShoppingList() when user taps "Done" keyboard action
         */
        mEditTextListName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    HashMap<String, Object> timestamp = new HashMap<>();
                    timestamp.put("date", ServerValue.TIMESTAMP);

                    ShoppingList shoppingList =
                            new ShoppingList(mEditTextListName.getText().toString(), "Temporary owner");

                    mPresenter.createEntry(shoppingList);
                }
                return true;
            }
        });

        /* Inflate and set the layout for the dialog */
        /* Pass null as the parent view because its going in the dialog layout*/
        builder.setView(rootView)
                /* Add action buttons */
                .setPositiveButton(R.string.positive_button_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        HashMap<String, Object> timestamp = new HashMap<>();
                        timestamp.put("date", ServerValue.TIMESTAMP);

                        ShoppingList shoppingList =
                                new ShoppingList(mEditTextListName.getText().toString(), "Temporary owner");

                        mPresenter.createEntry(shoppingList);
                    }
                });

        return builder.create();
    }

    @Override
    public void showEntry(ShoppingList shoppingList) {
        //TODO do something
    }
}

