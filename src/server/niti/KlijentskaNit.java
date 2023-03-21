/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.niti;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import communication.Odgovor;
import communication.Operacije;
import communication.Posiljalac;
import communication.Primalac;
import communication.Zahtev;
import domen.Aerodrom;
import domen.Avion;
import domen.Karta;
import domen.Let;
import domen.Pilot;
import server.kontroler.Kontroler;

/**
 *
 * @author UrosVesic
 */
public class KlijentskaNit extends Thread {

    Socket socket;
    ServerskaNit serverskaNit;
    Korisnik trenutKorisnik;

    public KlijentskaNit(Socket socket, ServerskaNit serverskaNit) {
        this.socket = socket;
        this.serverskaNit = serverskaNit;
    }

    public Korisnik getTrenutKorisnik() {
        return trenutKorisnik;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                Zahtev zahtev = (Zahtev) new Primalac(socket).primi();
                obradiZahtev(zahtev);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        serverskaNit.obavestiOOdjavljivanju(this);
    }

    void zaustavi() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void obradiZahtev(Zahtev zahtev) {
        Odgovor odgovor = new Odgovor();
        switch (zahtev.getOperacija()) {

            case Operacije.PRIJAVI_SE:
                odgovor = logIn(zahtev);
                break;

            case Operacije.KREIRAJ_PILOTA:
                odgovor = createPilot(zahtev);
                break;

            case Operacije.UCITAJ_ID_PILOTA:
                odgovor
                        = findNewIDPilot(zahtev);
                break;
            case Operacije.UCITAJ_SVE_PILOTE:
                odgovor = loadAllPilots(zahtev);
                break;
            case Operacije.PRONADJI_PILOTE:
                odgovor = findSpecificPilots(zahtev);
                break;

            case Operacije.IZBRISI_PILOTA:
                odgovor = deletePilot(zahtev);
                break;

            case Operacije.KREIRAJ_AERODROM:
                odgovor = createAirport(zahtev);
                break;

            case Operacije.UCITAJ_ID_AERODROMA:
                odgovor = findNewIDAirport(zahtev);
                break;

            case Operacije.UCITAJ_SVE_AERODROME:
                odgovor = loadAllAirports(zahtev);
                break;

            case Operacije.PRONADJI_AERODROME:
                odgovor = findSpecificAirports(zahtev);
                break;

            case Operacije.IZBRISI_AERODROM:
                odgovor = deleteAirport(zahtev);
                break;

            case Operacije.KREIRAJ_AVION:
                odgovor = createAirplane(zahtev);
                break;

            case Operacije.UCITAJ_ID_AVION:
                odgovor = findNewIDAirplane(zahtev);
                break;

            case Operacije.UCITAJ_SVE_AVIONE:
                odgovor = loadAllAirplanes(zahtev);
                break;

            case Operacije.PRONADJI_AVIONE:
                odgovor = findSpecificAirplanes(zahtev);
                break;

            case Operacije.IZBRISI_AVION:
                odgovor = deleteAirplane(zahtev);
                break;

            case Operacije.KREIRAJ_LET:
                odgovor = createFlight(zahtev);
                break;

            case Operacije.UCITAJ_ID_LET:
                odgovor = findNewIDFlight(zahtev);
                break;

            case Operacije.UCITAJ_SVE_LETOVE:
                odgovor = loadAllFlights(zahtev);
                break;

            case Operacije.PRONADJI_LETOVE:
                odgovor = findSpecificFlights(zahtev);
                break;

            case Operacije.KREIRAJ_KARTU:
                odgovor = createTicket(zahtev);
                break;

            case Operacije.UCITAJ_ID_KARTA:
                odgovor = findNewIDTicket(zahtev);
                break;

            case Operacije.PRONADJI_KARTU:
                odgovor = findTicket(zahtev);
                break;
            case Operacije.AZURIRAJ_KARTU:
                odgovor = updateTicket(zahtev);
                break;

            default:
                break;
        }

        new Posiljalac(socket).posalji(odgovor);
    }

