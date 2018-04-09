package cz.czechitas.webapp;

import javax.validation.constraints.*;

public class RegistraceForm {
    
    @Size(min=2, max = 30)
    private String krestniJmeno;
    @Size(min=2, max = 30)
    private String prijmeni;
    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    @NotNull
    @Size(min=2, max = 30)
    private String heslo;
    @NotNull
    @Size(min=2, max = 30)
    private String hesloZnovu;

    public RegistraceForm() {
        
    }

    public RegistraceForm(String krestniJmeno, String prijmeni, String email, String heslo, String hesloZnovu) {
        this.krestniJmeno = krestniJmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.heslo = heslo;
        this.hesloZnovu = hesloZnovu;
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
    public String getHesloZnovu() {
        return hesloZnovu;
    }

    public void setHesloZnovu(String newValue) {
        hesloZnovu = newValue;
    }
}
