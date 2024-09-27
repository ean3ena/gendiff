.DEFAULT_GOAL := build-run

setup:
	make -C setup

clean:
	make -C clean

build:
	make -C build

install:
	make -C install

run-dist:
	make -C run-dist

preset:
	make -C preset

run:
	make -C run

test:
	make -C test

report:
	make -C report

lint:
	make -C lint

check-deps:
	make -C check-deps

build-run: make -C build-run

.PHONY: build
