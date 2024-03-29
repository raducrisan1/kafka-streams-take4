/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.raducrisan.app;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Count extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -7841863370706398978L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Count\",\"namespace\":\"com.raducrisan.app\",\"fields\":[{\"name\":\"amount\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Count> ENCODER =
      new BinaryMessageEncoder<Count>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Count> DECODER =
      new BinaryMessageDecoder<Count>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Count> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Count> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Count>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Count to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Count from a ByteBuffer. */
  public static Count fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int amount;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Count() {}

  /**
   * All-args constructor.
   * @param amount The new value for amount
   */
  public Count(java.lang.Integer amount) {
    this.amount = amount;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return amount;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: amount = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'amount' field.
   * @return The value of the 'amount' field.
   */
  public java.lang.Integer getAmount() {
    return amount;
  }

  /**
   * Sets the value of the 'amount' field.
   * @param value the value to set.
   */
  public void setAmount(java.lang.Integer value) {
    this.amount = value;
  }

  /**
   * Creates a new Count RecordBuilder.
   * @return A new Count RecordBuilder
   */
  public static com.raducrisan.app.Count.Builder newBuilder() {
    return new com.raducrisan.app.Count.Builder();
  }

  /**
   * Creates a new Count RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Count RecordBuilder
   */
  public static com.raducrisan.app.Count.Builder newBuilder(com.raducrisan.app.Count.Builder other) {
    return new com.raducrisan.app.Count.Builder(other);
  }

  /**
   * Creates a new Count RecordBuilder by copying an existing Count instance.
   * @param other The existing instance to copy.
   * @return A new Count RecordBuilder
   */
  public static com.raducrisan.app.Count.Builder newBuilder(com.raducrisan.app.Count other) {
    return new com.raducrisan.app.Count.Builder(other);
  }

  /**
   * RecordBuilder for Count instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Count>
    implements org.apache.avro.data.RecordBuilder<Count> {

    private int amount;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.raducrisan.app.Count.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.amount)) {
        this.amount = data().deepCopy(fields()[0].schema(), other.amount);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Count instance
     * @param other The existing instance to copy.
     */
    private Builder(com.raducrisan.app.Count other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.amount)) {
        this.amount = data().deepCopy(fields()[0].schema(), other.amount);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'amount' field.
      * @return The value.
      */
    public java.lang.Integer getAmount() {
      return amount;
    }

    /**
      * Sets the value of the 'amount' field.
      * @param value The value of 'amount'.
      * @return This builder.
      */
    public com.raducrisan.app.Count.Builder setAmount(int value) {
      validate(fields()[0], value);
      this.amount = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'amount' field has been set.
      * @return True if the 'amount' field has been set, false otherwise.
      */
    public boolean hasAmount() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'amount' field.
      * @return This builder.
      */
    public com.raducrisan.app.Count.Builder clearAmount() {
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Count build() {
      try {
        Count record = new Count();
        record.amount = fieldSetFlags()[0] ? this.amount : (java.lang.Integer) defaultValue(fields()[0]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Count>
    WRITER$ = (org.apache.avro.io.DatumWriter<Count>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Count>
    READER$ = (org.apache.avro.io.DatumReader<Count>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
