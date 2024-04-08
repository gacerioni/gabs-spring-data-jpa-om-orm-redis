package io.platformengineer.demo.repository.redis;


import com.redis.om.spring.repository.RedisDocumentRepository;
import io.platformengineer.demo.model.redis.StudentRedis;

public interface StudentRedisRepository extends RedisDocumentRepository<StudentRedis, String> {

    Iterable<StudentRedis> findByAgeBetween(int minAge, int maxAge);

}