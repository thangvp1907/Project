package repositories;

import java.util.List;

public interface IBaseRepositories<E> {

    List<E> findByCondition(String id);
    List<E> findAll(int index);
    void create(E e);
    void delete(String id);
    void update(E e);
    int countItem();

    List<E> findByCondition(String id, int index);
}
