package com.lintang.miniproject.Exception;

import com.lintang.miniproject.Response.WebResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<WebResponse <Object>> handleRunTime(RuntimeException e){
        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                WebResponse.<Object>builder()
                        .status("Error")
                        .message(e.getMessage())
                        .data(null)
                        .build()
        );

    }
    //    Menangkap Error Jika Input @Valid Tidak Sesuai
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <WebResponse<List<String>>>handleValidation(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
       WebResponse.<List<String>>builder()
                .status("Bad Request")
                .message("Input Tidak Valid Bang")
                .data(null)
                .build()
        );

    }

//    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
//    public WebResponse<String> handleOptimisLocking(OptimisticLockingFailureException e){
//        return WebResponse.<String>builder()
//                .status("Conflict")
//                .message("Gagal Menyimpan Data Telah Di Ubah Oleh Pengguna Lain. Silahkan Muat Ulang Data")
//                .build();
//    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public WebResponse<String>handleValidationUnique(DataIntegrityViolationException e){
//        return WebResponse.<String>builder()
//                .status("Bad Request")
//                .message("Kode Unique Tidak Boleh Sama")
//                .build();
//    }
}
