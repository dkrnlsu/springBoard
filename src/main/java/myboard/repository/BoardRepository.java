package myboard.repository;

import myboard.entity.Board;

import java.util.List;

/**
 * User: HolyEyE
 * Date: 13. 2. 27. Time: 오후 5:22
 */
public interface BoardRepository {

    List<Board> getBoards();

    void addBoard(Board board);

    Board getBoard(int id);

    void updateBoard(Board board);

    void deleteBoard(int id, String pw);
}
