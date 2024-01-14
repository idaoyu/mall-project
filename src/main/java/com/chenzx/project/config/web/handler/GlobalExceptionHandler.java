package com.chenzx.project.config.web.handler;

import com.chenzx.project.common.data.R;
import com.chenzx.project.common.exception.BizException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Set;

import static com.chenzx.project.config.web.constant.WebRequestErrorCodeConstant.*;


/**
 * 系统异常处理器
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-10-29 21:35
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数校验失败异常处理器
     *
     * @param ex BindException
     * @return CommonResultDTO
     */
    @ExceptionHandler(value = BindException.class)
    public R<?> requestParamsExceptionHandler(BindException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return R.error(REQUEST_PARAMS_EXCEPTION.getErrorCode(), errorMessage);
    }

    /**
     * 参数校验失败异常处理器
     *
     * @param ex ValidationException
     * @return CommonResultDTO
     */
    @ExceptionHandler(value = ValidationException.class)
    public R<?> validationExceptionHandler(ValidationException ex) {
        if (ex instanceof ConstraintViolationException realException) {
            if (realException.getConstraintViolations() == null) {
                return R.error(REQUEST_PARAMS_EXCEPTION.getErrorCode(), realException.getMessage());
            }
            Set<ConstraintViolation<?>> constraintViolations = realException.getConstraintViolations();
            if (constraintViolations.iterator().hasNext()) {
                ConstraintViolation<?> violation = constraintViolations.iterator().next();
                return R.error(REQUEST_PARAMS_EXCEPTION.getErrorCode(), violation.getMessage());
            }
        }
        return R.error(REQUEST_PARAMS_EXCEPTION.getErrorCode(), REQUEST_PARAMS_EXCEPTION.getErrorMessage());
    }

    /**
     * 业务异常处理器
     *
     * @param ex BizException
     * @return CommonResultDTO
     */
    @ExceptionHandler(value = BizException.class)
    public R<?> bizExceptionHandler(BizException ex) {
        log.warn("业务逻辑产生异常，异常信息: {}", ex.getMessage());
        return R.error(BIZ_EXCEPTION.getErrorCode(), ex.getMessage());
    }

    /**
     * 404 异常处理器
     *
     * @param ex NoHandlerFoundException
     * @return CommonResultDTO
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public R<?> notFountExceptionHandler(NoHandlerFoundException ex) {
        String message = ex.getRequestURL() + " 404";
        return R.error(NOT_FOUNT_EXCEPTION.getErrorCode(), message);
    }

    /**
     * 默认的系统异常处理器
     *
     * @param ex Exception
     * @return CommonResultDTO
     */
    @ExceptionHandler(value = Exception.class)
    public R<?> defaultExceptionHandler(Exception ex) {
        log.info("系统出现未知异常", ex);
        return R.error(DEFAULT_EXCEPTION.getErrorCode(), DEFAULT_EXCEPTION.getErrorMessage());
    }

}
