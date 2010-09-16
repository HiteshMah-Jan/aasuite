/*
 * FlightSchedule.java
 *
 * Created on Oct 6, 2007, 6:38:22 AM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import bean.reference.AircraftType;
import bean.reference.Airport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import service.util.AbstractIBean;
import template.ChildRecord;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.Reports;
import template.UITemplate;
import template.screen.ChildTemplateListPopup;
import template.screen.ChildTemplateListPopupDownButton;
import template.screen.TemplateTabPage;

/**
 *
 * @author Budoy Entokwa
 */
@Entity
@Table(name = "FlightSchedule")
@UITemplate(template = template.screen.TemplateTabSinglePage.class, gridCount = 8, 
    columnSearch = {"carrier","flightNumber","fromDate","origin1"})
@Displays({
    @Display(name = "carrier", type="PopSearch", linktoBean=Carrier.class, width=50, mergeFields={"flightNumber"}),
    @Display(name = "flightNumber", width=40, hideLabel=true),
    @Display(name = "fromDate", label="From"),
    @Display(name = "toDate", label="To"),
    @Display(name = "aircraftType", type="PopSearch", linktoBean=AircraftType.class, label="A/C Type"),
    
    @Display(name = "origin1", width=60, type="PopSearch", linktoBean=Airport.class, label="From", labelTop=true, leftLabel="Flight 1"),
    @Display(name = "destination1", width=60, type="PopSearch", linktoBean=Airport.class, label="To", labelTop=true),
    @Display(name = "departureTime1", type="Time", label="Dep. Time", labelTop=true),
    @Display(name = "depPlus1", type="Combo", modelCombo={"-1","1","2","3","4"}, width=40, label="Plus", labelTop=true),
    @Display(name = "arrivalTime1", type="Time", label="Arr. Time", labelTop=true),
    @Display(name = "arrPlus1", type="Combo", modelCombo={"-1","1","2","3","4"}, width=40, label="Plus", labelTop=true),

    @Display(name = "origin2", type="PopSearch", linktoBean=Airport.class, label="Flight 2", width=-1),
    @Display(name = "destination2", type="PopSearch", linktoBean=Airport.class, hideLabel=true, width=-1),
    @Display(name = "departureTime2", type="Time", label="Dep. Time", hideLabel=true, width=-1),
    @Display(name = "depPlus2", type="Combo", modelCombo={"-1","1","2","3","4"}, hideLabel=true, width=-1),
    @Display(name = "arrivalTime2", type="Time", label="Arr. Time", hideLabel=true, width=-1),
    @Display(name = "arrPlus2", type="Combo", modelCombo={"-1","1","2","3","4"}, hideLabel=true, width=-1),

    @Display(name = "origin3", type="PopSearch", linktoBean=Airport.class, label="Flight 3", width=-1),
    @Display(name = "destination3", type="PopSearch", linktoBean=Airport.class, hideLabel=true, width=-1),
    @Display(name = "departureTime3", type="Time", label="Dep. Time", hideLabel=true, width=-1),
    @Display(name = "depPlus3", type="Combo", modelCombo={"-1","1","2","3","4"}, hideLabel=true, width=-1),
    @Display(name = "arrivalTime3", type="Time", label="Arr. Time", hideLabel=true, width=-1),
    @Display(name = "arrPlus3", type="Combo", modelCombo={"-1","1","2","3","4"}, hideLabel=true, width=-1),

    @Display(name = "origin4", type="PopSearch", linktoBean=Airport.class, label="Flight 4", width=-1),
    @Display(name = "destination4", type="PopSearch", linktoBean=Airport.class, hideLabel=true, width=-1),
    @Display(name = "departureTime4", type="Time", label="Dep. Time", hideLabel=true, width=-1),
    @Display(name = "depPlus4", type="Combo", modelCombo={"-1","1","2","3","4"}, hideLabel=true, width=-1),
    @Display(name = "arrivalTime4", type="Time", label="Arr. Time", hideLabel=true, width=-1),
    @Display(name = "arrPlus4", type="Combo", modelCombo={"-1","1","2","3","4"}, hideLabel=true, width=-1),
    
    @Display(name = "monday"),
    @Display(name = "tuesday"),
    @Display(name = "wednesday"),
    @Display(name = "thursday"),
    @Display(name = "friday"),
    @Display(name = "saturday"),
    @Display(name = "sunday")
})
@DisplayGroups({
    @DisplayGroup(title="Itenerary", gridCount=12, 
    		fields={
    			"origin1","destination1","departureTime1","arrivalTime1","depPlus1","arrPlus1",
    			"origin2","destination2","departureTime2","arrivalTime2","depPlus2","arrPlus2",
    			"origin3","destination3","departureTime3","arrivalTime3","depPlus3","arrPlus3",
    			"origin4","destination4","departureTime4","arrivalTime4","depPlus4","arrPlus4"
    		}),
    @DisplayGroup(title="Days", gridCount=14, fields={"monday","tuesday","wednesday","thursday","friday","saturday","sunday"})
})
@Reports({
    @template.Report(reportFile="FlightScheduleFlights", reportTitle="Flights", reportSql = "${seq}")
})
@ChildRecords(
    @ChildRecord(template=ChildTemplateListPopupDownButton.class, entity=Flight.class, sql="SELECT a FROM Flight a WHERE a.flightScheduleSeq=${seq}", title="Flight", fieldMapping={"seq","flightScheduleSeq"})
)
@template.ActionButtons({
    @template.ActionButton(name="btnCreateFlights", label="Create Flights")
})
public class FlightSchedule extends AbstractIBean implements Serializable {
	public static void main(String[] args) {
		view(FlightSchedule.class);
	}
	
