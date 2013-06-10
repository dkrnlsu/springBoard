package myboard.controller;

import myboard.entity.Board;
import myboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ojh
 * Date: 13. 6. 5
 * Time: 오후 4:33
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @RequestMapping(value = "/board/list", method=RequestMethod.GET)
    public ModelAndView boardList(HttpServletRequest request) {
        if (request.getServletContext().getAttribute("loginCount") == null) {
            request.getServletContext().setAttribute("loginCount", 0);
        }
        int loginCount = (Integer) request.getServletContext().getAttribute("loginCount");

        //1. model에서 데이터 조회
        List<Board> boards = boardRepository.getBoards();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("boardList");
        modelAndView.addObject("boards", boards);
        modelAndView.addObject("loginCount", loginCount);
        return modelAndView;
    }

    @RequestMapping(value = "/board/insertForm", method=RequestMethod.GET)
    public String insertForm(HttpServletRequest request) {
        // 로그인 체크하여 비로그인시 로그인창으로 이동
        HttpSession session = request.getSession();
        if(session.getAttribute("isLogin") == null) {
            return "redirect:/board/loginForm";
        } else {
            return "insertForm";
        }
    }

    @RequestMapping(value = "/board/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, Board board) {
        // 로그인 체크하여 비로그인시 로그인창으로 이동
        HttpSession session = request.getSession();
        if(session.getAttribute("isLogin") == null) {
            return "redirect:/board/loginForm";
        } else {
            //데이터 저장
            boardRepository.addBoard(board);

            //list로 이동
            return "redirect:/board/list";
        }
    }

    @RequestMapping(value = "/board/detail", method=RequestMethod.GET)
    public ModelAndView detail(HttpServletRequest request, @RequestParam(value="id") int id) {
        //1. model에서 데이터 조회
        Board board = boardRepository.getBoard(id);
        ModelAndView modelAndView = new ModelAndView();
        //2. request에 데이터 셋팅
        if (board == null) {
            modelAndView.setViewName("redirect:/board/list");
        } else {
            modelAndView.setViewName("detail");
            modelAndView.addObject("id", board.getId());
            modelAndView.addObject("title", board.getTitle());
            modelAndView.addObject("content", board.getContent());
            modelAndView.addObject("writer", board.getWriter());
            modelAndView.addObject("pw", board.getPw());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/board/updateForm", method=RequestMethod.GET)
    public ModelAndView  updateForm(HttpServletRequest request, @RequestParam(value="id") int id) {
        // 로그인 체크하여 비로그인시 로그인창으로 이동
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if(session.getAttribute("isLogin") == null) {
            modelAndView.setViewName("redirect:/board/loginForm");
        } else {
            //1. model에서 데이터 조회
            Board board = boardRepository.getBoard(id);

            //2. request에 데이터 셋팅
            if (board == null) {
                modelAndView.setViewName("redirect:/board/list");
            } else {
                modelAndView.setViewName("updateForm");
                modelAndView.addObject("id",board.getId());
                modelAndView.addObject("title",board.getTitle());
                modelAndView.addObject("content",board.getContent());
                modelAndView.addObject("writer",board.getWriter());
                modelAndView.addObject("pw",board.getPw());
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/board/update", method=RequestMethod.POST)
    public String update(HttpServletRequest request, Board board) {
        // 로그인 체크하여 비로그인시 로그인창으로 이동
        HttpSession session = request.getSession();
        if(session.getAttribute("isLogin") == null) {
            return "redirect:/board/loginForm";
        } else {
            //데이터 업데이트
            boardRepository.updateBoard(board);

            //list로 이동
            return "redirect:/board/list";
        }
    }

    @RequestMapping(value = "/board/delete", method=RequestMethod.POST)
    public String delete(HttpServletRequest request, @RequestParam(value="id") int id, @RequestParam(value="pw") String pw) {
        // 로그인 체크하여 비로그인시 로그인창으로 이동
        HttpSession session = request.getSession();
        if(session.getAttribute("isLogin") == null) {
            return "redirect:/board/loginForm";
        } else {
            boardRepository.deleteBoard(id, pw);

            //list로 이동
            return "redirect:/board/list";
        }
    }

    @RequestMapping(value = "/board/loginForm", method=RequestMethod.GET)
    public String loginForm() {
        return "/loginForm";
    }

    @RequestMapping(value = "/board/login", method=RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(value="id") String id,
                        @RequestParam(value="password") String password,
                        @RequestParam(value="idsave") String idsave) {
        // 디폴트 로그인 정보
        String defaultId = "ojh";
        String defaultPassword = "1111";

        // 사용자 정보와 디폴트 로그인 정보가 일치하는 경우 isLogin 세션 생성
        if (id.equals(defaultId) && password.equals(defaultPassword)) {
            HttpSession session = request.getSession();
            session.setAttribute("isLogin", true);

            //접속자수 추가
            if (request.getServletContext().getAttribute("loginCount") == null) {
                request.getServletContext().setAttribute("loginCount", 1);
            } else {
                request.getServletContext().setAttribute("loginCount", ((Integer) request.getServletContext().getAttribute("loginCount")) + 1);
            }

            // ID 저장 체크가 안된 경우
            if (idsave == null) {
                // 기존 저장된 쿠키값이 있다면 제거
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie: cookies) {
                    if (cookie.getName().equals("idsave")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
                // ID 저장 체크된 경우 - 해당 쿠키 저장
            } else {
                Cookie cookie = new Cookie("idsave", id);
                // 30일 유지
                cookie.setMaxAge(86400 * 30);
                response.addCookie(cookie);

            }
            return "redirect:/board/list";
            // 사용자 정보와 디폴트 로그인 정보가 불일치하는 경우 다시 로그인폼으로 이동
        } else {
            return "redirect:/board/loginForm";
        }
    }

    @RequestMapping(value = "/board/logout", method=RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // 세션정보 소멸
        HttpSession session = request.getSession();
        session.invalidate();

        //접속자수 빼기
        if (request.getServletContext().getAttribute("loginCount") == null || request.getServletContext().getAttribute("loginCount").equals(0)) {
            request.getServletContext().setAttribute("loginCount", 0);
        } else {
            request.getServletContext().setAttribute("loginCount", ((Integer) request.getServletContext().getAttribute("loginCount")) - 1);
        }

        //list로 이동
        return "redirect:/board/list";
    }
}
