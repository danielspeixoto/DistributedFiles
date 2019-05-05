#!/usr/bin/env bash
./gradlew build && ./gradlew shadowJar && java -jar build/libs/server-1.0-all.jar