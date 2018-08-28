package cn.slkj.sloa.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.slkj.sloa.Entity.Student;
import cn.slkj.sloa.Service.IStudentService;

@RestController
@RequestMapping("/api/Student")
public class StudentController {
    @Autowired
    private IStudentService service;

    @GetMapping()
    public String Get() {
        List<Student> students = service.selectByCondition(new Student());
        String jsonResult = com.alibaba.fastjson.JSON.toJSONString(students);
        return jsonResult;
    }
}
