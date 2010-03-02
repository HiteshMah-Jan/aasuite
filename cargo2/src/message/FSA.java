/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import message.util.StringSplitter;
import message.util.SubstringUtil;
import util.DBClient;
import util.DateUtil;
import bean.Awb;
import bean.Flight;

/**
 *
 * @author Charliemagne Mark
 */
public class FSA extends FSU {
	 private Awb awb = new Awb();
    public static void main(String[] args) {
        String message = 
                "FSA/11" +
                "\n077-12345675BOGNCE/T10K350" +
                "\nDLV/03NOV1615/NCE/T10K350/MICLARK";
        new FSA(message).processMessage();
    }
    
    public FSA(String message) {
        super(message);
    }

    @Override
    protected String getType() {
        return "FSA";
    }
    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FSA")) {
            processConsignmentDetail(awb, null, lineSplitter.getNextLine());
            while (lineSplitter.hasNextLine()) {
                String str = lineSplitter.getNextLine();
                processRCS(str);
                processRCT(str);
                processRCF(str);
                processBKD(str);
                processMAN(str);
                processDEP(str);
                processPRE(str);
                processTRM(str);
                processTFD(str);
                processNFD(str);
                processAWD(str);
                processCCD(str);
                processDLV(str);
                processDIS(str);
                processOSI(str);
                processCRC(str);
                processDDL(str);
                processTGC(str);
                processARR(str);
                processAWR(str);
            }
        }
    }
    
    protected void processRCS(String line) {
    	if (line.startsWith("RCS")) {
//    		RCS/15FEB1800/BHX/T13/SHIPPERNAME
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil received = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil volume = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port = airport.getNextSubstring(3);
            quantity.getNextNonNumeric();
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();
            String detail = received.getNextSubstring(35);
            double vol = volume.getNextDouble();
            
            awb.pieces = pieces;
            awb.volume = vol;
            awb.weight = weight;
            awb.origin = port;
            awb.bookRef = detail;
            awb.save();
    	}
    }

    protected void processRCT(String line) {
    	if (line.startsWith("RCT")) {
//    		RCT/TW/12NOV1900/CDG/T20
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil carrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String plane = carrier.getNextSubstring(2);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port = airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();
            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();
    	}
    }
    protected void processRCF(String line) {
    	if (line.startsWith("RCF")) {
//    		RCF/LH451/10JUL0700/FRA/T12
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil flightCarrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String plane = flightCarrier.getNextSubstring(2);
            String flightNo = flightCarrier.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port = airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();
            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();
    	}
    }
    protected void processBKD(String line) {
    	if (line.startsWith("BKD")) {
//    		BKD/SR903/22MAR/BSLKHI/T20/S1900/S0500-N
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil flightcarrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil originDestination = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil timeIndicatorDeparture = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil dayIndicatorArrival = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String carrier = flightcarrier.getNextSubstring(2);
            String flightNo = flightcarrier.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String origin = originDestination.getNextSubstring(3);
            String destination = originDestination.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();
            String timeIndicator = timeIndicatorDeparture.getNextSubstring(1);
            String departure = timeIndicatorDeparture.getNextSubstring(4);
            String dayIndicator = dayIndicatorArrival.getNextSubstring(1);
            String arrival = dayIndicatorArrival.getNextSubstring(4);
            dayIndicatorArrival.getNextSubstring(1);
            dayIndicatorArrival.getNextSubstring(1);

            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.save();  
    	}
    }
    protected void processMAN(String line) {
    	if (line.startsWith("MAN")) {
//    		MAN/SR1207/18AUG/HKGZRH/T20/S1200/S1900
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil flightcarrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil originDestination = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil timeIndicatorDeparture = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil dayIndicatorArrival = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String carrier = flightcarrier.getNextSubstring(2);
            String flightNo = flightcarrier.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String origin = originDestination.getNextSubstring(3);
            String destination = originDestination.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();
            String timeIndicator = timeIndicatorDeparture.getNextSubstring(1);
            String departure = timeIndicatorDeparture.getNextSubstring(4);
            String dayIndicator = dayIndicatorArrival.getNextSubstring(1);
            String arrival = dayIndicatorArrival.getNextSubstring(4);
            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.save();  
    	}
    }
    protected void processDEP(String line) {
    	if (line.startsWith("DEP")) {
//    		DEP/SR172/24MAR/BSLKHI/T20/A1930/E0530-N
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil flightcarrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil originDestination = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil timeIndicatorDeparture = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil dayIndicatorArrival = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String carrier = flightcarrier.getNextSubstring(2);
            String flightNo = flightcarrier.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String origin = originDestination.getNextSubstring(3);
            String destination = originDestination.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();
            String timeIndicator = timeIndicatorDeparture.getNextSubstring(1);
            String departure = timeIndicatorDeparture.getNextSubstring(4);
            String dayIndicator = dayIndicatorArrival.getNextSubstring(1);
            String arrival = dayIndicatorArrival.getNextSubstring(4);
            dayIndicatorArrival.getNextSubstring(1);
            dayIndicatorArrival.getNextSubstring(1);
            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.save();  
    	}
    }
    protected void processPRE(String line) {
    	if (line.startsWith("PRE")) {
//    		PRE///CDGHKG/T18/S2000/S0500-N
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil carrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil flight = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil originDestination = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil timeIndicatorDeparture = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil dayIndicatorArrival = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty()); 
            
            String status = code.getNextSubstring(3);
            String date = movement.getNextSubstring(0);
            String plane = carrier.getNextSubstring(0);
            String flightNo = flight.getNextSubstring(0);
            String origin = originDestination.getNextSubstring(3);
            String destination = originDestination.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();
            String timeIndicator = timeIndicatorDeparture.getNextSubstring(1);
            String departure = timeIndicatorDeparture.getNextSubstring(4);
            String dayIndicator = dayIndicatorArrival.getNextSubstring(1);
            String arrival = dayIndicatorArrival.getNextSubstring(4);
            dayIndicatorArrival.getNextSubstring(1);
            dayIndicatorArrival.getNextSubstring(1);
            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.save();
    	}
    }
    protected void processTRM(String line) {
    	if (line.startsWith("TRM")) {
//    		TRM/TW/SYD/T12
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil carrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String plane = carrier.getNextSubstring(2);
            String port = airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processTFD(String line) {
    	if (line.startsWith("TFD")) {
//    		TFD/CK/10JUL1800/HKG/T12
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil carrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movementFlight = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());

            
            String status = code.getNextSubstring(3);
            String plane = carrier.getNextSubstring(2);
            String date = movementFlight.getNextSubstring(5);
            String flightNo = movementFlight.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processNFD(String line) {
    	if (line.startsWith("NFD")) {
//    		NFD/03MAY2000/HEL/T3
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movementFlight = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());

            
            String status = code.getNextSubstring(3);
            String date = movementFlight.getNextSubstring(5);
            String flightNo = movementFlight.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processAWD(String line) {
    	if (line.startsWith("AWD")) {
//    		AWD/17JUL1530/ARN/T10
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            
            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processCCD(String line) {
    	if (line.startsWith("CCD")) {
//    		CCD/18JUL2100/JFK/T12
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processDLV(String line) {
    	if (line.startsWith("DLV")) {
//    		DLV/27OCT1300/NCE/T3
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processDIS(String line) {
    	if (line.startsWith("DIS")) {
//    		DIS/AF321/12JUN0930/LHR/MSAW/T1
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil flightCarrier = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airportDiscrepancy = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil discrepancyCode = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String plane = flightCarrier.getNextSubstring(3);
            String flight = flightCarrier.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port= airportDiscrepancy.getNextSubstring(3);
            String portCode= discrepancyCode.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processOSI(String line) {
    	if (line.startsWith("OSI")) {
//    		OSI/NO RECORD FOUND
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil received = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());

            
            String status = code.getNextSubstring(3);
            String detail = received.getNextSubstring(35);
            awb.save();  
    	}
    }
    protected void processCRC(String line) {
    	if (line.startsWith("CRC")) {
//    		CRC/18JUL1400/LYS/T12
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movementActualTime = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String date = movementActualTime.getNextSubstring(5);
            String time = movementActualTime.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processDDL(String line) {
    	if (line.startsWith("DDL")) {
//    		DDL/27OCT1015/NCE/T3/CNEE
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil deliveryName = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();
            String delivery = deliveryName.getNextSubstring(4);


            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processTGC(String line) {
    	if (line.startsWith("TGC")) {
//    		TGC/18JUL1400/LYS/T12
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processARR(String line) {
    	if (line.startsWith("ARR")) {
//    		ARR/LH451/10JUL0700/FRA/T12
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil carrierFlight = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String carrier = carrierFlight.getNextSubstring(2);
            String flightNo = carrierFlight.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    protected void processAWR(String line) {
    	if (line.startsWith("AWR")) {
//    		AWR/LH451/10JUL0700/FRA/T12
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            SubstringUtil code = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil carrierFlight = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil movement = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil airport = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            SubstringUtil quantity = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            
            String status = code.getNextSubstring(3);
            String carrier = carrierFlight.getNextSubstring(2);
            String flightNo = carrierFlight.getNextSubstring(3);
            String date = movement.getNextSubstring(5);
            String time = movement.getNextSubstring(4);
            String port= airport.getNextSubstring(3);
            int pieces = quantity.getNextInt();
            double weight = quantity.getNextDouble();

            awb.pieces = pieces;
            awb.weight = weight;
            awb.origin = port;
            awb.save();  
    	}
    }
    
}
