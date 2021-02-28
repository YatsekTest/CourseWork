package dao.interfaces;

import java.util.List;

public interface BaseDao<I, T> {

    void create(T t);

    List<T> findAll();

    T findById(I id);

    void updateById(I id, T t);

    void deleteById(I id);

}
