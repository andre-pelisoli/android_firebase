package br.com.pelisoli.android_firebase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.view.contract.IList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveListAdapter extends FirebaseRecyclerAdapter<ShoppingList, ActiveListAdapter.ViewHolder> {

    private IList mIList;

    public ActiveListAdapter(Class<ShoppingList> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, DatabaseReference ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_holder, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ShoppingList shoppingList = getItem(position);
        holder.mItemName.setText(shoppingList.getListName());
        holder.mItemOwner.setText(shoppingList.getOwner());
//        holder.mItemTimestamp.setText(Utils.SIMPLE_DATE_FORMAT.format(
//                new Date((shoppingList.getTimestampLastChangedLong()))));
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, ShoppingList model, int position) {

    }

    public void setIList(IList IList) {
        mIList = IList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.item_name)
        TextView mItemName;

        @BindView(R.id.item_owner)
        TextView mItemOwner;

        @BindView(R.id.item_timestamp)
        TextView mItemTimestamp;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mIList != null) {
                mIList.onListClicked(getLayoutPosition());
            }
        }
    }

}