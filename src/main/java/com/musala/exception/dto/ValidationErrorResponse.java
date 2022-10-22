package com.musala.exception.dto;

import com.musala.exception.dto.Violation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
  private List<Violation> violations = new ArrayList<>();
}
