package com.luv2code.booksusers.dao;

        import com.luv2code.booksusers.entity.Books;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Repository;
        import org.springframework.transaction.annotation.Transactional;

        import javax.persistence.EntityManager;
        import javax.persistence.Query;
        import java.util.List;
        import java.util.Optional;

@Repository
public class BooksDAOImpl implements BooksDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Books> findAll() {
        Query query = entityManager.createQuery("from Books");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Books findById(int id) {
       // Books product = entityManager.find(Books.class,id);
        return entityManager.find(Books.class,id);
    }

    @Override
    @Transactional
    public void save(Books product) {
        Books dbProduct = entityManager.merge(product);
        product.setId(dbProduct.getId());
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Books where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public List<Books> searchBy(String theName) {
       return null;
    }

}

