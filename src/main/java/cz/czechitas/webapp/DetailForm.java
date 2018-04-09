package cz.czechitas.webapp;

public class DetailForm {

    private String jmeno;
    private String mesto;
    private String stat;
    private String telefon;

    public DetailForm() {
    }

    public DetailForm (Long id, String jmeno, String mesto, String stat, String telefon) {
        this.jmeno = jmeno;
        this.mesto = mesto;
        this.stat = stat;
        this.telefon = telefon;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String newValue) {
        jmeno = newValue;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String newValue) {
        stat = newValue;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String newValue) {
        telefon = newValue;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String newValue) {
        mesto = newValue;
    }
}
