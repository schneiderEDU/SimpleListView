package com.schneiderchristian.simplelistview.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.schneiderchristian.simplelistview.R;
import com.schneiderchristian.simplelistview.models.ShoppingItem;

import java.util.ArrayList;

public class ShoppingListAdapter extends ArrayAdapter {
    private final Activity myContext;
    private final ArrayList<ShoppingItem> myShoppingList;

    static class ViewHolder {
        public ImageView imgShoppingItem;
        public TextView txtShoppingItemTitle;
        public TextView txtShoppingItemDescription;
        public TextView txtShoppingItemAmount;
    }

    public ShoppingListAdapter(Activity context, ArrayList<ShoppingItem> shoppingList) {
        super(context, R.layout.shopping_item, shoppingList);
        this.myContext = context;
        this.myShoppingList = shoppingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if(rowView == null) {
            LayoutInflater layoutInflater = myContext.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.shopping_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imgShoppingItem = rowView.findViewById(R.id.imgShoppingItem);
            viewHolder.txtShoppingItemTitle = rowView.findViewById(R.id.txtShoppingItemTitle);
            viewHolder.txtShoppingItemDescription = rowView.findViewById(R.id.txtShoppingItemDescription);
            viewHolder.txtShoppingItemAmount = rowView.findViewById(R.id.txtShoppingItemAmount);
            rowView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.txtShoppingItemTitle.setText(myShoppingList.get(position).getName());
        viewHolder.txtShoppingItemDescription.setText(myShoppingList.get(position).getDescription());
        viewHolder.txtShoppingItemAmount.setText(myShoppingList.get(position).getAmount());
        String itemSource = myShoppingList.get(position).getSource();
        int itemSourceRes = 0;
        if(itemSource.equals("butcher")) {
            itemSourceRes = R.drawable.butcher;
        } else if(itemSource.equals("drugstore")) {
            itemSourceRes = R.drawable.drugstore;
        } else if (itemSource.equals("supermarket")) {
            itemSourceRes = R.drawable.supermarket;
        } else {
            itemSourceRes = R.drawable.shopping_basket;
        }
        viewHolder.imgShoppingItem.setImageResource(itemSourceRes);
        return rowView;
    }
}
