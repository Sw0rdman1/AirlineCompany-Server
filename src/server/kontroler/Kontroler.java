/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this opsteIzvrsenjeSo file, choose Tools | Templates
 * and open the opsteIzvrsenjeSo in the editor.
 */
package server.kontroler;

import com.mysql.cj.util.StringUtils;
import domen.Aerodrom;
import domen.Avion;
import domen.Karta;
import domen.Korisnik;
import domen.Let;
import domen.OpstiDomenskiObjekat;
import domen.Pilot;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import server.broker.BrokerBP;
import server.forme.KonfiguracijaBazeForm;
import server.forme.KonfiguracijaServeraForm;
import server.forme.ServerskaForma;
import server.konstante.ServerskeKonstante;
import server.niti.ServerskaNit;
import server.so.OpstaSo;
import server.so.impl.CreateAirplaneSO;
import server.so.impl.CreateAirportSO;
import server.so.impl.CreatePilotSO;
import server.so.impl.CreateTicketSO;
import server.so.impl.DeleteAirplaneSO;
import server.so.impl.DeleteAirportSO;
import server.so.impl.DeletePilotSO;
import server.so.impl.FindNextIDAirplaneSO;
import server.so.impl.FindNextIDAirportSO;
import server.so.impl.FindNextIDPilotSO;
import server.so.impl.FindNextIDTicketSO;
import server.so.impl.FindSpecificAirplaneSO;
import server.so.impl.FindSpecificAirportSO;
import server.so.impl.FindSpecificFlightSO;
import server.so.impl.FindSpecificPilotSO;
import server.so.impl.LoadAllAirplanesSO;
import server.so.impl.LoadAllAirportsSO;
import server.so.impl.LoadAllPilotsSO;
import server.so.impl.LoadTicketSO;

import server.so.impl.LogInSO;
import server.so.impl.UpdateTicketSO;

/**
 *
 * @author
 */
public class Kontroler {

    private static Kontroler instanca;
    private final BrokerBP b;
    private ServerskaNit serverskaNit;
    private ServerskaForma serverForm;

