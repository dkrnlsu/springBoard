package myboard.entity;

/**
 * User: HolyEyE
 * Date: 13. 2. 27. Time: 오후 5:20
 */
public class Board {

    public Board(){}

    public Board(int id, String title, String writer, String pw, String content) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.pw = pw;
        this.content = content;
    }

    private int id;
    private String title;
    private String writer;
    private String pw;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", pw='" + pw + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
