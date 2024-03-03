package com.gfg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int walletId;
    public Double balance;
    public String userId;

    @CreationTimestamp
    public Date creationOn;
    @UpdateTimestamp
    public Date updateOn;
}
