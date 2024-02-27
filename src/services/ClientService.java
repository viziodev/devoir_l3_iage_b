package services;

import java.util.List;

import entities.Client;
import repositories.ClientRepository;

public class ClientService {
    private ClientRepository clientRepository=new ClientRepository();
   public void creerClient(Client client){
     clientRepository.insert(client);
   }

   public List<Client> listerClient(){
       return clientRepository.select();
   }
   public Client recherClientParTel(String tel){
    return clientRepository.selectByTel(tel);
   }

}
