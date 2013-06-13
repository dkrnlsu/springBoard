package myboard.validator;

import myboard.entity.Board;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: ojh
 * Date: 13. 6. 10
 * Time: 오후 5:32
 * To change this template use File | Settings | File Templates.
 */
public class BoardInsertValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Board.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Board board = (Board) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required",null, "<- title 필수입력");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "required",null, "<- content 필수입력");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "required",null, "<- writer 필수입력");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "required",null, "<- pw 필수입력");

    }
}