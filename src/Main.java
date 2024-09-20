import config.DbFunctions;
import dao.impls.ClientDao;
import dao.impls.MaterielDao;
import dao.impls.ProjetDao;
import dao.interfaces.IClientDao;

import dao.interfaces.IComposantDao;
import dao.interfaces.IProjetDao;
import services.impls.ClientService;
import services.impls.MaterielService;
import services.impls.ProjetService;
import services.interfaces.IClientService;
import services.interfaces.IComposantService;
import services.interfaces.IProjetService;
import ui.ClientMenu;
import ui.MaterielMenu;
import ui.ProjetMenu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        DbFunctions db =DbFunctions.getInstance();
        Connection connection = db.connectToDb();

        IClientDao clientDao = new ClientDao(connection);
        IClientService clientService = new ClientService(clientDao);

        ClientMenu clientMenu = new ClientMenu(clientService);
//        clientMenu.searchClientByName();

        IProjetDao projetDao = new ProjetDao(connection);
        IProjetService projetService = new ProjetService(projetDao);

        IComposantDao composantDao = new MaterielDao(connection);
        IComposantService composantService = new MaterielService(composantDao);
        MaterielMenu materielMenu = new MaterielMenu(composantService, projetService);
//        materielMenu.addMateriel();


        ProjetMenu projetMenu = new ProjetMenu(projetService, clientService, clientMenu, materielMenu);
        projetMenu.addProject();
    }

}