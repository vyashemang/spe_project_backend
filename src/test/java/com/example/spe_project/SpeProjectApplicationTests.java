package com.example.spe_project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({"/data.sql"})
class SpeProjectApplicationTests {

    @Test
    void contextLoads() {
    }

}
