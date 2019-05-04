package ProjektBus.Server.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueBusStopNameValidator.class)
public @interface UniqueBusStopName {
    String message() default "Bus stop with this name exists"; //TODO przenieść do pliku
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
