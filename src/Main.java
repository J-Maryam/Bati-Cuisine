import config.DbFunctions;
import dao.impls.ClientDao;
import dao.interfaces.IClientDao;

import services.impls.ClientService;
import services.interfaces.IClientService;
import ui.ClientMenu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        DbFunctions db =DbFunctions.getInstance();
        Connection connection = db.connectToDb();

        IClientDao clientDao = new ClientDao(connection);
        IClientService clientService = new ClientService(clientDao);

        ClientMenu clientMenu = new ClientMenu(clientService);
        clientMenu.updateClient();
    }
}