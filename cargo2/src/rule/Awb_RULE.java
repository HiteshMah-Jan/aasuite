/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.Awb;
import bean.Flight;
import bean.reference.Country;
import common2.Common2View;
import component.JTreeTableDisplayer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import service.FlightService;

/**
 *
 * @author Charliemagne Mark
 */
public class Awb_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnCreateBooking".equals(comp.getName())) {
            createBooking();
        }
        else if ("btnChooseRoute".equals(comp.getName())) {
            chooseRoute();
        }
        else if ("btnShowMessages".equals(comp.getName())) {
            showMessages();
        }
    }

    private void chooseRoute() {
        Awb awb = (Awb) this.getBean();
        if (awb.departureDate==null) {
            awb.departureDate = util.DateUtil.addDay(new Date(), 1);
            awb.save();
        }
        FlightService serv = new FlightService();
        List lst = serv.getAllRoutes(awb);
        if (lst==null || lst.isEmpty()) {
            showMessage("No routes available.");
            return;
        }
        DefaultMutableTreeNode obj = (DefaultMutableTreeNode) JTreeTableDisplayer.popup(lst, "Route Selection");
        if (obj!=null && obj.getUserObject() instanceof Flight) {
            List<Flight> lstFlight = new ArrayList<Flight>();
            Flight f = (Flight) obj.getUserObject();
            DefaultMutableTreeNode pnode = (DefaultMutableTreeNode) f.myNode.getParent();
            if (pnode==null || pnode.getUserObject()==null || pnode.getUserObject().toString().trim().isEmpty()) {
                lstFlight.add(f);
            }
            else {
                int count = pnode.getChildCount();
                for (int i = 0; i < count; i++) {
                    DefaultMutableTreeNode n = (DefaultMutableTreeNode) pnode.getChildAt(i);
                    lstFlight.add((Flight) n.getUserObject());
                }
            }
            awb.putRouting(lstFlight);
            redisplayRecord();
        }
    }

    private void createBooking() {
        Awb awb = (Awb) this.getBean();
        putBookingDefaults();
        awb.save();
        awb.createBooking();
        redisplayRecord();
    }

    private void showMessages() {
        Awb awb = (Awb) this.getBean();
        Common2View.showBeanPanel("CargoMessageInbox", "", "SELECT a FROM CargoMessageInbox a, CargoMessageTransactionDetail b WHERE a.seq=b.cargoMessageInboxSeq AND b.entity='Awb' AND b.entityId='"+awb.seq+"'");
    }

    protected void putBookingDefaults() {
        Awb awb = (Awb) this.getBean();
        Country c = awb.extractOriginCountry();
        setValue("currencyCode", c==null?"":c.currency);
    }
}
