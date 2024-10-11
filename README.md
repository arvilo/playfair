# PlayFair Cipher

This project implements the PlayFair cipher for encoding text using a key. The project includes a console application where users can input a key and text to see the encoded result, as well as unit tests to verify the functionality.

## Project Structure

- `az.arvilo.PlayFair`: Contains the core PlayFair cipher logic.
- `az.arvilo.PlayFairApp`: Console application for interacting with the PlayFair cipher.
- `az.arvilo.exception.EdgeCaseException`: Custom exception class for handling edge cases.
- `az.arvilo.PlayFairTest`: Unit tests for verifying the PlayFair encoding.

## Prerequisites

- Java 21
- Gradle

## Clone the Repository

To clone this repository to your local machine, use the following command:

```bash
git clone https://github.com/arvilo/playfair.git
```
To run app, use the following command:
```
./gradlew build
java -jar build/libs/playfair-1.0-SNAPSHOT.jar
```