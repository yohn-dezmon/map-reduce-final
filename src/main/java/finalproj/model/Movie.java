/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package finalproj.model;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Movie extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8140288460142937592L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Movie\",\"namespace\":\"finalproj.model\",\"fields\":[{\"name\":\"id\",\"type\":\"string\",\"doc\":\"The ID of the movie\",\"default\":\"\"},{\"name\":\"title\",\"type\":\"string\",\"doc\":\"The title of the movie\",\"default\":\"\"},{\"name\":\"year\",\"type\":\"string\",\"doc\":\"The year the movie was released\",\"default\":\"\"},{\"name\":\"genre\",\"type\":{\"type\":\"enum\",\"name\":\"MovieGenre\",\"symbols\":[\"Comedy\",\"Romance\",\"Adventure\",\"Action\",\"Animation\",\"Children\",\"Crime\",\"Documentary\",\"Drama\",\"Fantasy\",\"FilmNoir\",\"Horror\",\"Musical\",\"Mystery\",\"SciFi\",\"Thriller\",\"War\",\"Western\",\"nogenreslisted\",\"IMAX\"]},\"doc\":\"Genre of the movie\",\"default\":\"nogenreslisted\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** The ID of the movie */
  @Deprecated public java.lang.CharSequence id;
  /** The title of the movie */
  @Deprecated public java.lang.CharSequence title;
  /** The year the movie was released */
  @Deprecated public java.lang.CharSequence year;
  /** Genre of the movie */
  @Deprecated public finalproj.model.MovieGenre genre;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Movie() {}

  /**
   * All-args constructor.
   * @param id The ID of the movie
   * @param title The title of the movie
   * @param year The year the movie was released
   * @param genre Genre of the movie
   */
  public Movie(java.lang.CharSequence id, java.lang.CharSequence title, java.lang.CharSequence year, finalproj.model.MovieGenre genre) {
    this.id = id;
    this.title = title;
    this.year = year;
    this.genre = genre;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return title;
    case 2: return year;
    case 3: return genre;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: title = (java.lang.CharSequence)value$; break;
    case 2: year = (java.lang.CharSequence)value$; break;
    case 3: genre = (finalproj.model.MovieGenre)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The ID of the movie
   */
  public java.lang.CharSequence getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * The ID of the movie
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'title' field.
   * @return The title of the movie
   */
  public java.lang.CharSequence getTitle() {
    return title;
  }

  /**
   * Sets the value of the 'title' field.
   * The title of the movie
   * @param value the value to set.
   */
  public void setTitle(java.lang.CharSequence value) {
    this.title = value;
  }

  /**
   * Gets the value of the 'year' field.
   * @return The year the movie was released
   */
  public java.lang.CharSequence getYear() {
    return year;
  }

  /**
   * Sets the value of the 'year' field.
   * The year the movie was released
   * @param value the value to set.
   */
  public void setYear(java.lang.CharSequence value) {
    this.year = value;
  }

  /**
   * Gets the value of the 'genre' field.
   * @return Genre of the movie
   */
  public finalproj.model.MovieGenre getGenre() {
    return genre;
  }

  /**
   * Sets the value of the 'genre' field.
   * Genre of the movie
   * @param value the value to set.
   */
  public void setGenre(finalproj.model.MovieGenre value) {
    this.genre = value;
  }

  /**
   * Creates a new Movie RecordBuilder.
   * @return A new Movie RecordBuilder
   */
  public static finalproj.model.Movie.Builder newBuilder() {
    return new finalproj.model.Movie.Builder();
  }

  /**
   * Creates a new Movie RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Movie RecordBuilder
   */
  public static finalproj.model.Movie.Builder newBuilder(finalproj.model.Movie.Builder other) {
    return new finalproj.model.Movie.Builder(other);
  }

  /**
   * Creates a new Movie RecordBuilder by copying an existing Movie instance.
   * @param other The existing instance to copy.
   * @return A new Movie RecordBuilder
   */
  public static finalproj.model.Movie.Builder newBuilder(finalproj.model.Movie other) {
    return new finalproj.model.Movie.Builder(other);
  }

  /**
   * RecordBuilder for Movie instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Movie>
    implements org.apache.avro.data.RecordBuilder<Movie> {

    /** The ID of the movie */
    private java.lang.CharSequence id;
    /** The title of the movie */
    private java.lang.CharSequence title;
    /** The year the movie was released */
    private java.lang.CharSequence year;
    /** Genre of the movie */
    private finalproj.model.MovieGenre genre;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(finalproj.model.Movie.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.title)) {
        this.title = data().deepCopy(fields()[1].schema(), other.title);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.year)) {
        this.year = data().deepCopy(fields()[2].schema(), other.year);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.genre)) {
        this.genre = data().deepCopy(fields()[3].schema(), other.genre);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Movie instance
     * @param other The existing instance to copy.
     */
    private Builder(finalproj.model.Movie other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.title)) {
        this.title = data().deepCopy(fields()[1].schema(), other.title);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.year)) {
        this.year = data().deepCopy(fields()[2].schema(), other.year);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.genre)) {
        this.genre = data().deepCopy(fields()[3].schema(), other.genre);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * The ID of the movie
      * @return The value.
      */
    public java.lang.CharSequence getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * The ID of the movie
      * @param value The value of 'id'.
      * @return This builder.
      */
    public finalproj.model.Movie.Builder setId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * The ID of the movie
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * The ID of the movie
      * @return This builder.
      */
    public finalproj.model.Movie.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'title' field.
      * The title of the movie
      * @return The value.
      */
    public java.lang.CharSequence getTitle() {
      return title;
    }

    /**
      * Sets the value of the 'title' field.
      * The title of the movie
      * @param value The value of 'title'.
      * @return This builder.
      */
    public finalproj.model.Movie.Builder setTitle(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.title = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'title' field has been set.
      * The title of the movie
      * @return True if the 'title' field has been set, false otherwise.
      */
    public boolean hasTitle() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'title' field.
      * The title of the movie
      * @return This builder.
      */
    public finalproj.model.Movie.Builder clearTitle() {
      title = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'year' field.
      * The year the movie was released
      * @return The value.
      */
    public java.lang.CharSequence getYear() {
      return year;
    }

    /**
      * Sets the value of the 'year' field.
      * The year the movie was released
      * @param value The value of 'year'.
      * @return This builder.
      */
    public finalproj.model.Movie.Builder setYear(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.year = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'year' field has been set.
      * The year the movie was released
      * @return True if the 'year' field has been set, false otherwise.
      */
    public boolean hasYear() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'year' field.
      * The year the movie was released
      * @return This builder.
      */
    public finalproj.model.Movie.Builder clearYear() {
      year = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'genre' field.
      * Genre of the movie
      * @return The value.
      */
    public finalproj.model.MovieGenre getGenre() {
      return genre;
    }

    /**
      * Sets the value of the 'genre' field.
      * Genre of the movie
      * @param value The value of 'genre'.
      * @return This builder.
      */
    public finalproj.model.Movie.Builder setGenre(finalproj.model.MovieGenre value) {
      validate(fields()[3], value);
      this.genre = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'genre' field has been set.
      * Genre of the movie
      * @return True if the 'genre' field has been set, false otherwise.
      */
    public boolean hasGenre() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'genre' field.
      * Genre of the movie
      * @return This builder.
      */
    public finalproj.model.Movie.Builder clearGenre() {
      genre = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public Movie build() {
      try {
        Movie record = new Movie();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.title = fieldSetFlags()[1] ? this.title : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.year = fieldSetFlags()[2] ? this.year : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.genre = fieldSetFlags()[3] ? this.genre : (finalproj.model.MovieGenre) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}