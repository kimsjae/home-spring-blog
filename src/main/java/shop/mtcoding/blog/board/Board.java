package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "board_tb")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String title;
    private String content;
    private int userId;
    private LocalDateTime createdAt;
}
