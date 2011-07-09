/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import component.TabPanelForm;
/**
 *
 * @author Charliemagne Mark
 */
public class AdmissionForm extends TabPanelForm {
    @Override
    public String getTitle() {
        return "Library";
    }

    @Override
    public String tabs() {
        return "LibraryBooks,LibraryBorrowedBooks,LibraryBookShelf,LibraryBookPublisher";
    }

}
