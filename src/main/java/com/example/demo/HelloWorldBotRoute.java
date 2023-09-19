package com.example.demo;

import io.github.ndanhkhoi.telegram.bot.annotation.BotRoute;
import io.github.ndanhkhoi.telegram.bot.annotation.CommandDescription;
import io.github.ndanhkhoi.telegram.bot.annotation.CommandMapping;

@BotRoute
public class HelloWorldBotRoute {

    @CommandDescription("Say hello world")
    @CommandMapping(value = "/hi", allowAllUserAccess = true)
    public String hi() {
        return "Hello world";
    }

}