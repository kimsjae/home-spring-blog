package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);

        return query.getResultList();
    }

    public BoardResponse.DetailDTO findById(int idx) {
        Query query = em.createNativeQuery("select b.id, b.title, b.content, b.user_id, u.username from board_tb b inner join user_tb u on b.user_id = u.id where b.id = ?");
        query.setParameter(1, idx);

        Object[] row = (Object[]) query.getSingleResult();

        Integer id = (Integer) row[0];
        String title = (String) row[1];
        String content = (String) row[2];
        Integer userId = (Integer) row[3];
        String username = (String) row[4];

        BoardResponse.DetailDTO detailDTO = new BoardResponse.DetailDTO();
        detailDTO.setId(id);
        detailDTO.setTitle(title);
        detailDTO.setContent(content);
        detailDTO.setUserId(userId);
        detailDTO.setUsername(username);

        return detailDTO;
    }

    @Transactional
    public void save(BoardRequest.SaveDTO saveDTO, int userId) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, user_id, created_at) values(?,?,?, now())");
        query.setParameter(1, saveDTO.getTitle());
        query.setParameter(2, saveDTO.getContent());
        query.setParameter(3, userId);

        query.executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void update(BoardRequest.UpdateDTO updateDTO, int id) {
        Query query = em.createNativeQuery("update board_tb set title=?, content=? where id = ?");
        query.setParameter(1, updateDTO.getTitle());
        query.setParameter(2, updateDTO.getContent());
        query.setParameter(3, id);

        query.executeUpdate();
    }
}
