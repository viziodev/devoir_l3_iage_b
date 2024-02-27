package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Client;

public class ClientRepository extends Database {
    private final String SQL_INSERT="INSERT INTO `client` ( `nomComplet_client`, `telephone_client`, `email_client`) VALUES (?,?,?)";
    private final String SQL_SELECT="select * from client";
    private final String SQL_SELECT_TEL="select * from client where telephone_client like ? ";

    public void insert(Client client){
        ouvrirConnexion();
        initPrepareStatement(SQL_INSERT);
        try {
            statement.setString(1, client.getNomComplet());
            statement.setString(2, client.getTelephone());
            statement.setString(3, client.getEmail());
            executeUpdate();
            fermerConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }

  public List<Client> select() {
    List<Client>clients=new ArrayList<>();
    ouvrirConnexion();
    initPrepareStatement(SQL_SELECT);
    
    ResultSet rs = executeSelect();
      try {
        while (rs.next()) {
             Client client=new Client();
             client.setId(rs.getInt("id_client"));
             client.setNomComplet(rs.getString("nomComplet_client"));
             client.setNomComplet(rs.getString("nomComplet_client"));
             client.setTelephone(rs.getString("telephone_client"));
             client.setEmail(rs.getString("email_client"));
             clients.add(client);
          }
        rs.close();
        fermerConnexion();
    } catch (SQLException e) {
        e.printStackTrace();
    }
       return clients;
  }

  public Client selectByTel(String tel) {
    Client client=null;

      try {
        ouvrirConnexion();
        initPrepareStatement(SQL_SELECT_TEL);
        statement.setString(1, tel);
        ResultSet rs = executeSelect();
        while (rs.next()) {
             client=new Client();
             client.setId(rs.getInt("id_client"));
             client.setNomComplet(rs.getString("nomComplet_client"));
             client.setNomComplet(rs.getString("nomComplet_client"));
             client.setTelephone(rs.getString("telephone_client"));
             client.setEmail(rs.getString("email_client"));
          
          }
        rs.close();
        fermerConnexion();
    } catch (SQLException e) {
        e.printStackTrace();
    }
       return client;
  }
}
