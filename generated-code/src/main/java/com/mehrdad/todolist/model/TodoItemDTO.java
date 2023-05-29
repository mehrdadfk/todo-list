package com.mehrdad.todolist.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * TodoItemDTO
 */

@JsonTypeName("TodoItem")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-29T17:31:52.843841+02:00[Europe/Berlin]")
public class TodoItemDTO {

  private String id;

  private String description;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    NOT_DONE("not done"),
    
    DONE("done"),
    
    PAST_DUE("past due");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime dueDateTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime completedAt;

  /**
   * Default constructor
   * @deprecated Use {@link TodoItemDTO#TodoItemDTO(String, String, StatusEnum, LocalDateTime, LocalDateTime)}
   */
  @Deprecated
  public TodoItemDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TodoItemDTO(String id, String description, StatusEnum status, LocalDateTime createdAt, LocalDateTime dueDateTime) {
    this.id = id;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
    this.dueDateTime = dueDateTime;
  }

  public TodoItemDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TodoItemDTO description(String description) {
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

  public TodoItemDTO status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @NotNull 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public TodoItemDTO createdAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @NotNull @Valid 
  @Schema(name = "createdAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("createdAt")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public TodoItemDTO dueDateTime(LocalDateTime dueDateTime) {
    this.dueDateTime = dueDateTime;
    return this;
  }

  /**
   * Get dueDateTime
   * @return dueDateTime
  */
  @NotNull @Valid 
  @Schema(name = "dueDateTime", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("dueDateTime")
  public LocalDateTime getDueDateTime() {
    return dueDateTime;
  }

  public void setDueDateTime(LocalDateTime dueDateTime) {
    this.dueDateTime = dueDateTime;
  }

  public TodoItemDTO completedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
    return this;
  }

  /**
   * Get completedAt
   * @return completedAt
  */
  @Valid 
  @Schema(name = "completedAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("completedAt")
  public LocalDateTime getCompletedAt() {
    return completedAt;
  }

  public void setCompletedAt(LocalDateTime completedAt) {
    this.completedAt = completedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TodoItemDTO todoItem = (TodoItemDTO) o;
    return Objects.equals(this.id, todoItem.id) &&
        Objects.equals(this.description, todoItem.description) &&
        Objects.equals(this.status, todoItem.status) &&
        Objects.equals(this.createdAt, todoItem.createdAt) &&
        Objects.equals(this.dueDateTime, todoItem.dueDateTime) &&
        Objects.equals(this.completedAt, todoItem.completedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, status, createdAt, dueDateTime, completedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TodoItemDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    dueDateTime: ").append(toIndentedString(dueDateTime)).append("\n");
    sb.append("    completedAt: ").append(toIndentedString(completedAt)).append("\n");
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

