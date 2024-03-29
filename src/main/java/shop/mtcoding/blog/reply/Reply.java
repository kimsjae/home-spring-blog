package shop.mtcoding.blog.reply;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reply_tb")
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    private int userId;
    private int boardId;
    private LocalDateTime createdAt;
}
