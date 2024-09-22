import config.DbFunctions;
import repository.impls.ClientRepositoryImpl;
import repository.impls.MainDOeuvreRepositoryImpl;
import repository.impls.MaterielRepositoryImpl;
import repository.impls.ProjetRepositoryImpl;
import repository.ClientRepository;

import repository.ComposantRepository;
import repository.ProjetRepository;
import services.impls.ClientServiceImpl;
import services.impls.MainDOeuvreServiceImpl;
import services.impls.MaterielServiceImpl;
import services.ClientService;
import services.ComposantService;
import services.ProjetService;
import services.impls.ProjetServiceImpl;
import ui.ClientMenu;
import ui.MainDOeuvreMenu;
import ui.MaterielMenu;
import ui.ProjetMenu;

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

        ProjetMenu projetMenu = new ProjetMenu(projetService, clientService, clientMenu, materielMenu, mainDOeuvreMenu, materielService, mainDOeuvreService);
        projetMenu.addProject();
    }

}