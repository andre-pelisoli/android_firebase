package br.com.pelisoli.android_firebase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.pelisoli.android_firebase.R;
import br.com.pelisoli.android_firebase.model.ShoppingList;
import br.com.pelisoli.android_firebase.utils.Utils;
import br.com.pelisoli.android_firebase.view.contract.IList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pelisoli on 10/05/16.
 */
public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ItemHolder> {

    private List<ShoppingList> mShoppingLists;

    private LayoutInflater mLayoutInflater;

    private IList mIList;

    public ShoppingListAdapter(List<ShoppingList> itemsList, IList iList, Context context) {
        mShoppingLists = itemsList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mIList = iList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_holder, parent, false);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.mItemName.setText(mShoppingLists.get(position).getListName());
        holder.mItemOwner.setText(mShoppingLists.get(position).getOwner());
        holder.mItemTimestamp.setText(Utils.SIMPLE_DATE_FORMAT.format(
                new Date((mShoppingLists.get(position).getTimestampLastChangedLong()))));
    }

    @Override
    public int getItemCount() {
        int count = 0;

        if (mShoppingLists != null) {
            count = mShoppingLists.size();
        }

        return count;
    }

    public void addNewItem(ShoppingList shoppingList){
        if (shoppingList != null) {
            if (mShoppingLists != null) {
                mShoppingLists.add(shoppingList);
            }else {
                mShoppingLists = new ArrayList<>();
                mShoppingLists.add(shoppingList);
            }

            notifyItemInserted(mShoppingLists.size()-1);
        }
    }

    public void addNewList(List<ShoppingList> shoppingLists){
        mShoppingLists = shoppingLists;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.item_name)
        TextView mItemName;

        @BindView(R.id.item_owner)
        TextView mItemOwner;

        @BindView(R.id.item_timestamp)
        TextView mItemTimestamp;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mIList.onListClicked(mShoppingLists.get(getLayoutPosition()));
        }
    }

}
