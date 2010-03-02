/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package message;

import java.util.ArrayList;
import java.util.List;

import message.util.LineSplitter;
import message.util.StringSplitter;
import message.util.SubstringUtil;
import util.DateUtil;
import bean.Awb;
import bean.awb.AwbAccounting;
import bean.awb.AwbCCDestinationCurrency;
import bean.awb.AwbChargeSummary;
import bean.awb.AwbCharges;
import bean.awb.AwbCustoms;
import bean.awb.AwbFlt;
import bean.awb.AwbParticipant;
import bean.awb.AwbRateDescription;
import bean.awb.AwbRouting;
import bean.awb.AwbShc;

/**
 *
 * @author Charliemagne Mark
 */
public class FWB extends AbstractMessageProcessor {
    Awb awb = new Awb();
    public static void main(String[] args) {
        String message = 
                "FWB/14" +
                "\n777-12345675LHRJFK/T1K913.0" +
                "\nRTG/JFKII" +
                "\nSHP" +
                "\n/EXPORTANT LTD." +
                "\n/WAPPING TRADING ESTATE" +
                "\n/LONDON" +
                "\n/GB" +
                "\nCNE" +
                "\n/ACME CONSOLIDATORS" +
                "\n/500 MADISON AVE" +
                "\n/NEW YORK/NY" +
                "\n/US" +
                "\nAGT//9147123" +
                "\n/EXPORTANT LTD." +
                "\n/HEATHROW" +
                "\nCVD/GBP//PP/NVD/NCV/XXX" +
                "\nRTD/1/P1/K913.0/CQ/W913/R1.36/T1241.68" +
                "\n/NC/CONSOLIDATION" +
                "\n/2/NC/AS PER ATTACHED LIST" +
                "\n/3/NS/18" +
                "\n/4/ND//NDA-0-0-0/0" +
                "\nPPD/WT1241.68" +
                "\n/CT1241.68" +
                "\nCER/EXPORTANT LTD." +
                "\nISU/01OCT05/HEATHROW/EXPORTANT LTD." +
                "\nREF///AGT/EXPORTANTLTD/LHR" +
                "\nOCI/US/AGT/A/BCBP123" +
                "\n/US/TID/F/A999";
        new FWB(message).processMessage();
    }
    private List<AwbShc> lstShc = new ArrayList<AwbShc>();
    private List<AwbFlt> lstFlt;
    
    public FWB(String message) {
        super(message);
    }

    @Override
    public void processMessage() {
        String mtype = lineSplitter.getNextLine();
        if (mtype.startsWith("FWB")) {
            processConsignmentDetail(awb, lstShc, lineSplitter.getNextLine());
            processFlightDetail(awb, lstFlt, "FWB");
            processRouting(awb, lineSplitter.getLine("RTG"));
            processShipper(awb, lineSplitter.getLine("SHP"));
            processConsignee(awb, lineSplitter.getLine("CNE"));
            processAgent(awb, lineSplitter.getLine("AGT"));
            processSSR(awb, lineSplitter.getLine("SSR"));
            processAlsoNotify(awb, lineSplitter.getLine("NFY"));
            processAccntInfo(awb, lineSplitter.getLine("ACC"));
            processCVD(awb, lineSplitter.getLine("CVD"));
            processRate(awb, lineSplitter.getLine("RTD"));
            processOtherCharges(awb, lineSplitter.getLine("OTH"));
            processChargeSummary(awb, lineSplitter.getLine("PPD"), "PREPAID");
            processChargeSummary(awb, lineSplitter.getLine("PPD"), "COLLECT");
            processShipperCert(awb, lineSplitter.getLine("CER"));
            processCarrierExecution(awb, lineSplitter.getLine("ISU"));
            processOSI(awb, lineSplitter.getLine("OSI"));
            processCChargeDestCurrency(awb, lineSplitter.getLine("CDC"));
            processSenderReference(awb, lineSplitter.getLine("REF"));
            processCustomsOrigin(awb, lineSplitter.getLine("COR"));
            processCommissionInfo(awb, lineSplitter.getLine("COI"));
            processSalesIncentiveinfo(awb, lineSplitter.getLine("SII"));
            processAgentRefData(awb, lineSplitter.getLine("ARD"));
            processSHC(awb, lineSplitter.getLine("SPH"));
            processNominatedHandlingParty(awb, lineSplitter.getLine("NOM"));
            processSRI(awb, lineSplitter.getLine("SRI"));
            processOPI(awb, lineSplitter.getLines("OPI"));
            processOtherCustomsInfo(awb, lineSplitter.getLines("OCI"));            
        }
    }

