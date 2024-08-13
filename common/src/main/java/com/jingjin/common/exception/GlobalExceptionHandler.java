package com.jingjin.common.exception;

import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.ResultUtil;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLException;

/**
 * 全局异常处理器
 *
 * @author fxab
 * @date 2024/07/17
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 处理自定义异常
     *
     * @param e e
     * @return {@link BaseResult}<{@link ?}>
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResult<?> businessExceptionHandler(BusinessException e) {
        log.error("自定义异常：", e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }


    /**
     * SQL异常处理
     *
     * @param e e
     * @return {@link BaseResult}<{@link ?}>
     */
    @ExceptionHandler(SQLException.class)
    public BaseResult<?> sqlExceptionHandler(SQLException e) {
        log.error("SQLException", e);
        return ResultUtil.error(ErrorCode.DATABASE_ERROR, "数据库操作错误");
    }


    /**
     * 处理运行时异常
     *
     * @param e e
     * @return {@link BaseResult}<{@link ?}>
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResult<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常：", e);
        return ResultUtil.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

    /**
     * {@code @RequestBody} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public BaseResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        if (StringUtils.hasText(msg)) {
            return ResultUtil.error(ErrorCode.VALIDATE_FAILED, msg);
        }
        return ResultUtil.error(ErrorCode.VALIDATE_FAILED);
    }

    /**
     * {@code @PathVariable} 和 {@code @RequestParam} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public BaseResult<?> handleConstraintViolationException(ConstraintViolationException ex) {
        if (StringUtils.hasText(ex.getMessage())) {
            return ResultUtil.error(ErrorCode.VALIDATE_FAILED, ex.getMessage());
        }
        return ResultUtil.error(ErrorCode.VALIDATE_FAILED);
    }
}
