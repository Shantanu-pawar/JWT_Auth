package com.SpringJWT_auth.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    private String userId;
    private String name;
    private int price;
}
