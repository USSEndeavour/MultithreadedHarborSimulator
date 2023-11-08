# Multithreaded Port Simulation Application

## Overview

This project simulates a port operation where ships arrive to load and/or unload containers. The port has a limited number of docks and a storage area with a fixed capacity. The simulation is multithreaded, with each ship being represented by a separate thread that competes for dock resources.

## Features

- Multithreaded ships docking and undocking.
- Resource management to prevent dock overuse.
- Synchronization of ship operations to prevent data races.
- Exception handling for overflow and underflow of containers.

## How it Works

Ships arrive and request access to a dock. If a dock is available and the storage area of the port has enough capacity, a ship can proceed to load or unload its containers. The number of containers at the port and on the ship must not drop below zero or exceed the capacity of the port or ship.

## Project Structure

- `src/port/`
  - `Main.java`: The entry point of the application, setting up the simulation.
- `src/port/concurrent/`
  - `DockPool.java`: Manages the allocation and release of docks.
  - `DockClient.java`: Represents a ship and its behavior in the port.
- `src/port/models/`
  - `AbstractDock.java`: Abstract class for a dock in the port.
  - `Port.java`: Represents the port and its docks.
  - `Ship.java`: Represents a ship arriving at the port.
- `src/port/exceptions/`
  - `ContainerOverflowException.java`: Thrown when the port or ship container limit is exceeded.
  - `ContainerUnderflowException.java`: Thrown when there are no containers to unload.
  - `ResourceException.java`: Thrown when resources cannot be allocated within a certain time.
## Compiling and Running

- Compile the code from the root of the project directory:
  ```shell
  javac -d bin src/port/Main.java src/port/concurrent/*.java src/port/models/*.java src/port/exceptions/*.java -Xlint:unchecked
- Run the project from the /bin directory:
  ```shell
  java /port/Main