    private void processAccntInfo(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        for (int i = 0; i < 6; i++) {
            AwbAccounting acct = new AwbAccounting();
            acct.awbSeq = awb.seq;
            acct.informationIdentifier = ss.getNextSplitNonEmpty();
            acct.accountingInformation = ss.getNextSplitNonEmpty();
            acct.save();
        }
    }

    private void processAgentRefData(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        awb.agent = ss.getNextSplitNonEmpty();
    }

    private void processCChargeDestCurrency(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        AwbCCDestinationCurrency ccdest = new AwbCCDestinationCurrency();
        ccdest.awbSeq = awb.seq;
        SubstringUtil crate = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
        ccdest.country = crate.getNextNonNumeric();
        ccdest.exchangeRate = crate.getNextDouble();
        ccdest.ccAmount = ss.getNextSplitDouble();
        ccdest.destinationAmount = ss.getNextSplitDouble();
        ccdest.totalCharge = ss.getNextSplitDouble();
        ccdest.save();
    }

    private void processCarrierExecution(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        awb.issueDate = DateUtil.readDate(ss.getNextSplitNonEmpty(), "ddMMMyy");
        awb.issuePlace = ss.getNextSplitNonEmpty();
        awb.carrierSignature = ss.getNextSplitNonEmpty();
    }

    private void processCommissionInfo(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        String s = ss.getNextSplitNonEmpty();
        if (!s.startsWith("N")) {
            ss.reset();
            ss.getNextSplit();
            awb.commissionAmount = ss.getNextSplitDouble();
            awb.commissionPercent = ss.getNextSplitDouble();
        }
    }

    private void processCustomsOrigin(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        awb.customsOriginCode = ss.getNextSplitNonEmpty();
    }

    private void processNominatedHandlingParty(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        awb.handlingParty = ss.getNextSplitNonEmpty() + " " + ss.getNextSplitNonEmpty();
    }

    private void processOPI(Awb awb, List<String> lines) {
        for (String line : lines) {
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            ss.getNextSplit();
            AwbParticipant part = new AwbParticipant();
            part.awbSeq = awb.seq;
            part.name = ss.getNextSplitNonEmpty();
            part.messageAddress = ss.getNextSplitNonEmpty();
            part.fileReference = ss.getNextSplitNonEmpty();
            part.identifier = ss.getNextSplitNonEmpty();
            part.partCode = ss.getNextSplitNonEmpty();
            part.airportCode = ss.getNextSplitNonEmpty();
            part.save();
        }
    }

    private void processOtherCharges(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        for (int i = 0; i < 10; i++) {
            AwbCharges acct = new AwbCharges();
            acct.awbSeq = awb.seq;
            acct.chargeType = ss.getNextSplitNonEmpty();
            SubstringUtil s = lineSplitter.getSubstringUtil(ss.getNextSplitNonEmpty());
            acct.chargeCode = s.getNextSubstring(2);
            acct.cassIndicator = s.getNextSubstring(1);
            acct.amount = s.getNextDouble();
            acct.save();
        }
    }

    private void processOtherCustomsInfo(Awb awb, List<String> lines) {
        for (String line : lines) {
            StringSplitter ss = lineSplitter.getStringSplitter(line);
            ss.getNextSplit();
            AwbCustoms customs = new AwbCustoms();
            customs.awbSeq = awb.seq;
            customs.country = ss.getNextSplitNonEmpty();
            customs.customInformationIdentifier = ss.getNextSplitNonEmpty();
            customs.supplementaryCustomInformation = ss.getNextSplitNonEmpty();
            customs.save();
        }
    }

