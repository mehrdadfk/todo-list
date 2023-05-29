package com.mehrdad.todolist.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * NewTodoItemDTO
 */

@JsonTypeName("NewTodoItem")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-29T17:31:52.843841+02:00[Europe/Berlin]")
public class NewTodoItemDTO {

  private String description;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dueDateTime;

  /**
   * Default constructor
   * @deprecated Use {@link NewTodoItemDTO#NewTodoItemDTO(String)}
   */
  @Deprecated
  public NewTodoItemDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public NewTodoItemDTO(String description) {
    this.description = description;
  }

  public NewTodoItemDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @NotNull 
  @Schema(name = "description", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NewTodoItemDTO dueDateTime(LocalDateTime dueDateTime) {
    this.dueDateTime = dueDateTime;
    return this;
  }

  /**
   * Get dueDateTime
   * @return dueDateTime
  */
  @Valid 
  @Schema(name = "dueDateTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dueDateTime")
  public LocalDateTime getDueDateTime() {
    return dueDateTime;
  }

  public void setDueDateTime(LocalDateTime dueDateTime) {
    this.dueDateTime = dueDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewTodoItemDTO newTodoItem = (NewTodoItemDTO) o;
    return Objects.equals(this.description, newTodoItem.description) &&
        Objects.equals(this.dueDateTime, newTodoItem.dueDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, dueDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewTodoItemDTO {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    dueDateTime: ").append(toIndentedString(dueDateTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

