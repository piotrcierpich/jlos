package pl.kololos.api.utils;

import java.time.ZoneId;

public class Locals {
    public static final ZoneId ZONE_ID;

    static {
        ZONE_ID = ZoneId.of("Europe/Warsaw");
    }
}
