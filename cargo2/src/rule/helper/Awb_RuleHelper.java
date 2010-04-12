package rule.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import service.FlightService;
import util.DBClient;
import util.DateUtil;
import bean.Awb;
import bean.ChargesRule;
import bean.Flight;
import bean.admin.AppConfig;
import bean.awb.AwbDangerousGoods;
import bean.awb.AwbFlt;
import bean.awb.AwbShc;
import bean.reference.Charges;
import bean.reference.ServiceLevel;
import bean.reference.SpecialHandling;

public class Awb_RuleHelper {
    public void createBooking(Awb awb) {
        if (!awb.isEmptyKey()) {
            autoCreateSHC(awb);
            autoCreateFlights(awb);
        }
    }
    public void autoCreateFlights(Awb awb) {
        List col = awb.selectListCache("SELECT a FROM AwbFltBooking a WHERE a.awbSeq="+awb.seq);
        if (col != null && col.size() > 0) {
            return;
        }
        //get all direct flights first
        String origint = awb.getOrigin();
        String destinationt = awb.getDestination();
        double weightt = awb.getWeight();
        double volumet = awb.getVolume();
        int mpt = awb.getMp();
        int mht = awb.getMh();
        int lpt = awb.getLp();
        int lct = awb.getLc();
        FlightService service = new FlightService();
        List lst = service.getBestRoute(origint, destinationt, awb.departureDate, weightt, volumet, mht, mpt, lpt, lct);
        putRouting(lst, awb);
    }
    public void putRouting(List lst, Awb awb) {
        int line = 0;
        for (Object obj : lst) {
            Flight flight = (Flight) obj;
            AwbFlt flt = new AwbFlt();
            flt.setLine(++line);
            flt.setArrivalDate(flight.getArrivalDate());
            flt.setAwbSeq(awb.getSeq());
            flt.setFlightSeq(flight.getSeq());
            flt.setPieces(awb.getPieces());
            flt.setWeight(awb.getWeight());
            flt.setVolume(awb.getVolume());
            flt.setMh(awb.getMh());
            flt.setMp(awb.getMp());
            flt.setLc(awb.getLc());
            flt.setLp(awb.getLp());
            flt.setOrigin(flight.getOrigin());
            flt.setDestination(flight.getDestination());
            flt.setCarrier(flight.getCarrier());
            flt.setFlightNumber(flight.getFlightNumber());
            flt.setFlightDate(flight.getDepartureDate());
            flt.setStatus("KK");
            flt.save();
//
//            AwbRouting route = new AwbRouting(); 
//            route.setAwbSeq(getSeq());
//            route.setLine(line);
//            route.setOrigin(flight.getOrigin());
//            route.setDestination(flight.getDestination());
//            route.setStatus("KK");
//            route.save();
        }
    }
    
    public void autoCreateDG(AwbShc awbShc, Awb awb) {
        SpecialHandling shc = (SpecialHandling) awb.selectFirstCache("SELECT a FROM SpecialHandling a WHERE a.code='"+awbShc.getShcCode()+"'");
        if (shc.getDgNumber1()!=null) {
            createDG(shc.dgNumber1, awb.pieces, awb);
        }
        if (shc.getDgNumber2()!=null) {
            createDG(shc.dgNumber2, awb.pieces, awb);
        }
        if (shc.getDgNumber3()!=null) {
            createDG(shc.dgNumber3, awb.pieces, awb);
        }
        if (shc.getDgNumber4()!=null) {
            createDG(shc.dgNumber4, awb.pieces, awb);
        }
        if (shc.getDgNumber5()!=null) {
            createDG(shc.dgNumber5, awb.pieces, awb);
        }
    }
    public void autoCreateSHC(Awb awb) {
        List col = awb.selectListCache("SELECT a FROM AwbShc a WHERE a.awbSeq="+awb.seq);
        if (col != null && col.size() > 0) {
            return;
        }
        //check the service
        String serviceLevelt = awb.getServiceLevel();
        ServiceLevel serv = (ServiceLevel) awb.selectFirstCache("SELECT a FROM ServiceLevel a WHERE a.code='"+serviceLevelt+"'");
        if (serv != null) {
            if (!util.DataUtil.isEmpty(serv.getShc1())) {
                AwbShc shc1 = new AwbShc();
                shc1.setAwbSeq(awb.getSeq());
                shc1.setShcCode(serv.getShc1());
                shc1.save();
                autoCreateDG(shc1, awb);
            }
            if (!util.DataUtil.isEmpty(serv.getShc2())) {
                AwbShc shc2 = new AwbShc();
                shc2.setAwbSeq(awb.getSeq());
                shc2.setShcCode(serv.getShc2());
                shc2.save();
                autoCreateDG(shc2, awb);
            }
            if (!util.DataUtil.isEmpty(serv.getShc3())) {
                AwbShc shc3 = new AwbShc();
                shc3.setAwbSeq(awb.getSeq());
                shc3.setShcCode(serv.getShc3());
                shc3.save();
                autoCreateDG(shc3, awb);
            }
            if (!util.DataUtil.isEmpty(serv.getShc4())) {
                AwbShc shc4 = new AwbShc();
                shc4.setAwbSeq(awb.getSeq());
                shc4.setShcCode(serv.getShc4());
                shc4.save();
                autoCreateDG(shc4, awb);
            }
            if (!util.DataUtil.isEmpty(serv.getShc5())) {
                AwbShc shc5 = new AwbShc();
                shc5.setAwbSeq(awb.getSeq());
                shc5.setShcCode(serv.getShc5());
                shc5.save();
                autoCreateDG(shc5, awb);
            }
        }
    }
    
