import config.DbFunctions;
import repository.DeviRepository;
import repository.impls.*;
import repository.ClientRepository;

import repository.ComposantRepository;
import repository.ProjetRepository;
import services.DeviService;
import services.impls.*;
import services.ClientService;
import services.ComposantService;
import services.ProjetService;
import ui.*;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        DbFunctions db =DbFunctions.getInstance();
        Connection connection = db.connectToDb();

        ClientRepository clientRepository = new ClientRepositoryImpl(connection);
        ClientService clientService = new ClientServiceImpl(clientRepository);
        ClientUi clientUi = new ClientUi(clientService);

        ProjetRepository projetRepository = new ProjetRepositoryImpl(connection, clientService);

        ComposantRepository materielRepository = new MaterielRepositoryImpl(connection);
        ComposantService materielService = new MaterielServiceImpl(materielRepository);

        ComposantRepository mainDOeuvreRepository = new MainDOeuvreRepositoryImpl(connection);
        ComposantService mainDOeuvreService = new MainDOeuvreServiceImpl(mainDOeuvreRepository);

        ProjetService projetService = new ProjetServiceImpl(projetRepository, materielService, mainDOeuvreService);

        MaterielUi materielUi = new MaterielUi(materielService, projetService);

        MainDOeuvreUi mainDOeuvreUi = new MainDOeuvreUi(mainDOeuvreService, projetService);

        DeviRepository deviRepository = new DeviRepositoryImpl(connection);
        DeviService deviService = new DeviServiceImpl(deviRepository);
        DeviUi deviUi = new DeviUi(deviService, projetService);

        ProjetUi projetUi = new ProjetUi(projetService, clientService, clientUi, materielUi, mainDOeuvreUi, materielService, mainDOeuvreService, deviUi);
//        projetUi.addProject();

        projetUi.PrincipleMenu();
    }

}