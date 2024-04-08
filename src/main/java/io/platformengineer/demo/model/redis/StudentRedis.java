package io.platformengineer.demo.model.redis;

import java.util.Set;

import io.platformengineer.demo.model.generics.IStudent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.geo.Point;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Document
@TypeAlias("StudentRedis")
public class StudentRedis  {

    @Id
    @Indexed
    private String id;

    // Indexed for exact text matching
    @Indexed @NonNull
    private String firstName;

    @Indexed @NonNull
    private String lastName;

    @Indexed @NonNull
    private Integer age;

    @Searchable @NonNull
    private String email;


}
