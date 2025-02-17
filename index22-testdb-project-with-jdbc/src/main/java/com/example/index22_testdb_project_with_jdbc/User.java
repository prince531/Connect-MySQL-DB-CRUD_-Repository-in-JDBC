package com.example.index22_testdb_project_with_jdbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
@Table("MYTABLE")
@Data
public class User {
    @Id
     int id;
     String name;
    
}
