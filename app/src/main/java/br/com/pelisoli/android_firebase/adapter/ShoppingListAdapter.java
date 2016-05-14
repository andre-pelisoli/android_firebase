package br.com.pelisoli.android_firebase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.pelisoli.android_firebase.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pelisoli on 10/05/16.
 */
public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ItemHolder> {

    private List<String> itemsList;

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    public ShoppingListAdapter(List<String> itemsList, Context context) {
        this.itemsList = itemsList;
        mContext = context;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_holder, parent, false);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.mItemName.setText(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        int count = 0;

        if (itemsList != null) {
            count = itemsList.size();
        }

        return count;
    }

    public void addNewItem(String item){
        if (itemsList != null) {
            itemsList.add(item);
        }else {
            itemsList = new ArrayList<>();
            itemsList.add(item);
        }

        notifyItemInserted(itemsList.size());
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_name)
        TextView mItemName;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
