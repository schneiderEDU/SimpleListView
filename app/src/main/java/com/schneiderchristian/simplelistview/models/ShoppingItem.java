package com.schneiderchristian.simplelistview.models;

/**
 * Klasse für die Datenhaltung der einzelnen ShoppingItem-Objekte
 */
public class ShoppingItem {
    private String id;
    private String name;
    private String description;
    private String amount;
    private String source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @param id Eindeutige ID für ein Objekt, wird später automatisch gehandled
     * @param name Name des Artikels der gekauft werden soll
     * @param description weitere Informationen zum Artikel
     * @param amount Menge des Artikels inkl. Einheit (Stk., l, kg,...)
     * @param source Herkunft des Artikels, implementiert sind butcher, drugstore, supermarket und ein default-Wert für alle anderen Fälle
     */
    public ShoppingItem(String id, String name, String description, String amount, String source) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.source = source;
    }
}
