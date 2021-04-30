package com.shop.springboot.restController;

import com.shop.springboot.dto.MeDto.MeRequestDto;
import com.shop.springboot.dto.updatePasswordDto.UpdatePasswordRequestDto;
import com.shop.springboot.entity.User;
import com.shop.springboot.exception.ResourceNotFoundException;
import com.shop.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@RequestMapping
public class UserRestController {

    private final UserService userService;

    //  회원 중복 확인
    @GetMapping("/users/duplicateCheck")
    public ResponseEntity<?> duplicateCheck(@RequestParam String email) {

        return ResponseEntity.ok(userService.duplicateCheck(email));
    }

    //  회원 리스트 조회
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findUsers();
    }

    //  회원 수정
    @PreAuthorize("hasRole('ROLE_USER','ROLE_ADMIN')")
    @PutMapping("/users/{id}")
    public ResponseEntity<String> modifyProfiles(HttpServletRequest request, @PathVariable ("id") long userId,
                                                 @RequestBody @Valid MeRequestDto meRequestDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        request.getSession().setAttribute("user", userService.updateProfiles(userId, meRequestDto));

        return ResponseEntity.ok("프로필 수정이 완료되었습니다!");
    }

    //  회원 삭제
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteProfiles(@PathVariable ("id") long userId) {

        userService.delete(userId);

        return ResponseEntity.ok().body("탈퇴가 완료되었습니다.");
    }

    //  회원 비밀번호 변경

    @PutMapping("/users/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable ("id") long userId,
                                                 @RequestBody @Valid UpdatePasswordRequestDto passwordRequestDto,
                                                 BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        userService.updatePassword(userId, passwordRequestDto);

        return ResponseEntity.ok().body("비밀번호 수정이 완료되었습니다.");
    }
}
