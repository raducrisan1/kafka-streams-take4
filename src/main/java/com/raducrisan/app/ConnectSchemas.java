package com.raducrisan.app;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

public class ConnectSchemas {

    // Fields

    public static final String ID_FIELD = "id";
    public static final String CONTENT_FIELD = "content";

    // Schema names
    public static final String SCHEMA_KEY = "com.raducrisan.app.TextKey";
    public static final String SCHEMA_VALUE = "com.raducrisan.app.TextValue";

    public static final Schema KEY_SCHEMA = SchemaBuilder.struct().name(SCHEMA_KEY).version(1)
            .field(ID_FIELD, Schema.INT64_SCHEMA).build();

    public static final Schema VALUE_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE).version(1)
            .field(CONTENT_FIELD, Schema.STRING_SCHEMA).build();
}
