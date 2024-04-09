package io.platformengineer.demo.model.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@Document(prefixes = {"io.platformengineer.demo.model.redis.StudentRedis:", "student:"})
@TypeAlias("StudentRedis")
public class StudentRedis  {

    @Id
    private String id;

    // Indexed for exact text matching
    @Indexed @NonNull
    private String first_name;

    @Indexed @NonNull
    private String last_name;

    @Indexed @NonNull
    private Integer age;

    @Searchable @NonNull
    private String email;


}
