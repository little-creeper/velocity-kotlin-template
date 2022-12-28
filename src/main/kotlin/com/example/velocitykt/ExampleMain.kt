/*
* An example of a main class for a plugin.
*/
package com.example.velocitykt

// Imports:
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import org.slf4j.Logger

@Plugin(
    id = "example",
    name = "ExamplePlugin",
    version = "0.1.0",
    description = "A simple example plugin written in Kotlin.",
    authors = ["You"],
)
class ExampleMain @Inject constructor(
    private val proxy: ProxyServer,
    private val logger: Logger,
) {
    @Subscribe fun onProxyInitialize(event: ProxyInitializeEvent) {
        /*
        * This function is a listener for the ProxyInitializeEvent.
        * You would typically register listeners & commands here,
        * along with any other stuff you need to do before accepting connections.
        */

        logger.info("Hello, Velocity!")
    }
}