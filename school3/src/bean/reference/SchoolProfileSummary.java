 /*
 * Course.java
 *
 * Created on Nov 15, 2007, 1:18:51 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import service.util.AbstractIBean;
import template.ActionButton;
import template.ActionButtons;
import template.ChildRecords;
import template.Display;
import template.DisplayGroup;
import template.DisplayGroups;
import template.Displays;
import template.ParentAddInfo;
import template.UITemplate;
import template.screen.TemplateTabPage;
import template.Reports;
import bean.admin.AppConfig;

/**
 *
 * @author Charliemagne Mark
 */
@Entity
@Table(name = "SchoolProfileSummary")
@UITemplate(template=TemplateTabPage.class, columnSearch={"schoolYear"}, gridCount=36, title="School Profile")
@Displays({
    @Display(name="schoolYear"),
    @Display(name = "pre4Male", width=25, labelTop=true, label="Age4", leftLabel="BOYS - PRE"),
    @Display(name = "pre5Male", width=25, labelTop=true, label="5"),
    @Display(name = "pre6Male", width=25, labelTop=true, label="6"),
    @Display(name = "pre7Male", width=25, labelTop=true, label="7"),
    @Display(name = "pre8Male", width=25, labelTop=true, label="8"),
    @Display(name = "pre9Male", width=25, labelTop=true, label="9"),
    @Display(name = "pre10Male", width=25, labelTop=true, label="10"),
    @Display(name = "pre11Male", width=25, labelTop=true, label="11"),
    @Display(name = "pre12Male", width=25, labelTop=true, label="12"),
    @Display(name = "pre13Male", width=25, labelTop=true, label="13"),
    @Display(name = "pre14Male", width=25, labelTop=true, label="14"),
    @Display(name = "pre15Male", width=25, labelTop=true, label="15"),
    @Display(name = "pre16Male", width=25, labelTop=true, label="16"),
    @Display(name = "pre17Male", width=25, labelTop=true, label="17"),
    @Display(name = "pre18Male", width=25, labelTop=true, label="18"),
    @Display(name = "pre19Male", width=25, labelTop=true, label="19"),
    @Display(name = "pre20Male", width=25, labelTop=true, label="20"),
    
    @Display(name = "preClassesMale", width=25, labelTop=true, label="Class", leftLabel="--"),
    @Display(name = "preCompleteMale", width=25, labelTop=true, label="Pass"),
    @Display(name = "preDropMale", width=25, labelTop=true, label="Drop"),
    @Display(name = "preTransferOutMale", width=25, labelTop=true, label="Out"),
    @Display(name = "preFailedMale", width=25, labelTop=true, label="Fail"),
    @Display(name = "preShiftMale", width=25, labelTop=true, label="Shift"),

    @Display(name = "preMonoMale", width=25, labelTop=true, label="Mono", leftLabel="--"),
    @Display(name = "preMultiMale", width=25, labelTop=true, label="Multi"),
    @Display(name = "preSpedMale", width=25, labelTop=true, label="Sped"),
    @Display(name = "preRepeatMale", width=25, labelTop=true, label="Rpt"),
    @Display(name = "preTransferMale", width=25, labelTop=true, label="Trans"),
    @Display(name = "preBackMale", width=25, labelTop=true, label="Back"),
    @Display(name = "preMuslimMale", width=25, labelTop=true, label="Musl"),
    @Display(name = "preIndigenousMale", width=25, labelTop=true, label="Indi"),
    
    @Display(name = "preMale", width=25, labelTop=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "preESCMale", width=25, labelTop=true, label="ESC", leftLabel="BOYS - PRE"),
    @Display(name = "preESCCompleteMale", width=25, labelTop=true, label="Pass"),
    @Display(name = "preESCTransferOutMale", width=25, labelTop=true, label="Out"),
    @Display(name = "preESCDropMale", width=25, labelTop=true, label="Drop"),
    @Display(name = "preESCFailedMale", width=25, labelTop=true, label="Fail"),

    @Display(name = "preEVSMale", width=25, labelTop=true, label="EVS", leftLabel="--"),
    @Display(name = "preEVSCompleteMale", width=25, labelTop=true, label="Pass"),
    @Display(name = "preEVSTransferOutMale", width=25, labelTop=true, label="Out"),
    @Display(name = "preEVSDropMale", width=25, labelTop=true, label="Drop"),
    @Display(name = "preEVSFailedMale", width=25, labelTop=true, label="Fail"),

    @Display(name = "g14Male", width=25, label="Grade 1"),
    @Display(name = "g15Male", width=25, hideLabel=true, label="5"),
    @Display(name = "g16Male", width=25, hideLabel=true, label="6"),
    @Display(name = "g17Male", width=25, hideLabel=true, label="7"),
    @Display(name = "g18Male", width=25, hideLabel=true, label="8"),
    @Display(name = "g19Male", width=25, hideLabel=true, label="9"),
    @Display(name = "g110Male", width=25, hideLabel=true, label="10"),
    @Display(name = "g111Male", width=25, hideLabel=true, label="11"),
    @Display(name = "g112Male", width=25, hideLabel=true, label="12"),
    @Display(name = "g113Male", width=25, hideLabel=true, label="13"),
    @Display(name = "g114Male", width=25, hideLabel=true, label="14"),
    @Display(name = "g115Male", width=25, hideLabel=true, label="15"),
    @Display(name = "g116Male", width=25, hideLabel=true, label="16"),
    @Display(name = "g117Male", width=25, hideLabel=true, label="17"),
    @Display(name = "g118Male", width=25, hideLabel=true, label="18"),
    @Display(name = "g119Male", width=25, hideLabel=true, label="19"),
    @Display(name = "g120Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g1ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g1CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g1DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g1TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g1FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g1ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g1MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g1MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g1SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g1RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g1TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g1BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g1MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g1IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g1Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g1ESCMale", width=25, label="Grade 1"),
    @Display(name = "g1ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g1ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g1ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g1ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g1EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g1EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g1EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g1EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g1EVSFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g24Male", width=25, label="Grade 2"),
    @Display(name = "g25Male", width=25, hideLabel=true, label="5"),
    @Display(name = "g26Male", width=25, hideLabel=true, label="6"),
    @Display(name = "g27Male", width=25, hideLabel=true, label="7"),
    @Display(name = "g28Male", width=25, hideLabel=true, label="8"),
    @Display(name = "g29Male", width=25, hideLabel=true, label="9"),
    @Display(name = "g210Male", width=25, hideLabel=true, label="10"),
    @Display(name = "g211Male", width=25, hideLabel=true, label="11"),
    @Display(name = "g212Male", width=25, hideLabel=true, label="12"),
    @Display(name = "g213Male", width=25, hideLabel=true, label="13"),
    @Display(name = "g214Male", width=25, hideLabel=true, label="14"),
    @Display(name = "g215Male", width=25, hideLabel=true, label="15"),
    @Display(name = "g216Male", width=25, hideLabel=true, label="16"),
    @Display(name = "g217Male", width=25, hideLabel=true, label="17"),
    @Display(name = "g218Male", width=25, hideLabel=true, label="18"),
    @Display(name = "g219Male", width=25, hideLabel=true, label="19"),
    @Display(name = "g220Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g2ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g2CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g2DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g2TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g2FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g2ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g2MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g2MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g2SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g2RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g2TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g2BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g2MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g2IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g2Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g2ESCMale", width=25, label="Grade 2"),
    @Display(name = "g2ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g2ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g2ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g2ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g2EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g2EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g2EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g2EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g2EVSFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g34Male", width=25, label="Grade 3"),
    @Display(name = "g35Male", width=25, hideLabel=true, label="5"),
    @Display(name = "g36Male", width=25, hideLabel=true, label="6"),
    @Display(name = "g37Male", width=25, hideLabel=true, label="7"),
    @Display(name = "g38Male", width=25, hideLabel=true, label="8"),
    @Display(name = "g39Male", width=25, hideLabel=true, label="9"),
    @Display(name = "g310Male", width=25, hideLabel=true, label="10"),
    @Display(name = "g311Male", width=25, hideLabel=true, label="11"),
    @Display(name = "g312Male", width=25, hideLabel=true, label="12"),
    @Display(name = "g313Male", width=25, hideLabel=true, label="13"),
    @Display(name = "g314Male", width=25, hideLabel=true, label="14"),
    @Display(name = "g315Male", width=25, hideLabel=true, label="15"),
    @Display(name = "g316Male", width=25, hideLabel=true, label="16"),
    @Display(name = "g317Male", width=25, hideLabel=true, label="17"),
    @Display(name = "g318Male", width=25, hideLabel=true, label="18"),
    @Display(name = "g319Male", width=25, hideLabel=true, label="19"),
    @Display(name = "g320Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g3ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g3CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g3DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g3TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g3FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g3ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g3MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g3MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g3SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g3RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g3TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g3BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g3MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g3IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g3Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g3ESCMale", width=25, label="Grade 3"),
    @Display(name = "g3ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g3ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g3ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g3ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g3EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g3EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g3EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g3EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g3EVSFailedMale", width=25, hideLabel=true, label="Fail"),




    @Display(name = "g44Male", width=25, label="Grade 4"),
    @Display(name = "g45Male", width=25, hideLabel=true, label="5"),
    @Display(name = "g46Male", width=25, hideLabel=true, label="6"),
    @Display(name = "g47Male", width=25, hideLabel=true, label="7"),
    @Display(name = "g48Male", width=25, hideLabel=true, label="8"),
    @Display(name = "g49Male", width=25, hideLabel=true, label="9"),
    @Display(name = "g410Male", width=25, hideLabel=true, label="10"),
    @Display(name = "g411Male", width=25, hideLabel=true, label="11"),
    @Display(name = "g412Male", width=25, hideLabel=true, label="12"),
    @Display(name = "g413Male", width=25, hideLabel=true, label="13"),
    @Display(name = "g414Male", width=25, hideLabel=true, label="14"),
    @Display(name = "g415Male", width=25, hideLabel=true, label="15"),
    @Display(name = "g416Male", width=25, hideLabel=true, label="16"),
    @Display(name = "g417Male", width=25, hideLabel=true, label="17"),
    @Display(name = "g418Male", width=25, hideLabel=true, label="18"),
    @Display(name = "g419Male", width=25, hideLabel=true, label="19"),
    @Display(name = "g420Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g4ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g4CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g4DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g4TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g4FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g4ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g4MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g4MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g4SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g4RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g4TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g4BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g4MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g4IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g4Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g4ESCMale", width=25, label="Grade 4"),
    @Display(name = "g4ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g4ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g4ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g4ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g4EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g4EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g4EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g4EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g4EVSFailedMale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "g54Male", width=25, label="Grade 5"),
    @Display(name = "g55Male", width=25, hideLabel=true, label="5"),
    @Display(name = "g56Male", width=25, hideLabel=true, label="6"),
    @Display(name = "g57Male", width=25, hideLabel=true, label="7"),
    @Display(name = "g58Male", width=25, hideLabel=true, label="8"),
    @Display(name = "g59Male", width=25, hideLabel=true, label="9"),
    @Display(name = "g510Male", width=25, hideLabel=true, label="10"),
    @Display(name = "g511Male", width=25, hideLabel=true, label="11"),
    @Display(name = "g512Male", width=25, hideLabel=true, label="12"),
    @Display(name = "g513Male", width=25, hideLabel=true, label="13"),
    @Display(name = "g514Male", width=25, hideLabel=true, label="14"),
    @Display(name = "g515Male", width=25, hideLabel=true, label="15"),
    @Display(name = "g516Male", width=25, hideLabel=true, label="16"),
    @Display(name = "g517Male", width=25, hideLabel=true, label="17"),
    @Display(name = "g518Male", width=25, hideLabel=true, label="18"),
    @Display(name = "g519Male", width=25, hideLabel=true, label="19"),
    @Display(name = "g520Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g5ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g5CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g5DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g5TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g5FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g5ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g5MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g5MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g5SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g5RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g5TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g5BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g5MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g5IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g5Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g5ESCMale", width=25, label="Grade 5"),
    @Display(name = "g5ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g5ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g5ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g5ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g5EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g5EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g5EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g5EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g5EVSFailedMale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "g64Male", width=25, label="Grade 6"),
    @Display(name = "g65Male", width=25, hideLabel=true, label="5"),
    @Display(name = "g66Male", width=25, hideLabel=true, label="6"),
    @Display(name = "g67Male", width=25, hideLabel=true, label="7"),
    @Display(name = "g68Male", width=25, hideLabel=true, label="8"),
    @Display(name = "g69Male", width=25, hideLabel=true, label="9"),
    @Display(name = "g610Male", width=25, hideLabel=true, label="10"),
    @Display(name = "g611Male", width=25, hideLabel=true, label="11"),
    @Display(name = "g612Male", width=25, hideLabel=true, label="12"),
    @Display(name = "g613Male", width=25, hideLabel=true, label="13"),
    @Display(name = "g614Male", width=25, hideLabel=true, label="14"),
    @Display(name = "g615Male", width=25, hideLabel=true, label="15"),
    @Display(name = "g616Male", width=25, hideLabel=true, label="16"),
    @Display(name = "g617Male", width=25, hideLabel=true, label="17"),
    @Display(name = "g618Male", width=25, hideLabel=true, label="18"),
    @Display(name = "g619Male", width=25, hideLabel=true, label="19"),
    @Display(name = "g620Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g6ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g6CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g6DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g6TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g6FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g6ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g6MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g6MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g6SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g6RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g6TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g6BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g6MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g6IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g6Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g6ESCMale", width=25, label="Grade 6"),
    @Display(name = "g6ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g6ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g6ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g6ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g6EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g6EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g6EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g6EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g6EVSFailedMale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "h14Male", width=25, label="1st Yr"),
    @Display(name = "h15Male", width=25, hideLabel=true, label="5"),
    @Display(name = "h16Male", width=25, hideLabel=true, label="6"),
    @Display(name = "h17Male", width=25, hideLabel=true, label="7"),
    @Display(name = "h18Male", width=25, hideLabel=true, label="8"),
    @Display(name = "h19Male", width=25, hideLabel=true, label="9"),
    @Display(name = "h110Male", width=25, hideLabel=true, label="10"),
    @Display(name = "h111Male", width=25, hideLabel=true, label="11"),
    @Display(name = "h112Male", width=25, hideLabel=true, label="12"),
    @Display(name = "h113Male", width=25, hideLabel=true, label="13"),
    @Display(name = "h114Male", width=25, hideLabel=true, label="14"),
    @Display(name = "h115Male", width=25, hideLabel=true, label="15"),
    @Display(name = "h116Male", width=25, hideLabel=true, label="16"),
    @Display(name = "h117Male", width=25, hideLabel=true, label="17"),
    @Display(name = "h118Male", width=25, hideLabel=true, label="18"),
    @Display(name = "h119Male", width=25, hideLabel=true, label="19"),
    @Display(name = "h120Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "h1ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "h1CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h1DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h1TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h1FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "h1ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "h1MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "h1MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "h1SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "h1RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "h1TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "h1BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "h1MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "h1IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "h1Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "h1ESCMale", width=25, label="1st Yr"),
    @Display(name = "h1ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h1ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h1ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h1ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "h1EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "h1EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h1EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h1EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h1EVSFailedMale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "h24Male", width=25, label="2nd Yr"),
    @Display(name = "h25Male", width=25, hideLabel=true, label="5"),
    @Display(name = "h26Male", width=25, hideLabel=true, label="6"),
    @Display(name = "h27Male", width=25, hideLabel=true, label="7"),
    @Display(name = "h28Male", width=25, hideLabel=true, label="8"),
    @Display(name = "h29Male", width=25, hideLabel=true, label="9"),
    @Display(name = "h210Male", width=25, hideLabel=true, label="10"),
    @Display(name = "h211Male", width=25, hideLabel=true, label="11"),
    @Display(name = "h212Male", width=25, hideLabel=true, label="12"),
    @Display(name = "h213Male", width=25, hideLabel=true, label="13"),
    @Display(name = "h214Male", width=25, hideLabel=true, label="14"),
    @Display(name = "h215Male", width=25, hideLabel=true, label="15"),
    @Display(name = "h216Male", width=25, hideLabel=true, label="16"),
    @Display(name = "h217Male", width=25, hideLabel=true, label="17"),
    @Display(name = "h218Male", width=25, hideLabel=true, label="18"),
    @Display(name = "h219Male", width=25, hideLabel=true, label="19"),
    @Display(name = "h220Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "h2ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "h2CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h2DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h2TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h2FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "h2ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "h2MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "h2MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "h2SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "h2RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "h2TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "h2BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "h2MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "h2IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "h2Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "h2ESCMale", width=25, label="2nd Yr"),
    @Display(name = "h2ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h2ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h2ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h2ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "h2EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "h2EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h2EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h2EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h2EVSFailedMale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "h34Male", width=25, label="3rd Yr"),
    @Display(name = "h35Male", width=25, hideLabel=true, label="5"),
    @Display(name = "h36Male", width=25, hideLabel=true, label="6"),
    @Display(name = "h37Male", width=25, hideLabel=true, label="7"),
    @Display(name = "h38Male", width=25, hideLabel=true, label="8"),
    @Display(name = "h39Male", width=25, hideLabel=true, label="9"),
    @Display(name = "h310Male", width=25, hideLabel=true, label="10"),
    @Display(name = "h311Male", width=25, hideLabel=true, label="11"),
    @Display(name = "h312Male", width=25, hideLabel=true, label="12"),
    @Display(name = "h313Male", width=25, hideLabel=true, label="13"),
    @Display(name = "h314Male", width=25, hideLabel=true, label="14"),
    @Display(name = "h315Male", width=25, hideLabel=true, label="15"),
    @Display(name = "h316Male", width=25, hideLabel=true, label="16"),
    @Display(name = "h317Male", width=25, hideLabel=true, label="17"),
    @Display(name = "h318Male", width=25, hideLabel=true, label="18"),
    @Display(name = "h319Male", width=25, hideLabel=true, label="19"),
    @Display(name = "h320Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "h3ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "h3CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h3DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h3TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h3FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "h3ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "h3MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "h3MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "h3SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "h3RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "h3TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "h3BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "h3MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "h3IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "h3Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "h3ESCMale", width=25, label="3rd Yr"),
    @Display(name = "h3ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h3ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h3ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h3ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "h3EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "h3EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h3EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h3EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h3EVSFailedMale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "h44Male", width=25, label="4th Yr"),
    @Display(name = "h45Male", width=25, hideLabel=true, label="5"),
    @Display(name = "h46Male", width=25, hideLabel=true, label="6"),
    @Display(name = "h47Male", width=25, hideLabel=true, label="7"),
    @Display(name = "h48Male", width=25, hideLabel=true, label="8"),
    @Display(name = "h49Male", width=25, hideLabel=true, label="9"),
    @Display(name = "h410Male", width=25, hideLabel=true, label="10"),
    @Display(name = "h411Male", width=25, hideLabel=true, label="11"),
    @Display(name = "h412Male", width=25, hideLabel=true, label="12"),
    @Display(name = "h413Male", width=25, hideLabel=true, label="13"),
    @Display(name = "h414Male", width=25, hideLabel=true, label="14"),
    @Display(name = "h415Male", width=25, hideLabel=true, label="15"),
    @Display(name = "h416Male", width=25, hideLabel=true, label="16"),
    @Display(name = "h417Male", width=25, hideLabel=true, label="17"),
    @Display(name = "h418Male", width=25, hideLabel=true, label="18"),
    @Display(name = "h419Male", width=25, hideLabel=true, label="19"),
    @Display(name = "h420Male", width=25, hideLabel=true, label="20"),
    
    @Display(name = "h4ClassesMale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "h4CompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h4DropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h4TransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h4FailedMale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "h4ShiftMale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "h4MonoMale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "h4MultiMale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "h4SpedMale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "h4RepeatMale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "h4TransferMale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "h4BackMale", width=25, hideLabel=true, label="Back"),
    @Display(name = "h4MuslimMale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "h4IndigenousMale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "h4Male", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "h4ESCMale", width=25, label="4th Yr"),
    @Display(name = "h4ESCCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h4ESCTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h4ESCDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h4ESCFailedMale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "h4EVSMale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "h4EVSCompleteMale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h4EVSTransferOutMale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h4EVSDropMale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h4EVSFailedMale", width=25, hideLabel=true, label="Fail"),


    @Display(name = "pre4Female", width=25, labelTop=true, label="Age4", leftLabel="GIRLS - PRE"),
    @Display(name = "pre5Female", width=25, labelTop=true, label="5"),
    @Display(name = "pre6Female", width=25, labelTop=true, label="6"),
    @Display(name = "pre7Female", width=25, labelTop=true, label="7"),
    @Display(name = "pre8Female", width=25, labelTop=true, label="8"),
    @Display(name = "pre9Female", width=25, labelTop=true, label="9"),
    @Display(name = "pre10Female", width=25, labelTop=true, label="10"),
    @Display(name = "pre11Female", width=25, labelTop=true, label="11"),
    @Display(name = "pre12Female", width=25, labelTop=true, label="12"),
    @Display(name = "pre13Female", width=25, labelTop=true, label="13"),
    @Display(name = "pre14Female", width=25, labelTop=true, label="14"),
    @Display(name = "pre15Female", width=25, labelTop=true, label="15"),
    @Display(name = "pre16Female", width=25, labelTop=true, label="16"),
    @Display(name = "pre17Female", width=25, labelTop=true, label="17"),
    @Display(name = "pre18Female", width=25, labelTop=true, label="18"),
    @Display(name = "pre19Female", width=25, labelTop=true, label="19"),
    @Display(name = "pre20Female", width=25, labelTop=true, label="20"),
    
    @Display(name = "preClassesFemale", width=25, labelTop=true, label="Class", leftLabel="--"),
    @Display(name = "preCompleteFemale", width=25, labelTop=true, label="Pass"),
    @Display(name = "preDropFemale", width=25, labelTop=true, label="Drop"),
    @Display(name = "preTransferOutFemale", width=25, labelTop=true, label="Out"),
    @Display(name = "preFailedFemale", width=25, labelTop=true, label="Fail"),
    @Display(name = "preShiftFemale", width=25, labelTop=true, label="Shift"),

    @Display(name = "preMonoFemale", width=25, labelTop=true, label="Mono", leftLabel="--"),
    @Display(name = "preMultiFemale", width=25, labelTop=true, label="Multi"),
    @Display(name = "preSpedFemale", width=25, labelTop=true, label="Sped"),
    @Display(name = "preRepeatFemale", width=25, labelTop=true, label="Rpt"),
    @Display(name = "preTransferFemale", width=25, labelTop=true, label="Trans"),
    @Display(name = "preBackFemale", width=25, labelTop=true, label="Back"),
    @Display(name = "preMuslimFemale", width=25, labelTop=true, label="Musl"),
    @Display(name = "preIndigenousFemale", width=25, labelTop=true, label="Indi"),
    
    @Display(name = "preFemale", width=25, labelTop=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "preESCFemale", width=25, labelTop=true, label="ESC", leftLabel="GIRLS - PRE"),
    @Display(name = "preESCCompleteFemale", width=25, labelTop=true, label="Pass"),
    @Display(name = "preESCTransferOutFemale", width=25, labelTop=true, label="Out"),
    @Display(name = "preESCDropFemale", width=25, labelTop=true, label="Drop"),
    @Display(name = "preESCFailedFemale", width=25, labelTop=true, label="Fail"),

    @Display(name = "preEVSFemale", width=25, labelTop=true, label="EVS", leftLabel="--"),
    @Display(name = "preEVSCompleteFemale", width=25, labelTop=true, label="Pass"),
    @Display(name = "preEVSTransferOutFemale", width=25, labelTop=true, label="Out"),
    @Display(name = "preEVSDropFemale", width=25, labelTop=true, label="Drop"),
    @Display(name = "preEVSFailedFemale", width=25, labelTop=true, label="Fail"),

    @Display(name = "g14Female", width=25, label="Grade 1"),
    @Display(name = "g15Female", width=25, hideLabel=true, label="5"),
    @Display(name = "g16Female", width=25, hideLabel=true, label="6"),
    @Display(name = "g17Female", width=25, hideLabel=true, label="7"),
    @Display(name = "g18Female", width=25, hideLabel=true, label="8"),
    @Display(name = "g19Female", width=25, hideLabel=true, label="9"),
    @Display(name = "g110Female", width=25, hideLabel=true, label="10"),
    @Display(name = "g111Female", width=25, hideLabel=true, label="11"),
    @Display(name = "g112Female", width=25, hideLabel=true, label="12"),
    @Display(name = "g113Female", width=25, hideLabel=true, label="13"),
    @Display(name = "g114Female", width=25, hideLabel=true, label="14"),
    @Display(name = "g115Female", width=25, hideLabel=true, label="15"),
    @Display(name = "g116Female", width=25, hideLabel=true, label="16"),
    @Display(name = "g117Female", width=25, hideLabel=true, label="17"),
    @Display(name = "g118Female", width=25, hideLabel=true, label="18"),
    @Display(name = "g119Female", width=25, hideLabel=true, label="19"),
    @Display(name = "g120Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g1ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g1CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g1DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g1TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g1FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g1ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g1MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g1MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g1SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g1RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g1TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g1BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g1MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g1IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g1Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g1ESCFemale", width=25, label="Grade 1"),
    @Display(name = "g1ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g1ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g1ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g1ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g1EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g1EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g1EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g1EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g1EVSFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g24Female", width=25, label="Grade 2"),
    @Display(name = "g25Female", width=25, hideLabel=true, label="5"),
    @Display(name = "g26Female", width=25, hideLabel=true, label="6"),
    @Display(name = "g27Female", width=25, hideLabel=true, label="7"),
    @Display(name = "g28Female", width=25, hideLabel=true, label="8"),
    @Display(name = "g29Female", width=25, hideLabel=true, label="9"),
    @Display(name = "g210Female", width=25, hideLabel=true, label="10"),
    @Display(name = "g211Female", width=25, hideLabel=true, label="11"),
    @Display(name = "g212Female", width=25, hideLabel=true, label="12"),
    @Display(name = "g213Female", width=25, hideLabel=true, label="13"),
    @Display(name = "g214Female", width=25, hideLabel=true, label="14"),
    @Display(name = "g215Female", width=25, hideLabel=true, label="15"),
    @Display(name = "g216Female", width=25, hideLabel=true, label="16"),
    @Display(name = "g217Female", width=25, hideLabel=true, label="17"),
    @Display(name = "g218Female", width=25, hideLabel=true, label="18"),
    @Display(name = "g219Female", width=25, hideLabel=true, label="19"),
    @Display(name = "g220Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g2ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g2CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g2DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g2TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g2FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g2ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g2MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g2MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g2SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g2RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g2TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g2BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g2MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g2IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g2Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g2ESCFemale", width=25, label="Grade 2"),
    @Display(name = "g2ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g2ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g2ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g2ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g2EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g2EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g2EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g2EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g2EVSFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g34Female", width=25, label="Grade 3"),
    @Display(name = "g35Female", width=25, hideLabel=true, label="5"),
    @Display(name = "g36Female", width=25, hideLabel=true, label="6"),
    @Display(name = "g37Female", width=25, hideLabel=true, label="7"),
    @Display(name = "g38Female", width=25, hideLabel=true, label="8"),
    @Display(name = "g39Female", width=25, hideLabel=true, label="9"),
    @Display(name = "g310Female", width=25, hideLabel=true, label="10"),
    @Display(name = "g311Female", width=25, hideLabel=true, label="11"),
    @Display(name = "g312Female", width=25, hideLabel=true, label="12"),
    @Display(name = "g313Female", width=25, hideLabel=true, label="13"),
    @Display(name = "g314Female", width=25, hideLabel=true, label="14"),
    @Display(name = "g315Female", width=25, hideLabel=true, label="15"),
    @Display(name = "g316Female", width=25, hideLabel=true, label="16"),
    @Display(name = "g317Female", width=25, hideLabel=true, label="17"),
    @Display(name = "g318Female", width=25, hideLabel=true, label="18"),
    @Display(name = "g319Female", width=25, hideLabel=true, label="19"),
    @Display(name = "g320Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g3ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g3CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g3DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g3TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g3FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g3ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g3MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g3MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g3SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g3RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g3TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g3BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g3MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g3IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g3Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g3ESCFemale", width=25, label="Grade 3"),
    @Display(name = "g3ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g3ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g3ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g3ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g3EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g3EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g3EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g3EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g3EVSFailedFemale", width=25, hideLabel=true, label="Fail"),




    @Display(name = "g44Female", width=25, label="Grade 4"),
    @Display(name = "g45Female", width=25, hideLabel=true, label="5"),
    @Display(name = "g46Female", width=25, hideLabel=true, label="6"),
    @Display(name = "g47Female", width=25, hideLabel=true, label="7"),
    @Display(name = "g48Female", width=25, hideLabel=true, label="8"),
    @Display(name = "g49Female", width=25, hideLabel=true, label="9"),
    @Display(name = "g410Female", width=25, hideLabel=true, label="10"),
    @Display(name = "g411Female", width=25, hideLabel=true, label="11"),
    @Display(name = "g412Female", width=25, hideLabel=true, label="12"),
    @Display(name = "g413Female", width=25, hideLabel=true, label="13"),
    @Display(name = "g414Female", width=25, hideLabel=true, label="14"),
    @Display(name = "g415Female", width=25, hideLabel=true, label="15"),
    @Display(name = "g416Female", width=25, hideLabel=true, label="16"),
    @Display(name = "g417Female", width=25, hideLabel=true, label="17"),
    @Display(name = "g418Female", width=25, hideLabel=true, label="18"),
    @Display(name = "g419Female", width=25, hideLabel=true, label="19"),
    @Display(name = "g420Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g4ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g4CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g4DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g4TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g4FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g4ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g4MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g4MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g4SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g4RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g4TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g4BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g4MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g4IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g4Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g4ESCFemale", width=25, label="Grade 4"),
    @Display(name = "g4ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g4ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g4ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g4ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g4EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g4EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g4EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g4EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g4EVSFailedFemale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "g54Female", width=25, label="Grade 5"),
    @Display(name = "g55Female", width=25, hideLabel=true, label="5"),
    @Display(name = "g56Female", width=25, hideLabel=true, label="6"),
    @Display(name = "g57Female", width=25, hideLabel=true, label="7"),
    @Display(name = "g58Female", width=25, hideLabel=true, label="8"),
    @Display(name = "g59Female", width=25, hideLabel=true, label="9"),
    @Display(name = "g510Female", width=25, hideLabel=true, label="10"),
    @Display(name = "g511Female", width=25, hideLabel=true, label="11"),
    @Display(name = "g512Female", width=25, hideLabel=true, label="12"),
    @Display(name = "g513Female", width=25, hideLabel=true, label="13"),
    @Display(name = "g514Female", width=25, hideLabel=true, label="14"),
    @Display(name = "g515Female", width=25, hideLabel=true, label="15"),
    @Display(name = "g516Female", width=25, hideLabel=true, label="16"),
    @Display(name = "g517Female", width=25, hideLabel=true, label="17"),
    @Display(name = "g518Female", width=25, hideLabel=true, label="18"),
    @Display(name = "g519Female", width=25, hideLabel=true, label="19"),
    @Display(name = "g520Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g5ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g5CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g5DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g5TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g5FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g5ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g5MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g5MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g5SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g5RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g5TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g5BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g5MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g5IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g5Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g5ESCFemale", width=25, label="Grade 5"),
    @Display(name = "g5ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g5ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g5ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g5ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g5EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g5EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g5EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g5EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g5EVSFailedFemale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "g64Female", width=25, label="Grade 6"),
    @Display(name = "g65Female", width=25, hideLabel=true, label="5"),
    @Display(name = "g66Female", width=25, hideLabel=true, label="6"),
    @Display(name = "g67Female", width=25, hideLabel=true, label="7"),
    @Display(name = "g68Female", width=25, hideLabel=true, label="8"),
    @Display(name = "g69Female", width=25, hideLabel=true, label="9"),
    @Display(name = "g610Female", width=25, hideLabel=true, label="10"),
    @Display(name = "g611Female", width=25, hideLabel=true, label="11"),
    @Display(name = "g612Female", width=25, hideLabel=true, label="12"),
    @Display(name = "g613Female", width=25, hideLabel=true, label="13"),
    @Display(name = "g614Female", width=25, hideLabel=true, label="14"),
    @Display(name = "g615Female", width=25, hideLabel=true, label="15"),
    @Display(name = "g616Female", width=25, hideLabel=true, label="16"),
    @Display(name = "g617Female", width=25, hideLabel=true, label="17"),
    @Display(name = "g618Female", width=25, hideLabel=true, label="18"),
    @Display(name = "g619Female", width=25, hideLabel=true, label="19"),
    @Display(name = "g620Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "g6ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "g6CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g6DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g6TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g6FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "g6ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "g6MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "g6MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "g6SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "g6RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "g6TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "g6BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "g6MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "g6IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "g6Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "g6ESCFemale", width=25, label="Grade 6"),
    @Display(name = "g6ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g6ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g6ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g6ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "g6EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "g6EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "g6EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "g6EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "g6EVSFailedFemale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "h14Female", width=25, label="1st Yr"),
    @Display(name = "h15Female", width=25, hideLabel=true, label="5"),
    @Display(name = "h16Female", width=25, hideLabel=true, label="6"),
    @Display(name = "h17Female", width=25, hideLabel=true, label="7"),
    @Display(name = "h18Female", width=25, hideLabel=true, label="8"),
    @Display(name = "h19Female", width=25, hideLabel=true, label="9"),
    @Display(name = "h110Female", width=25, hideLabel=true, label="10"),
    @Display(name = "h111Female", width=25, hideLabel=true, label="11"),
    @Display(name = "h112Female", width=25, hideLabel=true, label="12"),
    @Display(name = "h113Female", width=25, hideLabel=true, label="13"),
    @Display(name = "h114Female", width=25, hideLabel=true, label="14"),
    @Display(name = "h115Female", width=25, hideLabel=true, label="15"),
    @Display(name = "h116Female", width=25, hideLabel=true, label="16"),
    @Display(name = "h117Female", width=25, hideLabel=true, label="17"),
    @Display(name = "h118Female", width=25, hideLabel=true, label="18"),
    @Display(name = "h119Female", width=25, hideLabel=true, label="19"),
    @Display(name = "h120Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "h1ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "h1CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h1DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h1TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h1FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "h1ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "h1MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "h1MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "h1SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "h1RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "h1TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "h1BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "h1MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "h1IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "h1Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "h1ESCFemale", width=25, label="1st Yr"),
    @Display(name = "h1ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h1ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h1ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h1ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "h1EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "h1EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h1EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h1EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h1EVSFailedFemale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "h24Female", width=25, label="2nd Yr"),
    @Display(name = "h25Female", width=25, hideLabel=true, label="5"),
    @Display(name = "h26Female", width=25, hideLabel=true, label="6"),
    @Display(name = "h27Female", width=25, hideLabel=true, label="7"),
    @Display(name = "h28Female", width=25, hideLabel=true, label="8"),
    @Display(name = "h29Female", width=25, hideLabel=true, label="9"),
    @Display(name = "h210Female", width=25, hideLabel=true, label="10"),
    @Display(name = "h211Female", width=25, hideLabel=true, label="11"),
    @Display(name = "h212Female", width=25, hideLabel=true, label="12"),
    @Display(name = "h213Female", width=25, hideLabel=true, label="13"),
    @Display(name = "h214Female", width=25, hideLabel=true, label="14"),
    @Display(name = "h215Female", width=25, hideLabel=true, label="15"),
    @Display(name = "h216Female", width=25, hideLabel=true, label="16"),
    @Display(name = "h217Female", width=25, hideLabel=true, label="17"),
    @Display(name = "h218Female", width=25, hideLabel=true, label="18"),
    @Display(name = "h219Female", width=25, hideLabel=true, label="19"),
    @Display(name = "h220Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "h2ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "h2CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h2DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h2TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h2FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "h2ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "h2MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "h2MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "h2SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "h2RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "h2TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "h2BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "h2MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "h2IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "h2Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "h2ESCFemale", width=25, label="2nd Yr"),
    @Display(name = "h2ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h2ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h2ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h2ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "h2EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "h2EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h2EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h2EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h2EVSFailedFemale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "h34Female", width=25, label="3rd Yr"),
    @Display(name = "h35Female", width=25, hideLabel=true, label="5"),
    @Display(name = "h36Female", width=25, hideLabel=true, label="6"),
    @Display(name = "h37Female", width=25, hideLabel=true, label="7"),
    @Display(name = "h38Female", width=25, hideLabel=true, label="8"),
    @Display(name = "h39Female", width=25, hideLabel=true, label="9"),
    @Display(name = "h310Female", width=25, hideLabel=true, label="10"),
    @Display(name = "h311Female", width=25, hideLabel=true, label="11"),
    @Display(name = "h312Female", width=25, hideLabel=true, label="12"),
    @Display(name = "h313Female", width=25, hideLabel=true, label="13"),
    @Display(name = "h314Female", width=25, hideLabel=true, label="14"),
    @Display(name = "h315Female", width=25, hideLabel=true, label="15"),
    @Display(name = "h316Female", width=25, hideLabel=true, label="16"),
    @Display(name = "h317Female", width=25, hideLabel=true, label="17"),
    @Display(name = "h318Female", width=25, hideLabel=true, label="18"),
    @Display(name = "h319Female", width=25, hideLabel=true, label="19"),
    @Display(name = "h320Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "h3ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "h3CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h3DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h3TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h3FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "h3ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "h3MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "h3MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "h3SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "h3RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "h3TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "h3BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "h3MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "h3IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "h3Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "h3ESCFemale", width=25, label="3rd Yr"),
    @Display(name = "h3ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h3ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h3ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h3ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "h3EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "h3EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h3EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h3EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h3EVSFailedFemale", width=25, hideLabel=true, label="Fail"),



    @Display(name = "h44Female", width=25, label="4th Yr"),
    @Display(name = "h45Female", width=25, hideLabel=true, label="5"),
    @Display(name = "h46Female", width=25, hideLabel=true, label="6"),
    @Display(name = "h47Female", width=25, hideLabel=true, label="7"),
    @Display(name = "h48Female", width=25, hideLabel=true, label="8"),
    @Display(name = "h49Female", width=25, hideLabel=true, label="9"),
    @Display(name = "h410Female", width=25, hideLabel=true, label="10"),
    @Display(name = "h411Female", width=25, hideLabel=true, label="11"),
    @Display(name = "h412Female", width=25, hideLabel=true, label="12"),
    @Display(name = "h413Female", width=25, hideLabel=true, label="13"),
    @Display(name = "h414Female", width=25, hideLabel=true, label="14"),
    @Display(name = "h415Female", width=25, hideLabel=true, label="15"),
    @Display(name = "h416Female", width=25, hideLabel=true, label="16"),
    @Display(name = "h417Female", width=25, hideLabel=true, label="17"),
    @Display(name = "h418Female", width=25, hideLabel=true, label="18"),
    @Display(name = "h419Female", width=25, hideLabel=true, label="19"),
    @Display(name = "h420Female", width=25, hideLabel=true, label="20"),
    
    @Display(name = "h4ClassesFemale", width=25, hideLabel=true, label="Class", leftLabel="--"),
    @Display(name = "h4CompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h4DropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h4TransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h4FailedFemale", width=25, hideLabel=true, label="Fail"),
    @Display(name = "h4ShiftFemale", width=25, hideLabel=true, label="Shift"),

    @Display(name = "h4MonoFemale", width=25, hideLabel=true, label="Mono", leftLabel="--"),
    @Display(name = "h4MultiFemale", width=25, hideLabel=true, label="Multi"),
    @Display(name = "h4SpedFemale", width=25, hideLabel=true, label="Sped"),
    @Display(name = "h4RepeatFemale", width=25, hideLabel=true, label="Rpt"),
    @Display(name = "h4TransferFemale", width=25, hideLabel=true, label="Trans"),
    @Display(name = "h4BackFemale", width=25, hideLabel=true, label="Back"),
    @Display(name = "h4MuslimFemale", width=25, hideLabel=true, label="Musl"),
    @Display(name = "h4IndigenousFemale", width=25, hideLabel=true, label="Indi"),
    
    @Display(name = "h4Female", width=25, hideLabel=true, label="Total", leftLabel="--"),
    
    
    @Display(name = "h4ESCFemale", width=25, label="4th Yr"),
    @Display(name = "h4ESCCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h4ESCTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h4ESCDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h4ESCFailedFemale", width=25, hideLabel=true, label="Fail"),

    @Display(name = "h4EVSFemale", width=25, hideLabel=true, label="EVS", leftLabel="--"),
    @Display(name = "h4EVSCompleteFemale", width=25, hideLabel=true, label="Pass"),
    @Display(name = "h4EVSTransferOutFemale", width=25, hideLabel=true, label="Out"),
    @Display(name = "h4EVSDropFemale", width=25, hideLabel=true, label="Drop"),
    @Display(name = "h4EVSFailedFemale", width=25, hideLabel=true, label="Fail"),
    
    



    @Display(name = "gsRoomInsAcademic", width=25, leftLabel="Grade School - Instructional", labelTop=true, label="Acad"),
    @Display(name = "gsRoomInsScienceLab", width=25, labelTop=true, label="SciLab"),
    @Display(name = "gsRoomInsHE", width=25, labelTop=true, label="HE"),
    @Display(name = "gsRoomInsWorkshop", width=25, labelTop=true, label="WShop"),
    @Display(name = "gsRoomInsComputer", width=25, labelTop=true, label="Comp"),
    @Display(name = "gsRoomInsNotUse", width=25, labelTop=true, label="Not"),

    @Display(name = "gsRoomLibrary", width=25, labelTop=true, label="Lib", leftLabel="   None Instructional"),
    @Display(name = "gsRoomClinic", width=25, labelTop=true, label="Clinic"),
    @Display(name = "gsRoomCanteen", width=25, labelTop=true, label="Cantn"),
    @Display(name = "gsRoomOffice", width=25, labelTop=true, label="Off"),
    @Display(name = "gsRoomOtherUse", width=25, labelTop=true, label="Other"),
    @Display(name = "gsRoomNotUse", width=25, labelTop=true, label="Not"),

    @Display(name = "gsDesk", width=25, labelTop=true, label="Desk", leftLabel="   Furniture"),
    @Display(name = "gsChairTable", width=25, labelTop=true, label="C&T"),
    @Display(name = "gsArmChair", width=25, labelTop=true, label="Arm"),
    
    @Display(name = "gsToiletShared", width=25, labelTop=true, label="Shrd", leftLabel="   Toilet"),
    @Display(name = "gsToiletGirlBowl", width=25, labelTop=true, label="Girls", leftLabel="   Bowl"),
    @Display(name = "gsToiletBoyBowl", width=25, labelTop=true, label="Boys"),
    @Display(name = "gsToiletBoyUrinalSingle", width=25, labelTop=true, label="Single", leftLabel="   Urinal"),
    @Display(name = "gsToiletBoyUrinalMultiple", width=25, labelTop=true, label="Multi"),
    
    @Display(name = "hsRoomInsAcademic", width=25, label="High School"),
    @Display(name = "hsRoomInsScienceLab", width=25, hideLabel=true, label="SciLab"),
    @Display(name = "hsRoomInsHE", width=25, hideLabel=true, label="HE"),
    @Display(name = "hsRoomInsWorkshop", width=25, hideLabel=true, label="WShop"),
    @Display(name = "hsRoomInsComputer", width=25, hideLabel=true, label="Comp"),
    @Display(name = "hsRoomInsNotUse", width=25, hideLabel=true, label="Not"),

    @Display(name = "hsRoomLibrary", width=25, hideLabel=true),
    @Display(name = "hsRoomClinic", width=25, hideLabel=true, label="Clinic"),
    @Display(name = "hsRoomCanteen", width=25, hideLabel=true, label="Cantn"),
    @Display(name = "hsRoomOffice", width=25, hideLabel=true, label="Off"),
    @Display(name = "hsRoomOtherUse", width=25, hideLabel=true, label="Other"),
    @Display(name = "hsRoomNotUse", width=25, hideLabel=true, label="Not"),

    @Display(name = "hsDesk", width=25, hideLabel=true, label="Desk"),
    @Display(name = "hsChairTable", width=25, hideLabel=true, label="C&T"),
    @Display(name = "hsArmChair", width=25, hideLabel=true, label="Arm"),
    
    @Display(name = "hsToiletShared", width=25, hideLabel=true, label="Shrd"),
    @Display(name = "hsToiletGirlBowl", width=25, hideLabel=true, label="Girls"),
    @Display(name = "hsToiletBoyBowl", width=25, hideLabel=true, label="Boys"),
    @Display(name = "hsToiletBoyUrinalSingle", width=25, hideLabel=true, label="Single"),
    @Display(name = "hsToiletBoyUrinalMultiple", width=25, hideLabel=true, label="Multi"),
    

    
    @Display(name = "preMaleFacultyFulltime", labelTop=true, leftLabel="PRE - Male", label="FT", width=30),
    @Display(name = "preMaleFacultyParttime", labelTop=true, label="Part", width=30),
    @Display(name = "preMaleFacultyAdmin", labelTop=true, label="Admin", width=30),

    @Display(name = "preFemaleFacultyFulltime", labelTop=true, leftLabel="Female", label="FT", width=30),
    @Display(name = "preFemaleFacultyParttime", labelTop=true, label="Part", width=30),
    @Display(name = "preFemaleFacultyAdmin", labelTop=true, label="Admin", width=30),
    
    
    @Display(name = "gsMaleFacultyFulltime", label="Grade School", width=30),
    @Display(name = "gsMaleFacultyParttime", hideLabel=true, width=30),
    @Display(name = "gsMaleFacultyAdmin", hideLabel=true, width=30),

    @Display(name = "gsFemaleFacultyFulltime", hideLabel=true, width=30),
    @Display(name = "gsFemaleFacultyParttime", hideLabel=true, width=30),
    @Display(name = "gsFemaleFacultyAdmin", hideLabel=true, width=30),


    @Display(name = "hsMaleFacultyFulltime", label="High School", width=30),
    @Display(name = "hsMaleFacultyParttime", hideLabel=true, width=30),
    @Display(name = "hsMaleFacultyAdmin", hideLabel=true, width=30),

    @Display(name = "hsFemaleFacultyFulltime", hideLabel=true, width=30),
    @Display(name = "hsFemaleFacultyParttime", hideLabel=true, width=30),
    @Display(name = "hsFemaleFacultyAdmin", hideLabel=true, width=30)
})
@DisplayGroups({
    @DisplayGroup(gridCount=64, title="Enrollment", fields={
    		"pre4Male","pre5Male","pre6Male","pre7Male","pre8Male",
    		"pre9Male","pre10Male","pre11Male","pre12Male",
    		"pre13Male","pre14Male","pre15Male","pre16Male",
    		"pre17Male","pre18Male","pre19Male","pre20Male",
    		    
    		"preMonoMale","preMultiMale","preSpedMale",
    		"preRepeatMale","preTransferMale","preBackMale",
    		"preMuslimMale","preIndigenousMale","preClassesMale",
    		"preCompleteMale","preDropMale","preTransferOutMale",
    		"preFailedMale","preShiftMale","preMale",
    		
    		
    		"g14Male","g15Male","g16Male","g17Male","g18Male",
    		"g19Male","g110Male","g111Male","g112Male",
    		"g113Male","g114Male","g115Male","g116Male",
    		"g117Male","g118Male","g119Male","g120Male",
    		    
    		"g1MonoMale","g1MultiMale","g1SpedMale",
    		"g1RepeatMale","g1TransferMale","g1BackMale",
    		"g1MuslimMale","g1IndigenousMale","g1ClassesMale",
    		"g1CompleteMale","g1DropMale","g1TransferOutMale",
    		"g1FailedMale","g1ShiftMale","g1Male",


    		"g24Male","g25Male","g26Male","g27Male","g28Male",
    		"g29Male","g210Male","g211Male","g212Male",
    		"g213Male","g214Male","g215Male","g216Male",
    		"g217Male","g218Male","g219Male","g220Male",
    		    
    		"g2MonoMale","g2MultiMale","g2SpedMale",
    		"g2RepeatMale","g2TransferMale","g2BackMale",
    		"g2MuslimMale","g2IndigenousMale","g2ClassesMale",
    		"g2CompleteMale","g2DropMale","g2TransferOutMale",
    		"g2FailedMale","g2ShiftMale","g2Male",


    		"g34Male","g35Male","g36Male","g37Male","g38Male",
    		"g39Male","g310Male","g311Male","g312Male",
    		"g313Male","g314Male","g315Male","g316Male",
    		"g317Male","g318Male","g319Male","g320Male",
    		    
    		"g3MonoMale","g3MultiMale","g3SpedMale",
    		"g3RepeatMale","g3TransferMale","g3BackMale",
    		"g3MuslimMale","g3IndigenousMale","g3ClassesMale",
    		"g3CompleteMale","g3DropMale","g3TransferOutMale",
    		"g3FailedMale","g3ShiftMale","g3Male",


    		"g44Male","g45Male","g46Male","g47Male","g48Male",
    		"g49Male","g410Male","g411Male","g412Male",
    		"g413Male","g414Male","g415Male","g416Male",
    		"g417Male","g418Male","g419Male","g420Male",
    		    
    		"g4MonoMale","g4MultiMale","g4SpedMale",
    		"g4RepeatMale","g4TransferMale","g4BackMale",
    		"g4MuslimMale","g4IndigenousMale","g4ClassesMale",
    		"g4CompleteMale","g4DropMale","g4TransferOutMale",
    		"g4FailedMale","g4ShiftMale","g4Male",


    		"g54Male","g55Male","g56Male","g57Male","g58Male",
    		"g59Male","g510Male","g511Male","g512Male",
    		"g513Male","g514Male","g515Male","g516Male",
    		"g517Male","g518Male","g519Male","g520Male",
    		    
    		"g5MonoMale","g5MultiMale","g5SpedMale",
    		"g5RepeatMale","g5TransferMale","g5BackMale",
    		"g5MuslimMale","g5IndigenousMale","g5ClassesMale",
    		"g5CompleteMale","g5DropMale","g5TransferOutMale",
    		"g5FailedMale","g5ShiftMale","g5Male",


    		"g64Male","g65Male","g66Male","g67Male","g68Male",
    		"g69Male","g610Male","g611Male","g612Male",
    		"g613Male","g614Male","g615Male","g616Male",
    		"g617Male","g618Male","g619Male","g620Male",
    		    
    		"g6MonoMale","g6MultiMale","g6SpedMale",
    		"g6RepeatMale","g6TransferMale","g6BackMale",
    		"g6MuslimMale","g6IndigenousMale","g6ClassesMale",
    		"g6CompleteMale","g6DropMale","g6TransferOutMale",
    		"g6FailedMale","g6ShiftMale","g6Male",


    		"h14Male","h15Male","h16Male","h17Male","h18Male",
    		"h19Male","h110Male","h111Male","h112Male",
    		"h113Male","h114Male","h115Male","h116Male",
    		"h117Male","h118Male","h119Male","h120Male",
    		    
    		"h1MonoMale","h1MultiMale","h1SpedMale",
    		"h1RepeatMale","h1TransferMale","h1BackMale",
    		"h1MuslimMale","h1IndigenousMale","h1ClassesMale",
    		"h1CompleteMale","h1DropMale","h1TransferOutMale",
    		"h1FailedMale","h1ShiftMale","h1Male",


    		"h24Male","h25Male","h26Male","h27Male","h28Male",
    		"h29Male","h210Male","h211Male","h212Male",
    		"h213Male","h214Male","h215Male","h216Male",
    		"h217Male","h218Male","h219Male","h220Male",
    		    
    		"h2MonoMale","h2MultiMale","h2SpedMale",
    		"h2RepeatMale","h2TransferMale","h2BackMale",
    		"h2MuslimMale","h2IndigenousMale","h2ClassesMale",
    		"h2CompleteMale","h2DropMale","h2TransferOutMale",
    		"h2FailedMale","h2ShiftMale","h2Male",


    		"h34Male","h35Male","h36Male","h37Male","h38Male",
    		"h39Male","h310Male","h311Male","h312Male",
    		"h313Male","h314Male","h315Male","h316Male",
    		"h317Male","h318Male","h319Male","h320Male",
    		    
    		"h3MonoMale","h3MultiMale","h3SpedMale",
    		"h3RepeatMale","h3TransferMale","h3BackMale",
    		"h3MuslimMale","h3IndigenousMale","h3ClassesMale",
    		"h3CompleteMale","h3DropMale","h3TransferOutMale",
    		"h3FailedMale","h3ShiftMale","h3Male",


    		"h44Male","h45Male","h46Male","h47Male","h48Male",
    		"h49Male","h410Male","h411Male","h412Male",
    		"h413Male","h414Male","h415Male","h416Male",
    		"h417Male","h418Male","h419Male","h420Male",
    		    
    		"h4MonoMale","h4MultiMale","h4SpedMale",
    		"h4RepeatMale","h4TransferMale","h4BackMale",
    		"h4MuslimMale","h4IndigenousMale","h4ClassesMale",
    		"h4CompleteMale","h4DropMale","h4TransferOutMale",
    		"h4FailedMale","h4ShiftMale","h4Male",


    		"pre4Female","pre5Female","pre6Female","pre7Female","pre8Female",
    		"pre9Female","pre10Female","pre11Female","pre12Female",
    		"pre13Female","pre14Female","pre15Female","pre16Female",
    		"pre17Female","pre18Female","pre19Female","pre20Female",
    		    
    		"preMonoFemale","preMultiFemale","preSpedFemale",
    		"preRepeatFemale","preTransferFemale","preBackFemale",
    		"preMuslimFemale","preIndigenousFemale","preClassesFemale",
    		"preCompleteFemale","preDropFemale","preTransferOutFemale",
    		"preFailedFemale","preShiftFemale","preFemale",
    		
    		
    		"g14Female","g15Female","g16Female","g17Female","g18Female",
    		"g19Female","g110Female","g111Female","g112Female",
    		"g113Female","g114Female","g115Female","g116Female",
    		"g117Female","g118Female","g119Female","g120Female",
    		    
    		"g1MonoFemale","g1MultiFemale","g1SpedFemale",
    		"g1RepeatFemale","g1TransferFemale","g1BackFemale",
    		"g1MuslimFemale","g1IndigenousFemale","g1ClassesFemale",
    		"g1CompleteFemale","g1DropFemale","g1TransferOutFemale",
    		"g1FailedFemale","g1ShiftFemale","g1Female",


    		"g24Female","g25Female","g26Female","g27Female","g28Female",
    		"g29Female","g210Female","g211Female","g212Female",
    		"g213Female","g214Female","g215Female","g216Female",
    		"g217Female","g218Female","g219Female","g220Female",
    		    
    		"g2MonoFemale","g2MultiFemale","g2SpedFemale",
    		"g2RepeatFemale","g2TransferFemale","g2BackFemale",
    		"g2MuslimFemale","g2IndigenousFemale","g2ClassesFemale",
    		"g2CompleteFemale","g2DropFemale","g2TransferOutFemale",
    		"g2FailedFemale","g2ShiftFemale","g2Female",


    		"g34Female","g35Female","g36Female","g37Female","g38Female",
    		"g39Female","g310Female","g311Female","g312Female",
    		"g313Female","g314Female","g315Female","g316Female",
    		"g317Female","g318Female","g319Female","g320Female",
    		    
    		"g3MonoFemale","g3MultiFemale","g3SpedFemale",
    		"g3RepeatFemale","g3TransferFemale","g3BackFemale",
    		"g3MuslimFemale","g3IndigenousFemale","g3ClassesFemale",
    		"g3CompleteFemale","g3DropFemale","g3TransferOutFemale",
    		"g3FailedFemale","g3ShiftFemale","g3Female",


    		"g44Female","g45Female","g46Female","g47Female","g48Female",
    		"g49Female","g410Female","g411Female","g412Female",
    		"g413Female","g414Female","g415Female","g416Female",
    		"g417Female","g418Female","g419Female","g420Female",
    		    
    		"g4MonoFemale","g4MultiFemale","g4SpedFemale",
    		"g4RepeatFemale","g4TransferFemale","g4BackFemale",
    		"g4MuslimFemale","g4IndigenousFemale","g4ClassesFemale",
    		"g4CompleteFemale","g4DropFemale","g4TransferOutFemale",
    		"g4FailedFemale","g4ShiftFemale","g4Female",


    		"g54Female","g55Female","g56Female","g57Female","g58Female",
    		"g59Female","g510Female","g511Female","g512Female",
    		"g513Female","g514Female","g515Female","g516Female",
    		"g517Female","g518Female","g519Female","g520Female",
    		    
    		"g5MonoFemale","g5MultiFemale","g5SpedFemale",
    		"g5RepeatFemale","g5TransferFemale","g5BackFemale",
    		"g5MuslimFemale","g5IndigenousFemale","g5ClassesFemale",
    		"g5CompleteFemale","g5DropFemale","g5TransferOutFemale",
    		"g5FailedFemale","g5ShiftFemale","g5Female",


    		"g64Female","g65Female","g66Female","g67Female","g68Female",
    		"g69Female","g610Female","g611Female","g612Female",
    		"g613Female","g614Female","g615Female","g616Female",
    		"g617Female","g618Female","g619Female","g620Female",
    		    
    		"g6MonoFemale","g6MultiFemale","g6SpedFemale",
    		"g6RepeatFemale","g6TransferFemale","g6BackFemale",
    		"g6MuslimFemale","g6IndigenousFemale","g6ClassesFemale",
    		"g6CompleteFemale","g6DropFemale","g6TransferOutFemale",
    		"g6FailedFemale","g6ShiftFemale","g6Female",


    		"h14Female","h15Female","h16Female","h17Female","h18Female",
    		"h19Female","h110Female","h111Female","h112Female",
    		"h113Female","h114Female","h115Female","h116Female",
    		"h117Female","h118Female","h119Female","h120Female",
    		    
    		"h1MonoFemale","h1MultiFemale","h1SpedFemale",
    		"h1RepeatFemale","h1TransferFemale","h1BackFemale",
    		"h1MuslimFemale","h1IndigenousFemale","h1ClassesFemale",
    		"h1CompleteFemale","h1DropFemale","h1TransferOutFemale",
    		"h1FailedFemale","h1ShiftFemale","h1Female",


    		"h24Female","h25Female","h26Female","h27Female","h28Female",
    		"h29Female","h210Female","h211Female","h212Female",
    		"h213Female","h214Female","h215Female","h216Female",
    		"h217Female","h218Female","h219Female","h220Female",
    		    
    		"h2MonoFemale","h2MultiFemale","h2SpedFemale",
    		"h2RepeatFemale","h2TransferFemale","h2BackFemale",
    		"h2MuslimFemale","h2IndigenousFemale","h2ClassesFemale",
    		"h2CompleteFemale","h2DropFemale","h2TransferOutFemale",
    		"h2FailedFemale","h2ShiftFemale","h2Female",


    		"h34Female","h35Female","h36Female","h37Female","h38Female",
    		"h39Female","h310Female","h311Female","h312Female",
    		"h313Female","h314Female","h315Female","h316Female",
    		"h317Female","h318Female","h319Female","h320Female",
    		    
    		"h3MonoFemale","h3MultiFemale","h3SpedFemale",
    		"h3RepeatFemale","h3TransferFemale","h3BackFemale",
    		"h3MuslimFemale","h3IndigenousFemale","h3ClassesFemale",
    		"h3CompleteFemale","h3DropFemale","h3TransferOutFemale",
    		"h3FailedFemale","h3ShiftFemale","h3Female",


    		"h44Female","h45Female","h46Female","h47Female","h48Female",
    		"h49Female","h410Female","h411Female","h412Female",
    		"h413Female","h414Female","h415Female","h416Female",
    		"h417Female","h418Female","h419Female","h420Female",
    		    
    		"h4MonoFemale","h4MultiFemale","h4SpedFemale",
    		"h4RepeatFemale","h4TransferFemale","h4BackFemale",
    		"h4MuslimFemale","h4IndigenousFemale","h4ClassesFemale",
    		"h4CompleteFemale","h4DropFemale","h4TransferOutFemale",
    		"h4FailedFemale","h4ShiftFemale","h4Female"
    }, addInfoOnly=true),
    @DisplayGroup(gridCount=20, title="ESC/EVS Info", fields={
    		"preESCMale","preEVSMale","preESCCompleteMale","preEVSCompleteMale",
    		"preESCTransferOutMale","preEVSTransferOutMale","preESCDropMale",
    		"preEVSDropMale","preESCFailedMale","preEVSFailedMale",


    		"g1ESCMale","g1EVSMale","g1ESCCompleteMale","g1EVSCompleteMale",
    		"g1ESCTransferOutMale","g1EVSTransferOutMale","g1ESCDropMale",
    		"g1EVSDropMale","g1ESCFailedMale","g1EVSFailedMale",


    		"g2ESCMale","g2EVSMale","g2ESCCompleteMale","g2EVSCompleteMale",
    		"g2ESCTransferOutMale","g2EVSTransferOutMale","g2ESCDropMale",
    		"g2EVSDropMale","g2ESCFailedMale","g2EVSFailedMale",


    		"g3ESCMale","g3EVSMale","g3ESCCompleteMale","g3EVSCompleteMale",
    		"g3ESCTransferOutMale","g3EVSTransferOutMale","g3ESCDropMale",
    		"g3EVSDropMale","g3ESCFailedMale","g3EVSFailedMale",


    		"g4ESCMale","g4EVSMale","g4ESCCompleteMale","g4EVSCompleteMale",
    		"g4ESCTransferOutMale","g4EVSTransferOutMale","g4ESCDropMale",
    		"g4EVSDropMale","g4ESCFailedMale","g4EVSFailedMale",


    		"g5ESCMale","g5EVSMale","g5ESCCompleteMale","g5EVSCompleteMale",
    		"g5ESCTransferOutMale","g5EVSTransferOutMale","g5ESCDropMale",
    		"g5EVSDropMale","g5ESCFailedMale","g5EVSFailedMale",


    		"g6ESCMale","g6EVSMale","g6ESCCompleteMale","g6EVSCompleteMale",
    		"g6ESCTransferOutMale","g6EVSTransferOutMale","g6ESCDropMale",
    		"g6EVSDropMale","g6ESCFailedMale","g6EVSFailedMale",


    		"h1ESCMale","h1EVSMale","h1ESCCompleteMale","h1EVSCompleteMale",
    		"h1ESCTransferOutMale","h1EVSTransferOutMale","h1ESCDropMale",
    		"h1EVSDropMale","h1ESCFailedMale","h1EVSFailedMale",


    		"h2ESCMale","h2EVSMale","h2ESCCompleteMale","h2EVSCompleteMale",
    		"h2ESCTransferOutMale","h2EVSTransferOutMale","h2ESCDropMale",
    		"h2EVSDropMale","h2ESCFailedMale","h2EVSFailedMale",


    		"h3ESCMale","h3EVSMale","h3ESCCompleteMale","h3EVSCompleteMale",
    		"h3ESCTransferOutMale","h3EVSTransferOutMale","h3ESCDropMale",
    		"h3EVSDropMale","h3ESCFailedMale","h3EVSFailedMale",


    		"h4ESCMale","h4EVSMale","h4ESCCompleteMale","h4EVSCompleteMale",
    		"h4ESCTransferOutMale","h4EVSTransferOutMale","h4ESCDropMale",
    		"h4EVSDropMale","h4ESCFailedMale","h4EVSFailedMale",


    		"preESCFemale","preEVSFemale","preESCCompleteFemale","preEVSCompleteFemale",
    		"preESCTransferOutFemale","preEVSTransferOutFemale","preESCDropFemale",
    		"preEVSDropFemale","preESCFailedFemale","preEVSFailedFemale",


    		"g1ESCFemale","g1EVSFemale","g1ESCCompleteFemale","g1EVSCompleteFemale",
    		"g1ESCTransferOutFemale","g1EVSTransferOutFemale","g1ESCDropFemale",
    		"g1EVSDropFemale","g1ESCFailedFemale","g1EVSFailedFemale",


    		"g2ESCFemale","g2EVSFemale","g2ESCCompleteFemale","g2EVSCompleteFemale",
    		"g2ESCTransferOutFemale","g2EVSTransferOutFemale","g2ESCDropFemale",
    		"g2EVSDropFemale","g2ESCFailedFemale","g2EVSFailedFemale",


    		"g3ESCFemale","g3EVSFemale","g3ESCCompleteFemale","g3EVSCompleteFemale",
    		"g3ESCTransferOutFemale","g3EVSTransferOutFemale","g3ESCDropFemale",
    		"g3EVSDropFemale","g3ESCFailedFemale","g3EVSFailedFemale",


    		"g4ESCFemale","g4EVSFemale","g4ESCCompleteFemale","g4EVSCompleteFemale",
    		"g4ESCTransferOutFemale","g4EVSTransferOutFemale","g4ESCDropFemale",
    		"g4EVSDropFemale","g4ESCFailedFemale","g4EVSFailedFemale",


    		"g5ESCFemale","g5EVSFemale","g5ESCCompleteFemale","g5EVSCompleteFemale",
    		"g5ESCTransferOutFemale","g5EVSTransferOutFemale","g5ESCDropFemale",
    		"g5EVSDropFemale","g5ESCFailedFemale","g5EVSFailedFemale",


    		"g6ESCFemale","g6EVSFemale","g6ESCCompleteFemale","g6EVSCompleteFemale",
    		"g6ESCTransferOutFemale","g6EVSTransferOutFemale","g6ESCDropFemale",
    		"g6EVSDropFemale","g6ESCFailedFemale","g6EVSFailedFemale",


    		"h1ESCFemale","h1EVSFemale","h1ESCCompleteFemale","h1EVSCompleteFemale",
    		"h1ESCTransferOutFemale","h1EVSTransferOutFemale","h1ESCDropFemale",
    		"h1EVSDropFemale","h1ESCFailedFemale","h1EVSFailedFemale",


    		"h2ESCFemale","h2EVSFemale","h2ESCCompleteFemale","h2EVSCompleteFemale",
    		"h2ESCTransferOutFemale","h2EVSTransferOutFemale","h2ESCDropFemale",
    		"h2EVSDropFemale","h2ESCFailedFemale","h2EVSFailedFemale",


    		"h3ESCFemale","h3EVSFemale","h3ESCCompleteFemale","h3EVSCompleteFemale",
    		"h3ESCTransferOutFemale","h3EVSTransferOutFemale","h3ESCDropFemale",
    		"h3EVSDropFemale","h3ESCFailedFemale","h3EVSFailedFemale",


    		"h4ESCFemale","h4EVSFemale","h4ESCCompleteFemale","h4EVSCompleteFemale",
    		"h4ESCTransferOutFemale","h4EVSTransferOutFemale","h4ESCDropFemale",
    		"h4EVSDropFemale","h4ESCFailedFemale","h4EVSFailedFemale"
    }, addInfoOnly=true),
    @DisplayGroup(gridCount=40, title="Rooms and Furnitures", fields={
    		"gsRoomInsAcademic","gsRoomInsScienceLab","gsRoomInsHE","gsRoomInsWorkshop","gsRoomInsComputer","gsRoomInsNotUse",
    		"gsRoomLibrary","gsRoomClinic","gsRoomCanteen","gsRoomOffice","gsRoomOtherUse","gsRoomNotUse",
    		"gsDesk","gsChairTable","gsArmChair",    
    		"gsToiletGirlBowl","gsToiletBoyUrinalSingle","gsToiletBoyUrinalMultiple","gsToiletBoyBowl","gsToiletShared",
    		
    		"hsRoomInsAcademic","hsRoomInsScienceLab","hsRoomInsHE","hsRoomInsWorkshop","hsRoomInsComputer","hsRoomInsNotUse",
    		"hsRoomLibrary","hsRoomClinic","hsRoomCanteen","hsRoomOffice","hsRoomOtherUse","hsRoomNotUse",
    		"hsDesk","hsChairTable","hsArmChair",    
    		"hsToiletGirlBowl","hsToiletBoyUrinalSingle","hsToiletBoyUrinalMultiple","hsToiletBoyBowl","hsToiletShared"
    }, addInfoOnly=true),
    @DisplayGroup(gridCount=12, title="Faculty And Admin", fields={
    		"preMaleFacultyFulltime","preMaleFacultyParttime","preMaleFacultyAdmin",
    		"preFemaleFacultyFulltime","preFemaleFacultyParttime","preFemaleFacultyAdmin",
    	    
    	    
    		"gsMaleFacultyFulltime","gsMaleFacultyParttime","gsMaleFacultyAdmin",
    		"gsFemaleFacultyFulltime","gsFemaleFacultyParttime","gsFemaleFacultyAdmin",


    		"hsMaleFacultyFulltime","hsMaleFacultyParttime","hsMaleFacultyAdmin",
    		"hsFemaleFacultyFulltime","hsFemaleFacultyParttime","hsFemaleFacultyAdmin"
    	    }, addInfoOnly=true)
})
@ChildRecords(value = { //@ChildRecord(template=ChildTemplateListPopup.class, fieldMapping={"seq","admissionId"}, entity=AdmissionExamReference.class, sql="SELECT a FROM AdmissionExam a WHERE a.admissionId=${seq} ORDER BY a.examType", title="Exams")
},
info = {
    @ParentAddInfo(title = "Pupil Data", gridCount = 36,displayFields={},hideGroup="1,2,3"),
    @ParentAddInfo(title = "ESC/EVS Profile", gridCount = 2,displayFields={},hideGroup="0,2,3"),
    @ParentAddInfo(title = "Room and Furnitures", gridCount = 4,displayFields={},hideGroup="0,1,3"),
    @ParentAddInfo(title = "Admin and Faculty", gridCount = 4,displayFields={},hideGroup="0,1,2")
})
@ActionButtons({
    @ActionButton(name = "btnExtractEnrollment", label = "Extract Current Enrollment")
})
@Reports( {
		@template.Report(reportFile = "PrivateSchoolProfile", reportTitle = "School Profile", reportSql = "")
})
        
public class SchoolProfileSummary extends AbstractIBean implements Serializable {
	public SchoolProfileSummary() {
		schoolYear = AppConfig.getSchoolYear();
	}

	public static void main(String[] args) {
        view(SchoolProfileSummary.class);
    }

    @Id
    @Column(name = "schoolYear", nullable = false, length = 20)
    public String schoolYear;

    @Column(name = "preMale")
    public int preMale;
    @Column(name = "pre4Male")
    public int pre4Male;
    @Column(name = "pre5Male")
    public int pre5Male;
    @Column(name = "pre6Male")
    public int pre6Male;
    @Column(name = "pre7Male")
    public int pre7Male;
    @Column(name = "pre8Male")
    public int pre8Male;
    @Column(name = "pre9Male")
    public int pre9Male;
    @Column(name = "pre10Male")
    public int pre10Male;
    @Column(name = "pre11Male")
    public int pre11Male;
    @Column(name = "pre12Male")
    public int pre12Male;
    @Column(name = "pre13Male")
    public int pre13Male;
    @Column(name = "pre14Male")
    public int pre14Male;
    @Column(name = "pre15Male")
    public int pre15Male;
    @Column(name = "pre16Male")
    public int pre16Male;
    @Column(name = "pre17Male")
    public int pre17Male;
    @Column(name = "pre18Male")
    public int pre18Male;
    @Column(name = "pre19Male")
    public int pre19Male;
    @Column(name = "pre20Male")
    public int pre20Male;
    @Column(name = "preMonoMale")
    public int preMonoMale;
    @Column(name = "preMultiMale")
    public int preMultiMale;
    @Column(name = "preSpedMale")
    public int preSpedMale;
    @Column(name = "preRepeatMale")
    public int preRepeatMale;
    @Column(name = "preTransferMale")
    public int preTransferMale;
    @Column(name = "preBackMale")
    public int preBackMale;
    @Column(name = "preMuslimMale")
    public int preMuslimMale;
    @Column(name = "preIndigenousMale")
    public int preIndigenousMale;
    @Column(name = "preClassesMale")
    public int preClassesMale;
    @Column(name = "preCompleteMale")
    public int preCompleteMale;
    @Column(name = "preDropMale")
    public int preDropMale;
    @Column(name = "preTransferOutMale")
    public int preTransferOutMale;
    @Column(name = "preFailedMale")
    public int preFailedMale;
    @Column(name = "preShiftMale")
    public int preShiftMale;
    @Column(name = "preESCMale")
    public int preESCMale;
    @Column(name = "preEVSMale")
    public int preEVSMale;
    @Column(name = "preESCCompleteMale")
    public int preESCCompleteMale;
    @Column(name = "preEVSCompleteMale")
    public int preEVSCompleteMale;
    @Column(name = "preESCTransferOutMale")
    public int preESCTransferOutMale;
    @Column(name = "preEVSTransferOutMale")
    public int preEVSTransferOutMale;
    @Column(name = "preESCDropMale")
    public int preESCDropMale;
    @Column(name = "preEVSDropMale")
    public int preEVSDropMale;
    @Column(name = "preESCFailedMale")
    public int preESCFailedMale;
    @Column(name = "preEVSFailedMale")
    public int preEVSFailedMale;



    @Column(name = "preFemale")
    public int preFemale;
    @Column(name = "pre4Female")
    public int pre4Female;
    @Column(name = "pre5Female")
    public int pre5Female;
    @Column(name = "pre6Female")
    public int pre6Female;
    @Column(name = "pre7Female")
    public int pre7Female;
    @Column(name = "pre8Female")
    public int pre8Female;
    @Column(name = "pre9Female")
    public int pre9Female;
    @Column(name = "pre10Female")
    public int pre10Female;
    @Column(name = "pre11Female")
    public int pre11Female;
    @Column(name = "pre12Female")
    public int pre12Female;
    @Column(name = "pre13Female")
    public int pre13Female;
    @Column(name = "pre14Female")
    public int pre14Female;
    @Column(name = "pre15Female")
    public int pre15Female;
    @Column(name = "pre16Female")
    public int pre16Female;
    @Column(name = "pre17Female")
    public int pre17Female;
    @Column(name = "pre18Female")
    public int pre18Female;
    @Column(name = "pre19Female")
    public int pre19Female;
    @Column(name = "pre20Female")
    public int pre20Female;

    @Column(name = "preMonoFemale")
    public int preMonoFemale;
    @Column(name = "preMultiFemale")
    public int preMultiFemale;
    @Column(name = "preSpedFemale")
    public int preSpedFemale;
    @Column(name = "preRepeatFemale")
    public int preRepeatFemale;
    @Column(name = "preTransferFemale")
    public int preTransferFemale;
    @Column(name = "preBackFemale")
    public int preBackFemale;
    @Column(name = "preMuslimFemale")
    public int preMuslimFemale;
    @Column(name = "preIndigenousFemale")
    public int preIndigenousFemale;

    @Column(name = "preClassesFemale")
    public int preClassesFemale;
    @Column(name = "preCompleteFemale")
    public int preCompleteFemale;
    @Column(name = "preDropFemale")
    public int preDropFemale;
    @Column(name = "preTransferOutFemale")
    public int preTransferOutFemale;
    @Column(name = "preFailedFemale")
    public int preFailedFemale;

    @Column(name = "preShiftFemale")
    public int preShiftFemale;

    @Column(name = "preESCFemale")
    public int preESCFemale;
    @Column(name = "preEVSFemale")
    public int preEVSFemale;
    @Column(name = "preESCCompleteFemale")
    public int preESCCompleteFemale;
    @Column(name = "preEVSCompleteFemale")
    public int preEVSCompleteFemale;
    @Column(name = "preESCTransferOutFemale")
    public int preESCTransferOutFemale;
    @Column(name = "preEVSTransferOutFemale")
    public int preEVSTransferOutFemale;
    @Column(name = "preESCDropFemale")
    public int preESCDropFemale;
    @Column(name = "preEVSDropFemale")
    public int preEVSDropFemale;
    @Column(name = "preESCFailedFemale")
    public int preESCFailedFemale;
    @Column(name = "preEVSFailedFemale")
    public int preEVSFailedFemale;



    @Column(name = "g1Male")
    public int g1Male;
    @Column(name = "g14Male")
    public int g14Male;
    @Column(name = "g15Male")
    public int g15Male;
    @Column(name = "g16Male")
    public int g16Male;
    @Column(name = "g17Male")
    public int g17Male;
    @Column(name = "g18Male")
    public int g18Male;
    @Column(name = "g19Male")
    public int g19Male;
    @Column(name = "g110Male")
    public int g110Male;
    @Column(name = "g111Male")
    public int g111Male;
    @Column(name = "g112Male")
    public int g112Male;
    @Column(name = "g113Male")
    public int g113Male;
    @Column(name = "g114Male")
    public int g114Male;
    @Column(name = "g115Male")
    public int g115Male;
    @Column(name = "g116Male")
    public int g116Male;
    @Column(name = "g117Male")
    public int g117Male;
    @Column(name = "g118Male")
    public int g118Male;
    @Column(name = "g119Male")
    public int g119Male;
    @Column(name = "g120Male")
    public int g120Male;
    @Column(name = "g1MonoMale")
    public int g1MonoMale;
    @Column(name = "g1MultiMale")
    public int g1MultiMale;
    @Column(name = "g1SpedMale")
    public int g1SpedMale;
    @Column(name = "g1RepeatMale")
    public int g1RepeatMale;
    @Column(name = "g1TransferMale")
    public int g1TransferMale;
    @Column(name = "g1BackMale")
    public int g1BackMale;
    @Column(name = "g1MuslimMale")
    public int g1MuslimMale;
    @Column(name = "g1IndigenousMale")
    public int g1IndigenousMale;
    @Column(name = "g1ClassesMale")
    public int g1ClassesMale;
    @Column(name = "g1CompleteMale")
    public int g1CompleteMale;
    @Column(name = "g1DropMale")
    public int g1DropMale;
    @Column(name = "g1TransferOutMale")
    public int g1TransferOutMale;
    @Column(name = "g1FailedMale")
    public int g1FailedMale;
    @Column(name = "g1ShiftMale")
    public int g1ShiftMale;
    @Column(name = "g1ESCMale")
    public int g1ESCMale;
    @Column(name = "g1EVSMale")
    public int g1EVSMale;
    @Column(name = "g1ESCCompleteMale")
    public int g1ESCCompleteMale;
    @Column(name = "g1EVSCompleteMale")
    public int g1EVSCompleteMale;
    @Column(name = "g1ESCTransferOutMale")
    public int g1ESCTransferOutMale;
    @Column(name = "g1EVSTransferOutMale")
    public int g1EVSTransferOutMale;
    @Column(name = "g1ESCDropMale")
    public int g1ESCDropMale;
    @Column(name = "g1EVSDropMale")
    public int g1EVSDropMale;
    @Column(name = "g1ESCFailedMale")
    public int g1ESCFailedMale;
    @Column(name = "g1EVSFailedMale")
    public int g1EVSFailedMale;



    @Column(name = "g1Female")
    public int g1Female;
    @Column(name = "g14Female")
    public int g14Female;
    @Column(name = "g15Female")
    public int g15Female;
    @Column(name = "g16Female")
    public int g16Female;
    @Column(name = "g17Female")
    public int g17Female;
    @Column(name = "g18Female")
    public int g18Female;
    @Column(name = "g19Female")
    public int g19Female;
    @Column(name = "g110Female")
    public int g110Female;
    @Column(name = "g111Female")
    public int g111Female;
    @Column(name = "g112Female")
    public int g112Female;
    @Column(name = "g113Female")
    public int g113Female;
    @Column(name = "g114Female")
    public int g114Female;
    @Column(name = "g115Female")
    public int g115Female;
    @Column(name = "g116Female")
    public int g116Female;
    @Column(name = "g117Female")
    public int g117Female;
    @Column(name = "g118Female")
    public int g118Female;
    @Column(name = "g119Female")
    public int g119Female;
    @Column(name = "g120Female")
    public int g120Female;

    @Column(name = "g1MonoFemale")
    public int g1MonoFemale;
    @Column(name = "g1MultiFemale")
    public int g1MultiFemale;
    @Column(name = "g1SpedFemale")
    public int g1SpedFemale;
    @Column(name = "g1RepeatFemale")
    public int g1RepeatFemale;
    @Column(name = "g1TransferFemale")
    public int g1TransferFemale;
    @Column(name = "g1BackFemale")
    public int g1BackFemale;
    @Column(name = "g1MuslimFemale")
    public int g1MuslimFemale;
    @Column(name = "g1IndigenousFemale")
    public int g1IndigenousFemale;

    @Column(name = "g1ClassesFemale")
    public int g1ClassesFemale;
    @Column(name = "g1CompleteFemale")
    public int g1CompleteFemale;
    @Column(name = "g1DropFemale")
    public int g1DropFemale;
    @Column(name = "g1TransferOutFemale")
    public int g1TransferOutFemale;
    @Column(name = "g1FailedFemale")
    public int g1FailedFemale;

    @Column(name = "g1ShiftFemale")
    public int g1ShiftFemale;

    @Column(name = "g1ESCFemale")
    public int g1ESCFemale;
    @Column(name = "g1EVSFemale")
    public int g1EVSFemale;
    @Column(name = "g1ESCCompleteFemale")
    public int g1ESCCompleteFemale;
    @Column(name = "g1EVSCompleteFemale")
    public int g1EVSCompleteFemale;
    @Column(name = "g1ESCTransferOutFemale")
    public int g1ESCTransferOutFemale;
    @Column(name = "g1EVSTransferOutFemale")
    public int g1EVSTransferOutFemale;
    @Column(name = "g1ESCDropFemale")
    public int g1ESCDropFemale;
    @Column(name = "g1EVSDropFemale")
    public int g1EVSDropFemale;
    @Column(name = "g1ESCFailedFemale")
    public int g1ESCFailedFemale;
    @Column(name = "g1EVSFailedFemale")
    public int g1EVSFailedFemale;




    @Column(name = "g2Male")
    public int g2Male;
    @Column(name = "g24Male")
    public int g24Male;
    @Column(name = "g25Male")
    public int g25Male;
    @Column(name = "g26Male")
    public int g26Male;
    @Column(name = "g27Male")
    public int g27Male;
    @Column(name = "g28Male")
    public int g28Male;
    @Column(name = "g29Male")
    public int g29Male;
    @Column(name = "g210Male")
    public int g210Male;
    @Column(name = "g211Male")
    public int g211Male;
    @Column(name = "g212Male")
    public int g212Male;
    @Column(name = "g213Male")
    public int g213Male;
    @Column(name = "g214Male")
    public int g214Male;
    @Column(name = "g215Male")
    public int g215Male;
    @Column(name = "g216Male")
    public int g216Male;
    @Column(name = "g217Male")
    public int g217Male;
    @Column(name = "g218Male")
    public int g218Male;
    @Column(name = "g219Male")
    public int g219Male;
    @Column(name = "g220Male")
    public int g220Male;
    @Column(name = "g2MonoMale")
    public int g2MonoMale;
    @Column(name = "g2MultiMale")
    public int g2MultiMale;
    @Column(name = "g2SpedMale")
    public int g2SpedMale;
    @Column(name = "g2RepeatMale")
    public int g2RepeatMale;
    @Column(name = "g2TransferMale")
    public int g2TransferMale;
    @Column(name = "g2BackMale")
    public int g2BackMale;
    @Column(name = "g2MuslimMale")
    public int g2MuslimMale;
    @Column(name = "g2IndigenousMale")
    public int g2IndigenousMale;
    @Column(name = "g2ClassesMale")
    public int g2ClassesMale;
    @Column(name = "g2CompleteMale")
    public int g2CompleteMale;
    @Column(name = "g2DropMale")
    public int g2DropMale;
    @Column(name = "g2TransferOutMale")
    public int g2TransferOutMale;
    @Column(name = "g2FailedMale")
    public int g2FailedMale;
    @Column(name = "g2ShiftMale")
    public int g2ShiftMale;
    @Column(name = "g2ESCMale")
    public int g2ESCMale;
    @Column(name = "g2EVSMale")
    public int g2EVSMale;
    @Column(name = "g2ESCCompleteMale")
    public int g2ESCCompleteMale;
    @Column(name = "g2EVSCompleteMale")
    public int g2EVSCompleteMale;
    @Column(name = "g2ESCTransferOutMale")
    public int g2ESCTransferOutMale;
    @Column(name = "g2EVSTransferOutMale")
    public int g2EVSTransferOutMale;
    @Column(name = "g2ESCDropMale")
    public int g2ESCDropMale;
    @Column(name = "g2EVSDropMale")
    public int g2EVSDropMale;
    @Column(name = "g2ESCFailedMale")
    public int g2ESCFailedMale;
    @Column(name = "g2EVSFailedMale")
    public int g2EVSFailedMale;



    @Column(name = "g2Female")
    public int g2Female;
    @Column(name = "g24Female")
    public int g24Female;
    @Column(name = "g25Female")
    public int g25Female;
    @Column(name = "g26Female")
    public int g26Female;
    @Column(name = "g27Female")
    public int g27Female;
    @Column(name = "g28Female")
    public int g28Female;
    @Column(name = "g29Female")
    public int g29Female;
    @Column(name = "g210Female")
    public int g210Female;
    @Column(name = "g211Female")
    public int g211Female;
    @Column(name = "g212Female")
    public int g212Female;
    @Column(name = "g213Female")
    public int g213Female;
    @Column(name = "g214Female")
    public int g214Female;
    @Column(name = "g215Female")
    public int g215Female;
    @Column(name = "g216Female")
    public int g216Female;
    @Column(name = "g217Female")
    public int g217Female;
    @Column(name = "g218Female")
    public int g218Female;
    @Column(name = "g219Female")
    public int g219Female;
    @Column(name = "g220Female")
    public int g220Female;

    @Column(name = "g2MonoFemale")
    public int g2MonoFemale;
    @Column(name = "g2MultiFemale")
    public int g2MultiFemale;
    @Column(name = "g2SpedFemale")
    public int g2SpedFemale;
    @Column(name = "g2RepeatFemale")
    public int g2RepeatFemale;
    @Column(name = "g2TransferFemale")
    public int g2TransferFemale;
    @Column(name = "g2BackFemale")
    public int g2BackFemale;
    @Column(name = "g2MuslimFemale")
    public int g2MuslimFemale;
    @Column(name = "g2IndigenousFemale")
    public int g2IndigenousFemale;

    @Column(name = "g2ClassesFemale")
    public int g2ClassesFemale;
    @Column(name = "g2CompleteFemale")
    public int g2CompleteFemale;
    @Column(name = "g2DropFemale")
    public int g2DropFemale;
    @Column(name = "g2TransferOutFemale")
    public int g2TransferOutFemale;
    @Column(name = "g2FailedFemale")
    public int g2FailedFemale;

    @Column(name = "g2ShiftFemale")
    public int g2ShiftFemale;

    @Column(name = "g2ESCFemale")
    public int g2ESCFemale;
    @Column(name = "g2EVSFemale")
    public int g2EVSFemale;
    @Column(name = "g2ESCCompleteFemale")
    public int g2ESCCompleteFemale;
    @Column(name = "g2EVSCompleteFemale")
    public int g2EVSCompleteFemale;
    @Column(name = "g2ESCTransferOutFemale")
    public int g2ESCTransferOutFemale;
    @Column(name = "g2EVSTransferOutFemale")
    public int g2EVSTransferOutFemale;
    @Column(name = "g2ESCDropFemale")
    public int g2ESCDropFemale;
    @Column(name = "g2EVSDropFemale")
    public int g2EVSDropFemale;
    @Column(name = "g2ESCFailedFemale")
    public int g2ESCFailedFemale;
    @Column(name = "g2EVSFailedFemale")
    public int g2EVSFailedFemale;




    @Column(name = "g3Male")
    public int g3Male;
    @Column(name = "g34Male")
    public int g34Male;
    @Column(name = "g35Male")
    public int g35Male;
    @Column(name = "g36Male")
    public int g36Male;
    @Column(name = "g37Male")
    public int g37Male;
    @Column(name = "g38Male")
    public int g38Male;
    @Column(name = "g39Male")
    public int g39Male;
    @Column(name = "g310Male")
    public int g310Male;
    @Column(name = "g311Male")
    public int g311Male;
    @Column(name = "g312Male")
    public int g312Male;
    @Column(name = "g313Male")
    public int g313Male;
    @Column(name = "g314Male")
    public int g314Male;
    @Column(name = "g315Male")
    public int g315Male;
    @Column(name = "g316Male")
    public int g316Male;
    @Column(name = "g317Male")
    public int g317Male;
    @Column(name = "g318Male")
    public int g318Male;
    @Column(name = "g319Male")
    public int g319Male;
    @Column(name = "g320Male")
    public int g320Male;
    @Column(name = "g3MonoMale")
    public int g3MonoMale;
    @Column(name = "g3MultiMale")
    public int g3MultiMale;
    @Column(name = "g3SpedMale")
    public int g3SpedMale;
    @Column(name = "g3RepeatMale")
    public int g3RepeatMale;
    @Column(name = "g3TransferMale")
    public int g3TransferMale;
    @Column(name = "g3BackMale")
    public int g3BackMale;
    @Column(name = "g3MuslimMale")
    public int g3MuslimMale;
    @Column(name = "g3IndigenousMale")
    public int g3IndigenousMale;
    @Column(name = "g3ClassesMale")
    public int g3ClassesMale;
    @Column(name = "g3CompleteMale")
    public int g3CompleteMale;
    @Column(name = "g3DropMale")
    public int g3DropMale;
    @Column(name = "g3TransferOutMale")
    public int g3TransferOutMale;
    @Column(name = "g3FailedMale")
    public int g3FailedMale;
    @Column(name = "g3ShiftMale")
    public int g3ShiftMale;
    @Column(name = "g3ESCMale")
    public int g3ESCMale;
    @Column(name = "g3EVSMale")
    public int g3EVSMale;
    @Column(name = "g3ESCCompleteMale")
    public int g3ESCCompleteMale;
    @Column(name = "g3EVSCompleteMale")
    public int g3EVSCompleteMale;
    @Column(name = "g3ESCTransferOutMale")
    public int g3ESCTransferOutMale;
    @Column(name = "g3EVSTransferOutMale")
    public int g3EVSTransferOutMale;
    @Column(name = "g3ESCDropMale")
    public int g3ESCDropMale;
    @Column(name = "g3EVSDropMale")
    public int g3EVSDropMale;
    @Column(name = "g3ESCFailedMale")
    public int g3ESCFailedMale;
    @Column(name = "g3EVSFailedMale")
    public int g3EVSFailedMale;



    @Column(name = "g3Female")
    public int g3Female;
    @Column(name = "g34Female")
    public int g34Female;
    @Column(name = "g35Female")
    public int g35Female;
    @Column(name = "g36Female")
    public int g36Female;
    @Column(name = "g37Female")
    public int g37Female;
    @Column(name = "g38Female")
    public int g38Female;
    @Column(name = "g39Female")
    public int g39Female;
    @Column(name = "g310Female")
    public int g310Female;
    @Column(name = "g311Female")
    public int g311Female;
    @Column(name = "g312Female")
    public int g312Female;
    @Column(name = "g313Female")
    public int g313Female;
    @Column(name = "g314Female")
    public int g314Female;
    @Column(name = "g315Female")
    public int g315Female;
    @Column(name = "g316Female")
    public int g316Female;
    @Column(name = "g317Female")
    public int g317Female;
    @Column(name = "g318Female")
    public int g318Female;
    @Column(name = "g319Female")
    public int g319Female;
    @Column(name = "g320Female")
    public int g320Female;

    @Column(name = "g3MonoFemale")
    public int g3MonoFemale;
    @Column(name = "g3MultiFemale")
    public int g3MultiFemale;
    @Column(name = "g3SpedFemale")
    public int g3SpedFemale;
    @Column(name = "g3RepeatFemale")
    public int g3RepeatFemale;
    @Column(name = "g3TransferFemale")
    public int g3TransferFemale;
    @Column(name = "g3BackFemale")
    public int g3BackFemale;
    @Column(name = "g3MuslimFemale")
    public int g3MuslimFemale;
    @Column(name = "g3IndigenousFemale")
    public int g3IndigenousFemale;

    @Column(name = "g3ClassesFemale")
    public int g3ClassesFemale;
    @Column(name = "g3CompleteFemale")
    public int g3CompleteFemale;
    @Column(name = "g3DropFemale")
    public int g3DropFemale;
    @Column(name = "g3TransferOutFemale")
    public int g3TransferOutFemale;
    @Column(name = "g3FailedFemale")
    public int g3FailedFemale;

    @Column(name = "g3ShiftFemale")
    public int g3ShiftFemale;

    @Column(name = "g3ESCFemale")
    public int g3ESCFemale;
    @Column(name = "g3EVSFemale")
    public int g3EVSFemale;
    @Column(name = "g3ESCCompleteFemale")
    public int g3ESCCompleteFemale;
    @Column(name = "g3EVSCompleteFemale")
    public int g3EVSCompleteFemale;
    @Column(name = "g3ESCTransferOutFemale")
    public int g3ESCTransferOutFemale;
    @Column(name = "g3EVSTransferOutFemale")
    public int g3EVSTransferOutFemale;
    @Column(name = "g3ESCDropFemale")
    public int g3ESCDropFemale;
    @Column(name = "g3EVSDropFemale")
    public int g3EVSDropFemale;
    @Column(name = "g3ESCFailedFemale")
    public int g3ESCFailedFemale;
    @Column(name = "g3EVSFailedFemale")
    public int g3EVSFailedFemale;




    @Column(name = "g4Male")
    public int g4Male;
    @Column(name = "g44Male")
    public int g44Male;
    @Column(name = "g45Male")
    public int g45Male;
    @Column(name = "g46Male")
    public int g46Male;
    @Column(name = "g47Male")
    public int g47Male;
    @Column(name = "g48Male")
    public int g48Male;
    @Column(name = "g49Male")
    public int g49Male;
    @Column(name = "g410Male")
    public int g410Male;
    @Column(name = "g411Male")
    public int g411Male;
    @Column(name = "g412Male")
    public int g412Male;
    @Column(name = "g413Male")
    public int g413Male;
    @Column(name = "g414Male")
    public int g414Male;
    @Column(name = "g415Male")
    public int g415Male;
    @Column(name = "g416Male")
    public int g416Male;
    @Column(name = "g417Male")
    public int g417Male;
    @Column(name = "g418Male")
    public int g418Male;
    @Column(name = "g419Male")
    public int g419Male;
    @Column(name = "g420Male")
    public int g420Male;
    @Column(name = "g4MonoMale")
    public int g4MonoMale;
    @Column(name = "g4MultiMale")
    public int g4MultiMale;
    @Column(name = "g4SpedMale")
    public int g4SpedMale;
    @Column(name = "g4RepeatMale")
    public int g4RepeatMale;
    @Column(name = "g4TransferMale")
    public int g4TransferMale;
    @Column(name = "g4BackMale")
    public int g4BackMale;
    @Column(name = "g4MuslimMale")
    public int g4MuslimMale;
    @Column(name = "g4IndigenousMale")
    public int g4IndigenousMale;
    @Column(name = "g4ClassesMale")
    public int g4ClassesMale;
    @Column(name = "g4CompleteMale")
    public int g4CompleteMale;
    @Column(name = "g4DropMale")
    public int g4DropMale;
    @Column(name = "g4TransferOutMale")
    public int g4TransferOutMale;
    @Column(name = "g4FailedMale")
    public int g4FailedMale;
    @Column(name = "g4ShiftMale")
    public int g4ShiftMale;
    @Column(name = "g4ESCMale")
    public int g4ESCMale;
    @Column(name = "g4EVSMale")
    public int g4EVSMale;
    @Column(name = "g4ESCCompleteMale")
    public int g4ESCCompleteMale;
    @Column(name = "g4EVSCompleteMale")
    public int g4EVSCompleteMale;
    @Column(name = "g4ESCTransferOutMale")
    public int g4ESCTransferOutMale;
    @Column(name = "g4EVSTransferOutMale")
    public int g4EVSTransferOutMale;
    @Column(name = "g4ESCDropMale")
    public int g4ESCDropMale;
    @Column(name = "g4EVSDropMale")
    public int g4EVSDropMale;
    @Column(name = "g4ESCFailedMale")
    public int g4ESCFailedMale;
    @Column(name = "g4EVSFailedMale")
    public int g4EVSFailedMale;



    @Column(name = "g4Female")
    public int g4Female;
    @Column(name = "g44Female")
    public int g44Female;
    @Column(name = "g45Female")
    public int g45Female;
    @Column(name = "g46Female")
    public int g46Female;
    @Column(name = "g47Female")
    public int g47Female;
    @Column(name = "g48Female")
    public int g48Female;
    @Column(name = "g49Female")
    public int g49Female;
    @Column(name = "g410Female")
    public int g410Female;
    @Column(name = "g411Female")
    public int g411Female;
    @Column(name = "g412Female")
    public int g412Female;
    @Column(name = "g413Female")
    public int g413Female;
    @Column(name = "g414Female")
    public int g414Female;
    @Column(name = "g415Female")
    public int g415Female;
    @Column(name = "g416Female")
    public int g416Female;
    @Column(name = "g417Female")
    public int g417Female;
    @Column(name = "g418Female")
    public int g418Female;
    @Column(name = "g419Female")
    public int g419Female;
    @Column(name = "g420Female")
    public int g420Female;

    @Column(name = "g4MonoFemale")
    public int g4MonoFemale;
    @Column(name = "g4MultiFemale")
    public int g4MultiFemale;
    @Column(name = "g4SpedFemale")
    public int g4SpedFemale;
    @Column(name = "g4RepeatFemale")
    public int g4RepeatFemale;
    @Column(name = "g4TransferFemale")
    public int g4TransferFemale;
    @Column(name = "g4BackFemale")
    public int g4BackFemale;
    @Column(name = "g4MuslimFemale")
    public int g4MuslimFemale;
    @Column(name = "g4IndigenousFemale")
    public int g4IndigenousFemale;

    @Column(name = "g4ClassesFemale")
    public int g4ClassesFemale;
    @Column(name = "g4CompleteFemale")
    public int g4CompleteFemale;
    @Column(name = "g4DropFemale")
    public int g4DropFemale;
    @Column(name = "g4TransferOutFemale")
    public int g4TransferOutFemale;
    @Column(name = "g4FailedFemale")
    public int g4FailedFemale;

    @Column(name = "g4ShiftFemale")
    public int g4ShiftFemale;

    @Column(name = "g4ESCFemale")
    public int g4ESCFemale;
    @Column(name = "g4EVSFemale")
    public int g4EVSFemale;
    @Column(name = "g4ESCCompleteFemale")
    public int g4ESCCompleteFemale;
    @Column(name = "g4EVSCompleteFemale")
    public int g4EVSCompleteFemale;
    @Column(name = "g4ESCTransferOutFemale")
    public int g4ESCTransferOutFemale;
    @Column(name = "g4EVSTransferOutFemale")
    public int g4EVSTransferOutFemale;
    @Column(name = "g4ESCDropFemale")
    public int g4ESCDropFemale;
    @Column(name = "g4EVSDropFemale")
    public int g4EVSDropFemale;
    @Column(name = "g4ESCFailedFemale")
    public int g4ESCFailedFemale;
    @Column(name = "g4EVSFailedFemale")
    public int g4EVSFailedFemale;




    @Column(name = "g5Male")
    public int g5Male;
    @Column(name = "g54Male")
    public int g54Male;
    @Column(name = "g55Male")
    public int g55Male;
    @Column(name = "g56Male")
    public int g56Male;
    @Column(name = "g57Male")
    public int g57Male;
    @Column(name = "g58Male")
    public int g58Male;
    @Column(name = "g59Male")
    public int g59Male;
    @Column(name = "g510Male")
    public int g510Male;
    @Column(name = "g511Male")
    public int g511Male;
    @Column(name = "g512Male")
    public int g512Male;
    @Column(name = "g513Male")
    public int g513Male;
    @Column(name = "g514Male")
    public int g514Male;
    @Column(name = "g515Male")
    public int g515Male;
    @Column(name = "g516Male")
    public int g516Male;
    @Column(name = "g517Male")
    public int g517Male;
    @Column(name = "g518Male")
    public int g518Male;
    @Column(name = "g519Male")
    public int g519Male;
    @Column(name = "g520Male")
    public int g520Male;
    @Column(name = "g5MonoMale")
    public int g5MonoMale;
    @Column(name = "g5MultiMale")
    public int g5MultiMale;
    @Column(name = "g5SpedMale")
    public int g5SpedMale;
    @Column(name = "g5RepeatMale")
    public int g5RepeatMale;
    @Column(name = "g5TransferMale")
    public int g5TransferMale;
    @Column(name = "g5BackMale")
    public int g5BackMale;
    @Column(name = "g5MuslimMale")
    public int g5MuslimMale;
    @Column(name = "g5IndigenousMale")
    public int g5IndigenousMale;
    @Column(name = "g5ClassesMale")
    public int g5ClassesMale;
    @Column(name = "g5CompleteMale")
    public int g5CompleteMale;
    @Column(name = "g5DropMale")
    public int g5DropMale;
    @Column(name = "g5TransferOutMale")
    public int g5TransferOutMale;
    @Column(name = "g5FailedMale")
    public int g5FailedMale;
    @Column(name = "g5ShiftMale")
    public int g5ShiftMale;
    @Column(name = "g5ESCMale")
    public int g5ESCMale;
    @Column(name = "g5EVSMale")
    public int g5EVSMale;
    @Column(name = "g5ESCCompleteMale")
    public int g5ESCCompleteMale;
    @Column(name = "g5EVSCompleteMale")
    public int g5EVSCompleteMale;
    @Column(name = "g5ESCTransferOutMale")
    public int g5ESCTransferOutMale;
    @Column(name = "g5EVSTransferOutMale")
    public int g5EVSTransferOutMale;
    @Column(name = "g5ESCDropMale")
    public int g5ESCDropMale;
    @Column(name = "g5EVSDropMale")
    public int g5EVSDropMale;
    @Column(name = "g5ESCFailedMale")
    public int g5ESCFailedMale;
    @Column(name = "g5EVSFailedMale")
    public int g5EVSFailedMale;



    @Column(name = "g5Female")
    public int g5Female;
    @Column(name = "g54Female")
    public int g54Female;
    @Column(name = "g55Female")
    public int g55Female;
    @Column(name = "g56Female")
    public int g56Female;
    @Column(name = "g57Female")
    public int g57Female;
    @Column(name = "g58Female")
    public int g58Female;
    @Column(name = "g59Female")
    public int g59Female;
    @Column(name = "g510Female")
    public int g510Female;
    @Column(name = "g511Female")
    public int g511Female;
    @Column(name = "g512Female")
    public int g512Female;
    @Column(name = "g513Female")
    public int g513Female;
    @Column(name = "g514Female")
    public int g514Female;
    @Column(name = "g515Female")
    public int g515Female;
    @Column(name = "g516Female")
    public int g516Female;
    @Column(name = "g517Female")
    public int g517Female;
    @Column(name = "g518Female")
    public int g518Female;
    @Column(name = "g519Female")
    public int g519Female;
    @Column(name = "g520Female")
    public int g520Female;

    @Column(name = "g5MonoFemale")
    public int g5MonoFemale;
    @Column(name = "g5MultiFemale")
    public int g5MultiFemale;
    @Column(name = "g5SpedFemale")
    public int g5SpedFemale;
    @Column(name = "g5RepeatFemale")
    public int g5RepeatFemale;
    @Column(name = "g5TransferFemale")
    public int g5TransferFemale;
    @Column(name = "g5BackFemale")
    public int g5BackFemale;
    @Column(name = "g5MuslimFemale")
    public int g5MuslimFemale;
    @Column(name = "g5IndigenousFemale")
    public int g5IndigenousFemale;

    @Column(name = "g5ClassesFemale")
    public int g5ClassesFemale;
    @Column(name = "g5CompleteFemale")
    public int g5CompleteFemale;
    @Column(name = "g5DropFemale")
    public int g5DropFemale;
    @Column(name = "g5TransferOutFemale")
    public int g5TransferOutFemale;
    @Column(name = "g5FailedFemale")
    public int g5FailedFemale;

    @Column(name = "g5ShiftFemale")
    public int g5ShiftFemale;

    @Column(name = "g5ESCFemale")
    public int g5ESCFemale;
    @Column(name = "g5EVSFemale")
    public int g5EVSFemale;
    @Column(name = "g5ESCCompleteFemale")
    public int g5ESCCompleteFemale;
    @Column(name = "g5EVSCompleteFemale")
    public int g5EVSCompleteFemale;
    @Column(name = "g5ESCTransferOutFemale")
    public int g5ESCTransferOutFemale;
    @Column(name = "g5EVSTransferOutFemale")
    public int g5EVSTransferOutFemale;
    @Column(name = "g5ESCDropFemale")
    public int g5ESCDropFemale;
    @Column(name = "g5EVSDropFemale")
    public int g5EVSDropFemale;
    @Column(name = "g5ESCFailedFemale")
    public int g5ESCFailedFemale;
    @Column(name = "g5EVSFailedFemale")
    public int g5EVSFailedFemale;




    @Column(name = "g6Male")
    public int g6Male;
    @Column(name = "g64Male")
    public int g64Male;
    @Column(name = "g65Male")
    public int g65Male;
    @Column(name = "g66Male")
    public int g66Male;
    @Column(name = "g67Male")
    public int g67Male;
    @Column(name = "g68Male")
    public int g68Male;
    @Column(name = "g69Male")
    public int g69Male;
    @Column(name = "g610Male")
    public int g610Male;
    @Column(name = "g611Male")
    public int g611Male;
    @Column(name = "g612Male")
    public int g612Male;
    @Column(name = "g613Male")
    public int g613Male;
    @Column(name = "g614Male")
    public int g614Male;
    @Column(name = "g615Male")
    public int g615Male;
    @Column(name = "g616Male")
    public int g616Male;
    @Column(name = "g617Male")
    public int g617Male;
    @Column(name = "g618Male")
    public int g618Male;
    @Column(name = "g619Male")
    public int g619Male;
    @Column(name = "g620Male")
    public int g620Male;
    @Column(name = "g6MonoMale")
    public int g6MonoMale;
    @Column(name = "g6MultiMale")
    public int g6MultiMale;
    @Column(name = "g6SpedMale")
    public int g6SpedMale;
    @Column(name = "g6RepeatMale")
    public int g6RepeatMale;
    @Column(name = "g6TransferMale")
    public int g6TransferMale;
    @Column(name = "g6BackMale")
    public int g6BackMale;
    @Column(name = "g6MuslimMale")
    public int g6MuslimMale;
    @Column(name = "g6IndigenousMale")
    public int g6IndigenousMale;
    @Column(name = "g6ClassesMale")
    public int g6ClassesMale;
    @Column(name = "g6CompleteMale")
    public int g6CompleteMale;
    @Column(name = "g6DropMale")
    public int g6DropMale;
    @Column(name = "g6TransferOutMale")
    public int g6TransferOutMale;
    @Column(name = "g6FailedMale")
    public int g6FailedMale;
    @Column(name = "g6ShiftMale")
    public int g6ShiftMale;
    @Column(name = "g6ESCMale")
    public int g6ESCMale;
    @Column(name = "g6EVSMale")
    public int g6EVSMale;
    @Column(name = "g6ESCCompleteMale")
    public int g6ESCCompleteMale;
    @Column(name = "g6EVSCompleteMale")
    public int g6EVSCompleteMale;
    @Column(name = "g6ESCTransferOutMale")
    public int g6ESCTransferOutMale;
    @Column(name = "g6EVSTransferOutMale")
    public int g6EVSTransferOutMale;
    @Column(name = "g6ESCDropMale")
    public int g6ESCDropMale;
    @Column(name = "g6EVSDropMale")
    public int g6EVSDropMale;
    @Column(name = "g6ESCFailedMale")
    public int g6ESCFailedMale;
    @Column(name = "g6EVSFailedMale")
    public int g6EVSFailedMale;



    @Column(name = "g6Female")
    public int g6Female;
    @Column(name = "g64Female")
    public int g64Female;
    @Column(name = "g65Female")
    public int g65Female;
    @Column(name = "g66Female")
    public int g66Female;
    @Column(name = "g67Female")
    public int g67Female;
    @Column(name = "g68Female")
    public int g68Female;
    @Column(name = "g69Female")
    public int g69Female;
    @Column(name = "g610Female")
    public int g610Female;
    @Column(name = "g611Female")
    public int g611Female;
    @Column(name = "g612Female")
    public int g612Female;
    @Column(name = "g613Female")
    public int g613Female;
    @Column(name = "g614Female")
    public int g614Female;
    @Column(name = "g615Female")
    public int g615Female;
    @Column(name = "g616Female")
    public int g616Female;
    @Column(name = "g617Female")
    public int g617Female;
    @Column(name = "g618Female")
    public int g618Female;
    @Column(name = "g619Female")
    public int g619Female;
    @Column(name = "g620Female")
    public int g620Female;

    @Column(name = "g6MonoFemale")
    public int g6MonoFemale;
    @Column(name = "g6MultiFemale")
    public int g6MultiFemale;
    @Column(name = "g6SpedFemale")
    public int g6SpedFemale;
    @Column(name = "g6RepeatFemale")
    public int g6RepeatFemale;
    @Column(name = "g6TransferFemale")
    public int g6TransferFemale;
    @Column(name = "g6BackFemale")
    public int g6BackFemale;
    @Column(name = "g6MuslimFemale")
    public int g6MuslimFemale;
    @Column(name = "g6IndigenousFemale")
    public int g6IndigenousFemale;

    @Column(name = "g6ClassesFemale")
    public int g6ClassesFemale;
    @Column(name = "g6CompleteFemale")
    public int g6CompleteFemale;
    @Column(name = "g6DropFemale")
    public int g6DropFemale;
    @Column(name = "g6TransferOutFemale")
    public int g6TransferOutFemale;
    @Column(name = "g6FailedFemale")
    public int g6FailedFemale;

    @Column(name = "g6ShiftFemale")
    public int g6ShiftFemale;

    @Column(name = "g6ESCFemale")
    public int g6ESCFemale;
    @Column(name = "g6EVSFemale")
    public int g6EVSFemale;
    @Column(name = "g6ESCCompleteFemale")
    public int g6ESCCompleteFemale;
    @Column(name = "g6EVSCompleteFemale")
    public int g6EVSCompleteFemale;
    @Column(name = "g6ESCTransferOutFemale")
    public int g6ESCTransferOutFemale;
    @Column(name = "g6EVSTransferOutFemale")
    public int g6EVSTransferOutFemale;
    @Column(name = "g6ESCDropFemale")
    public int g6ESCDropFemale;
    @Column(name = "g6EVSDropFemale")
    public int g6EVSDropFemale;
    @Column(name = "g6ESCFailedFemale")
    public int g6ESCFailedFemale;
    @Column(name = "g6EVSFailedFemale")
    public int g6EVSFailedFemale;




    @Column(name = "h1Male")
    public int h1Male;
    @Column(name = "h14Male")
    public int h14Male;
    @Column(name = "h15Male")
    public int h15Male;
    @Column(name = "h16Male")
    public int h16Male;
    @Column(name = "h17Male")
    public int h17Male;
    @Column(name = "h18Male")
    public int h18Male;
    @Column(name = "h19Male")
    public int h19Male;
    @Column(name = "h110Male")
    public int h110Male;
    @Column(name = "h111Male")
    public int h111Male;
    @Column(name = "h112Male")
    public int h112Male;
    @Column(name = "h113Male")
    public int h113Male;
    @Column(name = "h114Male")
    public int h114Male;
    @Column(name = "h115Male")
    public int h115Male;
    @Column(name = "h116Male")
    public int h116Male;
    @Column(name = "h117Male")
    public int h117Male;
    @Column(name = "h118Male")
    public int h118Male;
    @Column(name = "h119Male")
    public int h119Male;
    @Column(name = "h120Male")
    public int h120Male;
    @Column(name = "h1MonoMale")
    public int h1MonoMale;
    @Column(name = "h1MultiMale")
    public int h1MultiMale;
    @Column(name = "h1SpedMale")
    public int h1SpedMale;
    @Column(name = "h1RepeatMale")
    public int h1RepeatMale;
    @Column(name = "h1TransferMale")
    public int h1TransferMale;
    @Column(name = "h1BackMale")
    public int h1BackMale;
    @Column(name = "h1MuslimMale")
    public int h1MuslimMale;
    @Column(name = "h1IndigenousMale")
    public int h1IndigenousMale;
    @Column(name = "h1ClassesMale")
    public int h1ClassesMale;
    @Column(name = "h1CompleteMale")
    public int h1CompleteMale;
    @Column(name = "h1DropMale")
    public int h1DropMale;
    @Column(name = "h1TransferOutMale")
    public int h1TransferOutMale;
    @Column(name = "h1FailedMale")
    public int h1FailedMale;
    @Column(name = "h1ShiftMale")
    public int h1ShiftMale;
    @Column(name = "h1ESCMale")
    public int h1ESCMale;
    @Column(name = "h1EVSMale")
    public int h1EVSMale;
    @Column(name = "h1ESCCompleteMale")
    public int h1ESCCompleteMale;
    @Column(name = "h1EVSCompleteMale")
    public int h1EVSCompleteMale;
    @Column(name = "h1ESCTransferOutMale")
    public int h1ESCTransferOutMale;
    @Column(name = "h1EVSTransferOutMale")
    public int h1EVSTransferOutMale;
    @Column(name = "h1ESCDropMale")
    public int h1ESCDropMale;
    @Column(name = "h1EVSDropMale")
    public int h1EVSDropMale;
    @Column(name = "h1ESCFailedMale")
    public int h1ESCFailedMale;
    @Column(name = "h1EVSFailedMale")
    public int h1EVSFailedMale;



    @Column(name = "h1Female")
    public int h1Female;
    @Column(name = "h14Female")
    public int h14Female;
    @Column(name = "h15Female")
    public int h15Female;
    @Column(name = "h16Female")
    public int h16Female;
    @Column(name = "h17Female")
    public int h17Female;
    @Column(name = "h18Female")
    public int h18Female;
    @Column(name = "h19Female")
    public int h19Female;
    @Column(name = "h110Female")
    public int h110Female;
    @Column(name = "h111Female")
    public int h111Female;
    @Column(name = "h112Female")
    public int h112Female;
    @Column(name = "h113Female")
    public int h113Female;
    @Column(name = "h114Female")
    public int h114Female;
    @Column(name = "h115Female")
    public int h115Female;
    @Column(name = "h116Female")
    public int h116Female;
    @Column(name = "h117Female")
    public int h117Female;
    @Column(name = "h118Female")
    public int h118Female;
    @Column(name = "h119Female")
    public int h119Female;
    @Column(name = "h120Female")
    public int h120Female;

    @Column(name = "h1MonoFemale")
    public int h1MonoFemale;
    @Column(name = "h1MultiFemale")
    public int h1MultiFemale;
    @Column(name = "h1SpedFemale")
    public int h1SpedFemale;
    @Column(name = "h1RepeatFemale")
    public int h1RepeatFemale;
    @Column(name = "h1TransferFemale")
    public int h1TransferFemale;
    @Column(name = "h1BackFemale")
    public int h1BackFemale;
    @Column(name = "h1MuslimFemale")
    public int h1MuslimFemale;
    @Column(name = "h1IndigenousFemale")
    public int h1IndigenousFemale;

    @Column(name = "h1ClassesFemale")
    public int h1ClassesFemale;
    @Column(name = "h1CompleteFemale")
    public int h1CompleteFemale;
    @Column(name = "h1DropFemale")
    public int h1DropFemale;
    @Column(name = "h1TransferOutFemale")
    public int h1TransferOutFemale;
    @Column(name = "h1FailedFemale")
    public int h1FailedFemale;

    @Column(name = "h1ShiftFemale")
    public int h1ShiftFemale;

    @Column(name = "h1ESCFemale")
    public int h1ESCFemale;
    @Column(name = "h1EVSFemale")
    public int h1EVSFemale;
    @Column(name = "h1ESCCompleteFemale")
    public int h1ESCCompleteFemale;
    @Column(name = "h1EVSCompleteFemale")
    public int h1EVSCompleteFemale;
    @Column(name = "h1ESCTransferOutFemale")
    public int h1ESCTransferOutFemale;
    @Column(name = "h1EVSTransferOutFemale")
    public int h1EVSTransferOutFemale;
    @Column(name = "h1ESCDropFemale")
    public int h1ESCDropFemale;
    @Column(name = "h1EVSDropFemale")
    public int h1EVSDropFemale;
    @Column(name = "h1ESCFailedFemale")
    public int h1ESCFailedFemale;
    @Column(name = "h1EVSFailedFemale")
    public int h1EVSFailedFemale;




    @Column(name = "h2Male")
    public int h2Male;
    @Column(name = "h24Male")
    public int h24Male;
    @Column(name = "h25Male")
    public int h25Male;
    @Column(name = "h26Male")
    public int h26Male;
    @Column(name = "h27Male")
    public int h27Male;
    @Column(name = "h28Male")
    public int h28Male;
    @Column(name = "h29Male")
    public int h29Male;
    @Column(name = "h210Male")
    public int h210Male;
    @Column(name = "h211Male")
    public int h211Male;
    @Column(name = "h212Male")
    public int h212Male;
    @Column(name = "h213Male")
    public int h213Male;
    @Column(name = "h214Male")
    public int h214Male;
    @Column(name = "h215Male")
    public int h215Male;
    @Column(name = "h216Male")
    public int h216Male;
    @Column(name = "h217Male")
    public int h217Male;
    @Column(name = "h218Male")
    public int h218Male;
    @Column(name = "h219Male")
    public int h219Male;
    @Column(name = "h220Male")
    public int h220Male;
    @Column(name = "h2MonoMale")
    public int h2MonoMale;
    @Column(name = "h2MultiMale")
    public int h2MultiMale;
    @Column(name = "h2SpedMale")
    public int h2SpedMale;
    @Column(name = "h2RepeatMale")
    public int h2RepeatMale;
    @Column(name = "h2TransferMale")
    public int h2TransferMale;
    @Column(name = "h2BackMale")
    public int h2BackMale;
    @Column(name = "h2MuslimMale")
    public int h2MuslimMale;
    @Column(name = "h2IndigenousMale")
    public int h2IndigenousMale;
    @Column(name = "h2ClassesMale")
    public int h2ClassesMale;
    @Column(name = "h2CompleteMale")
    public int h2CompleteMale;
    @Column(name = "h2DropMale")
    public int h2DropMale;
    @Column(name = "h2TransferOutMale")
    public int h2TransferOutMale;
    @Column(name = "h2FailedMale")
    public int h2FailedMale;
    @Column(name = "h2ShiftMale")
    public int h2ShiftMale;
    @Column(name = "h2ESCMale")
    public int h2ESCMale;
    @Column(name = "h2EVSMale")
    public int h2EVSMale;
    @Column(name = "h2ESCCompleteMale")
    public int h2ESCCompleteMale;
    @Column(name = "h2EVSCompleteMale")
    public int h2EVSCompleteMale;
    @Column(name = "h2ESCTransferOutMale")
    public int h2ESCTransferOutMale;
    @Column(name = "h2EVSTransferOutMale")
    public int h2EVSTransferOutMale;
    @Column(name = "h2ESCDropMale")
    public int h2ESCDropMale;
    @Column(name = "h2EVSDropMale")
    public int h2EVSDropMale;
    @Column(name = "h2ESCFailedMale")
    public int h2ESCFailedMale;
    @Column(name = "h2EVSFailedMale")
    public int h2EVSFailedMale;



    @Column(name = "h2Female")
    public int h2Female;
    @Column(name = "h24Female")
    public int h24Female;
    @Column(name = "h25Female")
    public int h25Female;
    @Column(name = "h26Female")
    public int h26Female;
    @Column(name = "h27Female")
    public int h27Female;
    @Column(name = "h28Female")
    public int h28Female;
    @Column(name = "h29Female")
    public int h29Female;
    @Column(name = "h210Female")
    public int h210Female;
    @Column(name = "h211Female")
    public int h211Female;
    @Column(name = "h212Female")
    public int h212Female;
    @Column(name = "h213Female")
    public int h213Female;
    @Column(name = "h214Female")
    public int h214Female;
    @Column(name = "h215Female")
    public int h215Female;
    @Column(name = "h216Female")
    public int h216Female;
    @Column(name = "h217Female")
    public int h217Female;
    @Column(name = "h218Female")
    public int h218Female;
    @Column(name = "h219Female")
    public int h219Female;
    @Column(name = "h220Female")
    public int h220Female;

    @Column(name = "h2MonoFemale")
    public int h2MonoFemale;
    @Column(name = "h2MultiFemale")
    public int h2MultiFemale;
    @Column(name = "h2SpedFemale")
    public int h2SpedFemale;
    @Column(name = "h2RepeatFemale")
    public int h2RepeatFemale;
    @Column(name = "h2TransferFemale")
    public int h2TransferFemale;
    @Column(name = "h2BackFemale")
    public int h2BackFemale;
    @Column(name = "h2MuslimFemale")
    public int h2MuslimFemale;
    @Column(name = "h2IndigenousFemale")
    public int h2IndigenousFemale;

    @Column(name = "h2ClassesFemale")
    public int h2ClassesFemale;
    @Column(name = "h2CompleteFemale")
    public int h2CompleteFemale;
    @Column(name = "h2DropFemale")
    public int h2DropFemale;
    @Column(name = "h2TransferOutFemale")
    public int h2TransferOutFemale;
    @Column(name = "h2FailedFemale")
    public int h2FailedFemale;

    @Column(name = "h2ShiftFemale")
    public int h2ShiftFemale;

    @Column(name = "h2ESCFemale")
    public int h2ESCFemale;
    @Column(name = "h2EVSFemale")
    public int h2EVSFemale;
    @Column(name = "h2ESCCompleteFemale")
    public int h2ESCCompleteFemale;
    @Column(name = "h2EVSCompleteFemale")
    public int h2EVSCompleteFemale;
    @Column(name = "h2ESCTransferOutFemale")
    public int h2ESCTransferOutFemale;
    @Column(name = "h2EVSTransferOutFemale")
    public int h2EVSTransferOutFemale;
    @Column(name = "h2ESCDropFemale")
    public int h2ESCDropFemale;
    @Column(name = "h2EVSDropFemale")
    public int h2EVSDropFemale;
    @Column(name = "h2ESCFailedFemale")
    public int h2ESCFailedFemale;
    @Column(name = "h2EVSFailedFemale")
    public int h2EVSFailedFemale;




    @Column(name = "h3Male")
    public int h3Male;
    @Column(name = "h34Male")
    public int h34Male;
    @Column(name = "h35Male")
    public int h35Male;
    @Column(name = "h36Male")
    public int h36Male;
    @Column(name = "h37Male")
    public int h37Male;
    @Column(name = "h38Male")
    public int h38Male;
    @Column(name = "h39Male")
    public int h39Male;
    @Column(name = "h310Male")
    public int h310Male;
    @Column(name = "h311Male")
    public int h311Male;
    @Column(name = "h312Male")
    public int h312Male;
    @Column(name = "h313Male")
    public int h313Male;
    @Column(name = "h314Male")
    public int h314Male;
    @Column(name = "h315Male")
    public int h315Male;
    @Column(name = "h316Male")
    public int h316Male;
    @Column(name = "h317Male")
    public int h317Male;
    @Column(name = "h318Male")
    public int h318Male;
    @Column(name = "h319Male")
    public int h319Male;
    @Column(name = "h320Male")
    public int h320Male;
    @Column(name = "h3MonoMale")
    public int h3MonoMale;
    @Column(name = "h3MultiMale")
    public int h3MultiMale;
    @Column(name = "h3SpedMale")
    public int h3SpedMale;
    @Column(name = "h3RepeatMale")
    public int h3RepeatMale;
    @Column(name = "h3TransferMale")
    public int h3TransferMale;
    @Column(name = "h3BackMale")
    public int h3BackMale;
    @Column(name = "h3MuslimMale")
    public int h3MuslimMale;
    @Column(name = "h3IndigenousMale")
    public int h3IndigenousMale;
    @Column(name = "h3ClassesMale")
    public int h3ClassesMale;
    @Column(name = "h3CompleteMale")
    public int h3CompleteMale;
    @Column(name = "h3DropMale")
    public int h3DropMale;
    @Column(name = "h3TransferOutMale")
    public int h3TransferOutMale;
    @Column(name = "h3FailedMale")
    public int h3FailedMale;
    @Column(name = "h3ShiftMale")
    public int h3ShiftMale;
    @Column(name = "h3ESCMale")
    public int h3ESCMale;
    @Column(name = "h3EVSMale")
    public int h3EVSMale;
    @Column(name = "h3ESCCompleteMale")
    public int h3ESCCompleteMale;
    @Column(name = "h3EVSCompleteMale")
    public int h3EVSCompleteMale;
    @Column(name = "h3ESCTransferOutMale")
    public int h3ESCTransferOutMale;
    @Column(name = "h3EVSTransferOutMale")
    public int h3EVSTransferOutMale;
    @Column(name = "h3ESCDropMale")
    public int h3ESCDropMale;
    @Column(name = "h3EVSDropMale")
    public int h3EVSDropMale;
    @Column(name = "h3ESCFailedMale")
    public int h3ESCFailedMale;
    @Column(name = "h3EVSFailedMale")
    public int h3EVSFailedMale;



    @Column(name = "h3Female")
    public int h3Female;
    @Column(name = "h34Female")
    public int h34Female;
    @Column(name = "h35Female")
    public int h35Female;
    @Column(name = "h36Female")
    public int h36Female;
    @Column(name = "h37Female")
    public int h37Female;
    @Column(name = "h38Female")
    public int h38Female;
    @Column(name = "h39Female")
    public int h39Female;
    @Column(name = "h310Female")
    public int h310Female;
    @Column(name = "h311Female")
    public int h311Female;
    @Column(name = "h312Female")
    public int h312Female;
    @Column(name = "h313Female")
    public int h313Female;
    @Column(name = "h314Female")
    public int h314Female;
    @Column(name = "h315Female")
    public int h315Female;
    @Column(name = "h316Female")
    public int h316Female;
    @Column(name = "h317Female")
    public int h317Female;
    @Column(name = "h318Female")
    public int h318Female;
    @Column(name = "h319Female")
    public int h319Female;
    @Column(name = "h320Female")
    public int h320Female;

    @Column(name = "h3MonoFemale")
    public int h3MonoFemale;
    @Column(name = "h3MultiFemale")
    public int h3MultiFemale;
    @Column(name = "h3SpedFemale")
    public int h3SpedFemale;
    @Column(name = "h3RepeatFemale")
    public int h3RepeatFemale;
    @Column(name = "h3TransferFemale")
    public int h3TransferFemale;
    @Column(name = "h3BackFemale")
    public int h3BackFemale;
    @Column(name = "h3MuslimFemale")
    public int h3MuslimFemale;
    @Column(name = "h3IndigenousFemale")
    public int h3IndigenousFemale;

    @Column(name = "h3ClassesFemale")
    public int h3ClassesFemale;
    @Column(name = "h3CompleteFemale")
    public int h3CompleteFemale;
    @Column(name = "h3DropFemale")
    public int h3DropFemale;
    @Column(name = "h3TransferOutFemale")
    public int h3TransferOutFemale;
    @Column(name = "h3FailedFemale")
    public int h3FailedFemale;

    @Column(name = "h3ShiftFemale")
    public int h3ShiftFemale;

    @Column(name = "h3ESCFemale")
    public int h3ESCFemale;
    @Column(name = "h3EVSFemale")
    public int h3EVSFemale;
    @Column(name = "h3ESCCompleteFemale")
    public int h3ESCCompleteFemale;
    @Column(name = "h3EVSCompleteFemale")
    public int h3EVSCompleteFemale;
    @Column(name = "h3ESCTransferOutFemale")
    public int h3ESCTransferOutFemale;
    @Column(name = "h3EVSTransferOutFemale")
    public int h3EVSTransferOutFemale;
    @Column(name = "h3ESCDropFemale")
    public int h3ESCDropFemale;
    @Column(name = "h3EVSDropFemale")
    public int h3EVSDropFemale;
    @Column(name = "h3ESCFailedFemale")
    public int h3ESCFailedFemale;
    @Column(name = "h3EVSFailedFemale")
    public int h3EVSFailedFemale;




    @Column(name = "h4Male")
    public int h4Male;
    @Column(name = "h44Male")
    public int h44Male;
    @Column(name = "h45Male")
    public int h45Male;
    @Column(name = "h46Male")
    public int h46Male;
    @Column(name = "h47Male")
    public int h47Male;
    @Column(name = "h48Male")
    public int h48Male;
    @Column(name = "h49Male")
    public int h49Male;
    @Column(name = "h410Male")
    public int h410Male;
    @Column(name = "h411Male")
    public int h411Male;
    @Column(name = "h412Male")
    public int h412Male;
    @Column(name = "h413Male")
    public int h413Male;
    @Column(name = "h414Male")
    public int h414Male;
    @Column(name = "h415Male")
    public int h415Male;
    @Column(name = "h416Male")
    public int h416Male;
    @Column(name = "h417Male")
    public int h417Male;
    @Column(name = "h418Male")
    public int h418Male;
    @Column(name = "h419Male")
    public int h419Male;
    @Column(name = "h420Male")
    public int h420Male;
    @Column(name = "h4MonoMale")
    public int h4MonoMale;
    @Column(name = "h4MultiMale")
    public int h4MultiMale;
    @Column(name = "h4SpedMale")
    public int h4SpedMale;
    @Column(name = "h4RepeatMale")
    public int h4RepeatMale;
    @Column(name = "h4TransferMale")
    public int h4TransferMale;
    @Column(name = "h4BackMale")
    public int h4BackMale;
    @Column(name = "h4MuslimMale")
    public int h4MuslimMale;
    @Column(name = "h4IndigenousMale")
    public int h4IndigenousMale;
    @Column(name = "h4ClassesMale")
    public int h4ClassesMale;
    @Column(name = "h4CompleteMale")
    public int h4CompleteMale;
    @Column(name = "h4DropMale")
    public int h4DropMale;
    @Column(name = "h4TransferOutMale")
    public int h4TransferOutMale;
    @Column(name = "h4FailedMale")
    public int h4FailedMale;
    @Column(name = "h4ShiftMale")
    public int h4ShiftMale;
    @Column(name = "h4ESCMale")
    public int h4ESCMale;
    @Column(name = "h4EVSMale")
    public int h4EVSMale;
    @Column(name = "h4ESCCompleteMale")
    public int h4ESCCompleteMale;
    @Column(name = "h4EVSCompleteMale")
    public int h4EVSCompleteMale;
    @Column(name = "h4ESCTransferOutMale")
    public int h4ESCTransferOutMale;
    @Column(name = "h4EVSTransferOutMale")
    public int h4EVSTransferOutMale;
    @Column(name = "h4ESCDropMale")
    public int h4ESCDropMale;
    @Column(name = "h4EVSDropMale")
    public int h4EVSDropMale;
    @Column(name = "h4ESCFailedMale")
    public int h4ESCFailedMale;
    @Column(name = "h4EVSFailedMale")
    public int h4EVSFailedMale;



    @Column(name = "h4Female")
    public int h4Female;
    @Column(name = "h44Female")
    public int h44Female;
    @Column(name = "h45Female")
    public int h45Female;
    @Column(name = "h46Female")
    public int h46Female;
    @Column(name = "h47Female")
    public int h47Female;
    @Column(name = "h48Female")
    public int h48Female;
    @Column(name = "h49Female")
    public int h49Female;
    @Column(name = "h410Female")
    public int h410Female;
    @Column(name = "h411Female")
    public int h411Female;
    @Column(name = "h412Female")
    public int h412Female;
    @Column(name = "h413Female")
    public int h413Female;
    @Column(name = "h414Female")
    public int h414Female;
    @Column(name = "h415Female")
    public int h415Female;
    @Column(name = "h416Female")
    public int h416Female;
    @Column(name = "h417Female")
    public int h417Female;
    @Column(name = "h418Female")
    public int h418Female;
    @Column(name = "h419Female")
    public int h419Female;
    @Column(name = "h420Female")
    public int h420Female;

    @Column(name = "h4MonoFemale")
    public int h4MonoFemale;
    @Column(name = "h4MultiFemale")
    public int h4MultiFemale;
    @Column(name = "h4SpedFemale")
    public int h4SpedFemale;
    @Column(name = "h4RepeatFemale")
    public int h4RepeatFemale;
    @Column(name = "h4TransferFemale")
    public int h4TransferFemale;
    @Column(name = "h4BackFemale")
    public int h4BackFemale;
    @Column(name = "h4MuslimFemale")
    public int h4MuslimFemale;
    @Column(name = "h4IndigenousFemale")
    public int h4IndigenousFemale;

    @Column(name = "h4ClassesFemale")
    public int h4ClassesFemale;
    @Column(name = "h4CompleteFemale")
    public int h4CompleteFemale;
    @Column(name = "h4DropFemale")
    public int h4DropFemale;
    @Column(name = "h4TransferOutFemale")
    public int h4TransferOutFemale;
    @Column(name = "h4FailedFemale")
    public int h4FailedFemale;

    @Column(name = "h4ShiftFemale")
    public int h4ShiftFemale;

    @Column(name = "h4ESCFemale")
    public int h4ESCFemale;
    @Column(name = "h4EVSFemale")
    public int h4EVSFemale;
    @Column(name = "h4ESCCompleteFemale")
    public int h4ESCCompleteFemale;
    @Column(name = "h4EVSCompleteFemale")
    public int h4EVSCompleteFemale;
    @Column(name = "h4ESCTransferOutFemale")
    public int h4ESCTransferOutFemale;
    @Column(name = "h4EVSTransferOutFemale")
    public int h4EVSTransferOutFemale;
    @Column(name = "h4ESCDropFemale")
    public int h4ESCDropFemale;
    @Column(name = "h4EVSDropFemale")
    public int h4EVSDropFemale;
    @Column(name = "h4ESCFailedFemale")
    public int h4ESCFailedFemale;
    @Column(name = "h4EVSFailedFemale")
    public int h4EVSFailedFemale;


    
    
    
    @Column(name = "gsRoomInsAcademic")
    public int gsRoomInsAcademic;
    @Column(name = "gsRoomInsScienceLab")
    public int gsRoomInsScienceLab;
    @Column(name = "gsRoomInsHE")
    public int gsRoomInsHE;
    @Column(name = "gsRoomInsWorkshop")
    public int gsRoomInsWorkshop;
    @Column(name = "gsRoomInsComputer")
    public int gsRoomInsComputer;
    @Column(name = "gsRoomInsNotUse")
    public int gsRoomInsNotUse;

    @Column(name = "gsRoomLibrary")
    public int gsRoomLibrary;
    @Column(name = "gsRoomClinic")
    public int gsRoomClinic;
    @Column(name = "gsRoomCanteen")
    public int gsRoomCanteen;
    @Column(name = "gsRoomOffice")
    public int gsRoomOffice;
    @Column(name = "gsRoomOtherUse")
    public int gsRoomOtherUse;
    @Column(name = "gsRoomNotUse")
    public int gsRoomNotUse;

    @Column(name = "gsDesk")
    public int gsDesk;
    @Column(name = "gsChairTable")
    public int gsChairTable;
    @Column(name = "gsArmChair")
    public int gsArmChair;
    @Column(name = "gsToiletGirlBowl")
    public int gsToiletGirlBowl;
    @Column(name = "gsToiletBoyUrinalSingle")
    public int gsToiletBoyUrinalSingle;
    @Column(name = "gsToiletBoyUrinalMultiple")
    public int gsToiletBoyUrinalMultiple;
    @Column(name = "gsToiletBoyBowl")
    public int gsToiletBoyBowl;
    @Column(name = "gsToiletShared")
    public int gsToiletShared;


    @Column(name = "hsRoomInsAcademic")
    public int hsRoomInsAcademic;
    @Column(name = "hsRoomInsScienceLab")
    public int hsRoomInsScienceLab;
    @Column(name = "hsRoomInsHE")
    public int hsRoomInsHE;
    @Column(name = "hsRoomInsWorkshop")
    public int hsRoomInsWorkshop;
    @Column(name = "hsRoomInsComputer")
    public int hsRoomInsComputer;
    @Column(name = "hsRoomInsNotUse")
    public int hsRoomInsNotUse;

    @Column(name = "hsRoomLibrary")
    public int hsRoomLibrary;
    @Column(name = "hsRoomClinic")
    public int hsRoomClinic;
    @Column(name = "hsRoomCanteen")
    public int hsRoomCanteen;
    @Column(name = "hsRoomOffice")
    public int hsRoomOffice;
    @Column(name = "hsRoomOtherUse")
    public int hsRoomOtherUse;
    @Column(name = "hsRoomNotUse")
    public int hsRoomNotUse;

    @Column(name = "hsDesk")
    public int hsDesk;
    @Column(name = "hsChairTable")
    public int hsChairTable;
    @Column(name = "hsArmChair")
    public int hsArmChair;
    @Column(name = "hsToiletGirlBowl")
    public int hsToiletGirlBowl;
    @Column(name = "hsToiletBoyUrinalSingle")
    public int hsToiletBoyUrinalSingle;
    @Column(name = "hsToiletBoyUrinalMultiple")
    public int hsToiletBoyUrinalMultiple;
    @Column(name = "hsToiletBoyBowl")
    public int hsToiletBoyBowl;
    @Column(name = "hsToiletShared")
    public int hsToiletShared;

    
    @Column(name = "preMaleFacultyFulltime")
    public int preMaleFacultyFulltime;
    @Column(name = "preMaleFacultyParttime")
    public int preMaleFacultyParttime;
    @Column(name = "preMaleFacultyAdmin")
    public int preMaleFacultyAdmin;

    @Column(name = "preFemaleFacultyFulltime")
    public int preFemaleFacultyFulltime;
    @Column(name = "preFemaleFacultyParttime")
    public int preFemaleFacultyParttime;
    @Column(name = "preFemaleFacultyAdmin")
    public int preFemaleFacultyAdmin;


    @Column(name = "gsMaleFacultyFulltime")
    public int gsMaleFacultyFulltime;
    @Column(name = "gsMaleFacultyParttime")
    public int gsMaleFacultyParttime;
    @Column(name = "gsMaleFacultyAdmin")
    public int gsMaleFacultyAdmin;

    @Column(name = "gsFemaleFacultyFulltime")
    public int gsFemaleFacultyFulltime;
    @Column(name = "gsFemaleFacultyParttime")
    public int gsFemaleFacultyParttime;
    @Column(name = "gsFemaleFacultyAdmin")
    public int gsFemaleFacultyAdmin;


    @Column(name = "hsMaleFacultyFulltime")
    public int hsMaleFacultyFulltime;
    @Column(name = "hsMaleFacultyParttime")
    public int hsMaleFacultyParttime;
    @Column(name = "hsMaleFacultyAdmin")
    public int hsMaleFacultyAdmin;

    @Column(name = "hsFemaleFacultyFulltime")
    public int hsFemaleFacultyFulltime;
    @Column(name = "hsFemaleFacultyParttime")
    public int hsFemaleFacultyParttime;
    @Column(name = "hsFemaleFacultyAdmin")
    public int hsFemaleFacultyAdmin;

    @Override
    public String popupSearch(String criteria) {
        return buildSearch(criteria);
    }

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public int getPreMale() {
		return preMale;
	}

	public void setPreMale(int preMale) {
		this.preMale = preMale;
	}

	public int getPre4Male() {
		return pre4Male;
	}

	public void setPre4Male(int pre4Male) {
		this.pre4Male = pre4Male;
	}

	public int getPre5Male() {
		return pre5Male;
	}

	public void setPre5Male(int pre5Male) {
		this.pre5Male = pre5Male;
	}

	public int getPre6Male() {
		return pre6Male;
	}

	public void setPre6Male(int pre6Male) {
		this.pre6Male = pre6Male;
	}

	public int getPre7Male() {
		return pre7Male;
	}

	public void setPre7Male(int pre7Male) {
		this.pre7Male = pre7Male;
	}

	public int getPre8Male() {
		return pre8Male;
	}

	public void setPre8Male(int pre8Male) {
		this.pre8Male = pre8Male;
	}

	public int getPre9Male() {
		return pre9Male;
	}

	public void setPre9Male(int pre9Male) {
		this.pre9Male = pre9Male;
	}

	public int getPre10Male() {
		return pre10Male;
	}

	public void setPre10Male(int pre10Male) {
		this.pre10Male = pre10Male;
	}

	public int getPre11Male() {
		return pre11Male;
	}

	public void setPre11Male(int pre11Male) {
		this.pre11Male = pre11Male;
	}

	public int getPre12Male() {
		return pre12Male;
	}

	public void setPre12Male(int pre12Male) {
		this.pre12Male = pre12Male;
	}

	public int getPre13Male() {
		return pre13Male;
	}

	public void setPre13Male(int pre13Male) {
		this.pre13Male = pre13Male;
	}

	public int getPre14Male() {
		return pre14Male;
	}

	public void setPre14Male(int pre14Male) {
		this.pre14Male = pre14Male;
	}

	public int getPre15Male() {
		return pre15Male;
	}

	public void setPre15Male(int pre15Male) {
		this.pre15Male = pre15Male;
	}

	public int getPre16Male() {
		return pre16Male;
	}

	public void setPre16Male(int pre16Male) {
		this.pre16Male = pre16Male;
	}

	public int getPre17Male() {
		return pre17Male;
	}

	public void setPre17Male(int pre17Male) {
		this.pre17Male = pre17Male;
	}

	public int getPre18Male() {
		return pre18Male;
	}

	public void setPre18Male(int pre18Male) {
		this.pre18Male = pre18Male;
	}

	public int getPre19Male() {
		return pre19Male;
	}

	public void setPre19Male(int pre19Male) {
		this.pre19Male = pre19Male;
	}

	public int getPre20Male() {
		return pre20Male;
	}

	public void setPre20Male(int pre20Male) {
		this.pre20Male = pre20Male;
	}

	public int getPreMonoMale() {
		return preMonoMale;
	}

	public void setPreMonoMale(int preMonoMale) {
		this.preMonoMale = preMonoMale;
	}

	public int getPreMultiMale() {
		return preMultiMale;
	}

	public void setPreMultiMale(int preMultiMale) {
		this.preMultiMale = preMultiMale;
	}

	public int getPreSpedMale() {
		return preSpedMale;
	}

	public void setPreSpedMale(int preSpedMale) {
		this.preSpedMale = preSpedMale;
	}

	public int getPreRepeatMale() {
		return preRepeatMale;
	}

	public void setPreRepeatMale(int preRepeatMale) {
		this.preRepeatMale = preRepeatMale;
	}

	public int getPreTransferMale() {
		return preTransferMale;
	}

	public void setPreTransferMale(int preTransferMale) {
		this.preTransferMale = preTransferMale;
	}

	public int getPreBackMale() {
		return preBackMale;
	}

	public void setPreBackMale(int preBackMale) {
		this.preBackMale = preBackMale;
	}

	public int getPreMuslimMale() {
		return preMuslimMale;
	}

	public void setPreMuslimMale(int preMuslimMale) {
		this.preMuslimMale = preMuslimMale;
	}

	public int getPreIndigenousMale() {
		return preIndigenousMale;
	}

	public void setPreIndigenousMale(int preIndigenousMale) {
		this.preIndigenousMale = preIndigenousMale;
	}

	public int getPreClassesMale() {
		return preClassesMale;
	}

	public void setPreClassesMale(int preClassesMale) {
		this.preClassesMale = preClassesMale;
	}

	public int getPreCompleteMale() {
		return preCompleteMale;
	}

	public void setPreCompleteMale(int preCompleteMale) {
		this.preCompleteMale = preCompleteMale;
	}

	public int getPreDropMale() {
		return preDropMale;
	}

	public void setPreDropMale(int preDropMale) {
		this.preDropMale = preDropMale;
	}

	public int getPreTransferOutMale() {
		return preTransferOutMale;
	}

	public void setPreTransferOutMale(int preTransferOutMale) {
		this.preTransferOutMale = preTransferOutMale;
	}

	public int getPreFailedMale() {
		return preFailedMale;
	}

	public void setPreFailedMale(int preFailedMale) {
		this.preFailedMale = preFailedMale;
	}

	public int getPreShiftMale() {
		return preShiftMale;
	}

	public void setPreShiftMale(int preShiftMale) {
		this.preShiftMale = preShiftMale;
	}

	public int getPreESCMale() {
		return preESCMale;
	}

	public void setPreESCMale(int preESCMale) {
		this.preESCMale = preESCMale;
	}

	public int getPreEVSMale() {
		return preEVSMale;
	}

	public void setPreEVSMale(int preEVSMale) {
		this.preEVSMale = preEVSMale;
	}

	public int getPreESCCompleteMale() {
		return preESCCompleteMale;
	}

	public void setPreESCCompleteMale(int preESCCompleteMale) {
		this.preESCCompleteMale = preESCCompleteMale;
	}

	public int getPreEVSCompleteMale() {
		return preEVSCompleteMale;
	}

	public void setPreEVSCompleteMale(int preEVSCompleteMale) {
		this.preEVSCompleteMale = preEVSCompleteMale;
	}

	public int getPreESCTransferOutMale() {
		return preESCTransferOutMale;
	}

	public void setPreESCTransferOutMale(int preESCTransferOutMale) {
		this.preESCTransferOutMale = preESCTransferOutMale;
	}

	public int getPreEVSTransferOutMale() {
		return preEVSTransferOutMale;
	}

	public void setPreEVSTransferOutMale(int preEVSTransferOutMale) {
		this.preEVSTransferOutMale = preEVSTransferOutMale;
	}

	public int getPreESCDropMale() {
		return preESCDropMale;
	}

	public void setPreESCDropMale(int preESCDropMale) {
		this.preESCDropMale = preESCDropMale;
	}

	public int getPreEVSDropMale() {
		return preEVSDropMale;
	}

	public void setPreEVSDropMale(int preEVSDropMale) {
		this.preEVSDropMale = preEVSDropMale;
	}

	public int getPreESCFailedMale() {
		return preESCFailedMale;
	}

	public void setPreESCFailedMale(int preESCFailedMale) {
		this.preESCFailedMale = preESCFailedMale;
	}

	public int getPreEVSFailedMale() {
		return preEVSFailedMale;
	}

	public void setPreEVSFailedMale(int preEVSFailedMale) {
		this.preEVSFailedMale = preEVSFailedMale;
	}

	public int getPreFemale() {
		return preFemale;
	}

	public void setPreFemale(int preFemale) {
		this.preFemale = preFemale;
	}

	public int getPre4Female() {
		return pre4Female;
	}

	public void setPre4Female(int pre4Female) {
		this.pre4Female = pre4Female;
	}

	public int getPre5Female() {
		return pre5Female;
	}

	public void setPre5Female(int pre5Female) {
		this.pre5Female = pre5Female;
	}

	public int getPre6Female() {
		return pre6Female;
	}

	public void setPre6Female(int pre6Female) {
		this.pre6Female = pre6Female;
	}

	public int getPre7Female() {
		return pre7Female;
	}

	public void setPre7Female(int pre7Female) {
		this.pre7Female = pre7Female;
	}

	public int getPre8Female() {
		return pre8Female;
	}

	public void setPre8Female(int pre8Female) {
		this.pre8Female = pre8Female;
	}

	public int getPre9Female() {
		return pre9Female;
	}

	public void setPre9Female(int pre9Female) {
		this.pre9Female = pre9Female;
	}

	public int getPre10Female() {
		return pre10Female;
	}

	public void setPre10Female(int pre10Female) {
		this.pre10Female = pre10Female;
	}

	public int getPre11Female() {
		return pre11Female;
	}

	public void setPre11Female(int pre11Female) {
		this.pre11Female = pre11Female;
	}

	public int getPre12Female() {
		return pre12Female;
	}

	public void setPre12Female(int pre12Female) {
		this.pre12Female = pre12Female;
	}

	public int getPre13Female() {
		return pre13Female;
	}

	public void setPre13Female(int pre13Female) {
		this.pre13Female = pre13Female;
	}

	public int getPre14Female() {
		return pre14Female;
	}

	public void setPre14Female(int pre14Female) {
		this.pre14Female = pre14Female;
	}

	public int getPre15Female() {
		return pre15Female;
	}

	public void setPre15Female(int pre15Female) {
		this.pre15Female = pre15Female;
	}

	public int getPre16Female() {
		return pre16Female;
	}

	public void setPre16Female(int pre16Female) {
		this.pre16Female = pre16Female;
	}

	public int getPre17Female() {
		return pre17Female;
	}

	public void setPre17Female(int pre17Female) {
		this.pre17Female = pre17Female;
	}

	public int getPre18Female() {
		return pre18Female;
	}

	public void setPre18Female(int pre18Female) {
		this.pre18Female = pre18Female;
	}

	public int getPre19Female() {
		return pre19Female;
	}

	public void setPre19Female(int pre19Female) {
		this.pre19Female = pre19Female;
	}

	public int getPre20Female() {
		return pre20Female;
	}

	public void setPre20Female(int pre20Female) {
		this.pre20Female = pre20Female;
	}

	public int getPreMonoFemale() {
		return preMonoFemale;
	}

	public void setPreMonoFemale(int preMonoFemale) {
		this.preMonoFemale = preMonoFemale;
	}

	public int getPreMultiFemale() {
		return preMultiFemale;
	}

	public void setPreMultiFemale(int preMultiFemale) {
		this.preMultiFemale = preMultiFemale;
	}

	public int getPreSpedFemale() {
		return preSpedFemale;
	}

	public void setPreSpedFemale(int preSpedFemale) {
		this.preSpedFemale = preSpedFemale;
	}

	public int getPreRepeatFemale() {
		return preRepeatFemale;
	}

	public void setPreRepeatFemale(int preRepeatFemale) {
		this.preRepeatFemale = preRepeatFemale;
	}

	public int getPreTransferFemale() {
		return preTransferFemale;
	}

	public void setPreTransferFemale(int preTransferFemale) {
		this.preTransferFemale = preTransferFemale;
	}

	public int getPreBackFemale() {
		return preBackFemale;
	}

	public void setPreBackFemale(int preBackFemale) {
		this.preBackFemale = preBackFemale;
	}

	public int getPreMuslimFemale() {
		return preMuslimFemale;
	}

	public void setPreMuslimFemale(int preMuslimFemale) {
		this.preMuslimFemale = preMuslimFemale;
	}

	public int getPreIndigenousFemale() {
		return preIndigenousFemale;
	}

	public void setPreIndigenousFemale(int preIndigenousFemale) {
		this.preIndigenousFemale = preIndigenousFemale;
	}

	public int getPreClassesFemale() {
		return preClassesFemale;
	}

	public void setPreClassesFemale(int preClassesFemale) {
		this.preClassesFemale = preClassesFemale;
	}

	public int getPreCompleteFemale() {
		return preCompleteFemale;
	}

	public void setPreCompleteFemale(int preCompleteFemale) {
		this.preCompleteFemale = preCompleteFemale;
	}

	public int getPreDropFemale() {
		return preDropFemale;
	}

	public void setPreDropFemale(int preDropFemale) {
		this.preDropFemale = preDropFemale;
	}

	public int getPreTransferOutFemale() {
		return preTransferOutFemale;
	}

	public void setPreTransferOutFemale(int preTransferOutFemale) {
		this.preTransferOutFemale = preTransferOutFemale;
	}

	public int getPreFailedFemale() {
		return preFailedFemale;
	}

	public void setPreFailedFemale(int preFailedFemale) {
		this.preFailedFemale = preFailedFemale;
	}

	public int getPreShiftFemale() {
		return preShiftFemale;
	}

	public void setPreShiftFemale(int preShiftFemale) {
		this.preShiftFemale = preShiftFemale;
	}

	public int getPreESCFemale() {
		return preESCFemale;
	}

	public void setPreESCFemale(int preESCFemale) {
		this.preESCFemale = preESCFemale;
	}

	public int getPreEVSFemale() {
		return preEVSFemale;
	}

	public void setPreEVSFemale(int preEVSFemale) {
		this.preEVSFemale = preEVSFemale;
	}

	public int getPreESCCompleteFemale() {
		return preESCCompleteFemale;
	}

	public void setPreESCCompleteFemale(int preESCCompleteFemale) {
		this.preESCCompleteFemale = preESCCompleteFemale;
	}

	public int getPreEVSCompleteFemale() {
		return preEVSCompleteFemale;
	}

	public void setPreEVSCompleteFemale(int preEVSCompleteFemale) {
		this.preEVSCompleteFemale = preEVSCompleteFemale;
	}

	public int getPreESCTransferOutFemale() {
		return preESCTransferOutFemale;
	}

	public void setPreESCTransferOutFemale(int preESCTransferOutFemale) {
		this.preESCTransferOutFemale = preESCTransferOutFemale;
	}

	public int getPreEVSTransferOutFemale() {
		return preEVSTransferOutFemale;
	}

	public void setPreEVSTransferOutFemale(int preEVSTransferOutFemale) {
		this.preEVSTransferOutFemale = preEVSTransferOutFemale;
	}

	public int getPreESCDropFemale() {
		return preESCDropFemale;
	}

	public void setPreESCDropFemale(int preESCDropFemale) {
		this.preESCDropFemale = preESCDropFemale;
	}

	public int getPreEVSDropFemale() {
		return preEVSDropFemale;
	}

	public void setPreEVSDropFemale(int preEVSDropFemale) {
		this.preEVSDropFemale = preEVSDropFemale;
	}

	public int getPreESCFailedFemale() {
		return preESCFailedFemale;
	}

	public void setPreESCFailedFemale(int preESCFailedFemale) {
		this.preESCFailedFemale = preESCFailedFemale;
	}

	public int getPreEVSFailedFemale() {
		return preEVSFailedFemale;
	}

	public void setPreEVSFailedFemale(int preEVSFailedFemale) {
		this.preEVSFailedFemale = preEVSFailedFemale;
	}

	public int getG1Male() {
		return g1Male;
	}

	public void setG1Male(int male) {
		g1Male = male;
	}

	public int getG14Male() {
		return g14Male;
	}

	public void setG14Male(int male) {
		g14Male = male;
	}

	public int getG15Male() {
		return g15Male;
	}

	public void setG15Male(int male) {
		g15Male = male;
	}

	public int getG16Male() {
		return g16Male;
	}

	public void setG16Male(int male) {
		g16Male = male;
	}

	public int getG17Male() {
		return g17Male;
	}

	public void setG17Male(int male) {
		g17Male = male;
	}

	public int getG18Male() {
		return g18Male;
	}

	public void setG18Male(int male) {
		g18Male = male;
	}

	public int getG19Male() {
		return g19Male;
	}

	public void setG19Male(int male) {
		g19Male = male;
	}

	public int getG110Male() {
		return g110Male;
	}

	public void setG110Male(int male) {
		g110Male = male;
	}

	public int getG111Male() {
		return g111Male;
	}

	public void setG111Male(int male) {
		g111Male = male;
	}

	public int getG112Male() {
		return g112Male;
	}

	public void setG112Male(int male) {
		g112Male = male;
	}

	public int getG113Male() {
		return g113Male;
	}

	public void setG113Male(int male) {
		g113Male = male;
	}

	public int getG114Male() {
		return g114Male;
	}

	public void setG114Male(int male) {
		g114Male = male;
	}

	public int getG115Male() {
		return g115Male;
	}

	public void setG115Male(int male) {
		g115Male = male;
	}

	public int getG116Male() {
		return g116Male;
	}

	public void setG116Male(int male) {
		g116Male = male;
	}

	public int getG117Male() {
		return g117Male;
	}

	public void setG117Male(int male) {
		g117Male = male;
	}

	public int getG118Male() {
		return g118Male;
	}

	public void setG118Male(int male) {
		g118Male = male;
	}

	public int getG119Male() {
		return g119Male;
	}

	public void setG119Male(int male) {
		g119Male = male;
	}

	public int getG120Male() {
		return g120Male;
	}

	public void setG120Male(int male) {
		g120Male = male;
	}

	public int getG1MonoMale() {
		return g1MonoMale;
	}

	public void setG1MonoMale(int monoMale) {
		g1MonoMale = monoMale;
	}

	public int getG1MultiMale() {
		return g1MultiMale;
	}

	public void setG1MultiMale(int multiMale) {
		g1MultiMale = multiMale;
	}

	public int getG1SpedMale() {
		return g1SpedMale;
	}

	public void setG1SpedMale(int spedMale) {
		g1SpedMale = spedMale;
	}

	public int getG1RepeatMale() {
		return g1RepeatMale;
	}

	public void setG1RepeatMale(int repeatMale) {
		g1RepeatMale = repeatMale;
	}

	public int getG1TransferMale() {
		return g1TransferMale;
	}

	public void setG1TransferMale(int transferMale) {
		g1TransferMale = transferMale;
	}

	public int getG1BackMale() {
		return g1BackMale;
	}

	public void setG1BackMale(int backMale) {
		g1BackMale = backMale;
	}

	public int getG1MuslimMale() {
		return g1MuslimMale;
	}

	public void setG1MuslimMale(int muslimMale) {
		g1MuslimMale = muslimMale;
	}

	public int getG1IndigenousMale() {
		return g1IndigenousMale;
	}

	public void setG1IndigenousMale(int indigenousMale) {
		g1IndigenousMale = indigenousMale;
	}

	public int getG1ClassesMale() {
		return g1ClassesMale;
	}

	public void setG1ClassesMale(int classesMale) {
		g1ClassesMale = classesMale;
	}

	public int getG1CompleteMale() {
		return g1CompleteMale;
	}

	public void setG1CompleteMale(int completeMale) {
		g1CompleteMale = completeMale;
	}

	public int getG1DropMale() {
		return g1DropMale;
	}

	public void setG1DropMale(int dropMale) {
		g1DropMale = dropMale;
	}

	public int getG1TransferOutMale() {
		return g1TransferOutMale;
	}

	public void setG1TransferOutMale(int transferOutMale) {
		g1TransferOutMale = transferOutMale;
	}

	public int getG1FailedMale() {
		return g1FailedMale;
	}

	public void setG1FailedMale(int failedMale) {
		g1FailedMale = failedMale;
	}

	public int getG1ShiftMale() {
		return g1ShiftMale;
	}

	public void setG1ShiftMale(int shiftMale) {
		g1ShiftMale = shiftMale;
	}

	public int getG1ESCMale() {
		return g1ESCMale;
	}

	public void setG1ESCMale(int male) {
		g1ESCMale = male;
	}

	public int getG1EVSMale() {
		return g1EVSMale;
	}

	public void setG1EVSMale(int male) {
		g1EVSMale = male;
	}

	public int getG1ESCCompleteMale() {
		return g1ESCCompleteMale;
	}

	public void setG1ESCCompleteMale(int completeMale) {
		g1ESCCompleteMale = completeMale;
	}

	public int getG1EVSCompleteMale() {
		return g1EVSCompleteMale;
	}

	public void setG1EVSCompleteMale(int completeMale) {
		g1EVSCompleteMale = completeMale;
	}

	public int getG1ESCTransferOutMale() {
		return g1ESCTransferOutMale;
	}

	public void setG1ESCTransferOutMale(int transferOutMale) {
		g1ESCTransferOutMale = transferOutMale;
	}

	public int getG1EVSTransferOutMale() {
		return g1EVSTransferOutMale;
	}

	public void setG1EVSTransferOutMale(int transferOutMale) {
		g1EVSTransferOutMale = transferOutMale;
	}

	public int getG1ESCDropMale() {
		return g1ESCDropMale;
	}

	public void setG1ESCDropMale(int dropMale) {
		g1ESCDropMale = dropMale;
	}

	public int getG1EVSDropMale() {
		return g1EVSDropMale;
	}

	public void setG1EVSDropMale(int dropMale) {
		g1EVSDropMale = dropMale;
	}

	public int getG1ESCFailedMale() {
		return g1ESCFailedMale;
	}

	public void setG1ESCFailedMale(int failedMale) {
		g1ESCFailedMale = failedMale;
	}

	public int getG1EVSFailedMale() {
		return g1EVSFailedMale;
	}

	public void setG1EVSFailedMale(int failedMale) {
		g1EVSFailedMale = failedMale;
	}

	public int getG1Female() {
		return g1Female;
	}

	public void setG1Female(int female) {
		g1Female = female;
	}

	public int getG14Female() {
		return g14Female;
	}

	public void setG14Female(int female) {
		g14Female = female;
	}

	public int getG15Female() {
		return g15Female;
	}

	public void setG15Female(int female) {
		g15Female = female;
	}

	public int getG16Female() {
		return g16Female;
	}

	public void setG16Female(int female) {
		g16Female = female;
	}

	public int getG17Female() {
		return g17Female;
	}

	public void setG17Female(int female) {
		g17Female = female;
	}

	public int getG18Female() {
		return g18Female;
	}

	public void setG18Female(int female) {
		g18Female = female;
	}

	public int getG19Female() {
		return g19Female;
	}

	public void setG19Female(int female) {
		g19Female = female;
	}

	public int getG110Female() {
		return g110Female;
	}

	public void setG110Female(int female) {
		g110Female = female;
	}

	public int getG111Female() {
		return g111Female;
	}

	public void setG111Female(int female) {
		g111Female = female;
	}

	public int getG112Female() {
		return g112Female;
	}

	public void setG112Female(int female) {
		g112Female = female;
	}

	public int getG113Female() {
		return g113Female;
	}

	public void setG113Female(int female) {
		g113Female = female;
	}

	public int getG114Female() {
		return g114Female;
	}

	public void setG114Female(int female) {
		g114Female = female;
	}

	public int getG115Female() {
		return g115Female;
	}

	public void setG115Female(int female) {
		g115Female = female;
	}

	public int getG116Female() {
		return g116Female;
	}

	public void setG116Female(int female) {
		g116Female = female;
	}

	public int getG117Female() {
		return g117Female;
	}

	public void setG117Female(int female) {
		g117Female = female;
	}

	public int getG118Female() {
		return g118Female;
	}

	public void setG118Female(int female) {
		g118Female = female;
	}

	public int getG119Female() {
		return g119Female;
	}

	public void setG119Female(int female) {
		g119Female = female;
	}

	public int getG120Female() {
		return g120Female;
	}

	public void setG120Female(int female) {
		g120Female = female;
	}

	public int getG1MonoFemale() {
		return g1MonoFemale;
	}

	public void setG1MonoFemale(int monoFemale) {
		g1MonoFemale = monoFemale;
	}

	public int getG1MultiFemale() {
		return g1MultiFemale;
	}

	public void setG1MultiFemale(int multiFemale) {
		g1MultiFemale = multiFemale;
	}

	public int getG1SpedFemale() {
		return g1SpedFemale;
	}

	public void setG1SpedFemale(int spedFemale) {
		g1SpedFemale = spedFemale;
	}

	public int getG1RepeatFemale() {
		return g1RepeatFemale;
	}

	public void setG1RepeatFemale(int repeatFemale) {
		g1RepeatFemale = repeatFemale;
	}

	public int getG1TransferFemale() {
		return g1TransferFemale;
	}

	public void setG1TransferFemale(int transferFemale) {
		g1TransferFemale = transferFemale;
	}

	public int getG1BackFemale() {
		return g1BackFemale;
	}

	public void setG1BackFemale(int backFemale) {
		g1BackFemale = backFemale;
	}

	public int getG1MuslimFemale() {
		return g1MuslimFemale;
	}

	public void setG1MuslimFemale(int muslimFemale) {
		g1MuslimFemale = muslimFemale;
	}

	public int getG1IndigenousFemale() {
		return g1IndigenousFemale;
	}

	public void setG1IndigenousFemale(int indigenousFemale) {
		g1IndigenousFemale = indigenousFemale;
	}

	public int getG1ClassesFemale() {
		return g1ClassesFemale;
	}

	public void setG1ClassesFemale(int classesFemale) {
		g1ClassesFemale = classesFemale;
	}

	public int getG1CompleteFemale() {
		return g1CompleteFemale;
	}

	public void setG1CompleteFemale(int completeFemale) {
		g1CompleteFemale = completeFemale;
	}

	public int getG1DropFemale() {
		return g1DropFemale;
	}

	public void setG1DropFemale(int dropFemale) {
		g1DropFemale = dropFemale;
	}

	public int getG1TransferOutFemale() {
		return g1TransferOutFemale;
	}

	public void setG1TransferOutFemale(int transferOutFemale) {
		g1TransferOutFemale = transferOutFemale;
	}

	public int getG1FailedFemale() {
		return g1FailedFemale;
	}

	public void setG1FailedFemale(int failedFemale) {
		g1FailedFemale = failedFemale;
	}

	public int getG1ShiftFemale() {
		return g1ShiftFemale;
	}

	public void setG1ShiftFemale(int shiftFemale) {
		g1ShiftFemale = shiftFemale;
	}

	public int getG1ESCFemale() {
		return g1ESCFemale;
	}

	public void setG1ESCFemale(int female) {
		g1ESCFemale = female;
	}

	public int getG1EVSFemale() {
		return g1EVSFemale;
	}

	public void setG1EVSFemale(int female) {
		g1EVSFemale = female;
	}

	public int getG1ESCCompleteFemale() {
		return g1ESCCompleteFemale;
	}

	public void setG1ESCCompleteFemale(int completeFemale) {
		g1ESCCompleteFemale = completeFemale;
	}

	public int getG1EVSCompleteFemale() {
		return g1EVSCompleteFemale;
	}

	public void setG1EVSCompleteFemale(int completeFemale) {
		g1EVSCompleteFemale = completeFemale;
	}

	public int getG1ESCTransferOutFemale() {
		return g1ESCTransferOutFemale;
	}

	public void setG1ESCTransferOutFemale(int transferOutFemale) {
		g1ESCTransferOutFemale = transferOutFemale;
	}

	public int getG1EVSTransferOutFemale() {
		return g1EVSTransferOutFemale;
	}

	public void setG1EVSTransferOutFemale(int transferOutFemale) {
		g1EVSTransferOutFemale = transferOutFemale;
	}

	public int getG1ESCDropFemale() {
		return g1ESCDropFemale;
	}

	public void setG1ESCDropFemale(int dropFemale) {
		g1ESCDropFemale = dropFemale;
	}

	public int getG1EVSDropFemale() {
		return g1EVSDropFemale;
	}

	public void setG1EVSDropFemale(int dropFemale) {
		g1EVSDropFemale = dropFemale;
	}

	public int getG1ESCFailedFemale() {
		return g1ESCFailedFemale;
	}

	public void setG1ESCFailedFemale(int failedFemale) {
		g1ESCFailedFemale = failedFemale;
	}

	public int getG1EVSFailedFemale() {
		return g1EVSFailedFemale;
	}

	public void setG1EVSFailedFemale(int failedFemale) {
		g1EVSFailedFemale = failedFemale;
	}

	public int getG2Male() {
		return g2Male;
	}

	public void setG2Male(int male) {
		g2Male = male;
	}

	public int getG24Male() {
		return g24Male;
	}

	public void setG24Male(int male) {
		g24Male = male;
	}

	public int getG25Male() {
		return g25Male;
	}

	public void setG25Male(int male) {
		g25Male = male;
	}

	public int getG26Male() {
		return g26Male;
	}

	public void setG26Male(int male) {
		g26Male = male;
	}

	public int getG27Male() {
		return g27Male;
	}

	public void setG27Male(int male) {
		g27Male = male;
	}

	public int getG28Male() {
		return g28Male;
	}

	public void setG28Male(int male) {
		g28Male = male;
	}

	public int getG29Male() {
		return g29Male;
	}

	public void setG29Male(int male) {
		g29Male = male;
	}

	public int getG210Male() {
		return g210Male;
	}

	public void setG210Male(int male) {
		g210Male = male;
	}

	public int getG211Male() {
		return g211Male;
	}

	public void setG211Male(int male) {
		g211Male = male;
	}

	public int getG212Male() {
		return g212Male;
	}

	public void setG212Male(int male) {
		g212Male = male;
	}

	public int getG213Male() {
		return g213Male;
	}

	public void setG213Male(int male) {
		g213Male = male;
	}

	public int getG214Male() {
		return g214Male;
	}

	public void setG214Male(int male) {
		g214Male = male;
	}

	public int getG215Male() {
		return g215Male;
	}

	public void setG215Male(int male) {
		g215Male = male;
	}

	public int getG216Male() {
		return g216Male;
	}

	public void setG216Male(int male) {
		g216Male = male;
	}

	public int getG217Male() {
		return g217Male;
	}

	public void setG217Male(int male) {
		g217Male = male;
	}

	public int getG218Male() {
		return g218Male;
	}

	public void setG218Male(int male) {
		g218Male = male;
	}

	public int getG219Male() {
		return g219Male;
	}

	public void setG219Male(int male) {
		g219Male = male;
	}

	public int getG220Male() {
		return g220Male;
	}

	public void setG220Male(int male) {
		g220Male = male;
	}

	public int getG2MonoMale() {
		return g2MonoMale;
	}

	public void setG2MonoMale(int monoMale) {
		g2MonoMale = monoMale;
	}

	public int getG2MultiMale() {
		return g2MultiMale;
	}

	public void setG2MultiMale(int multiMale) {
		g2MultiMale = multiMale;
	}

	public int getG2SpedMale() {
		return g2SpedMale;
	}

	public void setG2SpedMale(int spedMale) {
		g2SpedMale = spedMale;
	}

	public int getG2RepeatMale() {
		return g2RepeatMale;
	}

	public void setG2RepeatMale(int repeatMale) {
		g2RepeatMale = repeatMale;
	}

	public int getG2TransferMale() {
		return g2TransferMale;
	}

	public void setG2TransferMale(int transferMale) {
		g2TransferMale = transferMale;
	}

	public int getG2BackMale() {
		return g2BackMale;
	}

	public void setG2BackMale(int backMale) {
		g2BackMale = backMale;
	}

	public int getG2MuslimMale() {
		return g2MuslimMale;
	}

	public void setG2MuslimMale(int muslimMale) {
		g2MuslimMale = muslimMale;
	}

	public int getG2IndigenousMale() {
		return g2IndigenousMale;
	}

	public void setG2IndigenousMale(int indigenousMale) {
		g2IndigenousMale = indigenousMale;
	}

	public int getG2ClassesMale() {
		return g2ClassesMale;
	}

	public void setG2ClassesMale(int classesMale) {
		g2ClassesMale = classesMale;
	}

	public int getG2CompleteMale() {
		return g2CompleteMale;
	}

	public void setG2CompleteMale(int completeMale) {
		g2CompleteMale = completeMale;
	}

	public int getG2DropMale() {
		return g2DropMale;
	}

	public void setG2DropMale(int dropMale) {
		g2DropMale = dropMale;
	}

	public int getG2TransferOutMale() {
		return g2TransferOutMale;
	}

	public void setG2TransferOutMale(int transferOutMale) {
		g2TransferOutMale = transferOutMale;
	}

	public int getG2FailedMale() {
		return g2FailedMale;
	}

	public void setG2FailedMale(int failedMale) {
		g2FailedMale = failedMale;
	}

	public int getG2ShiftMale() {
		return g2ShiftMale;
	}

	public void setG2ShiftMale(int shiftMale) {
		g2ShiftMale = shiftMale;
	}

	public int getG2ESCMale() {
		return g2ESCMale;
	}

	public void setG2ESCMale(int male) {
		g2ESCMale = male;
	}

	public int getG2EVSMale() {
		return g2EVSMale;
	}

	public void setG2EVSMale(int male) {
		g2EVSMale = male;
	}

	public int getG2ESCCompleteMale() {
		return g2ESCCompleteMale;
	}

	public void setG2ESCCompleteMale(int completeMale) {
		g2ESCCompleteMale = completeMale;
	}

	public int getG2EVSCompleteMale() {
		return g2EVSCompleteMale;
	}

	public void setG2EVSCompleteMale(int completeMale) {
		g2EVSCompleteMale = completeMale;
	}

	public int getG2ESCTransferOutMale() {
		return g2ESCTransferOutMale;
	}

	public void setG2ESCTransferOutMale(int transferOutMale) {
		g2ESCTransferOutMale = transferOutMale;
	}

	public int getG2EVSTransferOutMale() {
		return g2EVSTransferOutMale;
	}

	public void setG2EVSTransferOutMale(int transferOutMale) {
		g2EVSTransferOutMale = transferOutMale;
	}

	public int getG2ESCDropMale() {
		return g2ESCDropMale;
	}

	public void setG2ESCDropMale(int dropMale) {
		g2ESCDropMale = dropMale;
	}

	public int getG2EVSDropMale() {
		return g2EVSDropMale;
	}

	public void setG2EVSDropMale(int dropMale) {
		g2EVSDropMale = dropMale;
	}

	public int getG2ESCFailedMale() {
		return g2ESCFailedMale;
	}

	public void setG2ESCFailedMale(int failedMale) {
		g2ESCFailedMale = failedMale;
	}

	public int getG2EVSFailedMale() {
		return g2EVSFailedMale;
	}

	public void setG2EVSFailedMale(int failedMale) {
		g2EVSFailedMale = failedMale;
	}

	public int getG2Female() {
		return g2Female;
	}

	public void setG2Female(int female) {
		g2Female = female;
	}

	public int getG24Female() {
		return g24Female;
	}

	public void setG24Female(int female) {
		g24Female = female;
	}

	public int getG25Female() {
		return g25Female;
	}

	public void setG25Female(int female) {
		g25Female = female;
	}

	public int getG26Female() {
		return g26Female;
	}

	public void setG26Female(int female) {
		g26Female = female;
	}

	public int getG27Female() {
		return g27Female;
	}

	public void setG27Female(int female) {
		g27Female = female;
	}

	public int getG28Female() {
		return g28Female;
	}

	public void setG28Female(int female) {
		g28Female = female;
	}

	public int getG29Female() {
		return g29Female;
	}

	public void setG29Female(int female) {
		g29Female = female;
	}

	public int getG210Female() {
		return g210Female;
	}

	public void setG210Female(int female) {
		g210Female = female;
	}

	public int getG211Female() {
		return g211Female;
	}

	public void setG211Female(int female) {
		g211Female = female;
	}

	public int getG212Female() {
		return g212Female;
	}

	public void setG212Female(int female) {
		g212Female = female;
	}

	public int getG213Female() {
		return g213Female;
	}

	public void setG213Female(int female) {
		g213Female = female;
	}

	public int getG214Female() {
		return g214Female;
	}

	public void setG214Female(int female) {
		g214Female = female;
	}

	public int getG215Female() {
		return g215Female;
	}

	public void setG215Female(int female) {
		g215Female = female;
	}

	public int getG216Female() {
		return g216Female;
	}

	public void setG216Female(int female) {
		g216Female = female;
	}

	public int getG217Female() {
		return g217Female;
	}

	public void setG217Female(int female) {
		g217Female = female;
	}

	public int getG218Female() {
		return g218Female;
	}

	public void setG218Female(int female) {
		g218Female = female;
	}

	public int getG219Female() {
		return g219Female;
	}

	public void setG219Female(int female) {
		g219Female = female;
	}

	public int getG220Female() {
		return g220Female;
	}

	public void setG220Female(int female) {
		g220Female = female;
	}

	public int getG2MonoFemale() {
		return g2MonoFemale;
	}

	public void setG2MonoFemale(int monoFemale) {
		g2MonoFemale = monoFemale;
	}

	public int getG2MultiFemale() {
		return g2MultiFemale;
	}

	public void setG2MultiFemale(int multiFemale) {
		g2MultiFemale = multiFemale;
	}

	public int getG2SpedFemale() {
		return g2SpedFemale;
	}

	public void setG2SpedFemale(int spedFemale) {
		g2SpedFemale = spedFemale;
	}

	public int getG2RepeatFemale() {
		return g2RepeatFemale;
	}

	public void setG2RepeatFemale(int repeatFemale) {
		g2RepeatFemale = repeatFemale;
	}

	public int getG2TransferFemale() {
		return g2TransferFemale;
	}

	public void setG2TransferFemale(int transferFemale) {
		g2TransferFemale = transferFemale;
	}

	public int getG2BackFemale() {
		return g2BackFemale;
	}

	public void setG2BackFemale(int backFemale) {
		g2BackFemale = backFemale;
	}

	public int getG2MuslimFemale() {
		return g2MuslimFemale;
	}

	public void setG2MuslimFemale(int muslimFemale) {
		g2MuslimFemale = muslimFemale;
	}

	public int getG2IndigenousFemale() {
		return g2IndigenousFemale;
	}

	public void setG2IndigenousFemale(int indigenousFemale) {
		g2IndigenousFemale = indigenousFemale;
	}

	public int getG2ClassesFemale() {
		return g2ClassesFemale;
	}

	public void setG2ClassesFemale(int classesFemale) {
		g2ClassesFemale = classesFemale;
	}

	public int getG2CompleteFemale() {
		return g2CompleteFemale;
	}

	public void setG2CompleteFemale(int completeFemale) {
		g2CompleteFemale = completeFemale;
	}

	public int getG2DropFemale() {
		return g2DropFemale;
	}

	public void setG2DropFemale(int dropFemale) {
		g2DropFemale = dropFemale;
	}

	public int getG2TransferOutFemale() {
		return g2TransferOutFemale;
	}

	public void setG2TransferOutFemale(int transferOutFemale) {
		g2TransferOutFemale = transferOutFemale;
	}

	public int getG2FailedFemale() {
		return g2FailedFemale;
	}

	public void setG2FailedFemale(int failedFemale) {
		g2FailedFemale = failedFemale;
	}

	public int getG2ShiftFemale() {
		return g2ShiftFemale;
	}

	public void setG2ShiftFemale(int shiftFemale) {
		g2ShiftFemale = shiftFemale;
	}

	public int getG2ESCFemale() {
		return g2ESCFemale;
	}

	public void setG2ESCFemale(int female) {
		g2ESCFemale = female;
	}

	public int getG2EVSFemale() {
		return g2EVSFemale;
	}

	public void setG2EVSFemale(int female) {
		g2EVSFemale = female;
	}

	public int getG2ESCCompleteFemale() {
		return g2ESCCompleteFemale;
	}

	public void setG2ESCCompleteFemale(int completeFemale) {
		g2ESCCompleteFemale = completeFemale;
	}

	public int getG2EVSCompleteFemale() {
		return g2EVSCompleteFemale;
	}

	public void setG2EVSCompleteFemale(int completeFemale) {
		g2EVSCompleteFemale = completeFemale;
	}

	public int getG2ESCTransferOutFemale() {
		return g2ESCTransferOutFemale;
	}

	public void setG2ESCTransferOutFemale(int transferOutFemale) {
		g2ESCTransferOutFemale = transferOutFemale;
	}

	public int getG2EVSTransferOutFemale() {
		return g2EVSTransferOutFemale;
	}

	public void setG2EVSTransferOutFemale(int transferOutFemale) {
		g2EVSTransferOutFemale = transferOutFemale;
	}

	public int getG2ESCDropFemale() {
		return g2ESCDropFemale;
	}

	public void setG2ESCDropFemale(int dropFemale) {
		g2ESCDropFemale = dropFemale;
	}

	public int getG2EVSDropFemale() {
		return g2EVSDropFemale;
	}

	public void setG2EVSDropFemale(int dropFemale) {
		g2EVSDropFemale = dropFemale;
	}

	public int getG2ESCFailedFemale() {
		return g2ESCFailedFemale;
	}

	public void setG2ESCFailedFemale(int failedFemale) {
		g2ESCFailedFemale = failedFemale;
	}

	public int getG2EVSFailedFemale() {
		return g2EVSFailedFemale;
	}

	public void setG2EVSFailedFemale(int failedFemale) {
		g2EVSFailedFemale = failedFemale;
	}

	public int getG3Male() {
		return g3Male;
	}

	public void setG3Male(int male) {
		g3Male = male;
	}

	public int getG34Male() {
		return g34Male;
	}

	public void setG34Male(int male) {
		g34Male = male;
	}

	public int getG35Male() {
		return g35Male;
	}

	public void setG35Male(int male) {
		g35Male = male;
	}

	public int getG36Male() {
		return g36Male;
	}

	public void setG36Male(int male) {
		g36Male = male;
	}

	public int getG37Male() {
		return g37Male;
	}

	public void setG37Male(int male) {
		g37Male = male;
	}

	public int getG38Male() {
		return g38Male;
	}

	public void setG38Male(int male) {
		g38Male = male;
	}

	public int getG39Male() {
		return g39Male;
	}

	public void setG39Male(int male) {
		g39Male = male;
	}

	public int getG310Male() {
		return g310Male;
	}

	public void setG310Male(int male) {
		g310Male = male;
	}

	public int getG311Male() {
		return g311Male;
	}

	public void setG311Male(int male) {
		g311Male = male;
	}

	public int getG312Male() {
		return g312Male;
	}

	public void setG312Male(int male) {
		g312Male = male;
	}

	public int getG313Male() {
		return g313Male;
	}

	public void setG313Male(int male) {
		g313Male = male;
	}

	public int getG314Male() {
		return g314Male;
	}

	public void setG314Male(int male) {
		g314Male = male;
	}

	public int getG315Male() {
		return g315Male;
	}

	public void setG315Male(int male) {
		g315Male = male;
	}

	public int getG316Male() {
		return g316Male;
	}

	public void setG316Male(int male) {
		g316Male = male;
	}

	public int getG317Male() {
		return g317Male;
	}

	public void setG317Male(int male) {
		g317Male = male;
	}

	public int getG318Male() {
		return g318Male;
	}

	public void setG318Male(int male) {
		g318Male = male;
	}

	public int getG319Male() {
		return g319Male;
	}

	public void setG319Male(int male) {
		g319Male = male;
	}

	public int getG320Male() {
		return g320Male;
	}

	public void setG320Male(int male) {
		g320Male = male;
	}

	public int getG3MonoMale() {
		return g3MonoMale;
	}

	public void setG3MonoMale(int monoMale) {
		g3MonoMale = monoMale;
	}

	public int getG3MultiMale() {
		return g3MultiMale;
	}

	public void setG3MultiMale(int multiMale) {
		g3MultiMale = multiMale;
	}

	public int getG3SpedMale() {
		return g3SpedMale;
	}

	public void setG3SpedMale(int spedMale) {
		g3SpedMale = spedMale;
	}

	public int getG3RepeatMale() {
		return g3RepeatMale;
	}

	public void setG3RepeatMale(int repeatMale) {
		g3RepeatMale = repeatMale;
	}

	public int getG3TransferMale() {
		return g3TransferMale;
	}

	public void setG3TransferMale(int transferMale) {
		g3TransferMale = transferMale;
	}

	public int getG3BackMale() {
		return g3BackMale;
	}

	public void setG3BackMale(int backMale) {
		g3BackMale = backMale;
	}

	public int getG3MuslimMale() {
		return g3MuslimMale;
	}

	public void setG3MuslimMale(int muslimMale) {
		g3MuslimMale = muslimMale;
	}

	public int getG3IndigenousMale() {
		return g3IndigenousMale;
	}

	public void setG3IndigenousMale(int indigenousMale) {
		g3IndigenousMale = indigenousMale;
	}

	public int getG3ClassesMale() {
		return g3ClassesMale;
	}

	public void setG3ClassesMale(int classesMale) {
		g3ClassesMale = classesMale;
	}

	public int getG3CompleteMale() {
		return g3CompleteMale;
	}

	public void setG3CompleteMale(int completeMale) {
		g3CompleteMale = completeMale;
	}

	public int getG3DropMale() {
		return g3DropMale;
	}

	public void setG3DropMale(int dropMale) {
		g3DropMale = dropMale;
	}

	public int getG3TransferOutMale() {
		return g3TransferOutMale;
	}

	public void setG3TransferOutMale(int transferOutMale) {
		g3TransferOutMale = transferOutMale;
	}

	public int getG3FailedMale() {
		return g3FailedMale;
	}

	public void setG3FailedMale(int failedMale) {
		g3FailedMale = failedMale;
	}

	public int getG3ShiftMale() {
		return g3ShiftMale;
	}

	public void setG3ShiftMale(int shiftMale) {
		g3ShiftMale = shiftMale;
	}

	public int getG3ESCMale() {
		return g3ESCMale;
	}

	public void setG3ESCMale(int male) {
		g3ESCMale = male;
	}

	public int getG3EVSMale() {
		return g3EVSMale;
	}

	public void setG3EVSMale(int male) {
		g3EVSMale = male;
	}

	public int getG3ESCCompleteMale() {
		return g3ESCCompleteMale;
	}

	public void setG3ESCCompleteMale(int completeMale) {
		g3ESCCompleteMale = completeMale;
	}

	public int getG3EVSCompleteMale() {
		return g3EVSCompleteMale;
	}

	public void setG3EVSCompleteMale(int completeMale) {
		g3EVSCompleteMale = completeMale;
	}

	public int getG3ESCTransferOutMale() {
		return g3ESCTransferOutMale;
	}

	public void setG3ESCTransferOutMale(int transferOutMale) {
		g3ESCTransferOutMale = transferOutMale;
	}

	public int getG3EVSTransferOutMale() {
		return g3EVSTransferOutMale;
	}

	public void setG3EVSTransferOutMale(int transferOutMale) {
		g3EVSTransferOutMale = transferOutMale;
	}

	public int getG3ESCDropMale() {
		return g3ESCDropMale;
	}

	public void setG3ESCDropMale(int dropMale) {
		g3ESCDropMale = dropMale;
	}

	public int getG3EVSDropMale() {
		return g3EVSDropMale;
	}

	public void setG3EVSDropMale(int dropMale) {
		g3EVSDropMale = dropMale;
	}

	public int getG3ESCFailedMale() {
		return g3ESCFailedMale;
	}

	public void setG3ESCFailedMale(int failedMale) {
		g3ESCFailedMale = failedMale;
	}

	public int getG3EVSFailedMale() {
		return g3EVSFailedMale;
	}

	public void setG3EVSFailedMale(int failedMale) {
		g3EVSFailedMale = failedMale;
	}

	public int getG3Female() {
		return g3Female;
	}

	public void setG3Female(int female) {
		g3Female = female;
	}

	public int getG34Female() {
		return g34Female;
	}

	public void setG34Female(int female) {
		g34Female = female;
	}

	public int getG35Female() {
		return g35Female;
	}

	public void setG35Female(int female) {
		g35Female = female;
	}

	public int getG36Female() {
		return g36Female;
	}

	public void setG36Female(int female) {
		g36Female = female;
	}

	public int getG37Female() {
		return g37Female;
	}

	public void setG37Female(int female) {
		g37Female = female;
	}

	public int getG38Female() {
		return g38Female;
	}

	public void setG38Female(int female) {
		g38Female = female;
	}

	public int getG39Female() {
		return g39Female;
	}

	public void setG39Female(int female) {
		g39Female = female;
	}

	public int getG310Female() {
		return g310Female;
	}

	public void setG310Female(int female) {
		g310Female = female;
	}

	public int getG311Female() {
		return g311Female;
	}

	public void setG311Female(int female) {
		g311Female = female;
	}

	public int getG312Female() {
		return g312Female;
	}

	public void setG312Female(int female) {
		g312Female = female;
	}

	public int getG313Female() {
		return g313Female;
	}

	public void setG313Female(int female) {
		g313Female = female;
	}

	public int getG314Female() {
		return g314Female;
	}

	public void setG314Female(int female) {
		g314Female = female;
	}

	public int getG315Female() {
		return g315Female;
	}

	public void setG315Female(int female) {
		g315Female = female;
	}

	public int getG316Female() {
		return g316Female;
	}

	public void setG316Female(int female) {
		g316Female = female;
	}

	public int getG317Female() {
		return g317Female;
	}

	public void setG317Female(int female) {
		g317Female = female;
	}

	public int getG318Female() {
		return g318Female;
	}

	public void setG318Female(int female) {
		g318Female = female;
	}

	public int getG319Female() {
		return g319Female;
	}

	public void setG319Female(int female) {
		g319Female = female;
	}

	public int getG320Female() {
		return g320Female;
	}

	public void setG320Female(int female) {
		g320Female = female;
	}

	public int getG3MonoFemale() {
		return g3MonoFemale;
	}

	public void setG3MonoFemale(int monoFemale) {
		g3MonoFemale = monoFemale;
	}

	public int getG3MultiFemale() {
		return g3MultiFemale;
	}

	public void setG3MultiFemale(int multiFemale) {
		g3MultiFemale = multiFemale;
	}

	public int getG3SpedFemale() {
		return g3SpedFemale;
	}

	public void setG3SpedFemale(int spedFemale) {
		g3SpedFemale = spedFemale;
	}

	public int getG3RepeatFemale() {
		return g3RepeatFemale;
	}

	public void setG3RepeatFemale(int repeatFemale) {
		g3RepeatFemale = repeatFemale;
	}

	public int getG3TransferFemale() {
		return g3TransferFemale;
	}

	public void setG3TransferFemale(int transferFemale) {
		g3TransferFemale = transferFemale;
	}

	public int getG3BackFemale() {
		return g3BackFemale;
	}

	public void setG3BackFemale(int backFemale) {
		g3BackFemale = backFemale;
	}

	public int getG3MuslimFemale() {
		return g3MuslimFemale;
	}

	public void setG3MuslimFemale(int muslimFemale) {
		g3MuslimFemale = muslimFemale;
	}

	public int getG3IndigenousFemale() {
		return g3IndigenousFemale;
	}

	public void setG3IndigenousFemale(int indigenousFemale) {
		g3IndigenousFemale = indigenousFemale;
	}

	public int getG3ClassesFemale() {
		return g3ClassesFemale;
	}

	public void setG3ClassesFemale(int classesFemale) {
		g3ClassesFemale = classesFemale;
	}

	public int getG3CompleteFemale() {
		return g3CompleteFemale;
	}

	public void setG3CompleteFemale(int completeFemale) {
		g3CompleteFemale = completeFemale;
	}

	public int getG3DropFemale() {
		return g3DropFemale;
	}

	public void setG3DropFemale(int dropFemale) {
		g3DropFemale = dropFemale;
	}

	public int getG3TransferOutFemale() {
		return g3TransferOutFemale;
	}

	public void setG3TransferOutFemale(int transferOutFemale) {
		g3TransferOutFemale = transferOutFemale;
	}

	public int getG3FailedFemale() {
		return g3FailedFemale;
	}

	public void setG3FailedFemale(int failedFemale) {
		g3FailedFemale = failedFemale;
	}

	public int getG3ShiftFemale() {
		return g3ShiftFemale;
	}

	public void setG3ShiftFemale(int shiftFemale) {
		g3ShiftFemale = shiftFemale;
	}

	public int getG3ESCFemale() {
		return g3ESCFemale;
	}

	public void setG3ESCFemale(int female) {
		g3ESCFemale = female;
	}

	public int getG3EVSFemale() {
		return g3EVSFemale;
	}

	public void setG3EVSFemale(int female) {
		g3EVSFemale = female;
	}

	public int getG3ESCCompleteFemale() {
		return g3ESCCompleteFemale;
	}

	public void setG3ESCCompleteFemale(int completeFemale) {
		g3ESCCompleteFemale = completeFemale;
	}

	public int getG3EVSCompleteFemale() {
		return g3EVSCompleteFemale;
	}

	public void setG3EVSCompleteFemale(int completeFemale) {
		g3EVSCompleteFemale = completeFemale;
	}

	public int getG3ESCTransferOutFemale() {
		return g3ESCTransferOutFemale;
	}

	public void setG3ESCTransferOutFemale(int transferOutFemale) {
		g3ESCTransferOutFemale = transferOutFemale;
	}

	public int getG3EVSTransferOutFemale() {
		return g3EVSTransferOutFemale;
	}

	public void setG3EVSTransferOutFemale(int transferOutFemale) {
		g3EVSTransferOutFemale = transferOutFemale;
	}

	public int getG3ESCDropFemale() {
		return g3ESCDropFemale;
	}

	public void setG3ESCDropFemale(int dropFemale) {
		g3ESCDropFemale = dropFemale;
	}

	public int getG3EVSDropFemale() {
		return g3EVSDropFemale;
	}

	public void setG3EVSDropFemale(int dropFemale) {
		g3EVSDropFemale = dropFemale;
	}

	public int getG3ESCFailedFemale() {
		return g3ESCFailedFemale;
	}

	public void setG3ESCFailedFemale(int failedFemale) {
		g3ESCFailedFemale = failedFemale;
	}

	public int getG3EVSFailedFemale() {
		return g3EVSFailedFemale;
	}

	public void setG3EVSFailedFemale(int failedFemale) {
		g3EVSFailedFemale = failedFemale;
	}

	public int getG4Male() {
		return g4Male;
	}

	public void setG4Male(int male) {
		g4Male = male;
	}

	public int getG44Male() {
		return g44Male;
	}

	public void setG44Male(int male) {
		g44Male = male;
	}

	public int getG45Male() {
		return g45Male;
	}

	public void setG45Male(int male) {
		g45Male = male;
	}

	public int getG46Male() {
		return g46Male;
	}

	public void setG46Male(int male) {
		g46Male = male;
	}

	public int getG47Male() {
		return g47Male;
	}

	public void setG47Male(int male) {
		g47Male = male;
	}

	public int getG48Male() {
		return g48Male;
	}

	public void setG48Male(int male) {
		g48Male = male;
	}

	public int getG49Male() {
		return g49Male;
	}

	public void setG49Male(int male) {
		g49Male = male;
	}

	public int getG410Male() {
		return g410Male;
	}

	public void setG410Male(int male) {
		g410Male = male;
	}

	public int getG411Male() {
		return g411Male;
	}

	public void setG411Male(int male) {
		g411Male = male;
	}

	public int getG412Male() {
		return g412Male;
	}

	public void setG412Male(int male) {
		g412Male = male;
	}

	public int getG413Male() {
		return g413Male;
	}

	public void setG413Male(int male) {
		g413Male = male;
	}

	public int getG414Male() {
		return g414Male;
	}

	public void setG414Male(int male) {
		g414Male = male;
	}

	public int getG415Male() {
		return g415Male;
	}

	public void setG415Male(int male) {
		g415Male = male;
	}

	public int getG416Male() {
		return g416Male;
	}

	public void setG416Male(int male) {
		g416Male = male;
	}

	public int getG417Male() {
		return g417Male;
	}

	public void setG417Male(int male) {
		g417Male = male;
	}

	public int getG418Male() {
		return g418Male;
	}

	public void setG418Male(int male) {
		g418Male = male;
	}

	public int getG419Male() {
		return g419Male;
	}

	public void setG419Male(int male) {
		g419Male = male;
	}

	public int getG420Male() {
		return g420Male;
	}

	public void setG420Male(int male) {
		g420Male = male;
	}

	public int getG4MonoMale() {
		return g4MonoMale;
	}

	public void setG4MonoMale(int monoMale) {
		g4MonoMale = monoMale;
	}

	public int getG4MultiMale() {
		return g4MultiMale;
	}

	public void setG4MultiMale(int multiMale) {
		g4MultiMale = multiMale;
	}

	public int getG4SpedMale() {
		return g4SpedMale;
	}

	public void setG4SpedMale(int spedMale) {
		g4SpedMale = spedMale;
	}

	public int getG4RepeatMale() {
		return g4RepeatMale;
	}

	public void setG4RepeatMale(int repeatMale) {
		g4RepeatMale = repeatMale;
	}

	public int getG4TransferMale() {
		return g4TransferMale;
	}

	public void setG4TransferMale(int transferMale) {
		g4TransferMale = transferMale;
	}

	public int getG4BackMale() {
		return g4BackMale;
	}

	public void setG4BackMale(int backMale) {
		g4BackMale = backMale;
	}

	public int getG4MuslimMale() {
		return g4MuslimMale;
	}

	public void setG4MuslimMale(int muslimMale) {
		g4MuslimMale = muslimMale;
	}

	public int getG4IndigenousMale() {
		return g4IndigenousMale;
	}

	public void setG4IndigenousMale(int indigenousMale) {
		g4IndigenousMale = indigenousMale;
	}

	public int getG4ClassesMale() {
		return g4ClassesMale;
	}

	public void setG4ClassesMale(int classesMale) {
		g4ClassesMale = classesMale;
	}

	public int getG4CompleteMale() {
		return g4CompleteMale;
	}

	public void setG4CompleteMale(int completeMale) {
		g4CompleteMale = completeMale;
	}

	public int getG4DropMale() {
		return g4DropMale;
	}

	public void setG4DropMale(int dropMale) {
		g4DropMale = dropMale;
	}

	public int getG4TransferOutMale() {
		return g4TransferOutMale;
	}

	public void setG4TransferOutMale(int transferOutMale) {
		g4TransferOutMale = transferOutMale;
	}

	public int getG4FailedMale() {
		return g4FailedMale;
	}

	public void setG4FailedMale(int failedMale) {
		g4FailedMale = failedMale;
	}

	public int getG4ShiftMale() {
		return g4ShiftMale;
	}

	public void setG4ShiftMale(int shiftMale) {
		g4ShiftMale = shiftMale;
	}

	public int getG4ESCMale() {
		return g4ESCMale;
	}

	public void setG4ESCMale(int male) {
		g4ESCMale = male;
	}

	public int getG4EVSMale() {
		return g4EVSMale;
	}

	public void setG4EVSMale(int male) {
		g4EVSMale = male;
	}

	public int getG4ESCCompleteMale() {
		return g4ESCCompleteMale;
	}

	public void setG4ESCCompleteMale(int completeMale) {
		g4ESCCompleteMale = completeMale;
	}

	public int getG4EVSCompleteMale() {
		return g4EVSCompleteMale;
	}

	public void setG4EVSCompleteMale(int completeMale) {
		g4EVSCompleteMale = completeMale;
	}

	public int getG4ESCTransferOutMale() {
		return g4ESCTransferOutMale;
	}

	public void setG4ESCTransferOutMale(int transferOutMale) {
		g4ESCTransferOutMale = transferOutMale;
	}

	public int getG4EVSTransferOutMale() {
		return g4EVSTransferOutMale;
	}

	public void setG4EVSTransferOutMale(int transferOutMale) {
		g4EVSTransferOutMale = transferOutMale;
	}

	public int getG4ESCDropMale() {
		return g4ESCDropMale;
	}

	public void setG4ESCDropMale(int dropMale) {
		g4ESCDropMale = dropMale;
	}

	public int getG4EVSDropMale() {
		return g4EVSDropMale;
	}

	public void setG4EVSDropMale(int dropMale) {
		g4EVSDropMale = dropMale;
	}

	public int getG4ESCFailedMale() {
		return g4ESCFailedMale;
	}

	public void setG4ESCFailedMale(int failedMale) {
		g4ESCFailedMale = failedMale;
	}

	public int getG4EVSFailedMale() {
		return g4EVSFailedMale;
	}

	public void setG4EVSFailedMale(int failedMale) {
		g4EVSFailedMale = failedMale;
	}

	public int getG4Female() {
		return g4Female;
	}

	public void setG4Female(int female) {
		g4Female = female;
	}

	public int getG44Female() {
		return g44Female;
	}

	public void setG44Female(int female) {
		g44Female = female;
	}

	public int getG45Female() {
		return g45Female;
	}

	public void setG45Female(int female) {
		g45Female = female;
	}

	public int getG46Female() {
		return g46Female;
	}

	public void setG46Female(int female) {
		g46Female = female;
	}

	public int getG47Female() {
		return g47Female;
	}

	public void setG47Female(int female) {
		g47Female = female;
	}

	public int getG48Female() {
		return g48Female;
	}

	public void setG48Female(int female) {
		g48Female = female;
	}

	public int getG49Female() {
		return g49Female;
	}

	public void setG49Female(int female) {
		g49Female = female;
	}

	public int getG410Female() {
		return g410Female;
	}

	public void setG410Female(int female) {
		g410Female = female;
	}

	public int getG411Female() {
		return g411Female;
	}

	public void setG411Female(int female) {
		g411Female = female;
	}

	public int getG412Female() {
		return g412Female;
	}

	public void setG412Female(int female) {
		g412Female = female;
	}

	public int getG413Female() {
		return g413Female;
	}

	public void setG413Female(int female) {
		g413Female = female;
	}

	public int getG414Female() {
		return g414Female;
	}

	public void setG414Female(int female) {
		g414Female = female;
	}

	public int getG415Female() {
		return g415Female;
	}

	public void setG415Female(int female) {
		g415Female = female;
	}

	public int getG416Female() {
		return g416Female;
	}

	public void setG416Female(int female) {
		g416Female = female;
	}

	public int getG417Female() {
		return g417Female;
	}

	public void setG417Female(int female) {
		g417Female = female;
	}

	public int getG418Female() {
		return g418Female;
	}

	public void setG418Female(int female) {
		g418Female = female;
	}

	public int getG419Female() {
		return g419Female;
	}

	public void setG419Female(int female) {
		g419Female = female;
	}

	public int getG420Female() {
		return g420Female;
	}

	public void setG420Female(int female) {
		g420Female = female;
	}

	public int getG4MonoFemale() {
		return g4MonoFemale;
	}

	public void setG4MonoFemale(int monoFemale) {
		g4MonoFemale = monoFemale;
	}

	public int getG4MultiFemale() {
		return g4MultiFemale;
	}

	public void setG4MultiFemale(int multiFemale) {
		g4MultiFemale = multiFemale;
	}

	public int getG4SpedFemale() {
		return g4SpedFemale;
	}

	public void setG4SpedFemale(int spedFemale) {
		g4SpedFemale = spedFemale;
	}

	public int getG4RepeatFemale() {
		return g4RepeatFemale;
	}

	public void setG4RepeatFemale(int repeatFemale) {
		g4RepeatFemale = repeatFemale;
	}

	public int getG4TransferFemale() {
		return g4TransferFemale;
	}

	public void setG4TransferFemale(int transferFemale) {
		g4TransferFemale = transferFemale;
	}

	public int getG4BackFemale() {
		return g4BackFemale;
	}

	public void setG4BackFemale(int backFemale) {
		g4BackFemale = backFemale;
	}

	public int getG4MuslimFemale() {
		return g4MuslimFemale;
	}

	public void setG4MuslimFemale(int muslimFemale) {
		g4MuslimFemale = muslimFemale;
	}

	public int getG4IndigenousFemale() {
		return g4IndigenousFemale;
	}

	public void setG4IndigenousFemale(int indigenousFemale) {
		g4IndigenousFemale = indigenousFemale;
	}

	public int getG4ClassesFemale() {
		return g4ClassesFemale;
	}

	public void setG4ClassesFemale(int classesFemale) {
		g4ClassesFemale = classesFemale;
	}

	public int getG4CompleteFemale() {
		return g4CompleteFemale;
	}

	public void setG4CompleteFemale(int completeFemale) {
		g4CompleteFemale = completeFemale;
	}

	public int getG4DropFemale() {
		return g4DropFemale;
	}

	public void setG4DropFemale(int dropFemale) {
		g4DropFemale = dropFemale;
	}

	public int getG4TransferOutFemale() {
		return g4TransferOutFemale;
	}

	public void setG4TransferOutFemale(int transferOutFemale) {
		g4TransferOutFemale = transferOutFemale;
	}

	public int getG4FailedFemale() {
		return g4FailedFemale;
	}

	public void setG4FailedFemale(int failedFemale) {
		g4FailedFemale = failedFemale;
	}

	public int getG4ShiftFemale() {
		return g4ShiftFemale;
	}

	public void setG4ShiftFemale(int shiftFemale) {
		g4ShiftFemale = shiftFemale;
	}

	public int getG4ESCFemale() {
		return g4ESCFemale;
	}

	public void setG4ESCFemale(int female) {
		g4ESCFemale = female;
	}

	public int getG4EVSFemale() {
		return g4EVSFemale;
	}

	public void setG4EVSFemale(int female) {
		g4EVSFemale = female;
	}

	public int getG4ESCCompleteFemale() {
		return g4ESCCompleteFemale;
	}

	public void setG4ESCCompleteFemale(int completeFemale) {
		g4ESCCompleteFemale = completeFemale;
	}

	public int getG4EVSCompleteFemale() {
		return g4EVSCompleteFemale;
	}

	public void setG4EVSCompleteFemale(int completeFemale) {
		g4EVSCompleteFemale = completeFemale;
	}

	public int getG4ESCTransferOutFemale() {
		return g4ESCTransferOutFemale;
	}

	public void setG4ESCTransferOutFemale(int transferOutFemale) {
		g4ESCTransferOutFemale = transferOutFemale;
	}

	public int getG4EVSTransferOutFemale() {
		return g4EVSTransferOutFemale;
	}

	public void setG4EVSTransferOutFemale(int transferOutFemale) {
		g4EVSTransferOutFemale = transferOutFemale;
	}

	public int getG4ESCDropFemale() {
		return g4ESCDropFemale;
	}

	public void setG4ESCDropFemale(int dropFemale) {
		g4ESCDropFemale = dropFemale;
	}

	public int getG4EVSDropFemale() {
		return g4EVSDropFemale;
	}

	public void setG4EVSDropFemale(int dropFemale) {
		g4EVSDropFemale = dropFemale;
	}

	public int getG4ESCFailedFemale() {
		return g4ESCFailedFemale;
	}

	public void setG4ESCFailedFemale(int failedFemale) {
		g4ESCFailedFemale = failedFemale;
	}

	public int getG4EVSFailedFemale() {
		return g4EVSFailedFemale;
	}

	public void setG4EVSFailedFemale(int failedFemale) {
		g4EVSFailedFemale = failedFemale;
	}

	public int getG5Male() {
		return g5Male;
	}

	public void setG5Male(int male) {
		g5Male = male;
	}

	public int getG54Male() {
		return g54Male;
	}

	public void setG54Male(int male) {
		g54Male = male;
	}

	public int getG55Male() {
		return g55Male;
	}

	public void setG55Male(int male) {
		g55Male = male;
	}

	public int getG56Male() {
		return g56Male;
	}

	public void setG56Male(int male) {
		g56Male = male;
	}

	public int getG57Male() {
		return g57Male;
	}

	public void setG57Male(int male) {
		g57Male = male;
	}

	public int getG58Male() {
		return g58Male;
	}

	public void setG58Male(int male) {
		g58Male = male;
	}

	public int getG59Male() {
		return g59Male;
	}

	public void setG59Male(int male) {
		g59Male = male;
	}

	public int getG510Male() {
		return g510Male;
	}

	public void setG510Male(int male) {
		g510Male = male;
	}

	public int getG511Male() {
		return g511Male;
	}

	public void setG511Male(int male) {
		g511Male = male;
	}

	public int getG512Male() {
		return g512Male;
	}

	public void setG512Male(int male) {
		g512Male = male;
	}

	public int getG513Male() {
		return g513Male;
	}

	public void setG513Male(int male) {
		g513Male = male;
	}

	public int getG514Male() {
		return g514Male;
	}

	public void setG514Male(int male) {
		g514Male = male;
	}

	public int getG515Male() {
		return g515Male;
	}

	public void setG515Male(int male) {
		g515Male = male;
	}

	public int getG516Male() {
		return g516Male;
	}

	public void setG516Male(int male) {
		g516Male = male;
	}

	public int getG517Male() {
		return g517Male;
	}

	public void setG517Male(int male) {
		g517Male = male;
	}

	public int getG518Male() {
		return g518Male;
	}

	public void setG518Male(int male) {
		g518Male = male;
	}

	public int getG519Male() {
		return g519Male;
	}

	public void setG519Male(int male) {
		g519Male = male;
	}

	public int getG520Male() {
		return g520Male;
	}

	public void setG520Male(int male) {
		g520Male = male;
	}

	public int getG5MonoMale() {
		return g5MonoMale;
	}

	public void setG5MonoMale(int monoMale) {
		g5MonoMale = monoMale;
	}

	public int getG5MultiMale() {
		return g5MultiMale;
	}

	public void setG5MultiMale(int multiMale) {
		g5MultiMale = multiMale;
	}

	public int getG5SpedMale() {
		return g5SpedMale;
	}

	public void setG5SpedMale(int spedMale) {
		g5SpedMale = spedMale;
	}

	public int getG5RepeatMale() {
		return g5RepeatMale;
	}

	public void setG5RepeatMale(int repeatMale) {
		g5RepeatMale = repeatMale;
	}

	public int getG5TransferMale() {
		return g5TransferMale;
	}

	public void setG5TransferMale(int transferMale) {
		g5TransferMale = transferMale;
	}

	public int getG5BackMale() {
		return g5BackMale;
	}

	public void setG5BackMale(int backMale) {
		g5BackMale = backMale;
	}

	public int getG5MuslimMale() {
		return g5MuslimMale;
	}

	public void setG5MuslimMale(int muslimMale) {
		g5MuslimMale = muslimMale;
	}

	public int getG5IndigenousMale() {
		return g5IndigenousMale;
	}

	public void setG5IndigenousMale(int indigenousMale) {
		g5IndigenousMale = indigenousMale;
	}

	public int getG5ClassesMale() {
		return g5ClassesMale;
	}

	public void setG5ClassesMale(int classesMale) {
		g5ClassesMale = classesMale;
	}

	public int getG5CompleteMale() {
		return g5CompleteMale;
	}

	public void setG5CompleteMale(int completeMale) {
		g5CompleteMale = completeMale;
	}

	public int getG5DropMale() {
		return g5DropMale;
	}

	public void setG5DropMale(int dropMale) {
		g5DropMale = dropMale;
	}

	public int getG5TransferOutMale() {
		return g5TransferOutMale;
	}

	public void setG5TransferOutMale(int transferOutMale) {
		g5TransferOutMale = transferOutMale;
	}

	public int getG5FailedMale() {
		return g5FailedMale;
	}

	public void setG5FailedMale(int failedMale) {
		g5FailedMale = failedMale;
	}

	public int getG5ShiftMale() {
		return g5ShiftMale;
	}

	public void setG5ShiftMale(int shiftMale) {
		g5ShiftMale = shiftMale;
	}

	public int getG5ESCMale() {
		return g5ESCMale;
	}

	public void setG5ESCMale(int male) {
		g5ESCMale = male;
	}

	public int getG5EVSMale() {
		return g5EVSMale;
	}

	public void setG5EVSMale(int male) {
		g5EVSMale = male;
	}

	public int getG5ESCCompleteMale() {
		return g5ESCCompleteMale;
	}

	public void setG5ESCCompleteMale(int completeMale) {
		g5ESCCompleteMale = completeMale;
	}

	public int getG5EVSCompleteMale() {
		return g5EVSCompleteMale;
	}

	public void setG5EVSCompleteMale(int completeMale) {
		g5EVSCompleteMale = completeMale;
	}

	public int getG5ESCTransferOutMale() {
		return g5ESCTransferOutMale;
	}

	public void setG5ESCTransferOutMale(int transferOutMale) {
		g5ESCTransferOutMale = transferOutMale;
	}

	public int getG5EVSTransferOutMale() {
		return g5EVSTransferOutMale;
	}

	public void setG5EVSTransferOutMale(int transferOutMale) {
		g5EVSTransferOutMale = transferOutMale;
	}

	public int getG5ESCDropMale() {
		return g5ESCDropMale;
	}

	public void setG5ESCDropMale(int dropMale) {
		g5ESCDropMale = dropMale;
	}

	public int getG5EVSDropMale() {
		return g5EVSDropMale;
	}

	public void setG5EVSDropMale(int dropMale) {
		g5EVSDropMale = dropMale;
	}

	public int getG5ESCFailedMale() {
		return g5ESCFailedMale;
	}

	public void setG5ESCFailedMale(int failedMale) {
		g5ESCFailedMale = failedMale;
	}

	public int getG5EVSFailedMale() {
		return g5EVSFailedMale;
	}

	public void setG5EVSFailedMale(int failedMale) {
		g5EVSFailedMale = failedMale;
	}

	public int getG5Female() {
		return g5Female;
	}

	public void setG5Female(int female) {
		g5Female = female;
	}

	public int getG54Female() {
		return g54Female;
	}

	public void setG54Female(int female) {
		g54Female = female;
	}

	public int getG55Female() {
		return g55Female;
	}

	public void setG55Female(int female) {
		g55Female = female;
	}

	public int getG56Female() {
		return g56Female;
	}

	public void setG56Female(int female) {
		g56Female = female;
	}

	public int getG57Female() {
		return g57Female;
	}

	public void setG57Female(int female) {
		g57Female = female;
	}

	public int getG58Female() {
		return g58Female;
	}

	public void setG58Female(int female) {
		g58Female = female;
	}

	public int getG59Female() {
		return g59Female;
	}

	public void setG59Female(int female) {
		g59Female = female;
	}

	public int getG510Female() {
		return g510Female;
	}

	public void setG510Female(int female) {
		g510Female = female;
	}

	public int getG511Female() {
		return g511Female;
	}

	public void setG511Female(int female) {
		g511Female = female;
	}

	public int getG512Female() {
		return g512Female;
	}

	public void setG512Female(int female) {
		g512Female = female;
	}

	public int getG513Female() {
		return g513Female;
	}

	public void setG513Female(int female) {
		g513Female = female;
	}

	public int getG514Female() {
		return g514Female;
	}

	public void setG514Female(int female) {
		g514Female = female;
	}

	public int getG515Female() {
		return g515Female;
	}

	public void setG515Female(int female) {
		g515Female = female;
	}

	public int getG516Female() {
		return g516Female;
	}

	public void setG516Female(int female) {
		g516Female = female;
	}

	public int getG517Female() {
		return g517Female;
	}

	public void setG517Female(int female) {
		g517Female = female;
	}

	public int getG518Female() {
		return g518Female;
	}

	public void setG518Female(int female) {
		g518Female = female;
	}

	public int getG519Female() {
		return g519Female;
	}

	public void setG519Female(int female) {
		g519Female = female;
	}

	public int getG520Female() {
		return g520Female;
	}

	public void setG520Female(int female) {
		g520Female = female;
	}

	public int getG5MonoFemale() {
		return g5MonoFemale;
	}

	public void setG5MonoFemale(int monoFemale) {
		g5MonoFemale = monoFemale;
	}

	public int getG5MultiFemale() {
		return g5MultiFemale;
	}

	public void setG5MultiFemale(int multiFemale) {
		g5MultiFemale = multiFemale;
	}

	public int getG5SpedFemale() {
		return g5SpedFemale;
	}

	public void setG5SpedFemale(int spedFemale) {
		g5SpedFemale = spedFemale;
	}

	public int getG5RepeatFemale() {
		return g5RepeatFemale;
	}

	public void setG5RepeatFemale(int repeatFemale) {
		g5RepeatFemale = repeatFemale;
	}

	public int getG5TransferFemale() {
		return g5TransferFemale;
	}

	public void setG5TransferFemale(int transferFemale) {
		g5TransferFemale = transferFemale;
	}

	public int getG5BackFemale() {
		return g5BackFemale;
	}

	public void setG5BackFemale(int backFemale) {
		g5BackFemale = backFemale;
	}

	public int getG5MuslimFemale() {
		return g5MuslimFemale;
	}

	public void setG5MuslimFemale(int muslimFemale) {
		g5MuslimFemale = muslimFemale;
	}

	public int getG5IndigenousFemale() {
		return g5IndigenousFemale;
	}

	public void setG5IndigenousFemale(int indigenousFemale) {
		g5IndigenousFemale = indigenousFemale;
	}

	public int getG5ClassesFemale() {
		return g5ClassesFemale;
	}

	public void setG5ClassesFemale(int classesFemale) {
		g5ClassesFemale = classesFemale;
	}

	public int getG5CompleteFemale() {
		return g5CompleteFemale;
	}

	public void setG5CompleteFemale(int completeFemale) {
		g5CompleteFemale = completeFemale;
	}

	public int getG5DropFemale() {
		return g5DropFemale;
	}

	public void setG5DropFemale(int dropFemale) {
		g5DropFemale = dropFemale;
	}

	public int getG5TransferOutFemale() {
		return g5TransferOutFemale;
	}

	public void setG5TransferOutFemale(int transferOutFemale) {
		g5TransferOutFemale = transferOutFemale;
	}

	public int getG5FailedFemale() {
		return g5FailedFemale;
	}

	public void setG5FailedFemale(int failedFemale) {
		g5FailedFemale = failedFemale;
	}

	public int getG5ShiftFemale() {
		return g5ShiftFemale;
	}

	public void setG5ShiftFemale(int shiftFemale) {
		g5ShiftFemale = shiftFemale;
	}

	public int getG5ESCFemale() {
		return g5ESCFemale;
	}

	public void setG5ESCFemale(int female) {
		g5ESCFemale = female;
	}

	public int getG5EVSFemale() {
		return g5EVSFemale;
	}

	public void setG5EVSFemale(int female) {
		g5EVSFemale = female;
	}

	public int getG5ESCCompleteFemale() {
		return g5ESCCompleteFemale;
	}

	public void setG5ESCCompleteFemale(int completeFemale) {
		g5ESCCompleteFemale = completeFemale;
	}

	public int getG5EVSCompleteFemale() {
		return g5EVSCompleteFemale;
	}

	public void setG5EVSCompleteFemale(int completeFemale) {
		g5EVSCompleteFemale = completeFemale;
	}

	public int getG5ESCTransferOutFemale() {
		return g5ESCTransferOutFemale;
	}

	public void setG5ESCTransferOutFemale(int transferOutFemale) {
		g5ESCTransferOutFemale = transferOutFemale;
	}

	public int getG5EVSTransferOutFemale() {
		return g5EVSTransferOutFemale;
	}

	public void setG5EVSTransferOutFemale(int transferOutFemale) {
		g5EVSTransferOutFemale = transferOutFemale;
	}

	public int getG5ESCDropFemale() {
		return g5ESCDropFemale;
	}

	public void setG5ESCDropFemale(int dropFemale) {
		g5ESCDropFemale = dropFemale;
	}

	public int getG5EVSDropFemale() {
		return g5EVSDropFemale;
	}

	public void setG5EVSDropFemale(int dropFemale) {
		g5EVSDropFemale = dropFemale;
	}

	public int getG5ESCFailedFemale() {
		return g5ESCFailedFemale;
	}

	public void setG5ESCFailedFemale(int failedFemale) {
		g5ESCFailedFemale = failedFemale;
	}

	public int getG5EVSFailedFemale() {
		return g5EVSFailedFemale;
	}

	public void setG5EVSFailedFemale(int failedFemale) {
		g5EVSFailedFemale = failedFemale;
	}

	public int getG6Male() {
		return g6Male;
	}

	public void setG6Male(int male) {
		g6Male = male;
	}

	public int getG64Male() {
		return g64Male;
	}

	public void setG64Male(int male) {
		g64Male = male;
	}

	public int getG65Male() {
		return g65Male;
	}

	public void setG65Male(int male) {
		g65Male = male;
	}

	public int getG66Male() {
		return g66Male;
	}

	public void setG66Male(int male) {
		g66Male = male;
	}

	public int getG67Male() {
		return g67Male;
	}

	public void setG67Male(int male) {
		g67Male = male;
	}

	public int getG68Male() {
		return g68Male;
	}

	public void setG68Male(int male) {
		g68Male = male;
	}

	public int getG69Male() {
		return g69Male;
	}

	public void setG69Male(int male) {
		g69Male = male;
	}

	public int getG610Male() {
		return g610Male;
	}

	public void setG610Male(int male) {
		g610Male = male;
	}

	public int getG611Male() {
		return g611Male;
	}

	public void setG611Male(int male) {
		g611Male = male;
	}

	public int getG612Male() {
		return g612Male;
	}

	public void setG612Male(int male) {
		g612Male = male;
	}

	public int getG613Male() {
		return g613Male;
	}

	public void setG613Male(int male) {
		g613Male = male;
	}

	public int getG614Male() {
		return g614Male;
	}

	public void setG614Male(int male) {
		g614Male = male;
	}

	public int getG615Male() {
		return g615Male;
	}

	public void setG615Male(int male) {
		g615Male = male;
	}

	public int getG616Male() {
		return g616Male;
	}

	public void setG616Male(int male) {
		g616Male = male;
	}

	public int getG617Male() {
		return g617Male;
	}

	public void setG617Male(int male) {
		g617Male = male;
	}

	public int getG618Male() {
		return g618Male;
	}

	public void setG618Male(int male) {
		g618Male = male;
	}

	public int getG619Male() {
		return g619Male;
	}

	public void setG619Male(int male) {
		g619Male = male;
	}

	public int getG620Male() {
		return g620Male;
	}

	public void setG620Male(int male) {
		g620Male = male;
	}

	public int getG6MonoMale() {
		return g6MonoMale;
	}

	public void setG6MonoMale(int monoMale) {
		g6MonoMale = monoMale;
	}

	public int getG6MultiMale() {
		return g6MultiMale;
	}

	public void setG6MultiMale(int multiMale) {
		g6MultiMale = multiMale;
	}

	public int getG6SpedMale() {
		return g6SpedMale;
	}

	public void setG6SpedMale(int spedMale) {
		g6SpedMale = spedMale;
	}

	public int getG6RepeatMale() {
		return g6RepeatMale;
	}

	public void setG6RepeatMale(int repeatMale) {
		g6RepeatMale = repeatMale;
	}

	public int getG6TransferMale() {
		return g6TransferMale;
	}

	public void setG6TransferMale(int transferMale) {
		g6TransferMale = transferMale;
	}

	public int getG6BackMale() {
		return g6BackMale;
	}

	public void setG6BackMale(int backMale) {
		g6BackMale = backMale;
	}

	public int getG6MuslimMale() {
		return g6MuslimMale;
	}

	public void setG6MuslimMale(int muslimMale) {
		g6MuslimMale = muslimMale;
	}

	public int getG6IndigenousMale() {
		return g6IndigenousMale;
	}

	public void setG6IndigenousMale(int indigenousMale) {
		g6IndigenousMale = indigenousMale;
	}

	public int getG6ClassesMale() {
		return g6ClassesMale;
	}

	public void setG6ClassesMale(int classesMale) {
		g6ClassesMale = classesMale;
	}

	public int getG6CompleteMale() {
		return g6CompleteMale;
	}

	public void setG6CompleteMale(int completeMale) {
		g6CompleteMale = completeMale;
	}

	public int getG6DropMale() {
		return g6DropMale;
	}

	public void setG6DropMale(int dropMale) {
		g6DropMale = dropMale;
	}

	public int getG6TransferOutMale() {
		return g6TransferOutMale;
	}

	public void setG6TransferOutMale(int transferOutMale) {
		g6TransferOutMale = transferOutMale;
	}

	public int getG6FailedMale() {
		return g6FailedMale;
	}

	public void setG6FailedMale(int failedMale) {
		g6FailedMale = failedMale;
	}

	public int getG6ShiftMale() {
		return g6ShiftMale;
	}

	public void setG6ShiftMale(int shiftMale) {
		g6ShiftMale = shiftMale;
	}

	public int getG6ESCMale() {
		return g6ESCMale;
	}

	public void setG6ESCMale(int male) {
		g6ESCMale = male;
	}

	public int getG6EVSMale() {
		return g6EVSMale;
	}

	public void setG6EVSMale(int male) {
		g6EVSMale = male;
	}

	public int getG6ESCCompleteMale() {
		return g6ESCCompleteMale;
	}

	public void setG6ESCCompleteMale(int completeMale) {
		g6ESCCompleteMale = completeMale;
	}

	public int getG6EVSCompleteMale() {
		return g6EVSCompleteMale;
	}

	public void setG6EVSCompleteMale(int completeMale) {
		g6EVSCompleteMale = completeMale;
	}

	public int getG6ESCTransferOutMale() {
		return g6ESCTransferOutMale;
	}

	public void setG6ESCTransferOutMale(int transferOutMale) {
		g6ESCTransferOutMale = transferOutMale;
	}

	public int getG6EVSTransferOutMale() {
		return g6EVSTransferOutMale;
	}

	public void setG6EVSTransferOutMale(int transferOutMale) {
		g6EVSTransferOutMale = transferOutMale;
	}

	public int getG6ESCDropMale() {
		return g6ESCDropMale;
	}

	public void setG6ESCDropMale(int dropMale) {
		g6ESCDropMale = dropMale;
	}

	public int getG6EVSDropMale() {
		return g6EVSDropMale;
	}

	public void setG6EVSDropMale(int dropMale) {
		g6EVSDropMale = dropMale;
	}

	public int getG6ESCFailedMale() {
		return g6ESCFailedMale;
	}

	public void setG6ESCFailedMale(int failedMale) {
		g6ESCFailedMale = failedMale;
	}

	public int getG6EVSFailedMale() {
		return g6EVSFailedMale;
	}

	public void setG6EVSFailedMale(int failedMale) {
		g6EVSFailedMale = failedMale;
	}

	public int getG6Female() {
		return g6Female;
	}

	public void setG6Female(int female) {
		g6Female = female;
	}

	public int getG64Female() {
		return g64Female;
	}

	public void setG64Female(int female) {
		g64Female = female;
	}

	public int getG65Female() {
		return g65Female;
	}

	public void setG65Female(int female) {
		g65Female = female;
	}

	public int getG66Female() {
		return g66Female;
	}

	public void setG66Female(int female) {
		g66Female = female;
	}

	public int getG67Female() {
		return g67Female;
	}

	public void setG67Female(int female) {
		g67Female = female;
	}

	public int getG68Female() {
		return g68Female;
	}

	public void setG68Female(int female) {
		g68Female = female;
	}

	public int getG69Female() {
		return g69Female;
	}

	public void setG69Female(int female) {
		g69Female = female;
	}

	public int getG610Female() {
		return g610Female;
	}

	public void setG610Female(int female) {
		g610Female = female;
	}

	public int getG611Female() {
		return g611Female;
	}

	public void setG611Female(int female) {
		g611Female = female;
	}

	public int getG612Female() {
		return g612Female;
	}

	public void setG612Female(int female) {
		g612Female = female;
	}

	public int getG613Female() {
		return g613Female;
	}

	public void setG613Female(int female) {
		g613Female = female;
	}

	public int getG614Female() {
		return g614Female;
	}

	public void setG614Female(int female) {
		g614Female = female;
	}

	public int getG615Female() {
		return g615Female;
	}

	public void setG615Female(int female) {
		g615Female = female;
	}

	public int getG616Female() {
		return g616Female;
	}

	public void setG616Female(int female) {
		g616Female = female;
	}

	public int getG617Female() {
		return g617Female;
	}

	public void setG617Female(int female) {
		g617Female = female;
	}

	public int getG618Female() {
		return g618Female;
	}

	public void setG618Female(int female) {
		g618Female = female;
	}

	public int getG619Female() {
		return g619Female;
	}

	public void setG619Female(int female) {
		g619Female = female;
	}

	public int getG620Female() {
		return g620Female;
	}

	public void setG620Female(int female) {
		g620Female = female;
	}

	public int getG6MonoFemale() {
		return g6MonoFemale;
	}

	public void setG6MonoFemale(int monoFemale) {
		g6MonoFemale = monoFemale;
	}

	public int getG6MultiFemale() {
		return g6MultiFemale;
	}

	public void setG6MultiFemale(int multiFemale) {
		g6MultiFemale = multiFemale;
	}

	public int getG6SpedFemale() {
		return g6SpedFemale;
	}

	public void setG6SpedFemale(int spedFemale) {
		g6SpedFemale = spedFemale;
	}

	public int getG6RepeatFemale() {
		return g6RepeatFemale;
	}

	public void setG6RepeatFemale(int repeatFemale) {
		g6RepeatFemale = repeatFemale;
	}

	public int getG6TransferFemale() {
		return g6TransferFemale;
	}

	public void setG6TransferFemale(int transferFemale) {
		g6TransferFemale = transferFemale;
	}

	public int getG6BackFemale() {
		return g6BackFemale;
	}

	public void setG6BackFemale(int backFemale) {
		g6BackFemale = backFemale;
	}

	public int getG6MuslimFemale() {
		return g6MuslimFemale;
	}

	public void setG6MuslimFemale(int muslimFemale) {
		g6MuslimFemale = muslimFemale;
	}

	public int getG6IndigenousFemale() {
		return g6IndigenousFemale;
	}

	public void setG6IndigenousFemale(int indigenousFemale) {
		g6IndigenousFemale = indigenousFemale;
	}

	public int getG6ClassesFemale() {
		return g6ClassesFemale;
	}

	public void setG6ClassesFemale(int classesFemale) {
		g6ClassesFemale = classesFemale;
	}

	public int getG6CompleteFemale() {
		return g6CompleteFemale;
	}

	public void setG6CompleteFemale(int completeFemale) {
		g6CompleteFemale = completeFemale;
	}

	public int getG6DropFemale() {
		return g6DropFemale;
	}

	public void setG6DropFemale(int dropFemale) {
		g6DropFemale = dropFemale;
	}

	public int getG6TransferOutFemale() {
		return g6TransferOutFemale;
	}

	public void setG6TransferOutFemale(int transferOutFemale) {
		g6TransferOutFemale = transferOutFemale;
	}

	public int getG6FailedFemale() {
		return g6FailedFemale;
	}

	public void setG6FailedFemale(int failedFemale) {
		g6FailedFemale = failedFemale;
	}

	public int getG6ShiftFemale() {
		return g6ShiftFemale;
	}

	public void setG6ShiftFemale(int shiftFemale) {
		g6ShiftFemale = shiftFemale;
	}

	public int getG6ESCFemale() {
		return g6ESCFemale;
	}

	public void setG6ESCFemale(int female) {
		g6ESCFemale = female;
	}

	public int getG6EVSFemale() {
		return g6EVSFemale;
	}

	public void setG6EVSFemale(int female) {
		g6EVSFemale = female;
	}

	public int getG6ESCCompleteFemale() {
		return g6ESCCompleteFemale;
	}

	public void setG6ESCCompleteFemale(int completeFemale) {
		g6ESCCompleteFemale = completeFemale;
	}

	public int getG6EVSCompleteFemale() {
		return g6EVSCompleteFemale;
	}

	public void setG6EVSCompleteFemale(int completeFemale) {
		g6EVSCompleteFemale = completeFemale;
	}

	public int getG6ESCTransferOutFemale() {
		return g6ESCTransferOutFemale;
	}

	public void setG6ESCTransferOutFemale(int transferOutFemale) {
		g6ESCTransferOutFemale = transferOutFemale;
	}

	public int getG6EVSTransferOutFemale() {
		return g6EVSTransferOutFemale;
	}

	public void setG6EVSTransferOutFemale(int transferOutFemale) {
		g6EVSTransferOutFemale = transferOutFemale;
	}

	public int getG6ESCDropFemale() {
		return g6ESCDropFemale;
	}

	public void setG6ESCDropFemale(int dropFemale) {
		g6ESCDropFemale = dropFemale;
	}

	public int getG6EVSDropFemale() {
		return g6EVSDropFemale;
	}

	public void setG6EVSDropFemale(int dropFemale) {
		g6EVSDropFemale = dropFemale;
	}

	public int getG6ESCFailedFemale() {
		return g6ESCFailedFemale;
	}

	public void setG6ESCFailedFemale(int failedFemale) {
		g6ESCFailedFemale = failedFemale;
	}

	public int getG6EVSFailedFemale() {
		return g6EVSFailedFemale;
	}

	public void setG6EVSFailedFemale(int failedFemale) {
		g6EVSFailedFemale = failedFemale;
	}

	public int getH1Male() {
		return h1Male;
	}

	public void setH1Male(int male) {
		h1Male = male;
	}

	public int getH14Male() {
		return h14Male;
	}

	public void setH14Male(int male) {
		h14Male = male;
	}

	public int getH15Male() {
		return h15Male;
	}

	public void setH15Male(int male) {
		h15Male = male;
	}

	public int getH16Male() {
		return h16Male;
	}

	public void setH16Male(int male) {
		h16Male = male;
	}

	public int getH17Male() {
		return h17Male;
	}

	public void setH17Male(int male) {
		h17Male = male;
	}

	public int getH18Male() {
		return h18Male;
	}

	public void setH18Male(int male) {
		h18Male = male;
	}

	public int getH19Male() {
		return h19Male;
	}

	public void setH19Male(int male) {
		h19Male = male;
	}

	public int getH110Male() {
		return h110Male;
	}

	public void setH110Male(int male) {
		h110Male = male;
	}

	public int getH111Male() {
		return h111Male;
	}

	public void setH111Male(int male) {
		h111Male = male;
	}

	public int getH112Male() {
		return h112Male;
	}

	public void setH112Male(int male) {
		h112Male = male;
	}

	public int getH113Male() {
		return h113Male;
	}

	public void setH113Male(int male) {
		h113Male = male;
	}

	public int getH114Male() {
		return h114Male;
	}

	public void setH114Male(int male) {
		h114Male = male;
	}

	public int getH115Male() {
		return h115Male;
	}

	public void setH115Male(int male) {
		h115Male = male;
	}

	public int getH116Male() {
		return h116Male;
	}

	public void setH116Male(int male) {
		h116Male = male;
	}

	public int getH117Male() {
		return h117Male;
	}

	public void setH117Male(int male) {
		h117Male = male;
	}

	public int getH118Male() {
		return h118Male;
	}

	public void setH118Male(int male) {
		h118Male = male;
	}

	public int getH119Male() {
		return h119Male;
	}

	public void setH119Male(int male) {
		h119Male = male;
	}

	public int getH120Male() {
		return h120Male;
	}

	public void setH120Male(int male) {
		h120Male = male;
	}

	public int getH1MonoMale() {
		return h1MonoMale;
	}

	public void setH1MonoMale(int monoMale) {
		h1MonoMale = monoMale;
	}

	public int getH1MultiMale() {
		return h1MultiMale;
	}

	public void setH1MultiMale(int multiMale) {
		h1MultiMale = multiMale;
	}

	public int getH1SpedMale() {
		return h1SpedMale;
	}

	public void setH1SpedMale(int spedMale) {
		h1SpedMale = spedMale;
	}

	public int getH1RepeatMale() {
		return h1RepeatMale;
	}

	public void setH1RepeatMale(int repeatMale) {
		h1RepeatMale = repeatMale;
	}

	public int getH1TransferMale() {
		return h1TransferMale;
	}

	public void setH1TransferMale(int transferMale) {
		h1TransferMale = transferMale;
	}

	public int getH1BackMale() {
		return h1BackMale;
	}

	public void setH1BackMale(int backMale) {
		h1BackMale = backMale;
	}

	public int getH1MuslimMale() {
		return h1MuslimMale;
	}

	public void setH1MuslimMale(int muslimMale) {
		h1MuslimMale = muslimMale;
	}

	public int getH1IndigenousMale() {
		return h1IndigenousMale;
	}

	public void setH1IndigenousMale(int indigenousMale) {
		h1IndigenousMale = indigenousMale;
	}

	public int getH1ClassesMale() {
		return h1ClassesMale;
	}

	public void setH1ClassesMale(int classesMale) {
		h1ClassesMale = classesMale;
	}

	public int getH1CompleteMale() {
		return h1CompleteMale;
	}

	public void setH1CompleteMale(int completeMale) {
		h1CompleteMale = completeMale;
	}

	public int getH1DropMale() {
		return h1DropMale;
	}

	public void setH1DropMale(int dropMale) {
		h1DropMale = dropMale;
	}

	public int getH1TransferOutMale() {
		return h1TransferOutMale;
	}

	public void setH1TransferOutMale(int transferOutMale) {
		h1TransferOutMale = transferOutMale;
	}

	public int getH1FailedMale() {
		return h1FailedMale;
	}

	public void setH1FailedMale(int failedMale) {
		h1FailedMale = failedMale;
	}

	public int getH1ShiftMale() {
		return h1ShiftMale;
	}

	public void setH1ShiftMale(int shiftMale) {
		h1ShiftMale = shiftMale;
	}

	public int getH1ESCMale() {
		return h1ESCMale;
	}

	public void setH1ESCMale(int male) {
		h1ESCMale = male;
	}

	public int getH1EVSMale() {
		return h1EVSMale;
	}

	public void setH1EVSMale(int male) {
		h1EVSMale = male;
	}

	public int getH1ESCCompleteMale() {
		return h1ESCCompleteMale;
	}

	public void setH1ESCCompleteMale(int completeMale) {
		h1ESCCompleteMale = completeMale;
	}

	public int getH1EVSCompleteMale() {
		return h1EVSCompleteMale;
	}

	public void setH1EVSCompleteMale(int completeMale) {
		h1EVSCompleteMale = completeMale;
	}

	public int getH1ESCTransferOutMale() {
		return h1ESCTransferOutMale;
	}

	public void setH1ESCTransferOutMale(int transferOutMale) {
		h1ESCTransferOutMale = transferOutMale;
	}

	public int getH1EVSTransferOutMale() {
		return h1EVSTransferOutMale;
	}

	public void setH1EVSTransferOutMale(int transferOutMale) {
		h1EVSTransferOutMale = transferOutMale;
	}

	public int getH1ESCDropMale() {
		return h1ESCDropMale;
	}

	public void setH1ESCDropMale(int dropMale) {
		h1ESCDropMale = dropMale;
	}

	public int getH1EVSDropMale() {
		return h1EVSDropMale;
	}

	public void setH1EVSDropMale(int dropMale) {
		h1EVSDropMale = dropMale;
	}

	public int getH1ESCFailedMale() {
		return h1ESCFailedMale;
	}

	public void setH1ESCFailedMale(int failedMale) {
		h1ESCFailedMale = failedMale;
	}

	public int getH1EVSFailedMale() {
		return h1EVSFailedMale;
	}

	public void setH1EVSFailedMale(int failedMale) {
		h1EVSFailedMale = failedMale;
	}

	public int getH1Female() {
		return h1Female;
	}

	public void setH1Female(int female) {
		h1Female = female;
	}

	public int getH14Female() {
		return h14Female;
	}

	public void setH14Female(int female) {
		h14Female = female;
	}

	public int getH15Female() {
		return h15Female;
	}

	public void setH15Female(int female) {
		h15Female = female;
	}

	public int getH16Female() {
		return h16Female;
	}

	public void setH16Female(int female) {
		h16Female = female;
	}

	public int getH17Female() {
		return h17Female;
	}

	public void setH17Female(int female) {
		h17Female = female;
	}

	public int getH18Female() {
		return h18Female;
	}

	public void setH18Female(int female) {
		h18Female = female;
	}

	public int getH19Female() {
		return h19Female;
	}

	public void setH19Female(int female) {
		h19Female = female;
	}

	public int getH110Female() {
		return h110Female;
	}

	public void setH110Female(int female) {
		h110Female = female;
	}

	public int getH111Female() {
		return h111Female;
	}

	public void setH111Female(int female) {
		h111Female = female;
	}

	public int getH112Female() {
		return h112Female;
	}

	public void setH112Female(int female) {
		h112Female = female;
	}

	public int getH113Female() {
		return h113Female;
	}

	public void setH113Female(int female) {
		h113Female = female;
	}

	public int getH114Female() {
		return h114Female;
	}

	public void setH114Female(int female) {
		h114Female = female;
	}

	public int getH115Female() {
		return h115Female;
	}

	public void setH115Female(int female) {
		h115Female = female;
	}

	public int getH116Female() {
		return h116Female;
	}

	public void setH116Female(int female) {
		h116Female = female;
	}

	public int getH117Female() {
		return h117Female;
	}

	public void setH117Female(int female) {
		h117Female = female;
	}

	public int getH118Female() {
		return h118Female;
	}

	public void setH118Female(int female) {
		h118Female = female;
	}

	public int getH119Female() {
		return h119Female;
	}

	public void setH119Female(int female) {
		h119Female = female;
	}

	public int getH120Female() {
		return h120Female;
	}

	public void setH120Female(int female) {
		h120Female = female;
	}

	public int getH1MonoFemale() {
		return h1MonoFemale;
	}

	public void setH1MonoFemale(int monoFemale) {
		h1MonoFemale = monoFemale;
	}

	public int getH1MultiFemale() {
		return h1MultiFemale;
	}

	public void setH1MultiFemale(int multiFemale) {
		h1MultiFemale = multiFemale;
	}

	public int getH1SpedFemale() {
		return h1SpedFemale;
	}

	public void setH1SpedFemale(int spedFemale) {
		h1SpedFemale = spedFemale;
	}

	public int getH1RepeatFemale() {
		return h1RepeatFemale;
	}

	public void setH1RepeatFemale(int repeatFemale) {
		h1RepeatFemale = repeatFemale;
	}

	public int getH1TransferFemale() {
		return h1TransferFemale;
	}

	public void setH1TransferFemale(int transferFemale) {
		h1TransferFemale = transferFemale;
	}

	public int getH1BackFemale() {
		return h1BackFemale;
	}

	public void setH1BackFemale(int backFemale) {
		h1BackFemale = backFemale;
	}

	public int getH1MuslimFemale() {
		return h1MuslimFemale;
	}

	public void setH1MuslimFemale(int muslimFemale) {
		h1MuslimFemale = muslimFemale;
	}

	public int getH1IndigenousFemale() {
		return h1IndigenousFemale;
	}

	public void setH1IndigenousFemale(int indigenousFemale) {
		h1IndigenousFemale = indigenousFemale;
	}

	public int getH1ClassesFemale() {
		return h1ClassesFemale;
	}

	public void setH1ClassesFemale(int classesFemale) {
		h1ClassesFemale = classesFemale;
	}

	public int getH1CompleteFemale() {
		return h1CompleteFemale;
	}

	public void setH1CompleteFemale(int completeFemale) {
		h1CompleteFemale = completeFemale;
	}

	public int getH1DropFemale() {
		return h1DropFemale;
	}

	public void setH1DropFemale(int dropFemale) {
		h1DropFemale = dropFemale;
	}

	public int getH1TransferOutFemale() {
		return h1TransferOutFemale;
	}

	public void setH1TransferOutFemale(int transferOutFemale) {
		h1TransferOutFemale = transferOutFemale;
	}

	public int getH1FailedFemale() {
		return h1FailedFemale;
	}

	public void setH1FailedFemale(int failedFemale) {
		h1FailedFemale = failedFemale;
	}

	public int getH1ShiftFemale() {
		return h1ShiftFemale;
	}

	public void setH1ShiftFemale(int shiftFemale) {
		h1ShiftFemale = shiftFemale;
	}

	public int getH1ESCFemale() {
		return h1ESCFemale;
	}

	public void setH1ESCFemale(int female) {
		h1ESCFemale = female;
	}

	public int getH1EVSFemale() {
		return h1EVSFemale;
	}

	public void setH1EVSFemale(int female) {
		h1EVSFemale = female;
	}

	public int getH1ESCCompleteFemale() {
		return h1ESCCompleteFemale;
	}

	public void setH1ESCCompleteFemale(int completeFemale) {
		h1ESCCompleteFemale = completeFemale;
	}

	public int getH1EVSCompleteFemale() {
		return h1EVSCompleteFemale;
	}

	public void setH1EVSCompleteFemale(int completeFemale) {
		h1EVSCompleteFemale = completeFemale;
	}

	public int getH1ESCTransferOutFemale() {
		return h1ESCTransferOutFemale;
	}

	public void setH1ESCTransferOutFemale(int transferOutFemale) {
		h1ESCTransferOutFemale = transferOutFemale;
	}

	public int getH1EVSTransferOutFemale() {
		return h1EVSTransferOutFemale;
	}

	public void setH1EVSTransferOutFemale(int transferOutFemale) {
		h1EVSTransferOutFemale = transferOutFemale;
	}

	public int getH1ESCDropFemale() {
		return h1ESCDropFemale;
	}

	public void setH1ESCDropFemale(int dropFemale) {
		h1ESCDropFemale = dropFemale;
	}

	public int getH1EVSDropFemale() {
		return h1EVSDropFemale;
	}

	public void setH1EVSDropFemale(int dropFemale) {
		h1EVSDropFemale = dropFemale;
	}

	public int getH1ESCFailedFemale() {
		return h1ESCFailedFemale;
	}

	public void setH1ESCFailedFemale(int failedFemale) {
		h1ESCFailedFemale = failedFemale;
	}

	public int getH1EVSFailedFemale() {
		return h1EVSFailedFemale;
	}

	public void setH1EVSFailedFemale(int failedFemale) {
		h1EVSFailedFemale = failedFemale;
	}

	public int getH2Male() {
		return h2Male;
	}

	public void setH2Male(int male) {
		h2Male = male;
	}

	public int getH24Male() {
		return h24Male;
	}

	public void setH24Male(int male) {
		h24Male = male;
	}

	public int getH25Male() {
		return h25Male;
	}

	public void setH25Male(int male) {
		h25Male = male;
	}

	public int getH26Male() {
		return h26Male;
	}

	public void setH26Male(int male) {
		h26Male = male;
	}

	public int getH27Male() {
		return h27Male;
	}

	public void setH27Male(int male) {
		h27Male = male;
	}

	public int getH28Male() {
		return h28Male;
	}

	public void setH28Male(int male) {
		h28Male = male;
	}

	public int getH29Male() {
		return h29Male;
	}

	public void setH29Male(int male) {
		h29Male = male;
	}

	public int getH210Male() {
		return h210Male;
	}

	public void setH210Male(int male) {
		h210Male = male;
	}

	public int getH211Male() {
		return h211Male;
	}

	public void setH211Male(int male) {
		h211Male = male;
	}

	public int getH212Male() {
		return h212Male;
	}

	public void setH212Male(int male) {
		h212Male = male;
	}

	public int getH213Male() {
		return h213Male;
	}

	public void setH213Male(int male) {
		h213Male = male;
	}

	public int getH214Male() {
		return h214Male;
	}

	public void setH214Male(int male) {
		h214Male = male;
	}

	public int getH215Male() {
		return h215Male;
	}

	public void setH215Male(int male) {
		h215Male = male;
	}

	public int getH216Male() {
		return h216Male;
	}

	public void setH216Male(int male) {
		h216Male = male;
	}

	public int getH217Male() {
		return h217Male;
	}

	public void setH217Male(int male) {
		h217Male = male;
	}

	public int getH218Male() {
		return h218Male;
	}

	public void setH218Male(int male) {
		h218Male = male;
	}

	public int getH219Male() {
		return h219Male;
	}

	public void setH219Male(int male) {
		h219Male = male;
	}

	public int getH220Male() {
		return h220Male;
	}

	public void setH220Male(int male) {
		h220Male = male;
	}

	public int getH2MonoMale() {
		return h2MonoMale;
	}

	public void setH2MonoMale(int monoMale) {
		h2MonoMale = monoMale;
	}

	public int getH2MultiMale() {
		return h2MultiMale;
	}

	public void setH2MultiMale(int multiMale) {
		h2MultiMale = multiMale;
	}

	public int getH2SpedMale() {
		return h2SpedMale;
	}

	public void setH2SpedMale(int spedMale) {
		h2SpedMale = spedMale;
	}

	public int getH2RepeatMale() {
		return h2RepeatMale;
	}

	public void setH2RepeatMale(int repeatMale) {
		h2RepeatMale = repeatMale;
	}

	public int getH2TransferMale() {
		return h2TransferMale;
	}

	public void setH2TransferMale(int transferMale) {
		h2TransferMale = transferMale;
	}

	public int getH2BackMale() {
		return h2BackMale;
	}

	public void setH2BackMale(int backMale) {
		h2BackMale = backMale;
	}

	public int getH2MuslimMale() {
		return h2MuslimMale;
	}

	public void setH2MuslimMale(int muslimMale) {
		h2MuslimMale = muslimMale;
	}

	public int getH2IndigenousMale() {
		return h2IndigenousMale;
	}

	public void setH2IndigenousMale(int indigenousMale) {
		h2IndigenousMale = indigenousMale;
	}

	public int getH2ClassesMale() {
		return h2ClassesMale;
	}

	public void setH2ClassesMale(int classesMale) {
		h2ClassesMale = classesMale;
	}

	public int getH2CompleteMale() {
		return h2CompleteMale;
	}

	public void setH2CompleteMale(int completeMale) {
		h2CompleteMale = completeMale;
	}

	public int getH2DropMale() {
		return h2DropMale;
	}

	public void setH2DropMale(int dropMale) {
		h2DropMale = dropMale;
	}

	public int getH2TransferOutMale() {
		return h2TransferOutMale;
	}

	public void setH2TransferOutMale(int transferOutMale) {
		h2TransferOutMale = transferOutMale;
	}

	public int getH2FailedMale() {
		return h2FailedMale;
	}

	public void setH2FailedMale(int failedMale) {
		h2FailedMale = failedMale;
	}

	public int getH2ShiftMale() {
		return h2ShiftMale;
	}

	public void setH2ShiftMale(int shiftMale) {
		h2ShiftMale = shiftMale;
	}

	public int getH2ESCMale() {
		return h2ESCMale;
	}

	public void setH2ESCMale(int male) {
		h2ESCMale = male;
	}

	public int getH2EVSMale() {
		return h2EVSMale;
	}

	public void setH2EVSMale(int male) {
		h2EVSMale = male;
	}

	public int getH2ESCCompleteMale() {
		return h2ESCCompleteMale;
	}

	public void setH2ESCCompleteMale(int completeMale) {
		h2ESCCompleteMale = completeMale;
	}

	public int getH2EVSCompleteMale() {
		return h2EVSCompleteMale;
	}

	public void setH2EVSCompleteMale(int completeMale) {
		h2EVSCompleteMale = completeMale;
	}

	public int getH2ESCTransferOutMale() {
		return h2ESCTransferOutMale;
	}

	public void setH2ESCTransferOutMale(int transferOutMale) {
		h2ESCTransferOutMale = transferOutMale;
	}

	public int getH2EVSTransferOutMale() {
		return h2EVSTransferOutMale;
	}

	public void setH2EVSTransferOutMale(int transferOutMale) {
		h2EVSTransferOutMale = transferOutMale;
	}

	public int getH2ESCDropMale() {
		return h2ESCDropMale;
	}

	public void setH2ESCDropMale(int dropMale) {
		h2ESCDropMale = dropMale;
	}

	public int getH2EVSDropMale() {
		return h2EVSDropMale;
	}

	public void setH2EVSDropMale(int dropMale) {
		h2EVSDropMale = dropMale;
	}

	public int getH2ESCFailedMale() {
		return h2ESCFailedMale;
	}

	public void setH2ESCFailedMale(int failedMale) {
		h2ESCFailedMale = failedMale;
	}

	public int getH2EVSFailedMale() {
		return h2EVSFailedMale;
	}

	public void setH2EVSFailedMale(int failedMale) {
		h2EVSFailedMale = failedMale;
	}

	public int getH2Female() {
		return h2Female;
	}

	public void setH2Female(int female) {
		h2Female = female;
	}

	public int getH24Female() {
		return h24Female;
	}

	public void setH24Female(int female) {
		h24Female = female;
	}

	public int getH25Female() {
		return h25Female;
	}

	public void setH25Female(int female) {
		h25Female = female;
	}

	public int getH26Female() {
		return h26Female;
	}

	public void setH26Female(int female) {
		h26Female = female;
	}

	public int getH27Female() {
		return h27Female;
	}

	public void setH27Female(int female) {
		h27Female = female;
	}

	public int getH28Female() {
		return h28Female;
	}

	public void setH28Female(int female) {
		h28Female = female;
	}

	public int getH29Female() {
		return h29Female;
	}

	public void setH29Female(int female) {
		h29Female = female;
	}

	public int getH210Female() {
		return h210Female;
	}

	public void setH210Female(int female) {
		h210Female = female;
	}

	public int getH211Female() {
		return h211Female;
	}

	public void setH211Female(int female) {
		h211Female = female;
	}

	public int getH212Female() {
		return h212Female;
	}

	public void setH212Female(int female) {
		h212Female = female;
	}

	public int getH213Female() {
		return h213Female;
	}

	public void setH213Female(int female) {
		h213Female = female;
	}

	public int getH214Female() {
		return h214Female;
	}

	public void setH214Female(int female) {
		h214Female = female;
	}

	public int getH215Female() {
		return h215Female;
	}

	public void setH215Female(int female) {
		h215Female = female;
	}

	public int getH216Female() {
		return h216Female;
	}

	public void setH216Female(int female) {
		h216Female = female;
	}

	public int getH217Female() {
		return h217Female;
	}

	public void setH217Female(int female) {
		h217Female = female;
	}

	public int getH218Female() {
		return h218Female;
	}

	public void setH218Female(int female) {
		h218Female = female;
	}

	public int getH219Female() {
		return h219Female;
	}

	public void setH219Female(int female) {
		h219Female = female;
	}

	public int getH220Female() {
		return h220Female;
	}

	public void setH220Female(int female) {
		h220Female = female;
	}

	public int getH2MonoFemale() {
		return h2MonoFemale;
	}

	public void setH2MonoFemale(int monoFemale) {
		h2MonoFemale = monoFemale;
	}

	public int getH2MultiFemale() {
		return h2MultiFemale;
	}

	public void setH2MultiFemale(int multiFemale) {
		h2MultiFemale = multiFemale;
	}

	public int getH2SpedFemale() {
		return h2SpedFemale;
	}

	public void setH2SpedFemale(int spedFemale) {
		h2SpedFemale = spedFemale;
	}

	public int getH2RepeatFemale() {
		return h2RepeatFemale;
	}

	public void setH2RepeatFemale(int repeatFemale) {
		h2RepeatFemale = repeatFemale;
	}

	public int getH2TransferFemale() {
		return h2TransferFemale;
	}

	public void setH2TransferFemale(int transferFemale) {
		h2TransferFemale = transferFemale;
	}

	public int getH2BackFemale() {
		return h2BackFemale;
	}

	public void setH2BackFemale(int backFemale) {
		h2BackFemale = backFemale;
	}

	public int getH2MuslimFemale() {
		return h2MuslimFemale;
	}

	public void setH2MuslimFemale(int muslimFemale) {
		h2MuslimFemale = muslimFemale;
	}

	public int getH2IndigenousFemale() {
		return h2IndigenousFemale;
	}

	public void setH2IndigenousFemale(int indigenousFemale) {
		h2IndigenousFemale = indigenousFemale;
	}

	public int getH2ClassesFemale() {
		return h2ClassesFemale;
	}

	public void setH2ClassesFemale(int classesFemale) {
		h2ClassesFemale = classesFemale;
	}

	public int getH2CompleteFemale() {
		return h2CompleteFemale;
	}

	public void setH2CompleteFemale(int completeFemale) {
		h2CompleteFemale = completeFemale;
	}

	public int getH2DropFemale() {
		return h2DropFemale;
	}

	public void setH2DropFemale(int dropFemale) {
		h2DropFemale = dropFemale;
	}

	public int getH2TransferOutFemale() {
		return h2TransferOutFemale;
	}

	public void setH2TransferOutFemale(int transferOutFemale) {
		h2TransferOutFemale = transferOutFemale;
	}

	public int getH2FailedFemale() {
		return h2FailedFemale;
	}

	public void setH2FailedFemale(int failedFemale) {
		h2FailedFemale = failedFemale;
	}

	public int getH2ShiftFemale() {
		return h2ShiftFemale;
	}

	public void setH2ShiftFemale(int shiftFemale) {
		h2ShiftFemale = shiftFemale;
	}

	public int getH2ESCFemale() {
		return h2ESCFemale;
	}

	public void setH2ESCFemale(int female) {
		h2ESCFemale = female;
	}

	public int getH2EVSFemale() {
		return h2EVSFemale;
	}

	public void setH2EVSFemale(int female) {
		h2EVSFemale = female;
	}

	public int getH2ESCCompleteFemale() {
		return h2ESCCompleteFemale;
	}

	public void setH2ESCCompleteFemale(int completeFemale) {
		h2ESCCompleteFemale = completeFemale;
	}

	public int getH2EVSCompleteFemale() {
		return h2EVSCompleteFemale;
	}

	public void setH2EVSCompleteFemale(int completeFemale) {
		h2EVSCompleteFemale = completeFemale;
	}

	public int getH2ESCTransferOutFemale() {
		return h2ESCTransferOutFemale;
	}

	public void setH2ESCTransferOutFemale(int transferOutFemale) {
		h2ESCTransferOutFemale = transferOutFemale;
	}

	public int getH2EVSTransferOutFemale() {
		return h2EVSTransferOutFemale;
	}

	public void setH2EVSTransferOutFemale(int transferOutFemale) {
		h2EVSTransferOutFemale = transferOutFemale;
	}

	public int getH2ESCDropFemale() {
		return h2ESCDropFemale;
	}

	public void setH2ESCDropFemale(int dropFemale) {
		h2ESCDropFemale = dropFemale;
	}

	public int getH2EVSDropFemale() {
		return h2EVSDropFemale;
	}

	public void setH2EVSDropFemale(int dropFemale) {
		h2EVSDropFemale = dropFemale;
	}

	public int getH2ESCFailedFemale() {
		return h2ESCFailedFemale;
	}

	public void setH2ESCFailedFemale(int failedFemale) {
		h2ESCFailedFemale = failedFemale;
	}

	public int getH2EVSFailedFemale() {
		return h2EVSFailedFemale;
	}

	public void setH2EVSFailedFemale(int failedFemale) {
		h2EVSFailedFemale = failedFemale;
	}

	public int getH3Male() {
		return h3Male;
	}

	public void setH3Male(int male) {
		h3Male = male;
	}

	public int getH34Male() {
		return h34Male;
	}

	public void setH34Male(int male) {
		h34Male = male;
	}

	public int getH35Male() {
		return h35Male;
	}

	public void setH35Male(int male) {
		h35Male = male;
	}

	public int getH36Male() {
		return h36Male;
	}

	public void setH36Male(int male) {
		h36Male = male;
	}

	public int getH37Male() {
		return h37Male;
	}

	public void setH37Male(int male) {
		h37Male = male;
	}

	public int getH38Male() {
		return h38Male;
	}

	public void setH38Male(int male) {
		h38Male = male;
	}

	public int getH39Male() {
		return h39Male;
	}

	public void setH39Male(int male) {
		h39Male = male;
	}

	public int getH310Male() {
		return h310Male;
	}

	public void setH310Male(int male) {
		h310Male = male;
	}

	public int getH311Male() {
		return h311Male;
	}

	public void setH311Male(int male) {
		h311Male = male;
	}

	public int getH312Male() {
		return h312Male;
	}

	public void setH312Male(int male) {
		h312Male = male;
	}

	public int getH313Male() {
		return h313Male;
	}

	public void setH313Male(int male) {
		h313Male = male;
	}

	public int getH314Male() {
		return h314Male;
	}

	public void setH314Male(int male) {
		h314Male = male;
	}

	public int getH315Male() {
		return h315Male;
	}

	public void setH315Male(int male) {
		h315Male = male;
	}

	public int getH316Male() {
		return h316Male;
	}

	public void setH316Male(int male) {
		h316Male = male;
	}

	public int getH317Male() {
		return h317Male;
	}

	public void setH317Male(int male) {
		h317Male = male;
	}

	public int getH318Male() {
		return h318Male;
	}

	public void setH318Male(int male) {
		h318Male = male;
	}

	public int getH319Male() {
		return h319Male;
	}

	public void setH319Male(int male) {
		h319Male = male;
	}

	public int getH320Male() {
		return h320Male;
	}

	public void setH320Male(int male) {
		h320Male = male;
	}

	public int getH3MonoMale() {
		return h3MonoMale;
	}

	public void setH3MonoMale(int monoMale) {
		h3MonoMale = monoMale;
	}

	public int getH3MultiMale() {
		return h3MultiMale;
	}

	public void setH3MultiMale(int multiMale) {
		h3MultiMale = multiMale;
	}

	public int getH3SpedMale() {
		return h3SpedMale;
	}

	public void setH3SpedMale(int spedMale) {
		h3SpedMale = spedMale;
	}

	public int getH3RepeatMale() {
		return h3RepeatMale;
	}

	public void setH3RepeatMale(int repeatMale) {
		h3RepeatMale = repeatMale;
	}

	public int getH3TransferMale() {
		return h3TransferMale;
	}

	public void setH3TransferMale(int transferMale) {
		h3TransferMale = transferMale;
	}

	public int getH3BackMale() {
		return h3BackMale;
	}

	public void setH3BackMale(int backMale) {
		h3BackMale = backMale;
	}

	public int getH3MuslimMale() {
		return h3MuslimMale;
	}

	public void setH3MuslimMale(int muslimMale) {
		h3MuslimMale = muslimMale;
	}

	public int getH3IndigenousMale() {
		return h3IndigenousMale;
	}

	public void setH3IndigenousMale(int indigenousMale) {
		h3IndigenousMale = indigenousMale;
	}

	public int getH3ClassesMale() {
		return h3ClassesMale;
	}

	public void setH3ClassesMale(int classesMale) {
		h3ClassesMale = classesMale;
	}

	public int getH3CompleteMale() {
		return h3CompleteMale;
	}

	public void setH3CompleteMale(int completeMale) {
		h3CompleteMale = completeMale;
	}

	public int getH3DropMale() {
		return h3DropMale;
	}

	public void setH3DropMale(int dropMale) {
		h3DropMale = dropMale;
	}

	public int getH3TransferOutMale() {
		return h3TransferOutMale;
	}

	public void setH3TransferOutMale(int transferOutMale) {
		h3TransferOutMale = transferOutMale;
	}

	public int getH3FailedMale() {
		return h3FailedMale;
	}

	public void setH3FailedMale(int failedMale) {
		h3FailedMale = failedMale;
	}

	public int getH3ShiftMale() {
		return h3ShiftMale;
	}

	public void setH3ShiftMale(int shiftMale) {
		h3ShiftMale = shiftMale;
	}

	public int getH3ESCMale() {
		return h3ESCMale;
	}

	public void setH3ESCMale(int male) {
		h3ESCMale = male;
	}

	public int getH3EVSMale() {
		return h3EVSMale;
	}

	public void setH3EVSMale(int male) {
		h3EVSMale = male;
	}

	public int getH3ESCCompleteMale() {
		return h3ESCCompleteMale;
	}

	public void setH3ESCCompleteMale(int completeMale) {
		h3ESCCompleteMale = completeMale;
	}

	public int getH3EVSCompleteMale() {
		return h3EVSCompleteMale;
	}

	public void setH3EVSCompleteMale(int completeMale) {
		h3EVSCompleteMale = completeMale;
	}

	public int getH3ESCTransferOutMale() {
		return h3ESCTransferOutMale;
	}

	public void setH3ESCTransferOutMale(int transferOutMale) {
		h3ESCTransferOutMale = transferOutMale;
	}

	public int getH3EVSTransferOutMale() {
		return h3EVSTransferOutMale;
	}

	public void setH3EVSTransferOutMale(int transferOutMale) {
		h3EVSTransferOutMale = transferOutMale;
	}

	public int getH3ESCDropMale() {
		return h3ESCDropMale;
	}

	public void setH3ESCDropMale(int dropMale) {
		h3ESCDropMale = dropMale;
	}

	public int getH3EVSDropMale() {
		return h3EVSDropMale;
	}

	public void setH3EVSDropMale(int dropMale) {
		h3EVSDropMale = dropMale;
	}

	public int getH3ESCFailedMale() {
		return h3ESCFailedMale;
	}

	public void setH3ESCFailedMale(int failedMale) {
		h3ESCFailedMale = failedMale;
	}

	public int getH3EVSFailedMale() {
		return h3EVSFailedMale;
	}

	public void setH3EVSFailedMale(int failedMale) {
		h3EVSFailedMale = failedMale;
	}

	public int getH3Female() {
		return h3Female;
	}

	public void setH3Female(int female) {
		h3Female = female;
	}

	public int getH34Female() {
		return h34Female;
	}

	public void setH34Female(int female) {
		h34Female = female;
	}

	public int getH35Female() {
		return h35Female;
	}

	public void setH35Female(int female) {
		h35Female = female;
	}

	public int getH36Female() {
		return h36Female;
	}

	public void setH36Female(int female) {
		h36Female = female;
	}

	public int getH37Female() {
		return h37Female;
	}

	public void setH37Female(int female) {
		h37Female = female;
	}

	public int getH38Female() {
		return h38Female;
	}

	public void setH38Female(int female) {
		h38Female = female;
	}

	public int getH39Female() {
		return h39Female;
	}

	public void setH39Female(int female) {
		h39Female = female;
	}

	public int getH310Female() {
		return h310Female;
	}

	public void setH310Female(int female) {
		h310Female = female;
	}

	public int getH311Female() {
		return h311Female;
	}

	public void setH311Female(int female) {
		h311Female = female;
	}

	public int getH312Female() {
		return h312Female;
	}

	public void setH312Female(int female) {
		h312Female = female;
	}

	public int getH313Female() {
		return h313Female;
	}

	public void setH313Female(int female) {
		h313Female = female;
	}

	public int getH314Female() {
		return h314Female;
	}

	public void setH314Female(int female) {
		h314Female = female;
	}

	public int getH315Female() {
		return h315Female;
	}

	public void setH315Female(int female) {
		h315Female = female;
	}

	public int getH316Female() {
		return h316Female;
	}

	public void setH316Female(int female) {
		h316Female = female;
	}

	public int getH317Female() {
		return h317Female;
	}

	public void setH317Female(int female) {
		h317Female = female;
	}

	public int getH318Female() {
		return h318Female;
	}

	public void setH318Female(int female) {
		h318Female = female;
	}

	public int getH319Female() {
		return h319Female;
	}

	public void setH319Female(int female) {
		h319Female = female;
	}

	public int getH320Female() {
		return h320Female;
	}

	public void setH320Female(int female) {
		h320Female = female;
	}

	public int getH3MonoFemale() {
		return h3MonoFemale;
	}

	public void setH3MonoFemale(int monoFemale) {
		h3MonoFemale = monoFemale;
	}

	public int getH3MultiFemale() {
		return h3MultiFemale;
	}

	public void setH3MultiFemale(int multiFemale) {
		h3MultiFemale = multiFemale;
	}

	public int getH3SpedFemale() {
		return h3SpedFemale;
	}

	public void setH3SpedFemale(int spedFemale) {
		h3SpedFemale = spedFemale;
	}

	public int getH3RepeatFemale() {
		return h3RepeatFemale;
	}

	public void setH3RepeatFemale(int repeatFemale) {
		h3RepeatFemale = repeatFemale;
	}

	public int getH3TransferFemale() {
		return h3TransferFemale;
	}

	public void setH3TransferFemale(int transferFemale) {
		h3TransferFemale = transferFemale;
	}

	public int getH3BackFemale() {
		return h3BackFemale;
	}

	public void setH3BackFemale(int backFemale) {
		h3BackFemale = backFemale;
	}

	public int getH3MuslimFemale() {
		return h3MuslimFemale;
	}

	public void setH3MuslimFemale(int muslimFemale) {
		h3MuslimFemale = muslimFemale;
	}

	public int getH3IndigenousFemale() {
		return h3IndigenousFemale;
	}

	public void setH3IndigenousFemale(int indigenousFemale) {
		h3IndigenousFemale = indigenousFemale;
	}

	public int getH3ClassesFemale() {
		return h3ClassesFemale;
	}

	public void setH3ClassesFemale(int classesFemale) {
		h3ClassesFemale = classesFemale;
	}

	public int getH3CompleteFemale() {
		return h3CompleteFemale;
	}

	public void setH3CompleteFemale(int completeFemale) {
		h3CompleteFemale = completeFemale;
	}

	public int getH3DropFemale() {
		return h3DropFemale;
	}

	public void setH3DropFemale(int dropFemale) {
		h3DropFemale = dropFemale;
	}

	public int getH3TransferOutFemale() {
		return h3TransferOutFemale;
	}

	public void setH3TransferOutFemale(int transferOutFemale) {
		h3TransferOutFemale = transferOutFemale;
	}

	public int getH3FailedFemale() {
		return h3FailedFemale;
	}

	public void setH3FailedFemale(int failedFemale) {
		h3FailedFemale = failedFemale;
	}

	public int getH3ShiftFemale() {
		return h3ShiftFemale;
	}

	public void setH3ShiftFemale(int shiftFemale) {
		h3ShiftFemale = shiftFemale;
	}

	public int getH3ESCFemale() {
		return h3ESCFemale;
	}

	public void setH3ESCFemale(int female) {
		h3ESCFemale = female;
	}

	public int getH3EVSFemale() {
		return h3EVSFemale;
	}

	public void setH3EVSFemale(int female) {
		h3EVSFemale = female;
	}

	public int getH3ESCCompleteFemale() {
		return h3ESCCompleteFemale;
	}

	public void setH3ESCCompleteFemale(int completeFemale) {
		h3ESCCompleteFemale = completeFemale;
	}

	public int getH3EVSCompleteFemale() {
		return h3EVSCompleteFemale;
	}

	public void setH3EVSCompleteFemale(int completeFemale) {
		h3EVSCompleteFemale = completeFemale;
	}

	public int getH3ESCTransferOutFemale() {
		return h3ESCTransferOutFemale;
	}

	public void setH3ESCTransferOutFemale(int transferOutFemale) {
		h3ESCTransferOutFemale = transferOutFemale;
	}

	public int getH3EVSTransferOutFemale() {
		return h3EVSTransferOutFemale;
	}

	public void setH3EVSTransferOutFemale(int transferOutFemale) {
		h3EVSTransferOutFemale = transferOutFemale;
	}

	public int getH3ESCDropFemale() {
		return h3ESCDropFemale;
	}

	public void setH3ESCDropFemale(int dropFemale) {
		h3ESCDropFemale = dropFemale;
	}

	public int getH3EVSDropFemale() {
		return h3EVSDropFemale;
	}

	public void setH3EVSDropFemale(int dropFemale) {
		h3EVSDropFemale = dropFemale;
	}

	public int getH3ESCFailedFemale() {
		return h3ESCFailedFemale;
	}

	public void setH3ESCFailedFemale(int failedFemale) {
		h3ESCFailedFemale = failedFemale;
	}

	public int getH3EVSFailedFemale() {
		return h3EVSFailedFemale;
	}

	public void setH3EVSFailedFemale(int failedFemale) {
		h3EVSFailedFemale = failedFemale;
	}

	public int getH4Male() {
		return h4Male;
	}

	public void setH4Male(int male) {
		h4Male = male;
	}

	public int getH44Male() {
		return h44Male;
	}

	public void setH44Male(int male) {
		h44Male = male;
	}

	public int getH45Male() {
		return h45Male;
	}

	public void setH45Male(int male) {
		h45Male = male;
	}

	public int getH46Male() {
		return h46Male;
	}

	public void setH46Male(int male) {
		h46Male = male;
	}

	public int getH47Male() {
		return h47Male;
	}

	public void setH47Male(int male) {
		h47Male = male;
	}

	public int getH48Male() {
		return h48Male;
	}

	public void setH48Male(int male) {
		h48Male = male;
	}

	public int getH49Male() {
		return h49Male;
	}

	public void setH49Male(int male) {
		h49Male = male;
	}

	public int getH410Male() {
		return h410Male;
	}

	public void setH410Male(int male) {
		h410Male = male;
	}

	public int getH411Male() {
		return h411Male;
	}

	public void setH411Male(int male) {
		h411Male = male;
	}

	public int getH412Male() {
		return h412Male;
	}

	public void setH412Male(int male) {
		h412Male = male;
	}

	public int getH413Male() {
		return h413Male;
	}

	public void setH413Male(int male) {
		h413Male = male;
	}

	public int getH414Male() {
		return h414Male;
	}

	public void setH414Male(int male) {
		h414Male = male;
	}

	public int getH415Male() {
		return h415Male;
	}

	public void setH415Male(int male) {
		h415Male = male;
	}

	public int getH416Male() {
		return h416Male;
	}

	public void setH416Male(int male) {
		h416Male = male;
	}

	public int getH417Male() {
		return h417Male;
	}

	public void setH417Male(int male) {
		h417Male = male;
	}

	public int getH418Male() {
		return h418Male;
	}

	public void setH418Male(int male) {
		h418Male = male;
	}

	public int getH419Male() {
		return h419Male;
	}

	public void setH419Male(int male) {
		h419Male = male;
	}

	public int getH420Male() {
		return h420Male;
	}

	public void setH420Male(int male) {
		h420Male = male;
	}

	public int getH4MonoMale() {
		return h4MonoMale;
	}

	public void setH4MonoMale(int monoMale) {
		h4MonoMale = monoMale;
	}

	public int getH4MultiMale() {
		return h4MultiMale;
	}

	public void setH4MultiMale(int multiMale) {
		h4MultiMale = multiMale;
	}

	public int getH4SpedMale() {
		return h4SpedMale;
	}

	public void setH4SpedMale(int spedMale) {
		h4SpedMale = spedMale;
	}

	public int getH4RepeatMale() {
		return h4RepeatMale;
	}

	public void setH4RepeatMale(int repeatMale) {
		h4RepeatMale = repeatMale;
	}

	public int getH4TransferMale() {
		return h4TransferMale;
	}

	public void setH4TransferMale(int transferMale) {
		h4TransferMale = transferMale;
	}

	public int getH4BackMale() {
		return h4BackMale;
	}

	public void setH4BackMale(int backMale) {
		h4BackMale = backMale;
	}

	public int getH4MuslimMale() {
		return h4MuslimMale;
	}

	public void setH4MuslimMale(int muslimMale) {
		h4MuslimMale = muslimMale;
	}

	public int getH4IndigenousMale() {
		return h4IndigenousMale;
	}

	public void setH4IndigenousMale(int indigenousMale) {
		h4IndigenousMale = indigenousMale;
	}

	public int getH4ClassesMale() {
		return h4ClassesMale;
	}

	public void setH4ClassesMale(int classesMale) {
		h4ClassesMale = classesMale;
	}

	public int getH4CompleteMale() {
		return h4CompleteMale;
	}

	public void setH4CompleteMale(int completeMale) {
		h4CompleteMale = completeMale;
	}

	public int getH4DropMale() {
		return h4DropMale;
	}

	public void setH4DropMale(int dropMale) {
		h4DropMale = dropMale;
	}

	public int getH4TransferOutMale() {
		return h4TransferOutMale;
	}

	public void setH4TransferOutMale(int transferOutMale) {
		h4TransferOutMale = transferOutMale;
	}

	public int getH4FailedMale() {
		return h4FailedMale;
	}

	public void setH4FailedMale(int failedMale) {
		h4FailedMale = failedMale;
	}

	public int getH4ShiftMale() {
		return h4ShiftMale;
	}

	public void setH4ShiftMale(int shiftMale) {
		h4ShiftMale = shiftMale;
	}

	public int getH4ESCMale() {
		return h4ESCMale;
	}

	public void setH4ESCMale(int male) {
		h4ESCMale = male;
	}

	public int getH4EVSMale() {
		return h4EVSMale;
	}

	public void setH4EVSMale(int male) {
		h4EVSMale = male;
	}

	public int getH4ESCCompleteMale() {
		return h4ESCCompleteMale;
	}

	public void setH4ESCCompleteMale(int completeMale) {
		h4ESCCompleteMale = completeMale;
	}

	public int getH4EVSCompleteMale() {
		return h4EVSCompleteMale;
	}

	public void setH4EVSCompleteMale(int completeMale) {
		h4EVSCompleteMale = completeMale;
	}

	public int getH4ESCTransferOutMale() {
		return h4ESCTransferOutMale;
	}

	public void setH4ESCTransferOutMale(int transferOutMale) {
		h4ESCTransferOutMale = transferOutMale;
	}

	public int getH4EVSTransferOutMale() {
		return h4EVSTransferOutMale;
	}

	public void setH4EVSTransferOutMale(int transferOutMale) {
		h4EVSTransferOutMale = transferOutMale;
	}

	public int getH4ESCDropMale() {
		return h4ESCDropMale;
	}

	public void setH4ESCDropMale(int dropMale) {
		h4ESCDropMale = dropMale;
	}

	public int getH4EVSDropMale() {
		return h4EVSDropMale;
	}

	public void setH4EVSDropMale(int dropMale) {
		h4EVSDropMale = dropMale;
	}

	public int getH4ESCFailedMale() {
		return h4ESCFailedMale;
	}

	public void setH4ESCFailedMale(int failedMale) {
		h4ESCFailedMale = failedMale;
	}

	public int getH4EVSFailedMale() {
		return h4EVSFailedMale;
	}

	public void setH4EVSFailedMale(int failedMale) {
		h4EVSFailedMale = failedMale;
	}

	public int getH4Female() {
		return h4Female;
	}

	public void setH4Female(int female) {
		h4Female = female;
	}

	public int getH44Female() {
		return h44Female;
	}

	public void setH44Female(int female) {
		h44Female = female;
	}

	public int getH45Female() {
		return h45Female;
	}

	public void setH45Female(int female) {
		h45Female = female;
	}

	public int getH46Female() {
		return h46Female;
	}

	public void setH46Female(int female) {
		h46Female = female;
	}

	public int getH47Female() {
		return h47Female;
	}

	public void setH47Female(int female) {
		h47Female = female;
	}

	public int getH48Female() {
		return h48Female;
	}

	public void setH48Female(int female) {
		h48Female = female;
	}

	public int getH49Female() {
		return h49Female;
	}

	public void setH49Female(int female) {
		h49Female = female;
	}

	public int getH410Female() {
		return h410Female;
	}

	public void setH410Female(int female) {
		h410Female = female;
	}

	public int getH411Female() {
		return h411Female;
	}

	public void setH411Female(int female) {
		h411Female = female;
	}

	public int getH412Female() {
		return h412Female;
	}

	public void setH412Female(int female) {
		h412Female = female;
	}

	public int getH413Female() {
		return h413Female;
	}

	public void setH413Female(int female) {
		h413Female = female;
	}

	public int getH414Female() {
		return h414Female;
	}

	public void setH414Female(int female) {
		h414Female = female;
	}

	public int getH415Female() {
		return h415Female;
	}

	public void setH415Female(int female) {
		h415Female = female;
	}

	public int getH416Female() {
		return h416Female;
	}

	public void setH416Female(int female) {
		h416Female = female;
	}

	public int getH417Female() {
		return h417Female;
	}

	public void setH417Female(int female) {
		h417Female = female;
	}

	public int getH418Female() {
		return h418Female;
	}

	public void setH418Female(int female) {
		h418Female = female;
	}

	public int getH419Female() {
		return h419Female;
	}

	public void setH419Female(int female) {
		h419Female = female;
	}

	public int getH420Female() {
		return h420Female;
	}

	public void setH420Female(int female) {
		h420Female = female;
	}

	public int getH4MonoFemale() {
		return h4MonoFemale;
	}

	public void setH4MonoFemale(int monoFemale) {
		h4MonoFemale = monoFemale;
	}

	public int getH4MultiFemale() {
		return h4MultiFemale;
	}

	public void setH4MultiFemale(int multiFemale) {
		h4MultiFemale = multiFemale;
	}

	public int getH4SpedFemale() {
		return h4SpedFemale;
	}

	public void setH4SpedFemale(int spedFemale) {
		h4SpedFemale = spedFemale;
	}

	public int getH4RepeatFemale() {
		return h4RepeatFemale;
	}

	public void setH4RepeatFemale(int repeatFemale) {
		h4RepeatFemale = repeatFemale;
	}

	public int getH4TransferFemale() {
		return h4TransferFemale;
	}

	public void setH4TransferFemale(int transferFemale) {
		h4TransferFemale = transferFemale;
	}

	public int getH4BackFemale() {
		return h4BackFemale;
	}

	public void setH4BackFemale(int backFemale) {
		h4BackFemale = backFemale;
	}

	public int getH4MuslimFemale() {
		return h4MuslimFemale;
	}

	public void setH4MuslimFemale(int muslimFemale) {
		h4MuslimFemale = muslimFemale;
	}

	public int getH4IndigenousFemale() {
		return h4IndigenousFemale;
	}

	public void setH4IndigenousFemale(int indigenousFemale) {
		h4IndigenousFemale = indigenousFemale;
	}

	public int getH4ClassesFemale() {
		return h4ClassesFemale;
	}

	public void setH4ClassesFemale(int classesFemale) {
		h4ClassesFemale = classesFemale;
	}

	public int getH4CompleteFemale() {
		return h4CompleteFemale;
	}

	public void setH4CompleteFemale(int completeFemale) {
		h4CompleteFemale = completeFemale;
	}

	public int getH4DropFemale() {
		return h4DropFemale;
	}

	public void setH4DropFemale(int dropFemale) {
		h4DropFemale = dropFemale;
	}

	public int getH4TransferOutFemale() {
		return h4TransferOutFemale;
	}

	public void setH4TransferOutFemale(int transferOutFemale) {
		h4TransferOutFemale = transferOutFemale;
	}

	public int getH4FailedFemale() {
		return h4FailedFemale;
	}

	public void setH4FailedFemale(int failedFemale) {
		h4FailedFemale = failedFemale;
	}

	public int getH4ShiftFemale() {
		return h4ShiftFemale;
	}

	public void setH4ShiftFemale(int shiftFemale) {
		h4ShiftFemale = shiftFemale;
	}

	public int getH4ESCFemale() {
		return h4ESCFemale;
	}

	public void setH4ESCFemale(int female) {
		h4ESCFemale = female;
	}

	public int getH4EVSFemale() {
		return h4EVSFemale;
	}

	public void setH4EVSFemale(int female) {
		h4EVSFemale = female;
	}

	public int getH4ESCCompleteFemale() {
		return h4ESCCompleteFemale;
	}

	public void setH4ESCCompleteFemale(int completeFemale) {
		h4ESCCompleteFemale = completeFemale;
	}

	public int getH4EVSCompleteFemale() {
		return h4EVSCompleteFemale;
	}

	public void setH4EVSCompleteFemale(int completeFemale) {
		h4EVSCompleteFemale = completeFemale;
	}

	public int getH4ESCTransferOutFemale() {
		return h4ESCTransferOutFemale;
	}

	public void setH4ESCTransferOutFemale(int transferOutFemale) {
		h4ESCTransferOutFemale = transferOutFemale;
	}

	public int getH4EVSTransferOutFemale() {
		return h4EVSTransferOutFemale;
	}

	public void setH4EVSTransferOutFemale(int transferOutFemale) {
		h4EVSTransferOutFemale = transferOutFemale;
	}

	public int getH4ESCDropFemale() {
		return h4ESCDropFemale;
	}

	public void setH4ESCDropFemale(int dropFemale) {
		h4ESCDropFemale = dropFemale;
	}

	public int getH4EVSDropFemale() {
		return h4EVSDropFemale;
	}

	public void setH4EVSDropFemale(int dropFemale) {
		h4EVSDropFemale = dropFemale;
	}

	public int getH4ESCFailedFemale() {
		return h4ESCFailedFemale;
	}

	public void setH4ESCFailedFemale(int failedFemale) {
		h4ESCFailedFemale = failedFemale;
	}

	public int getH4EVSFailedFemale() {
		return h4EVSFailedFemale;
	}

	public void setH4EVSFailedFemale(int failedFemale) {
		h4EVSFailedFemale = failedFemale;
	}

	public int getGsRoomInsAcademic() {
		return gsRoomInsAcademic;
	}

	public void setGsRoomInsAcademic(int gsRoomInsAcademic) {
		this.gsRoomInsAcademic = gsRoomInsAcademic;
	}

	public int getGsRoomInsScienceLab() {
		return gsRoomInsScienceLab;
	}

	public void setGsRoomInsScienceLab(int gsRoomInsScienceLab) {
		this.gsRoomInsScienceLab = gsRoomInsScienceLab;
	}

	public int getGsRoomInsHE() {
		return gsRoomInsHE;
	}

	public void setGsRoomInsHE(int gsRoomInsHE) {
		this.gsRoomInsHE = gsRoomInsHE;
	}

	public int getGsRoomInsWorkshop() {
		return gsRoomInsWorkshop;
	}

	public void setGsRoomInsWorkshop(int gsRoomInsWorkshop) {
		this.gsRoomInsWorkshop = gsRoomInsWorkshop;
	}

	public int getGsRoomInsComputer() {
		return gsRoomInsComputer;
	}

	public void setGsRoomInsComputer(int gsRoomInsComputer) {
		this.gsRoomInsComputer = gsRoomInsComputer;
	}

	public int getGsRoomInsNotUse() {
		return gsRoomInsNotUse;
	}

	public void setGsRoomInsNotUse(int gsRoomInsNotUse) {
		this.gsRoomInsNotUse = gsRoomInsNotUse;
	}

	public int getGsRoomLibrary() {
		return gsRoomLibrary;
	}

	public void setGsRoomLibrary(int gsRoomLibrary) {
		this.gsRoomLibrary = gsRoomLibrary;
	}

	public int getGsRoomClinic() {
		return gsRoomClinic;
	}

	public void setGsRoomClinic(int gsRoomClinic) {
		this.gsRoomClinic = gsRoomClinic;
	}

	public int getGsRoomCanteen() {
		return gsRoomCanteen;
	}

	public void setGsRoomCanteen(int gsRoomCanteen) {
		this.gsRoomCanteen = gsRoomCanteen;
	}

	public int getGsRoomOffice() {
		return gsRoomOffice;
	}

	public void setGsRoomOffice(int gsRoomOffice) {
		this.gsRoomOffice = gsRoomOffice;
	}

	public int getGsRoomOtherUse() {
		return gsRoomOtherUse;
	}

	public void setGsRoomOtherUse(int gsRoomOtherUse) {
		this.gsRoomOtherUse = gsRoomOtherUse;
	}

	public int getGsRoomNotUse() {
		return gsRoomNotUse;
	}

	public void setGsRoomNotUse(int gsRoomNotUse) {
		this.gsRoomNotUse = gsRoomNotUse;
	}

	public int getGsDesk() {
		return gsDesk;
	}

	public void setGsDesk(int gsDesk) {
		this.gsDesk = gsDesk;
	}

	public int getGsChairTable() {
		return gsChairTable;
	}

	public void setGsChairTable(int gsChairTable) {
		this.gsChairTable = gsChairTable;
	}

	public int getGsArmChair() {
		return gsArmChair;
	}

	public void setGsArmChair(int gsArmChair) {
		this.gsArmChair = gsArmChair;
	}

	public int getGsToiletGirlBowl() {
		return gsToiletGirlBowl;
	}

	public void setGsToiletGirlBowl(int gsToiletGirlBowl) {
		this.gsToiletGirlBowl = gsToiletGirlBowl;
	}

	public int getGsToiletBoyUrinalSingle() {
		return gsToiletBoyUrinalSingle;
	}

	public void setGsToiletBoyUrinalSingle(int gsToiletBoyUrinalSingle) {
		this.gsToiletBoyUrinalSingle = gsToiletBoyUrinalSingle;
	}

	public int getGsToiletBoyUrinalMultiple() {
		return gsToiletBoyUrinalMultiple;
	}

	public void setGsToiletBoyUrinalMultiple(int gsToiletBoyUrinalMultiple) {
		this.gsToiletBoyUrinalMultiple = gsToiletBoyUrinalMultiple;
	}

	public int getGsToiletBoyBowl() {
		return gsToiletBoyBowl;
	}

	public void setGsToiletBoyBowl(int gsToiletBoyBowl) {
		this.gsToiletBoyBowl = gsToiletBoyBowl;
	}

	public int getGsToiletShared() {
		return gsToiletShared;
	}

	public void setGsToiletShared(int gsToiletShared) {
		this.gsToiletShared = gsToiletShared;
	}

	public int getHsRoomInsAcademic() {
		return hsRoomInsAcademic;
	}

	public void setHsRoomInsAcademic(int hsRoomInsAcademic) {
		this.hsRoomInsAcademic = hsRoomInsAcademic;
	}

	public int getHsRoomInsScienceLab() {
		return hsRoomInsScienceLab;
	}

	public void setHsRoomInsScienceLab(int hsRoomInsScienceLab) {
		this.hsRoomInsScienceLab = hsRoomInsScienceLab;
	}

	public int getHsRoomInsHE() {
		return hsRoomInsHE;
	}

	public void setHsRoomInsHE(int hsRoomInsHE) {
		this.hsRoomInsHE = hsRoomInsHE;
	}

	public int getHsRoomInsWorkshop() {
		return hsRoomInsWorkshop;
	}

	public void setHsRoomInsWorkshop(int hsRoomInsWorkshop) {
		this.hsRoomInsWorkshop = hsRoomInsWorkshop;
	}

	public int getHsRoomInsComputer() {
		return hsRoomInsComputer;
	}

	public void setHsRoomInsComputer(int hsRoomInsComputer) {
		this.hsRoomInsComputer = hsRoomInsComputer;
	}

	public int getHsRoomInsNotUse() {
		return hsRoomInsNotUse;
	}

	public void setHsRoomInsNotUse(int hsRoomInsNotUse) {
		this.hsRoomInsNotUse = hsRoomInsNotUse;
	}

	public int getHsRoomLibrary() {
		return hsRoomLibrary;
	}

	public void setHsRoomLibrary(int hsRoomLibrary) {
		this.hsRoomLibrary = hsRoomLibrary;
	}

	public int getHsRoomClinic() {
		return hsRoomClinic;
	}

	public void setHsRoomClinic(int hsRoomClinic) {
		this.hsRoomClinic = hsRoomClinic;
	}

	public int getHsRoomCanteen() {
		return hsRoomCanteen;
	}

	public void setHsRoomCanteen(int hsRoomCanteen) {
		this.hsRoomCanteen = hsRoomCanteen;
	}

	public int getHsRoomOffice() {
		return hsRoomOffice;
	}

	public void setHsRoomOffice(int hsRoomOffice) {
		this.hsRoomOffice = hsRoomOffice;
	}

	public int getHsRoomOtherUse() {
		return hsRoomOtherUse;
	}

	public void setHsRoomOtherUse(int hsRoomOtherUse) {
		this.hsRoomOtherUse = hsRoomOtherUse;
	}

	public int getHsRoomNotUse() {
		return hsRoomNotUse;
	}

	public void setHsRoomNotUse(int hsRoomNotUse) {
		this.hsRoomNotUse = hsRoomNotUse;
	}

	public int getHsDesk() {
		return hsDesk;
	}

	public void setHsDesk(int hsDesk) {
		this.hsDesk = hsDesk;
	}

	public int getHsChairTable() {
		return hsChairTable;
	}

	public void setHsChairTable(int hsChairTable) {
		this.hsChairTable = hsChairTable;
	}

	public int getHsArmChair() {
		return hsArmChair;
	}

	public void setHsArmChair(int hsArmChair) {
		this.hsArmChair = hsArmChair;
	}

	public int getHsToiletGirlBowl() {
		return hsToiletGirlBowl;
	}

	public void setHsToiletGirlBowl(int hsToiletGirlBowl) {
		this.hsToiletGirlBowl = hsToiletGirlBowl;
	}

	public int getHsToiletBoyUrinalSingle() {
		return hsToiletBoyUrinalSingle;
	}

	public void setHsToiletBoyUrinalSingle(int hsToiletBoyUrinalSingle) {
		this.hsToiletBoyUrinalSingle = hsToiletBoyUrinalSingle;
	}

	public int getHsToiletBoyUrinalMultiple() {
		return hsToiletBoyUrinalMultiple;
	}

	public void setHsToiletBoyUrinalMultiple(int hsToiletBoyUrinalMultiple) {
		this.hsToiletBoyUrinalMultiple = hsToiletBoyUrinalMultiple;
	}

	public int getHsToiletBoyBowl() {
		return hsToiletBoyBowl;
	}

	public void setHsToiletBoyBowl(int hsToiletBoyBowl) {
		this.hsToiletBoyBowl = hsToiletBoyBowl;
	}

	public int getHsToiletShared() {
		return hsToiletShared;
	}

	public void setHsToiletShared(int hsToiletShared) {
		this.hsToiletShared = hsToiletShared;
	}

	public int getPreMaleFacultyFulltime() {
		return preMaleFacultyFulltime;
	}

	public void setPreMaleFacultyFulltime(int preMaleFacultyFulltime) {
		this.preMaleFacultyFulltime = preMaleFacultyFulltime;
	}

	public int getPreMaleFacultyParttime() {
		return preMaleFacultyParttime;
	}

	public void setPreMaleFacultyParttime(int preMaleFacultyParttime) {
		this.preMaleFacultyParttime = preMaleFacultyParttime;
	}

	public int getPreMaleFacultyAdmin() {
		return preMaleFacultyAdmin;
	}

	public void setPreMaleFacultyAdmin(int preMaleFacultyAdmin) {
		this.preMaleFacultyAdmin = preMaleFacultyAdmin;
	}

	public int getPreFemaleFacultyFulltime() {
		return preFemaleFacultyFulltime;
	}

	public void setPreFemaleFacultyFulltime(int preFemaleFacultyFulltime) {
		this.preFemaleFacultyFulltime = preFemaleFacultyFulltime;
	}

	public int getPreFemaleFacultyParttime() {
		return preFemaleFacultyParttime;
	}

	public void setPreFemaleFacultyParttime(int preFemaleFacultyParttime) {
		this.preFemaleFacultyParttime = preFemaleFacultyParttime;
	}

	public int getPreFemaleFacultyAdmin() {
		return preFemaleFacultyAdmin;
	}

	public void setPreFemaleFacultyAdmin(int preFemaleFacultyAdmin) {
		this.preFemaleFacultyAdmin = preFemaleFacultyAdmin;
	}

	public int getGsMaleFacultyFulltime() {
		return gsMaleFacultyFulltime;
	}

	public void setGsMaleFacultyFulltime(int gsMaleFacultyFulltime) {
		this.gsMaleFacultyFulltime = gsMaleFacultyFulltime;
	}

	public int getGsMaleFacultyParttime() {
		return gsMaleFacultyParttime;
	}

	public void setGsMaleFacultyParttime(int gsMaleFacultyParttime) {
		this.gsMaleFacultyParttime = gsMaleFacultyParttime;
	}

	public int getGsMaleFacultyAdmin() {
		return gsMaleFacultyAdmin;
	}

	public void setGsMaleFacultyAdmin(int gsMaleFacultyAdmin) {
		this.gsMaleFacultyAdmin = gsMaleFacultyAdmin;
	}

	public int getGsFemaleFacultyFulltime() {
		return gsFemaleFacultyFulltime;
	}

	public void setGsFemaleFacultyFulltime(int gsFemaleFacultyFulltime) {
		this.gsFemaleFacultyFulltime = gsFemaleFacultyFulltime;
	}

	public int getGsFemaleFacultyParttime() {
		return gsFemaleFacultyParttime;
	}

	public void setGsFemaleFacultyParttime(int gsFemaleFacultyParttime) {
		this.gsFemaleFacultyParttime = gsFemaleFacultyParttime;
	}

	public int getGsFemaleFacultyAdmin() {
		return gsFemaleFacultyAdmin;
	}

	public void setGsFemaleFacultyAdmin(int gsFemaleFacultyAdmin) {
		this.gsFemaleFacultyAdmin = gsFemaleFacultyAdmin;
	}

	public int getHsMaleFacultyFulltime() {
		return hsMaleFacultyFulltime;
	}

	public void setHsMaleFacultyFulltime(int hsMaleFacultyFulltime) {
		this.hsMaleFacultyFulltime = hsMaleFacultyFulltime;
	}

	public int getHsMaleFacultyParttime() {
		return hsMaleFacultyParttime;
	}

	public void setHsMaleFacultyParttime(int hsMaleFacultyParttime) {
		this.hsMaleFacultyParttime = hsMaleFacultyParttime;
	}

	public int getHsMaleFacultyAdmin() {
		return hsMaleFacultyAdmin;
	}

	public void setHsMaleFacultyAdmin(int hsMaleFacultyAdmin) {
		this.hsMaleFacultyAdmin = hsMaleFacultyAdmin;
	}

	public int getHsFemaleFacultyFulltime() {
		return hsFemaleFacultyFulltime;
	}

	public void setHsFemaleFacultyFulltime(int hsFemaleFacultyFulltime) {
		this.hsFemaleFacultyFulltime = hsFemaleFacultyFulltime;
	}

	public int getHsFemaleFacultyParttime() {
		return hsFemaleFacultyParttime;
	}

	public void setHsFemaleFacultyParttime(int hsFemaleFacultyParttime) {
		this.hsFemaleFacultyParttime = hsFemaleFacultyParttime;
	}

	public int getHsFemaleFacultyAdmin() {
		return hsFemaleFacultyAdmin;
	}

	public void setHsFemaleFacultyAdmin(int hsFemaleFacultyAdmin) {
		this.hsFemaleFacultyAdmin = hsFemaleFacultyAdmin;
	}
    
}