    private Odgovor logIn(Zahtev zahtev) {
        Korisnik korisnik = (Korisnik) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().prijaviSe(korisnik);
            odgovor.setIzvrsenaOperacija(Operacije.PRIJAVI_SE);
            odgovor.setRezultat(korisnik);
            trenutKorisnik = korisnik;
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor createPilot(Zahtev zahtev) {
        Pilot pilot = (Pilot) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajPilota(pilot);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_PILOTA);
            odgovor.setRezultat(pilot);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_PILOTA);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;

    }

    private Odgovor findNewIDPilot(Zahtev zahtev) {
        Pilot pilot = (Pilot) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().findNextIDPilot(pilot);
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_PILOTA);
            odgovor.setRezultat(pilot);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_PILOTA);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor loadAllPilots(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista;
        Odgovor odgovor = new Odgovor();

        try {
            lista = Kontroler.getInstanca().loadAllPilots();
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_SVE_PILOTE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findSpecificPilots(Zahtev zahtev) {
        Pilot pilot = (Pilot) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            List<OpstiDomenskiObjekat> lista = Kontroler.getInstanca().findSpecificPilots(pilot);
            odgovor.setIzvrsenaOperacija(Operacije.PRONADJI_PILOTE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor deletePilot(Zahtev zahtev) {
        Pilot pilot = (Pilot) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().deletePilot(pilot);
            odgovor.setIzvrsenaOperacija(Operacije.IZBRISI_PILOTA);
            odgovor.setRezultat(null);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor createAirport(Zahtev zahtev) {
        Aerodrom aerodrom = (Aerodrom) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajAerodrom(aerodrom);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_AERODROM);
            odgovor.setRezultat(aerodrom);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_AERODROM);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findNewIDAirport(Zahtev zahtev) {
        Aerodrom aerodrom = (Aerodrom) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().findNextIDAerodrom(aerodrom);
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_AERODROMA);
            odgovor.setRezultat(aerodrom);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_AERODROMA);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor loadAllAirports(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista;
        Odgovor odgovor = new Odgovor();

        try {
            lista = Kontroler.getInstanca().loadAllAirports();
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_SVE_AERODROME);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findSpecificAirports(Zahtev zahtev) {
        Aerodrom aerodrom = (Aerodrom) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            List<OpstiDomenskiObjekat> lista = Kontroler.getInstanca().findSpecificAirports(aerodrom);
            odgovor.setIzvrsenaOperacija(Operacije.PRONADJI_AERODROME);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor deleteAirport(Zahtev zahtev) {
        Aerodrom aerodrom = (Aerodrom) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().deleteAirport(aerodrom);
            odgovor.setIzvrsenaOperacija(Operacije.IZBRISI_AERODROM);
            odgovor.setRezultat(null);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findNewIDAirplane(Zahtev zahtev) {
        Avion avion = (Avion) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().findNextIDAerodrom(avion);
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_AVION);
            odgovor.setRezultat(avion);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_AVION);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor createAirplane(Zahtev zahtev) {
        Avion avion = (Avion) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajAvion(avion);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_AVION);
            odgovor.setRezultat(avion);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_AVION);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor loadAllAirplanes(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista;
        Odgovor odgovor = new Odgovor();

        try {
            lista = Kontroler.getInstanca().loadAllAirplanes();
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_SVE_AVIONE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findSpecificAirplanes(Zahtev zahtev) {
        Avion avion = (Avion) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            List<OpstiDomenskiObjekat> lista = Kontroler.getInstanca().findSpecificAirplanes(avion);
            odgovor.setIzvrsenaOperacija(Operacije.PRONADJI_AVIONE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor deleteAirplane(Zahtev zahtev) {
        Avion avion = (Avion) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().deleteAirplane(avion);
            odgovor.setIzvrsenaOperacija(Operacije.IZBRISI_AVION);
            odgovor.setRezultat(null);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor createFlight(Zahtev zahtev) {
        Let let = (Let) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajLet(let);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_LET);
            odgovor.setRezultat(let);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_LET);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findNewIDFlight(Zahtev zahtev) {
        Let let = (Let) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().findNextIDLet(let);
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_LET);
            odgovor.setRezultat(let);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_LET);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor loadAllFlights(Zahtev zahtev) {
        List<OpstiDomenskiObjekat> lista;
        Odgovor odgovor = new Odgovor();

        try {
            lista = Kontroler.getInstanca().loadAllFlights();
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_SVE_LETOVE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findSpecificFlights(Zahtev zahtev) {
        Let let = (Let) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            List<OpstiDomenskiObjekat> lista = Kontroler.getInstanca().findSpecificFlights(let);
            odgovor.setIzvrsenaOperacija(Operacije.PRONADJI_LETOVE);
            odgovor.setRezultat(lista);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor createTicket(Zahtev zahtev) {
        Karta karta = (Karta) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().kreirajKartu(karta);
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_KARTU);
            odgovor.setRezultat(karta);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.KREIRAJ_KARTU);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findNewIDTicket(Zahtev zahtev) {
        Karta karta = (Karta) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().findNextIDTicket(karta);
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_KARTA);
            odgovor.setRezultat(karta);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setIzvrsenaOperacija(Operacije.UCITAJ_ID_KARTA);
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor findTicket(Zahtev zahtev) {
        Karta karta = (Karta) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstanca().ucitajKartu(karta);
            odgovor.setIzvrsenaOperacija(Operacije.PRONADJI_KARTU);
            odgovor.setRezultat(karta);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor updateTicket(Zahtev zahtev) {
        Karta karta = (Karta) zahtev.getParametar();
        Odgovor odgovor = new Odgovor();

        try {
            Kontroler.getInstanca().azurirajKartu(karta);
            odgovor.setIzvrsenaOperacija(Operacije.AZURIRAJ_KARTU);
            odgovor.setUspesno(true);
        } catch (Exception ex) {
            odgovor.setUspesno(false);
            odgovor.setException(ex);
        }
        return odgovor;
    }

}