    public void createDG(String un, int pieces, Awb awb) {
        AwbDangerousGoods dg = new AwbDangerousGoods();
        dg.awbSeq = awb.seq;
        dg.unNumber = un;
        dg.pieces = pieces;
        dg.save();
    }

    public List<ChargesRule> getApplicableCharges(Awb awb) {
    	List<AwbShc> shcs = DBClient.getList("SELECT a FROM AwbShc a WHERE a.awbSeq='"+awb.seq+"'");
    	String s = "";
    	if (shcs != null && !shcs.isEmpty()) {
         	for (AwbShc shc : shcs) {
        		s += "'"+shc.shcCode+"',";
        	}
    	}
    	s += "''";
    	String d = "'"+DateUtil.formatDateToSql(new Date())+"'";
    	String sql = "";
    	
//    	this part of program should have a switch to disable and enable
    	if (AppConfig.isAutoCreateChargeRule()) {
        	sql = "SELECT a FROM ChargesRule a " +
			"WHERE a.origin='"+awb.origin+"' AND a.destination='"+awb.destination+"' " +
			"AND "+d+" BETWEEN a.startDate AND a.endDate AND a.shc IS NULL AND a.serviceLevel IS NULL";
			List originRules = DBClient.getList(sql);
			if (originRules == null || originRules.isEmpty()) {
				createChargesRule(awb);
			}
		
			sql = "SELECT a FROM ChargesRule a " +
				"WHERE a.origin='"+awb.origin+"' AND a.destination='"+awb.destination+"' " +
				"AND "+d+" BETWEEN a.startDate AND a.endDate " +
				"AND a.shc IN ("+s+") ";
			List shcRules = DBClient.getList(sql);
			if (shcRules == null || shcRules.isEmpty()) {
				createChargesRuleForSHC(awb);
			}
			
			sql = "SELECT a FROM ChargesRule a " +
				"WHERE a.origin='"+awb.origin+"' AND a.destination='"+awb.destination+"' " +
				"AND "+d+" BETWEEN a.startDate AND a.endDate " +			
				"AND a.serviceLevel IN ('"+awb.serviceLevel+"') ";
			List serviceRules = DBClient.getList(sql);
			if (serviceRules == null || serviceRules.isEmpty()) {
				createChargesRuleForServiceLevel(awb);
			}
    	}
		
    	sql = "SELECT a FROM ChargesRule a " +
			"WHERE a.origin='"+awb.origin+"' AND a.destination='"+awb.destination+"' " +
			"AND "+d+" BETWEEN a.startDate AND a.endDate " +
			"AND (a.shc IS NULL OR a.shc IN ("+s+")) " +
			"AND (a.serviceLevel IS NULL OR a.serviceLevel IN ('"+awb.serviceLevel+"')) ";
    	return DBClient.getList(sql);
    }
    
    public void createChargesRule(Awb awb) {
    	List l = new ArrayList();
    	List<Charges> lst = DBClient.getList("SELECT a FROM Charges a WHERE a.always=true");
//    	usual charges
    	for (Charges ch : lst) {
        	ChargesRule rule = new ChargesRule();
        	rule.chargeCode = ch.code;
        	rule.active = true;
        	rule.startDate = DateUtil.getFirstDayOfYear();
        	rule.endDate = DateUtil.getEndDayOfYear();
        	rule.origin = awb.origin;
        	rule.destination = awb.destination;
        	rule.amount = ch.amount;
        	l.add(rule);
    	}
    	DBClient.persistBean(l);
    }

    public void createChargesRuleForSHC(Awb awb) {
    	List l = new ArrayList();
//    	charge for shc
    	List<AwbShc> shcs = DBClient.getList("SELECT a FROM AwbShc a WHERE a.awbSeq='"+awb.seq+"'");
    	if (shcs != null && !shcs.isEmpty()) {
         	for (AwbShc shc : shcs) {
            	if (shc.shcCode!=null && !shc.shcCode.isEmpty()) {
                	ChargesRule rule = new ChargesRule();
                	rule.chargeCode = "SHC";
                	rule.active = true;
                	rule.startDate = DateUtil.getFirstDayOfYear();
                	rule.endDate = DateUtil.getEndDayOfYear();
                	rule.origin = awb.origin;
                	rule.destination = awb.destination;
                	rule.amount = 0;
                	rule.shc = shc.shcCode;
                	l.add(rule);
            	}
        	}
    	}
    	DBClient.persistBean(l);
    }
    
    public void createChargesRuleForServiceLevel(Awb awb) {
    	List l = new ArrayList();
    	if (awb.serviceLevel!=null && !awb.serviceLevel.isEmpty()) {
        	ChargesRule rule = new ChargesRule();
        	rule.chargeCode = "PROD";
        	rule.active = true;
        	rule.startDate = DateUtil.getFirstDayOfYear();
        	rule.endDate = DateUtil.getEndDayOfYear();
        	rule.origin = awb.origin;
        	rule.destination = awb.destination;
        	rule.amount = 0;
        	rule.serviceLevel = awb.serviceLevel;
        	l.add(rule);
    	}
    	DBClient.persistBean(l);
    }
}
