package rule.helper;

import java.util.List;

import service.FlightService;
import bean.Awb;
import bean.Flight;
import bean.awb.AwbCharges;
import bean.awb.AwbDangerousGoods;
import bean.awb.AwbFlt;
import bean.awb.AwbShc;
import bean.reference.ServiceLevel;
import bean.reference.SpecialHandling;

public class Awb_RuleHelper {
    public void createBooking(Awb awb) {
        if (!awb.isEmptyKey()) {
            autoCreateSHC(awb);
            autoCreateFlights(awb);
            autoCreateCharges(awb);
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
    
    public void autoCreateCharges(Awb awb) {
        String serviceLevelt = awb.getServiceLevel();
        if (util.DataUtil.isEmpty(serviceLevelt)) return;
        //check if there are charges already
        AwbCharges charge = (AwbCharges) awb.selectFirstCache("SELECT a FROM AwbCharges a WHERE a.awbSeq="+awb.seq);
        if (charge!=null && !charge.isEmptyKey()) return;
        ServiceLevel serv = (ServiceLevel) awb.selectFirstCache("SELECT a FROM ServiceLevel a WHERE a.code='"+serviceLevelt+"'");
        if (serv.getChargeCode1()!=null) {
            createCharge(serv.chargeCode1, awb);
        }
        if (serv.getChargeCode2()!=null) {
            createCharge(serv.chargeCode2, awb);
        }
        if (serv.getChargeCode3()!=null) {
            createCharge(serv.chargeCode3, awb);
        }
        if (serv.getChargeCode4()!=null) {
            createCharge(serv.chargeCode4, awb);
        }
        if (serv.getChargeCode5()!=null) {
            createCharge(serv.chargeCode5, awb);
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
    
    public void createCharge(String charge, Awb awb) {
        AwbCharges ch = new AwbCharges();
        ch.chargeCode = charge;
        ch.awbSeq = awb.seq;
        ch.save();
    }
    public void createDG(String un, int pieces, Awb awb) {
        AwbDangerousGoods dg = new AwbDangerousGoods();
        dg.awbSeq = awb.seq;
        dg.unNumber = un;
        dg.pieces = pieces;
        dg.save();
    }

}
