package com.mehrdad.todolist.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateTodoItemDTO
 */

@JsonTypeName("UpdateTodoItem")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-29T17:31:52.843841+02:00[Europe/Berlin]")
public class UpdateTodoItemDTO {

  private String description;

  /**
   * Default constructor
   * @deprecated Use {@link UpdateTodoItemDTO#UpdateTodoItemDTO(String)}
   */
  @Deprecated
  public UpdateTodoItemDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UpdateTodoItemDTO(String description) {
    this.description = description;
  }

  public UpdateTodoItemDTO description(String description) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateTodoItemDTO updateTodoItem = (UpdateTodoItemDTO) o;
    return Objects.equals(this.description, updateTodoItem.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateTodoItemDTO {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

