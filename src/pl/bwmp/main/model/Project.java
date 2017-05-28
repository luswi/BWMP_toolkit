package pl.bwmp.main.model;

import java.time.LocalDate;

import javafx.beans.property.*;

/**
* Created by lukas on 14.05.2017.
* hold information for projects
*/

public class Project {

    private final StringProperty shipId;
    private final StringProperty shipName;
    private final StringProperty shipStatus; //approval stage
    private final StringProperty LCS;
    private final StringProperty seq;
    private final StringProperty flow;
    private final StringProperty sfaCreated;
    private final StringProperty oracle;
    private final StringProperty sfaSent;
    private final StringProperty sfaRec;
    private final StringProperty cert;
    private final StringProperty sharePoint;
    private final SimpleDoubleProperty hUsed;
    private final SimpleDoubleProperty hMax;
    private final StringProperty invoice;
    private final StringProperty po;
    private final StringProperty status;




/**
* Default constructor
*/

    public Project() {
        this(null, null,null);

    }

/**
* Constructor with some initial data
*/

    public Project(String shipId, String shipName, String shipStatus){
        this.shipId = new SimpleStringProperty(shipId);
        this.shipName = new SimpleStringProperty(shipName);
        this.shipStatus = new SimpleStringProperty(shipStatus);
        //Some initial dummy data for testing
        this.LCS = new SimpleStringProperty("LCS - Yes/No");
        this.seq = new SimpleStringProperty("SEQ - Yes/No");
        this.flow = new SimpleStringProperty("FLOW - Yes/No");
        this.sfaCreated = new SimpleStringProperty("SFA - Yes/No");
        this.oracle = new SimpleStringProperty("ORACLE - Yes/No");
        this.sfaSent = new SimpleStringProperty("SFA Sent - Yes/No");
        this.sfaRec = new SimpleStringProperty("SFA Rec - Yes/No");
        this.cert = new SimpleStringProperty("Certificate - Yes/No");
        this.sharePoint = new SimpleStringProperty("SharePoint - Yes/No");
        this.hUsed = new SimpleDoubleProperty(0.5);
        this.hMax = new SimpleDoubleProperty(4.5);
        this.invoice = new SimpleStringProperty("Invoice info");
        this.po = new SimpleStringProperty("PO info");
        this.status = new SimpleStringProperty("open/close");


    }

    public String getShipId(){
        return shipId.get();
    }
    public void setShipId(String shipId){
        this.shipId.set(shipId);
    }
    public StringProperty shipIdProperty(){
        return shipId;
    }



    public String getShipName(){
        return shipName.get();
    }
    public void setShipName(String shipName){
        this.shipName.set(shipName);
    }
    public StringProperty shipNameProperty(){
        return shipName;
    }



    public String getShipStatus(){
        return shipStatus.get();
    }
    public void setShipStatus(String shipStatus){
        this.shipStatus.set(shipStatus);
    }
    public StringProperty shipStatusProperty(){
        return shipStatus;
    }



    public String getLCS(){
        return LCS.get();
    }
    public void setLCS(String LCS){
        this.LCS.set(LCS);
    }
    public StringProperty LCSProperty(){
        return LCS;
    }



    public String getSeq() {
        return seq.get();
    }
    public void setSeq(String seq){
        this.seq.set(seq);
    }
    public StringProperty seqProperty(){
        return seq;
    }


    public String getFlow(){
        return flow.get();
    }
    public void setFlow(String flow){
        this.flow.set(flow);
    }
    public StringProperty flowProperty(){
        return flow;
    }


    public String getSfaCreated() {
        return sfaCreated.get();
    }
    public void setSfaCreated(String sfaCreated){
        this.sfaCreated.set(sfaCreated);
    }
    public StringProperty sfaCreatedProperty() {
        return  sfaCreated;
    }


    public String getOracle(){
        return oracle.get();
    }
    public void setOracle(String oracle){
        this.oracle.set(oracle);
    }
    public StringProperty oracleProperty(){
        return oracle;
    }


    public String getSfaSent(){
        return sfaSent.get();
    }
    public void setSfaSent(String sfaSent){
        this.sfaSent.set(sfaSent);
    }
    public StringProperty sfaSentProperty(){
        return sfaSent;
    }


    public String getSfaRec(){
        return sfaRec.get();
    }
    public void setSfaRec(String sfaRec){
        this.sfaRec.set(sfaRec);
    }
    public StringProperty sfaRecProperty(){
        return sfaRec;
    }


    public String getCert(){
        return cert.get();
    }
    public void setCert(String cert){
        this.cert.set(cert);
    }
    public StringProperty certProperty(){
        return cert;
    }


    public String getSharePoint(){
        return sharePoint.get();
    }
    public void setSharePoint(String sharePoint){
        this.sharePoint.set(sharePoint);
    }
    public StringProperty sharePointProperty(){
        return sharePoint;
    }


    public double getHused(){
        return hUsed.get();
    }
    public void setHused(double hUsed){
        this.hUsed.set(hUsed);
    }
    private DoubleProperty hUsedProperty(){
        return hUsed;
    }


    public double getHmax(){
        return hMax.get();
    }
    public void setHmax(double hMax){
        this.hMax.set(hMax);
    }
    public DoubleProperty hMaxProperty(){
        return hMax;
    }

    public String getInvoice(){ return invoice.get(); }
    public void setInvoice(String invoice){ this.invoice.set(invoice); }
    public StringProperty invoiceProperty(){ return invoice; }

    public String getPo(){ return po.get(); }
    public void setPo(String po){ this.po.set(po); }
    public StringProperty poProperty(){ return po; }

    public String getStatus(){ return status.get();}
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() {return status;}


}
