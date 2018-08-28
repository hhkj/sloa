package cn.slkj.sloa.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.slkj.sloa.Entity.Student;

@Repository
public interface StudentMapper {
	int deleteByPrimaryKey(byte[] uid);

	int insert(Student record);

	int insertSelective(Student record);

	Student selectByPrimaryKey(byte[] uid);

	List<Student> selectByCondition(Student record);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);
}