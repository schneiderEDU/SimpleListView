package com.schneiderchristian.simplelistview;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.schneiderchristian.simplelistview.adapters.ShoppingListAdapter;
import com.schneiderchristian.simplelistview.models.ShoppingItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
     * Generates complex demo content
     * @return ArrayList of type ShoppingItem
     */
    private ArrayList<ShoppingItem> createComplexDemoContent() {
        ArrayList<ShoppingItem> dummyList = new ArrayList<>();
        dummyList.add(new ShoppingItem("item1", "Butter", "Irische", "500g", "supermarket"));
        dummyList.add(new ShoppingItem("item2","Eier", "braun", "10 Stk.", "supermarket"));
        dummyList.add(new ShoppingItem("item3","Milch", "1,5%", "4l", "supermarket"));
        dummyList.add(new ShoppingItem("item4","Fleisch", "Geflügel", "5kg", "butcher"));
        dummyList.add(new ShoppingItem("item5","Duschgel", "KEIN Moschusduft", "2", "drugstore"));
        return dummyList;
    }
    private ShoppingItem touchedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ShoppingItem> demoContent = createComplexDemoContent();
        final ListView listViewShoppingList = findViewById(R.id.listViewShoppingList);
        final ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(this, demoContent);
        listViewShoppingList.setAdapter(shoppingListAdapter);

        final View constraintLayout = findViewById(R.id.constraintLayout);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                touchedItem = demoContent.get(position);
                Snackbar snackbar
                        = Snackbar.make(constraintLayout, touchedItem.getAmount() + " " + touchedItem.getName() + " kaufen!", Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.snackbar_shopping_item_bought, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        demoContent.remove(touchedItem);
                        shoppingListAdapter.notifyDataSetChanged();
                    }
                });
                snackbar.show();
            }
        };
        listViewShoppingList.setOnItemClickListener(onItemClickListener);
    }
}
