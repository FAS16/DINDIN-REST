package org.fahadali.dindin.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.ws.rs.Path;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import brugerautorisation.transport.rmi.Brugeradmin;

public class LoginService {
	
	private Brugeradmin brugerAdmin;

	public Brugeradmin getBrugerAdmin() {

		try {
			brugerAdmin = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		return brugerAdmin;
		
	}

}
