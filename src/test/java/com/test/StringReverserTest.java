package com.test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringReverserTest {

    @org.junit.jupiter.api.Test
    void reverse() {
        List<String> list = Arrays.asList(
                "Are you twenty-one years old?",
                "Hello, how are you?",
                "Good afternoon"
        );

        list.stream().map(str->StringReverser.builder()
                .delemeter(new String[]{" "})
                .pushDelemeter(new String[]{",", "?"})
                .stringToParse(str)
                .build().reverse()).forEach(System.out::println);
    }
}