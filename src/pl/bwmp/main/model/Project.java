package pl.bwmp.main.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
* Created by lukas on 14.05.2017.
* hold information for projects
*/

public class Project {

    private final StringProperty shipId;
    private final StringProperty shipName;
    private final StringProperty shipStatus; //approval stage
    private final StringProperty LCS;
    private final StringProperty sfaCreated;
    //private final StringProperty seq;
    //private final StringProperty flow;

    //private final StringProperty oracle;
    //private final StringProperty sfaSent;
    //private final StringProperty sfaRec;
    //private final StringProperty cert;
    //private final StringProperty sharePoint;
    //private final StringProperty invoice;
    //private final StringProperty po;
    //private final StringProperty status;
    //private final SimpleIntegerProperty hUsed;
    //private final StringProperty hLeft;
    //private final StringProperty hMax;



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
        this.sfaCreated = new SimpleStringProperty("SFA - Yes/No");
        //this.oracle = new SimpleStringProperty("No");
        //this.sfaSent = new SimpleStringProperty("No");
        //this.sfaRec = new SimpleStringProperty("No");
        //this.cert = new SimpleStringProperty("No");
        //this.sharePoint = new SimpleStringProperty("No");
        //this.invoice = new SimpleStringProperty("No");
        //this.po = new SimpleStringProperty("No");
        //this.status = new SimpleStringProperty("pre-check");
        //this.hUsed = new SimpleIntegerProperty(0);

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

    public String getSfaCreated() {return sfaCreated.get();}
    public void setSfaCreated(String sfaCreated){this.sfaCreated.set(sfaCreated);}
    public StringProperty sfaCreatedProperty() {return  sfaCreated;}

}
