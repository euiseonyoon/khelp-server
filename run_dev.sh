#!/bin/bash

# Load environment variables from .env file
set -a
source .env
set +a

echo "========================================"
echo "Starting KHelp Backend (Dev Environment)"
echo "========================================"
echo "DB: $POSTGRES_DB @ localhost:$POSTGRES_PORT"
echo "User: $POSTGRES_USER"
echo "Profile: $SPRING_PROFILES_ACTIVE"
echo "========================================"

./gradlew bootRun
