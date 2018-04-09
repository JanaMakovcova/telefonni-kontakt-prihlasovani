package cz.czechitas.webapp;

import java.util.*;
import java.util.concurrent.*;
import javax.validation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private List<TelefonniKontakt> seznamKontaktu = Arrays.asList(
            new TelefonniKontakt(500L, "Thomas Alva Edison", "New Jersey", "USA", "+1-123-555-666"),
            new TelefonniKontakt(501L, "Johan Gregor Mendel", "Brno", "Rakousko-Uhersko", "+420 544213255"),
            new TelefonniKontakt(502L, "Albert Einstein", "Bern", "Švýcarsko", "+41 953 203 569"),
            new TelefonniKontakt(503L, "Marie Curie Sklodowská", "Paříž", "Francie", "+33 7456 123 523"),
            new TelefonniKontakt(504L, "Kamil Ševeček", "Brno", "Česko", "+420 604 123 456")
    );

    private List<Uzivatel> seznamRegistrovanych;

    public HlavniController() {
        seznamRegistrovanych = new CopyOnWriteArrayList<>();
        seznamRegistrovanych.add(new Uzivatel("Pepa", "Prasátko", "pepa@pepa.com", "pepa"));
        seznamRegistrovanych.add(new Uzivatel("Connie", "Kravička", "connie@connie.com", "connie"));
        seznamRegistrovanych.add(new Uzivatel("Pokus", "Pokus", "pokus@pokus.com", "password"));
    }


    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("index");
        return drzakNaData;
    }

    @RequestMapping("/kontakty/")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("kontakty/index");
        drzakNaData.addObject("seznam", seznamKontaktu);

        return drzakNaData;
    }

    @RequestMapping("/login/registrace-uspesna.html")
    public ModelAndView zobrazRegistraceUspesna() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("login/registrace-uspesna");
        return drzakNaData;
    }

    @RequestMapping("/login/zapomenute-heslo-poslano.html")
    public ModelAndView hesloPoslano() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("login/zapomenute-heslo-poslano");
        return drzakNaData;
    }

    @RequestMapping(value = "/login/zapomenute-heslo.html", method = RequestMethod.GET)
    public ModelAndView zobrazHeslo() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("login/zapomenute-heslo");
        return drzakNaData;
    }
    @RequestMapping(value = "/login/zapomenute-heslo.html", method = RequestMethod.POST)
    public ModelAndView posliHeslo(String email) {
        ModelAndView drzakNaData;
        String posliHesloNaMail = email;
        drzakNaData = new ModelAndView("login/zapomenute-heslo-poslano");
        return drzakNaData;
    }

    @RequestMapping(value = "/login/prihlaseni.html", method = RequestMethod.GET)
    public ModelAndView zobrazPrihlaseni() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("login/prihlaseni");
        return drzakNaData;
    }
    @RequestMapping(value = "/login/prihlaseni.html", method = RequestMethod.POST)
    public ModelAndView zobrazPoPrihlaseni(String email, String heslo) {
        ModelAndView drzakNaData;
        ModelAndView drzakNaDataChyba;
        drzakNaData = new ModelAndView("redirect:../kontakty/");
        drzakNaDataChyba = new ModelAndView("login/prihlaseni-fail");
        if (jeRegistrovany(email, heslo)) return drzakNaData;
        else return drzakNaDataChyba;
    }
    private Boolean jeRegistrovany(String email, String heslo) {
        for (Uzivatel registrace : seznamRegistrovanych) {
            if ((registrace.getEmail().equals(email))){
                if ((registrace.getHeslo().equals(heslo))) return true;
            }
        }
        return false;
    }
    
    @RequestMapping(value = "/login/registrace.html", method = RequestMethod.GET)
    public ModelAndView zobrazRegistrace() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("login/registrace");
        RegistraceForm vyplnenaRegistrace = new RegistraceForm();
        drzakNaData.addObject("registrace", vyplnenaRegistrace);
        drzakNaData.addObject("chybnyUdaj", "");
        return drzakNaData;
    }
    @RequestMapping(value = "/login/registrace.html", method = RequestMethod.POST)
    public ModelAndView zpracujRegistrace(@Valid RegistraceForm vyplnenaRegistrace, BindingResult validacniChyby) {

        if (validacniChyby.hasErrors()){
            ModelAndView drzakNaDataChyby;
            drzakNaDataChyby = new ModelAndView("login/registrace");
            drzakNaDataChyby.addObject("registrace", vyplnenaRegistrace);
            drzakNaDataChyby.addObject("chybnyUdaj", "Chyba! Vyplňte, prosím, znovu");
            return drzakNaDataChyby;
        }

        if (!vyplnenaRegistrace.getHeslo().equals(vyplnenaRegistrace.getHesloZnovu())) {
            ModelAndView drzakNaDataChyby;
            drzakNaDataChyby = new ModelAndView("login/registrace");
            drzakNaDataChyby.addObject("registrace", vyplnenaRegistrace);
            drzakNaDataChyby.addObject("chybnyUdaj", "Druhé heslo není stejné! Vyplňte, prosím, znovu");
            return drzakNaDataChyby;
        }
        ModelAndView drzakNaData;
        Uzivatel novaRegistrace = new Uzivatel();
        novaRegistrace.setKrestniJmeno(vyplnenaRegistrace.getKrestniJmeno());
        novaRegistrace.setPrijmeni(vyplnenaRegistrace.getPrijmeni());
        novaRegistrace.setEmail(vyplnenaRegistrace.getEmail());
        novaRegistrace.setHeslo(vyplnenaRegistrace.getHeslo());
        seznamRegistrovanych.add(novaRegistrace);
        drzakNaData = new ModelAndView("login/registrace-uspesna");

        return drzakNaData;
    }

    @RequestMapping(value = "/kontakty/{cislo}.html", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable("cislo") Long id) {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("kontakty/detail");
        TelefonniKontakt nalezenyKontakt = najdi(id);
        drzakNaData.addObject("kontakt", nalezenyKontakt);

        return drzakNaData;
    }

    @RequestMapping(value = "/kontakty/{cislo}.html", method = RequestMethod.POST)
    public ModelAndView zpracujDetail(@PathVariable("cislo") Long id, DetailForm vyplnenyFormular) {
        TelefonniKontakt aktualniKontakt = najdi(id);
        aktualniKontakt.setJmeno(vyplnenyFormular.getJmeno());
        aktualniKontakt.setMesto(vyplnenyFormular.getMesto());
        aktualniKontakt.setStat(vyplnenyFormular.getStat());
        aktualniKontakt.setTelefon(vyplnenyFormular.getTelefon());
        
        return new ModelAndView("redirect:/kontakty/");
    }

    private TelefonniKontakt najdi(Long id) {
        for (TelefonniKontakt kontakt : seznamKontaktu) {
            if (kontakt.getId().equals(id)) {
                return kontakt;
            }
        }
        throw new IllegalArgumentException("Kontakt nenalezen");
    }

}
