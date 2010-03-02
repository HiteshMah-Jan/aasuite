/*
 * java
 *
 * Created on Sep 30, 2007, 8:02:08 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "aawb")
public class OnlineAwb extends Awb {
}
