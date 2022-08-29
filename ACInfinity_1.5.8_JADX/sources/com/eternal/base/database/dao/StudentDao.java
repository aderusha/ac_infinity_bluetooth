package com.eternal.base.database.dao;

import androidx.paging.DataSource;
import com.eternal.base.database.entity.Student;
import java.util.List;

public interface StudentDao {
    DataSource.Factory<Integer, Student> getAllStudent();

    List<Student> getStudents(String str);

    void insert(Student student);

    void insert(List<Student> list);
}
