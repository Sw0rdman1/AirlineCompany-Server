/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.so.impl;

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
public class DeletePilotSO extends OpstaSo {

    public DeletePilotSO(BrokerBP b, OpstiDomenskiObjekat odo) {
        super(b, odo);
    }

    @Override
    public void izvrsiOperaciju() throws Exception {
        b.obrisiSlog(odo);
    }

    @Override
    public void proveriPreduslove() throws Exception {
        if (odo == null) {
            throw new ValidationException("Value of object for creating is null");
        }
        if (!(odo instanceof Pilot)) {
            throw new ValidationException("Wrong type!");
        }
        Pilot pilot = (Pilot) odo;
        Validator.startValidation().validateNotNull(pilot.getIme(), "First Name is null")
                .validateNotNull(pilot.getPrezime(), "Last Name is null")
                .validateNotNull(pilot.getBrojPasosa(), "Passport number is null")
                .validateNotNull(pilot.getMestoBroavka(), "Adress is null")
                .validateNotNull(pilot.getDatumRodjenja(), "Date of birth is null")
                .throwIfInvalide();

    }
}
