/*
 * FlightService.java
 *
 * Created on Oct 4, 2007, 1:26:29 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Awb;
import bean.reference.Connection;
import bean.Flight;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.DBClient;
import util.DateUtil;
import util.Log;

/**
 *
 * @author Budoy Entokwa
 */
public class FlightService {
    public List getAllRoutes(Awb awb) {
        return getAllRoutes(awb.origin, awb.destination, awb.departureDate, awb.weight, awb.volume, awb.mp, awb.mh, awb.lp, awb.lc);
    }
    
    public List getAllRoutes(String origin, String destination, Date date, double... capacity) {
        List retList = new ArrayList();
        //check for direct flight
        for (int i = 0; i < 5; i++) {
            Date d = util.DateUtil.addDay(date, i);
            Flight flt = this.getDirectFlight(origin, destination, d, capacity);
            if (flt != null) {
                retList.add(flt);
            }
            List lst = getRoutesWithConnection(origin, destination, d, capacity);
            if (lst != null || lst.size() > 0) {
                retList.add(lst);
            }
        }
        return retList;
    }

    public List<Flight> getRouteWithFirstConnection(String origin, String destination, Date date, double... capacity) {
        List retList = new ArrayList();
        //for connecting flights, get connection from origin to destination
        System.out.println("\n\nUSE CONNECTING FLIGHT\n\n");
        List lstConn = DBClient.getList("SELECT a FROM Connection a WHERE a.origin='",origin,"' and a.destination='",destination,"'");
        if (lstConn != null && lstConn.size() > 0) {
            Connection conn = (Connection) lstConn.get(0);
            retList.addAll(getRouteFromConnection(conn, origin, destination, date, capacity));
        }
        return retList;
    }

    public List<List> getRoutesWithConnection(String origin, String destination, Date date, double... capacity) {
        List retList = new ArrayList();
        //for connecting flights, get connection from origin to destination
        System.out.println("\n\nUSE CONNECTING FLIGHT\n\n");
        List lstConn = DBClient.getList("SELECT a FROM Connection a WHERE a.origin='",origin,"' and a.destination='",destination,"'");
        if (lstConn != null && lstConn.size() > 0) {
            for (Object object : lstConn) {
                Connection conn = (Connection) object;
                retList.add(getRouteFromConnection(conn, origin, destination, date, capacity));
            }
        }
        return retList;
    }

    public List<Flight> getRouteFromConnection(Connection conn, String origin, String destination, Date date, double... capacity) {
        List retList = new ArrayList<Flight>();
        String airport1 = conn.getAirport1(); //origin

        String airport2 = conn.getAirport2();
        String airport3 = conn.getAirport3();
        String airport4 = conn.getAirport4();
        String airport5 = conn.getAirport5();

        //select flight using airport 1 and 2
        Log.out("\n\nAIRPORT ",airport1,":",airport2);
        Flight flt1 = this.getDirectFlight(airport1, airport2, date, capacity);
        if (flt1 != null) {
            retList.add(flt1);
            Log.out("\n\nAIRPORT ",airport2,":",airport3);
            Flight flt2 = this.getDirectFlight(airport2, airport3, flt1.getDepartureDate(), flt1.getDepartureTime(), capacity);
            if (flt2 != null) {
                retList.add(flt2);
                Flight flt3 = this.getDirectFlight(airport3, airport4, flt2.getDepartureDate(), flt2.getDepartureTime(), capacity);
                if (flt3 != null) {
                    retList.add(flt3);
                    Flight flt4 = this.getDirectFlight(airport4, airport5, flt3.getDepartureDate(), flt3.getDepartureTime(), capacity);
                    if (flt4 != null) {
                        retList.add(flt4);
                    }
                }
            }
        }
        return retList;
    }

    public List<Flight> getBestRoute(String origin, String destination, Date date, double... capacity) {
        List retList = new ArrayList<Flight>();
        //check for direct flight
        for (int i = 0; retList.isEmpty() && i < 3; i++) {
            Date d = util.DateUtil.addDay(date, i);
            Flight flt = this.getDirectFlight(origin, destination, d, capacity);
            if (flt != null) {
                retList.add(flt);
            } else {
                retList.addAll(getRouteWithFirstConnection(origin, destination, d, capacity));
            }
        }
        return retList;
    }

    public Flight getDirectFlight(String origin, String destination, Date date, double... capacity) {
        return this.getDirectFlight(origin, destination, date, DateUtil.formatDate(date, "hhmm"), capacity);
    }

    public Flight getDirectFlight(String origin, String destination, Date date, String time, double... capacity) {
        List lst = DBClient.getList("SELECT a FROM Flight a WHERE a.origin='",origin,"' and a.airportList LIKE '%",destination,"%' AND a.departureDate='",util.DateUtil.formatDateToSql(date),"' ORDER BY a.departureDate");
        //get all direct flights first, if there is a good direct flight use it, most likely it will be better than the connecting flights
        for (Object obj : lst) {
            Log.info("ITERATE DIRECT FLIGHTS");
            Flight flt = (Flight) obj;
            if (flt.goodCapacity(capacity)) {
                //            check timezone
//                    if (DateUtil.isTimezoneLate(origin, flt.getDepartureDate(), time)) {
//                        continue;
//                    }
                return flt;
            }
        }
        return null;
    }
}
