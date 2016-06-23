package br.com.pelisoli.android_firebase.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.firebase.client.Firebase;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.presenter.RemoveListDialogPresenter;
import br.com.pelisoli.android_firebase.utils.Constants;
import br.com.pelisoli.android_firebase.view.contract.RemoveListDialogContract;

public class RemoveListDialogFragment extends DialogFragment implements RemoveListDialogContract.View{
    RemoveListDialogContract.Presenter mPresenter;

    String mListId;

    final static String LOG_TAG = RemoveListDialogFragment.class.getSimpleName();

    /**
     * Public static constructor that creates fragment and passes a bundle with data into it when adapter is created
     */
    public static RemoveListDialogFragment newInstance(String listId) {
        RemoveListDialogFragment removeListDialogFragment = new RemoveListDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_LIST_ID, listId);
        removeListDialogFragment.setArguments(bundle);
        return removeListDialogFragment;
    }

    /**
     * Initialize instance variables with data from bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListId = getArguments().getString(Constants.KEY_LIST_ID);

        Firebase mFirebase = new Firebase(Constants.FIREBASE_URL_ACTIVE_LIST);
        mPresenter = new RemoveListDialogPresenter(mFirebase, this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomTheme_Dialog)
                .setTitle(getActivity().getResources().getString(R.string.action_remove_list))
                .setMessage(getString(R.string.dialog_message_are_you_sure_remove_list))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.removeList(mListId);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        closeDialog();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        return builder.create();
    }

    @Override
    public void closeDialog() {
        dismiss();
    }
}