package com.lintang.miniproject.Exception;

import com.lintang.miniproject.Response.WebResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //    Menangkap Error Jika ID Tidak Di Temukan
    @ExceptionHandler(RuntimeException.class)
    public WebResponse <String> handleNotFound(RuntimeException e){
        e.printStackTrace();

        return WebResponse.<String> builder()
                .status("Not Found")
                .message(e.getMessage())
                .build();
    }
    //    Menangkap Error Jika Input @Valid Tidak Sesuai
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse<List<String>> handleValidation(MethodArgumentNotValidException e){

        List<String> errorMessages = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();

        return WebResponse.<List<String>>builder()
                .status("Bad Request")
                .message("Input Tidak Valid Bang")
                .data(errorMessages)
                .build();
    }

//    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
//    public WebResponse<String> handleOptimisLocking(OptimisticLockingFailureException e){
//        return WebResponse.<String>builder()
//                .status("Conflict")
//                .message("Gagal Menyimpan Data Telah Di Ubah Oleh Pengguna Lain. Silahkan Muat Ulang Data")
//                .build();
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public WebResponse<String>handleValidationUnique(DataIntegrityViolationException e){
        return WebResponse.<String>builder()
                .status("Bad Request")
                .message("Kode Sku Tidak Boleh Sama")
                .build();
    }
}
