package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

/**
 * 회원 관리 API
 * 회원 목록 조회: GET /users
 * 회원 등록: POST /users
 * 회원 조회: GET /users/{userId}
 * 회원 수정: PATCH /users/{userId}
 * 회원 삭제: DELETE /users/{userId}
 */

@RestController
@RequestMapping("/mapping/users") //요청 url 통합
public class MappingClassController {
    /**
     * GET /mapping/users
     */
    @GetMapping
    public String users() {
        return "get users";
    }

    /**
     * POST /mapping/users
     */
    @PostMapping //요청 방식에 따라 컨트롤러를 구분한다.
    public String addUser() {
        return "post user";
    }

    /**
     * GET /mapping/users/{userId}
     */
    @GetMapping("/{userId}") //경로변수 사용
    public String findUser(@PathVariable String userId) {
        return "get userId=" + userId;
    }

    /**
     * PATCH /mapping/users/{userId}
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    /**
     * DELETE /mapping/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }
}