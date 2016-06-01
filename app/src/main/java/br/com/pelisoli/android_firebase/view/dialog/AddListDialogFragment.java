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

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.presenter.AddListDialogPresenter;
import br.com.pelisoli.android_firebase.utils.Constants;
import br.com.pelisoli.android_firebase.view.contract.AddShoppingDialogContract;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pelisoli on 31/05/16.
 */
public class AddListDialogFragment extends DialogFragment implements AddShoppingDialogContract.View {
    @BindView(R.id.edit_text_list_name)
    EditText mEditTextListName;

    AddListDialogPresenter mAddListDialogPresenter;

    Firebase mFirebase;

    public static AddListDialogFragment newInstance() {
        AddListDialogFragment addListDialogFragment = new AddListDialogFragment();
        Bundle bundle = new Bundle();
        addListDialogFragment.setArguments(bundle);
        return addListDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_add_list, null);

        ButterKnife.bind(this, rootView);

        mFirebase = new Firebase(Constants.FIREBASE_URL);
        mAddListDialogPresenter = new AddListDialogPresenter(mFirebase, this);

        mEditTextListName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    String userEnteredName = mEditTextListName.getText().toString();
                    String owner = "Anonymous Owner";
                    ShoppingList currentList = new ShoppingList(userEnteredName, owner);

                    mAddListDialogPresenter.createEntry(currentList);
                }
                return true;
            }
        });

        builder.setView(rootView)
                .setPositiveButton(R.string.positive_button_create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String userEnteredName = mEditTextListName.getText().toString();
                        String owner = "Anonymous Owner";
                        ShoppingList currentList = new ShoppingList(userEnteredName, owner);

                        mAddListDialogPresenter.createEntry(currentList);
                    }
                });

        return builder.create();
    }

    @Override
    public void closeDialog(ShoppingList shoppingList) {
        dismiss();
    }
}
