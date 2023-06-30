package repositories;

import java.util.List;

public interface ITypeRepositories<E> {
    List<E> findAll();
}