    private void processChargeSummary(Awb awb, String line, String type) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        String str = ss.getNextSplitNonEmpty();
        AwbChargeSummary sum = new AwbChargeSummary();
        sum.summaryType = type;
        sum.awbSeq = awb.seq;
        while (str!=null) {
            if ("WT".equals(str)) {
                sum.totalWeightAmount = ss.getNextSplitDouble();
            }
            else if ("VC".equals(str)) {
                sum.valuationChargeAmount = ss.getNextSplitDouble();
            }
            else if ("TX".equals(str)) {
                sum.taxesAmount = ss.getNextSplitDouble();
            }
            else if ("OA".equals(str)) {
                sum.totalOtherChargesDueAgent = ss.getNextSplitDouble();
            }
            else if ("OC".equals(str)) {
                sum.totalOtherChargesDueCarrier = ss.getNextSplitDouble();
            }
            else if ("CT".equals(str)) {
                sum.chargeSummaryTotal = ss.getNextSplitDouble();
            }
            str = ss.getNextSplitNonEmpty();
        }
        sum.save();
    }

    private void processRate(Awb awb, String line) {
        LineSplitter l = new LineSplitter(line);
        while (l.hasNextLine()) {
            String s = l.getNextLine();
            StringSplitter ss = l.getStringSplitter(s);
            if (s.startsWith("RTD")) {
                ss.getNextSplit();
            }
            AwbRateDescription rate = new AwbRateDescription();
            rate.awbSeq = awb.seq;

            String str = ss.getNextSplitNonEmpty();
            while (str!=null) {
                SubstringUtil tpieces = l.getSubstringUtil(str);
                str = ss.getNextSplitNonEmpty();
                String first = tpieces.getNextSubstring(1);
                if (first.startsWith("P")) {
                    rate.pieces = tpieces.getNextInt();
                }
                else if (first.startsWith("K")) {
                    rate.weight = tpieces.getNextDouble();
                }
                else if (first.startsWith("C")) {
                    rate.rateClassCode = tpieces.getNextSubstring(1);
                }
                else if (first.startsWith("S")) {
                    rate.harmonisedCommodityCode = tpieces.getNextNonNumeric();
                }
                else if (first.startsWith("W")) {
                    rate.weight = tpieces.getNextDouble();
                }
                else if (first.startsWith("R")) {
                    rate.chargeAmount = tpieces.getNextDouble();
                }
                else if (first.startsWith("T")) {
                    rate.chargeAmount = tpieces.getNextDouble();
                }
                else if (first.startsWith("N")) {
                    String two = first+tpieces.getNextSubstring(1);
                    if ("NG".equals(two)) {
                        rate.ngNatureOfGoods = tpieces.getNextNonNumeric();
                    }
                    else if ("NC".equals(two)) {
                        rate.ncNatureOfGoods = tpieces.getNextNonNumeric();
                    }
                    else if ("ND".equals(two)) {
//                        rate.weight = tpieces.getNextDouble();
                    }
                    else if ("NV".equals(two)) {
                        rate.volumeCode = tpieces.getNextNonNumeric();
                        rate.volume = tpieces.getNextDouble();
                    }
                    else if ("NU".equals(two)) {
                        rate.uld = tpieces.getNextSubstring(10);
                    }
                    else if ("NS".equals(two)) {
                        rate.slac = tpieces.getNextInt()+"";
                    }
                    else if ("NH".equals(two)) {
                        rate.harmonisedCommodityCode = tpieces.getNextSubstring(18);
                    }
                    else if ("NO".equals(two)) {
                        rate.country = tpieces.getNextNonNumeric();
                    }
                }
                else {
                    rate.serviceCode = str;
                }
            }
            rate.save();
        }
    }

    private void processRouting(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        AwbRouting route = new AwbRouting();
        route.awbSeq = awb.seq;
        route.route1 = ss.getNextSplitNonEmpty();
        route.route2 = ss.getNextSplitNonEmpty();
        route.route3 = ss.getNextSplitNonEmpty();
        route.save();
    }

    private void processSHC(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        for (int i = 0; i < 9; i++) {
            String s = ss.getNextSplitNonEmpty();
            if (s==null || s.isEmpty()) continue;
            AwbShc shc = new AwbShc();
            shc.awbSeq = awb.seq;
            shc.shcCode = s;
        }
    }

    private void processSalesIncentiveinfo(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        awb.salesIncentiveAmount = ss.getNextSplitDouble();
    }

    private void processSenderReference(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        awb.senderReference = ss.getNextSplitNonEmpty();
        awb.senderFileReference = ss.getNextSplitNonEmpty();
        awb.senderPartId = ss.getNextSplitNonEmpty() + ss.getNextSplitNonEmpty() + ss.getNextSplitNonEmpty();
    }

    private void processShipperCert(Awb awb, String line) {
        StringSplitter ss = lineSplitter.getStringSplitter(line);
        ss.getNextSplit();
        awb.shipperSignature = ss.getNextSplitNonEmpty();
    }

}
