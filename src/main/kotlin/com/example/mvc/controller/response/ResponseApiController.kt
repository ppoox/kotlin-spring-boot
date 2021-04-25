package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4xx
    // GET http://localhost:8080/api/response?age=10
    @GetMapping()
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {

        // 보다 코틀린 스럽게 (표준함수를 사용)
        return age?.let {
            // age not null
            if (age < 20) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 갑은 20보다 커야합니다.")
            }

            ResponseEntity.ok("OK")

        }?: kotlin.run {
            // age is null
            return ResponseEntity.badRequest().body("age 값이 누락되었습니다.")
        }

        /*

        // 1) age == null -> 400 error
        if (age == null) {
            return ResponseEntity.badRequest().body("age 값이 누락되었습니다.")
        }

        // 2) age < 20 -> 400 error
        if (age < 20) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 갑은 20보다 커야합니다.")
        }

        println(age)
        return ResponseEntity.ok("OK")

        */
    }

    // 2. post 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {

        return ResponseEntity.status(HttpStatus.OK).body(userRequest)
    }

    // 3. put 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        // 1. 기존 데이터가 없어서 새로 생성했다.

        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Nothing> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }


}