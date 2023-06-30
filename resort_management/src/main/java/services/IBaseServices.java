package services;

import java.util.List;
import java.util.Map;

public interface IBaseServices<E> {
    List<E> findByCondition(String id, int index);
    List<E> findAll(int i);
    E findById(String id);
    Map<String,String> create(E e);
    Map<String,String> update(E e);
    void delete(String id);
    int maxPages();

}
