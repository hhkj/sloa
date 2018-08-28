package cn.slkj.sloa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.slkj.sloa.Dao.StudentMapper;
import cn.slkj.sloa.Entity.Student;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(byte[] uid) {
        return studentMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(byte[] uid) {
        return studentMapper.selectByPrimaryKey(uid);
    }

    @Override
    public List<Student> selectByCondition(Student record) {
        return studentMapper.selectByCondition(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }
}
