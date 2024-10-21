package com.example.personagen.Data.Model;

import java.util.List;

public class ApiResponce {
    public List<User> results;

    public ApiResponce(List<User> results) {
        this.results = results;
    }
}
