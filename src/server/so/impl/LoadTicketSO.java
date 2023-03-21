/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.Karta;
import domen.OpstiDomenskiObjekat;
import domen.StavkaKarte;
import java.util.ArrayList;
import java.util.List;
import server.broker.BrokerBP;
import server.so.OpstaSo;

/**
 *
 * @author draskovesic
 */
public class LoadTicketSO extends OpstaSo {

    public LoadTicketSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        //b.pronadjiSlozenSlog(odo);
        b.pronadjiSlog(odo);
        Karta karta = (Karta) odo;
        StavkaKarte stavka = new StavkaKarte();
        stavka.setKarta(karta);
        try {
            List<OpstiDomenskiObjekat> listaStavkiOdo = b.pronadjiSlogove(stavka);
            List<StavkaKarte> stavkeKarte = new ArrayList<>();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStavkiOdo) {
                stavkeKarte.add((StavkaKarte) opstiDomenskiObjekat);
                System.out.println("Boza");
            }
            karta.setStavkeKarte(stavkeKarte);
        } catch (Exception exception) {
        }

    }

    @Override
    public void proveriPreduslove() throws Exception {
    }

}
