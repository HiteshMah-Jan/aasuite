/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import service.util.AbstractIBean;

/**
 *
 * @author Entokwaa
 */
public class DisplayImpl extends AbstractIBean {
    public String name;
    public String label;
    public String tooltip;
    public String button;
    public String type;
    public int labelWidth;
    public int width;
    public int height;
    public String defaultValue;
    public boolean upCase;
    public String sqlCombo;
    public String modelCombo;
    public String linkto;
    public String linktoBean;
    public String linktoColumns;
    public boolean mandatory;
    public boolean hidden;
    public String users;
    public String groups;
    public String duties;
    public int gridLabelWidth;
    public int gridFieldWidth;
    public boolean addInfoOnly;
    public int startCount;
    public int endCount;
    
//    @Override
//    public String popupSearch(String criteria) {
//        return buildSearch(criteria);
//    }

    public String toString() {
        return "";
    }
    
    public boolean isAddInfoOnly() {
        return addInfoOnly;
    }

    public void setAddInfoOnly(boolean addInfoOnly) {
        this.addInfoOnly = addInfoOnly;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    public int getEndCount() {
        return endCount;
    }

    public void setEndCount(int endCount) {
        this.endCount = endCount;
    }

    public int getGridFieldWidth() {
        return gridFieldWidth;
    }

    public void setGridFieldWidth(int gridFieldWidth) {
        this.gridFieldWidth = gridFieldWidth;
    }

    public int getGridLabelWidth() {
        return gridLabelWidth;
    }

    public void setGridLabelWidth(int gridLabelWidth) {
        this.gridLabelWidth = gridLabelWidth;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(int labelWidth) {
        this.labelWidth = labelWidth;
    }

    public String getLinkto() {
        return linkto;
    }

    public void setLinkto(String linkto) {
        this.linkto = linkto;
    }

    public String getLinktoBean() {
        return linktoBean;
    }

    public void setLinktoBean(String linktoBean) {
        this.linktoBean = linktoBean;
    }

    public String getLinktoColumns() {
        return linktoColumns;
    }

    public void setLinktoColumns(String linktoColumns) {
        this.linktoColumns = linktoColumns;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getModelCombo() {
        return modelCombo;
    }

    public void setModelCombo(String modelCombo) {
        this.modelCombo = modelCombo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSqlCombo() {
        return sqlCombo;
    }

    public void setSqlCombo(String sqlCombo) {
        this.sqlCombo = sqlCombo;
    }

    public int getStartCount() {
        return startCount;
    }

    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUpCase() {
        return upCase;
    }

    public void setUpCase(boolean upCase) {
        this.upCase = upCase;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

    @Override
    public String _Key() {
        return "name";
    }
}