    private Kontroler() {
        b = new BrokerBP();

    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void setServerForm(ServerskaForma serverForm) {
        this.serverForm = serverForm;
    }

    public void pokreniServer(ServerskaForma serverskaForma) throws IOException, Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerskeKonstante.SERVER_CONFIG_PATH));
        int port = -1;
        if (!"".equals(properties.getProperty("port"))) {
            port = Integer.parseInt(properties.getProperty("port"));
        } else {
            throw new Exception();
        }
        ServerSocket serverSocket = new ServerSocket(port);
        serverskaNit = new ServerskaNit(serverSocket);
        serverskaNit.start();

    }

    public void zaustaviServer(ServerskaForma serverskaForma) throws IOException {
        serverskaNit.zaustavi();

    }

    public void konfigurisiBazu(String url, String username, String password) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        properties.store(new FileOutputStream(ServerskeKonstante.DB_CONFIG_PATH), "");
    }

    public void procitajKonfiguracijuBaze(KonfiguracijaBazeForm kbf) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerskeKonstante.DB_CONFIG_PATH));
        kbf.getLblURL().setText(properties.getProperty("url"));
        kbf.getLblUsername().setText(properties.getProperty("username"));
        kbf.getLblPassword().setText(properties.getProperty("password"));

    }

    public void konfigurisiServer(String port) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("port", port);
        properties.store(new FileOutputStream(ServerskeKonstante.SERVER_CONFIG_PATH), port);

    }

    public void procitajKonfiguracijuServera(KonfiguracijaServeraForm ksf) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerskeKonstante.SERVER_CONFIG_PATH));
        ksf.getLblCurrentPort().setText(properties.getProperty("port"));
    }

    public void prijaviSe(Korisnik korisnik) throws Exception {
        OpstaSo so = new LogInSO(b, korisnik);
        so.opsteIzvrsenjeSo();
    }

    public void kreirajPilota(Pilot pilot) throws SQLException, Exception {
        OpstaSo so = new CreatePilotSO(b, pilot);
        so.opsteIzvrsenjeSo();
    }

    public void findNextIDPilot(Pilot pilot) throws SQLException, Exception {
        OpstaSo so = new FindNextIDPilotSO(b, pilot);
        so.opsteIzvrsenjeSo();
    }

    public List<OpstiDomenskiObjekat> loadAllPilots() throws SQLException, Exception {
        OpstaSo so = new LoadAllPilotsSO(b, new Pilot());
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public List<OpstiDomenskiObjekat> findSpecificPilots(Pilot pilot) throws Exception {
        OpstaSo so = new FindSpecificPilotSO(b, pilot);
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public void deletePilot(Pilot pilot) throws Exception {
        OpstaSo so = new DeletePilotSO(b, pilot);
        so.opsteIzvrsenjeSo();
    }

    public void kreirajAerodrom(Aerodrom aerodrom) throws SQLException, Exception {
        OpstaSo so = new CreateAirportSO(b, aerodrom);
        so.opsteIzvrsenjeSo();
    }

    public void findNextIDAerodrom(Aerodrom aerodrom) throws SQLException, Exception {
        OpstaSo so = new FindNextIDAirportSO(b, aerodrom);
        so.opsteIzvrsenjeSo();
    }

    public List<OpstiDomenskiObjekat> loadAllAirports() throws SQLException, Exception {
        OpstaSo so = new LoadAllAirportsSO(b, new Aerodrom());
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public List<OpstiDomenskiObjekat> findSpecificAirports(Aerodrom aerodrom) throws Exception {
        OpstaSo so = new FindSpecificAirportSO(b, aerodrom);
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public void deleteAirport(Aerodrom aerodrom) throws Exception {
        OpstaSo so = new DeleteAirportSO(b, aerodrom);
        so.opsteIzvrsenjeSo();
    }

    public void kreirajAvion(Avion avion) throws SQLException, Exception {
        OpstaSo so = new CreateAirplaneSO(b, avion);
        so.opsteIzvrsenjeSo();
    }

    public void findNextIDAerodrom(Avion avion) throws SQLException, Exception {
        OpstaSo so = new FindNextIDAirplaneSO(b, avion);
        so.opsteIzvrsenjeSo();
    }

    public List<OpstiDomenskiObjekat> loadAllAirplanes() throws SQLException, Exception {
        OpstaSo so = new LoadAllAirplanesSO(b, new Avion());
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public List<OpstiDomenskiObjekat> findSpecificAirplanes(Avion avion) throws Exception {
        OpstaSo so = new FindSpecificAirplaneSO(b, avion);
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public void deleteAirplane(Avion avion) throws Exception {
        OpstaSo so = new DeleteAirplaneSO(b, avion);
        so.opsteIzvrsenjeSo();
    }

    public void kreirajLet(Let let) throws SQLException, Exception {
        OpstaSo so = new CreateAirplaneSO(b, let);
        so.opsteIzvrsenjeSo();
    }

    public void findNextIDLet(Let let) throws SQLException, Exception {
        OpstaSo so = new FindNextIDAirplaneSO(b, let);
        so.opsteIzvrsenjeSo();
    }

    public List<OpstiDomenskiObjekat> loadAllFlights() throws SQLException, Exception {
        OpstaSo so = new LoadAllAirportsSO(b, new Let());
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public List<OpstiDomenskiObjekat> findSpecificFlights(Let let) throws Exception {
        OpstaSo so = new FindSpecificFlightSO(b, let);
        so.opsteIzvrsenjeSo();
        return so.getLista();
    }

    public void kreirajKartu(Karta karta) throws SQLException, Exception {
        OpstaSo so = new CreateTicketSO(b, karta);
        so.opsteIzvrsenjeSo();
    }

    public void findNextIDTicket(Karta karta) throws SQLException, Exception {
        OpstaSo so = new FindNextIDTicketSO(b, karta);
        so.opsteIzvrsenjeSo();
    }

    public void ucitajKartu(Karta karta) throws Exception {
        OpstaSo so = new LoadTicketSO(b, karta);
        so.opsteIzvrsenjeSo();
    }

    public void azurirajKartu(Karta karta) throws SQLException, Exception {
        OpstaSo so = new UpdateTicketSO(b, karta);
        so.opsteIzvrsenjeSo();
    }

}
