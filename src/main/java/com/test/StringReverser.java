package com.test;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

@Data
@Builder
public  class StringReverser {

    private String[] delemeter;

    private String[] pushDelemeter;

    private String stringToParse;

    public String reverse() {

        final StringBuilder builder = new StringBuilder();

        final Stack<String> stack = new Stack<>();

        assertThat(this.stringToParse).isNotNull();

        Arrays.asList(this.stringToParse.split("")).stream().forEach(ch -> {
            if (this.matchDelimeter(ch)) {
                if(builder.length() > 0)
                    stack.push(builder.toString());
                builder.delete(0, builder.length());
            }
            else if (this.matchDelimeterPush(ch)) {
                stack.push(builder.toString());
                stack.push(ch);
                builder.delete(0, builder.length());
            }
            else if(!ch.isEmpty())
                builder.append(ch);
        });
        if (builder.length() > 0) {
            stack.push(builder.toString());
        }

        StringBuilder result = new StringBuilder();

        while (stack.isEmpty() == false) {
            String str = stack.pop();
            result.append(str);
            if(!stack.isEmpty() && !stack.peek().equals(",") && !str.equals("?"))
                result.append(" ");
        }
        return result.toString();
    }

    private boolean matchDelimeter(String charater) {
        return this.checkCharacter(charater, this.delemeter);
    }

    private boolean matchDelimeterPush(String charater) {
        return this.checkCharacter(charater, this.pushDelemeter);
    }

    private boolean checkCharacter(String character, String[] match) {
        return Arrays.asList(match).stream().anyMatch(s -> s.equalsIgnoreCase(character));
    }
}