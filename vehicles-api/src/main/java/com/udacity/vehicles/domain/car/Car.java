package com.udacity.vehicles.domain.car;

import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.Location;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Declares the Car class, related variables and methods.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Valid
    @Embedded
    private Details details = new Details();

    @Valid
    @Embedded
    private Location location = new Location(0d, 0d);

    @Transient
    private String price;
}
