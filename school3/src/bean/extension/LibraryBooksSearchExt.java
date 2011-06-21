/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.extension;

import bean.LibraryBookShelf;
import java.io.Serializable;
import template.*;
import template.screen.TemplateSearchOnly;

/**
 *
 * @author Entokwaa
 */
@UITemplate( template=TemplateSearchOnly.class,
		columnSearch={"callNumber","isbn","title","subtitle","shelfOrGroup","accessionNumber","author","availPieces"},
		criteriaSearch={"searchStr"},
		gridCount=4, title="Online Catalog")
@Displays({
        @Display(name="title"),
        @Display(name="shelfOrGroup", label="Subject", type="PopSearch", linktoBean=LibraryBookShelf.class),
        @Display(name="subtitle"),
        @Display(name="author"),
        @Display(name="pieces"),
        @Display(name="bookDescription"),
        @Display(name="copyrightDate"),
        @Display(name="dateAcquired"),
        @Display(name="searchStr", label="Search")        
})
public class LibraryBooksSearchExt extends bean.LibraryBooks implements Serializable {
    public static void main(String[] args) {
        view(LibraryBooksSearchExt.class);
    }
}