package dev.roshin.productservicefeb24.advices;

import dev.roshin.productservicefeb24.dtos.ErrorDto;
import dev.roshin.productservicefeb24.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception){
        ErrorDto errorDto= new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        ResponseEntity<ErrorDto> responseEntity  = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
