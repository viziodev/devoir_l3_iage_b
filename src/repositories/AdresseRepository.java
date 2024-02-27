package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Adresse;
import entities.Client;

public class AdresseRepository extends Database {
    private final String SQL_INSERT="INSERT INTO `adresse` ( `ville`, `quartier`, `numVilla`, `client_id`) VALUES (?,?,?,?)";
    private final String SQL_SELECT="SELECT * FROM `adresse` a, client cl WHERE a.client_id=cl.id_client";
     public void insert(Adresse adresse){
       ouvrirConnexion();
        initPrepareStatement(SQL_INSERT);
        try {
            statement.setString(1, adresse.getVille());
            statement.setString(2, adresse.getQuartier());
            statement.setString(3, adresse.getNumVilla());
            statement.setInt(4, adresse.getClient().getId());
            executeUpdate();
            fermerConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
  }

  public List<Adresse> select(){
       List<Adresse>adresses=new ArrayList<>();
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
              Adresse adresse=new Adresse();
              adresse.setId(rs.getInt("adresse_id"));
              adresse.setQuartier(rs.getString("quartier"));
              adresse.setVille(rs.getString("ville"));
              adresse.setNumVilla(rs.getString("numVilla"));
              adresse.setClient(client);
              adresses.add(adresse);
          }
        rs.close();
        fermerConnexion();
    } catch (SQLException e) {
        e.printStackTrace();
    }
       return adresses;
  } 
}
