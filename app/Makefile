.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	@ ./build/install/app/bin/app

preset-json-stylish:
	@ ./build/install/app/bin/app  src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json

preset-yaml-plain:
	@ ./build/install/app/bin/app  src/test/resources/fixtures/file1.yml src/test/resources/fixtures/file2.yml -f plain

preset-yaml-json:
	@ ./build/install/app/bin/app  src/test/resources/fixtures/file1.yml src/test/resources/fixtures/file2.yml -f json

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release

build-run: build run

.PHONY: build
