package com.secretary.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс Plans
 *
 * @author Max Ivanov
 * created 28.11.2021
 */

@Data
@AllArgsConstructor
public class Plans {
    /**
     * user's id
     */
    @JsonProperty("id")
    private int id;
    /**
     * user's plan
     */
    @JsonProperty("plan")
    private String plan;

    @Override
    public String toString() {
        if(getPlan() == null || getPlan().isEmpty()) return "Sorry, cant find plan :(";

        return " -" + plan + '\n';
    }
}
