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
 * @author draskovesic
 */
public class CreateTicketSO extends OpstaSo {
    
    public CreateTicketSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }
    
    @Override
    public void izvrsiOperaciju() throws Exception {
        Karta karta = (Karta) odo;
        b.kreirajSlog(odo);
        
        for (StavkaKarte stavka : karta.getStavkeKarte()) {
            stavka.setKarta(karta);
            b.kreirajSlog(stavka);
        }
        
    }
    
    @Override
    public void proveriPreduslove() throws Exception {
        
    }
    
}
