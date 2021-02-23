package com.tr.task.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tr.task.dto.PagedResultDto;
import com.tr.task.dto.TaskDto;
import com.tr.task.dto.TaskUpdateDto;
import com.tr.task.enums.TaskStatus;
import com.tr.task.service.TaskService;

@RestController
@RequestMapping(path = "/api/v1/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping(path = "/all")
	public ResponseEntity<PagedResultDto<TaskDto>> getAllTask(
			@Valid @RequestParam(name = "page", defaultValue = "1") @Min(value = 1) Integer page,
			@RequestParam(name = "size", defaultValue = "25") Integer size) {
		return ResponseEntity.ok(this.taskService.getAllTasks(page - 1, size));
	}

	@GetMapping(path = "/getTasksByStatus")
	public ResponseEntity<PagedResultDto<TaskDto>> getTasksByStatus(
			@Valid @RequestParam(name = "page", defaultValue = "1") @Min(value = 1) Integer page,
			@RequestParam(name = "size", defaultValue = "25") Integer size,
			@RequestParam(name = "status", required = true) TaskStatus taskStatus) {
		return ResponseEntity.ok(this.taskService.getTasksByTaskStatus(taskStatus, page - 1, size));
	}

	@GetMapping(path = "/getTasksByAssigneeId")
	public ResponseEntity<PagedResultDto<TaskDto>> getTasksByStatus(
			@Valid @RequestParam(name = "page", defaultValue = "1") @Min(value = 1) Integer page,
			@RequestParam(name = "size", defaultValue = "25") Integer size,
			@RequestParam(name = "id", required = true) Long assigneeId) {
		return ResponseEntity.ok(this.taskService.getTasksByAssigneeId(assigneeId, page - 1, size));
	}

	@PostMapping(path = "/register")
	public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto dto) {
		return ResponseEntity.ok(this.taskService.save(dto));
	}

	@PutMapping(path = "/edit/{id}")
	public ResponseEntity<TaskUpdateDto> createTask(@PathVariable(name = "id", required = true) Long id,
			@RequestBody TaskUpdateDto task) {
		return ResponseEntity.ok(this.taskService.updateTask(id, task));
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Boolean> createTask(@PathVariable(name = "id", required = true) Long id) {
		return ResponseEntity.ok(this.taskService.deleteTaskById(id));
	}

}
