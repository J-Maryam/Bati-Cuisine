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
        ClientMenu clientMenu = new ClientMenu(clientService);

        ProjetRepository projetRepository = new ProjetRepositoryImpl(connection);

        ComposantRepository materielRepository = new MaterielRepositoryImpl(connection);
        ComposantService materielService = new MaterielServiceImpl(materielRepository);

        ComposantRepository mainDOeuvreRepository = new MainDOeuvreRepositoryImpl(connection);
        ComposantService mainDOeuvreService = new MainDOeuvreServiceImpl(mainDOeuvreRepository);

        ProjetService projetService = new ProjetServiceImpl(projetRepository, materielService, mainDOeuvreService);

        MaterielMenu materielMenu = new MaterielMenu(materielService, projetService);

        MainDOeuvreMenu mainDOeuvreMenu = new MainDOeuvreMenu(mainDOeuvreService, projetService);

        DeviRepository deviRepository = new DeviRepositoryImpl(connection);
        DeviService deviService = new DeviServiceImpl(deviRepository);
        DeviMenu deviMenu = new DeviMenu(deviService, projetService);

        ProjetMenu projetMenu = new ProjetMenu(projetService, clientService, clientMenu, materielMenu, mainDOeuvreMenu, materielService, mainDOeuvreService, deviMenu);
        projetMenu.addProject();
    }

}