package org.example.dao;

import org.example.Domain.MarkRecord;
import org.example.Domain.Swimmer;
import org.example.Domain.TrialSwimmer;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> extends AutoCloseable{
    List<T> findAll() throws SQLException;
    T findById(int Cod_Swimmer) throws SQLException ;
    T save(T entity) throws SQLException ;
    void delete(T entity) throws SQLException ;


}
