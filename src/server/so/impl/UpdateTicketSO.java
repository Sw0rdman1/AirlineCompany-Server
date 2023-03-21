/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.Karta;
import domen.OpstiDomenskiObjekat;
import domen.StavkaKarte;
import java.util.List;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author UrosVesic
 */
public class UpdateTicketSO extends OpstaSo {

    public UpdateTicketSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        //b.pamtiSlozeniSlog(odo);
        Karta karta = (Karta) odo;
        List<OpstiDomenskiObjekat> stavkeIzBaze = b.pronadjiSlogove(karta.getStavkeKarte().get(0));
        b.promeniSlog(odo);

        for (StavkaKarte stavka : karta.getStavkeKarte()) {
            if (stavka != null && b.daLiPostojiSlog(stavka)) {
                b.promeniSlog(stavka);
            } else {
                b.kreirajSlog(stavka);
            }
        }

        for (OpstiDomenskiObjekat stavkaIzBaze : stavkeIzBaze) {
            if (!karta.getStavkeKarte().contains(stavkaIzBaze)) {
                b.obrisiSlog(stavkaIzBaze);
            }
        }
    }

    @Override
    public void proveriPreduslove() throws Exception {

    }

}
