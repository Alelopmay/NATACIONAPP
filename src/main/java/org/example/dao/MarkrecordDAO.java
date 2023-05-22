package org.example.dao;

import org.example.Conections.ConnectionMySQL;
import org.example.Domain.MarkRecord;
import org.example.Domain.Swimmer;
import org.example.Domain.TrialSwimmer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarkrecordDAO implements DAO<MarkRecord> {

    private final static String FINDALL = "SELECT * from MarkRecord";
    private final static String FINDBYID = "SELECT * from MarkRecord WHERE ID_MarkRecord=?";
    private final static String INSERT = "INSERT INTO MarkRecord (ID_MarkRecord,Id,Cod_Swimmer,Date,Time) VALUES (?,?,?,?,?)";
    private final static String UPDATE = "UPDATE MarkRecord SET ID_Markrecord=?, ID=?, Cod_Swimmer=?, Date=?, Time=? WHERE ID_MarkRecord=?";
    private final static String FINDBYCODSWIMMER = "SELECT * FROM Swimmer WHERE Cod_swimmer=?";
    private final static String FINDBYIDANDCODSWIMMER = "SELECT * FROM MarkRecord WHERE ID=? AND Cod_swimmer=?";

    private final static String FINDBYAUTOR = "SELECT * from Markrecord WHERE ID_Markrecord=?";
    private final static String DELETE = "DELETE from Markrecord WHERE ID_Markrecord=?";
    private final static String JOIN = "SELECT s.Name AS SwimmerName, ts.Id AS TrialswimmerID, mr.Time, mr.Date " +
            "FROM swimmer s " +
            "JOIN markrecord mr ON s.Cod_swimmer = mr.Cod_swimmer " +
            "JOIN trialswimmer ts ON mr.Id = ts.Id " +
            "WHERE s.Cod_swimmer = ? AND ts.Id = ? " +
            "ORDER BY mr.Time ASC";

    private Connection conn;

    public MarkrecordDAO(Connection conn) {
        this.conn = conn;
    }

    public MarkrecordDAO() {
        this.conn = ConnectionMySQL.getConnect();
    }


    @Override
    public List<MarkRecord> findAll() throws SQLException {
        List<MarkRecord> result = new ArrayList();
        try (PreparedStatement pst = this.conn.prepareStatement(FINDALL)) {
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    MarkRecord M = new MarkRecord();
                    M.getCod_Swimmer();
                    M.getTime();


                }
            }
        }
        return result;

    }

    @Override
    public MarkRecord findById(int Cod_Swimmer) throws SQLException {
        return null;
    }

    @Override
    public MarkRecord save(MarkRecord entity) throws SQLException {
        if (entity != null) {
            // INSERT
            try (PreparedStatement pst = this.conn.prepareStatement(INSERT)) {
                pst.setInt(1, entity.getID_Marckrecord());
                pst.setInt(2, entity.getId().getId());
                pst.setInt(3, entity.getCod_Swimmer().getCod_Swimmer());
                pst.setDate(4, java.sql.Date.valueOf(entity.getDate())); // Convertir LocalDate a java.sql.Date
                pst.setString(5, entity.getTime());
                pst.executeUpdate();
            }
        } else {
            // UPDATE
            try (PreparedStatement pst = this.conn.prepareStatement(UPDATE)) {
                pst.setInt(1, entity.getID_Marckrecord());
                pst.setInt(2, entity.getId().getId());
                pst.setInt(3, entity.getCod_Swimmer().getCod_Swimmer());
                pst.setDate(4, java.sql.Date.valueOf(entity.getDate())); // Convertir LocalDate a java.sql.Date
                pst.setString(5, entity.getTime());
                pst.executeUpdate();
            }
        }
        return entity;
    }


    @Override
    public void delete(MarkRecord entity) throws SQLException {

    }





    public void update(MarkRecord entity) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(UPDATE)) {
            pst.setInt(1, entity.getID_Marckrecord());
            pst.setInt(2, entity.getId().getId());
            pst.setInt(3, entity.getCod_Swimmer().getCod_Swimmer());
            pst.setDate(4, java.sql.Date.valueOf(entity.getDate()));
            pst.setString(5, entity.getTime());
            pst.setInt(6, entity.getID_Marckrecord()); // Agrega el parámetro faltante para el WHERE

            pst.executeUpdate();
        }
    }


    @Override
    public void close() throws Exception {

    }

    public List<MarkRecord> findBySwimmerAndTrialSwimmer(int swimmerId, int trialSwimmerId) throws SQLException {
        List<MarkRecord> result = new ArrayList<>();

        try (PreparedStatement pst = this.conn.prepareStatement(JOIN)) {
            pst.setInt(1, swimmerId);
            pst.setInt(2, trialSwimmerId);
            try (ResultSet res = pst.executeQuery()) {
                while (res.next()) {
                    Swimmer swimmer = new Swimmer();
                    swimmer.setName(res.getString("SwimmerName"));

                    TrialSwimmer trialSwimmer = new TrialSwimmer();
                    trialSwimmer.setId(res.getInt("TrialswimmerID"));

                    MarkRecord markRecord = new MarkRecord();
                    markRecord.setCod_Swimmer(swimmer);
                    markRecord.setId(trialSwimmer);
                    markRecord.setTime(res.getString("Time"));
                    markRecord.setDate(res.getDate("Date").toLocalDate());

                    result.add(markRecord);
                }
            }
        }
        return result;
    }


    public Swimmer getSwimmerByCodSwimmer(int codSwimmer) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(FINDBYCODSWIMMER)) {
            pst.setInt(1, codSwimmer);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    Swimmer swimmer = new Swimmer();
                    swimmer.setCod_Swimmer(res.getInt("Cod_swimmer"));
                    swimmer.setName(res.getString("name"));
                    // Establecer más atributos si es necesario
                    return swimmer;
                }
            }
        }
        return null;
    }

    public boolean checkMarkRecordExists(Swimmer swimmer, TrialSwimmer trialSwimmer) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(FINDBYIDANDCODSWIMMER)) {
            pst.setInt(1, trialSwimmer.getId());
            pst.setInt(2, swimmer.getCod_Swimmer());
            try (ResultSet res = pst.executeQuery()) {
                return res.next();
            }
        }
    }
}
