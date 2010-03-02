/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rule;

import bean.FlightSchedule;
import javax.swing.JComponent;
import service.FlightService;

/**
 *
 * @author Charliemagne Mark
 */
public class FlightSchedule_RULE extends BusinessRuleWrapper {

    @Override
    public void runFocusLost(JComponent comp) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void runOnClick(JComponent comp) {
        if ("btnCreateFlights".equals(comp.getName())) {
            createFlights();
        }
    }

    private void createFlights() {
        FlightSchedule sched = (FlightSchedule) getBean();
        sched.constructAirportList();
        sched.save();
        sched.buildFlightSchedule();
        this.redisplayRecord();
    }

}
