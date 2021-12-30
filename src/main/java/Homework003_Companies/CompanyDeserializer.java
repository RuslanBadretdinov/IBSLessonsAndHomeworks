package Homework003_Companies;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class CompanyDeserializer extends StdDeserializer {

    public CompanyDeserializer() {
        this(null);
    }

    public CompanyDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Company deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        long id = (Long) node.get("id").numberValue();
        String name = node.get("name").asText();
        String address = node.get("address").asText();
        String phoneNumber = node.get("phoneNumber").asText();
        long inn = (Long) node.get("inn").numberValue();
        String founded = node.get("founded").asText();
        return new Company(id, name, address, phoneNumber, inn, founded, null);
    }
}
