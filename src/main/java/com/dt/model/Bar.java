package com.dt.model;

import com.dt.enums.Baz;
import lombok.Data;

import java.util.Map;

@Data
public class Bar {

    private Baz baz;

    private Map<Baz, String> bazMap;

}
