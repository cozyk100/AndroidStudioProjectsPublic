package com.example.mockk

class Car {
    private var doorType: DoorType = DoorType.REAR_RIGHT
    private val windowStateMap = mapOf(
        DoorType.FRONT_LEFT to WindowState.DOWN,
        DoorType.FRONT_RIGHT to WindowState.DOWN,
        DoorType.REAR_LEFT to WindowState.DOWN,
        DoorType.REAR_RIGHT to WindowState.DOWN)

    fun drive(direction: Direction): Outcome {
        return Outcome.NG
    }

    fun recordTelemetry(speed: Double, direction: Direction, lat: Double = 0.0, long: Double = 0.0): Outcome {
        return Outcome.NG
    }

    fun door(doorType: DoorType): Car {
        this.doorType = doorType
        return this
    }

    fun windowState(): WindowState {
        return this.windowStateMap[this.doorType]!!
    }

    fun accelerate(fromSpeed: Double, toSpeed: Double): Outcome {
        return Outcome.NG
    }
}