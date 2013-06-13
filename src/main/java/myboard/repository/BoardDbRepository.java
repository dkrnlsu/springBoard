package myboard.repository;

import myboard.entity.Board;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: HolyEyE
 * Date: 13. 2. 27. Time: 오후 5:22
 */
@Component
public class BoardDbRepository implements BoardRepository {

    private static BoardDbRepository instance = new BoardDbRepository();

    public static BoardDbRepository getInstance() {
        return instance;
    }

    public BoardDbRepository() {

    }

    @Override
    public List<Board> getBoards() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Board> boards = new ArrayList<Board>();
        try {
            conn = DbConnect();
            String sql = "SELECT * FROM board ORDER BY id DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // select시에 추가해야 할 부분
            while(rs.next()) { // boolean 값을 던짐
                Board board = new Board();
                board.setId(Integer.parseInt(rs.getString("id")));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setPw(rs.getString("pw"));
                board.setContent(rs.getString("content"));
                boards.add(board);
            }
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbClose(rs, pstmt, conn);
        }
        return boards;
    }

    @Override
    public void addBoard(Board board) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnect();
            String sql = "INSERT INTO board (title, writer, pw, content) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getWriter());
            pstmt.setString(3, board.getPw());
            pstmt.setString(4, board.getContent());
            pstmt.executeUpdate();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbClose(pstmt, conn);
        }
    }

    @Override
    public Board getBoard(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Board board = new Board();
        try {
            conn = DbConnect();
            String sql = "SELECT * FROM board WHERE ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery(); // select시에 추가해야 할 부분
            while(rs.next()) { // boolean 값을 던짐
                board.setId(Integer.parseInt(rs.getString("id")));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setPw(rs.getString("pw"));
                board.setContent(rs.getString("content"));
            }
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbClose(rs, pstmt, conn);
        }
        return board;
    }

    @Override
    public void updateBoard(Board updateBoard) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnect();
            String sql = "UPDATE board SET title=?, writer=?, pw=?, content=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, updateBoard.getTitle());
            pstmt.setString(2, updateBoard.getWriter());
            pstmt.setString(3, updateBoard.getPw());
            pstmt.setString(4, updateBoard.getContent());
            pstmt.setInt(5, updateBoard.getId());
            pstmt.executeUpdate();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbClose(pstmt, conn);
        }
    }

    @Override
    public void deleteBoard(int id, String pw) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbConnect();
            String sql = "DELETE FROM board WHERE id=? and pw=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, pw);
            pstmt.executeUpdate();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            DbClose(pstmt, conn);
        }
    }

    public Connection DbConnect() {
        String url = "jdbc:postgresql://localhost:5432/BOARD";
        String usr = "board";
        String pwd = "board";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, usr, pwd);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public void DbClose(PreparedStatement pstmt, Connection conn){
        try{
            if(pstmt!=null) { pstmt.close(); }
            if(conn!=null) { conn.close(); }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void DbClose(ResultSet rs, PreparedStatement pstmt, Connection conn){
        try{
            if(rs!=null) { rs.close(); } // connect()한 순서 역순으로
            if(pstmt!=null) { pstmt.close(); }
            if(conn!=null) { conn.close(); }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
