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

preset-json-12:
	@ ./build/install/app/bin/app  src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json

preset-json-34:
	@ ./build/install/app/bin/app  src/test/resources/fixtures/file3.json src/test/resources/fixtures/file4.json

preset-yaml-12:
	@ ./build/install/app/bin/app  src/test/resources/fixtures/file1.yaml src/test/resources/fixtures/file2.yaml

preset-yaml-34:
	@ ./build/install/app/bin/app  src/test/resources/fixtures/file3.yml src/test/resources/fixtures/file4.yml

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
