package org.matera.fff.screenings.api

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import java.time.LocalDateTime

@Introspected
class NewScreening {
    @NotBlank
    String screeningRoomShortName
    @NotEmpty
    List<String> filmIds
    @NonNull
    LocalDateTime start
    String comment
    @Nullable
    @Min(1L)
    Integer extraLength
}
