package org.example.dao;

import org.example.Conections.ConnectionMySQL;
import org.example.Domain.MarkRecord;
import org.example.Domain.SEX;
import org.example.Domain.Swimmer;
import org.example.Domain.TrialSwimmer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class SwimmerDAO implements DAO<Swimmer> {

    private final static String FINDALL = "SELECT * from swimmer";
    private final static String FINBYID = "SELECT * from Swimmer WHERE Cod_Swimmer=?";
    private final static String INSERT = "INSERT INTO swimmer (Cod_Swimmer,Last_Name,Name,Category,Age,Sex) VALUES (?,?,?,?,?,?)";
    private final static String UPDATE = "UPDATE Swimmer SET Name=?, Last_Name=?, Category=?, Age=?, Sex=? WHERE Cod_Swimmer=?";
    private final static String DELETE = "DELETE FROM Swimmer WHERE Cod_Swimmer = ?";


    public static Connection conn;

    public SwimmerDAO() {
        this.conn = ConnectionMySQL.getConnect();
        if (this.conn == null) {
            throw new RuntimeException("Unable to establish connection to the database.");
        }
    }




    @Override
    public List<Swimmer> findAll() throws SQLException {
        List<Swimmer> result = new ArrayList<>();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Swimmer S = new Swimmer();
                    S.setCod_Swimmer(res.getInt("Cod_Swimmer"));
                    S.setName(res.getString("Name"));
                    S.setLast_Name(res.getString("Last_Name"));
                    S.setAge(res.getInt("Age"));
                    S.setCategory(res.getString("Category"));
                    S.setSex(SEX.valueOf(res.getString("Sex").toUpperCase()));

                    result.add(S); // Agregar el nadador a la lista result
                }
            }
        }
        return result;
    }


    @Override
    public Swimmer findById(int Cod_Swimmer) throws SQLException {
        Swimmer result =new Swimmer();
        try {
            if (conn != null) { // verifica si la conexión no es nula
                try(PreparedStatement pst= this.conn.prepareStatement(FINBYID);){
                    pst.setInt(1,Cod_Swimmer);
                    try(ResultSet res = pst.executeQuery();){
                        if(res.next()){
                            result = new Swimmer();
                            result.setCod_Swimmer(res.getInt("Cod_Swimmer"));
                            result.setName(res.getString("Name"));
                            result.setLast_Name(res.getString("Last_Name"));
                            result.setAge(res.getInt(("Age")));
                            result.setCategory(res.getString("Category"));
                           result.setSex(SEX.valueOf(res.getString("Sex")));


                        }
                    }
                }
            } else {
                throw new SQLException("La conexión a la base de datos es nula.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }



    @Override
    public Swimmer save(Swimmer entity) throws SQLException {

        Swimmer result=new Swimmer();
        if(entity!=null){
                    //INSERT
                    try(PreparedStatement pst= this.conn.prepareStatement(INSERT)){
                        pst.setInt(1, entity.getCod_Swimmer());
                        pst.setString(2, entity.getLast_Name());
                        pst.setString(3, entity.getName());
                        pst.setString(4, entity.getCategory());
                        pst.setInt(5, entity.getAge());
                        pst.setString(6, String.valueOf(entity.getSex()).toString());

                        pst.executeUpdate();
                    }
                }else{
                    try(PreparedStatement pst= this.conn.prepareStatement(UPDATE);){
                        pst.setInt(1, entity.getCod_Swimmer());
                        pst.setString(2, entity.getLast_Name());
                        pst.setString(3, entity.getName());
                        pst.setString(4, entity.getCategory());
                        pst.setInt(5, entity.getAge());
                        pst.setString(6, String.valueOf(entity.getSex()).toString());
                        pst.executeUpdate();
                    }
                }
        return entity;

        }




    @Override
    public void delete(Swimmer entity) throws SQLException {
        if(entity!=null){
            try(PreparedStatement pst=this.conn.prepareStatement(DELETE)){
                pst.setInt(1,entity.getCod_Swimmer());
                pst.executeUpdate();
            }
        }
    }

    public Swimmer update(Swimmer entity) throws SQLException {
        if (entity != null) {
            try (PreparedStatement pst = this.conn.prepareStatement(UPDATE)) {
                pst.setString(1, entity.getName());
                pst.setString(2, entity.getLast_Name());
                pst.setString(3, entity.getCategory());
                pst.setInt(4, entity.getAge());
                pst.setString(5, String.valueOf(entity.getSex()));
                pst.setInt(6, entity.getCod_Swimmer());
                pst.executeUpdate();
            }
        }
        return entity;
    }
    public List<Swimmer> search(String searchText) throws SQLException {
        List<Swimmer> result = new ArrayList<>();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDALL + " WHERE Cod_Swimmer = ? OR Name LIKE ?")) {
            pst.setString(1, searchText);
            pst.setString(2, "%" + searchText + "%");
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Swimmer S = new Swimmer();
                    S.setCod_Swimmer(res.getInt("Cod_Swimmer"));
                    S.setName(res.getString("Name"));
                    S.setLast_Name(res.getString("Last_Name"));
                    S.setAge(res.getInt("Age"));
                    S.setCategory(res.getString("Category"));
                    S.setSex(SEX.valueOf(res.getString("Sex").toUpperCase()));
                    result.add(S);
                }
            }
        }
        return result;
    }





    @Override
    public void close() throws Exception {

    }
}

