package com.example.data.postgres.migration.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "temp_migrate")
public class TempMigrate {

    @Id
    @SequenceGenerator(name = "sequence-generator", sequenceName = "temp_migrate_tmpid_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    private int tmpid;

    private long irecipientid;

    private LocalDate tsfirstsignup;

    private String sfirstsignupcountryisoa2;

    private String sbrandcode;

    private String seuci;

    private long istoreid;

    private long isourceid;

    private long irecipientstatus;

    public TempMigrate() {
    }

    public TempMigrate(long irecipientid, LocalDate tsfirstsignup, String sfirstsignupcountryisoa2, String sbrandcode,
                       String seuci, long istoreid, long isourceid, long irecipientstatus) {
        this.irecipientid = irecipientid;
        this.tsfirstsignup = tsfirstsignup;
        this.sfirstsignupcountryisoa2 = sfirstsignupcountryisoa2;
        this.sbrandcode = sbrandcode;
        this.seuci = seuci;
        this.istoreid = istoreid;
        this.isourceid = isourceid;
        this.irecipientstatus = irecipientstatus;
    }

    public Integer getTmpid() {
        return tmpid;
    }

    public void setTmpid(Integer tmpid) {
        this.tmpid = tmpid;
    }

    public long getIrecipientid() {
        return irecipientid;
    }

    public void setIrecipientid(long irecipientid) {
        this.irecipientid = irecipientid;
    }

    public LocalDate getTsfirstsignup() {
        return tsfirstsignup;
    }

    public void setTsfirstsignup(LocalDate tsfirstsignup) {
        this.tsfirstsignup = tsfirstsignup;
    }

    public String getSfirstsignupcountryisoa2() {
        return sfirstsignupcountryisoa2;
    }

    public void setSfirstsignupcountryisoa2(String sfirstsignupcountryisoa2) {
        this.sfirstsignupcountryisoa2 = sfirstsignupcountryisoa2;
    }

    public String getSbrandcode() {
        return sbrandcode;
    }

    public void setSbrandcode(String sbrandcode) {
        this.sbrandcode = sbrandcode;
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

    public long getIrecipientstatus() {
        return irecipientstatus;
    }

    public void setIrecipientstatus(long irecipientstatus) {
        this.irecipientstatus = irecipientstatus;
    }
}
