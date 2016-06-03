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
import br.com.pelisoli.android_firebase.presenter.EditListNamePresenter;
import br.com.pelisoli.android_firebase.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditListNameDialogFragment extends DialogFragment {

    @BindView(R.id.edit_text_list_dialog)
    EditText mEdtListName;

    private Firebase mFirebase;

    String currentName = "";


    private EditListNamePresenter mEditListNamePresenter;

    public static EditListNameDialogFragment newInstance(String nameList) {
        EditListNameDialogFragment editListNameDialogFragment = new EditListNameDialogFragment();

        if (nameList != null) {
            Bundle bundle = new Bundle();
            bundle.putString("listName", nameList);
            editListNameDialogFragment.setArguments(bundle);
        }

        return editListNameDialogFragment;
    }

    public static EditListNameDialogFragment newInstance() {
        EditListNameDialogFragment editListNameDialogFragment = new EditListNameDialogFragment();
        Bundle bundle = new Bundle();
        editListNameDialogFragment.setArguments(bundle);
        return editListNameDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        mFirebase = new Firebase(Constants.FIREBASE_URL_ACTIVE_LIST);
        mEditListNamePresenter = new EditListNamePresenter(mFirebase);

        Bundle bundle = getArguments();
        currentName = bundle.getString("listName");

        mEdtListName.setText(currentName);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /* Use the Builder class for convenient dialog construction */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog);
        /* Get the layout inflater */
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.dialog_edit_list_name, null);

        ButterKnife.bind(this, rootView);

        mEdtListName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                }

                return true;
            }
        });


        builder.setView(rootView)
                .setPositiveButton(R.string.positive_button_edit_item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (mEditListNamePresenter != null) {
                            mEditListNamePresenter.editListName(currentName , mEdtListName.getText().toString());
                        }
                    }
                });

        builder.setView(rootView)
                .setNegativeButton(R.string.negative_button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });

        return builder.create();
    }
} 