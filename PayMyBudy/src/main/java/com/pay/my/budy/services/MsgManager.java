package com.pay.my.budy.services;

import org.springframework.stereotype.Component;

@Component
public class MsgManager {

	
	//Refonte a venir
	
	
	
	public String logMessage(int message) {

		switch (message) {
		case 1:
			return " Erreur !!  Veuillez réesayer plus tard";

		case 2:
			return "Adresse email invalide !";
			
		case 3:
			return "format d'email incorrect";
			
		case 4: 
			return "Adresse email obligatoire";
			
		case 5:
			return "format de mdp incorrect";
			
		case 6:
			return "Email ou mot de passe incorrect !!";
			
		case 7:
			return "Information utilisateur correct";
			
		case 8:
			return "Utilisateur non trouvé !";
			
		case 9:
			return "Utilisateur éxistant !";

		default:			
			break;
		}
		
				return null;

	}

}
