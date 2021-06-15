package at.htlvillach.dal.dao;

import java.util.List;

//data access object interface
public interface Dao<T> {
    List<T> getAll();
    T getById( int id );
    boolean insert (T item);
    boolean delete(T item);
    boolean update (T item);
}
