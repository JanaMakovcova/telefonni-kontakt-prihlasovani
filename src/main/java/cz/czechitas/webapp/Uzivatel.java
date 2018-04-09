package cz.czechitas.webapp;

public class Uzivatel {
    
    private String krestniJmeno;
    private String prijmeni;
    private String email;
    private String heslo;

    public Uzivatel() {
    }

    public Uzivatel(String krestniJmeno, String prijmeni, String email, String heslo) {
        this.krestniJmeno = krestniJmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.heslo = heslo;
    }

    public String getKrestniJmeno() {
        return krestniJmeno;
    }

    public void setKrestniJmeno(String newValue) {
        krestniJmeno = newValue;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String newValue) {
        prijmeni = newValue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newValue) {
        email = newValue;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String newValue) {
        heslo = newValue;
    }
}
