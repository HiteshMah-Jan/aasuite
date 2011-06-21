package ui;

import component.TabPanelForm;

public class InventoryForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Inventory";
    }

    @Override
    public String tabs() {
        return "ProductEmployeeSupply,Inventory";
    }

}
