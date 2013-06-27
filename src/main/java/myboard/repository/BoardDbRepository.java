package myboard.repository;

import myboard.entity.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: HolyEyE
 * Date: 13. 2. 27. Time: 오후 5:22
 */
@Component
public class BoardDbRepository implements BoardRepository {
    private JdbcTemplate jdbcTemplate;

    public BoardDbRepository() {

    }

    public BoardDbRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Board> getBoards() {
        return this.jdbcTemplate.query(
            "SELECT * FROM board ORDER BY id DESC",
            new Object[] { },
            new RowMapper<Board>() {
                @Override
                public Board mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    Board board = new Board();
                    board.setId(Integer.parseInt(rs.getString("id")));
                    board.setTitle(rs.getString("title"));
                    board.setWriter(rs.getString("writer"));
                    board.setPw(rs.getString("pw"));
                    board.setContent(rs.getString("content"));
                    return board;
                }

            }
        );
    }

    @Override
    public void addBoard(Board board) {
        this.jdbcTemplate.update(
                "INSERT INTO board (title, writer, pw, content) VALUES (?, ?, ?, ?)",
                new Object[] {
                        board.getTitle(),
                        board.getWriter(),
                        board.getPw(),
                        board.getContent()
                }
        );
    }

    @Override
    public Board getBoard(int id) {
        return this.jdbcTemplate.queryForObject(
                "SELECT * FROM board WHERE ID=?",
                new Object[] {id},
                new RowMapper<Board>() {
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Board board = new Board();
                        board.setId(Integer.parseInt(rs.getString("id")));
                        board.setTitle(rs.getString("title"));
                        board.setWriter(rs.getString("writer"));
                        board.setPw(rs.getString("pw"));
                        board.setContent(rs.getString("content"));
                        return board;
                    }

                }
        );
    }

    @Override
    public void updateBoard(Board updateBoard) {
        this.jdbcTemplate.update(
                "UPDATE board SET title=?, writer=?, content=? WHERE id=? and pw=?",
                new Object[] {
                        updateBoard.getTitle(),
                        updateBoard.getWriter(),
                        updateBoard.getContent(),
                        updateBoard.getId(),
                        updateBoard.getPw(),
                }
        );
    }

    @Override
    public void deleteBoard(int id, String pw) {
        this.jdbcTemplate.update(
                "DELETE FROM board WHERE id=? and pw=?",
                new Object[] {id, pw}
        );
    }
}
