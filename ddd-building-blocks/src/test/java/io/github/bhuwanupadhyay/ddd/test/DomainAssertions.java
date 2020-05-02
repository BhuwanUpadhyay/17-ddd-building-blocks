package io.github.bhuwanupadhyay.ddd.test;

import io.github.bhuwanupadhyay.ddd.DomainError;
import io.github.bhuwanupadhyay.ddd.DomainValidationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.AbstractAssert;

public final class DomainAssertions {

  public static DomainValidationAssert assertThat(Runnable callback) {
    try {
      callback.run();
      return new DomainValidationAssert(null);
    } catch (DomainValidationException e) {
      return new DomainValidationAssert(e);
    }
  }

  public static class DomainValidationAssert
      extends AbstractAssert<DomainValidationAssert, DomainValidationException> {

    private DomainValidationAssert(DomainValidationException exception) {
      super(exception, DomainValidationAssert.class);
    }

    private DomainValidationAssert hasErrors() {

      if (this.actual == null) {
        failWithMessage("Domain errors does not occurred.");
      }

      List<DomainError> domainErrors = this.actual.getDomainErrors();

      if (domainErrors.isEmpty()) {
        failWithMessage("Domain validation does not have any errors.");
      }
      return this;
    }

    public DomainValidationAssert hasNoErrors() {
      if (this.actual != null) {
        failWithMessage(
            "Found total <%d> domain errors but expected zero errors.%s",
            errorCodes().count(), prettyCodeErrors());
      }

      return this;
    }

    public DomainValidationAssert hasErrorCode(String errorCode) {
      this.hasErrors();

      long count =
          errorCodes().filter(Objects::nonNull).filter(error -> error.endsWith(errorCode)).count();

      if (count != 1) {
        failWithMessage(
            "%s exists <%d> times on errors but expected <1> times.%s",
            errorCode, count, prettyCodeErrors());
      }
      return this;
    }

    private Stream<String> errorCodes() {
      return this.actual.getDomainErrors().stream().map(DomainError::getErrorCode);
    }

    private String prettyCodeErrors() {
      return String.format(
          "\n--------\nACTUAL_ERRORS: [\n%s\n]\n--------\n",
          errorCodes().collect(Collectors.joining(",\n")));
    }
  }
}