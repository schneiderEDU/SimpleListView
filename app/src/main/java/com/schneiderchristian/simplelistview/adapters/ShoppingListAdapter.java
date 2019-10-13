package com.schneiderchristian.simplelistview.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
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
    private final Activity activity;
    private final ArrayList<ShoppingItem> shoppingList;

    //ViewHolder als verschachtelte Klasse (nested class)
    static class ViewHolder {
        ImageView imgShoppingItem;
        TextView txtShoppingItemTitle;
        TextView txtShoppingItemDescription;
        TextView txtShoppingItemAmount;
    }

    public ShoppingListAdapter(Activity activity, ArrayList<ShoppingItem> shoppingList) {
        //Übergabe der Activity, des Layouts und der Daten an einen übergeordneten Konstruktor von ArrayAdapter
        super(activity, R.layout.shopping_item, shoppingList);
        this.activity = activity;
        this.shoppingList = shoppingList;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Zur besseren Übersicht. Natürlich könnte man auch direkt auf convertView arbeiten
        View rowView = convertView;

        //Zuweisen eines Views für rowView, falls dieser noch nicht besteht
        if(rowView == null) {
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            rowView = layoutInflater.inflate(R.layout.shopping_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imgShoppingItem = rowView.findViewById(R.id.imgShoppingItem);
            viewHolder.txtShoppingItemTitle = rowView.findViewById(R.id.txtShoppingItemTitle);
            viewHolder.txtShoppingItemDescription = rowView.findViewById(R.id.txtShoppingItemDescription);
            viewHolder.txtShoppingItemAmount = rowView.findViewById(R.id.txtShoppingItemAmount);
            //Versehen des rowViews mit einem Tag zur Identifizierung im Speicher
            rowView.setTag(viewHolder);
        }
        //holen des Views aus dem Speicher und befüllen mit Daten
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.txtShoppingItemTitle.setText(shoppingList.get(position).getName());
        viewHolder.txtShoppingItemDescription.setText(shoppingList.get(position).getDescription());
        viewHolder.txtShoppingItemAmount.setText(shoppingList.get(position).getAmount());
        String itemSource = shoppingList.get(position).getSource();
        int itemSourceRes;
        switch (itemSource) {
            case "butcher":
                itemSourceRes = R.drawable.butcher;
                break;
            case "drugstore":
                itemSourceRes = R.drawable.drugstore;
                break;
            case "supermarket":
                itemSourceRes = R.drawable.supermarket;
                break;
            default:
                itemSourceRes = R.drawable.shopping_basket;
                break;
        }
        viewHolder.imgShoppingItem.setImageResource(itemSourceRes);
        //Übergabe des Views als Listenelement an den ListView
        return rowView;
    }
}
