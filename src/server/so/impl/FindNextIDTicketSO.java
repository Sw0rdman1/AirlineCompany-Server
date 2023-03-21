/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.Aerodrom;
import domen.Avion;
import domen.Let;
import domen.OpstiDomenskiObjekat;
import domen.Pilot;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author Božidar
 */
public class FindNextIDTicketSO extends OpstaSo {

    public FindNextIDTicketSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        b.vratiMaxID(odo);
    }

    @Override
    protected void proveriPreduslove() throws Exception {

    }

}
