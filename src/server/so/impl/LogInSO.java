/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.Korisnik;
import domen.OpstiDomenskiObjekat;
import javax.xml.bind.ValidationException;
import server.broker.BrokerBP;
import server.so.OpstaSo;

/**
 *
 * @author Božidar
 */
public class LogInSO extends OpstaSo {

    public LogInSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    protected void izvrsiOperaciju() throws Exception {
        b.pronadjiSlog(odo);
    }

    @Override
    protected void proveriPreduslove() throws Exception {
        if (odo == null) {
            throw new ValidationException("Vrednost objekta za kreiranje null");
        }
        if (!(odo instanceof Korisnik)) {
            throw new ValidationException("Pogresan tip domenskog objekta");
        }

    }
}
