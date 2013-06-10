package myboard.repository;

import myboard.entity.Board;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: HolyEyE
 * Date: 13. 2. 27. Time: 오후 5:22
 */
@Component
public class BoardMemoryRepository implements BoardRepository {

    private static int keyStore = 0;

    private static BoardMemoryRepository instance = new BoardMemoryRepository();

    private List<Board> boards = new ArrayList<Board>();

    public static BoardMemoryRepository getInstance() {
        return instance;
    }

    public BoardMemoryRepository() {

        Board board = new Board();
        board.setId(generateId());
        board.setTitle("title");
        board.setWriter("작성자");
        board.setContent("좋은 글 입니다.");
        board.setPw("pw");

        boards.add(board);
        boards.add(new Board(generateId(), "title2","작성자2","pw","content입니다."));
    }

    @Override
    public List<Board> getBoards() {
        return boards;
    }

    @Override
    public void addBoard(Board board) {

        int newId = generateId();
        board.setId(newId);
        boards.add(board);
    }

    @Override
    public Board getBoard(int id) {
        for (Board board: boards) {
            if (board.getId() == id) {
                return board;
            }
        }
        return null;
    }

    private synchronized int generateId() {
        return keyStore++;
    }


    @Override
    public void updateBoard(Board updateBoard) {
        for (Board board: boards) {
            // id와 pw가 일치하는 경우만 업데이트
            if (board.getId() == updateBoard.getId() && board.getPw().equals(updateBoard.getPw())) {
                board.setTitle(updateBoard.getTitle());
                board.setContent(updateBoard.getContent());
                board.setWriter(updateBoard.getWriter());
                break;
            }
        }
    }

    @Override
    public void deleteBoard(int id, String pw) {
        for (Board board: boards) {
            // id와 pw가 일치하는 경우만 업데이트
            if (board.getId() == id  && board.getPw().equals(pw)) {
                boards.remove(board);
                break;
            }
        }
    }
}