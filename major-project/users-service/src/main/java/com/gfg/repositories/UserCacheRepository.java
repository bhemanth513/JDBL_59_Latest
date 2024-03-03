package com.gfg.repositories;

import com.gfg.model.User;
import com.gfg.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserCacheRepository {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public void create(User user){
        this.redisTemplate.opsForValue().set(getKey(user.getUserId()),user,Constants.USER_REDIS_KEY_EXPIRE, TimeUnit.SECONDS);
    }

    public User getUser(int userId){
        return (User) this.redisTemplate.opsForValue().get(getKey(userId));
    }

    private String getKey(int userId){
        return Constants.USER_REDIS_KEY_PREFIX+userId;
    }
}
