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
     * Generiert Testdaten
     * @return ArrayList<ShoppingItem> mit Objekten aus Testdaten
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
    //Instanz eines ShoppingItems, wird für die Snackbar und die Löschfunktion benötigt
    private ShoppingItem touchedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ArrayList mit den generierten Testdaten
        final ArrayList<ShoppingItem> demoContent = createComplexDemoContent();
        //ListView aus dem Layout holen
        final ListView listViewShoppingList = findViewById(R.id.listViewShoppingList);
        //erstellen einer Adapter-Instanz, Übergabe der aktuellen Activity mittels this-Pointer und der Testdaten-Liste
        final ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(this, demoContent);
        //Zuweisen des Adapters zum ListView
        listViewShoppingList.setAdapter(shoppingListAdapter);

        //Umgebendes ConstraintLayout (quasi ganzes Content-Fenster) aus dem Layout holen, wird für die Platzierung der Snackbar benötigt
        final View constraintLayout = findViewById(R.id.constraintLayout);
        //Instanziierung eines OnItemClickListeners für das Behandeln der Klicks auf einen Listeneintrag benötigt (Methode inkl. Override-Funktionskopf wird generiert)
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //Holen des kompletten Objekts der geklickten Reihe im ListView aus der Testdaten-Liste
                touchedItem = demoContent.get(position);
                //Instanziierung eines Snackbar-Objekts auf dem ConstraintLayout mit Daten aus dem zuvor aus der ArrayList geholten Objekt touchedItem
                Snackbar snackbar
                        = Snackbar.make(constraintLayout, touchedItem.getAmount() + " " + touchedItem.getName() + " kaufen!", Snackbar.LENGTH_LONG);
                /**
                 * Zuweisen einer anonymen Instanz eines OnClickListeners zur Snackbar.
                 * Dadurch entsteht ein Button mit der Aufschrift GEKAUFT! auf der Snackbar.
                 * Beim Klick auf diesen Button wird die onClick()-Methode aufgeführt, die das Objekt aus der Testdaten-Liste entfernt.
                 * Dies entfernt allerdings NICHT die Daten aus dem ListView.
                 * Dazu muss noch der Adapter mittels seiner notifyDataSetChanged()-Methode informiert werden, der den ListView dann entsprechend "neu"zusammenbaut"
                 */
                snackbar.setAction(R.string.snackbar_shopping_item_bought, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        demoContent.remove(touchedItem);
                        shoppingListAdapter.notifyDataSetChanged();
                    }
                });
                //Snackbar anzeigen
                snackbar.show();
            }
        };
        //Zuweisen des OnItemClickListeners zum ListView
        listViewShoppingList.setOnItemClickListener(onItemClickListener);
    }
}
