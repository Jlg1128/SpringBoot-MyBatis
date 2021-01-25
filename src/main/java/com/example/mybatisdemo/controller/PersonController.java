package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.Responese.MyResponese;
import com.example.mybatisdemo.domin.Person;
import com.example.mybatisdemo.vo.ResultVO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Validated
@RestController
@RequestMapping(value = "/api/person")
public class PersonController {
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> savePerson(@RequestBody @Valid Person person,
                                             HttpServletRequest request
                                             ) {
        System.out.println(person);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("person", person);
        ResultVO resultVO = MyResponese.success(null);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/getPerson")
    public ResponseEntity<String> getPerson(@RequestParam("classId") @Valid @NotEmpty(message = "classID不能为空") String classId) {
        return ResponseEntity.ok(classId);
    }
}