	@Id
    @Column(name = "seq", nullable = false)
    public Integer seq;
    @Column(name = "tuesday")
    public boolean tuesday;
    @Column(name = "departureTime1", nullable = false, length = 4)
    public String departureTime1;
    @Column(name = "destination1", nullable = false, length = 3)
    public String destination1;
    @Column(name = "wednesday")
    public boolean wednesday;
    @Column(name = "destination4", length = 3)
    public String destination4;
    @Column(name = "flightNumber", nullable = false, length = 5)
    public String flightNumber;
    @Column(name = "departureTime4", length = 4)
    public String departureTime4;
    @Column(name = "thursday")
    public boolean thursday;
    @Column(name = "arrivalTime1", nullable = false, length = 4)
    public String arrivalTime1;
    @Column(name = "origin1", nullable = false, length = 3)
    public String origin1;
    @Column(name = "arrivalTime4", length = 4)
    public String arrivalTime4;
    @Column(name = "friday")
    public boolean friday;
    @Column(name = "toDate")
    @Temporal(value = TemporalType.DATE)
    public Date toDate;
    @Column(name = "carrier", nullable = false, length = 3)
    public String carrier;
    @Column(name = "aircraftType", nullable = false, length = 20)
    public String aircraftType;
    @Column(name = "saturday")
    public boolean saturday;
    @Column(name = "monday")
    public boolean monday;
    @Column(name = "origin2", length = 3)
    public String origin2;
    @Column(name = "origin4", length = 3)
    public String origin4;
    @Column(name = "sunday")
    public boolean sunday;
    @Column(name = "arrivalTime2", length = 4)
    public String arrivalTime2;
    @Column(name = "depPlus1")
    public int depPlus1;
    @Column(name = "destination3", length = 3)
    public String destination3;
    @Column(name = "depPlus2")
    public int depPlus2;
    @Column(name = "arrivalTime3", length = 4)
    public String arrivalTime3;
    @Column(name = "depPlus3")
    public int depPlus3;
    @Column(name = "fromDate", nullable = false)
    @Temporal(value = TemporalType.DATE)
    public Date fromDate;
    @Column(name = "depPlus4")
    public int depPlus4;
    @Column(name = "origin3", length = 3)
    public String origin3;
    @Column(name = "arrPlus1")
    public int arrPlus1;
    @Column(name = "destination2", length = 3)
    public String destination2;
    @Column(name = "arrPlus2")
    public int arrPlus2;
    @Column(name = "departureTime3", length = 4)
    public String departureTime3;
    @Column(name = "arrPlus3")
    public int arrPlus3;
    @Column(name = "departureTime2", length = 4)
    public String departureTime2;
    @Column(name = "arrPlus4")
    public int arrPlus4;
    @Column(name = "active")
    public boolean active;
    @Column(name = "airportList")
    public String airportList;

    public String getAirportList() {
        return airportList;
    }

