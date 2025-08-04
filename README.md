# Kafka Message Producer Project

## Project Goal

This project aims to produce messages to a Confluent Kafka topic named `THKLD01`. It provides a REST API to send messages, which will then be encoded with a custom encoding scheme (Korean characters to 'C' followed by 4 hexadecimal digits) before being sent to Kafka.

## Environment

This project requires **Java 11** or higher to run.

## Implementation Plan (Checklist)

- [ ] **1. `pom.xml` Configuration**
  - [x] Add Spring Kafka dependency.
  - [x] Add Spring Web dependency.

- [ ] **2. `application.properties` Configuration**
  - [x] Configure Kafka producer properties (bootstrap servers, serializers, etc.).

- [ ] **3. Package and Class Structure Setup**
  - [x] Create `controller` package.
  - [x] Create `service` package.

- [ ] **4. Kafka Producer (Service) Implementation**
  - [x] Create a Kafka producer service class (e.g., `KafkaProducerService`).
  - [x] Implement a method to send messages to the `THKLD01` topic.
  - [x] Implement custom encoding logic for Korean characters (e.g., `encodeCustomEncoding`).

- [ ] **5. Controller (REST API) Implementation**
  - [x] Create a REST controller class (e.g., `MessageController`).
  - [x] Implement an endpoint to receive messages and send them to Kafka using the producer service.
  - [x] Ensure proper HTTP methods (POST) and request formats.

- [ ] **6. HTTP Client Script for Testing**
  - [ ] Create a `test.http` file in the project root.
  - [ ] Add HTTP requests to test the message sending API endpoint.