package com.mediading.stealthystriver.network;

import com.mediading.stealthystriver.network.api.ApiUserService;

public class StealthyStriverNetwork {
    private static ApiUserService userService = ServiceCreator.createService(ApiUserService.class);

}
