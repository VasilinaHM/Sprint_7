package org.example;

import static org.example.Utils.randomString;

public class CourierGenerator {

    public static CreateCourier randomCourier() {
        return new CreateCourier()
                .setLogin(randomString(5))
                .setPassword(randomString(8))
                .setFirstName(randomString(10));
    }
}
