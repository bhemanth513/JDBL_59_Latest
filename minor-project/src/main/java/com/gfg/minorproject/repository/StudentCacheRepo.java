package com.gfg.minorproject.repository;

import com.gfg.minorproject.dto.StudentResponse;
import com.gfg.minorproject.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class StudentCacheRepo {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private static final String STUDENT_KEY_PREFIX = "std::";
    public void set(StudentResponse studentResponse){
        String key = STUDENT_KEY_PREFIX+studentResponse.getId();
        redisTemplate.opsForValue().set(key,studentResponse,3600, TimeUnit.SECONDS);
    }

    public StudentResponse getStudent(int studentId){
        if(studentId == 0) return null;
        String key = STUDENT_KEY_PREFIX + studentId;
         return (StudentResponse) redisTemplate.opsForValue().get(key);

    }
}
