package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void save(UserRequest.JoinDTO joinDTO) {
        Query query = em.createNativeQuery("insert into user_tb(username, password, email, created_at) values (?,?,?, now())");
        query.setParameter(1, joinDTO.getUsername());
        query.setParameter(2, joinDTO.getPassword());
        query.setParameter(3, joinDTO.getUsername());

        query.executeUpdate();
    }
}
