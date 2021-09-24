package com.example.data.postgres.migration.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "temp_migrate")
public class TempMigrate {

    @Id
    @SequenceGenerator(name = "sequence-generator", sequenceName = "temp_migrate_tmpid_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    private int tmpid;

    private long irecid;

    private LocalDate tsignupdate;

    private String scountry;

    private String sbrand;

    private String seuci;

    private long istoreid;

    private long isourceid;

    private long istatus;

    public TempMigrate() {
    }

    public TempMigrate(long irecid, LocalDate tsignupdate, String scountry, String sbrand,
                       String seuci, long istoreid, long isourceid, long istatus) {
        this.irecid = irecid;
        this.tsignupdate = tsignupdate;
        this.scountry = scountry;
        this.sbrand = sbrand;
        this.seuci = seuci;
        this.istoreid = istoreid;
        this.isourceid = isourceid;
        this.istatus = istatus;
    }

    public int getTmpid() {
        return tmpid;
    }

    public void setTmpid(int tmpid) {
        this.tmpid = tmpid;
    }

    public long getIrecid() {
        return irecid;
    }

    public void setIrecid(long irecid) {
        this.irecid = irecid;
    }

    public LocalDate getTsignupdate() {
        return tsignupdate;
    }

    public void setTsignupdate(LocalDate tsignupdate) {
        this.tsignupdate = tsignupdate;
    }

    public String getScountry() {
        return scountry;
    }

    public void setScountry(String scountry) {
        this.scountry = scountry;
    }

    public String getSbrand() {
        return sbrand;
    }

    public void setSbrand(String sbrand) {
        this.sbrand = sbrand;
    }

    public String getSeuci() {
        return seuci;
    }

    public void setSeuci(String seuci) {
        this.seuci = seuci;
    }

    public long getIstoreid() {
        return istoreid;
    }

    public void setIstoreid(long istoreid) {
        this.istoreid = istoreid;
    }

    public long getIsourceid() {
        return isourceid;
    }

    public void setIsourceid(long isourceid) {
        this.isourceid = isourceid;
    }

    public long getIstatus() {
        return istatus;
    }

    public void setIstatus(long istatus) {
        this.istatus = istatus;
    }
}
