import java.util.List;
import java.util.Scanner;

import entities.Adresse;
import entities.Client;
import services.AdresseService;
import services.ClientService;

public class App {
    public static void main(String[] args) throws Exception {
       
        Scanner scanner=new Scanner(System.in);
        ClientService clientService=new ClientService();
        AdresseService adresseService=new AdresseService();
        int choix;
        do{
           System.out.println("1-Creer Client");
           System.out.println("2-Lister les  Clients");
           System.out.println("3-Creer Adresse");
           System.out.println("4-Lister les Adresse");
           System.out.println("5-Quitter");
           choix=scanner.nextInt();
           scanner.nextLine();
           switch (choix) {
             case 1:
              Client client=new Client();
              System.out.println("Nom et Prenom");
              client.setNomComplet(scanner.nextLine());  
              System.out.println("Telephone");
              client.setTelephone(scanner.nextLine());  
              System.out.println("Email");
              client.setEmail(scanner.nextLine()); 
              clientService.creerClient(client); 
                 break;

            case 2:
            List<Client> clients = clientService.listerClient();
             for (Client cl : clients) {
               System.out.println("Nom et Prenom :"+cl.getNomComplet()); 
               System.out.println("Telephone :"+cl.getTelephone());
               System.out.println("Email :"+cl.getEmail());  
               System.out.println("------------------------------------");
             }
               break;
            case 3:
              System.out.println("Telephone");
              String tel=scanner.nextLine();
              client= clientService.recherClientParTel(tel);
              if (client==null) {
                   System.out.println("Erreur de Telephone");
              } else {
                Adresse adresse=new Adresse();
                System.out.println("Ville");  
                adresse.setVille(scanner.nextLine());
                System.out.println("Quartier"); 
                adresse.setQuartier(scanner.nextLine()); 
                System.out.println("Numero Villa"); 
                adresse.setNumVilla(scanner.nextLine());
                adresse.setClient(client);
                adresseService.creerAdresse(adresse);
              }
              break;
              case 4:
              List<Adresse> adresses = adresseService.listerAdresses();
               for (Adresse adresse : adresses) {
                System.out.println("Client "+adresse.getClient().getNomComplet()); 
                System.out.println("Ville "+adresse.getVille()); 
                System.out.println("Quartier "+adresse.getQuartier()); 
                System.out.println("Numero Villa "+adresse.getNumVilla());
                System.out.println("------------------------------------"); 
               }
            }
        }while(choix!=5);

        scanner.close();

    }
}
