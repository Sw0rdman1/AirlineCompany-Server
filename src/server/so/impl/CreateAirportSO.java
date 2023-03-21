/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

import domen.Aerodrom;
import domen.OpstiDomenskiObjekat;
import domen.Pilot;
import server.broker.BrokerBP;
import server.so.OpstaSo;
import validator.ValidationException;
import validator.Validator;

/**
 *
 * @author draskovesic
 */
public class CreateAirportSO extends OpstaSo {

    public CreateAirportSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        b.kreirajSlog(odo);
    }

    @Override
    public void proveriPreduslove() throws Exception {
        if (odo == null) {
            throw new ValidationException("Value of object for creating is null");
        }
        if (!(odo instanceof Aerodrom)) {
            throw new ValidationException("Wrong type!");
        }
        Aerodrom aerodrom = (Aerodrom) odo;
        Validator.startValidation().validateNotNull(aerodrom.getImeAerodroma(), "Name is null")
                .validateNotNull(aerodrom.getGrad(), "City is null")
                .throwIfInvalide();

    }

}
