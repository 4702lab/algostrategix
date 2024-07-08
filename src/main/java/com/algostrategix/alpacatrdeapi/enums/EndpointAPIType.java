package com.algostrategix.alpacatrdeapi.enums;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.SerializedName;


/**
 * Defines the API endpoints to use with Alpaca.
 * <p>
 *
 *
 */
public enum EndpointAPIType {

    @SerializedName("live")
    LIVE("live"),
    @SerializedName("paper")
    PAPER("paper");
    private final String value;
    private final static Map<String, EndpointAPIType> CONSTANTS = new HashMap<>();

    static {
        for (EndpointAPIType c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    EndpointAPIType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }

    public static EndpointAPIType fromValue(String value) {
        EndpointAPIType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
