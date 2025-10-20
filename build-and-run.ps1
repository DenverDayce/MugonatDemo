# Build and run helper for Windows PowerShell
# Requires JDK 21 installed and JAVA_HOME set (or available on PATH)

# Compile (use UTF-8 encoding and Java 21 release flag)
# Requires JDK 21 on PATH or JAVA_HOME
if (-not (Test-Path -Path out)) { New-Item -ItemType Directory -Path out | Out-Null }
javac --release 21 -encoding UTF-8 -d out -sourcepath src src\*.java; if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

# Run
java -cp out Main