    public void setAirportList(String airportList) {
        this.airportList = airportList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public int getArrPlus1() {
        return arrPlus1;
    }

    public void setArrPlus1(int arrPlus1) {
        this.arrPlus1 = arrPlus1;
    }

    public int getArrPlus2() {
        return arrPlus2;
    }

    public void setArrPlus2(int arrPlus2) {
        this.arrPlus2 = arrPlus2;
    }

    public int getArrPlus3() {
        return arrPlus3;
    }

    public void setArrPlus3(int arrPlus3) {
        this.arrPlus3 = arrPlus3;
    }

    public int getArrPlus4() {
        return arrPlus4;
    }

    public void setArrPlus4(int arrPlus4) {
        this.arrPlus4 = arrPlus4;
    }

    public String getArrivalTime1() {
        return arrivalTime1;
    }

    public void setArrivalTime1(String arrivalTime1) {
        this.arrivalTime1 = arrivalTime1;
    }

    public String getArrivalTime2() {
        return arrivalTime2;
    }

    public void setArrivalTime2(String arrivalTime2) {
        this.arrivalTime2 = arrivalTime2;
    }

    public String getArrivalTime3() {
        return arrivalTime3;
    }

    public void setArrivalTime3(String arrivalTime3) {
        this.arrivalTime3 = arrivalTime3;
    }

    public String getArrivalTime4() {
        return arrivalTime4;
    }

    public void setArrivalTime4(String arrivalTime4) {
        this.arrivalTime4 = arrivalTime4;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getDepPlus1() {
        return depPlus1;
    }

    public void setDepPlus1(int depPlus1) {
        this.depPlus1 = depPlus1;
    }

    public int getDepPlus2() {
        return depPlus2;
    }

    public void setDepPlus2(int depPlus2) {
        this.depPlus2 = depPlus2;
    }

    public int getDepPlus3() {
        return depPlus3;
    }

    public void setDepPlus3(int depPlus3) {
        this.depPlus3 = depPlus3;
    }

    public int getDepPlus4() {
        return depPlus4;
    }

    public void setDepPlus4(int depPlus4) {
        this.depPlus4 = depPlus4;
    }

    public String getDepartureTime1() {
        return departureTime1;
    }

    public void setDepartureTime1(String departureTime1) {
        this.departureTime1 = departureTime1;
    }

    public String getDepartureTime2() {
        return departureTime2;
    }

    public void setDepartureTime2(String departureTime2) {
        this.departureTime2 = departureTime2;
    }

    public String getDepartureTime3() {
        return departureTime3;
    }

    public void setDepartureTime3(String departureTime3) {
        this.departureTime3 = departureTime3;
    }

    public String getDepartureTime4() {
        return departureTime4;
    }

    public void setDepartureTime4(String departureTime4) {
        this.departureTime4 = departureTime4;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public String getDestination3() {
        return destination3;
    }

    public void setDestination3(String destination3) {
        this.destination3 = destination3;
    }

    public String getDestination4() {
        return destination4;
    }

    public void setDestination4(String destination4) {
        this.destination4 = destination4;
    }

    public String getDestination1() {
        return destination1;
    }

    public void setDestination1(String destination1) {
        this.destination1 = destination1;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public String getOrigin1() {
        return origin1;
    }

    public void setOrigin1(String origin1) {
        this.origin1 = origin1;
    }

    public String getOrigin2() {
        return origin2;
    }

    public void setOrigin2(String origin2) {
        this.origin2 = origin2;
    }

    public String getOrigin3() {
        return origin3;
    }

    public void setOrigin3(String origin3) {
        this.origin3 = origin3;
    }

    public String getOrigin4() {
        return origin4;
    }

    public void setOrigin4(String origin4) {
        this.origin4 = origin4;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public void constructAirportList() {
        airportList = BeanUtil.concat(origin1," ",destination1," ",destination2," ",destination3," ",destination4);
    }
    
    public List<Flight> buildFlightSchedule() {
        //Create all the flights here
        List<Flight> lstFlt = new ArrayList<Flight>();
        Date from = getFromDate();
        Date to = getToDate();
        //create all the flights here
        int days = util.DateUtil.countDaySpan(from, to);
        Date useDate = null;
        for (int i = 0; i <= days; i++) {
            useDate = util.DateUtil.addDay(getFromDate(), i);
            if (isFlyingOnDate(useDate)) {
                Flight flt1 = createFlight(useDate, 1);
                Flight flt2 = createFlight(useDate, 2);
                Flight flt3 = createFlight(useDate, 3);
                Flight flt4 = createFlight(useDate, 4);

                lstFlt.add(flt1);
                if (flt2 != null) {
                    lstFlt.add(flt2);
                }
                if (flt3 != null) {
                    lstFlt.add(flt3);
                }
                if (flt4 != null) {
                    lstFlt.add(flt4);
                }
            }
        }
        List lst = selectListCache("SELECT a FROM Flight a WHERE a.flightScheduleSeq=",seq);
        for (Flight flt : lstFlt) {
            if (this.hasSameBuilt(lst, flt)) {
                continue;
            }
            flt.save();
            lst.add(flt);
        }
        return lstFlt;
    }

    private boolean hasSameBuilt(List<Flight> lst, Flight flight) {
        if (lst == null || lst.size() == 0) {
            return false;
        }
        for (Flight flt : lst) {
            if (flt.isSameBuilt(flight)) {
                flight.setSeq(flt.getSeq());
                return true;
            }
        }
        return false;
    }

    public boolean isFlyingOnDate(Date useDate) {
        if (isMonday() && DateUtil.Days.isMonday(useDate)) {
            return true;
        }
        if (isTuesday() && DateUtil.Days.isTuesday(useDate)) {
            return true;
        }
        if (isWednesday() && DateUtil.Days.isWednesday(useDate)) {
            return true;
        }
        if (isThursday() && DateUtil.Days.isThursday(useDate)) {
            return true;
        }
        if (isFriday() && DateUtil.Days.isFriday(useDate)) {
            return true;
        }
        if (isSaturday() && DateUtil.Days.isSaturday(useDate)) {
            return true;
        }
        if (isSunday() && DateUtil.Days.isSunday(useDate)) {
            return true;
        }
        return false;
    }
    
    public Flight createFlight(Date useDate, int leg) {
        Flight flt = new Flight();
        flt.setIsActive(true);
        flt.setFlightScheduleSeq(getSeq());
        flt.setAircraftType(getAircraftType());
        flt.setCarrier(getCarrier());
        flt.setFlightNumber(getFlightNumber());
        flt.setFlightDate(useDate);
        flt.setLegNumber(leg);
        constructAirportList();
        flt.airportList = airportList;

        AircraftType aircraft = (AircraftType) selectFirstCache("SELECT a FROM AircraftType a WHERE a.code='",getAircraftType(),"'");
        flt.setAvailLc(aircraft.getLc());
        flt.setAvailLp(aircraft.getLp());
        flt.setAvailMh(aircraft.getMh());
        flt.setAvailMp(aircraft.getMp());
        flt.setAvailVolume(aircraft.getDefaultVolume());
        flt.setAvailWeight(aircraft.getDefaultWeight());
        if (leg == 1) {
            flt.setOrigin(getOrigin1());
            flt.setDestination(getDestination1());

            flt.setDepartureDate(DateUtil.addDay(useDate, getDepPlus1()));
            flt.setArrivalDate(DateUtil.addDay(useDate, getArrPlus1()));
            flt.setDepartureTime(getDepartureTime1());
            flt.setArrivalTime(getArrivalTime1());
        } else if (leg == 2) {
            if (PanelUtil.isEmpty(getOrigin2()) || (PanelUtil.isEmpty(getDestination2()))) {
                return null;
            }
            flt.setOrigin(getOrigin2());
            flt.setDestination(getDestination2());

            flt.setDepartureDate(DateUtil.addDay(useDate, getDepPlus2()));
            flt.setArrivalDate(DateUtil.addDay(useDate, getArrPlus2()));
            flt.setDepartureTime(getDepartureTime2());
            flt.setArrivalTime(getArrivalTime2());
        } else if (leg == 3) {
            if (PanelUtil.isEmpty(getOrigin3()) || (PanelUtil.isEmpty(getDestination3()))) {
                return null;
            }
            flt.setOrigin(getOrigin3());
            flt.setDestination(getDestination3());

            flt.setDepartureDate(DateUtil.addDay(useDate, getDepPlus3()));
            flt.setArrivalDate(DateUtil.addDay(useDate, getArrPlus3()));
            flt.setDepartureTime(getDepartureTime3());
            flt.setArrivalTime(getArrivalTime3());
        } else if (leg == 4) {
            if (PanelUtil.isEmpty(getOrigin4()) || (PanelUtil.isEmpty(getDestination4()))) {
                return null;
            }
            flt.setOrigin(getOrigin4());
            flt.setDestination(getDestination4());

            flt.setDepartureDate(DateUtil.addDay(useDate, getDepPlus4()));
            flt.setArrivalDate(DateUtil.addDay(useDate, getArrPlus4()));
            flt.setDepartureTime(getDepartureTime4());
            flt.setArrivalTime(getArrivalTime4());
        }
        return flt;
    }

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	@Override
	public void setupIndex() {
		runUniqueIndex(1, "carrier","flightNumber","fromDate");
	}
	
}
