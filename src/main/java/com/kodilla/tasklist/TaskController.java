package com.kodilla.tasklist;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@Validated
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @GetMapping("/{priority}")
    public ResponseEntity<List<TaskDto>> getFiltered(@PathVariable @Range int priority) {
    List<TaskDto> dtos = TaskRepository.getRepository()
    .stream()
    .filter(task -> task.getPriority() == priority)
    .collect(Collectors.toList());
    return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskDto taskDto) {
    return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Map<String, String>> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException exc) {
    Map<String, String> errorsMap = new HashMap<>();
    List<ObjectError> errorsList = exc.getBindingResult().getAllErrors();
    errorsList.forEach((errorObject) -> {
    FieldError fieldError = (FieldError) errorObject;
    String name = fieldError.getField();
    String message = errorObject.getDefaultMessage();
    errorsMap.put(name, message);
    });
    return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Map<String, String>> handleException(ConstraintViolationException exc) {
    Map<String, String> resultMap = new HashMap<>();
    String[] errorArray = exc.getMessage().split(":");
    resultMap.put(errorArray[0], errorArray[1]);
    return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
    }
}