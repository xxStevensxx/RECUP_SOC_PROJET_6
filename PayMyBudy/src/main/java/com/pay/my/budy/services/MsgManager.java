package com.pay.my.budy.services;

import org.springframework.stereotype.Component;

@Component
public class MsgManager {

	public String errMsgManger(int err) {

		String msgGenerique = "";

		switch (err) {
		case 1:
			return "";

		case 2:
			return "Mot de passe obligatoire";
			
		case 3:
			return "format email incorect";
			
		case 4: 
			return "Adresse mail obligatoire";
			
		case 5:
			return "Adresse email invalide !";
			
		case 6:
			return "Adresse email existante !!";
			
		case 7:
			return "Email ou mot de passe incorrect !!";

		default:			
			break;
		}
		return msgGenerique;

	}
	

	public String logMsgManager(int log) {

		String msgGenerique = "";

		switch (log) {
		case 1:
			return "";

		case 2:
			return "Mot de passe obligatoire";
			
		case 3:
			return "checkUserInput succes!!";
			
		case 4:
			return "Information utilisateur correct";

		default:
			break;
		}
		return msgGenerique;

	}

}
