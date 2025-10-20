Mugonat Demo Employee System

This small demo is a plain Java project (no package declarations) with three source files under `src/`.

Goal: upgrade to Java 21 (LTS).

Prerequisites
- JDK 21 installed and available on PATH or set via JAVA_HOME.
- Maven (optional) if you prefer building with Maven.

Quick run (PowerShell)

# Compile and run using the included PowerShell helper
.\build-and-run.ps1

Using Maven

# Compile and package (produces jar under target)
mvn clean package

# Run the packaged jar (jar-with-dependencies)
java -jar target\mugonat-employee-system-1.0.0-jar-with-dependencies.jar

Notes
- A `pom.xml` was added to enforce Java 21 when building with Maven (maven-compiler-plugin with <release>21).
- If you use an IDE, configure its project SDK to JDK 21.
