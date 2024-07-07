package org.john.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONUtils {

    private final static Logger LOGGER = Logger.getLogger(JSONUtils.class.getName());

    public static String toJSON(Object obj) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        try {
            return ow.writeValueAsString(obj);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        return null;
    }
}
