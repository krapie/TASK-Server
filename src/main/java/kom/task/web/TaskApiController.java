package kom.task.web;

import com.fasterxml.jackson.databind.node.TextNode;
import kom.task.service.TaskService;
import kom.task.web.dto.daydo.DaydoResponseDto;
import kom.task.web.dto.daydo.DaydoSaveRequestDto;
import kom.task.web.dto.daydo.DaydoUpdateRequestDto;
import kom.task.web.dto.pomodoro.PomodoroResponseDto;
import kom.task.web.dto.pomodoro.PomodoroUpdateRequestDto;
import kom.task.web.dto.todo.TodoResponseDto;
import kom.task.web.dto.todo.TodoSaveRequestDto;
import kom.task.web.dto.todo.TodoUpdateRequestDto;
import kom.task.web.dto.user.UserLoginResponseDto;
import kom.task.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://tasko.today","http://localhost:3000","http://localhost:3001","http://localhost:3002"})
public class TaskApiController {

    private final TaskService taskService;

    /*** LOGIN REST CONTROLLER ***/
    // Login
    @PostMapping("/api/google/tokensignin")
    public ResponseEntity<?> googleTokenLogin(@RequestBody TextNode tokenDtoString) {
        UserLoginResponseDto responseDto = taskService.googleTokenLogin(tokenDtoString.asText());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // Get User Info
    @PostMapping("/api/user")
    public ResponseEntity<?> fetchUserData(@RequestBody TextNode userIdString) {
        UserResponseDto responseDto = taskService.fetchUserInfo(userIdString.asText());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    /*** To Do REST CONTROLLER ***/
    // Create
    @PostMapping("/api/todo")
    public ResponseEntity<?> saveTodoItem(@RequestBody TodoSaveRequestDto requestDto) {
        TodoResponseDto responseDto = taskService.saveTodoItem(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // Read
    @PostMapping("/api/todos")
    public ResponseEntity<?> fetchAllTodoItems(@RequestBody TextNode userIdString) {
        List<TodoResponseDto> responseDto = taskService.fetchAllTodoItems(userIdString.asText());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // Update
    @PutMapping("/api/todo/{id}")
    public Long updateTodoItem(@PathVariable Long id, @RequestBody TodoUpdateRequestDto requestDto) {
        return taskService.updateTodoItem(id, requestDto);
    }

    // Delete
    @DeleteMapping("/api/todo/{id}")
    public Long deleteTodoItem(@PathVariable Long id) {
        return taskService.deleteTodoItem(id);
    }


    /*** Day Do REST CONTROLLER ***/
    // Create
    @PostMapping("/api/daydo")
    public ResponseEntity<?> saveDaydoItem(@RequestBody DaydoSaveRequestDto requestDto) {
        DaydoResponseDto responseDto = taskService.saveDaydoItem(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // Read
    @PostMapping("/api/daydos")
    public ResponseEntity<?> fetchAllDaydoItems(@RequestBody TextNode userIdString) {
        List<DaydoResponseDto> responseDto = taskService.fetchAllDaydoItems(userIdString.asText());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // Update
    @PutMapping("/api/daydo/{id}")
    public Long updateDaydoItem(@PathVariable Long id, @RequestBody DaydoUpdateRequestDto requestDto) {
        return taskService.updateDaydoItem(id, requestDto);
    }

    // Delete
    @DeleteMapping("/api/daydo/{id}")
    public Long deleteDaydoItem(@PathVariable Long id) {
        return taskService.deleteDaydoItem(id);
    }


    /*** Pomodoro REST Controller ***/

     // Read
     @PostMapping("/api/pomodoro")
     public ResponseEntity<?> fetchPomodoroItem(@RequestBody TextNode userIdString) {
         PomodoroResponseDto responseDto = taskService.fetchPomodoroItem(userIdString.asText());

         return ResponseEntity.status(HttpStatus.OK).body(responseDto);
     }

     // Update
     @PutMapping("/api/pomodoro/update")
     public ResponseEntity<?> updatePomodoroItem(@RequestBody PomodoroUpdateRequestDto requestDto) {

         PomodoroResponseDto updatedDto = taskService.updatePomodoroItem(requestDto);

         return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
     }
}
