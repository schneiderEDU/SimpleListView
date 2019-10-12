package com.schneiderchristian.simplelistview.models;

/**
 * Class for holding data for different shopping items
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
     * @param id unique identifier for the item
     * @param name Name of the item
     * @param description Further description of the item
     * @param amount Amount of the item as number
     * @param source Source of the item (e.g. grocery store)
     */
    public ShoppingItem(String id, String name, String description, String amount, String source) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.source = source;
    }
}
