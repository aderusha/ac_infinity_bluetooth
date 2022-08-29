package com.google.gson;

import com.google.gson.stream.JsonReader;
import java.io.IOException;

public interface ToNumberStrategy {
    Number readNumber(JsonReader jsonReader) throws IOException;
}
